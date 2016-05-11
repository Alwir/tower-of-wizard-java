package gui;

import java.awt.Color;

import javax.swing.JLabel;

public class HelpPanel extends InfoPanels { 
	private static final long serialVersionUID = -7756754270109508048L;
	
	protected void AddComponent() {
		JLabel helpText=new JLabel("<html>А здесь могла бы быть ваша реклама<br> и еще куча всякой фигни, но вы этого не увидите...</html>");
		helpText.setBounds(380,140,500,380);
		helpText.setFont(MyButton.myFont);
		helpText.setForeground(new Color(150,255,255));

		helpText.setVerticalAlignment(JLabel.TOP);
		helpText.setHorizontalAlignment(JLabel.LEFT);
		
		add(helpText);
	}   
}