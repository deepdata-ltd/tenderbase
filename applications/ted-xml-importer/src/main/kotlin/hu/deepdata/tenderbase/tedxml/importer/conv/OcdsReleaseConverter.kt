/*
 *    Copyright 2018 DeepData Ltd.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package hu.deepdata.tenderbase.tedxml.importer.conv

import hu.deepdata.tenderbase.ocds.jackson.*
import hu.deepdata.tenderbase.tedxml.simplexml.model.*
import hu.deepdata.tenderbase.tedxml.simplexml.parser.*
import org.apache.commons.codec.digest.*
import java.util.*

/**
 * @author Zsolt Jurányi
 */
class OcdsReleaseConverter(val ocidPrefix: String) : IConverter<TedExport, ReleaseSchema> {

	companion object {
		val BUYER = "buyer"
		val SUPPLIER = "supplier"
		val TENDERER = "tenderer"
	}

	override fun convert(input: TedExport): ReleaseSchema {
		with(Core(TedExportParser(input), ocidPrefix)) {
			val awardContracts = parser.awardContracts()
			val buyerOrg = buyerOrg(parser.buyer())
			val supplierOrgs = awardContracts
					.map(parser::winners).flatten()
					.map(this::supplierOrg)
			val supplierRefs = supplierOrgs.mapNotNull(this::organizationReference)

			return ReleaseSchema().apply {
				this.awards = awards(awardContracts).toSet()
				buyer = organizationReference(buyerOrg)
				date = parser.publicationDate()
				id = noticeId()
				initiationType = ReleaseSchema.InitiationType.TENDER
				language = parser.language()
				ocid = tenderId()
				parties = setOf(buyerOrg, *supplierOrgs.toTypedArray())
				tag = listOfNotNull(releaseTag(input.codedDataSection.codifData.tdDocumentType))
				tender = tender(awardContracts, supplierRefs)
			}
		}

	}

	class Core(val parser: TedExportParser, val ocidPrefix: String) {

		fun address(body: ContactContractingBody?): Address? {
			val a = Address().apply {
				countryName = body?.country
				locality = body?.town
				postalCode = body?.postalCode
				streetAddress = body?.address
			}
			val s = listOfNotNull(a.countryName, a.locality, a.postalCode, a.region, a.streetAddress).joinToString()
			return if (s.isBlank()) null else a
		}

		fun amount(amount: Double?, currencyStr: String?) = when (amount) {
			null -> null
			else -> Amount().apply {
				this.amount = amount
				this.currency = currency(currencyStr)
			}
		}

		fun amount(v: ValueRange?) = amount(v?.high, v?.currency?.name)

		fun awardCriteria(v: TextAndCode?) = when (v?.code?.toUpperCase() ?: "") {
		// TODO add enum class to OCDS model? (write into docs then)
			"1" -> "priceOnly" // Lowest price
			"2" -> "ratedCriteria" // The most economic tender
			else -> null
		}

		fun awardCriteriaDefinitionToString(prefix: String, acDefinition: AcDefinition?): String {
			val s = StringBuilder()
			if (null != acDefinition) {
				s.append("- $prefix")
				if (null != acDefinition.acCriterion) s.append(": ${acDefinition.acCriterion}")
				if (null != acDefinition.acWeighting) s.append(" (${acDefinition.acWeighting})")
				s.append("\n")
			}
			return s.toString()
		}

		fun awardCriteriaDetails(): String {
			val s = StringBuilder(parser.codif().acAwardCrit?.value + "\n\n")
			parser.objects().forEach { oc ->
				oc.objectDescrs.forEach { od ->
					val a = StringBuilder()
					if (null != od.acProcurementDoc) a.append("- Procurement documentation criteria\n")

					val acs = listOfNotNull(
							od.directive200981EC,
							od.directive201423EU,
							od.directive201424EU,
							od.directive201425EU,
							od.ac,
							Ac().apply {
								acCosts = od.acCosts
								acCriterions = od.acCriterions
								acPrice = od.acPrice
								acQualitys = od.acQualitys
							}
					)

					acs.mapNotNull(Ac::acPrice).firstOrNull()?.also {
						a.append(awardCriteriaDefinitionToString("Price", it))
					}

					acs.mapNotNull(Ac::acCosts).flatten().forEach {
						a.append(awardCriteriaDefinitionToString("Cost", it))
					}

					acs.mapNotNull(Ac::acQualitys).flatten().forEach {
						a.append(awardCriteriaDefinitionToString("Quality", it))
					}

					acs.mapNotNull(Ac::acCriterias).flatten().forEach {
						a.append(awardCriteriaDefinitionToString("Criteria", it))
					}

					acs.mapNotNull(Ac::acCriterions).flatten().forEach {
						a.append("- Criterion: $it\n")
					}

					if (!a.isEmpty()) {
						s.append("Award criteria for item ${od.item ?: 0}:\n$a\n")
					}
				}
			}
			return s.toString().trim()
		}

		fun awards(awardContracts: List<AwardContract>) =
				awardContracts.mapIndexed { i, awardContract ->
					val awardedContract = awardContract.awardedContract
					val supplierRefs = parser.winners(awardContract)
							.map(this::supplierOrg)
							.map(this::organizationReference)
					Award().apply {
						date = awardedContract?.dateConclusionContract
						id = "${tenderId()}:award:$i"
						status = awardStatus(awardContract)
						suppliers = supplierRefs.toSet()
						title = awardContract.title
						value = amount(parser.awardValue(awardedContract))
					}
				}

		fun awardStatus(awardContract: AwardContract) = when {
			null == awardContract.noAwardedContract -> Award.Status.ACTIVE
			null != awardContract.noAwardedContract?.procurementUnsuccessful -> Award.Status.UNSUCCESSFUL
			else -> Award.Status.CANCELLED
		}

		fun buyerOrg(body: ContactContractingBody?) = when (body) {
			null -> null
			else -> organization(body, BUYER)
		}

		fun classificationFromCpv(cpv: TextAndCode?) = if (null != cpv) Classification().apply {
			this.description = cpv.value
			this.id = cpv.code
			this.scheme = "CPV"
		} else null

		fun contactPoint(body: ContactContractingBody?): ContactPoint? {
			val cp = ContactPoint().apply {
				email = body?.email
				faxNumber = body?.fax
				name = body?.contactPoint
				telephone = body?.phone
				url = parser.url(body)
			}
			val s = listOfNotNull(cp.email, cp.faxNumber, cp.name, cp.telephone, cp.url?.toString()).joinToString()
			return if (s.isBlank()) null else cp
		}

		fun currency(currencyStr: String?) = try {
			Amount.Currency.valueOf(currencyStr?.toUpperCase() ?: "")
		} catch (e: Exception) {
			// TODO log unknown currency (if not null)
			null
		}

		fun eligibilityCriteria(lefti: Lefti?): String? {
			val s = StringBuilder()
			lefti?.suitability?.also { s.append("Suitability:\n\n$it\n\n") }
			lefti?.economicFinancialInfo?.also { s.append("Economic/financial eligibility:\n\n$it\n\n") }
			lefti?.economicFinancialMinLevel?.also { s.append("Economic/financial minimum level:\n\n$it\n\n") }
			lefti?.technicalProfessionalInfo?.also { s.append("Technical/professional eligibility:\n\n$it\n\n") }
			lefti?.technicalProfessionalMinLevel?.also { s.append("Technical/professional minimum level:\n\n$it\n\n") }
			return s.toString().trim()
		}

		fun identifier(body: ContactContractingBody): Identifier = Identifier().apply {
			val cleanedName = body.officialName
					?.toUpperCase()
					?.replace(Regex("(\\s|[.,:;!?'/&+*=%—(){}]|-|\\\\|\\[|]|\")+"), "") ?: ""
			id = ocidPrefix + ":org:" + DigestUtils.sha1Hex(cleanedName)
			legalName = body.officialName
			scheme = ocidPrefix
			// TODO URI?
		}

		fun item(item: Int?, shortDescr: String?, mainCpvs: CpvSet?, additionalCpvs: List<CpvSet>?): Item? {
			val mainCpvArray = mainCpvs?.cpvCodes?.toTypedArray() ?: arrayOf()
			val additionalCpvArray = additionalCpvs?.mapNotNull(CpvSet::cpvCodes)?.flatten()?.toTypedArray()
					?: arrayOf()
			val cpvs = listOfNotNull(*mainCpvArray, *additionalCpvArray).toMutableList()
			val c = classificationFromCpv(cpvs.firstOrNull())
			cpvs.removeAt(0)
			val ac = cpvs.mapNotNull(this::classificationFromCpv).filterNot { it.id == c?.id }

			return if (!shortDescr.isNullOrBlank()) {
				Item().apply {
					additionalClassifications = ac.toSet()
					classification = c
					description = shortDescr
					id = "${tenderId()}:obj:${item ?: 0}"
				}
			} else null
		}

		fun items(objectContract: ObjectContract): List<Item> {
			with(objectContract) {
				return if (objectDescrs.isEmpty()) listOfNotNull(item(item, shortDescr, cpvMain, null))
				else objectDescrs.mapNotNull {
					item(it.item, it.shortDescr, objectContract.cpvMain, it.cpvAdditionals)
				}
			}
		}

		fun mainProcurementCategory(v: TextAndCode?) = when (v?.code?.toUpperCase() ?: "") {
			"1" -> Tender.MainProcurementCategory.WORKS
			"2" -> Tender.MainProcurementCategory.GOODS
			"4" -> Tender.MainProcurementCategory.SERVICES
			else -> null
		}

		fun noticeId() = "${tenderId()}:${parser.swappedNoticeId()}"

		fun organization(body: ContactContractingBody?, vararg roles: String) = when (body) {
			null -> null
			else -> Organization().apply {
				this.address = address(body)
				this.contactPoint = contactPoint(body)
				identifier = identifier(body)
				id = identifier.id
				name = body.officialName
				this.roles = roles.toList()
			}
		}

		fun organizationReference(org: Organization?) = when (org) {
			null -> null
			else -> OrganizationReference().apply {
				id = org.id
				name = org.name
			}
		}

		fun period(start: Date?, end: Date?) =
				if (null == start && null == end) null
				else Period().apply {
					startDate = start
					endDate = end
				}

		fun procurementMethod(v: TextAndCode?) = when (v?.code?.toUpperCase() ?: "") {
			"1" -> Tender.ProcurementMethod.OPEN // Open procedure
			"2" -> Tender.ProcurementMethod.LIMITED // Restricted procedure
			"3" -> Tender.ProcurementMethod.LIMITED // Accelerated restricted procedure
		// TODO 4: Negotiated procedure
		// TODO 6: Accelerated negotiated procedure
			"A" -> Tender.ProcurementMethod.DIRECT // Direct award
		// TODO B: Competitive procedure with negotiation
		// TODO C: Competitive dialogue
		// TODO E: Concession award procedure
		// TODO F: Concession award without prior concession notice
		// TODO G: Innovation partnership
		// TODO T: Negotiated procedure without a call for competition
		// TODO V: Contract award without prior publication
		// http://standard.open-contracting.org/latest/en/schema/codelists/#method
			else -> null
		}

		fun procurementMethodDetails(): String {
			var s = StringBuilder(parser.codif().prProc?.value + "\n\n")
			// justification from first PT_* element
			parser.procedure()?.run {
				if (!acceleratedProc.isNullOrBlank()) s.append("Accelerated procedure:\n\n$acceleratedProc\n\n")
				this.framework?.run {
					if (null != justification) s.append("Framework:\n\n$justification\n\n")
					if (null != nbParticipants) s.append("Participants: $nbParticipants\n\n")
				}
				if (null != nbMinParticipants) s.append("Min participants: $nbMinParticipants\n\n")
				if (null != nbMaxParticipants) s.append("Max participants: $nbMaxParticipants\n\n")

				// justification
				val justificationsFromPts = listOfNotNull(
						ptAwardContractWithoutCall,
						ptAwardContractWithoutPublication
				).mapNotNull(Annex::dJustification)

				val justificationsFromDirectives = listOfNotNull(
						directive200981EC,
						directive201423EU,
						directive201424EU,
						directive201425EU
				).map {
					listOfNotNull(
							it.ptAwardContractWithoutCall,
							it.ptAwardContractWithoutPublication,
							it.ptNegotiatedWithoutPublication
					)
				}.flatten().mapNotNull(Annex::dJustification)

				val justification = listOf(
						justificationsFromPts,
						justificationsFromDirectives
				).flatten().firstOrNull()

				if (!justification.isNullOrBlank()) s.append("Justification:\n\n$justification\n\n")
			}
			return s.toString().trim()
		}

		fun releaseTag(v: TextAndCode?) = when (v?.code?.toUpperCase() ?: "") {
		// TODO document types to tags
			"1" -> Tag.TENDER_UPDATE // TODO can be AWARD_UPDATE after TD-7
			"2" -> Tag.TENDER_UPDATE // TODO can be AWARD_UPDATE after TD-7
			"3" -> Tag.TENDER
			"7" -> Tag.AWARD
			else -> null
		}

		fun supplierOrg(body: ContactContractingBody?) = when (body) {
			null -> null
			else -> organization(body, SUPPLIER, TENDERER)
		}

		fun tender(awardContracts: List<AwardContract>, tendererRefs: List<OrganizationReference>) = Tender().apply {
			// TODO limit to TD-3 (and TD-7)?
			awardCriteria = awardCriteria(parser.codif().acAwardCrit)
			awardCriteriaDetails = awardCriteriaDetails()
			awardPeriod = period(parser.codif().dtDateForSubmission, null) // TODO end date? pub of TD-7?
			eligibilityCriteria = eligibilityCriteria(parser.lefti())
			items = parser.objects().map { items(it) }.flatten().toSet()
			mainProcurementCategory = mainProcurementCategory(parser.codif().ncContractNature)
			numberOfTenderers = awardContracts.map { it.awardedContract?.nbTendersReceived ?: 0 }.sum()
			procurementMethod = procurementMethod(parser.codif().prProc)
			procurementMethodDetails = procurementMethodDetails()
			// TODO status: TD-3: ACTIVE, TD-7: UNSUCCESSFUL or COMPLETE - maybe based on award.status too?
			tenderPeriod = period(parser.publicationDate(), parser.codif().dtDateForSubmission)
			tenderers = tendererRefs.toSet()
			title = parser.title(LanguageValue.EN)

			val v = parser.totalEstimatedValue()?.asValueRange()
			if (null != v) {
				minValue = amount(v.low, v.currency?.name)
				value = amount(v.high, v.currency?.name)
			}
		}

		fun tenderId(): String {
			// because TenderIdFilter, we do have tender ID here
			return "$ocidPrefix:${parser.tedExport!!.tenderId!!}"
		}
	}
}