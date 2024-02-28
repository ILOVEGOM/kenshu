import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Border extends JFrame {
	
	Border(){
		setLayout(new BorderLayout(5, 5));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JButton btnn = new JButton("North Button");
		JButton btns = new JButton("South button");
		JButton btnw = new JButton("West");
		JButton btne = new JButton("East");
		JButton btnc = new JButton("Center Button");
		
		add(btnn, BorderLayout.NORTH);
		add(btns, BorderLayout.SOUTH);
		add(btnw, BorderLayout.WEST);
		add(btne, BorderLayout.EAST);
		add(btnc, BorderLayout.CENTER);
		
		setTitle("Border");
		pack();
		setSize(300,160);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Border();
	}
}
