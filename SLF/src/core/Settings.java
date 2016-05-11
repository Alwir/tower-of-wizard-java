package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import mainSLF.StartFrame;

public class Settings implements Serializable {

	private static final long serialVersionUID = 6426757564476121892L;
	private double musicVolume=0.3;
	private double soundVolume=0.5;
	

	
    /*
     * ������� ��������������� - ��������� �������� ������������
     * ����� ��� ����� String, ��� � ����� Path
     * @param path - ���� ���������� � ���� (String ��� File)
     */
    private static File castFileFromObject(Object path) {
        File fpath;
        if (path instanceof java.lang.String) {
            fpath = new File((String)path);
        } else {
            if (path instanceof java.io.File) {
                fpath = (File)path;
            } else {
                fpath = null;
            }
        }
        return fpath;
    }
    
    /*
     * ���������� ������� Java � ����
     * @param path - ���� ���������� � ���� (String ��� File)
     * @param value - ������ ��� ����������
     */
    public static void SaveSettings() {
        File fpath = castFileFromObject("settings");
        if (fpath != null) {
	        try {
				FileOutputStream fos = new FileOutputStream(fpath);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(StartFrame.myGame.settings);
				oos.close();
				fos.close();
			} 
	        catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
	        catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
    
    /*
     * �������� ������� java �� �����
     * @param path - ���� ������� �� ����� (String ��� File)
     */
    public static Settings LoadSettings() {
    	Settings result = null;
        File fpath = castFileFromObject("settings");
        if (fpath != null) {
            try {
				FileInputStream fis = new FileInputStream(fpath);
				ObjectInputStream ois = new ObjectInputStream(fis);
				result = (Settings) ois.readObject();
				ois.close();
				fis.close();
			} catch (FileNotFoundException e) {
				result=new Settings();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return result;
    }
	
	public double getMusicVolume() {
		return musicVolume;
	}
	public double getSoundVolume() {
		return soundVolume;
	}
	public void setMusicVolume(double musicVolume) {
		this.musicVolume = musicVolume;
	}
	public void setSoundVolume(double soundVolume) {
		this.soundVolume = soundVolume;
	}

	
}
