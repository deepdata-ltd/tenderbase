
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

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Organization reference
 * <p>
 * The id and name of the party being referenced. Used to cross-reference to the
 * parties section
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
		"name",
		"id",
		"identifier",
		"address",
		"additionalIdentifiers",
		"contactPoint"
})
public class OrganizationReference {

	/**
	 * Organization name
	 * <p>
	 * The name of the party being referenced. This must match the name of an
	 * entry in the parties section. (Required)
	 */
	@JsonProperty("name")
	@JsonPropertyDescription("The name of the party being referenced. This must match the name of an entry in the parties section.")
	private String name;
	/**
	 * Organization ID
	 * <p>
	 * The id of the party being referenced. This must match the id of an entry
	 * in the parties section.
	 */
	@JsonProperty("id")
	@JsonPropertyDescription("The id of the party being referenced. This must match the id of an entry in the parties section.")
	private String id;
	/**
	 * Identifier
	 * <p>
	 */
	@JsonProperty("identifier")
	private Identifier identifier;
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
	 * Additional identifiers
	 * <p>
	 * (Deprecated outside the parties section) A list of additional /
	 * supplemental identifiers for the organization, using the [organization
	 * identifier guidance](http://standard.open-contracting.org/latest/en/schema/identifiers/).
	 * This could be used to provide an internally used identifier for this
	 * organization in addition to the primary legal entity identifier.
	 */
	@JsonProperty("additionalIdentifiers")
	@JsonDeserialize(as = java.util.LinkedHashSet.class)
	@JsonPropertyDescription("(Deprecated outside the parties section) A list of additional / supplemental identifiers for the organization, using the [organization identifier guidance](http://standard.open-contracting.org/latest/en/schema/identifiers/). This could be used to provide an internally used identifier for this organization in addition to the primary legal entity identifier.")
	private Set<Identifier> additionalIdentifiers = new LinkedHashSet<Identifier>();
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
	 * Organization name
	 * <p>
	 * The name of the party being referenced. This must match the name of an
	 * entry in the parties section. (Required)
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * Organization name
	 * <p>
	 * The name of the party being referenced. This must match the name of an
	 * entry in the parties section. (Required)
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Organization ID
	 * <p>
	 * The id of the party being referenced. This must match the id of an entry
	 * in the parties section.
	 */
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	/**
	 * Organization ID
	 * <p>
	 * The id of the party being referenced. This must match the id of an entry
	 * in the parties section.
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
	 * Additional identifiers
	 * <p>
	 * (Deprecated outside the parties section) A list of additional /
	 * supplemental identifiers for the organization, using the [organization
	 * identifier guidance](http://standard.open-contracting.org/latest/en/schema/identifiers/).
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
	 * (Deprecated outside the parties section) A list of additional /
	 * supplemental identifiers for the organization, using the [organization
	 * identifier guidance](http://standard.open-contracting.org/latest/en/schema/identifiers/).
	 * This could be used to provide an internally used identifier for this
	 * organization in addition to the primary legal entity identifier.
	 */
	@JsonProperty("additionalIdentifiers")
	public void setAdditionalIdentifiers(Set<Identifier> additionalIdentifiers) {
		this.additionalIdentifiers = additionalIdentifiers;
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

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", name).append("id", id).append("identifier", identifier).append("address", address).append("additionalIdentifiers", additionalIdentifiers).append("contactPoint", contactPoint).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(identifier).append(address).append(contactPoint).append(name).append(additionalIdentifiers).append(id).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof OrganizationReference) == false) {
			return false;
		}
		OrganizationReference rhs = ((OrganizationReference) other);
		return new EqualsBuilder().append(identifier, rhs.identifier).append(address, rhs.address).append(contactPoint, rhs.contactPoint).append(name, rhs.name).append(additionalIdentifiers, rhs.additionalIdentifiers).append(id, rhs.id).isEquals();
	}

}
