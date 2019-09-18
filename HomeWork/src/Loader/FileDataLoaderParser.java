package Loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileDataLoaderParser {
	public static Map<String, Double> coins;
    public static String err = "";
    
	public static void getDataFromFile() {
		String line = "";
		coins = new HashMap<String, Double>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("c:/file.csv"))) {

			while ((line = bufferedReader.readLine()) != null) {
				String[] string = line.split("\r\n");
				System.out.println("Get line: " + string[0]);
				if (!string[0].contains("\"")) {
					String[] s = string[0].split(",");
					double value = toFloat(s[1]);
					System.out.println("Parsed line: " + s[0] + "  " + value);
					coins.put(s[0], value);
				} else {
					String[] s = string[0].split("\"");
					String coin = s[0].substring(0, s[0].length() - 1);
					double value = toFloat(s[1]);
					System.out.println("Parsed line: " + coin + "  " + value);
					coins.put(coin, value);
				}
			}
			System.out.println("Parser end with result:");
			System.out.println(coins);

		} catch (IOException e) {
			err = e.getMessage();
			e.printStackTrace();
		}
	}

	private static double toFloat(String value) {
		if (value.contains(","))
			return Double.parseDouble(value.replace(",", "."));
		return Double.parseDouble(value);
	}

}
