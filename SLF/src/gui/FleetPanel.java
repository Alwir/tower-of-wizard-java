package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class FleetPanel extends AllPanels {
	private static final long serialVersionUID = -5165823440759361598L;
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
	
	public FleetPanel() {
		backg=LoadImageSfl.LoadImage(Messages.getString("fleetPanelImageFile.0"));
		image=LoadImageSfl.LoadImage(Messages.getString("fleetPanelImageFile.1"));
		alarmOff=LoadImageSfl.LoadImage(Messages.getString("mapPanelImageFile.3"));
		alarmOn=LoadImageSfl.LoadImage(Messages.getString("mapPanelImageFile.4"));
		coverOff=LoadImageSfl.LoadImage(Messages.getString("mapPanelImageFile.5"));
		coverOn=LoadImageSfl.LoadImage(Messages.getString("mapPanelImageFile.6"));

		
//		image = image.getScaledInstance((int) (1280*(1280.0/1315.0)), (int) (768*(968.0/1035.0)), Image.SCALE_SMOOTH);
		backg = backg.getScaledInstance(mainSLF.StartFrame.DEFAULT_WIDTH, mainSLF.StartFrame.DEFAULT_HEIGHT, Image.SCALE_SMOOTH);
		
		MyButton menuButton = new MyButton(960,0,new String[] {"mapPanelImageFile.7","mapPanelImageFile.8"},Messages.getString("buttonTextMenu"));
//		menuButton.addActionListener(new GamePanelsAction("menuButton"));
		add(menuButton);
	
		MyButton baseButton = new MyButton(960,50,new String[] {"mapPanelImageFile.7","mapPanelImageFile.8"},Messages.getString("buttonTextBase"));
//		helpButton.addActionListener(new StartPanelsAction("helpButton"));
		add(baseButton);

		MyButton fleetButton = new MyButton(960,100,new String[] {"mapPanelImageFile.7","mapPanelImageFile.8"},Messages.getString("buttonTextFlot"));
//		fleetButton.addActionListener(new GamePanelsAction("fleetButton"));
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
//		startButton.addMouseListener(new MouseHandler("startButton"));
//		helpButton.addActionListener(new StartPanelsAction("helpButton"));
		add(startButton);
		
		MyButton leftButton = new MyButton(161,240,new String[] {"mapPanelImageFile.12","mapPanelImageFile.12"},"");
//		leftButton.addActionListener(new GamePanelsAction("leftButton"));
//		leftButton.addMouseListener(new MouseHandler("leftButton"));
		add(leftButton);
		MyButton rightButton = new MyButton(903,240,new String[] {"mapPanelImageFile.13","mapPanelImageFile.13"},"");
//		rightButton.addActionListener(new GamePanelsAction("rightButton"));
//		rightButton.addMouseListener(new MouseHandler("rightButton"));
		add(rightButton);
		MyButton upButton = new MyButton(553,45,new String[] {"mapPanelImageFile.14","mapPanelImageFile.14"},"");
//		upButton.addActionListener(new GamePanelsAction("upButton"));
//		upButton.addMouseListener(new MouseHandler("upButton"));
		add(upButton);
		MyButton downButton = new MyButton(553,460,new String[] {"mapPanelImageFile.15","mapPanelImageFile.15"},"");
//		downButton.addActionListener(new GamePanelsAction("downButton"));
//		downButton.addMouseListener(new MouseHandler("downButton"));
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
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(backg,0 ,0, this);		
		g2.drawImage(image,-20,-147, this);//-15,-105,
	}
}
