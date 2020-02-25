package ihm;
//Program to create a context menu and add it to label 
//and associate the context menu with window event listener 
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.stage.WindowEvent; 
import javafx.event.EventHandler; 
import javafx.collections.*; 
import javafx.stage.Stage; 
import javafx.scene.text.Text.*; 
import javafx.scene.paint.*; 
import javafx.scene.text.*; 
public class contextMenu_1 extends Application { 
	// labels 
	Label label; 

	// launch the application 
	public void start(Stage stage) 
	{ 
		// set title for the stage 
		stage.setTitle("creating contextMenu "); 

		// create a label 
		Label label1 = new Label("This is a ContextMenu example "); 

		// create a menu 
		ContextMenu contextMenu = new ContextMenu(); 

		// create menuitems 
		MenuItem menuItem1 = new MenuItem("menu item 1"); 
		MenuItem menuItem2 = new MenuItem("menu item 2"); 
		MenuItem menuItem3 = new MenuItem("menu item 3"); 

		// add menu items to menu 
		contextMenu.getItems().add(menuItem1); 
		contextMenu.getItems().add(menuItem2); 
		contextMenu.getItems().add(menuItem3); 

		// label to display events 
		Label label = new Label("context menu hidden"); 

		// create window event 
		EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() { 
			public void handle(WindowEvent e) 
			{ 
				if (contextMenu.isShowing()) 
					label.setText("context menu showing"); 
				else
					label.setText("context menu hidden"); 
			} 
		}; 

		// add event 
		contextMenu.setOnShowing(event); 
		contextMenu.setOnHiding(event); 

		// create a tilepane 
		TilePane tilePane = new TilePane(label1); 

		tilePane.getChildren().add(label); 

		// setContextMenu to label 
		label.setContextMenu(contextMenu); 

		// create a scene 
		Scene sc = new Scene(tilePane, 200, 200); 

		// set the scene 
		stage.setScene(sc); 

		stage.show(); 
	} 

	public static void main(String args[]) 
	{ 
		// launch the application 
		launch(args); 
	} 
} 
