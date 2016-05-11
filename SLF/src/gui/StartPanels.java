package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import mainSLF.MainSLF;
import mainSLF.StartFrame;
import sound.MakeSound;
import core.Settings;

public abstract class StartPanels extends AllPanels { 
	private static final long serialVersionUID = 1024944991702665403L;
	protected static Image foto;
	protected static MakeSound menuMusic=new MakeSound(Messages.getString("soundMenuMusic"),true);
	
	public StartPanels() {
		LoadImage();

		MyButton exitButton = new MyButton(442,486,new String[] {"MyButton.0","MyButton.1"},Messages.getString("buttonTextExit"));
		exitButton.addActionListener(new StartPanelsAction("exitButton"));
		add(exitButton);
			
		MyButton settingsButton = new MyButton(310,583,new String[] {"MyButton.2","MyButton.3"},"");
		settingsButton.addActionListener(new StartPanelsAction("settingsButton"));
		add(settingsButton);
			
		MyButton helpButton = new MyButton(749,583,new String[] {"MyButton.4","MyButton.5"},"");
		helpButton.addActionListener(new StartPanelsAction("helpButton"));
		add(helpButton);
		
		MyButton logoButton = new MyButton(437,589,new String[] {"MyButton.6","MyButton.6"},"");
		logoButton.addActionListener(new StartPanelsAction("logoButton"));
		add(logoButton);

		AddComponent();
		
		LoadFotoEmpty();
	}
	
	protected abstract void LoadImage();
	protected abstract void AddComponent();
	
	protected void AddRosterLabel(int coorX, int coorY) {
		JLabel roster=new JLabel(Messages.getString("startTextRoster"));
		roster.setBounds(coorX,coorY,270,70);
		roster.setFont(MyButton.myFont.deriveFont(36f));
		roster.setForeground(new Color(57, 59, 167));
		roster.setVerticalAlignment(JLabel.CENTER);
		roster.setHorizontalAlignment(JLabel.CENTER);
		add(roster);
	}
	
	protected void LoadImageFromText(String NameString) {
	        image = LoadImageSfl.LoadImage(Messages.getString(NameString)); //$NON-NLS-1$
//	        image = image.getScaledInstance(mainSLF.StartFrame.DEFAULT_WIDTH, mainSLF.StartFrame.DEFAULT_HEIGHT, Image.SCALE_SMOOTH);
	}
	
	protected void LoadFoto(String fotoInd) {
		if (fotoInd!="") {
        	String fotoName=Messages.getString("CharImage")+fotoInd+Messages.getString("EndImage");
			foto=LoadImageSfl.LoadImage(fotoName);
        }		
	}
	
	protected void LoadFotoEmpty() {
		String fotoInd=StartFrame.myGame.GetFoto();
		LoadFoto(fotoInd);
	}
	   
	private class StartPanelsAction implements ActionListener {
		private String name;
			
		public StartPanelsAction(String name) {
			this.name=name;
		}
		
		public void actionPerformed(ActionEvent event) {
			if(name=="exitButton") {
				Settings.SaveSettings();
				StartFrame.myGame.SaveGame();
				MainSLF.frame.dispose();
				System.exit(0);
			}
			else if (name=="settingsButton") {
				mainSLF.StartFrame.PanelChanger(getName(), "StartMenuSettingsPanel");	
			}
			else if (name=="helpButton") {
				mainSLF.StartFrame.PanelChanger(getName(), "StartMenuHelpPanel");	
			}
			else if (name=="logoButton") {
				mainSLF.StartFrame.PanelChanger(getName(), "StartMenuAboutPanel");	
			}
		}
	}
}
