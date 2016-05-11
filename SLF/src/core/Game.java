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

public class Game implements Serializable {
	private static final long serialVersionUID = -2238703437055403766L;
	
	private String charFoto;
	private String charName;
	private String charRank;
	private boolean OldGameExist=false;
	public Settings settings;//=new Settings();
	
	public Game() {
//TODO Initialization from file or new start
		settings=Settings.LoadSettings();
		charFoto="01";
		charName="";
		charRank="Адмирал";
	}
	
	public void Start() {
//		StartFrame.maPan.setVisible(true);

		if (GetName()=="") {
			StartFrame.crPan.setVisible(true);
		}
		else {
			StartFrame.stPan.setVisible(true);
		}

	}
	
	public void Initialization() {
		SetOldGame(true);
	}
	
	public static Game LoadInitGame() {
		Game result = null;
        File fpath = new File("game");
        if (fpath != null) {
            try {
				FileInputStream fis = new FileInputStream(fpath);
				ObjectInputStream ois = new ObjectInputStream(fis);
				result = (Game) ois.readObject();
				ois.close();
				fis.close();
			} catch (FileNotFoundException e) {
				result=new Game();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return result;
	}
	
    public void SaveGame() {
    	if (GetName()=="") {
    		return;
    	}
        File fpath = new File("game");
        if (fpath != null) {
	        try {
				FileOutputStream fos = new FileOutputStream(fpath);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(StartFrame.myGame);
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
	
    public void SetOldGame(boolean bool) {
    	this.OldGameExist=bool;
    }
	public void SetRank(String name) {
		this.charRank=name;
	}
	public void SetName(String name) {
		this.charName=name;
	}
	public void SetFoto(int name) {
		if (name>6)
			name=1;
		
		if (name<1)
			name=6;
		
		if (this.charFoto=="")
			name=1;
		
		this.charFoto="0"+String.valueOf(name);
	}

	public final boolean GetOldGame(){ 
		return this.OldGameExist;
	}
	public final String GetRank() {
		return this.charRank;
	}
	public final String GetName() {
		return this.charName;
	}
	public final String GetIndexFoto(int ind) {
		if (this.charFoto=="")
			return "00";
		
		return "0"+String.valueOf(ind);
	}
	public final String GetFoto() {
		if (this.charFoto=="")
			return "00";
		
		return this.charFoto;
	}
}
