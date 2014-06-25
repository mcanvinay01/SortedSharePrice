package com.company.inputToOutput;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class InputFromFile {
	/**
	 * @return
	 */
	public static Map<String, List<String>> getCompanyList(String fileName){
		/*
		 * Variable Declarartion 
		 * companyList:- For storing company names from the file
		 * br:- BufferedReader for reading inputs from file
		 * currentLine:- For reading current line of the file
		 * companyMap:- A linkedHashMap for storing the monthYear of the company's share leading the market
		 */
		List<String> companyList = new ArrayList<String>();
		BufferedReader br = null;
		String currentLine = "";
		Map<String, List<String>> companyMap = new LinkedHashMap<String, List<String>>();
		try {
			/*
			 * Reading the CSV file from hardDrive
			 */
			br = new BufferedReader(new FileReader(new File(fileName)));
			while((currentLine = br.readLine()) != null){
				System.out.println("Test:::: " + br.readLine());
				if(currentLine.toLowerCase().contains("month")){
					String [] companyNames = currentLine.split(",");
					/*
					 * Putting the company names from current line to list
					 */
					for(int i=2; i< companyNames.length; i++){
						companyMap.put(companyNames[i], new ArrayList<String>());
						companyList.add(companyNames[i]);
					}
				} else {
					String monthYear = "";
					String items[] = currentLine.split(",");
					if(items[0] != null && items[1] != null){
						monthYear = items[0] + items[1];
					}
					/*
					 * Declaring a list containing price
					 */
					List<Integer> sharePrice = new ArrayList<Integer>();
					/*
					 * Putting the price value from current line to price List
					 */
					for(int i=2; i < items.length; i++){
						if(items[i] != null && items[i].trim() != null){
							String str = items[i].trim();
							Integer itemVal = Integer.parseInt(str);
							sharePrice.add(itemVal);
						}
					}
					/*
					 * Getting the maximum price value of company's share
					 * putting it into the map
					 */
					List<Integer> sortedPriceList = new ArrayList<Integer>(sharePrice);
					Collections.sort(sortedPriceList);
					int index = sharePrice.indexOf(sortedPriceList.get(sortedPriceList.size() - 1));
					System.out.println();
					String compName = companyList.get(index);
					List<String> tempList = companyMap.get(compName);
					tempList.add(monthYear);
					companyMap.put(compName, tempList);
				}
			} 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			/*
			 * Closing bufferedReader 
			 */
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return companyMap;
	}
}
