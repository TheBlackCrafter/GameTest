package Objects;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.lwjgl.opengl.Display;


public class Object {
	
	public final int shape_CIRCLE = 1;
	public final int shape_CUBE = 2;
	public final int wallAction_BOUNCE = 11;
	public final int wallAction_STOP_V = 12;
	public final int wallAction_NOTHING = 13;
	
	private float x = 0f,y = 0f,xv = 0.0f,yv = 0.0f;
	private int w = 80,h = 80,shape = shape_CIRCLE, wallAction = wallAction_NOTHING, name;
	private Color c = Color.cyan;
	private Boolean visable = true;
	private ActionListener action;
	private Drawer draw = new Drawer();
	private CollisionBox box = new CollisionBox();
	private boolean update = true;

	public Object(){
		action = new ActionListener() {@Override
			public void actionPerformed(ActionEvent e) {
			
		}};
	}
	
	
	public void update(){
	if(update){
		box.setTop(y+h);
		box.setBottom(y-h);
		box.setLeft(x-w);
		box.setRight(x+w);
		box.setX(this.x);
		box.setY(this.y+ (h/2));
		
		
		x = x + xv;
		y = y + yv;
		
		switch(wallAction){
			case wallAction_BOUNCE:
				if (x < w){ x = w; xv = xv *-1;}
				if (x > Display.getWidth()-w){ x = Display.getWidth()-w; xv = xv * -1;}
				if (y < h){ y = h; yv = yv *-1;}
				if (y > Display.getHeight()-h){ y = Display.getHeight()-h; yv = yv * -1;}
				if (w < 1){ w = 1;}
				if (h < 1){ h = 1;}
				//System.out.println("x: " + x + "  Y: " + y + "  w: " + w + "  h: " + h);
				
			break;
			case wallAction_STOP_V:
				if (x < w){ x = w; xv = 0;}
				if (x > Display.getWidth()-h){ x = Display.getWidth()-h; xv = 0;}
				if (y < h){ y = h; yv = 0;}
				if (y > Display.getHeight()-h){ y = Display.getHeight()-h; yv = 0;}
				if (w < 1){ w = 1;}
				if (h < 1){ h = 1;}
			break;
			case wallAction_NOTHING:
			break;
		}
		
		action.actionPerformed(null);
		
		if(visable){
			if(shape == shape_CIRCLE){
				draw.circle(x, y, c, w);
			}
			if(shape == shape_CUBE){
				draw.cube(x, y, c, w, h);
			}
		}
	}
	}
	public CollisionBox getCollisionBox(){
		return box;
	}
	
	public boolean isCollision(CollisionBox b){
		return box.tuches(b);
	}
	
	public void setColor(Color C){
		c = C;
	}
	
	public Color getColor(){
		return c;
	}
	
	public float getX() {
		return x;
	}

	public void X(float f) {
		this.x = f;
	}

	public float getY() {
		return y;
	}

	public void Y(float f) {
		this.y = f;
	}

	public int getWidht() {
		return w;
	}

	public void setWidht(int w) {
		this.w = w;
	}

	public int getHeight() {
		return h;
	}

	public void setHeight(int h) {
		this.h = h;
	}

	public int getShape() {
		return shape;
	}

	public Boolean getVisable() {
		return visable;
	}

	public void setVisable(Boolean visable) {
		this.visable = visable;
	}

	public void setShape(int shape) {
		this.shape = shape;
	}

	public float getXvelocity() {
		return xv;
	}

	public void setXvelocity(float xv) {
		this.xv = xv;
	}

	public float getYvelocity() {
		return yv;
	}

	public void setYvelocity(float yv) {
		this.yv = yv;
	}

	public int getWallAction() {
		return wallAction;
	}

	public void setWallAction(int wallAction) {
		this.wallAction = wallAction;
	}

	/**
	 * @return the action
	 */
	public ActionListener getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(ActionListener action) {
		this.action = action;
	}


	/**
	 * @return the update
	 */
	public boolean isUpdate() {
		return update;
	}


	/**
	 * @param update the update to set
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}


	/**
	 * @return the name
	 * name is not used in the actual engine!!
	 */
	public int getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(int name) {
		this.name = name;
	}
}
