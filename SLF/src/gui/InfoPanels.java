package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class InfoPanels extends AllPanels { 
	private static final long serialVersionUID = 9182278292031619604L;

	public InfoPanels() {
		image = LoadImageSfl.LoadImage(Messages.getString("hpPanel.0"));
		
		MyButton returnButton = new MyButton(310,584,new String[] {"hpMyButton.0","hpMyButton.1"},"");
		returnButton.addActionListener(new HelpPanelAction("returnButton"));
		add(returnButton);
		
		MyButton logoButton = new MyButton(437,589,new String[] {"MyButton.6","MyButton.6"},"");
		logoButton.addActionListener(new HelpPanelAction("logoButton"));
		add(logoButton);
		
		AddComponent();
	}
	
	protected void AddComponent(){}
	
	private class HelpPanelAction implements ActionListener {
		private String nameButton;
		
		HelpPanelAction(String name) {
			nameButton=name;
		}
		public void actionPerformed(ActionEvent event) {
			if (nameButton=="returnButton") {
				mainSLF.StartFrame.PanelChanger(getName(), GetOldPanel());
			}
		}
	}
	   
}