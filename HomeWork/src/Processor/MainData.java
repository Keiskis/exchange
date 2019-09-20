package Processor;

import java.util.Map;

public class MainData {

	private static MainData mainData;
	private Map<String, Double> coins;
	private String err;
 
	private MainData() {
	}
	
	public static synchronized MainData getInstance() {
		if(mainData == null) {
			mainData = new MainData();
		}
		return mainData;
	}

	public Map<String, Double> getCoins() {
		return coins;
	}

	public void setCoins(Map<String, Double> coins) {
		this.coins = coins;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}
}
