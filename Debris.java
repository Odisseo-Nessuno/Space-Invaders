import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.util.Random;
public class Debris extends Ente{

	private int dx, dy;

	public Debris(int a, int b){
			x=a;
			y=b;
			Random r = new Random();
			double dice =r.nextDouble() * 10;
			ImageIcon ii = null;
			if(dice<2)
				ii=new ImageIcon("c0.jpg");
			else if(dice >2 && dice <4)
				ii=new ImageIcon("c1.jpg");
			else if(dice>4 && dice <6)
				ii=new ImageIcon("c2.jpg");
			else if(dice > 6 )
				ii = new ImageIcon("c3.jpg");
			sprite = ii.getImage();

			dice=  r.nextDouble() * 10 ;

			if(dice<5)
				dx=+3;
			else
				dx=-3;
			dice =  r.nextDouble() * 10;
			if(dice >5)
				dy = -3;
			else
				dy = +3;
	}

	public void move(){
		x=x+dx;
		y=y+dy;
	}



}