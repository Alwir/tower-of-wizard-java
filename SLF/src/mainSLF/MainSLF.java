package mainSLF;

import gui.LoadImageSfl;
import gui.Messages;

import javax.swing.JComponent;
import javax.swing.JFrame;

public final class MainSLF {
	public static JComponent focusComponent;
	public static StartFrame frame;
	
	public static void main(String[] args) {
	      StartFrame frameMain = new StartFrame();

//	      frameMain.setType(Type.NORMAL);
	      frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      frame=frameMain;
//	      frameMain.setUndecorated(true);
	      frame.setIconImage(LoadImageSfl.LoadImage(Messages.getString("iconImage")));
	      frameMain.setVisible(true);
	      focusComponent.requestFocusInWindow();
	 }
}
