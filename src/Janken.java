import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Janken extends JFrame {
	static Random random = new Random();
	static int rs ;
	public Janken(){
		
		//buttonのImageの大きさ調整
		JButton button1 = new JButton();
		JButton button2 = new JButton();
		JButton button3 = new JButton();
		ImageIcon image1 = new ImageIcon("image/0.png");
		ImageIcon image2 = new ImageIcon("image/1.png");	
		ImageIcon image3 = new ImageIcon("image/2.png");
		Image img1 = image1.getImage();
		Image img2 = image2.getImage();
		Image img3 = image3.getImage();
		Image update1 = img1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		Image update2 = img2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		Image update3 = img3.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon rock = new ImageIcon(update1);
		ImageIcon seaser = new ImageIcon(update2);
		ImageIcon paper = new ImageIcon(update3);
		button1.setIcon(rock);
		button2.setIcon(seaser);
		button3.setIcon(paper);
		
		rs = random.nextInt(3);
		
		button1.setPreferredSize(new Dimension(100, 100));
		button2.setPreferredSize(new Dimension(100, 100));
		button3.setPreferredSize(new Dimension(100, 100));
		
		JPanel buttonspanel = new JPanel();
		
		  //result panel
		JPanel rspanel = new JPanel();
		JLabel user= new JLabel();
		JLabel result = new JLabel();
		JLabel com = new JLabel();
		
		
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rs == 0) {
					user.setIcon(rock);
					com.setIcon(rock);
					result.setText("引き分け");
					rs = random.nextInt(3);
					System.out.println(rs);
				}else if (rs == 1) {
					user.setIcon(rock);
					com.setIcon(seaser);
					result.setText("勝ち");
					rs = random.nextInt(3);
					System.out.println(rs);
				}else if (rs == 2){
					user.setIcon(rock);
					com.setIcon(paper);
					result.setText("負け");
					rs = random.nextInt(3);
					System.out.println(rs);
				}
				
			}
		});
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rs == 0) {
					user.setIcon(seaser);
					com.setIcon(rock);
					result.setText("負け");
					rs = random.nextInt(3);
					System.out.println(rs);
				}else if (rs == 1) {
					user.setIcon(seaser);
					com.setIcon(seaser);
					result.setText("引き分け");
					rs = random.nextInt(3);
					System.out.println(rs);
				}else if (rs == 2){
					user.setIcon(seaser);
					com.setIcon(paper);
					result.setText("勝ち");
					rs = random.nextInt(3);
					System.out.println(rs);
				}
			}
		});
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rs == 0) {
					user.setIcon(paper);
					com.setIcon(rock);
					result.setText("勝ち");
					rs = random.nextInt(3);
					System.out.println(rs);
				}else if (rs == 1) {
					user.setIcon(paper);
					com.setIcon(seaser);
					result.setText("負け");
					rs = random.nextInt(3);
					System.out.println(rs);
				}else if (rs == 2){
					user.setIcon(paper);
					com.setIcon(paper);
					result.setText("引き分け");
					rs = random.nextInt(3);
					System.out.println(rs);
				}
			}
		});
		setLayout(new GridLayout(4, 1, 5, 5));
		add(buttonspanel);
		buttonspanel.add(button1);
		buttonspanel.add(button2);
		buttonspanel.add(button3);
		
		add(rspanel);
		rspanel.add(user);
		rspanel.add(com);
		rspanel.add(result);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 700);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Janken();
		
	}
}
