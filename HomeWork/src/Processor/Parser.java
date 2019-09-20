package Processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class Parser {
	public static void parse(BufferedReader bufferedReader) throws IOException {

		String line = "";
		MainData data = MainData.getInstance();
		data.setCoins(new HashMap<String, Double>());
		System.out.println("Start parser....");

		while ((line = bufferedReader.readLine()) != null) {
			String[] string = line.split("\r\n");
			System.out.println("Get line: " + string[0]);
			if (!string[0].contains("\"")) {
				String[] s = string[0].split(",");
				double value = toDouble(s[1]);
				System.out.println("Parsed line: " + s[0] + "  " + value);
				data.getCoins().put(s[0], value);
			} else {
				String[] s = string[0].split("\"");
				String coin = s[0].substring(0, s[0].length() - 1);
				double value = toDouble(s[1]);
				System.out.println("Parsed line: " + coin + "  " + value);
				data.getCoins().put(coin, value);
			}
		}
		System.out.println("Parser end with result:");
		System.out.println(data.getCoins());
		System.out.println("--------------------------------------------");
	}

	private static double toDouble(String value) {
		if (value.contains(","))
			return Double.parseDouble(value.replace(",", "."));
		return Double.parseDouble(value);
	}
}
