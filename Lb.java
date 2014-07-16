import java.awt.Image;
import javax.swing.ImageIcon;

public class Lb extends Ente{
	private boolean fullPower;
	private int loadStatus;

	public Lb(int a, int b){
		x=a;
		y=b;
		ImageIcon ii = (new ImageIcon(this.getClass().getResource("lb.jpg")));
		sprite = ii.getImage();
		fullPower=true;


	}

	public boolean getFull(){
		return fullPower;

	}
	public void setLoadStatus(int a){
		loadStatus=a;
	}
	public int getLoadStatus(){
		return loadStatus;
	}
	public void move(){
		
	}

}