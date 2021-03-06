
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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum Tag {

	PLANNING("planning"),
	PLANNING_UPDATE("planningUpdate"),
	TENDER("tender"),
	TENDER_AMENDMENT("tenderAmendment"),
	TENDER_UPDATE("tenderUpdate"),
	TENDER_CANCELLATION("tenderCancellation"),
	AWARD("award"),
	AWARD_UPDATE("awardUpdate"),
	AWARD_CANCELLATION("awardCancellation"),
	CONTRACT("contract"),
	CONTRACT_UPDATE("contractUpdate"),
	CONTRACT_AMENDMENT("contractAmendment"),
	IMPLEMENTATION("implementation"),
	IMPLEMENTATION_UPDATE("implementationUpdate"),
	CONTRACT_TERMINATION("contractTermination"),
	COMPILED("compiled");
	private final String value;
	private final static Map<String, Tag> CONSTANTS = new HashMap<String, Tag>();

	static {
		for (Tag c : values()) {
			CONSTANTS.put(c.value, c);
		}
	}

	private Tag(String value) {
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
	public static Tag fromValue(String value) {
		Tag constant = CONSTANTS.get(value);
		if (constant == null) {
			throw new IllegalArgumentException(value);
		} else {
			return constant;
		}
	}

}
