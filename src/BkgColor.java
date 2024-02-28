import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BkgColor extends JFrame{

	BkgColor() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 200);
		setVisible(true);
	
	}
	public static void main(String[] args) {
		new BkgColor();
	}
}
