package edu.ucr.cs.cs172;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;


public class UI extends JFrame {
	public boolean enterButton = false; //variable accessed by Manager.java
	public Scanner userInput = new Scanner(System.in);

	JPanel jp = new JPanel();
	JLabel jl = new JLabel();
	JTextField jt = new JTextField(30);
	JButton jb = new JButton("Enter");

	public UI() {
	setTitle("Search");
	setVisible(true);
	setSize(400,200);
	setDefaultCloseOperation(EXIT_ON_CLOSE);

	jp.add(jt);
	//Above code creates and displays text field "search box"

	jt.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent temp) {
			enterButton = true; //Manager class checks to see if this variable is true
			String userString = userInput.nextLine();
			//userString = jt.getText();
			jl.setText(userString);
			}
		});
	jp.add(jb);
	//Above code copies and displays user input next to text field
	jb.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent temp) {
			String input = jt.getText();
			jl.setText(input);
		}
		});
	jp.add(jl);
	add(jp);
	}
	//Above code checks to see if JButton is pressed. If it is then the input is retrieved...
	public static void main(String[] args) { //Not needed. Only when testing UI separately.

		UI tBox = new UI();
	}
}
