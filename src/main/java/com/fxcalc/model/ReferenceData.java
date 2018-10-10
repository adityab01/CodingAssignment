package com.fxcalc.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.stream.Stream;
import org.springframework.util.ResourceUtils;

//@Component(value = "referenceData")
public class ReferenceData {

	public static ReferenceData refDataInstance = null;
	public HashMap<String, String> ratesMap = null;
	public HashMap<String, String> formatterMap = null;
	final static String FILE_NAME_RATES = "ReferecneData_Rate.txt";
	final static String FILE_NAME_FORMATTER = "ReferenceData_Formatter.txt";

	private ReferenceData() {

	}

	public static synchronized ReferenceData getInstance() {
		try {

			if (refDataInstance == null) {
				refDataInstance = new ReferenceData();
				HashMap<String, String> currencyMap = new HashMap<String, String>();
				HashMap<String, String> decimalPosMap = new HashMap<String, String>();
				
				//Read the currency rates from the reference file and loads in map
				File file = ResourceUtils.getFile("classpath:" + FILE_NAME_RATES);
				Stream<String> stream = Files.lines(file.toPath());
				stream.filter(line -> line.contains("="))
						.forEach(line -> currencyMap.putIfAbsent(line.split("=")[0], line.split("=")[1]));
				refDataInstance.setRatesMap(currencyMap);
				
				
				//Read the decimal point position for each currency and loads in map
				file = ResourceUtils.getFile("classpath:" + FILE_NAME_FORMATTER);
				stream = Files.lines(file.toPath());
				stream.filter(line -> line.contains("="))
						.forEach(line -> decimalPosMap.putIfAbsent(line.split("=")[0], line.split("=")[1].trim()));
				refDataInstance.setFormatterMap(decimalPosMap);
				stream.close();
			}
		} catch (IOException ioe) {
			System.out.println("\n Reference Currency rates file not available");
			ioe.printStackTrace();
		}
		return refDataInstance;
	}

	public HashMap<String, String> getRatesMap() {
		return ratesMap;
	}

	public void setRatesMap(HashMap<String, String> ratesMap) {
		this.ratesMap = ratesMap;
	}

	public HashMap<String, String> getFormatterMap() {
		return formatterMap;
	}

	public void setFormatterMap(HashMap<String, String> formatterMap) {
		this.formatterMap = formatterMap;
	}

	
	

}