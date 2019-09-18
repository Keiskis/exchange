package Loader;

import Gui.UserWindow;

public class Main {

	public static void main(String[] args) {
		FileDataLoaderParser.getDataFromFile();
		UserWindow.run(new UserWindow());
	}

}
