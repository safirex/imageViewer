package ihm;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.sun.javafx.scene.SceneUtils;

import code_lecteur.FolderManager;
import controleur.Controleur;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Screen;
import javafx.stage.Stage;  

/**
 * @author Xxsafirex
 */
public class ImageExample extends Application {  

	Boolean readerMode=false;
	FolderManager manager;
	ImageView imageView;

	double imgViewPreReaderMode=0;
	@Override 
	public void start(Stage stage) throws FileNotFoundException { 



		//Controleur controleur=new Controleur();

		manager=new FolderManager();

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
		Menu readerModeMenu= new Menu("Manga Mode");

		MenuItem open=new MenuItem("open");
		MenuItem openDir=new MenuItem("open directory");
		MenuItem save=new MenuItem("Save");
		MenuItem saveas=new MenuItem("Save as");

		MenuItem mangaMode=new MenuItem("Manga");

		readerModeMenu.getItems().add(mangaMode);
		file.setMnemonicParsing(false);				//ne met pas de separation entre les items
		file.getItems().addAll(/*New,*/	open,openDir,save,saveas);

		double taskbarheight = Toolkit.getDefaultToolkit().getScreenSize().height 
				- GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().getHeight();

		menubar.getMenus().addAll(file,edit,acces,help,readerModeMenu);	




		Event initModeEvent=new Event(Event.ANY);
		Event.fireEvent(mangaMode, initModeEvent);


		Popup leftClick= new Popup();
		leftClick.setHideOnEscape(true);
		PopupWindow popupWindow = new PopupWindow() {
		};



		//imageView settings
		//Image image = new Image(new FileInputStream(".\\pics\\p4.jpg"));  
		imageView = new ImageView();
		//Setting the preserve ratio of the image view 
		imageView.setPreserveRatio(true);  
		imageView.fitWidthProperty().bind(stage.widthProperty().subtract(taskbarheight));
		imageView.fitHeightProperty().bind(scene.heightProperty().subtract(taskbarheight));
		imageView.setOnMouseClicked(new EventHandler<MouseEvent>(){@Override public void handle(MouseEvent event) {
			if (event.getButton()==MouseButton.SECONDARY)
			{
				//stack.getChildren().add(popupWindow);
				popupWindow.setAnchorX(event.getX());
				popupWindow.setAnchorY(event.getY());
				System.out.println("left clicked");
				popupWindow.show(stage);
				popupWindow.setX(50);
				popupWindow.setY(50);
			}
		}});

		mangaMode.setOnAction(event ->{

			System.out.println("changing mode to "+!readerMode);
			if(!readerMode) {
				imgViewPreReaderMode=imageView.getTranslateY();
				imageView.fitHeightProperty().bind(scene.heightProperty().add(
						Math.min(imageView.getImage().getHeight()/2,width-scene.getWidth()*0.1*2)));

				readerMode=true;
			}
			else	{
				imageView.fitHeightProperty().bind(scene.heightProperty().subtract(taskbarheight));
				imageView.setTranslateY(menubar.getHeight()/2);

				readerMode=false;
				imageView.setLayoutY(8);
			}
		});

		//scroll event
		p2.setOnScroll(event->{
			
			if(readerMode) {
				
				
				Image tmp=imageView.getImage();
				double coef=(imageView.fitHeightProperty().doubleValue()/imageView.fitWidthProperty().doubleValue());
				System.out.println("coef imgv "+coef);
				double scrollY=event.getDeltaY();
				double actualY=imageView.getTranslateY();
				System.out.println(actualY+scrollY);
				double finalTransformValue=
					Math.max(
						Math.min(taskbarheight,actualY+scrollY),
						-(tmp.getHeight()/coef)
						);
				
				System.out.println("tail img "+imageView.getImage().getHeight());
				imageView.setTranslateY(finalTransformValue);
				menubar.toFront();
			}
		});



		ToolBar tb=new ToolBar();



		// imageview's left button settings and listener
		Button gauche = new Button("<");
		gauche.setMnemonicParsing(false);
		gauche.setPrefWidth(scene.getWidth()*0.1);
		gauche.setPrefHeight(height);
		gauche.setOpacity(0); //rend le gauche invisible
		//gauche.setStyle("myButton");
		gauche.prefHeightProperty().bind(stack.heightProperty());


		//Example d'action on click
		gauche.setOnMouseClicked(new EventHandler<MouseEvent>(){@Override public void handle(MouseEvent event) {
			//imageView.getImage().cancel();
			manager.getPrecedingImage();
			stage.setTitle(manager.getImageName());	
			if(readerMode)
				imageView.setTranslateY(taskbarheight);	}});
		gauche.setOnMouseEntered((new EventHandler<MouseEvent>(){@Override public void handle(MouseEvent event) {
			gauche.setOpacity(30);}}));
		gauche.setOnMouseExited((new EventHandler<MouseEvent>(){@Override public void handle(MouseEvent event) {
			gauche.setOpacity(0);	}}));

		//gauche.setStyle("-fx-background-color : #ffaadd;");=


		// imageview's right button settings and listener
		Button droite = new Button(">");
		droite.setMnemonicParsing(false);
		droite.setPrefWidth(scene.getWidth()*0.1);
		droite.setPrefHeight(height);
		droite.setOpacity(0);
		//Example d'action on click
		droite.setOnMouseClicked(new EventHandler<MouseEvent>(){@Override public void handle(MouseEvent event) {
			manager.getNextImage();
			stage.setTitle(manager.getImageName());
			if(readerMode)
				imageView.setTranslateY(taskbarheight);}});

		droite.setOnMouseEntered(event->{
			droite.setOpacity(30);
		});
		
		droite.setOnMouseExited(event-> {
			droite.setOpacity(0);
			
		});








		//setting of stackpane (containing imageview and both buttons)
		stack.getChildren().addAll(imageView,droite,gauche);
		StackPane.setAlignment(imageView,Pos.CENTER);
		StackPane.setAlignment(gauche, Pos.CENTER_LEFT);
		StackPane.setAlignment(droite, Pos.TOP_RIGHT);
		stack.autosize();
		stack.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		//Group topGroup=new Group(tb,menubar);

		p2.setTop(menubar);
		p2.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		p2.setCenter(stack);
		p2.prefWidthProperty().bind(scene.widthProperty()); //put borderpane across all the screen
		p2.prefHeightProperty().bind(scene.heightProperty());


		p2.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {

				// si la source n'est pas la target
				if (event.getGestureSource() != p2
						&& event.getDragboard().hasFiles()) {
					// allow for both copying and moving, whatever user chooses 
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});

		p2.setOnDragDropped(new EventHandler<DragEvent >() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				
				
				
				if (db.hasFiles()) {
					String filename=db.getFiles().toString();
					File file=db.getFiles().get(0);
					if(file.isFile() && file.canRead()) {
					try {
						System.out.println(file.getAbsolutePath());
						manager.setCurrentImage(file.getAbsolutePath());
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						manager.getPrecedingImage();
					}
					}
					else if(file.isDirectory()) {
						try {
							manager.setCurrentFolder(file.getAbsolutePath());
						} catch (Exception e2) {
							try {
								manager.setCurrentFolder(file.getCanonicalPath());
							}catch(Exception e) {
								
							}
							// TODO: handle exception
						}
					}


					//dropped.setText(db.getFiles().toString());
					success = true;
				}
				/* let the source know whether the string was successfully 
				 * transferred and used */
				event.setDropCompleted(success);
				event.consume();
			}
		});


		MenuController menuC=new MenuController(menubar);
		open.setOnAction(event->{
			menuC.open();
		});
		openDir.setOnAction(event ->{
			menuC.openDir();
		});


		menuC.initModel(manager);	//permit communication between the menu and the controler/ folder manager

		//System.out.println("np"); this one works
		//link the immageView of jfx with the image of folderManager
		Bindings.bindBidirectional(imageView.imageProperty(),manager.getCurrentImage());







		//Displaying the contents of the stage 
		stage.show(); 
		//imgViewPreReaderMode=imageView.getTranslateY();
		System.out.println("tranY "+imageView.getTranslateY());
		System.out.println(imageView.getLayoutY());
		System.out.println(imageView.getY());

	}  
	public static void main(String args[]) { 

		if(args.length>0) {

			FolderManager.appArgs=args[0];

		}
		launch(); 
	} 



}
