import java.awt.Image;
import javax.swing.ImageIcon;

public class Ashot extends Ente{

	public Ashot(int a, int b){
		x=a;
		y=b;
		ImageIcon ii = (new ImageIcon("sa2.jpg"));
		sprite = ii.getImage();
	}

	public void move(){
		y=y+1;
	}
}