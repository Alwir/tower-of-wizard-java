package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

public abstract class AllPanels extends JPanel {
	private static final long serialVersionUID = 7554039558922373293L;
	
	protected Image image;
	private String oldPanel;
	
	public final String GetOldPanel() {
		return oldPanel;
	}
	
	public void SetOldPanel(String name) {
		oldPanel=name;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, -35,-155, this);
	}
}
