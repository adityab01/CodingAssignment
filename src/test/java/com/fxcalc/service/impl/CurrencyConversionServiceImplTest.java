package com.fxcalc.service.impl;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.fxcalc.helper.ReferenceDataLoader;
import com.fxcalc.model.ConversionRequest;

/**
 * 
 * @author Aditya Bhanose
 *@category Test class
 */

@RunWith(MockitoJUnitRunner.class)
public class CurrencyConversionServiceImplTest {
	
	@InjectMocks
	CurrencyConversionServiceImpl mockCurrencyConversionServiceImpl;
	
	@Mock
	ConversionRequest mockconvRequest;
	
	@Mock
	ReferenceDataLoader mockreferenceDataLoader;
	
	HashMap<String, HashMap<String, String>> mockcrossMatrix;
	HashMap<String, String> mockcurrencyRates;
	HashMap<String, String> mockformatterMap;
	
	
	@Before
	public void setup(){
		mockcrossMatrix = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("AUD", "1:1");
		map.put("USD", "D");
		mockcrossMatrix.put("AUD", map);
		HashMap<String, String> map1 = new HashMap<String, String>();
		map.put("AUD", "Inv");
		map.put("USD", "1:1");
		mockcrossMatrix.put("USD", map1);
		
		mockcurrencyRates = new HashMap<String, String>();
		mockcurrencyRates.put("AUDUSD","0.8371");
		
		mockformatterMap = new HashMap<String, String>();
		mockformatterMap.put("USD","2");
		mockformatterMap.put("AUD","2");
		
		mockconvRequest = new ConversionRequest();
		mockconvRequest.setToCurrency("USD");
		mockconvRequest.setFromCurrency("AUD");
		mockconvRequest.setAmount(1.1);
	}
	
	@Test
	public void testprocessRequest() {
//		mockreferenceDataLoader.populateCurrencyRate(mockcurrencyRates);
//		mockreferenceDataLoader.populateCrossViaMatrix(mockcrossMatrix);
//		mockreferenceDataLoader.getFormatterMap(mockformatterMap);
//		mockCurrencyConversionServiceImpl.lookupCrossMatrix(mockcrossMatrix, mockconvRequest);
	}
	
}
	
	
	