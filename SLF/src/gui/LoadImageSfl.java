package gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadImageSfl {
	LoadImageSfl(){}
	
	public static Image LoadImage(String fileName)
	{
		Image image=null;
		try {
	        image = ImageIO.read(new File(fileName)); //$NON-NLS-1$
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
		return image;
	}
}
