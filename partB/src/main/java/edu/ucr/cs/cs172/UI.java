package edu.ucr.cs.cs172;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class UI extends JFrame {
	JPanel jp = new JPanel();
	JLabel jl = new JLabel();
	JTextField jt = new JTextField(30);

	public UI() {
	setTitle("Search");
	setVisible(true);
	setSize(400,200);
	setDefaultCloseOperation(EXIT_ON_CLOSE);

														                jp.add(jt);
														                add(jp);													        }

	public static void main(String[] args) {
		UI tBox = new UI();
	}
}
