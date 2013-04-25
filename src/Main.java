import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	static Object block[][] = new Object[15][15];
	static long lastFrame, lastFPS;
	static boolean isLaunched = false;
	static int deltaX, i1,i2;
 
	public static void init(){
		i1 = 70;
		for(int i = 1; i <=12 ; i++){
			i2 = 580;
			for(int j = 1; j <= 12; j++){
				block[i][j] = new Object();
				block[i][j].setColor(new Color(i1));
				block[i][j].setShape(block[i][j].shape_CUBE);
				block[i][j].setWidht(30);
				block[i][j].setHeight(5);
				block[i][j].setWallAction(block[i][j].wallAction_BOUNCE);
				block[i][j].X(i1);
				block[i][j].Y(i2);
				System.out.println(i1 + ", "+ i2);
				i2 = i2-11;
			}
			i1 = i1+61;
		}
		
		
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
				ball.setYvelocity(-6);
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ADD)) 		plank.setWidht(plank.getWidht() + 10);
		if (Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT)) 	plank.setWidht(plank.getWidht() - 10);	
		if(ball.getY() <= 10){
			init();
		}
		
		if(ball.isCollision(plank.getCollisionBox())){
			
			deltaX = (int)plank.getX() - (int)ball.getX();
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
			
			ball.setXvelocity(ball.getXvelocity() - deltaX);
			ball.Y(ball.getY() + 10);	
			
		}
		
		
		fire.update();
		ball.update();
		plank.update();
		
		for(int i = 1; i <=12 ; i++){
			for(int j = 1; j <= 12; j++){
				if(ball.isCollision(block[i][j].getCollisionBox())){
					block[i][j].setVisable(false);
				}
				if(block[i][j].getVisable()){
					block[i][j].update();
				}
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