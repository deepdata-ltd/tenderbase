
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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Organization
 * <p>
 * A party (organization)
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
		"name",
		"id",
		"identifier",
		"additionalIdentifiers",
		"address",
		"contactPoint",
		"roles",
		"details"
})
public class Organization {

	/**
	 * Common name
	 * <p>
	 * A common name for this organization or other participant in the
	 * contracting process. The identifier object provides an space for the
	 * formal legal name, and so this may either repeat that value, or could
	 * provide the common name by which this organization or entity is known.
	 * This field may also include details of the department or sub-unit
	 * involved in this contracting process.
	 */
	@JsonProperty("name")
	@JsonPropertyDescription("A common name for this organization or other participant in the contracting process. The identifier object provides an space for the formal legal name, and so this may either repeat that value, or could provide the common name by which this organization or entity is known. This field may also include details of the department or sub-unit involved in this contracting process.")
	private String name;
	/**
	 * Entity ID
	 * <p>
	 * The ID used for cross-referencing to this party from other sections of
	 * the release. This field may be built with the following structure
	 * {identifier.scheme}-{identifier.id}(-{department-identifier}).
	 */
	@JsonProperty("id")
	@JsonPropertyDescription("The ID used for cross-referencing to this party from other sections of the release. This field may be built with the following structure {identifier.scheme}-{identifier.id}(-{department-identifier}).")
	private String id;
	/**
	 * Identifier
	 * <p>
	 */
	@JsonProperty("identifier")
	private Identifier identifier;
	/**
	 * Additional identifiers
	 * <p>
	 * A list of additional / supplemental identifiers for the organization or
	 * participant, using the [organization identifier guidance](http://standard.open-contracting.org/latest/en/schema/identifiers/).
	 * This could be used to provide an internally used identifier for this
	 * organization in addition to the primary legal entity identifier.
	 */
	@JsonProperty("additionalIdentifiers")
	@JsonDeserialize(as = java.util.LinkedHashSet.class)
	@JsonPropertyDescription("A list of additional / supplemental identifiers for the organization or participant, using the [organization identifier guidance](http://standard.open-contracting.org/latest/en/schema/identifiers/). This could be used to provide an internally used identifier for this organization in addition to the primary legal entity identifier.")
	private Set<Identifier> additionalIdentifiers = new LinkedHashSet<Identifier>();
	/**
	 * Address
	 * <p>
	 * An address. This may be the legally registered address of the
	 * organization, or may be a correspondence address for this particular
	 * contracting process.
	 */
	@JsonProperty("address")
	@JsonPropertyDescription("An address. This may be the legally registered address of the organization, or may be a correspondence address for this particular contracting process.")
	private Address address;
	/**
	 * Contact point
	 * <p>
	 * An person, contact point or department to contact in relation to this
	 * contracting process.
	 */
	@JsonProperty("contactPoint")
	@JsonPropertyDescription("An person, contact point or department to contact in relation to this contracting process.")
	private ContactPoint contactPoint;
	/**
	 * Organization roles
	 * <p>
	 * The party's role(s) in the contracting process. Role(s) should be taken
	 * from the [partyRole codelist](http://standard.open-contracting.org/latest/en/schema/codelists/#party-role).
	 * Values from the provided codelist should be used wherever possible,
	 * though extended values can be provided if the codelist does not have a
	 * relevant code.
	 */
	@JsonProperty("roles")
	@JsonPropertyDescription("The party's role(s) in the contracting process. Role(s) should be taken from the [partyRole codelist](http://standard.open-contracting.org/latest/en/schema/codelists/#party-role). Values from the provided codelist should be used wherever possible, though extended values can be provided if the codelist does not have a relevant code.")
	private List<String> roles = new ArrayList<String>();
	/**
	 * Details
	 * <p>
	 * Additional classification information about parties can be provided using
	 * partyDetail extensions that define particular properties and
	 * classification schemes.
	 */
	@JsonProperty("details")
	@JsonPropertyDescription("Additional classification information about parties can be provided using partyDetail extensions that define particular properties and classification schemes. ")
	private Details details;

	/**
	 * Common name
	 * <p>
	 * A common name for this organization or other participant in the
	 * contracting process. The identifier object provides an space for the
	 * formal legal name, and so this may either repeat that value, or could
	 * provide the common name by which this organization or entity is known.
	 * This field may also include details of the department or sub-unit
	 * involved in this contracting process.
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * Common name
	 * <p>
	 * A common name for this organization or other participant in the
	 * contracting process. The identifier object provides an space for the
	 * formal legal name, and so this may either repeat that value, or could
	 * provide the common name by which this organization or entity is known.
	 * This field may also include details of the department or sub-unit
	 * involved in this contracting process.
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Entity ID
	 * <p>
	 * The ID used for cross-referencing to this party from other sections of
	 * the release. This field may be built with the following structure
	 * {identifier.scheme}-{identifier.id}(-{department-identifier}).
	 */
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	/**
	 * Entity ID
	 * <p>
	 * The ID used for cross-referencing to this party from other sections of
	 * the release. This field may be built with the following structure
	 * {identifier.scheme}-{identifier.id}(-{department-identifier}).
	 */
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Identifier
	 * <p>
	 */
	@JsonProperty("identifier")
	public Identifier getIdentifier() {
		return identifier;
	}

	/**
	 * Identifier
	 * <p>
	 */
	@JsonProperty("identifier")
	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	/**
	 * Additional identifiers
	 * <p>
	 * A list of additional / supplemental identifiers for the organization or
	 * participant, using the [organization identifier guidance](http://standard.open-contracting.org/latest/en/schema/identifiers/).
	 * This could be used to provide an internally used identifier for this
	 * organization in addition to the primary legal entity identifier.
	 */
	@JsonProperty("additionalIdentifiers")
	public Set<Identifier> getAdditionalIdentifiers() {
		return additionalIdentifiers;
	}

	/**
	 * Additional identifiers
	 * <p>
	 * A list of additional / supplemental identifiers for the organization or
	 * participant, using the [organization identifier guidance](http://standard.open-contracting.org/latest/en/schema/identifiers/).
	 * This could be used to provide an internally used identifier for this
	 * organization in addition to the primary legal entity identifier.
	 */
	@JsonProperty("additionalIdentifiers")
	public void setAdditionalIdentifiers(Set<Identifier> additionalIdentifiers) {
		this.additionalIdentifiers = additionalIdentifiers;
	}

	/**
	 * Address
	 * <p>
	 * An address. This may be the legally registered address of the
	 * organization, or may be a correspondence address for this particular
	 * contracting process.
	 */
	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	/**
	 * Address
	 * <p>
	 * An address. This may be the legally registered address of the
	 * organization, or may be a correspondence address for this particular
	 * contracting process.
	 */
	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Contact point
	 * <p>
	 * An person, contact point or department to contact in relation to this
	 * contracting process.
	 */
	@JsonProperty("contactPoint")
	public ContactPoint getContactPoint() {
		return contactPoint;
	}

	/**
	 * Contact point
	 * <p>
	 * An person, contact point or department to contact in relation to this
	 * contracting process.
	 */
	@JsonProperty("contactPoint")
	public void setContactPoint(ContactPoint contactPoint) {
		this.contactPoint = contactPoint;
	}

	/**
	 * Organization roles
	 * <p>
	 * The party's role(s) in the contracting process. Role(s) should be taken
	 * from the [partyRole codelist](http://standard.open-contracting.org/latest/en/schema/codelists/#party-role).
	 * Values from the provided codelist should be used wherever possible,
	 * though extended values can be provided if the codelist does not have a
	 * relevant code.
	 */
	@JsonProperty("roles")
	public List<String> getRoles() {
		return roles;
	}

	/**
	 * Organization roles
	 * <p>
	 * The party's role(s) in the contracting process. Role(s) should be taken
	 * from the [partyRole codelist](http://standard.open-contracting.org/latest/en/schema/codelists/#party-role).
	 * Values from the provided codelist should be used wherever possible,
	 * though extended values can be provided if the codelist does not have a
	 * relevant code.
	 */
	@JsonProperty("roles")
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	/**
	 * Details
	 * <p>
	 * Additional classification information about parties can be provided using
	 * partyDetail extensions that define particular properties and
	 * classification schemes.
	 */
	@JsonProperty("details")
	public Details getDetails() {
		return details;
	}

	/**
	 * Details
	 * <p>
	 * Additional classification information about parties can be provided using
	 * partyDetail extensions that define particular properties and
	 * classification schemes.
	 */
	@JsonProperty("details")
	public void setDetails(Details details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", name).append("id", id).append("identifier", identifier).append("additionalIdentifiers", additionalIdentifiers).append("address", address).append("contactPoint", contactPoint).append("roles", roles).append("details", details).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(identifier).append(address).append(contactPoint).append(roles).append(name).append(additionalIdentifiers).append(details).append(id).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Organization) == false) {
			return false;
		}
		Organization rhs = ((Organization) other);
		return new EqualsBuilder().append(identifier, rhs.identifier).append(address, rhs.address).append(contactPoint, rhs.contactPoint).append(roles, rhs.roles).append(name, rhs.name).append(additionalIdentifiers, rhs.additionalIdentifiers).append(details, rhs.details).append(id, rhs.id).isEquals();
	}

}
