package com.fxcalc.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxcalc.model.ConversionRequest;
import com.fxcalc.service.InputValidationService;

/**
 * @author Aditya Bhanose
 * @since 6/10/2018
 * @version 1.0
 * @category Service implementation
 */
@Component(value = "inputValidationService")
public class InputValidationServiceImpl implements InputValidationService {

	@Autowired
	private ConversionRequest convRequest;

	/**
	 * Method to validate the input from console
	 * 
	 * @param inputCommand
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean validateInput(String inputCommand, ConversionRequest convRequest) {

		boolean isValid = true;
		try {

			String[] splitedCommand = inputCommand.split(" ");
			Map<String, String> supportedCurrency = convRequest.getSupportedCurrency();

			if (splitedCommand.length != 4) {
				System.out.println("\n Invalid Request.Please enter valid input");
				return false;

			} else {

				if (splitedCommand[0].length() != 3 && splitedCommand[3].length() != 3) {
					System.out.println("\n Incorrect Currency.Please enter valid To and From currency");
					return false;
				}

				if (!(splitedCommand[0].isEmpty()) && (supportedCurrency.get(splitedCommand[0].toUpperCase()) == null)) {
					System.out.println("\n From Currency not supported.Please enter valid from currency");
					return false;
				}

				if (!(splitedCommand[3].isEmpty()) && (supportedCurrency.get(splitedCommand[3].toUpperCase()) == null)) {
					System.out.println("\n To Currency not supported.Please enter valid TO currency");
					return false;
				}

				if (!splitedCommand[1].isEmpty()) {
					// Double.parseDouble(splitedCommand[1]);
					convRequest.setAmount(Double.parseDouble(splitedCommand[1]));
					convRequest.setFromCurrency(splitedCommand[0].toUpperCase());
					convRequest.setToCurrency(splitedCommand[3].toUpperCase());
				}
			}

		} catch (NumberFormatException nfe) {
			System.out.println("\n Invalid Amount.Please enter valid amount");
			return false;
			// nfe.printStackTrace();
		}

		catch (Exception e) {
			System.out.println("\n Invalid Request.Please enter valid request");
			// e.printStackTrace();
			// throw e;
			return false;
		}

		return isValid;

	}

}