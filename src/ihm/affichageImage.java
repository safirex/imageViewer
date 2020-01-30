package ihm;

import javafx.application.Application;
import javafx.scene.Group;				//ajout pour afficher img
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;		// pour editeur de texte
import javafx.scene.image.Image;			// image
import javafx.scene.image.ImageView;		//pour afficher une image
public class affichageImage extends Application {
	//setUserAgentStylesheet(STYLESHEET_CASPIAN);
	@Override
	public void start(Stage premierStage) {
		premierStage.setTitle("Ma première application JavaFX");
		
		
     
         
         
		
		Button _1 = new Button("Haut");
		Button _2 = new Button("gauche");
		Button _3 = new Button("centre");
		Button _4 = new Button("droite");
		Button _5 = new Button("bas");
		Button _11 = new Button("Haut");
		Button _21 = new Button("gauche");
		Button _31 = new Button("centre");
		Button _41 = new Button("droite");
		Button _51 = new Button("bas");
		
		BorderPane pane=new BorderPane();
		
		_3.setMaxHeight(500);
		_3.setMaxWidth(500);
		
		
		_1.setMaxWidth(500);
		_11.setMaxWidth(500);
		_5.setMaxWidth(500);
		_51.setMaxWidth(500);
		
		_2.setMaxHeight(500);
		_21.setMaxHeight(500);
		_4.setMaxHeight(500);
		_41.setMaxHeight(500);
		
		pane.setTop(_1);
		pane.setLeft(_2);
		pane.setCenter(_3);
		pane.setBottom(_5);
		pane.setRight(_4);
	
		BorderPane p2=new BorderPane();
		p2.setTop(_11);
		p2.setLeft(_21);
		p2.setCenter(pane);
		p2.setBottom(_51);
		p2.setRight(_41);
		
         
		Button btnHello = new Button("Bonjour à tous");
		//root.setCenter(btnHello);
		Scene scene = new Scene(p2, 250, 100);
		premierStage.setScene(scene);
		premierStage.initStyle(StageStyle.DECORATED);
		
		
		
		
		
		premierStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}

}
