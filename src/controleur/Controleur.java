package controleur;


import code_lecteur.FolderManager;
import ihm.ImageExample;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

public class Controleur {
	
	String fichierActuel;
	private ListView<Image> imgList;
	private FolderManager manager;
	
	
	public Controleur() {
		
	}
	public Controleur(String arg) {
		fichierActuel=arg;
	}
	public String getFile() {
		return this.fichierActuel;
	}
	
	public void initModel(FolderManager f) {
		manager=f;
		
	}
}
