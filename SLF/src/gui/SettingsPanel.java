package gui;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mainSLF.StartFrame;
import sound.MakeSound;

public class SettingsPanel extends InfoPanels {
	private static final long serialVersionUID = -9005412483552583170L;
	private JSlider musicVolume;
	private JSlider soundVolume;
	
	protected void AddComponent() {
		JPanel soundPanel=new JPanel();
		soundPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0f,0f,0f)), Messages.getString("settingsSoundPanelTitul")));
		soundPanel.setBounds(370,140,260,100);
		soundPanel.setLayout(null);
		soundPanel.setBackground(new Color(0f,0f,0f,0f));
//		soundPanel.setOpaque(true);
		
		JLabel music=new JLabel(Messages.getString("settingsMusicLabel"));
		music.setBounds(5,20,100,35);
		music.setFont(MyButton.myFont.deriveFont(16f));
		music.setForeground(new Color(150,255,255));
		soundPanel.add(music);
		JLabel sound=new JLabel(Messages.getString("settingsSoundLabel"));
		sound.setBounds(5,50,100,35);
		sound.setFont(MyButton.myFont.deriveFont(16f));
		sound.setForeground(new Color(150,255,255));
		soundPanel.add(sound);
		
		musicVolume=new JSlider(0,100);
		musicVolume.setBounds(105, 20, 150, 35);
		musicVolume.setBackground(new Color(0f,0f,0f,0f));
		musicVolume.setValue((int) (StartFrame.myGame.settings.getMusicVolume()*100));
		musicVolume.addChangeListener(new SoundHandler());
		musicVolume.setName("music");
		soundPanel.add(musicVolume);
		
		soundVolume=new JSlider(0,100);
		soundVolume.setBounds(105, 50, 150, 35);
		soundVolume.setBackground(new Color(0f,0f,0f,0f));
		soundVolume.setValue((int) (StartFrame.myGame.settings.getSoundVolume()*100));
		soundVolume.addChangeListener(new SoundHandler());
		soundVolume.setName("sound");
		soundPanel.add(soundVolume);
		
		add(soundPanel);
		addComponentListener(new SettingsPanelCompAction());

	}
	
	private class SettingsPanelCompAction implements ComponentListener {
		public void componentShown(ComponentEvent arg0) {
			musicVolume.setValue((int) (StartFrame.myGame.settings.getMusicVolume()*100));
			soundVolume.setValue((int) (StartFrame.myGame.settings.getSoundVolume()*100));
		}
		public void componentHidden(ComponentEvent arg0) {}
		public void componentMoved(ComponentEvent e) {}
		public void componentResized(ComponentEvent e) {}
	}
	
	private class SoundHandler implements ChangeListener {
		public void stateChanged(ChangeEvent event) {
			String name=((JSlider) (event.getSource())).getName();
			if (name.equals("music")) {
				StartFrame.myGame.settings.setMusicVolume(musicVolume.getValue()/100.0);
			}
			else if (name.equals("sound")) {
				StartFrame.myGame.settings.setSoundVolume(soundVolume.getValue()/100.0);
				new MakeSound(Messages.getString("soundButtonOnmove"));
			}
	    }		
	}
}
