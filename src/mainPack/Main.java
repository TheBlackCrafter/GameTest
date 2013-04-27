package mainPack;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import Objects.Drawer;
import Objects.Level;
import Objects.Object;
 
public class Main{
 
	public static Drawer draw = new Drawer();
	public static Object plank = new Object();
	public static Object fire = new Object();
	public static Object ball = new Object();
	public static Object mouse = new Object();
	public static Object block[][] = new Object[13][41];
	public static long lastFrame, lastFPS;
	public static boolean isLaunched = false;
	public static boolean isTurned = false;
	public static int deltaX, i1,i2,i3;
	public static String level;
	public static Level l1,l2 = new Level();
 
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
		
		 l1 = new Level();
		 l1.open(new File("rec/" + level + ".lvl"));
		 l1.Draw();
		isLaunched = false;
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
			Update.updateFrame(deltaTime);
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