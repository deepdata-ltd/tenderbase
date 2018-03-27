
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

/**
 * Address
 * <p>
 * An address. This may be the legally registered address of the organization, or may be a correspondence address for this particular contracting process.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
		"streetAddress",
		"locality",
		"region",
		"postalCode",
		"countryName"
})
public class Address {

	/**
	 * Street address
	 * <p>
	 * The street address. For example, 1600 Amphitheatre Pkwy.
	 */
	@JsonProperty("streetAddress")
	@JsonPropertyDescription("The street address. For example, 1600 Amphitheatre Pkwy.")
	private String streetAddress;
	/**
	 * Locality
	 * <p>
	 * The locality. For example, Mountain View.
	 */
	@JsonProperty("locality")
	@JsonPropertyDescription("The locality. For example, Mountain View.")
	private String locality;
	/**
	 * Region
	 * <p>
	 * The region. For example, CA.
	 */
	@JsonProperty("region")
	@JsonPropertyDescription("The region. For example, CA.")
	private String region;
	/**
	 * Postal code
	 * <p>
	 * The postal code. For example, 94043.
	 */
	@JsonProperty("postalCode")
	@JsonPropertyDescription("The postal code. For example, 94043.")
	private String postalCode;
	/**
	 * Country name
	 * <p>
	 * The country name. For example, United States.
	 */
	@JsonProperty("countryName")
	@JsonPropertyDescription("The country name. For example, United States.")
	private String countryName;

	/**
	 * Street address
	 * <p>
	 * The street address. For example, 1600 Amphitheatre Pkwy.
	 */
	@JsonProperty("streetAddress")
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * Street address
	 * <p>
	 * The street address. For example, 1600 Amphitheatre Pkwy.
	 */
	@JsonProperty("streetAddress")
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	/**
	 * Locality
	 * <p>
	 * The locality. For example, Mountain View.
	 */
	@JsonProperty("locality")
	public String getLocality() {
		return locality;
	}

	/**
	 * Locality
	 * <p>
	 * The locality. For example, Mountain View.
	 */
	@JsonProperty("locality")
	public void setLocality(String locality) {
		this.locality = locality;
	}

	/**
	 * Region
	 * <p>
	 * The region. For example, CA.
	 */
	@JsonProperty("region")
	public String getRegion() {
		return region;
	}

	/**
	 * Region
	 * <p>
	 * The region. For example, CA.
	 */
	@JsonProperty("region")
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * Postal code
	 * <p>
	 * The postal code. For example, 94043.
	 */
	@JsonProperty("postalCode")
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Postal code
	 * <p>
	 * The postal code. For example, 94043.
	 */
	@JsonProperty("postalCode")
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Country name
	 * <p>
	 * The country name. For example, United States.
	 */
	@JsonProperty("countryName")
	public String getCountryName() {
		return countryName;
	}

	/**
	 * Country name
	 * <p>
	 * The country name. For example, United States.
	 */
	@JsonProperty("countryName")
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("streetAddress", streetAddress).append("locality", locality).append("region", region).append("postalCode", postalCode).append("countryName", countryName).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(locality).append(countryName).append(region).append(streetAddress).append(postalCode).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Address) == false) {
			return false;
		}
		Address rhs = ((Address) other);
		return new EqualsBuilder().append(locality, rhs.locality).append(countryName, rhs.countryName).append(region, rhs.region).append(streetAddress, rhs.streetAddress).append(postalCode, rhs.postalCode).isEquals();
	}

}
