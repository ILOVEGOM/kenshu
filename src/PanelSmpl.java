import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelSmpl extends JFrame{
	static boolean flag = true;
	
	PanelSmpl() {
		ImageIcon image = new ImageIcon("image/marir.gif");	
		JLabel label = new JLabel(image);
		JScrollPane scr = new JScrollPane(label);
//		label.setPreferredSize(new Dimension(220, 40));
//		LineBorder border = new LineBorder(Color.BLACK, 1, true);
//		label.setBorder(border);
		flag = true;
		label.setHorizontalAlignment(JLabel.CENTER);
	
		JButton button = new JButton("変更");
		button.setPreferredSize(new Dimension(220, 40));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (flag == true) {
					ImageIcon image = new ImageIcon("image/maril.gif");
					flag = false;
					label.setIcon(image);
				}else {
					ImageIcon image = new ImageIcon("image/marir.gif");
					flag = true;
					label.setIcon(image);
				}
//				label.setIcon(image);
			}
		});
		setLayout(new GridLayout(2, 1, 5, 5));
		JPanel panel = new JPanel();
//		panel.add(label);
//		panel.add(button);
		add(scr);
		add(button);
//		add(panel);
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 200);
		setVisible(true);
		
		}
		
		public static void main(String[] args) {
			new PanelSmpl();
		}
}
