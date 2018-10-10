package com.fxcalc.model;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.util.ResourceUtils;

public class CrossViaMatrix {

	public static CrossViaMatrix matrixInstance = null;
	public HashMap<String, HashMap<String, String>> matrixMap = null;
	final static String FILE_NAME_MATRIX = "ReferenceData_Matrix.txt";

	private CrossViaMatrix() {

	}

	public static synchronized CrossViaMatrix getInstance() {
		try {

			if (matrixInstance == null) {
				matrixInstance= new CrossViaMatrix();
				File file = ResourceUtils.getFile("classpath:" + FILE_NAME_MATRIX);
				HashMap<String, String> mapFromFile = new HashMap<>();
				HashMap<String, HashMap<String, String>> map = new HashMap<String, HashMap<String, String>>();
				Stream<String> stream = Files.lines(file.toPath());
				stream.filter(line -> line.contains("-"))
						.forEach(line -> mapFromFile.putIfAbsent(line.split("-")[0], line.split("-")[1]));

				for (Map.Entry<String, String> entry : mapFromFile.entrySet()) {
					String value[] = entry.getValue().split(",");
					HashMap<String, String> mapCrossMatrix = new HashMap<String, String>();
					for (String temp : value) {
						mapCrossMatrix.put(temp.split("=")[0], temp.split("=")[1]);
					}
					map.put(entry.getKey(), mapCrossMatrix);
				}
				matrixInstance.setMatrixMap(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return matrixInstance;
	}

	
	public HashMap<String, HashMap<String, String>> getMatrixMap() {
		return matrixMap;
	}

	public void setMatrixMap(HashMap<String, HashMap<String, String>> matrixMap) {
		this.matrixMap = matrixMap;
	}

}