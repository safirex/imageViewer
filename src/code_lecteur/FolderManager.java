package code_lecteur;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * 
 * S'occupe de la gestion des fichiers
 * 
 * @author Xxsafirex
 * 
 * 
 */
public class FolderManager {
	public static String appArgs;
	private int imageNum;
	String path=".";
	String currentImageName;
	String[] imageNameList= {""};
	List<String> imageNameArray;
	Boolean nextExists =false, precedentExists =false;
	
	private final ObservableList<Image> imgList = FXCollections.observableArrayList(image -> 
    												new Observable[] {(Observable) image});
	public final ObjectProperty<Image> currentImage = new SimpleObjectProperty<>();
	
	public FolderManager() throws FileNotFoundException {
		if(appArgs==null) {
		String p="";
		//setCurrentImage(p);
		
		}
		else
			setCurrentImage(appArgs);
	};
	
	public FolderManager(String n) {
		path=n;
	} 
	public Image currentImageProperty() {
        return currentImage.get() ;
    }
	
	public ObservableList<Image> getImageList(){
		return imgList; 
	}
	
	/**
	 * modifie l'image actuelle par l'image du path
	 * @param path of the picture, including it's name
	 * @throws FileNotFoundException
	 */
	
	public final void setCurrentImage(String path) throws FileNotFoundException {
		setPath(path);
        currentImage.set(new Image(new FileInputStream(path))); 
        //System.out.println(currentImage.getName());
        getFileImageList();
        imageSearch();
        lookForNextImage();
        lookForPrecedent();
    }
	
	public final void setDefaultImage() {
		currentImage.set(new Image(""));
	}
	
	public final void setImageList() {
		
	}
	
	
	/**
	 * @param imagePath path de l'image, pas celui du dossier voulu
	 */
	public final void setPath(String imagePath) {
		try {
		    BufferedWriter writer =new BufferedWriter(new FileWriter(".\\log.txt"));
			writer.write(imagePath);	
		    writer.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		this.path=imagePath.substring(0, imagePath.lastIndexOf("\\")); //path = path of file - file name
		currentImageName=imagePath.substring(imagePath.lastIndexOf("\\")+1);
		
	}
	
	public final ObjectProperty<Image> getCurrentImage() {
		return currentImage;
	}
	/**
	 * find all images of folder and put them in
	 * imageNameList and imageNameArray
	 */
	public void getFileImageList() {
		File dir=new File(path);
		imageNameList=  dir.list(new FilenameFilter() {
						    public boolean accept(File dir, String name) {
						    	String nom=name.toLowerCase();
						        return nom.endsWith(".jpg" ) 
						        		 || nom.endsWith(".png" )
						        		 || nom.endsWith(".gif");
						    }
		}); 
		
		imageNameArray=new ArrayList<String>();
		for(String f:imageNameList) {
			imageNameArray.add(f);
		}
		
		
	}
	
	/**
	 * add all images in a list and search the actual image index
	 */
	/*public void gestionImageList() {
		int i=0;							// give pos of actual image in the folder
		imageNum=-1;
		for(String f:imageNameList) {
			imgList.add(i,new Image(path+"\\"+f));	//ajoute toutes les images du folder dans la liste, brainless et couteux
			if(f==currentImage.getName())
				imageNum=i;
			i++;
		}
		if (imageNum==-1)
			imageNum=0;			
	}*/
	
	/**
	 * search the index of the current image 
	 * and store it in imageNum 
	 */
	public void imageSearch() {
		int i=0;
		for(String f:imageNameList) {
			
			if(f.equals(currentImageName))
				imageNum=i;
			i++;
		}
	}
	
	
	
	
	public void lookForNextImage() {
		if(imageNameArray.size()>imageNum+1)
			nextExists=true;
	}
	
	public void lookForPrecedent() {
		if(imageNum-1>=0 && ! imageNameArray.isEmpty()) 
			precedentExists=true;
	}
	
	/**
	 * will acces next image and load it in currentImage
	 */
	public void getNextImage()  {
		try
		{
			if( imageNameArray.size()>imageNum+1) {
				imageNum+=1;
				setCurrentImage(path+"\\"+imageNameArray.get(imageNum));
			}
		}catch( Exception e){
			System.err.println("next image not found");
		}
	}
	
	
	/**
	 * will access preceding image and load it in currentImage
	 */
	public void getPrecedingImage() {
		try
		{
			if( imageNum-1>=0 && ! imageNameArray.isEmpty()) {
				imageNum-=1;
				setCurrentImage(path+"\\"+imageNameArray.get(imageNum));
			}
		}catch( Exception e){
			System.err.println("precedent image not found");
		}
	}
	
	public final String getPath() {
		return path;
	}
	public final String getImageName() {
		return currentImageName;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		FolderManager fold=new FolderManager(".");

		//fold.setPath(".\\pics\\p4.jpg");
		fold.setPath("C:\\Users\\Xxsafirex\\Desktop\\perso\\images\\.");
		fold.getFileImageList();
		
		//fold.gestionImageList();
		
		FolderManager.printArray(fold.imageNameList);
		//System.out.println(fold.path);
		fold.setCurrentImage(fold.path+"\\Renown.png");
		//fold.gestionImageList();
		System.out.println(fold.currentImageName);
		fold.imageSearch();
		System.out.println(fold.imageNum);
		fold.getNextImage();fold.getNextImage();
		System.out.println(fold.imageNum);
		System.out.println(fold.currentImageName);

		fold.getPrecedingImage();fold.getPrecedingImage();
		System.out.println(fold.imageNum);
		System.out.println(fold.currentImageName);
		
	}
	static public void printArray(String[] a) {
		System.out.println(Arrays.toString(a));
	}
	
}
