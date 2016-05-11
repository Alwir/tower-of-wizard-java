package sound;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import mainSLF.StartFrame;

public class MakeSound extends JFXPanel {
	private static final long serialVersionUID = 6565267441669598582L;
	private MusicThread music;
	
	public MakeSound(String soundName,boolean loop){		
		music=new MusicThread(soundName,loop);
		music.start();
	}
	public MakeSound(String soundName){		
		music=new MusicThread(soundName,false);
		music.start();
	}
	
	public void StopMusic() {
		music.StopMusic();
	}
	public void PlayMusic(){
		music.PlayMusic();
	}
	
	private class MusicThread extends Thread {
		private String resource;
		private boolean isLoop;
		private boolean isStop=false;
		
		public MusicThread(String resource,boolean loop){
			this.resource=resource;
			this.isLoop=loop;
		}
		
		public void StopMusic(){
			isStop=true;
		}
		public void PlayMusic(){
			isStop=false;
		}
		
		public void run() {
			final Media media = new Media(new File(resource).toURI().toString());
			final MediaPlayer mediaPlayer = new MediaPlayer(media);
			double volume=0;
			double globalMusicVolume=StartFrame.myGame.settings.getMusicVolume(); //MainSLF.frame
			double globalSoundVolume=StartFrame.myGame.settings.getSoundVolume();
			
			if (isLoop) {
				mediaPlayer.setCycleCount(Integer.MAX_VALUE);
				mediaPlayer.setVolume(volume);
				mediaPlayer.play();
			}
			else {
				if (globalSoundVolume>0) {
					mediaPlayer.play();
				}
				else {
					mediaPlayer.stop();
					mediaPlayer.dispose();
					return;
				}
				mediaPlayer.setVolume(globalSoundVolume);
			}
			try {
//TODO need will make pause how duration of sound
				do {
					sleep(100);
					if (isLoop) {
						globalMusicVolume=StartFrame.myGame.settings.getMusicVolume();
						if (volume<globalMusicVolume) {
							volume+=0.005;
							if (volume>1){
								volume=1;
							}
							mediaPlayer.setVolume(volume);
						}
						else if(volume>globalMusicVolume) {
							volume=globalMusicVolume;
							mediaPlayer.setVolume(volume);
						}
						if (isStop) {
							mediaPlayer.pause();
						}
						else if (mediaPlayer.getStatus()==MediaPlayer.Status.PAUSED) {
							volume=0;
							mediaPlayer.play();
						}
					}
					else if (globalSoundVolume!=StartFrame.myGame.settings.getSoundVolume()) {
						globalSoundVolume=StartFrame.myGame.settings.getSoundVolume();
						mediaPlayer.setVolume(globalSoundVolume);
					}
				} while (mediaPlayer.getCurrentRate()>0f || mediaPlayer.getStatus()==MediaPlayer.Status.PAUSED);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			mediaPlayer.stop();
			mediaPlayer.dispose();
		} 
	}
}