package com.nilsonmassarenti.application.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataTransformationController {

	public Boolean converterFile(String[] args) {

		String sCurrentLine;
		BufferedReader brFlatConfiguration = readFile(args[0] + args[2]);
		Map<String, String> labelConfiguration = new LinkedHashMap<>();
		try {
			while ((sCurrentLine = brFlatConfiguration.readLine()) != null) {
				String[] dataConfiguration = sCurrentLine.split("\\s+");
				labelConfiguration.put(dataConfiguration[0], dataConfiguration[1]);

			}
			brFlatConfiguration.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		BufferedReader brFlatIDTranslator = readFile(args[0] + args[3]);
		Map<String, String> idTranslator = new LinkedHashMap<>();
		try {
			while ((sCurrentLine = brFlatIDTranslator.readLine()) != null) {
				String[] dataId = sCurrentLine.split("\\s+");
				idTranslator.put(dataId[0], dataId[1]);
			}
			brFlatIDTranslator.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		FileWriter fw;
		String separator = System.getProperty( "line.separator" );
		try {
			fw = new FileWriter(args[0] + "4-translated.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		BufferedReader brFlatVendor = readFile(args[0] + args[1]);
		if (brFlatVendor != null) {
			try {
				String[] columns = brFlatVendor.readLine().split("\\s+");
				String header = labelConfiguration.get(columns[0]);
				for (int i = 1; i < columns.length; i++) {
					columns[i] = labelConfiguration.get(columns[i]);
					if (columns[i] != null) {
						header += "\t" + columns[i];
					}
				}
				fw.write(header);
				while ((sCurrentLine = brFlatVendor.readLine()) != null) {
					String[] data = sCurrentLine.split("\\s+");
					if (idTranslator.get(data[0]) != null) {
						String values = idTranslator.get(data[0]);
						for (int i = 1; i < data.length; i++) {
							if (columns[i] != null) {
								values += "\t" + data[i];
							}

						}
						fw.write(separator + values);
					}

				}
				brFlatVendor.close();
			} catch (IOException e) {
				e.printStackTrace();
				try {
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return false;
			}
		}

		try {
			brFlatVendor.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;

	}

	private BufferedReader readFile(String fileName) {
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			br = null;
		}

		return br;
	}

}
