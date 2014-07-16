import java.awt.Image;
public abstract class Ente{
	public Image sprite;
	public int x,y,dx,dy;

	public Ente(){
	}

	public Image getSprite(){
		return sprite;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public abstract void move();

}	