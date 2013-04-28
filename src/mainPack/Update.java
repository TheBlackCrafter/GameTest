package mainPack;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Update extends Main{
	
	public static void updateFrame(int delta){
	//
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
		
		
	//
		if(ball.isCollision(plank.getCollisionBox())){
			
			deltaX = (int)plank.getX() - (int)ball.getX();
			ball.setYvelocity(ball.getYvelocity()*-1);

				deltaX = deltaX/8;
			
			ball.setXvelocity(ball.getXvelocity() - deltaX);
			ball.Y(ball.getY() + 10);	
			
		}
	//
		
		fire.update();
		ball.update();
		plank.update();
		mouse.update();
		for(int i = 1; i <=12 ; i++){
			for(int j = 1; j <= 40; j++){
				if(ball.isCollision(block[i][j].getCollisionBox())&& block[i][j].isUpdate()){
					block[i][j].setUpdate(false);
					if(!isTurned){
						isTurned = true;
						ball.setXvelocity(ball.getXvelocity()*-1);
						ball.setYvelocity(ball.getYvelocity() *-1);
						ball.X(ball.getX() + ball.getXvelocity()*2);
						ball.Y(ball.getY() + ball.getXvelocity()*2);
					}

				}
				if(block[i][j].getVisable()){
					block[i][j].update();
				}
	//
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
	//
	}
}
