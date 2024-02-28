import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame{

		Window() {
			//ラベルを作る
			JLabel label = new JLabel("ウインドウ　サンプルコード");
			add(label);
			
			// 画面サイズを取得して画面の中央に表示する。
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gs[] = ge.getScreenDevices();
			GraphicsDevice gd = gs[0];
			GraphicsConfiguration gc[] = gd.getConfigurations();
			GraphicsConfiguration gc0 = gc[0];
			Rectangle rect = gc0.getBounds();
			int dw = rect.width;
			int dh = rect.height;
			
			int ww = 300;
			int wh = 200;
			Rectangle rct = new Rectangle((dw - ww)/2, (dh - wh)/2, ww, wh);
			setBounds(rct);	
			
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setTitle("Window");
			setVisible(true);
		}
		
		public static void main(String[] args) {
			new Window();
		}
}
