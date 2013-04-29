package Threading;

import mainPack.Editor;

public class Thread_Editor implements Runnable{

	public static Editor editor = new Editor();
	
	@Override
	public void run() {
		editor.openEditor();
		
	}
	
	public void Start(){
		if(!editor.isOpen){
			editor.isOpen = true;
			new Thread(this);
			this.run();
		}
		
	}

}
