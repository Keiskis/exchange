package Processor;

public class ExchangeCalculator {

	static double exchangeRateFrom;
	static double exchangeRateTo;
	static double exchangeAmount;
	static MainData data =  MainData.getInstance();
	
	public static String exchange(String from, String to, String amount) {
		 exchangeRateFrom = data.getCoins().get(from);
		 exchangeRateTo = data.getCoins().get(to);
		 exchangeAmount = 0.0;
		if (amount.contains(",")) {
			exchangeAmount = Double.parseDouble(amount.replace(",", "."));
		} else {
			exchangeAmount = Double.parseDouble(amount);
		}
		System.out.println("Exchange rate from: [" + from + "] " + exchangeRateFrom + ", amount: " + exchangeAmount + ", exchange rate to: ["
			+ to + "] " + exchangeRateTo);
		double withdravalAmaount = (exchangeRateFrom * exchangeAmount) / exchangeRateTo;
		String withdrawal = String.format ("%.18f", withdravalAmaount);
		System.out.println("Withdrawal: " + withdrawal + " " + to);
		return withdrawal;
	}
}
