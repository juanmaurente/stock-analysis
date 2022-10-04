package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fileprocessors.StockFileData;
import fileprocessors.StockFileReader;

public class StockFileApplication {

	public static void main(String args[]) throws IOException {
		StockFileReader fr = new StockFileReader("table.csv");

		List<HashMap<String, Double>> dataResult = populateStockFileData(fr.getHeaders(), fr.readFileData());
		StockFileData fileData = new StockFileData();
		fileData.addData(dataResult);
		fileData.printData();
		System.out.println(dataResult.size());
	}

	/**
	 * I started from the data I expect to receive which is :
	 *  [{High=142,67 Low=141.85 ... }, 
	 *   {High=142,27 Low=141.25 ... }]
	 * 
	 * I need a List of a map with key/value pairs where keys are each heading and the different values.
	 * 
	 * 
	 * @param headers
	 * @param lines
	 * @return List
	 */
	public static List<HashMap<String, Double>> populateStockFileData(List<String> headers, List<String> lines) {
		List<HashMap<String, Double>> dataResult = new ArrayList<>();
		
		//loop over the first of lines and return an array of strings separated with a comma.
		//["142.88", "142.38", "141.33"]
		for(String line : lines) {
			String[] values = line.split(",");
			
			int counter = 0;
			HashMap<String, Double> headerValueMap = new HashMap<>();
			for(String value: values) {
				//Parse String to double
				double dval = Double.parseDouble(value);
				
				// now I need to populate our map, with headers as keys and values of this list
				headerValueMap.put(headers.get(counter), dval);
				counter ++;
			}
			dataResult.add(headerValueMap);
		}
		return dataResult;
	}

}
