package mainPack;

import legendsDarkApi.LButtonGroup;
import legendsDarkApi.LFrame;
import legendsDarkApi.LRadioButton;

public class Editor extends Main{
	private static LFrame screen;
	private static LRadioButton air = new LRadioButton(), block = new LRadioButton(), block_double_1 = new LRadioButton();
	private static LButtonGroup group = new LButtonGroup();
	public boolean isOpen = false;
	
	public void openEditor(){
		screen = new LFrame();
		screen.setSize(100, 600);
		screen.setTitel("Editor");
		
		air.setPlace(0, 1);
		block.setPlace(0, 2);
		block_double_1.setPlace(0,3);
		
		air.setText("Air");
		block.setText("Normal Block");
		block_double_1.setText("2X Block");

		group.add(block);
		group.add(block_double_1);
		group.add(air);
		
		screen.add(air);
		screen.add(block);
		screen.add(block_double_1);
		
		screen.revalidate();
		screen.get().pack();
		screen.revalidate();
	}
	
}
