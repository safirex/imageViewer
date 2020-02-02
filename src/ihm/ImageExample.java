package ihm;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream; 
import java.io.FileNotFoundException;

import code_lecteur.FolderManager;
import controleur.Controleur;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Screen;
import javafx.stage.Stage;  

public class ImageExample extends Application {  
	/*	
	 * @author: safirex
	 * 
	 * 
	 * 
	 */



	@Override 
	public void start(Stage stage) throws FileNotFoundException { 
		
		
		
		
		
		//Controleur controleur=new Controleur();

		FolderManager manager=new FolderManager();
		
		//String css = this.getClass().getResource(".\\src\\css\\btn.css").toExternalForm(); 
		
		
		//set the stage to max sized window
		stage.setMaximized(true);
		BorderPane p2=new BorderPane();
		//p2.setMinSize(stage.getWidth(), stage.getHeight());
		

		int height=(int)	Screen.getPrimary().getBounds().getHeight();
		int width=(int) 	Screen.getPrimary().getBounds().getWidth(); 
		StackPane stack=new StackPane();

		//Creating a Group object  
		Group root = new Group(p2); 
		
		
		//Creating a scene object 
		Scene scene = new Scene(root, height, width);
		//scene.getStylesheets().add(css);

		//Setting title to the Stage 
		stage.setTitle("Loading an image");  

		//Adding scene to the stage 
		stage.setScene(scene);


		//top screen menuBar & menu settings. listeners are at end of file
		MenuBar menubar=new MenuBar();

		Menu edit=new Menu("Edit");
		Menu help =new Menu("Help");
		Menu file=new Menu("File");
		Menu acces=new Menu("Rapid Access");
		
		MenuItem open=new MenuItem("open");
		MenuItem openDir=new MenuItem("open directory");
		MenuItem save=new MenuItem("Save");
		MenuItem saveas=new MenuItem("Save as");
		
		file.setMnemonicParsing(false);				//ne met pas de separation entre les items
		file.getItems().addAll(/*New,*/	open,openDir,save,saveas);
		
		menubar.getMenus().addAll(file,edit,acces,help);	
		
		
		
		
		
		
		double taskbarheight = Toolkit.getDefaultToolkit().getScreenSize().height 
				- GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().getHeight();
		
		
		Popup leftClick= new Popup();
		leftClick.setHideOnEscape(true);
		PopupWindow popupWindow = new PopupWindow() {
		};
		
		
		
		//imageView settings
		Image image = new Image(new FileInputStream(".\\pics\\p4.jpg"));  
		ImageView imageView = new ImageView(image);
		//Setting the preserve ratio of the image view 
		imageView.setPreserveRatio(true);  
		imageView.fitWidthProperty().bind(stage.widthProperty());
		imageView.fitHeightProperty().bind(scene.heightProperty().subtract(taskbarheight));
		imageView.setOnMouseClicked(new EventHandler<MouseEvent>(){@Override public void handle(MouseEvent event) {
			if (event.getButton()==MouseButton.SECONDARY)
			{
				//stack.getChildren().add(popupWindow);
				popupWindow.setAnchorX(event.getX());
				popupWindow.setAnchorY(event.getY());
			}
		}});
		
		
		
		
		
		
		
		
		
		
		// imageview's left button settings and listener
		Button gauche = new Button("<");
		gauche.setMnemonicParsing(false);
		gauche.setPrefWidth(width*0.1);
		gauche.setPrefHeight(height);
		gauche.setOpacity(0); //rend le gauche invisible
		//gauche.setStyle("myButton");
		gauche.prefHeightProperty().bind(stack.heightProperty());
		
		
		//Example d'action on click
		gauche.setOnMouseClicked(new EventHandler<MouseEvent>(){@Override public void handle(MouseEvent event) {
				manager.getPrecedingImage();
				stage.setTitle(manager.getImageName());
		}});
		gauche.setOnMouseEntered((new EventHandler<MouseEvent>(){@Override public void handle(MouseEvent event) {
			gauche.setOpacity(100);
	}}));
		gauche.setOnMouseExited((new EventHandler<MouseEvent>(){@Override public void handle(MouseEvent event) {
			gauche.setOpacity(0);
	}}));

		//gauche.setStyle("-fx-background-color : #ffaadd;");=
		
		
		// imageview's right button settings and listener
		Button droite = new Button(">");
		droite.setMnemonicParsing(false);
		droite.setPrefWidth(width*0.1);
		droite.setPrefHeight(height);
		droite.setOpacity(0);
		//Example d'action on click
		droite.setOnMouseClicked(new EventHandler<MouseEvent>(){@Override public void handle(MouseEvent event) {
				manager.getNextImage();
				stage.setTitle(manager.getImageName());}});
		
		droite.setOnMouseEntered((new EventHandler<MouseEvent>(){@Override public void handle(MouseEvent event) {
			droite.setOpacity(100);}}));
		droite.setOnMouseExited((new EventHandler<MouseEvent>(){@Override public void handle(MouseEvent event) {
			droite.setOpacity(0);}}));
				
		/*
		AnchorPane anchor=new AnchorPane();
		AnchorPane.setLeftAnchor(droite, 0.0);
		anchor.getChildren().addAll(imageView,droite,gauche);
		*/
		
		

		
		
		
		
		
		//setting of stackpane (containing imageview and both buttons)
		stack.getChildren().addAll(imageView,droite,gauche);
		StackPane.setAlignment(imageView,Pos.CENTER);
		StackPane.setAlignment(gauche, Pos.CENTER_LEFT);
		StackPane.setAlignment(droite, Pos.TOP_RIGHT);
		stack.autosize();
		stack.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		
		
	
		
		
		p2.setTop(menubar);
		p2.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		p2.setCenter(stack);
		p2.prefWidthProperty().bind(scene.widthProperty()); //put borderpane across all the screen
		p2.prefHeightProperty().bind(scene.heightProperty());
		

		MenuController menuC=new MenuController(menubar);
		open.setOnAction(new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent event) {
				menuC.open();
		}});
		openDir.setOnAction(new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent event) {
				menuC.openDir();
		}});
		
		
		menuC.initModel(manager);	//permit communication between the menu and the controler/ folder manager
		
		//link the immageView of jfx with the image of folderManager
		Bindings.bindBidirectional(imageView.imageProperty(),manager.getCurrentImage());
		
		
		//Displaying the contents of the stage 
		stage.show(); 
	}  
	public static void main(String args[]) { 
		
		if(args.length>0)
			FolderManager.appArgs=args[0];
		launch(); 
	} 
}
