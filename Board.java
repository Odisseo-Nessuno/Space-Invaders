import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;


public class Board extends JPanel implements ActionListener{
	private Nave n;
	private Timer timer;
	private ArrayList<Shot> spari = new ArrayList<Shot>();
	private ArrayList<Alien> alieni = new ArrayList<Alien>();
	private ArrayList<Debris> deb = new ArrayList<Debris>();
	private ArrayList<Ashot> aSpari = new ArrayList<Ashot>();
	private Lb ricarica;
	private int sec,width,height,life,round;


	public Board(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int) screenSize.getWidth();
		height =  (int) screenSize.getHeight();
		this.addKeyListener(new TAdapter());
		setFocusable(true);
		requestFocus();
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		round=3;
		timer = new Timer(20,this);
		n= new Nave(width/2, height-100);
		timer.start();
		sec = 0;
		life=5;
		summonAlien();
	}

	public void summonAlien(){
		for(int a=32; a<height/3; a=a+32){
			for(int i=150; i<width-150; i=i+64){
				alieni.add(new Alien(i,a));
			}
		}
	}
	public void checkAlien(){
		boolean Cr=false;

		for(int i = 0; i<alieni.size(); i++){
			if(alieni.get(i).getX()<10 || alieni.get(i).getX()>width-100){
				Cr=true;
				System.out.println("CR");
				alieni.get(i).changeDir();
				alieni.get(i).move();
				break;
			}
			if(alieni.get(i).Shots() <=0.5){
				aSpari.add(new Ashot(alieni.get(i).getX(),alieni.get(i).getY()));
			}
		}	
		if(Cr){
			for(Alien x: alieni	){
				x.changeRow();
			}
		Cr=false;
		}

	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(,n.getX(),n.getY(),this);
		g2d.drawImage(n.getSprite(),n.getX(),n.getY(),this);
		for(Alien x: alieni){
			g2d.drawImage(x.getSprite(), x.getX(), x.getY(),null);
		}
		for (int i = 0; i<spari.size(); i++){
			Shot used = spari.get(i);
			g2d.drawImage(used.getSprite(), used.getX(), used.getY(),null);
		}
		for (int i = 0; i<deb.size(); i++){
			g2d.drawImage(deb.get(i).getSprite(), deb.get(i).getX(), deb.get(i).getY(),null);
		}
		for(Ashot x: aSpari){
			g2d.drawImage(x.getSprite(),x.getX(),x.getY(),null);
		}
		g2d.drawString(("Hull Integrity: <"+life+">"),50,height-20);
		g2d.drawString(("Shots Available"+round+">"),300,height-20);
		int ind = checkVictory();
		if(ind==-1)
			g2d.drawString(("Gli alieni vincono..."),width/2,height/2);
		else if(ind==1){
			g2d.drawString(("La Terra vive ancora!"),width/2,height/2);
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	public void checkSparo(Shot s){
		if(s.getY()<0){
			s=null;
			round ++;
		}

	}
	public void checkAlienShot(){
		for(int i =0; i<aSpari.size(); i++){
			if(aSpari.get(i).getY()>600);
				aSpari.remove(i);
		}
	}
	public void checkCollision(){
		int a=999;
		int b=999;
		for(Alien x: alieni){ 
			for(Shot y: spari){
				if(checkCoords(y.getX(),y.getY(),x.getX(),x.getY())){
					a=alieni.indexOf(x);
					b=spari.indexOf(y);
					round++;
					for(int i=0; i<8;i++)
					deb.add(new Debris(x.getX()+16+i*4,x.getY()+16-i*4));
				}
			}
		}
		for(int i=0; i<aSpari.size(); i++){
			if(checkCoords(n.getX(),n.getY(),aSpari.get(i).getX(),aSpari.get(i).getY())){
				aSpari.remove(i);
				life --;

			}
		
		}
		if(a != 999){ //cambiare foreach in for per evitare questo obbrobrio;
		alieni.remove(a);
		spari.remove(b);
	}

	}

	public void checkDebris(){
		for(Debris x:deb)
			if((x.getX()<0||x.getX()>width) || (x.getY()<0|| x.getY()>height))
				deb.remove(x);
	}
	public int checkVictory(){
		if(life==0)
			return -1;
		else if(alieni.size()==0)
			return 1;
		return 0;
		
	}
	public boolean checkCoords(int x, int y, int a, int b){
		if(((x<a+32 && x>=a)||(x+32>a && x<=a ))&& (y<b+32 && y >b))
			return true;
		return false;
	}

	public void actionPerformed(ActionEvent e){
	//	System.out.println("AP");

		n.move();
		checkCollision();
		this.checkAlien();
		//checkAlienShot();
		for(Alien x: alieni	){
			x.move();
		}
		for(Debris x:deb){
			x.move();
		}
		for(Ashot x: aSpari){
			x.move();

		}
		if(n.shoots()){
			if(round>0){
				if (sec == 0){
				spari.add(new Shot(n.getX()+8,n.getY()));
				round --;
				n.shootFalse();
				}
				else if(sec ==10000){
					sec=0;
				}
				else{
					sec ++;
				}
			}

		}
		for (int i = 0; i<spari.size(); i++){
			spari.get(i).move();
				if (spari.get(i).getY() < 0){		//exchange with the proper method checkSpari
					spari.remove(i);
					round ++;
				}

		}
		repaint();
	}

	private class TAdapter extends KeyAdapter {
		public void keyReleased(KeyEvent e){
			n.keyReleased(e);
		}
		public void keyPressed(KeyEvent e){
		//	System.out.println("KP");
			n.keyPressed(e);	
		}
	}
	
}


















