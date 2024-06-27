package controllers;
/**
 * Sample Skeleton for 'pantalla.fxml' Controller Class
 */

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class pantallaController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmdAbrirChatGrupal"
    private Button cmdAbrirChatGrupal; // Value injected by FXMLLoader

    @FXML // fx:id="cmdAbrirChatPrivado"
    private Button cmdAbrirChatPrivado; // Value injected by FXMLLoader

    @FXML // fx:id="cmdCerrarSesion"
    private Button cmdCerrarSesion; // Value injected by FXMLLoader

    @FXML // fx:id="cmdEnviarMensajeGrupal"
    private Button cmdEnviarMensajeGrupal; // Value injected by FXMLLoader

    @FXML // fx:id="cmdEnviarMensajePrivado"
    private Button cmdEnviarMensajePrivado; // Value injected by FXMLLoader
    
    @FXML // fx:id="lvChatGrupal"
    private ListView<String> lvChatGrupal; // Value injected by FXMLLoader

    @FXML // fx:id="lvChatPrivado"
    private ListView<String> lvChatPrivado; // Value injected by FXMLLoader

    @FXML // fx:id="tabPaneChats"
    private TabPane tabPaneChats; // Value injected by FXMLLoader
    
    @FXML // fx:id="tabPanePrincipal"
    private TabPane tabPanePrincipal; // Value injected by FXMLLoader

    @FXML // fx:id="tabBienvenida"
    private Tab tabBienvenida; // Value injected by FXMLLoader

    @FXML // fx:id="tabChatGrupal"
    private Tab tabChatGrupal; // Value injected by FXMLLoader

    @FXML // fx:id="tabChatPrivado"
    private Tab tabChatPrivado; // Value injected by FXMLLoader

    @FXML // fx:id="txtIngresarUsuario"
    private TextField txtIngresarUsuario; // Value injected by FXMLLoader

    @FXML // fx:id="txtMensajeGrupal"
    private TextField txtMensajeGrupal; // Value injected by FXMLLoader

    @FXML // fx:id="txtMensajePrivado"
    private TextField txtMensajePrivado; // Value injected by FXMLLoader

    @FXML // fx:id="txtUsuarioGrupal"
    private TextField txtUsuarioGrupal; // Value injected by FXMLLoader

    @FXML // fx:id="txtUsuarioPrivado"
    private TextField txtUsuarioPrivado; // Value injected by FXMLLoader
    
    private ObservableList<String> listaMensajesGrupales;
    private ObservableList<String> listaMensajesPrivados;
    
    public pantallaController() {
    	listaMensajesGrupales = FXCollections.observableArrayList();
    	listaMensajesPrivados = FXCollections.observableArrayList();
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
    	tabBienvenida.setDisable(false);
    	tabChatPrivado.setDisable(true);
    	tabChatGrupal.setDisable(true);
    	tabPanePrincipal.getSelectionModel().select(tabBienvenida);   
    }

    @FXML
    void enviarMensajeGrupal(ActionEvent event) {
    	Calendar c = Calendar.getInstance();
//    	int dia = c.get(Calendar.DAY_OF_MONTH);
//    	int mes = c.get(Calendar.MONTH)+1;
//    	int anio = c.get(Calendar.YEAR);
    	int hora = c.get(Calendar.HOUR_OF_DAY);
    	int minuto = c.get(Calendar.MINUTE);
        
    	String fechaHora = "(" + hora + ":" + minuto + ")";
    	listaMensajesGrupales.add(txtUsuarioGrupal.getText() + fechaHora + ": " + txtMensajeGrupal.getText());
    }

    @FXML
    void enviarMensajePrivado(ActionEvent event) {
    	Calendar c = Calendar.getInstance();
    	int hora = c.get(Calendar.HOUR_OF_DAY);
    	int minuto = c.get(Calendar.MINUTE);
        
    	String fechaHora = "(" + hora + ":" + minuto + ")";
    	listaMensajesPrivados.add(txtUsuarioPrivado.getText() + fechaHora + ": " + txtMensajePrivado.getText());
    }
    
    @FXML
    void irAChatPrivado(ActionEvent event) {
    	if (txtIngresarUsuario.getText().equals("")) {
    		Alert alert = new Alert(Alert.AlertType.WARNING, "Ingrese un nombre de usuario valido", ButtonType.CLOSE);
            alert.setTitle("Nombre de usuario invalido");
            alert.showAndWait();
    	} else {
    		txtUsuarioGrupal.setText(txtIngresarUsuario.getText());
    		txtUsuarioPrivado.setText(txtIngresarUsuario.getText());
        	tabBienvenida.setDisable(true);
        	tabChatPrivado.setDisable(false);
        	tabChatGrupal.setDisable(false);
        	tabPanePrincipal.getSelectionModel().select(tabChatPrivado);        
    	}
    }
    
    void agregarChats() {
    	Tab nueva = new Tab("Chat con " + txtIngresarUsuario.getText());
    	AnchorPane contenido = new AnchorPane();
    	TextArea txtArea = new TextArea();
    	txtArea.setPrefSize(660, 250);
    	contenido.getChildren().add(txtArea);
    	nueva.setContent(contenido);
    	this.tabPaneChats.getTabs().add(nueva);
    }

    @FXML
    void irAChatGrupal(ActionEvent event) {
    	if (txtIngresarUsuario.getText().equals("")) {
    		Alert alert = new Alert(Alert.AlertType.WARNING, "Ingrese un nombre de usuario valido", ButtonType.CLOSE);
            alert.setTitle("Nombre de usuario invalido");
            alert.showAndWait();
    	} else {
    		txtUsuarioGrupal.setText(txtIngresarUsuario.getText());
    		txtUsuarioPrivado.setText(txtIngresarUsuario.getText());
        	tabBienvenida.setDisable(true);
        	tabChatPrivado.setDisable(false);
        	tabChatGrupal.setDisable(false);    
            tabPanePrincipal.getSelectionModel().select(tabChatGrupal);  
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmdAbrirChatGrupal != null : "fx:id=\"cmdAbrirChatGrupal\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert cmdAbrirChatPrivado != null : "fx:id=\"cmdAbrirChatPrivado\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert cmdCerrarSesion != null : "fx:id=\"cmdCerrarSesion\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert cmdEnviarMensajeGrupal != null : "fx:id=\"cmdEnviarMensajeGrupal\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert cmdEnviarMensajePrivado != null : "fx:id=\"cmdEnviarMensajePrivado\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert lvChatGrupal != null : "fx:id=\"lvChatGrupal\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert lvChatPrivado != null : "fx:id=\"lvChatPrivado\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert tabBienvenida != null : "fx:id=\"tabBienvenida\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert tabChatGrupal != null : "fx:id=\"tabChatGrupal\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert tabChatPrivado != null : "fx:id=\"tabChatPrivado\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert tabPaneChats != null : "fx:id=\"tabPaneChats\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert tabPanePrincipal != null : "fx:id=\"tabPanePrincipal\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert txtIngresarUsuario != null : "fx:id=\"txtIngresarUsuario\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert txtMensajeGrupal != null : "fx:id=\"txtMensajeGrupal\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert txtMensajePrivado != null : "fx:id=\"txtMensajePrivado\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert txtUsuarioGrupal != null : "fx:id=\"txtUsuarioGrupal\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert txtUsuarioPrivado != null : "fx:id=\"txtUsuarioPrivado\" was not injected: check your FXML file 'pantalla.fxml'.";
        
        tabChatGrupal.setDisable(true);
        tabChatPrivado.setDisable(true);
        lvChatGrupal.setItems(listaMensajesGrupales);
        lvChatPrivado.setItems(listaMensajesPrivados);
    }

}
