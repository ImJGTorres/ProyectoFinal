package views;

import java.util.Scanner;

import controllers.pantallaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pantalla.fxml"));
            Parent root = loader.load();
            // Crear la escena con el contenido cargado
            Scene scene = new Scene(root);
            // Configurar el escenario (stage)
            primaryStage.setTitle("BorisPonganos5:D");
            primaryStage.setScene(scene);
            primaryStage.show();
//            pantallaController pantalla = new pantallaController(); 		
//    		while(primaryStage != null) {
//    			System.out.println(pantalla.getListaMensajesGrupales());
//    		}        
        } catch (Exception e) {
            e.printStackTrace();
        }	
	}
}
