
import acm.graphics.*;
import acm.program.*;
import acm.graphics.GImage;
import acm.util.*;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;


	public class Plane extends GraphicsProgram {
		/* Size of background*/
		private static final int WIDTH =1000;
		private static final int HEIGHT =800;
		/* Size and speed of Plane */ 
		private static final int PLANE_WIDTH =240;
		private static final int PLANE_HEIGHT =240;
		private static final int PLANE_SPEED =25;
		/*Speed of bullet */
		private static final int BULLET_SPEED =40;
		/*Speed of test-tube*/
		private static final int PROBIRKA_SPEED_1= 13;
		private static final int PROBIRKA_SPEED_2=10;
		/*Numbers of bullets*/
        int attempt =10;
        /*Numbers of targets*/
        int target =0;
		public void run(){ 
	    this.setSize(WIDTH,HEIGHT);
	    fon();
		addMouseListeners();
		planeInTheScreen();
		bakterias();
		probirkas();
		while(!gameOver()){
		score();
	    movePlane();
	    moveProbirka1();
	    moveProbirka2();
	    moveBullet();
	    collision();
	    collisionForPlane();
	    land();
		}
		end();
		}
		/* * method adds a background to the screen*/
		private void fon(){
			GImage background = new GImage("plane.jpeg");
		    background.scale(2);
			add(background);
		}
		/*method adds a plane on the screen*/
		private void planeInTheScreen(){
			plane = new GImage("planeToRight.png",0,50);
		    plane.scale(0.2);
		    add(plane);
		}
	   /* when you click on the mouse, a rocket is released from the plane*/
		public void mouseClicked(MouseEvent e) { 
			if (bullet == null) { 
				bullet = new GImage("bullet.png"); 
				bullet.scale(0.0203);
				add(bullet,plane.getX()+PLANE_WIDTH/2-5.5, plane.getY()+PLANE_HEIGHT/2-15);
			    attempt--;
			} 
			if(attempt<-1){
				remove(bullet);
				bullet =null;
				}
		} 
		/* method adds virus molecules on the screen*/
        private void bakterias(){
        	bakteria1 = new GImage("bacteria.png",rgen.nextInt(1,330),rgen.nextInt(400,644));
            bakteria2 = new GImage("bacteria.png",rgen.nextInt(340,650),rgen.nextInt(400,644));
            bakteria3 = new GImage("bacteria.png",rgen.nextInt(660,836),rgen.nextInt(400,644));
            bakteria1.scale(0.13);
            bakteria2.scale(0.14);
            bakteria3.scale(0.12);
            add(bakteria1);
            add(bakteria2);
            add(bakteria3);
        }
        /*method add ampuls on the screen*/
        private void probirkas(){
        	probirka1 = new GImage("probirka.png",rgen.nextInt(1,WIDTH),650);
        	probirka2 = new GImage("probirka.png",rgen.nextInt(1,WIDTH),500);
            probirka1.scale(0.05);
            probirka2.scale(0.05);
            add(probirka1);
            add(probirka2);
        }
        /* method is responsible for the movement of the ampul1*/
        private void moveProbirka1(){
        	if(probirkaToLeft1){
            pause(10);
			probirka1.move(-PROBIRKA_SPEED_1,0);
				if(probirka1.getX()<=0){
				probirkaToLeft1=false;
				}
			}else{
				pause(10);
			    probirka1.move(PROBIRKA_SPEED_1, 0);
			    if(probirka1.getX()+120>=WIDTH){
			    pause(10);
			    probirkaToLeft1 =true;
			    }
			}
        }
        /* method is responsible for the movement of the ampul2*/
        private void moveProbirka2(){
        	if(probirkaToLeft2){
        		pause(10);
     			probirka2.move(-PROBIRKA_SPEED_2, 0);
     		if(probirka2.getX()<=0){
				probirkaToLeft2=false;
				}
        	}else{
        		pause(10);
			    probirka2.move(PROBIRKA_SPEED_2, 0);
			    if(probirka2.getX()+120>=WIDTH){
			    pause(10);
			    probirkaToLeft2 =true;
			    }
        	}
        }
        /*method , which moves a bullet*/
		private void moveBullet(){
			SoundClip pulya = new SoundClip("bullet.wav");
			if (bullet != null) {
				pulya.setVolume(0.1);
				pulya.play();
				pulya.loop();
				bullet.move(0, BULLET_SPEED);
				pause(10);
			if (bullet.getY() >= 769) { 
				remove(bullet); 
				bullet = null; 
				} 
				}
			}
		/*method checks whether the missile shot down objects, if shot down - blows them*/
		private void collision(){
			SoundClip boomSound = new SoundClip("boom.wav");
			if (bullet != null) { 
				GObject obj = getElementAt(bullet.getX()+40, bullet.getY()+41); 
				if (obj.equals(bakteria1)) {
					boomSound.setVolume(1);
					boomSound.play();
					fire = new GImage("fire.png",bakteria1.getX(),bakteria1.getY());
		        	fire.scale(0.15);
		        	add(fire);
		        	pause(330);
		        	remove(fire);
					remove(bakteria1); 
					remove(bullet); 
					bakteria1 = null; 
					bullet = null; 
					target++;
				} 
				if (obj.equals(bakteria2)) {
					boomSound.setVolume(1);
					boomSound.play();
					fire = new GImage("fire.png",bakteria2.getX(),bakteria2.getY());
		        	fire.scale(0.15);
		        	add(fire);
		        	pause(330);
		        	remove(fire);
					remove(bakteria2); 
					remove(bullet); 
					bakteria2 = null; 
					bullet = null; 
					target++;
				} 
				if (obj.equals(bakteria3)) {
					boomSound.setVolume(1);
					boomSound.play();
					fire = new GImage("fire.png",bakteria3.getX(),bakteria3.getY());
		        	fire.scale(0.15);
		        	add(fire);
		        	pause(330);
					remove(fire); 
					remove(bakteria3); 
					remove(bullet); 
					bakteria3 = null; 
					bullet = null; 
					target++;
				} 
				if (obj.equals(probirka1)) {
					boomSound.setVolume(1);
					boomSound.play();
					fire = new GImage("fire.png",probirka1.getX(),probirka1.getY());
		        	fire.scale(0.15);
		        	add(fire);
		        	pause(330);
					remove(fire); 
					remove(probirka1); 
					remove(bullet);
					bullet = null; 
					target++;
				} 
				if (obj.equals(probirka2)) {
					boomSound.setVolume(1);
					boomSound.play();
					fire = new GImage("fire.png",probirka2.getX(),probirka2.getY());
		        	fire.scale(0.15);
		        	add(fire);
		        	pause(330);
					remove(fire); 
					remove(probirka2); 
					remove(bullet); 
					bullet = null; 
					target++;
				} 
			}
		}
		/* method checks collision for the plane*/
		private void collisionForPlane(){
			SoundClip boomSound = new SoundClip("boom.wav");
			if (plane != null) { 
				GObject obj = getElementAt(plane.getX()+PLANE_WIDTH/2,plane.getY()+PLANE_HEIGHT/2); 
				if(bakteria1 !=null){
				if (obj.equals(bakteria1)) {
					boomSound.setVolume(1);
					boomSound.play();
					fire = new GImage("fire.png",bakteria1.getX(),bakteria1.getY());
		        	fire.scale(0.15);
		        	add(fire);
		        	pause(330);
					remove(fire); 
					remove(bakteria1); 
					remove(plane); 
					plane = null; 
				}
			}
				if(bakteria2 !=null){
					if (obj.equals(bakteria2)) {
					boomSound.setVolume(1);
					boomSound.play();
					fire = new GImage("fire.png",bakteria2.getX(),bakteria2.getY());
			        fire.scale(0.15);
			        add(fire);
			        pause(330);
					remove(fire); 
					remove(bakteria2); 
					remove(plane); 
					plane = null; 
					}
				}
				if(bakteria3 !=null){
					if (obj.equals(bakteria3)) {
					boomSound.setVolume(1);
					boomSound.play();
					fire = new GImage("fire.png",bakteria3.getX(),bakteria3.getY());
			        fire.scale(0.15);
			        add(fire);
			        pause(330);
					remove(fire);
					remove(bakteria3); 
					remove(plane); 
					plane = null; 
					}
				}
				if(probirka1 !=null){
					if (obj.equals(probirka1)) {
				    boomSound.setVolume(1);
					boomSound.play();
					fire = new GImage("fire.png",probirka1.getX(),probirka1.getY());
			        fire.scale(0.15);
			        add(fire);
			        pause(330);
					remove(fire); 
					remove(probirka1); 
					remove(plane); 
					plane = null; 
					}
				}
				if(probirka2 !=null){
					if (obj.equals(probirka2)) {
				    boomSound.setVolume(1);
					boomSound.play();
					fire = new GImage("fire.png",probirka2.getX(),probirka2.getY());
			        fire.scale(0.15);
			        add(fire);
			        pause(330);
					remove(probirka2); 
					remove(plane); 
					plane = null; 
					}
				}
			}
		}
		/*methods shows the number of bullets, which are stayed*/
		private void score(){
			 GRect sky = new GRect(0,0,400,75);
			 sky.setFilled(true);
			 sky.setColor(Color.BLACK);
			 add(sky);
			 
			 GLabel label = new GLabel("Залишилось пуль:  "+ attempt, 40, 45);
			 label.setFont("timesNewRoman-37");
			 label.setColor(Color.WHITE);
			 add(label);
		}
		private void land(){
			SoundClip boomSound = new SoundClip("boom.wav");
			if(plane!= null){
			if(plane.getY()+PLANE_HEIGHT-100>=HEIGHT){
			  boomSound.setVolume(1);
			  boomSound.play();
			  fire = new GImage("fire.png",plane.getX(),plane.getY());
	          fire.scale(0.15);
	          add(fire);
	          pause(330);
			  remove(plane);
			  plane = null;
				}
			}
		}
		/*method moves the plane*/
		private void movePlane(){
			if(planeToLeft){
				pause(10);
				plane.setImage("planeToLeft.png");
			    plane.scale(0.2);
				plane.move(-PLANE_SPEED,0);
				if(plane.getX()<=0){
					plane.move(0, 50);
					pause(10);
					planeToLeft=false;
				}
			}else{
				pause(10);
				plane.setImage("planeToRight.png");
			    plane.scale(0.2);
			    plane.move(PLANE_SPEED, 0);
			    if(plane.getX()+PLANE_WIDTH>=WIDTH){
			    	plane.move(0, 50);
			    	pause(10);
			    	planeToLeft =true;
			    }
			}
		}
		/*method shows the result for the game*/
		private void end(){
			fon();
			for(int g=0;g<3;g++){
			 GRect lines = new GRect(0,325 + 75*g,WIDTH,30);
			 lines.setFilled(true);
			 lines.setColor(Color.GRAY);
			 add(lines);
				 }
			for(int g=0;g<3;g++){
			 GRect lines1 = new GRect(0,335 + 75*g,WIDTH,10);
			 lines1.setFilled(true);
			 lines1.setColor(Color.WHITE);
			 add(lines1);
			 }
			if((target==5)){
			SoundClip winSound = new SoundClip("win.wav");
			winSound.setVolume(1);
			winSound.play();
			GLabel labelWin = new GLabel("YOU WIN", 170, 465);
			labelWin.setFont("timesNewRoman-150");
			labelWin.setColor(Color.BLACK);
			add(labelWin);
			}
			if((plane==null)||(plane.getY() >= HEIGHT - PLANE_HEIGHT) ||(attempt==-1)){
			SoundClip loseSound = new SoundClip("lose.wav");
			loseSound.setVolume(1);
			loseSound.play();
			GLabel labelLose = new GLabel("YOU LOSE", 130, 465);
			labelLose.setFont("timesNewRoman-150");
			labelLose.setColor(Color.BLACK);
			add(labelLose);	
			}
		}
		/*determines if game is over*/
		private boolean gameOver() { 
		return (plane == null) || (plane.getY() >= HEIGHT - PLANE_HEIGHT)|| (target==5) || (attempt==-1); 
			}
		/* private instance variables */ 
		private GImage plane;
		private boolean planeToLeft; 
		private GImage bullet;
		private GImage bakteria1;
		private GImage bakteria2;
		private GImage bakteria3;
		private GImage probirka1;
		private GImage probirka2;
		private GImage fire;
		private boolean probirkaToLeft1;
		private boolean probirkaToLeft2;
		private RandomGenerator rgen = RandomGenerator.getInstance();
	}
	

