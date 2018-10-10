package com.fxcalc.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Aditya Bhanose
 * @since  6/10/2018
 * @version 1.0
 */

@Component (value="conversionRequest")
public class ConversionRequest {
	
	private String fromCurrency;
	
	private String toCurrency;
	
	private Double amount;
	
	private String crossCurrency;

	
	public String getCrossCurrency() {
		return crossCurrency;
	}

	public void setCrossCurrency(String crossCurrency) {
		this.crossCurrency = crossCurrency;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	 private  final Map<String, String> supportedCurrency = new HashMap<String, String>();
	     {
	        supportedCurrency.put("AUD", "AUD");
	        supportedCurrency.put("CAD", "CAD");
	        supportedCurrency.put("CNY", "CNY");
	        supportedCurrency.put("CZK", "CZK");
	        supportedCurrency.put("DKK", "DKK");
	        supportedCurrency.put("EUR", "EUR");
	        supportedCurrency.put("GBP", "GBP");
	        supportedCurrency.put("JPY", "JPY");
	        supportedCurrency.put("NOK", "NOK");
	        supportedCurrency.put("NZD", "NZD");
	        supportedCurrency.put("USD", "USD");
	        
	    }
	     
		public Map<String, String> getSupportedCurrency() {
			return supportedCurrency;
		}
	     
	     
	     
	
}