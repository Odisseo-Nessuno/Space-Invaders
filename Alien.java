import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.util.Random;
public class Alien extends Ente{
	public static int lor=1;				//SIGNIFICA CHE STANNO ANDANDO A DX
	private static  boolean shots;
	public Alien(int a, int b){
		Random dice = new Random();
		ImageIcon ii=null;
		if(dice.nextDouble()>0.5)
			ii = (new ImageIcon("a0.jpg"));
		else
			ii = (new ImageIcon(this.getClass().getResource("a1.jpg")));

		sprite = ii.getImage();
		x=a;
		y=b;
	}

	public void move(){
		if(lor==1)
			x=x+1;
		else
			x=x-1;
	}

	public void changeDir(){
		lor = lor*-1;
	}

	public void changeRow(){
		y=y+16;
	}
	public boolean isShooting(){
		return shots;
	}
	public double Shots(){
		Random r = new Random();
		return r.nextDouble()*1000;
	}
}