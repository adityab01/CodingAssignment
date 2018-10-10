package com.fxcalc.command;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Aditya Bhanose
 * @Catgory 
 * Invoker to execute the commands
 */
@Component(value="currencyConvertor")
public class CurrencyConvertor {

	private ConversionCommand command;

	public void setCommand(ConversionCommand command) {

		this.command = command;

	}

	public void executeCommand() {

		command.execute();

	}

}