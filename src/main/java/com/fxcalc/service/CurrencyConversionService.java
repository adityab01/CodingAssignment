package com.fxcalc.service;

import com.fxcalc.model.ConversionRequest;

/**
 * 
 * @author Aditya Bhanose
 * @since 6/10/2018
 * @version 1.0
 * Service class provides the currency conversion
 */


public interface CurrencyConversionService{
	
	
	public void processRequest(ConversionRequest convRequest);
	
	
	
}