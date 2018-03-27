
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

/**
 * Classification
 * <p>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
		"scheme",
		"id",
		"description",
		"uri"
})
public class Classification {

	/**
	 * Scheme
	 * <p>
	 * An classification should be drawn from an existing scheme or list of codes. This field is used to indicate the scheme/codelist from which the classification is drawn. For line item classifications, this value should represent an known [Item Classification Scheme](http://standard.open-contracting.org/latest/en/schema/codelists/#item-classification-scheme) wherever possible.
	 */
	@JsonProperty("scheme")
	@JsonPropertyDescription("An classification should be drawn from an existing scheme or list of codes. This field is used to indicate the scheme/codelist from which the classification is drawn. For line item classifications, this value should represent an known [Item Classification Scheme](http://standard.open-contracting.org/latest/en/schema/codelists/#item-classification-scheme) wherever possible.")
	private String scheme;
	/**
	 * ID
	 * <p>
	 * The classification code drawn from the selected scheme.
	 */
	@JsonProperty("id")
	@JsonPropertyDescription("The classification code drawn from the selected scheme.")
	private String id;
	/**
	 * Description
	 * <p>
	 * A textual description or title for the code.
	 */
	@JsonProperty("description")
	@JsonPropertyDescription("A textual description or title for the code.")
	private String description;
	/**
	 * URI
	 * <p>
	 * A URI to identify the code. In the event individual URIs are not available for items in the identifier scheme this value should be left blank.
	 */
	@JsonProperty("uri")
	@JsonPropertyDescription("A URI to identify the code. In the event individual URIs are not available for items in the identifier scheme this value should be left blank.")
	private URI uri;

	/**
	 * Scheme
	 * <p>
	 * An classification should be drawn from an existing scheme or list of codes. This field is used to indicate the scheme/codelist from which the classification is drawn. For line item classifications, this value should represent an known [Item Classification Scheme](http://standard.open-contracting.org/latest/en/schema/codelists/#item-classification-scheme) wherever possible.
	 */
	@JsonProperty("scheme")
	public String getScheme() {
		return scheme;
	}

	/**
	 * Scheme
	 * <p>
	 * An classification should be drawn from an existing scheme or list of codes. This field is used to indicate the scheme/codelist from which the classification is drawn. For line item classifications, this value should represent an known [Item Classification Scheme](http://standard.open-contracting.org/latest/en/schema/codelists/#item-classification-scheme) wherever possible.
	 */
	@JsonProperty("scheme")
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	/**
	 * ID
	 * <p>
	 * The classification code drawn from the selected scheme.
	 */
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	/**
	 * ID
	 * <p>
	 * The classification code drawn from the selected scheme.
	 */
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Description
	 * <p>
	 * A textual description or title for the code.
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 * Description
	 * <p>
	 * A textual description or title for the code.
	 */
	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * URI
	 * <p>
	 * A URI to identify the code. In the event individual URIs are not available for items in the identifier scheme this value should be left blank.
	 */
	@JsonProperty("uri")
	public URI getUri() {
		return uri;
	}

	/**
	 * URI
	 * <p>
	 * A URI to identify the code. In the event individual URIs are not available for items in the identifier scheme this value should be left blank.
	 */
	@JsonProperty("uri")
	public void setUri(URI uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("scheme", scheme).append("id", id).append("description", description).append("uri", uri).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(description).append(id).append(scheme).append(uri).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Classification) == false) {
			return false;
		}
		Classification rhs = ((Classification) other);
		return new EqualsBuilder().append(description, rhs.description).append(id, rhs.id).append(scheme, rhs.scheme).append(uri, rhs.uri).isEquals();
	}

}
