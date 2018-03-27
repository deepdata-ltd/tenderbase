
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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Value
 * <p>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
		"amount",
		"currency"
})
public class Amount {

	/**
	 * Amount
	 * <p>
	 * Amount as a number.
	 */
	@JsonProperty("amount")
	@JsonPropertyDescription("Amount as a number.")
	private Double amount;
	/**
	 * Currency
	 * <p>
	 * The currency for each amount should always be specified using the uppercase 3-letter currency code from ISO4217.
	 */
	@JsonProperty("currency")
	@JsonPropertyDescription("The currency for each amount should always be specified using the uppercase 3-letter currency code from ISO4217.")
	private Amount.Currency currency;

	/**
	 * Amount
	 * <p>
	 * Amount as a number.
	 */
	@JsonProperty("amount")
	public Double getAmount() {
		return amount;
	}

	/**
	 * Amount
	 * <p>
	 * Amount as a number.
	 */
	@JsonProperty("amount")
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * Currency
	 * <p>
	 * The currency for each amount should always be specified using the uppercase 3-letter currency code from ISO4217.
	 */
	@JsonProperty("currency")
	public Amount.Currency getCurrency() {
		return currency;
	}

	/**
	 * Currency
	 * <p>
	 * The currency for each amount should always be specified using the uppercase 3-letter currency code from ISO4217.
	 */
	@JsonProperty("currency")
	public void setCurrency(Amount.Currency currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("amount", amount).append("currency", currency).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(amount).append(currency).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Amount) == false) {
			return false;
		}
		Amount rhs = ((Amount) other);
		return new EqualsBuilder().append(amount, rhs.amount).append(currency, rhs.currency).isEquals();
	}

	public enum Currency {

		AED("AED"),
		AFN("AFN"),
		ALL("ALL"),
		AMD("AMD"),
		ANG("ANG"),
		AOA("AOA"),
		ARS("ARS"),
		AUD("AUD"),
		AWG("AWG"),
		AZN("AZN"),
		BAM("BAM"),
		BBD("BBD"),
		BDT("BDT"),
		BGN("BGN"),
		BHD("BHD"),
		BIF("BIF"),
		BMD("BMD"),
		BND("BND"),
		BOB("BOB"),
		BOV("BOV"),
		BRL("BRL"),
		BSD("BSD"),
		BTN("BTN"),
		BWP("BWP"),
		BYR("BYR"),
		BZD("BZD"),
		CAD("CAD"),
		CDF("CDF"),
		CHF("CHF"),
		CLF("CLF"),
		CLP("CLP"),
		CNY("CNY"),
		COP("COP"),
		COU("COU"),
		CRC("CRC"),
		CUC("CUC"),
		CUP("CUP"),
		CVE("CVE"),
		CZK("CZK"),
		DJF("DJF"),
		DKK("DKK"),
		DOP("DOP"),
		DZD("DZD"),
		EEK("EEK"),
		EGP("EGP"),
		ERN("ERN"),
		ETB("ETB"),
		EUR("EUR"),
		FJD("FJD"),
		FKP("FKP"),
		GBP("GBP"),
		GEL("GEL"),
		GHS("GHS"),
		GIP("GIP"),
		GMD("GMD"),
		GNF("GNF"),
		GTQ("GTQ"),
		GYD("GYD"),
		HKD("HKD"),
		HNL("HNL"),
		HRK("HRK"),
		HTG("HTG"),
		HUF("HUF"),
		IDR("IDR"),
		ILS("ILS"),
		INR("INR"),
		IQD("IQD"),
		IRR("IRR"),
		ISK("ISK"),
		JMD("JMD"),
		JOD("JOD"),
		JPY("JPY"),
		KES("KES"),
		KGS("KGS"),
		KHR("KHR"),
		KMF("KMF"),
		KPW("KPW"),
		KRW("KRW"),
		KWD("KWD"),
		KYD("KYD"),
		KZT("KZT"),
		LAK("LAK"),
		LBP("LBP"),
		LKR("LKR"),
		LRD("LRD"),
		LSL("LSL"),
		LTL("LTL"),
		LVL("LVL"),
		LYD("LYD"),
		MAD("MAD"),
		MDL("MDL"),
		MGA("MGA"),
		MKD("MKD"),
		MMK("MMK"),
		MNT("MNT"),
		MOP("MOP"),
		MRO("MRO"),
		MUR("MUR"),
		MVR("MVR"),
		MWK("MWK"),
		MXN("MXN"),
		MXV("MXV"),
		MYR("MYR"),
		MZN("MZN"),
		NAD("NAD"),
		NGN("NGN"),
		NIO("NIO"),
		NOK("NOK"),
		NPR("NPR"),
		NZD("NZD"),
		OMR("OMR"),
		PAB("PAB"),
		PEN("PEN"),
		PGK("PGK"),
		PHP("PHP"),
		PKR("PKR"),
		PLN("PLN"),
		PYG("PYG"),
		QAR("QAR"),
		RON("RON"),
		RSD("RSD"),
		RUB("RUB"),
		RWF("RWF"),
		SAR("SAR"),
		SBD("SBD"),
		SCR("SCR"),
		SDG("SDG"),
		SEK("SEK"),
		SGD("SGD"),
		SHP("SHP"),
		SLL("SLL"),
		SOS("SOS"),
		SSP("SSP"),
		SRD("SRD"),
		STD("STD"),
		SVC("SVC"),
		SYP("SYP"),
		SZL("SZL"),
		THB("THB"),
		TJS("TJS"),
		TMT("TMT"),
		TND("TND"),
		TOP("TOP"),
		TRY("TRY"),
		TTD("TTD"),
		TWD("TWD"),
		TZS("TZS"),
		UAH("UAH"),
		UGX("UGX"),
		USD("USD"),
		USN("USN"),
		USS("USS"),
		UYI("UYI"),
		UYU("UYU"),
		UZS("UZS"),
		VEF("VEF"),
		VND("VND"),
		VUV("VUV"),
		WST("WST"),
		XAF("XAF"),
		XBT("XBT"),
		XCD("XCD"),
		XDR("XDR"),
		XOF("XOF"),
		XPF("XPF"),
		YER("YER"),
		ZAR("ZAR"),
		ZMK("ZMK"),
		ZWL("ZWL");
		private final String value;
		private final static Map<String, Amount.Currency> CONSTANTS = new HashMap<String, Amount.Currency>();

		static {
			for (Amount.Currency c : values()) {
				CONSTANTS.put(c.value, c);
			}
		}

		private Currency(String value) {
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
		public static Amount.Currency fromValue(String value) {
			Amount.Currency constant = CONSTANTS.get(value);
			if (constant == null) {
				throw new IllegalArgumentException(value);
			} else {
				return constant;
			}
		}

	}

}
