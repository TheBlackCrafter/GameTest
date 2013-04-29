package Threading;

import mainPack.Main;

public class Thread_Main implements Runnable{

	public static Main main = new Main();
	
	@Override
	public void run() {
		Main.startGame();
		
	}
	
	public void Start(){
		new Thread(this);
		this.run();
	}

}
