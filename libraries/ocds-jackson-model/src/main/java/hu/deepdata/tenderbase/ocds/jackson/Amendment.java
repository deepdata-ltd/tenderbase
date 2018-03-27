
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Amendment
 * <p>
 * Amendment information
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
		"date",
		"rationale",
		"id",
		"description",
		"amendsReleaseID",
		"releaseID",
		"changes"
})
public class Amendment {

	/**
	 * Amendment date
	 * <p>
	 * The date of this amendment.
	 */
	@JsonProperty("date")
	@JsonPropertyDescription("The date of this amendment.")
	private Date date;
	/**
	 * Rationale
	 * <p>
	 * An explanation for the amendment.
	 */
	@JsonProperty("rationale")
	@JsonPropertyDescription("An explanation for the amendment.")
	private String rationale;
	/**
	 * ID
	 * <p>
	 * An identifier for this amendment: often the amendment number
	 */
	@JsonProperty("id")
	@JsonPropertyDescription("An identifier for this amendment: often the amendment number")
	private String id;
	/**
	 * Description
	 * <p>
	 * A free text, or semi-structured, description of the changes made in this amendment.
	 */
	@JsonProperty("description")
	@JsonPropertyDescription("A free text, or semi-structured, description of the changes made in this amendment.")
	private String description;
	/**
	 * Amended release (identifier)
	 * <p>
	 * Provide the identifier (release.id) of the OCDS release (from this contracting process) that provides the values for this contracting process **before** the amendment was made.
	 */
	@JsonProperty("amendsReleaseID")
	@JsonPropertyDescription("Provide the identifier (release.id) of the OCDS release (from this contracting process) that provides the values for this contracting process **before** the amendment was made.")
	private String amendsReleaseID;
	/**
	 * Amending release (identifier)
	 * <p>
	 * Provide the identifier (release.id) of the OCDS release (from this contracting process) that provides the values for this contracting process **after** the amendment was made.
	 */
	@JsonProperty("releaseID")
	@JsonPropertyDescription("Provide the identifier (release.id) of the OCDS release (from this contracting process) that provides the values for this contracting process **after** the amendment was made.")
	private String releaseID;
	/**
	 * Amended fields
	 * <p>
	 * An array change objects describing the fields changed, and their former values. (Deprecated in 1.1)
	 */
	@JsonProperty("changes")
	@JsonPropertyDescription("An array change objects describing the fields changed, and their former values. (Deprecated in 1.1)")
	private List<Change> changes = new ArrayList<Change>();

	/**
	 * Amendment date
	 * <p>
	 * The date of this amendment.
	 */
	@JsonProperty("date")
	public Date getDate() {
		return date;
	}

	/**
	 * Amendment date
	 * <p>
	 * The date of this amendment.
	 */
	@JsonProperty("date")
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Rationale
	 * <p>
	 * An explanation for the amendment.
	 */
	@JsonProperty("rationale")
	public String getRationale() {
		return rationale;
	}

	/**
	 * Rationale
	 * <p>
	 * An explanation for the amendment.
	 */
	@JsonProperty("rationale")
	public void setRationale(String rationale) {
		this.rationale = rationale;
	}

	/**
	 * ID
	 * <p>
	 * An identifier for this amendment: often the amendment number
	 */
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	/**
	 * ID
	 * <p>
	 * An identifier for this amendment: often the amendment number
	 */
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Description
	 * <p>
	 * A free text, or semi-structured, description of the changes made in this amendment.
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 * Description
	 * <p>
	 * A free text, or semi-structured, description of the changes made in this amendment.
	 */
	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Amended release (identifier)
	 * <p>
	 * Provide the identifier (release.id) of the OCDS release (from this contracting process) that provides the values for this contracting process **before** the amendment was made.
	 */
	@JsonProperty("amendsReleaseID")
	public String getAmendsReleaseID() {
		return amendsReleaseID;
	}

	/**
	 * Amended release (identifier)
	 * <p>
	 * Provide the identifier (release.id) of the OCDS release (from this contracting process) that provides the values for this contracting process **before** the amendment was made.
	 */
	@JsonProperty("amendsReleaseID")
	public void setAmendsReleaseID(String amendsReleaseID) {
		this.amendsReleaseID = amendsReleaseID;
	}

	/**
	 * Amending release (identifier)
	 * <p>
	 * Provide the identifier (release.id) of the OCDS release (from this contracting process) that provides the values for this contracting process **after** the amendment was made.
	 */
	@JsonProperty("releaseID")
	public String getReleaseID() {
		return releaseID;
	}

	/**
	 * Amending release (identifier)
	 * <p>
	 * Provide the identifier (release.id) of the OCDS release (from this contracting process) that provides the values for this contracting process **after** the amendment was made.
	 */
	@JsonProperty("releaseID")
	public void setReleaseID(String releaseID) {
		this.releaseID = releaseID;
	}

	/**
	 * Amended fields
	 * <p>
	 * An array change objects describing the fields changed, and their former values. (Deprecated in 1.1)
	 */
	@JsonProperty("changes")
	public List<Change> getChanges() {
		return changes;
	}

	/**
	 * Amended fields
	 * <p>
	 * An array change objects describing the fields changed, and their former values. (Deprecated in 1.1)
	 */
	@JsonProperty("changes")
	public void setChanges(List<Change> changes) {
		this.changes = changes;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("date", date).append("rationale", rationale).append("id", id).append("description", description).append("amendsReleaseID", amendsReleaseID).append("releaseID", releaseID).append("changes", changes).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(date).append(releaseID).append(changes).append(description).append(id).append(amendsReleaseID).append(rationale).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Amendment) == false) {
			return false;
		}
		Amendment rhs = ((Amendment) other);
		return new EqualsBuilder().append(date, rhs.date).append(releaseID, rhs.releaseID).append(changes, rhs.changes).append(description, rhs.description).append(id, rhs.id).append(amendsReleaseID, rhs.amendsReleaseID).append(rationale, rhs.rationale).isEquals();
	}

}
