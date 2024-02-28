import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class TraceMouse extends JFrame {
	
	// Point型のリストを作成する。
	List<Point> poss = new ArrayList<>();
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < poss.size() - 1; i++) {
			int x1 = (int)poss.get(i).getX();
			int y1 = (int)poss.get(i).getY();
			int x2 = (int)poss.get(i+1).getX();
			int y2 = (int)poss.get(i+1).getY();
			g.drawLine(x1, y1, x2, y2);
		}
	}
	class MyMouseListner implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			poss.add(e.getPoint());
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			poss.clear();
		}
		
	}
	
	public TraceMouse(){
		addMouseMotionListener(new MyMouseListner());
		
		//ウィンドウを閉じたらプログラムを終了する。
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("TraceMouse");
		setSize(500,300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TraceMouse();
	}
}
