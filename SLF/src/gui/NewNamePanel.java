package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import mainSLF.MainSLF;
import mainSLF.StartFrame;
import constance.Dialogs;
import constance.YesNo;

public class NewNamePanel extends StartPanels {
	private static final long serialVersionUID = -4636977756412689253L;
	
	private static String charName;
	private static MyButton returnButton;
	private static JFormattedTextField tf;
	private static int numberFoto=Integer.parseInt(StartFrame.myGame.GetFoto());
	
	protected void AddComponent() {
		MyButton createButton = new MyButton(620,358,new String[] {"nnpMyButton.0","nnpMyButton.1"},Messages.getString("buttonTextCreate"));
		createButton.addActionListener(new NewNameAction("createButton"));
		add(createButton);
		
		MyButton leftButton = new MyButton(531,285,new String[] {"nnpMyButton.2","nnpMyButton.3"},"");
		leftButton.addActionListener(new NewNameAction("leftButton"));
		add(leftButton);
		
		MyButton rightButton = new MyButton(335,285,new String[] {"nnpMyButton.4","nnpMyButton.5"},"");
		rightButton.addActionListener(new NewNameAction("rightButton"));
		add(rightButton);
		
		returnButton = new MyButton(455,429,new String[] {"nnpMyButton.0","nnpMyButton.1"},Messages.getString("buttonTextReturn"));
		returnButton.addActionListener(new NewNameAction("returnButton"));
		add(returnButton);
		
		tf=new JFormattedTextField();
		tf.setFont(MyButton.myFont);
		tf.setBounds(630,280,250,70);
		tf.setCaretColor(new Color(43,56,143));
		tf.setForeground(new Color(43,56,143));
		tf.setSelectedTextColor(new Color(255,255,255));
		tf.setOpaque(false);
		tf.setBorder(null);
//		tf.setVerticalAlignment(JLabel.CENTER);
		tf.setHorizontalAlignment(JLabel.CENTER);
		
		tf.setDocument(new PlainDocument() {
			private static final long serialVersionUID = 5273818881775665072L;
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
	            if (getLength()<10) {
	            	super.insertString(offs, str, a);
	            	charName=tf.getText();
	            }
	        }
			public void remove(int off,int len) throws BadLocationException  {
				super.remove(off, len);
				charName=tf.getText();
			}
	    });
		
		MainSLF.focusComponent=tf;
		add(tf);
		
		
		AddRosterLabel(571,215);
		
		addComponentListener(new NewNamePanelCompAction());
	}
	
	private class NewNamePanelCompAction implements ComponentListener {
		@Override
		public void componentShown(ComponentEvent arg0) {
			if (GetOldPanel()=="StartMenuCreatePanel") {
				returnButton.setEnabled(false);
			}
			else {
				returnButton.setEnabled(true);
			}
			
			numberFoto=Integer.parseInt(StartFrame.myGame.GetFoto());
			tf.setText(StartFrame.myGame.GetName());
		}
		@Override
		public void componentHidden(ComponentEvent arg0) {}
		@Override
		public void componentMoved(ComponentEvent e) {}
		@Override
		public void componentResized(ComponentEvent e) {}
	}
	
	protected void LoadImage() {
		LoadImageFromText("nnpPanel.0");
	}
	
	private class NewNameAction implements ActionListener {
		private String name;
		
		public NewNameAction(String name) {
			this.name=name;
		}
		
		public void actionPerformed(ActionEvent event) {
			if (name=="createButton") {
				if (charName==null || charName.isEmpty()) {
					Messages.ShowMessage("messageNoName",Dialogs.MESSAGE);
				}
				else {
					YesNo answer=Messages.ShowMessage("messageNewChar",Dialogs.DIALOG);
					if (answer==YesNo.YES) {
						StartFrame.myGame.SetOldGame(false);
						StartFrame.myGame.SetName(charName);
						StartFrame.myGame.SetFoto(numberFoto);
						mainSLF.StartFrame.PanelChanger(getName(), "StartMenuStartPanel");
					}
				}
			}
			else if (name=="leftButton" || name=="rightButton") {
				int tempNumberFoto=numberFoto;
				switch (name) {
					case "leftButton":
						tempNumberFoto--;
						break;
					case "rightButton":
						tempNumberFoto++;
						break;
				}
				if (tempNumberFoto>6) {
					tempNumberFoto=1;
				}
				if (tempNumberFoto<1) {
					tempNumberFoto=6;					
				}
				numberFoto=tempNumberFoto;
				LoadFoto(StartFrame.myGame.GetIndexFoto(numberFoto));
				repaint();
			}
			else if (name=="returnButton") {
				mainSLF.StartFrame.PanelChanger(getName(), GetOldPanel());
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		if (foto!=null)
			g2.drawImage(foto, 376,238, this);
	}
}
