package ihm;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import code_lecteur.FolderManager;
import javafx.scene.control.MenuBar;
import javafx.stage.FileChooser;

public class MenuController {
	private MenuBar menuBar ;
	
	private FolderManager model;
	
	public MenuController(MenuBar m) {
		menuBar=m;
	}
	
	
	
	public void initModel(FolderManager model) {
        if (this.model != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.model = model ; 
    }
	
	
	public void open() {
        FileChooser chooser = new FileChooser();
        File f=new File(model.getPath());
        chooser.setInitialDirectory(f);
        
        File file = chooser.showOpenDialog(menuBar.getScene().getWindow());
        if (file != null) {
            try {
            	model.setCurrentImage(file.getAbsolutePath());
            } catch (Exception exc) {
                // handle exception...
            }
        }
    }

	public void openDir() {
		try {
			Desktop.getDesktop().open(new File (model.getPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
