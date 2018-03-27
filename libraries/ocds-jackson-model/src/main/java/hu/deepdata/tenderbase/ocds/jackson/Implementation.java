
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
 * Implementation
 * <p>
 * Information during the performance / implementation stage of the contract.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
		"transactions",
		"milestones",
		"documents"
})
public class Implementation {

	/**
	 * Transactions
	 * <p>
	 * A list of the spending transactions made against this contract
	 */
	@JsonProperty("transactions")
	@JsonDeserialize(as = java.util.LinkedHashSet.class)
	@JsonPropertyDescription("A list of the spending transactions made against this contract")
	private Set<Transaction> transactions = new LinkedHashSet<Transaction>();
	/**
	 * Milestones
	 * <p>
	 * As milestones are completed, milestone completions should be documented.
	 */
	@JsonProperty("milestones")
	@JsonDeserialize(as = java.util.LinkedHashSet.class)
	@JsonPropertyDescription("As milestones are completed, milestone completions should be documented.")
	private Set<Milestone> milestones = new LinkedHashSet<Milestone>();
	/**
	 * Documents
	 * <p>
	 * Documents and reports that are part of the implementation phase e.g. audit and evaluation reports.
	 */
	@JsonProperty("documents")
	@JsonDeserialize(as = java.util.LinkedHashSet.class)
	@JsonPropertyDescription("Documents and reports that are part of the implementation phase e.g. audit and evaluation reports.")
	private Set<Document> documents = new LinkedHashSet<Document>();

	/**
	 * Transactions
	 * <p>
	 * A list of the spending transactions made against this contract
	 */
	@JsonProperty("transactions")
	public Set<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * Transactions
	 * <p>
	 * A list of the spending transactions made against this contract
	 */
	@JsonProperty("transactions")
	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	/**
	 * Milestones
	 * <p>
	 * As milestones are completed, milestone completions should be documented.
	 */
	@JsonProperty("milestones")
	public Set<Milestone> getMilestones() {
		return milestones;
	}

	/**
	 * Milestones
	 * <p>
	 * As milestones are completed, milestone completions should be documented.
	 */
	@JsonProperty("milestones")
	public void setMilestones(Set<Milestone> milestones) {
		this.milestones = milestones;
	}

	/**
	 * Documents
	 * <p>
	 * Documents and reports that are part of the implementation phase e.g. audit and evaluation reports.
	 */
	@JsonProperty("documents")
	public Set<Document> getDocuments() {
		return documents;
	}

	/**
	 * Documents
	 * <p>
	 * Documents and reports that are part of the implementation phase e.g. audit and evaluation reports.
	 */
	@JsonProperty("documents")
	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("transactions", transactions).append("milestones", milestones).append("documents", documents).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(transactions).append(milestones).append(documents).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Implementation) == false) {
			return false;
		}
		Implementation rhs = ((Implementation) other);
		return new EqualsBuilder().append(transactions, rhs.transactions).append(milestones, rhs.milestones).append(documents, rhs.documents).isEquals();
	}

}
