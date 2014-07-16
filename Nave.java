
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
public class Nave extends Ente{


	private boolean debug=false;
	private boolean isShooting=false;

	
	public Nave(int a, int b){
		ImageIcon ii = (new ImageIcon("nav.jpg"));
		sprite =ii.getImage();
		x=a;
		y=b;
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		//System.out.println("KP");

		if(key== KeyEvent.VK_LEFT)
			dx = -3;
		if(key== KeyEvent.VK_RIGHT)
			dx= +3;
		if(key == KeyEvent.VK_SPACE){
			isShooting=true;
		}
	}
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
            dx = 0;
        if (key == KeyEvent.VK_RIGHT) 
            dx = 0;

   	}
	public void move(){
		x=x+dx;
		y=y+dy;
	if(debug)
		System.out.println(x+" "+y);
	}

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Image getSprite(){
		return sprite;
	}
	public boolean shoots(){
		return isShooting;
	}
	public void shootFalse(){
		isShooting=false;
	}
}






