package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JLabel;

import mainSLF.StartFrame;
import constance.Dialogs;
import constance.YesNo;

public class StartPanel extends StartPanels {
	
	private static final long serialVersionUID = -2155186070427664757L;
	
	private JLabel nameAdmiral;
	private MyButton continueButton;

	protected void AddComponent() {
		MyButton medalButton = new MyButton(590,339,new String[] {"spMyButton.4","spMyButton.5"},Messages.getString("buttonTextInsigna"));
		medalButton.addActionListener(new StartPanelAction("medalButton"));
		add(medalButton);
		
		continueButton = new MyButton(358,426,new String[] {"spMyButton.2","spMyButton.3"},Messages.getString("buttonTextContinue"));
		continueButton.addActionListener(new StartPanelAction("continueButton"));
		add(continueButton);
		
		MyButton startButton = new MyButton(595,426,new String[] {"spMyButton.2","spMyButton.3"},Messages.getString("buttonTextStartGame"));
		startButton.addActionListener(new StartPanelAction("startButton"));
		add(startButton);
		
		nameAdmiral=new JLabel();
		nameAdmiral.setText(StartFrame.myGame.GetRank()+" "+StartFrame.myGame.GetName());
		nameAdmiral.setBounds(571,240,300,70);
		nameAdmiral.setFont(MyButton.myFont);
		nameAdmiral.setForeground(new Color(43,56,143)); 
		add(nameAdmiral);
		
		MyButton changeButton = new MyButton(541,250,new String[] {"spMyButton.0","spMyButton.1"},"");
		changeButton.addActionListener(new StartPanelAction("changeButton"));
		add(changeButton);
		
		AddRosterLabel(541,200);

		addComponentListener(new StartPanelCompAction());

	}
	
	private class StartPanelCompAction implements ComponentListener {
		@Override
		public void componentShown(ComponentEvent arg0) {
			nameAdmiral.setText(StartFrame.myGame.GetRank()+" "+StartFrame.myGame.GetName());
			LoadFotoEmpty();
			if (!StartFrame.myGame.GetOldGame()) {
				continueButton.setEnabled(false);
			}
			else {
				continueButton.setEnabled(true);
			}
		}
		@Override
		public void componentHidden(ComponentEvent arg0) {}
		@Override
		public void componentMoved(ComponentEvent e) {}
		@Override
		public void componentResized(ComponentEvent e) {}
	}
	
	protected void LoadImage() {
		LoadImageFromText("spPanel.0");
	}
	
	
	private class StartPanelAction implements ActionListener {
		private String name;
		
		public StartPanelAction(String name) {
			this.name=name;
		}
		
		public void actionPerformed(ActionEvent event) {
			if (name=="changeButton") {
				mainSLF.StartFrame.PanelChanger(getName(), "StartMenuNewNamePanel");			
			}
			else if (name=="continueButton") {
				menuMusic.StopMusic();
				mainSLF.StartFrame.PanelChanger(getName(), "GameMapPanel");			
			}
			else if (name=="startButton") {
				YesNo answer=Messages.ShowMessage("messageNewGame",Dialogs.DIALOG);
				if (answer==YesNo.YES) {
					//TODO game initialization
					StartFrame.myGame.Initialization();
					menuMusic.StopMusic();
					mainSLF.StartFrame.PanelChanger(getName(), "GameMapPanel");
				}
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;

		if (foto!=null)
			g2.drawImage(foto, 370,221, this);
		

	}
}
