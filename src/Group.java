import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Group extends JFrame {
	
	Group(){
		JPanel panel = new JPanel();
		JLabel lbl1 = new JLabel("ID");
		JLabel lbl2 = new JLabel("PASSWORD");
		JTextField tf1 = new JTextField(20);
		JPasswordField pf1 = new JPasswordField(20);
		JButton btnOK = new JButton("OK");
		JButton btnCancel= new JButton("CANCEL");
		
		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		panel.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(lbl1).addComponent(lbl2).addComponent(btnOK));
		hGroup.addGroup(layout.createParallelGroup().addComponent(tf1).addComponent(pf1).addComponent(btnCancel));
		layout.setHorizontalGroup(hGroup);
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lbl1).addComponent(tf1));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lbl2).addComponent(pf1));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(btnOK).addComponent(btnCancel));
		layout.setVerticalGroup(vGroup);
		
		add(panel);
		
		// ウィンドウを閉じたらプログラムを終了する。
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Group Layout");
		pack();
		setSize(200, 135);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Group();
	}
}
