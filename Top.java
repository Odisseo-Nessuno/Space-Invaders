
import javax.swing.JFrame;
import java.awt.Dimension;
public class Top extends JFrame{
	public Top(){
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(350,350);
	setTitle("Spostiamo le stelle!");
	setLocationRelativeTo(null);
	setTitle("test");
	setVisible(true);
	Board b = new Board();
	add(b);
	b.repaint();
	b.requestFocus();
	
	}
	public void newSize(int a, int b){
		setSize(a,b);
	}
	public static void main(String[] args){
		Top running = new Top();
		//running.resize(running.getWidth(),running.getHeight());
	}
}
