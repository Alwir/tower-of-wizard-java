package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import mainSLF.MainSLF;
import constance.Dialogs;
import constance.YesNo;

public class DialogPanel extends JDialog {
	private static final long serialVersionUID = -4357362455807916188L;
	private YesNo result=YesNo.CANCEL;

	public DialogPanel(String message, Dialogs type) {
		super(MainSLF.frame,true);
		
		Image panelImage=LoadImageSfl.LoadImage(Messages.getString("messagePanel.01"));
		
		this.setLayout(null);
		this.setBounds(0,0,panelImage.getWidth(this),panelImage.getHeight(this));
		this.setLocationRelativeTo (MainSLF.frame);
		this.setUndecorated(true);
		this.setBackground(new Color(0f,0f,0f,0f));
		this.setDefaultCloseOperation (DISPOSE_ON_CLOSE);
		
		JLabel titul=new JLabel( Messages.getString("messageDialogsTitul"));
		titul.setBounds(120,155,230,20);
//		titul.setBackground(new Color(100,200,20));
//		titul.setOpaque(true);
		titul.setForeground(new Color(64,64,65));
		titul.setVerticalAlignment(JLabel.CENTER);
		titul.setHorizontalAlignment(JLabel.CENTER);
		add(titul);
	
		JLabel text=new JLabel(Messages.getString(message));
		text.setBounds(120,170,220,70);
//		text.setBackground(new Color(20,200,0));
//		text.setOpaque(true);
		text.setForeground(new Color(150,255,255));
		text.setVerticalAlignment(JLabel.CENTER);
		text.setHorizontalAlignment(JLabel.CENTER);
		add(text);
		
		YesNo[] answer=Dialogs.GetAnswer(type);
		String [] answerText=YesNo.GetYesNoText(answer);
		switch (type) {
			case MESSAGE: {
				MyButton firstButton = new MyButton(180,panelImage.getHeight(this)-120,new String[] {"messagePanel.02","messagePanel.03"},answerText[0]);
				firstButton.addActionListener(new DialogAction(answer[0]));		
				add(firstButton);
				break;
			}
			case DIALOG: {
				MyButton firstButton = new MyButton(140,panelImage.getHeight(this)-120,new String[] {"messagePanel.02","messagePanel.03"},answerText[0]);
				firstButton.addActionListener(new DialogAction(answer[0]));		
				add(firstButton);
				MyButton secondButton = new MyButton(220,panelImage.getHeight(this)-120,new String[] {"messagePanel.02","messagePanel.03"},answerText[1]);
				secondButton.addActionListener(new DialogAction(answer[1]));		
				add(secondButton);
				break;
			}
			case BIGDIALOG: {
				MyButton.setButtonFontSize(9);
				MyButton firstButton = new MyButton(95,panelImage.getHeight(this)-120,new String[] {"messagePanel.02","messagePanel.03"},answerText[0]);
				firstButton.addActionListener(new DialogAction(answer[0]));		
				add(firstButton);
				MyButton secondButton = new MyButton(175,panelImage.getHeight(this)-120,new String[] {"messagePanel.02","messagePanel.03"},answerText[1]);
				secondButton.addActionListener(new DialogAction(answer[1]));		
				add(secondButton);
				MyButton thirdButton = new MyButton(255,panelImage.getHeight(this)-120,new String[] {"messagePanel.02","messagePanel.03"},answerText[2]);
				thirdButton.addActionListener(new DialogAction(answer[2]));		
				add(thirdButton);
				MyButton.setButtonFontSize(18);
				break;
			}
		}
		
		
		
		ImageIcon panelIcon=new ImageIcon(panelImage);
		JLabel panel=new JLabel(panelIcon);
		panel.setBounds(0,0,panelImage.getWidth(this),panelImage.getHeight(this));
		add(panel);	
		
		this.setVisible(true);
	}
	
  	private class DialogAction implements ActionListener {
  		private YesNo answer;
		public DialogAction(YesNo answer) {
			this.answer=answer;
		}
		public void actionPerformed(ActionEvent event) {
			result=answer;
			setVisible(false);
		}
	}
  	
  	public final YesNo GetResult() {
  		return result;
  	}

}