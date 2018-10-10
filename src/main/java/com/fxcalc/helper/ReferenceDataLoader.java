package com.fxcalc.helper;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

/**
 * 
 * @author Aditya Bhanose
 * @since 6/10/2018
 * @version 1.0
 */

@Component(value = "referenceDataLoader")
public class ReferenceDataLoader {
//
//	final String FILE_NAME_MATRIX = "ReferenceData_Matrix.txt";
////	final String FILE_NAME_RATES = "ReferecneData_Rate.txt";
//	final String FILE_NAME_FORMATTER = "ReferenceData_Formatter.txt";
//	final String PATH = "//resources//";
//	String key, value = "";
	
	/**
	 * 
	 * @param currencyRate
	 */
	
//	public HashMap<String, String> populateCurrencyRate() {
//		HashMap<String, String> currencyRate = new HashMap<String, String>();
//		try {
//			File file = ResourceUtils.getFile("classpath:" + FILE_NAME_RATES);
//			Stream<String> stream = Files.lines(file.toPath());
//			stream.filter(line -> line.contains("="))
//					.forEach(line -> currencyRate.putIfAbsent(line.split("=")[0], line.split("=")[1]));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return currencyRate;
//	}
	
	
//	/**
//	 * Method loads the Cross Via Matrix from file
//	 * @param crossMatrix
//	 */
//	public HashMap<String, HashMap<String, String>> populateCrossViaMatrix() {
//		HashMap<String, HashMap<String, String>> crossMatrix = new HashMap<String, HashMap<String, String>>();
//		try {
//			File file = ResourceUtils.getFile("classpath:" + FILE_NAME_MATRIX);
//			HashMap<String, String> mapFromFile = new HashMap<>();
//			Stream<String> stream = Files.lines(file.toPath());
//			stream.filter(line -> line.contains("-"))
//					.forEach(line -> mapFromFile.putIfAbsent(line.split("-")[0], line.split("-")[1]));
//
//			for (Map.Entry<String, String> entry : mapFromFile.entrySet()) {
//				// System.out.println("Key : " + entry.getKey() + " Value : " +
//				// entry.getValue());
//				String value[] = entry.getValue().split(",");
//				HashMap<String, String> mapCrossMatrix = new HashMap<String, String>();
//				for (String temp : value) {
//					mapCrossMatrix.put(temp.split("=")[0], temp.split("=")[1]);
//				}
//
//				crossMatrix.put(entry.getKey(), mapCrossMatrix);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return crossMatrix;
//	}
	
//	/**
//	 * Method to get the country specific decimal position
//	 * @param formatterMap
//	 */
//	public HashMap<String, String> getFormatterMap() {
//		HashMap<String, String> formatterMap = new HashMap<String, String>();
//		try {
//
//			File file = ResourceUtils.getFile("classpath:" + FILE_NAME_FORMATTER);
//			Stream<String> stream = Files.lines(file.toPath());
//			stream.filter(line -> line.contains("="))
//					.forEach(line -> formatterMap.putIfAbsent(line.split("=")[0], line.split("=")[1].trim()));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return formatterMap;
//	}
}