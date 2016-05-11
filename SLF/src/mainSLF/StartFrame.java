package mainSLF;

import gui.AboutPanel;
import gui.AllPanels;
import gui.CreatePanel;
import gui.FleetPanel;
import gui.GamePanel;
import gui.HelpPanel;
import gui.NewNamePanel;
import gui.SettingsPanel;
import gui.StartPanel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import core.Game;
import core.Settings;

public class StartFrame extends JFrame {

	private static final long serialVersionUID = -5164932368208143884L;
	
	public static final int DEFAULT_WIDTH = 1280;
	public static final int DEFAULT_HEIGHT = 768;
	public static Game myGame = Game.LoadInitGame();//new core.Game();
	
	public static CreatePanel crPan;
	public static NewNamePanel nnPan;
	public static StartPanel stPan;
	public static HelpPanel hePan;
	public static SettingsPanel sePan;
	public static AboutPanel abPan;
	
	public static GamePanel maPan;
	public static FleetPanel flPan;
	
	public static AllPanels panel;
	
	public static JLayeredPane cont;
	
	public StartFrame() {		
	      setTitle(gui.Messages.getString("Title")); //$NON-NLS-1$
	      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	      setResizable(false);
//TODO DON'T FORGET ABOUT THE ICON
	      
	      gui.BackgrPanel background=new gui.BackgrPanel();
	      background.setBounds(0,0,DEFAULT_WIDTH, DEFAULT_HEIGHT);
	      
	      cont=new JLayeredPane();
      
	      crPan = (CreatePanel) SetPanels("StartMenuCreatePanel", new CreatePanel(), cont);
	      nnPan = (NewNamePanel) SetPanels("а", new NewNamePanel(), cont);
	      stPan = (StartPanel) SetPanels("StartMenuStartPanel", new StartPanel(), cont);
	      hePan = (HelpPanel) SetPanels("StartMenuHelpPanel", new HelpPanel(), cont);
	      sePan =  (SettingsPanel) SetPanels("StartMenuSettingsPanel", new SettingsPanel(), cont);
	      abPan =  (AboutPanel) SetPanels("StartMenuAboutPanel", new AboutPanel(), cont);
	      
	      maPan = (GamePanel) SetPanels("GameMapPanel", new GamePanel(), cont);	      
	      flPan =  (FleetPanel) SetPanels("GameFleetPanel", new FleetPanel(), cont);
	      
	      cont.add(background, new Integer(0));
	      
	      this.setLayeredPane(cont);
	      addWindowListener(new WindowHandler());

	      myGame.Start();
	}
	
	class WindowHandler extends WindowAdapter {
		  public void windowClosing(WindowEvent e) {
			  Settings.SaveSettings();
			  myGame.SaveGame();
			  MainSLF.frame.dispose();
			  System.exit(0);
		  }
	}
	
	private AllPanels SetPanels(String name, AllPanels panel, JLayeredPane cont) {
	      panel.setLayout(null);
	      panel.setOpaque(false);
	      panel.setBounds(0,0,DEFAULT_WIDTH, DEFAULT_HEIGHT);
	      panel.setVisible(false);
	      panel.setName(name);
	      
	      cont.add(panel, new Integer(1));
	      return panel;
	}
	
	public static void PanelChanger(String oldName, String newName) {			
		for (int i=0;i<cont.getComponentCountInLayer(1);i++) {
			if (cont.getComponent(i).getName().equals(oldName)) {
				cont.getComponent(i).setVisible(false);
			}
			if (cont.getComponent(i).getName().equals(newName)) {
				AllPanels temp=(AllPanels) cont.getComponent(i);
				temp.setVisible(true);
				temp.SetOldPanel(oldName);
			}
		}
		
		MainSLF.focusComponent.requestFocusInWindow();
	}

}
