import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class searchTextBox extends JFrame {
	JPanel jp = new JPanel();
	JLabel jl = new JLabel();
	JTextField jt = new JTextField(30);

	public searchTextBox() {
	setTitle("Search");
	setVisible(true);
	setSize(400,200);
	setDefaultCloseOperation(EXIT_ON_CLOSE);

														                jp.add(jt);
														                add(jp);													        }

	public static void main(String[] args) {
		searchTextBox tBox = new searchTextBox();
	}
}
