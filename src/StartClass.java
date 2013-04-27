import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import legendsDarkApi.LButton;
import legendsDarkApi.LFrame;
import legendsDarkApi.LPicture;

public class StartClass extends Main{
	public static LFrame startScreen;
	public static LButton start = new LButton();
	public static LButton instellingen = new LButton();
	public static LPicture pic = new LPicture("rec/BG.png");
	
	public static void main(String[] args){
		startScreen = new LFrame();
		startScreen.setTitel("My Game");
		startScreen.setSize(500, 300);
		
		pic.setPlace(0, 0);
		pic.setGridSize(3, 1);
		pic.setSize(500, 250);
		
		start.setPlace(0, 1);
		start.setSize(249, 50);
		start.setText("Start!");
		
		start.get().addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent arg0) {
				startScreen.get().dispose();
			startGame();
		}});
		
		instellingen.setPlace(2, 1);
		instellingen.setSize(249, 50);
		instellingen.setText("Instellingen");
		
		startScreen.add(pic);
		startScreen.add(instellingen);
		startScreen.add(start);
		startScreen.get().pack();
		startScreen.get().setResizable(false);
		
	}
	
	
	
	
	
	
	
}
