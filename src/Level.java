import java.awt.Color;
import java.io.File;
import Objects.Object;
import legendsDarkApi.LFile;


public class Level extends Main{

	private LFile file;
	private boolean isShown[][] = new boolean[21][21];
	
	public Level(File f){
		file = new LFile(f);
		file.open(false);
		System.out.println("start");
		
		// i1    welke kolom je bent
		i2 = 1;
		while(i2<= 12){
			//i2    	welke rij je bent 
			i1 = 1;
			while(i1 <= 20){
				if(file.string[i2].substring((i2-1), i2).equals("0")){
					isShown[i2][i1] = false;
				}
				else{
					isShown[i2][i1] = true;
				}
				
				i1++;
			}

			i2++;
		}
	}
	
	public void Draw(){
		i1 = 70;
		for(int i = 1; i <=12 ; i++){
			i2 = 580;
			for(int j = 1; j <= 20; j++){
				block[i][j] = new Object();
				block[i][j].setColor(Color.GRAY);
				block[i][j].setShape(block[i][j].shape_CUBE);
				block[i][j].setWidht(30);
				block[i][j].setHeight(5);
				block[i][j].setWallAction(block[i][j].wallAction_BOUNCE);
				block[i][j].X(i1);
				block[i][j].Y(i2);
				block[i][j].setUpdate(true);
				block[i][j].update();
				i2 = i2-11;
			}
			i1 = i1+61;
		}
		
		for(int i = 1; i <=12 ; i++){
			for(int j = 1; j <= 20; j++){
				if(!isShown[i][j]){
					block[i][j].setUpdate(false);
				}
			}
		}
	}
	
	
	
	
	
}
