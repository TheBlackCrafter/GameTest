import java.awt.Color;

import org.lwjgl.opengl.GL11;

public class Drawer {

	public void cube(float x, float y, Color c, int w, int h) {
		GL11.glColor3f(c.getRed(), c.getGreen(), c.getBlue());
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glTranslatef(-x, -y, 0);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x - w, y - h);
			GL11.glVertex2f(x + w, y - h);
			GL11.glVertex2f(x + w, y + h);
			GL11.glVertex2f(x - w, y + h);
		GL11.glEnd();
		GL11.glPopMatrix();
		
	}

	public void circle(float x, float y, Color c, int w) {
        double increment = 2*Math.PI/w;
        GL11.glColor3f(c.getRed(), c.getGreen(), c.getBlue());
        for(double angle = 0; angle < 2*Math.PI; angle+=increment){
	  		GL11.glBegin(GL11.GL_POLYGON);
	  		GL11.glVertex2d(x, y);
	  		GL11.glVertex2d(x + Math.cos(angle)* w, y + Math.sin(angle)*w);
	  		GL11.glVertex2d(x + Math.cos(angle + increment)*w, y + Math.sin(angle + increment)*w);
	  		GL11.glEnd();
        }
		
	}
	
	
}
