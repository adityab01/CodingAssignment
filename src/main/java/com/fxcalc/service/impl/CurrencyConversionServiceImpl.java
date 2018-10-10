package com.fxcalc.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxcalc.command.ConversionCommand;
import com.fxcalc.command.CurrencyCommand;
import com.fxcalc.command.CurrencyConvertor;
import com.fxcalc.command.DirectFeedCommand;
import com.fxcalc.command.InversionCommand;
import com.fxcalc.command.UnityCommand;
import com.fxcalc.model.ConversionRequest;
import com.fxcalc.model.CrossViaMatrix;
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
	HashMap<String, String> ratesMap;

	@Autowired
	CurrencyConvertor currencyConv;
	
	/**
	 * Method to process the input request
	 */
	@Override
	public void processRequest(ConversionRequest convRequest) {

		currencyConv = new CurrencyConvertor();
		ConversionCommand directCommand = new DirectFeedCommand(convRequest);
		ConversionCommand unityCommand = new UnityCommand(convRequest);
		ConversionCommand inversionCommand = new InversionCommand(convRequest);
		ConversionCommand currencyCommand = new CurrencyCommand(convRequest);

		String crossMatrixValue = lookupCrossMatrix(convRequest);

		// if Direct Feed
		if (crossMatrixValue.equalsIgnoreCase("D")) {
			currencyConv.setCommand(directCommand);
			directCommand.execute();
		}
		// If unity
		else if (crossMatrixValue.equalsIgnoreCase("1:1")) {
			currencyConv.setCommand(unityCommand);
			unityCommand.execute();

		}
		// If Inversion
		else if (crossMatrixValue.equalsIgnoreCase("INV")) {
			currencyConv.setCommand(inversionCommand);
			inversionCommand.execute();
		}
		// If Currency
		else {
			convRequest.setCrossCurrency(crossMatrixValue);
			currencyConv.setCommand(inversionCommand);
			currencyCommand.execute();
			processRequest(convRequest);
		}
	}

	/**
	 * Method to lookup Cross via Matrix
	 * 
	 * @param convRequest
	 * @return
	 */
	public String lookupCrossMatrix(ConversionRequest convRequest) {

		crossMatrix = CrossViaMatrix.getInstance().getMatrixMap();
		HashMap<String, String> map = crossMatrix.get(convRequest.getFromCurrency());
		String value = map.get(convRequest.getToCurrency());
		return value;
	}

}