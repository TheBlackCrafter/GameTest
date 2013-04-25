import java.awt.Color;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
 
public class Main {
 
	public static Drawer draw = new Drawer();
	static Object plank = new Object();
	static Object fire = new Object();
	static Object ball = new Object();
	static long lastFrame, lastFPS;
	static boolean isLaunched = false;
	static int deltaX;
 
	public static void init(){
		plank.setColor(Color.cyan);
		plank.setShape(plank.shape_CUBE);
		plank.X(400);
		plank.Y(50);
		plank.setWidht(30);
		plank.setHeight(4);
		plank.setWallAction(plank.wallAction_BOUNCE);
		
		fire.setColor(Color.RED);
		fire.setShape(fire.shape_CUBE);
		fire.X(0);
		fire.Y(0);
		fire.setWidht(800);
		fire.setHeight(10);
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
		
		isLaunched = false;
	}
	
	public static void update(int delta) {
		fire.update();
		ball.update();
		plank.update();
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) 		plank.X(plank.getX() - delta*0.8f);
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) 	plank.X(plank.getX() + delta*0.8f);
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) 		plank.X(plank.getX() - delta*0.1f);
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) 		plank.X(plank.getX() + delta*0.1f);
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) 	init();
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			if(!isLaunched){
				isLaunched = true;
				ball.setYvelocity(-6);
			}
		}
		
		if(ball.getY() <= 10){
			Display.destroy();
		}
		
		if(ball.getY() <= 65 && ball.getY() >55){
			deltaX = (int)plank.getX() - (int)ball.getX();
			if(deltaX<=32 && deltaX >=-32){
				ball.setYvelocity(ball.getYvelocity()*-1);
				
				if(deltaX>7 || deltaX<7){
					deltaX = deltaX/2;
				}
				if(deltaX>7 || deltaX<7){
					deltaX = deltaX/2;
				}
				if(deltaX>9 || deltaX<9){
					deltaX = deltaX/2;
				}
				
				ball.setXvelocity(ball.getXvelocity()- deltaX);
			}

		}
			

		
		
	}

	public static void main(String[] args){
		init();
		
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