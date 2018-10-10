package com.fxcalc.command;

import com.fxcalc.model.ConversionRequest;
import com.fxcalc.util.FxCalculatorUtil;

public class UnityCommand implements ConversionCommand {

	ConversionRequest convRequest;

	public UnityCommand(ConversionRequest convRequest) {

		this.convRequest = convRequest;

	}

	@Override
	public void execute() {
		FxCalculatorUtil.displayFormattedAmount(convRequest.getToCurrency(),convRequest.getAmount());
	}

}