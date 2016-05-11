package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JLabel;

import sound.MakeSound;

class MyButton extends JButton
{
	private static final long serialVersionUID = 6008698942738843587L;
	private BufferedImage ramkaOn;
	private BufferedImage ramkaTemp;
	private BufferedImage ramkaOff;
	private BufferedImage ramka;
	private JLabel myText;
	private static float fontSize=18;

	static Font myFont;

	public MyButton(int xCoord,int yCoord,String [] files, String text) {		
	    InputStream is;
		try {
			ramkaOff =  (BufferedImage) LoadImageSfl.LoadImage(Messages.getString(files[0]));//ImageIO.read(new File(Messages.getString(files[0]))); //$NON-NLS-1$
			ramkaOn =  (BufferedImage) LoadImageSfl.LoadImage(Messages.getString(files[1]));//ImageIO.read(new File(Messages.getString(files[1]))); //$NON-NLS-1$
			ramka=ramkaOff;
			ramkaTemp=ramkaOn;
			
			is = new FileInputStream(Messages.getString("Font"));
			myFont=Font.createFont(Font.TRUETYPE_FONT,is);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		myFont=myFont.deriveFont(Font.BOLD,fontSize);
		
		this.setLayout(null);
		
	    if (text!="") {
			myText=new JLabel(text);
			myText.setBounds(20,7,ramka.getWidth(this)-40,ramka.getHeight(this)-14);
			myText.setFont(myFont);
			myText.setForeground(new Color(43,56,143));
			myText.setVerticalAlignment(JLabel.CENTER);
			myText.setHorizontalAlignment(JLabel.CENTER);
			
//			intelegence.setBackground(new Color(100,100,143));
//			intelegence.setOpaque(true);
			
			this.add(myText);
		}

		this.setBorderPainted(false);
		this.addMouseListener(new MouseHandler());
		this.setContentAreaFilled(false);
		this.setBounds(xCoord,yCoord,ramka.getWidth(this),ramka.getHeight(this));	
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
                								RenderingHints.VALUE_RENDER_QUALITY));
		
		g2.drawImage(ramka, 0, 0, ramka.getWidth(this), ramka.getHeight(this),   this);
	}
	
	public void setEnabled(boolean onOff)
	{
		if (onOff) {
			myText.setForeground(new Color(43,56,143));
			ramkaOn=ramkaTemp;
			super.setEnabled(true);
		}
		else {
			myText.setForeground(new Color(100,156,243));
			ramkaOn=ramkaOff;
			super.setEnabled(false);
		}
	}

	public static void setButtonFontSize(float fontSizes) {
		fontSize=fontSizes;
	}
	
 	private class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent event) {
//			JButton temp=(JButton) event.getSource();
//System.out.print(temp.getName());
			new MakeSound(Messages.getString("soundButtonClick"));
		}
		public void mouseClicked(MouseEvent event) {}
		public void mouseEntered(MouseEvent event) {
			new MakeSound(Messages.getString("soundButtonOnmove"));
			ramka=ramkaOn;
		}
		public void mouseExited(MouseEvent event) {
			ramka=ramkaOff;
		}	
	}
}