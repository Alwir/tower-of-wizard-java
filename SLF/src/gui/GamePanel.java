package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import sound.MakeSound;
import constance.Directions;

public class GamePanel extends AllPanels { 
	private static final long serialVersionUID = 1024944991702665403L;
	protected Image backg;
	protected Image image;
	
	private static Image mapBackgroundImage;
	private static int mapOffsetX=-1175;
	private static int mapOffsetY=-610;	

	private static Image alarmOn;
	private static Image alarmOff;
	private static Image coverOn;
	private static Image coverOff;
	static JLabel alarm;
	static JLabel cover;
	
	public GamePanel() {
		backg=LoadImageSfl.LoadImage(Messages.getString("mapPanelImageFile.0"));
		image=LoadImageSfl.LoadImage(Messages.getString("mapPanelImageFile.1"));
		alarmOff=LoadImageSfl.LoadImage(Messages.getString("mapPanelImageFile.3"));
		alarmOn=LoadImageSfl.LoadImage(Messages.getString("mapPanelImageFile.4"));
		coverOff=LoadImageSfl.LoadImage(Messages.getString("mapPanelImageFile.5"));
		coverOn=LoadImageSfl.LoadImage(Messages.getString("mapPanelImageFile.6"));

		
//		image = image.getScaledInstance((int) (1280*(1280.0/1315.0)), (int) (768*(968.0/1035.0)), Image.SCALE_SMOOTH);
		backg = backg.getScaledInstance(mainSLF.StartFrame.DEFAULT_WIDTH, mainSLF.StartFrame.DEFAULT_HEIGHT, Image.SCALE_SMOOTH);
		
		MyButton menuButton = new MyButton(960,0,new String[] {"mapPanelImageFile.7","mapPanelImageFile.8"},Messages.getString("buttonTextMenu"));
		menuButton.addActionListener(new GamePanelsAction("menuButton"));
		add(menuButton);
	
		MyButton baseButton = new MyButton(960,50,new String[] {"mapPanelImageFile.7","mapPanelImageFile.8"},Messages.getString("buttonTextBase"));
//		helpButton.addActionListener(new StartPanelsAction("helpButton"));
		add(baseButton);

		MyButton fleetButton = new MyButton(960,100,new String[] {"mapPanelImageFile.7","mapPanelImageFile.8"},Messages.getString("buttonTextFlot"));
		fleetButton.addActionListener(new GamePanelsAction("fleetButton"));
		add(fleetButton);
		
		JLabel intelegence=new JLabel("00:00");
		intelegence.setBounds(982,140,110,70);
		intelegence.setFont(MyButton.myFont);
		intelegence.setForeground(new Color(150,255,255));
		intelegence.setVerticalAlignment(JLabel.CENTER);
		intelegence.setHorizontalAlignment(JLabel.CENTER);
		add(intelegence);
		
		MyButton intelegenceButton = new MyButton(952,194,new String[] {"mapPanelImageFile.9","mapPanelImageFile.10"},Messages.getString("buttonTextIntel"));
//		helpButton.addActionListener(new StartPanelsAction("helpButton"));
		add(intelegenceButton);
		
		JLabel striceFlot=new JLabel(Messages.getString("mapPanelTextStrikeFleet"));
		striceFlot.setBounds(262,480,200,70);
		striceFlot.setFont(MyButton.myFont);
		striceFlot.setForeground(new Color(64,64,65));
		striceFlot.setVerticalAlignment(JLabel.CENTER);
		striceFlot.setHorizontalAlignment(JLabel.CENTER);
		add(striceFlot);

		JLabel reserveFlot=new JLabel(Messages.getString("mapPanelTextReservedFleet"));
		reserveFlot.setBounds(812,480,200,70);
		reserveFlot.setFont(MyButton.myFont);
		reserveFlot.setForeground(new Color(64,64,65));
		reserveFlot.setVerticalAlignment(JLabel.CENTER);
		reserveFlot.setHorizontalAlignment(JLabel.CENTER);
		add(reserveFlot);
		
		alarm=new JLabel();
		alarm.setBounds(622,485,200,70);
		ImageIcon alarmIco = new ImageIcon();
		alarmIco.setImage(alarmOff);
		alarm.setIcon(alarmIco);
		add(alarm);
		
		cover=new JLabel();
		cover.setBounds(579,535,200,200);
		ImageIcon coverIco = new ImageIcon();
		coverIco.setImage(coverOff);
		cover.setIcon(coverIco);
		add(cover);
		
		MyButton startButton = new MyButton(603,660,new String[] {"mapPanelImageFile.2","mapPanelImageFile.2"},"");
		startButton.addMouseListener(new MouseHandler("startButton"));
//		helpButton.addActionListener(new StartPanelsAction("helpButton"));
		add(startButton);
		
		MyButton leftButton = new MyButton(161,240,new String[] {"mapPanelImageFile.12","mapPanelImageFile.12"},"");
		leftButton.addActionListener(new GamePanelsAction("leftButton"));
		leftButton.addMouseListener(new MouseHandler("leftButton"));
		add(leftButton);
		MyButton rightButton = new MyButton(903,240,new String[] {"mapPanelImageFile.13","mapPanelImageFile.13"},"");
		rightButton.addActionListener(new GamePanelsAction("rightButton"));
		rightButton.addMouseListener(new MouseHandler("rightButton"));
		add(rightButton);
		MyButton upButton = new MyButton(553,45,new String[] {"mapPanelImageFile.14","mapPanelImageFile.14"},"");
		upButton.addActionListener(new GamePanelsAction("upButton"));
		upButton.addMouseListener(new MouseHandler("upButton"));
		add(upButton);
		MyButton downButton = new MyButton(553,460,new String[] {"mapPanelImageFile.15","mapPanelImageFile.15"},"");
		downButton.addActionListener(new GamePanelsAction("downButton"));
		downButton.addMouseListener(new MouseHandler("downButton"));
		add(downButton);
		
		mapBackgroundImage=LoadImageSfl.LoadImage(Messages.getString("mapPanelImageFile.11"));

		
/*		
		JLabel mapBackground=new JLabel();
		mapBackground.setBounds(100,100,200,200);
		ImageIcon mapBackIco=new ImageIcon();
		mapBackgroundImage=LoadImageSfl.LoadImage(Messages.getString("mapPanelImageFile.11"));
		mapBackgroundImage.getGraphics().clipRect(0, 0, 100, 100);
		
		mapBackIco.setImage(mapBackgroundImage);
		mapBackground.setIcon(mapBackIco);
//		add(mapBackground);
 */
		
	}
	
	private static class MapScroll extends Thread {
		private static Directions direction;
		boolean pause=false;
		
		MapScroll(Directions direct)
		{
			direction=direct;
		}
	
		public void run() {
			while(true) {
				if (direction==Directions.LEFT) {
					mapOffsetX-=1;
				}
				else if (direction==Directions.DOWN) {
					mapOffsetY-=1;	
				}
				else if (direction==Directions.RIGHT) {
					mapOffsetX+=1;
				}
				else if (direction==Directions.UP) {
					mapOffsetY+=1;
				}
				try {
					sleep(60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (pause) {
					break;
				}
			}
		}
		
		public void Pause() {
			pause=true;
		}
	}
	
	private class MouseHandler extends MouseAdapter {
		private String buttonName;
		private MapScroll mapScroll;
		
		MouseHandler(String name) {
			buttonName=name;
		}
		
		public void InitDaemonScroll(Directions direction) {
			mapScroll=new MapScroll(direction);
			mapScroll.start();
		}
		
		public void mouseClicked(MouseEvent event) {}
		public void mouseEntered(MouseEvent event) {
			if (buttonName=="startButton") {
				ChangeImage(alarm,alarmOn);
				ChangeImage(cover,coverOn);
			}
			else if(buttonName=="leftButton") {
				InitDaemonScroll(Directions.LEFT);
			}
			else if(buttonName=="rightButton") {
				InitDaemonScroll(Directions.RIGHT);
			}
			else if(buttonName=="upButton") {
				InitDaemonScroll(Directions.UP);
			}
			else if(buttonName=="downButton") {
				InitDaemonScroll(Directions.DOWN);
			}
		}
		public void mouseExited(MouseEvent event) {
			if (buttonName=="startButton") {
				ChangeImage(alarm,alarmOff);
				ChangeImage(cover,coverOff);
			}
			else if(buttonName=="leftButton" || buttonName=="rightButton" || buttonName=="upButton" || buttonName=="downButton") {
				mapScroll.Pause();
			}
		}	
	}
	
	private static void ChangeImage(JLabel button, Image image) {
		ImageIcon setIco = new ImageIcon();
		setIco.setImage(image);
		button.setIcon(setIco);		
	}

	private class GamePanelsAction implements ActionListener {
		private String name;
			
		public GamePanelsAction(String name) {
			this.name=name;
		}
		
		public void actionPerformed(ActionEvent event) {
			if(name=="menuButton") {
				new MakeSound(Messages.getString("soundButtonMenu"));
				StartPanels.menuMusic.PlayMusic();
				mainSLF.StartFrame.PanelChanger(getName(), "StartMenuStartPanel");	
			}
			else if(name=="fleetButton") {
				new MakeSound(Messages.getString("soundButtonMenu"));
				mainSLF.StartFrame.PanelChanger(getName(), "GameFleetPanel");	
			}
			else if(name=="leftButton") { //1980x1080 805x470 1175x610
				mapOffsetX+=1;
			}
			else if(name=="rightButton") {
				mapOffsetX-=1;
			}
			else if(name=="upButton") {
				mapOffsetY+=1;
			}
			else if(name=="downButton") {
				mapOffsetY-=1;
			}
		}
	}
	   
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(backg,0 ,0, this);
		
//		g2.drawImage(mapBackgroundImage,140 ,30,945,500,140+mapOffsetX,30+mapOffsetY,945+mapOffsetX,500+mapOffsetY, this);
		
		g2.drawImage(mapBackgroundImage,140, 30, 945, 500,
										1900+mapOffsetX, 1080+mapOffsetY, 1900+mapOffsetX+840, 1080+mapOffsetY+470, this);
		
		g2.drawImage(image,-20,-147, this);//-15,-105,

	}
}
