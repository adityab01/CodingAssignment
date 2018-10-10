package com.fxcalc.command;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Aditya Bhanose
 * @category Interface
 */
@Component(value="conversionCommand")
public interface ConversionCommand {

	void execute();
}