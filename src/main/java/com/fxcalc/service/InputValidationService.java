package com.fxcalc.service;

import com.fxcalc.model.ConversionRequest;

/**
 *  @author Aditya Bhanose
 *  @since 6/10/2018
 *  @version 1.0
 *  Service class to validate input command from console  
 */


public interface InputValidationService {
	
	
	/**
	 * Method to validate the input from console
	 * @param inputCommand
	 * @return
	 * @throws Exception
	 */
	boolean validateInput(String inputCommand, ConversionRequest convRequest) throws Exception;
	
	
	
}