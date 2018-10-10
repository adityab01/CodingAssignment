package com.fxcalc.command;

import java.util.HashMap;

import com.fxcalc.model.ConversionRequest;
import com.fxcalc.model.CrossViaMatrix;
import com.fxcalc.model.ReferenceData;

public class CurrencyCommand implements ConversionCommand {

	ConversionRequest convRequest;

	public CurrencyCommand(ConversionRequest convRequest) {
		this.convRequest = convRequest;
	}

	@Override
	public void execute() {

		HashMap<String, HashMap<String, String>> crossMatrix = CrossViaMatrix.getInstance().getMatrixMap();
		HashMap<String, String> ratesMap = ReferenceData.getInstance().getRatesMap();
		HashMap<String, String> map = crossMatrix.get(convRequest.getFromCurrency());
		String strRate = "";
		Double rate = 0.0;
		String newvalue = map.get(convRequest.getCrossCurrency());

		if (newvalue.equalsIgnoreCase("D")) {

			strRate = ratesMap.get(convRequest.getFromCurrency().concat(convRequest.getCrossCurrency()));
			rate = Double.parseDouble(strRate);
			Double newAmount = rate * convRequest.getAmount();
			convRequest.setAmount(newAmount);
			convRequest.setFromCurrency(convRequest.getCrossCurrency());
			// lookupCrossMatrix(crossMatrix, convRequest);
		} else if (newvalue.equalsIgnoreCase("1:1")) {
			convRequest.setFromCurrency(convRequest.getCrossCurrency());
			// lookupCrossMatrix(crossMatrix, convRequest);
		} else if (newvalue.equalsIgnoreCase("INV")) {
			strRate = ratesMap.get(convRequest.getCrossCurrency().concat(convRequest.getFromCurrency()));
			rate = 1 / Double.parseDouble(strRate);
			Double newAmount = rate * convRequest.getAmount();
			convRequest.setAmount(newAmount);
			convRequest.setFromCurrency(convRequest.getCrossCurrency());
			// lookupCrossMatrix(crossMatrix, convRequest);
		} else {
			convRequest.setFromCurrency(newvalue);
			// (crossMatrix, convRequest);
		}

	}

}