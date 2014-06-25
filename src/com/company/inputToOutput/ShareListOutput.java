package com.company.inputToOutput;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ShareListOutput {
	public static void main(String args[]){
		/*
		 * Getting Map with company name as key and list of year as value
		 */
		Map<String, List<String>> sharePriceMap = InputFromFile.getCompanyList("sampleData.csv");
		Set<Entry<String, List<String>>> entry = sharePriceMap.entrySet();
		Iterator<Entry<String, List<String>>> it = entry.iterator();
		while(it.hasNext()){
			Map.Entry<String, List<String>> mapEntry = (Map.Entry<String, List<String>>) it.next();
			/*
			 * Displaying the Company name
			 */
			System.out.println(mapEntry.getKey());
			List<String> monthYear = mapEntry.getValue();
			for(String value : monthYear){
				/*
				 * Displaying the month year
				 */
				System.out.println(value);
			}
		}
	}
}
