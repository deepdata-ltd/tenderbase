
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

package hu.deepdata.tenderbase.ocds.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;

/**
 * Schema for an Open Contracting Release
 * <p>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
		"ocid",
		"id",
		"date",
		"tag",
		"initiationType",
		"parties",
		"buyer",
		"planning",
		"tender",
		"awards",
		"contracts",
		"language",
		"relatedProcesses"
})
public class ReleaseSchema {

	/**
	 * Open Contracting ID
	 * <p>
	 * A globally unique identifier for this Open Contracting Process. Composed
	 * of a publisher prefix and an identifier for the contracting process. For
	 * more information see the [Open Contracting Identifier
	 * guidance](http://standard.open-contracting.org/latest/en/schema/identifiers/)
	 * (Required)
	 */
	@JsonProperty("ocid")
	@JsonPropertyDescription("A globally unique identifier for this Open Contracting Process. Composed of a publisher prefix and an identifier for the contracting process. For more information see the [Open Contracting Identifier guidance](http://standard.open-contracting.org/latest/en/schema/identifiers/)")
	private String ocid;
	/**
	 * Release ID
	 * <p>
	 * An identifier for this particular release of information. A release
	 * identifier must be unique within the scope of its related contracting
	 * process (defined by a common ocid), and unique within any release package
	 * it appears in. A release identifier must not contain the # character.
	 * (Required)
	 */
	@JsonProperty("id")
	@JsonPropertyDescription("An identifier for this particular release of information. A release identifier must be unique within the scope of its related contracting process (defined by a common ocid), and unique within any release package it appears in. A release identifier must not contain the # character.")
	private String id;
	/**
	 * Release Date
	 * <p>
	 * The date this information was first released, or published. (Required)
	 */
	@JsonProperty("date")
	@JsonPropertyDescription("The date this information was first released, or published.")
	private Date date;
	/**
	 * Release Tag
	 * <p>
	 * One or more values from the [releaseTag codelist](http://standard.open-contracting.org/latest/en/schema/codelists/#release-tag).
	 * Tags may be used to filter release and to understand the kind of
	 * information that a release might contain. (Required)
	 */
	@JsonProperty("tag")
	@JsonPropertyDescription("One or more values from the [releaseTag codelist](http://standard.open-contracting.org/latest/en/schema/codelists/#release-tag). Tags may be used to filter release and to understand the kind of information that a release might contain.")
	private List<Tag> tag = new ArrayList<Tag>();
	/**
	 * Initiation type
	 * <p>
	 * String specifying the type of initiation process used for this contract,
	 * taken from the [initiationType](http://standard.open-contracting.org/latest/en/schema/codelists/#initiation-type)
	 * codelist. Currently only tender is supported. (Required)
	 */
	@JsonProperty("initiationType")
	@JsonPropertyDescription("String specifying the type of initiation process used for this contract, taken from the [initiationType](http://standard.open-contracting.org/latest/en/schema/codelists/#initiation-type) codelist. Currently only tender is supported.")
	private ReleaseSchema.InitiationType initiationType;
	/**
	 * Parties
	 * <p>
	 * Information on the parties (organizations, economic operators and other
	 * participants) who are involved in the contracting process and their
	 * roles, e.g. buyer, procuring entity, supplier etc. Organization
	 * references elsewhere in the schema are used to refer back to this entries
	 * in this list.
	 */
	@JsonProperty("parties")
	@JsonDeserialize(as = java.util.LinkedHashSet.class)
	@JsonPropertyDescription("Information on the parties (organizations, economic operators and other participants) who are involved in the contracting process and their roles, e.g. buyer, procuring entity, supplier etc. Organization references elsewhere in the schema are used to refer back to this entries in this list.")
	private Set<Organization> parties = new LinkedHashSet<Organization>();
	/**
	 * Organization reference
	 * <p>
	 * The id and name of the party being referenced. Used to cross-reference to
	 * the parties section
	 */
	@JsonProperty("buyer")
	@JsonPropertyDescription("The id and name of the party being referenced. Used to cross-reference to the parties section")
	private OrganizationReference buyer;
	/**
	 * Planning
	 * <p>
	 * Information from the planning phase of the contracting process. Note that
	 * many other fields may be filled in a planning release, in the appropriate
	 * fields in other schema sections, these would likely be estimates at this
	 * stage e.g. totalValue in tender
	 */
	@JsonProperty("planning")
	@JsonPropertyDescription("Information from the planning phase of the contracting process. Note that many other fields may be filled in a planning release, in the appropriate fields in other schema sections, these would likely be estimates at this stage e.g. totalValue in tender")
	private Planning planning;
	/**
	 * Tender
	 * <p>
	 * Data regarding tender process - publicly inviting prospective contractors
	 * to submit bids for evaluation and selecting a winner or winners.
	 */
	@JsonProperty("tender")
	@JsonPropertyDescription("Data regarding tender process - publicly inviting prospective contractors to submit bids for evaluation and selecting a winner or winners.")
	private Tender tender;
	/**
	 * Awards
	 * <p>
	 * Information from the award phase of the contracting process. There may be
	 * more than one award per contracting process e.g. because the contract is
	 * split among different providers, or because it is a standing offer.
	 */
	@JsonProperty("awards")
	@JsonDeserialize(as = java.util.LinkedHashSet.class)
	@JsonPropertyDescription("Information from the award phase of the contracting process. There may be more than one award per contracting process e.g. because the contract is split among different providers, or because it is a standing offer.")
	private Set<Award> awards = new LinkedHashSet<Award>();
	/**
	 * Contracts
	 * <p>
	 * Information from the contract creation phase of the procurement process.
	 */
	@JsonProperty("contracts")
	@JsonDeserialize(as = java.util.LinkedHashSet.class)
	@JsonPropertyDescription("Information from the contract creation phase of the procurement process.")
	private Set<Contract> contracts = new LinkedHashSet<Contract>();
	/**
	 * Release language
	 * <p>
	 * Specifies the default language of the data using either two-letter
	 * [ISO639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes), or
	 * extended [BCP47 language tags](http://www.w3.org/International/articles/language-tags/).
	 * The use of lowercase two-letter codes from [ISO639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes)
	 * is strongly recommended.
	 */
	@JsonProperty("language")
	@JsonPropertyDescription("Specifies the default language of the data using either two-letter [ISO639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes), or extended [BCP47 language tags](http://www.w3.org/International/articles/language-tags/). The use of lowercase two-letter codes from [ISO639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes) is strongly recommended.")
	private String language = "en";
	/**
	 * Related processes
	 * <p>
	 * If this process follows on from one or more prior process, represented
	 * under a separate open contracting identifier (ocid) then details of the
	 * related process can be provided here. This is commonly used to relate
	 * mini-competitions to their parent frameworks, full tenders to a
	 * pre-qualification phase, or individual tenders to a broad planning
	 * process.
	 */
	@JsonProperty("relatedProcesses")
	@JsonDeserialize(as = java.util.LinkedHashSet.class)
	@JsonPropertyDescription("If this process follows on from one or more prior process, represented under a separate open contracting identifier (ocid) then details of the related process can be provided here. This is commonly used to relate mini-competitions to their parent frameworks, full tenders to a pre-qualification phase, or individual tenders to a broad planning process.")
	private Set<RelatedProcess> relatedProcesses = new LinkedHashSet<RelatedProcess>();

	/**
	 * Open Contracting ID
	 * <p>
	 * A globally unique identifier for this Open Contracting Process. Composed
	 * of a publisher prefix and an identifier for the contracting process. For
	 * more information see the [Open Contracting Identifier
	 * guidance](http://standard.open-contracting.org/latest/en/schema/identifiers/)
	 * (Required)
	 */
	@JsonProperty("ocid")
	public String getOcid() {
		return ocid;
	}

	/**
	 * Open Contracting ID
	 * <p>
	 * A globally unique identifier for this Open Contracting Process. Composed
	 * of a publisher prefix and an identifier for the contracting process. For
	 * more information see the [Open Contracting Identifier
	 * guidance](http://standard.open-contracting.org/latest/en/schema/identifiers/)
	 * (Required)
	 */
	@JsonProperty("ocid")
	public void setOcid(String ocid) {
		this.ocid = ocid;
	}

	/**
	 * Release ID
	 * <p>
	 * An identifier for this particular release of information. A release
	 * identifier must be unique within the scope of its related contracting
	 * process (defined by a common ocid), and unique within any release package
	 * it appears in. A release identifier must not contain the # character.
	 * (Required)
	 */
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	/**
	 * Release ID
	 * <p>
	 * An identifier for this particular release of information. A release
	 * identifier must be unique within the scope of its related contracting
	 * process (defined by a common ocid), and unique within any release package
	 * it appears in. A release identifier must not contain the # character.
	 * (Required)
	 */
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Release Date
	 * <p>
	 * The date this information was first released, or published. (Required)
	 */
	@JsonProperty("date")
	public Date getDate() {
		return date;
	}

	/**
	 * Release Date
	 * <p>
	 * The date this information was first released, or published. (Required)
	 */
	@JsonProperty("date")
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Release Tag
	 * <p>
	 * One or more values from the [releaseTag codelist](http://standard.open-contracting.org/latest/en/schema/codelists/#release-tag).
	 * Tags may be used to filter release and to understand the kind of
	 * information that a release might contain. (Required)
	 */
	@JsonProperty("tag")
	public List<Tag> getTag() {
		return tag;
	}

	/**
	 * Release Tag
	 * <p>
	 * One or more values from the [releaseTag codelist](http://standard.open-contracting.org/latest/en/schema/codelists/#release-tag).
	 * Tags may be used to filter release and to understand the kind of
	 * information that a release might contain. (Required)
	 */
	@JsonProperty("tag")
	public void setTag(List<Tag> tag) {
		this.tag = tag;
	}

	/**
	 * Initiation type
	 * <p>
	 * String specifying the type of initiation process used for this contract,
	 * taken from the [initiationType](http://standard.open-contracting.org/latest/en/schema/codelists/#initiation-type)
	 * codelist. Currently only tender is supported. (Required)
	 */
	@JsonProperty("initiationType")
	public ReleaseSchema.InitiationType getInitiationType() {
		return initiationType;
	}

	/**
	 * Initiation type
	 * <p>
	 * String specifying the type of initiation process used for this contract,
	 * taken from the [initiationType](http://standard.open-contracting.org/latest/en/schema/codelists/#initiation-type)
	 * codelist. Currently only tender is supported. (Required)
	 */
	@JsonProperty("initiationType")
	public void setInitiationType(ReleaseSchema.InitiationType initiationType) {
		this.initiationType = initiationType;
	}

	/**
	 * Parties
	 * <p>
	 * Information on the parties (organizations, economic operators and other
	 * participants) who are involved in the contracting process and their
	 * roles, e.g. buyer, procuring entity, supplier etc. Organization
	 * references elsewhere in the schema are used to refer back to this entries
	 * in this list.
	 */
	@JsonProperty("parties")
	public Set<Organization> getParties() {
		return parties;
	}

	/**
	 * Parties
	 * <p>
	 * Information on the parties (organizations, economic operators and other
	 * participants) who are involved in the contracting process and their
	 * roles, e.g. buyer, procuring entity, supplier etc. Organization
	 * references elsewhere in the schema are used to refer back to this entries
	 * in this list.
	 */
	@JsonProperty("parties")
	public void setParties(Set<Organization> parties) {
		this.parties = parties;
	}

	/**
	 * Organization reference
	 * <p>
	 * The id and name of the party being referenced. Used to cross-reference to
	 * the parties section
	 */
	@JsonProperty("buyer")
	public OrganizationReference getBuyer() {
		return buyer;
	}

	/**
	 * Organization reference
	 * <p>
	 * The id and name of the party being referenced. Used to cross-reference to
	 * the parties section
	 */
	@JsonProperty("buyer")
	public void setBuyer(OrganizationReference buyer) {
		this.buyer = buyer;
	}

	/**
	 * Planning
	 * <p>
	 * Information from the planning phase of the contracting process. Note that
	 * many other fields may be filled in a planning release, in the appropriate
	 * fields in other schema sections, these would likely be estimates at this
	 * stage e.g. totalValue in tender
	 */
	@JsonProperty("planning")
	public Planning getPlanning() {
		return planning;
	}

	/**
	 * Planning
	 * <p>
	 * Information from the planning phase of the contracting process. Note that
	 * many other fields may be filled in a planning release, in the appropriate
	 * fields in other schema sections, these would likely be estimates at this
	 * stage e.g. totalValue in tender
	 */
	@JsonProperty("planning")
	public void setPlanning(Planning planning) {
		this.planning = planning;
	}

	/**
	 * Tender
	 * <p>
	 * Data regarding tender process - publicly inviting prospective contractors
	 * to submit bids for evaluation and selecting a winner or winners.
	 */
	@JsonProperty("tender")
	public Tender getTender() {
		return tender;
	}

	/**
	 * Tender
	 * <p>
	 * Data regarding tender process - publicly inviting prospective contractors
	 * to submit bids for evaluation and selecting a winner or winners.
	 */
	@JsonProperty("tender")
	public void setTender(Tender tender) {
		this.tender = tender;
	}

	/**
	 * Awards
	 * <p>
	 * Information from the award phase of the contracting process. There may be
	 * more than one award per contracting process e.g. because the contract is
	 * split among different providers, or because it is a standing offer.
	 */
	@JsonProperty("awards")
	public Set<Award> getAwards() {
		return awards;
	}

	/**
	 * Awards
	 * <p>
	 * Information from the award phase of the contracting process. There may be
	 * more than one award per contracting process e.g. because the contract is
	 * split among different providers, or because it is a standing offer.
	 */
	@JsonProperty("awards")
	public void setAwards(Set<Award> awards) {
		this.awards = awards;
	}

	/**
	 * Contracts
	 * <p>
	 * Information from the contract creation phase of the procurement process.
	 */
	@JsonProperty("contracts")
	public Set<Contract> getContracts() {
		return contracts;
	}

	/**
	 * Contracts
	 * <p>
	 * Information from the contract creation phase of the procurement process.
	 */
	@JsonProperty("contracts")
	public void setContracts(Set<Contract> contracts) {
		this.contracts = contracts;
	}

	/**
	 * Release language
	 * <p>
	 * Specifies the default language of the data using either two-letter
	 * [ISO639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes), or
	 * extended [BCP47 language tags](http://www.w3.org/International/articles/language-tags/).
	 * The use of lowercase two-letter codes from [ISO639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes)
	 * is strongly recommended.
	 */
	@JsonProperty("language")
	public String getLanguage() {
		return language;
	}

	/**
	 * Release language
	 * <p>
	 * Specifies the default language of the data using either two-letter
	 * [ISO639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes), or
	 * extended [BCP47 language tags](http://www.w3.org/International/articles/language-tags/).
	 * The use of lowercase two-letter codes from [ISO639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes)
	 * is strongly recommended.
	 */
	@JsonProperty("language")
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Related processes
	 * <p>
	 * If this process follows on from one or more prior process, represented
	 * under a separate open contracting identifier (ocid) then details of the
	 * related process can be provided here. This is commonly used to relate
	 * mini-competitions to their parent frameworks, full tenders to a
	 * pre-qualification phase, or individual tenders to a broad planning
	 * process.
	 */
	@JsonProperty("relatedProcesses")
	public Set<RelatedProcess> getRelatedProcesses() {
		return relatedProcesses;
	}

	/**
	 * Related processes
	 * <p>
	 * If this process follows on from one or more prior process, represented
	 * under a separate open contracting identifier (ocid) then details of the
	 * related process can be provided here. This is commonly used to relate
	 * mini-competitions to their parent frameworks, full tenders to a
	 * pre-qualification phase, or individual tenders to a broad planning
	 * process.
	 */
	@JsonProperty("relatedProcesses")
	public void setRelatedProcesses(Set<RelatedProcess> relatedProcesses) {
		this.relatedProcesses = relatedProcesses;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("ocid", ocid).append("id", id).append("date", date).append("tag", tag).append("initiationType", initiationType).append("parties", parties).append("buyer", buyer).append("planning", planning).append("tender", tender).append("awards", awards).append("contracts", contracts).append("language", language).append("relatedProcesses", relatedProcesses).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(date).append(tender).append(relatedProcesses).append(language).append(contracts).append(buyer).append(initiationType).append(planning).append(awards).append(parties).append(id).append(tag).append(ocid).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof ReleaseSchema) == false) {
			return false;
		}
		ReleaseSchema rhs = ((ReleaseSchema) other);
		return new EqualsBuilder().append(date, rhs.date).append(tender, rhs.tender).append(relatedProcesses, rhs.relatedProcesses).append(language, rhs.language).append(contracts, rhs.contracts).append(buyer, rhs.buyer).append(initiationType, rhs.initiationType).append(planning, rhs.planning).append(awards, rhs.awards).append(parties, rhs.parties).append(id, rhs.id).append(tag, rhs.tag).append(ocid, rhs.ocid).isEquals();
	}

	public enum InitiationType {

		TENDER("tender");
		private final String value;
		private final static Map<String, ReleaseSchema.InitiationType> CONSTANTS = new HashMap<String, ReleaseSchema.InitiationType>();

		static {
			for (ReleaseSchema.InitiationType c : values()) {
				CONSTANTS.put(c.value, c);
			}
		}

		private InitiationType(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}

		@JsonCreator
		public static ReleaseSchema.InitiationType fromValue(String value) {
			ReleaseSchema.InitiationType constant = CONSTANTS.get(value);
			if (constant == null) {
				throw new IllegalArgumentException(value);
			} else {
				return constant;
			}
		}

	}

}
