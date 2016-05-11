package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatePanel extends StartPanels {  
	private static final long serialVersionUID = 6696978150653573519L;
	
	protected void AddComponent() {
		MyButton createButton = new MyButton(590,341,new String[] {"cpMyButton.0","cpMyButton.1"},Messages.getString("buttonTextCreate"));
		createButton.addActionListener(new CreatePanelAction("createButton"));
		add(createButton);
		
		AddRosterLabel(541,270);
	}
	
	protected void LoadImage() {	
		LoadImageFromText("cpPanel.0");
	}
	
	private class CreatePanelAction implements ActionListener {
		private String name;
		
		public CreatePanelAction(String name) {
			this.name=name;
		}
		
		public void actionPerformed(ActionEvent event) {
			if (name=="createButton") {
				mainSLF.StartFrame.PanelChanger(getName(), "StartMenuNewNamePanel");			
			}
		}
	}
}