
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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.net.URI;
import java.util.Date;

/**
 * Transaction information
 * <p>
 * A spending transaction related to the contracting process. Draws upon the
 * data models of the [Fiscal Data Package](http://fiscal.dataprotocols.org/)
 * and the [International Aid Transparency Initiative](http://iatistandard.org/activity-standard/iati-activities/iati-activity/transaction/)
 * and should be used to cross-reference to more detailed information held using
 * a Fiscal Data Package, IATI file, or to provide enough information to allow a
 * user to manually or automatically cross-reference with some other published
 * source of transactional spending data.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
		"id",
		"source",
		"date",
		"value",
		"payer",
		"payee",
		"uri",
		"amount",
		"providerOrganization",
		"receiverOrganization"
})
public class Transaction {

	/**
	 * ID
	 * <p>
	 * A unique identifier for this transaction. This identifier should be
	 * possible to cross-reference against the provided data source. For IATI
	 * this is the transaction reference. (Required)
	 */
	@JsonProperty("id")
	@JsonPropertyDescription("A unique identifier for this transaction. This identifier should be possible to cross-reference against the provided data source. For IATI this is the transaction reference.")
	private String id;
	/**
	 * Data source
	 * <p>
	 * Used to point either to a corresponding Fiscal Data Package, IATI file,
	 * or machine or human-readable source where users can find further
	 * information on the budget line item identifiers, or project identifiers,
	 * provided here.
	 */
	@JsonProperty("source")
	@JsonPropertyDescription("Used to point either to a corresponding Fiscal Data Package, IATI file, or machine or human-readable source where users can find further information on the budget line item identifiers, or project identifiers, provided here.")
	private URI source;
	/**
	 * Date
	 * <p>
	 * The date of the transaction
	 */
	@JsonProperty("date")
	@JsonPropertyDescription("The date of the transaction")
	private Date date;
	/**
	 * Value
	 * <p>
	 */
	@JsonProperty("value")
	private Amount value;
	/**
	 * Organization reference
	 * <p>
	 * The id and name of the party being referenced. Used to cross-reference to
	 * the parties section
	 */
	@JsonProperty("payer")
	@JsonPropertyDescription("The id and name of the party being referenced. Used to cross-reference to the parties section")
	private OrganizationReference payer;
	/**
	 * Organization reference
	 * <p>
	 * The id and name of the party being referenced. Used to cross-reference to
	 * the parties section
	 */
	@JsonProperty("payee")
	@JsonPropertyDescription("The id and name of the party being referenced. Used to cross-reference to the parties section")
	private OrganizationReference payee;
	/**
	 * Linked spending information
	 * <p>
	 * A URI pointing directly to a machine-readable record about this spending
	 * transaction.
	 */
	@JsonProperty("uri")
	@JsonPropertyDescription("A URI pointing directly to a machine-readable record about this spending transaction.")
	private URI uri;
	/**
	 * Value
	 * <p>
	 */
	@JsonProperty("amount")
	private Amount amount;
	/**
	 * Identifier
	 * <p>
	 */
	@JsonProperty("providerOrganization")
	private Identifier providerOrganization;
	/**
	 * Identifier
	 * <p>
	 */
	@JsonProperty("receiverOrganization")
	private Identifier receiverOrganization;

	/**
	 * ID
	 * <p>
	 * A unique identifier for this transaction. This identifier should be
	 * possible to cross-reference against the provided data source. For IATI
	 * this is the transaction reference. (Required)
	 */
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	/**
	 * ID
	 * <p>
	 * A unique identifier for this transaction. This identifier should be
	 * possible to cross-reference against the provided data source. For IATI
	 * this is the transaction reference. (Required)
	 */
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Data source
	 * <p>
	 * Used to point either to a corresponding Fiscal Data Package, IATI file,
	 * or machine or human-readable source where users can find further
	 * information on the budget line item identifiers, or project identifiers,
	 * provided here.
	 */
	@JsonProperty("source")
	public URI getSource() {
		return source;
	}

	/**
	 * Data source
	 * <p>
	 * Used to point either to a corresponding Fiscal Data Package, IATI file,
	 * or machine or human-readable source where users can find further
	 * information on the budget line item identifiers, or project identifiers,
	 * provided here.
	 */
	@JsonProperty("source")
	public void setSource(URI source) {
		this.source = source;
	}

	/**
	 * Date
	 * <p>
	 * The date of the transaction
	 */
	@JsonProperty("date")
	public Date getDate() {
		return date;
	}

	/**
	 * Date
	 * <p>
	 * The date of the transaction
	 */
	@JsonProperty("date")
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Value
	 * <p>
	 */
	@JsonProperty("value")
	public Amount getValue() {
		return value;
	}

	/**
	 * Value
	 * <p>
	 */
	@JsonProperty("value")
	public void setValue(Amount value) {
		this.value = value;
	}

	/**
	 * Organization reference
	 * <p>
	 * The id and name of the party being referenced. Used to cross-reference to
	 * the parties section
	 */
	@JsonProperty("payer")
	public OrganizationReference getPayer() {
		return payer;
	}

	/**
	 * Organization reference
	 * <p>
	 * The id and name of the party being referenced. Used to cross-reference to
	 * the parties section
	 */
	@JsonProperty("payer")
	public void setPayer(OrganizationReference payer) {
		this.payer = payer;
	}

	/**
	 * Organization reference
	 * <p>
	 * The id and name of the party being referenced. Used to cross-reference to
	 * the parties section
	 */
	@JsonProperty("payee")
	public OrganizationReference getPayee() {
		return payee;
	}

	/**
	 * Organization reference
	 * <p>
	 * The id and name of the party being referenced. Used to cross-reference to
	 * the parties section
	 */
	@JsonProperty("payee")
	public void setPayee(OrganizationReference payee) {
		this.payee = payee;
	}

	/**
	 * Linked spending information
	 * <p>
	 * A URI pointing directly to a machine-readable record about this spending
	 * transaction.
	 */
	@JsonProperty("uri")
	public URI getUri() {
		return uri;
	}

	/**
	 * Linked spending information
	 * <p>
	 * A URI pointing directly to a machine-readable record about this spending
	 * transaction.
	 */
	@JsonProperty("uri")
	public void setUri(URI uri) {
		this.uri = uri;
	}

	/**
	 * Value
	 * <p>
	 */
	@JsonProperty("amount")
	public Amount getAmount() {
		return amount;
	}

	/**
	 * Value
	 * <p>
	 */
	@JsonProperty("amount")
	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	/**
	 * Identifier
	 * <p>
	 */
	@JsonProperty("providerOrganization")
	public Identifier getProviderOrganization() {
		return providerOrganization;
	}

	/**
	 * Identifier
	 * <p>
	 */
	@JsonProperty("providerOrganization")
	public void setProviderOrganization(Identifier providerOrganization) {
		this.providerOrganization = providerOrganization;
	}

	/**
	 * Identifier
	 * <p>
	 */
	@JsonProperty("receiverOrganization")
	public Identifier getReceiverOrganization() {
		return receiverOrganization;
	}

	/**
	 * Identifier
	 * <p>
	 */
	@JsonProperty("receiverOrganization")
	public void setReceiverOrganization(Identifier receiverOrganization) {
		this.receiverOrganization = receiverOrganization;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("source", source).append("date", date).append("value", value).append("payer", payer).append("payee", payee).append("uri", uri).append("amount", amount).append("providerOrganization", providerOrganization).append("receiverOrganization", receiverOrganization).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(date).append(payee).append(amount).append(providerOrganization).append(id).append(source).append(receiverOrganization).append(value).append(payer).append(uri).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Transaction) == false) {
			return false;
		}
		Transaction rhs = ((Transaction) other);
		return new EqualsBuilder().append(date, rhs.date).append(payee, rhs.payee).append(amount, rhs.amount).append(providerOrganization, rhs.providerOrganization).append(id, rhs.id).append(source, rhs.source).append(receiverOrganization, rhs.receiverOrganization).append(value, rhs.value).append(payer, rhs.payer).append(uri, rhs.uri).isEquals();
	}

}
