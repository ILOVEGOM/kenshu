import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GridBag extends JFrame {
	
	GridBag(){
		//パネルを作る。
		JPanel panel = new JPanel();
		
		// コンポーネントを作る。
		JLabel lblID = new JLabel("ID:");
		JTextField idfield = new JTextField(20);
		JLabel lblPass = new JLabel("パスワード:");
		JPasswordField passfield =  new JPasswordField(20);
		JButton btnOK = new JButton("OK");
		JButton btnCancle = new JButton("Cancel");
		
		// レイアウトを設定する。
		GridBagLayout layout = new GridBagLayout();
		layout.columnWeights = new double[9];
		layout.columnWidths = new int [9];
		for (int i = 0; i < layout.columnWeights.length; i++) {
			layout.columnWeights[i] = 1.0;
			layout.columnWidths[i] = 100;
		}
		layout.rowWeights = new double[5];
		layout.rowHeights = new int[5];
		for (int i = 0; i < layout.rowHeights.length; i++) {
			layout.rowWeights[i] = 1.0;
			layout.rowHeights[i] = 100;
		}
		panel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		layout.setConstraints(lblID, gbc);
		panel.add(lblID);
		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(idfield, gbc);
		panel.add(idfield);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		layout.setConstraints(lblPass, gbc);
		panel.add(lblPass);
		gbc.gridx = 5;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(passfield, gbc);
		panel.add(passfield);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		layout.setConstraints(btnOK, gbc);
		panel.add(btnOK);
		gbc.gridx = 5;
		gbc.gridy = 3;
		gbc.gridwidth = 3;
		gbc.gridheight =1; 
		layout.setConstraints(btnCancle, gbc);
		panel.add(btnCancle);
		
		add(panel);
		
		// ウィンドウを閉じたらプログラムを終了する。
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("GridBag");
		pack();
		setSize(200, 180);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new GridBag();
	}
}
