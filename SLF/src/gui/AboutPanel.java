package gui;

import java.awt.Color;

import javax.swing.JLabel;

public class AboutPanel extends InfoPanels {
	private static final long serialVersionUID = -4843474561055196045L;

	protected void AddComponent() {
		JLabel helpText=new JLabel("<html>Настя - суперрисовастя <br>Блейз - вездезалез <br>Алвир - вообщемолодец</html>");
		helpText.setBounds(380,140,500,380);
		helpText.setFont(MyButton.myFont);
		helpText.setForeground(new Color(150,255,255));

		helpText.setVerticalAlignment(JLabel.TOP);
		helpText.setHorizontalAlignment(JLabel.LEFT);
		
		add(helpText);

	}  
}
