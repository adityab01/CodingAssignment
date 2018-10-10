package com.fxcalc.command;

import java.util.HashMap;
import com.fxcalc.model.ConversionRequest;
import com.fxcalc.model.ReferenceData;
import com.fxcalc.util.FxCalculatorUtil;

public class InversionCommand implements ConversionCommand {

	ConversionRequest convRequest;

	public InversionCommand(ConversionRequest convRequest) {

		this.convRequest = convRequest;

	}

	@Override
	public void execute() {
		HashMap<String, String> ratesMap = ReferenceData.getInstance().getRatesMap();
		String strRate = ratesMap.get(convRequest.getToCurrency().concat(convRequest.getFromCurrency()));
		Double rate = 1 / Double.parseDouble(strRate);
		FxCalculatorUtil.displayFormattedAmount(convRequest.getToCurrency(), rate * convRequest.getAmount());
	}

}