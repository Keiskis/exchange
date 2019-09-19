package Loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import Processor.MainData;
import Processor.Parser;

public class FileDataLoader {

 	public static void getDataFromFile() {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("c:/file.csv"))) {
			Parser.parse(bufferedReader);
		} catch (IOException e) {
			MainData.err = e.getMessage(); 
			e.printStackTrace();
		}
	}
}
