package jfxtuto;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.omg.CORBA.Environment;

import com.sun.javafx.scene.input.DragboardHelper;

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
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.DirectoryChooser;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Screen;
import javafx.stage.Stage;  

public class DragDropTest extends Application { 
	  
    @Override 
    public void start(Stage primaryStage) { 
        final Rectangle rectangle = new Rectangle(50, 50, 150, 100); 
        rectangle.setFill(Color.RED); 
      /*  rectangle.setOnDragDetected(mouseEvent -> { 
            System.out.println("DnD detecté."); 
            final Dragboard dragBroard = rectangle.startDragAndDrop(TransferMode.COPY); 
            // Remlissage du contenu. 
            final ClipboardContent content = new ClipboardContent(); 
            // Exporter en tant que texte. 
            content.putString("Un rectangle rouge."); 
            // Exporter en tant que couleur ARGB. 
            DataFormat paintFormat = DataFormat.lookupMimeType(Paint.class.getName()); 
            paintFormat = (paintFormat == null) ? new DataFormat(Paint.class.getName()) : paintFormat; 
            final Color color = (Color) rectangle.getFill(); 
            final int red = (int) (255 * color.getRed()); 
            final int green = (int) (255 * color.getGreen()); 
            final int blue = (int) (255 * color.getBlue()); 
            final int alpha = (int) (255 * color.getOpacity()); 
            final int argb = alpha << 24 | red << 16 | green << 8 | blue << 0; 
            content.put(paintFormat, argb); 
            // Exporter en tant qu'image.        
            final WritableImage capture = rectangle.snapshot(null, null); 
            content.putImage(capture); 
            // 
            dragBroard.setContent(content); 
            mouseEvent.consume();         
        }); */
       
        
        
        
        final Pane root = new Pane(); 
        TextArea txt=new TextArea("test");
        root.getChildren().setAll(rectangle,txt); 
        
        
        
        txt.setOnDragOver(MouseEvent ->{
        	//System.out.println("drag entered");
        });
        txt.setOnDragDropped(MouseEvent ->{
        	System.out.println("drag finished");
        });
        
        root.setOnDragDetected(MouseEvent -> { 
            System.out.println("DnD detecté.");
        });
        
        final Scene scene = new Scene(root, 600, 600); 
        primaryStage.setTitle("Test de DnD"); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    } 
  
    public static void main(String[] args) { 
        launch(args); 
    } 
}