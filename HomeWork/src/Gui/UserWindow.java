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

import Loader.FileDataLoader;
import Processor.ExchangeCalculator;
import Processor.MainData;

public class UserWindow extends JApplet {

	static JLabel labelFrom = new JLabel("From", JLabel.CENTER);
	static JLabel labelTo = new JLabel("To", JLabel.CENTER);
	static JLabel labelOutput = new JLabel("0.0", JLabel.LEFT);
	static JLabel label = new JLabel(" ");
	static JTextField inputValue = new JTextField(15);
	static JComboBox<String> comboFrom = new JComboBox<String>();
	static JComboBox<String> comboTo = new JComboBox<String>();
	static JButton buttonCalculate = new JButton("Calculate");
	String fromIndex = "";
	String toIndex = "";
	String amount = "0.0";

	public void init() {
		for (String currency : MainData.coins.keySet()) {
			comboFrom.addItem(currency);
			comboTo.addItem(currency);
		}

		fromIndex = (String) comboFrom.getSelectedItem();
		toIndex = (String) comboTo.getSelectedItem();
		if (MainData.err != null) {
			label.setFont(new Font("Courier New", Font.ITALIC, 8));
			label.setForeground(Color.RED);
			label.setText(MainData.err);
		}
		buttonCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amount = inputValue.getText();
				if (amount.equals(""))
					amount = "0.0";
				System.out.println("User select: " + amount + " " + fromIndex + ", convert to: " + toIndex);
				if (fromIndex != null && toIndex != null) {
					amount = ExchangeCalculator.exchange(fromIndex, toIndex, amount);
					labelOutput.setText(amount);
				} else {
					labelOutput.setText("ERROR");
					System.out.println("ERROR: " + MainData.err);
				}
			}
		});

		comboFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fromIndex = (String) comboFrom.getSelectedItem();
			}
		});

		comboTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toIndex = (String) comboTo.getSelectedItem();
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