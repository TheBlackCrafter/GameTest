package mainPack;

import org.lwjgl.input.Mouse;

import Objects.Level;

public class Update extends Main{
	
	public static void updateFrame(){
		KeyHandler.KeyControl();
		
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
				if(ball.isCollision(block[i][j].getCollisionBox())&& !(block[i][j].getName() == Level.AIR)){
					block[i][j].setName(Level.AIR);
					if(!isTurned){
						isTurned = true;
						ball.setYvelocity(ball.getYvelocity() * -1);
						ball.Y(ball.getY() + ball.getYvelocity()*2);
					}

				}
				if(!(block[i][j].getName() == 10)){
					block[i][j].update();
				}
	//
				if(block[i][j].isCollision(mouse.getCollisionBox()) && mouse.isUpdate()){
					if(Mouse.isButtonDown(0)){
						block[i][j].setName(Level.BLOCK);
					}else{
						block[i][j].setName(Level.AIR);
					}
					
				}
			}
				
		}
		isTurned = false;
	//
	}
}
