import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import Objects.Drawer;
import Objects.Object;
 
public class Main {
 
	public static Drawer draw = new Drawer();
	static Object plank = new Object();
	static Object fire = new Object();
	static Object ball = new Object();
	static Object mouse = new Object();
	static Object block[][] = new Object[21][21];
	static long lastFrame, lastFPS;
	static boolean isLaunched = false;
	static boolean isTurned = false;
	static int deltaX, i1,i2,i3;
	public static Level l1;
 
	public static void init(){
		mouse.setColor(Color.YELLOW);
		mouse.setShape(mouse.shape_CUBE);
		mouse.setUpdate(false);
		mouse.setWidht(1);
		mouse.setHeight(1);
		mouse.setWallAction(mouse.wallAction_NOTHING);
		
		plank.setColor(Color.cyan);
		plank.setShape(plank.shape_CUBE);
		plank.X(400);
		plank.Y(50);
		plank.setWidht(80);
		plank.setHeight(2);
		plank.setWallAction(plank.wallAction_BOUNCE);
		
		fire.setColor(Color.RED);
		fire.setShape(fire.shape_CUBE);
		fire.X(0);
		fire.Y(0);
		fire.setWidht(800);
		fire.setHeight(20);
		fire.setWallAction(fire.wallAction_NOTHING);
		
		ball.setColor(Color.PINK);
		ball.setShape(ball.shape_CIRCLE);
		ball.X(400);
		ball.Y(70);
		ball.setXvelocity(0);
		ball.setYvelocity(0);
		ball.setWidht(10);
		ball.setHeight(10);
		ball.setWallAction(ball.wallAction_BOUNCE);
		ball.setAction(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {
				if(ball.isCollision(plank.getCollisionBox())){
					ball.Y(plank.getY()+15);
				}
		}});
		
		 l1 = new Level(new File("rec/1.lvl"));
		 l1.Draw();
		isLaunched = false;
	}
	
	public static void update(int delta) {	
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) 		plank.X(plank.getX() - delta*0.8f);
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) 	plank.X(plank.getX() + delta*0.8f);
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) 		plank.X(plank.getX() - delta*0.1f);
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) 		plank.X(plank.getX() + delta*0.1f);
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) 	init();
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			if(!isLaunched){
				isLaunched = true;
				ball.setYvelocity(-7);
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ADD)) 		plank.setWidht(plank.getWidht() + 10);
		if (Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT)) 	plank.setWidht(plank.getWidht() - 10);	
		if(ball.getY() <= 10){
			init();
		}
		if(Mouse.isButtonDown(0) || Mouse.isButtonDown(1)){
			mouse.setUpdate(true);
			mouse.X(Mouse.getX());
			mouse.Y(Mouse.getY());
		}else{mouse.setUpdate(false);}
		
		if(ball.isCollision(plank.getCollisionBox())){
			
			deltaX = (int)plank.getX() - (int)ball.getX();
			ball.setYvelocity(ball.getYvelocity()*-1);

				deltaX = deltaX/8;
			
			ball.setXvelocity(ball.getXvelocity() - deltaX);
			ball.Y(ball.getY() + 10);	
			
		}
		
		
		fire.update();
		ball.update();
		plank.update();
		mouse.update();
		for(int i = 1; i <=12 ; i++){
			for(int j = 1; j <= 20; j++){
				if(ball.isCollision(block[i][j].getCollisionBox())&& block[i][j].isUpdate()){
					block[i][j].setUpdate(false);
					if(!isTurned){
						isTurned = true;
						//ball.setXvelocity(ball.getXvelocity()*-1);
						ball.setYvelocity(ball.getYvelocity() *-1);
						ball.X(ball.getX() + ball.getXvelocity()*2);
						ball.Y(ball.getY() + ball.getXvelocity()*2);
					}

				}
				if(block[i][j].getVisable()){
					block[i][j].update();
				}
				
				//editor thingy
				if(block[i][j].isCollision(mouse.getCollisionBox()) && mouse.isUpdate()){
					if(Mouse.isButtonDown(0)){
						block[i][j].setUpdate(true);
					}else{
						block[i][j].setUpdate(false);
					}
					
				}
			}
				
		}
		isTurned = false;
	}
			
	

	public static void startGame(){
		
		
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		}catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		initGL();
		getDelta();
		lastFPS = getTime();
		init();
 
		while (!Display.isCloseRequested()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			int deltaTime = getDelta();
			update(deltaTime);
			Display.update();
			Display.sync(60);
		}
 
		Display.destroy();
	}
 
	public static int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	    return delta;
	}
 
	public static long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
 
	public static void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
}