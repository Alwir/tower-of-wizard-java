package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;


public class BackgrPanel extends JPanel {

	private static final long serialVersionUID = 7049451118033631696L;
	private static Image backg;
	private static int smeschenie;
	
	public BackgrPanel() {
		backg = LoadImageSfl.LoadImage(Messages.getString("BackGround"));      
		smeschenie=0;
		
		BackgrThread thread=new BackgrThread(this);
		thread.start();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(backg, 0, 0, mainSLF.StartFrame.DEFAULT_WIDTH, mainSLF.StartFrame.DEFAULT_HEIGHT, 
						    0-smeschenie, 0, mainSLF.StartFrame.DEFAULT_WIDTH-smeschenie, mainSLF.StartFrame.DEFAULT_HEIGHT, this);
		if (mainSLF.StartFrame.DEFAULT_WIDTH-smeschenie>backg.getWidth(this)) {
			smeschenie=-730;
		}
		super.paintComponents(g);
	}
   
	public void incrsm() {
		smeschenie-=1;
	}
	

}
