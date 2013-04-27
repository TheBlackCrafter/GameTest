package Objects;
import java.awt.Color;
import java.io.File;
import legendsDarkApi.LFile;
import mainPack.Main;

public class Level extends Main{

	private LFile file = new LFile();
	private String onthouder;
	private boolean isShown[][] = new boolean[13][41];
	
	public void open(File f){
		file.setFile(f);
		file.open(false);
		i1 = 1;
			while( i1 <=40){
				i2 = 1;
				while(i2<=12){
					onthouder = file.string[i1];
					if(onthouder.substring(i2-1,i2).equals("0")){
						isShown[i2][i1] = false;
					}else{
						isShown[i2][i1] = true;
					}
					
					i2++;
				}
				i1++;
			}
		
	}
	
	public void Draw(){
		i1 = 70;
		for(int i = 1; i <=12 ; i++){
			i2 = 580;
			for(int j = 1; j <= 40; j++){
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
			for(int j = 1; j <= 40; j++){
				if(!isShown[i][j]){
					block[i][j].setUpdate(false);
				}
			}
		}
	}
	
	public void Save(){
		file.setFile(new File(file.GetSafePath()));
		file.create();
		
		i1 = 1;
		while( i1 <=40){
			onthouder = "";
			i2 = 1;
			while(i2<=12){
				if(block[i2][i1].isUpdate()){
					onthouder = onthouder + "1";
				}else{
					onthouder = onthouder + "0";
				}
				
				i2++;
			}
			file.append(onthouder);
			i1++;
		}
	}
	
	
	
}
