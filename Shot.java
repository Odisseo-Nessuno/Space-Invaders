import java.awt.Image;
import javax.swing.ImageIcon;
public class Shot extends Ente {

	public Shot(int a, int b){
		x=a;
		y=b;
		ImageIcon ii = (new ImageIcon(this.getClass().getResource("s2.jpg")));
		sprite =ii.getImage();
	}

	public void move(){
		y=y-5;
	}
	public void setXY(int a, int b){
			x=a;
			y=b;
	}
}