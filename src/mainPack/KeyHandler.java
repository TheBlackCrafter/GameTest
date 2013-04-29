package mainPack;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class KeyHandler extends Main{
	static void KeyControl(){
		
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			thread_Editor.Start();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_P))			l2.Save();	
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
		if(Keyboard.isKeyDown(Keyboard.KEY_1))			mouse.setWidht(mouse.getWidht() +5); mouse.setHeight(mouse.getHeight() +5);
		if(Keyboard.isKeyDown(Keyboard.KEY_2))			mouse.setWidht(mouse.getWidht() -5); mouse.setHeight(mouse.getHeight() -5);
		
		
		
	}
	
	
}
