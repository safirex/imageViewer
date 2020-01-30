package code_lecteur;

import ihm.ImageExample;
import javafx.scene.image.Image;

public class ImageReader {
	String name;
	public ImageReader(String n) {
		name=n;
	}
	public ImageReader() {}

	
	public void setImage(String n) {
		name=n;
		
	}
	
	public Image getImage() {
		return new Image(name);
	}
	
	
}
