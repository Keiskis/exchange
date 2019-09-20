package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Processor.ExchangeCalculator;
import Processor.MainData;

public class UserWindow extends JApplet {

	private static final long serialVersionUID = 2453831924003382755L;
	static JLabel labelFrom = new JLabel("Exchange from", JLabel.CENTER);
	static JLabel labelTo = new JLabel("Exchange to", JLabel.CENTER);
	static JLabel labelOutput = new JLabel("0.0", JLabel.LEFT);
	static JLabel label = new JLabel(" ");
	static JTextField inputValue = new JTextField(20);
	static JComboBox<String> comboFrom = new JComboBox<String>();
	static JComboBox<String> comboTo = new JComboBox<String>();
	static JButton buttonCalculate = new JButton("Exchange");
	String fromKey;
	String toKey;
	String amount;
	MainData data =  MainData.getInstance();

	public void init() {
		if (data.getCoins() != null) {
			for (String currency : data.getCoins().keySet()) {
				comboFrom.addItem(currency);
				comboTo.addItem(currency);
			}
		}
		fromKey = (String) comboFrom.getSelectedItem();
		toKey = (String) comboTo.getSelectedItem();
		if (data.getErr() != null) {
			label.setFont(new Font("Courier New", Font.ITALIC, 8));
			label.setForeground(Color.RED);
			label.setText(data.getErr());
		}
		buttonCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amount = inputValue.getText();
				if (amount.equals(""))
					amount = "0.0";
				System.out.println("User select: " + amount + " " + fromKey + ", convert to: " + toKey);
				if (fromKey != null && toKey != null) {
					amount = ExchangeCalculator.exchange(fromKey, toKey, amount);
					labelOutput.setText(amount);
				} else {
					labelOutput.setText("ERROR");
					System.out.println("ERROR: " + data.getErr());
				}
			}
		});

		comboFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fromKey = (String) comboFrom.getSelectedItem();
			}
		});

		comboTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toKey = (String) comboTo.getSelectedItem();
			}
		});
	}

	public static void run(JApplet applet) {
		applet.init();
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Currency exchange");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(4, 2));
		frame.add(labelFrom);
		frame.add(labelTo);
		frame.add(comboFrom);
		frame.add(comboTo);
		frame.add(inputValue);
		frame.add(labelOutput);
		frame.add(label);
		frame.add(buttonCalculate);
		frame.pack();
		frame.setVisible(true);
	}
}