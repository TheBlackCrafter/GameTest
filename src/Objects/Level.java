package Objects;
import java.awt.Color;
import java.io.File;
import legendsDarkApi.LFile;
import mainPack.Main;

public class Level extends Main{

	public static final int AIR = 10;
	public static final int BLOCK = 11;
	public static final int BLOCK_DOUBLE_1 = 12;
	public static final int BLOCK_DOUBLE_2 = 13;
	
	private LFile file = new LFile();
	private String onthouder;
	private int type[][] = new int[13][41];
	
	public void open(File f){
		file.setFile(f);
		file.open(false);
		i1 = 1;
		while( i1 <=40){
			i2 = 1;
			i3 = 1;
			while(i2<=23){
				onthouder = file.string[i1];
					type[i3][i1] = Integer.parseInt(onthouder.substring(i2-1,i2+1));
					System.out.println(type[i3][i1]);
				i3++;
				i2= i2+2;
			}
			i1++;
			System.out.println("==");
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
				block[i][j].setName(10);
				i2 = i2-11;
			}
			i1 = i1+61;
		}
		
		for(int i = 1; i <=12 ; i++){
			for(int j = 1; j <= 40; j++){
				if(type[i][j] ==  AIR){
					block[i][j].setUpdate(false);
					block[i][j].setName(AIR);
				}
				if(type[i][j] == BLOCK){
					block[i][j].setName(BLOCK);
				}
				if(type[i][j] == BLOCK_DOUBLE_1){
					block[i][j].setColor(Color.red);
					block[i][j].setName(BLOCK_DOUBLE_1);
				}
				if(type[i][j] == BLOCK_DOUBLE_2){
					block[i][j].setColor(Color.green);
					block[i][j].setName(BLOCK_DOUBLE_2);
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
					onthouder = onthouder + Integer.toString(block[i2][i1].getName());
					System.out.println(onthouder);
				i2++;
			}
			file.append(onthouder);
			i1++;
		}
	}
	
	
	
}
