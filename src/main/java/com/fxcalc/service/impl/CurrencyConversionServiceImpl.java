package com.fxcalc.service.impl;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fxcalc.helper.ReferenceDataLoader;
import com.fxcalc.model.ConversionRequest;
import com.fxcalc.service.CurrencyConversionService;

/**
 * 
 * @author Aditya Bhanose
 * @since 6/10/2018
 * @version 1.0 Service class provides the currency conversion
 */

@Component(value = "currencyConversionService")
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

	HashMap<String, HashMap<String, String>> crossMatrix;
	HashMap<String, String> currencyRates;
	HashMap<String, String> formatterMap;

	@Autowired
	ReferenceDataLoader referenceDataLoader;

	/**
	 * Method to process the input request
	 */

	@Override
	public void processRequest(ConversionRequest convRequest) {

		crossMatrix = new HashMap<String, HashMap<String, String>>();
		currencyRates = new HashMap<String, String>();
		formatterMap = new HashMap<String, String>();
		referenceDataLoader.populateCurrencyRate(currencyRates);
		referenceDataLoader.populateCrossViaMatrix(crossMatrix);
		referenceDataLoader.getFormatterMap(formatterMap);
		lookupCrossMatrix(crossMatrix, convRequest);
	}

	/**
	 * Method to lookup the Cross Via Matrix and perform the conversion
	 * 
	 * @param crossMatrix
	 * @param convRequest
	 */

	public void lookupCrossMatrix(HashMap<String, HashMap<String, String>> crossMatrix, ConversionRequest convRequest) {
		String convertedAmount = "";
		if (!convRequest.getFromCurrency().isEmpty()) {
			HashMap<String, String> map = crossMatrix.get(convRequest.getFromCurrency());
			String value = map.get(convRequest.getToCurrency());
			
			// if Direct Feed
			if (value.equalsIgnoreCase("D")) {
				String strRate = currencyRates.get(convRequest.getFromCurrency().concat(convRequest.getToCurrency()));
				Double rate = Double.parseDouble(strRate);
				convertedAmount = String.format("%."+formatterMap.get(convRequest.getToCurrency())+"f %n",
						rate * convRequest.getAmount());
				System.out.println("\n Conversion Result: " + convertedAmount);
			
			//If unity
			} else if (value.equalsIgnoreCase("1:1")) {
				convertedAmount = String.format("%."+formatterMap.get(convRequest.getToCurrency())+"f %n",
						convRequest.getAmount());
				System.out.println("\n Conversion Result: " + convertedAmount);
			
			//If Inversion
			} else if (value.equalsIgnoreCase("INV")) {
				String strRate = currencyRates.get(convRequest.getToCurrency().concat(convRequest.getFromCurrency()));
				Double rate = 1 / Double.parseDouble(strRate);
				convertedAmount = String.format("%."+formatterMap.get(convRequest.getToCurrency())+"f %n",
						rate * convRequest.getAmount());
				//System.out.println("\n Conversion Result: " + rate * convRequest.getAmount());
			
			//If Currency
			} else {
				String newvalue = map.get(value);
				if (newvalue.equalsIgnoreCase("D")) {
					String strRate = currencyRates.get(convRequest.getFromCurrency().concat(value));
					Double rate = Double.parseDouble(strRate);
					Double newAmount = rate * convRequest.getAmount();
					convRequest.setAmount(newAmount);
					convRequest.setFromCurrency(value);
					lookupCrossMatrix(crossMatrix, convRequest);
				} else if (newvalue.equalsIgnoreCase("1:1")) {
					convRequest.setFromCurrency(value);
					lookupCrossMatrix(crossMatrix, convRequest);
				} else if (newvalue.equalsIgnoreCase("INV")) {
					String strRate = currencyRates.get(value.concat(convRequest.getFromCurrency()));
					Double rate = 1 / Double.parseDouble(strRate);
					Double newAmount = rate * convRequest.getAmount();
					convRequest.setAmount(newAmount);
					convRequest.setFromCurrency(value);
					lookupCrossMatrix(crossMatrix, convRequest);
				} else {
					convRequest.setFromCurrency(newvalue);
					lookupCrossMatrix(crossMatrix, convRequest);
				}

			}
		}

	}
}