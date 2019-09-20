package Loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import Processor.MainData;
import Processor.Parser;

public class FileDataLoader {
	 
	 static String filePath = "c:/tmp/file.csv";
	 static MainData data =  MainData.getInstance();
	
 	public static  void getDataFromFile() {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
			System.out.println("Loaded file: " + filePath);
			Parser.parse(bufferedReader);
		} catch (IOException e) {
			data.setErr(e.getMessage()); 
			e.printStackTrace();
		}
	}
}
