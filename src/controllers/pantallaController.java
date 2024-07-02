package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;

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
    private ArrayList<String> usuariosActivos;
    private ArrayList<String> usuariosInactivos;
    private Map<Tab, ObservableList<String>> mensajesPrivadosPorTab;

    
    public pantallaController() {
    	listaMensajesGrupales = FXCollections.observableArrayList();
    	mensajesPrivadosPorTab = new HashMap<>();
    	usuariosActivos = new ArrayList<String>();
    	usuariosInactivos = new ArrayList<String>();
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
//    	tabBienvenida.setDisable(false);
//    	tabChatPrivado.setDisable(true);
//    	tabChatGrupal.setDisable(true);
    	tabPanePrincipal.getSelectionModel().select(tabBienvenida);   
    	usuariosActivos.remove(txtUsuarioPrivado.getText());
    	usuariosInactivos.add(txtUsuarioPrivado.getText());
    	cerrarTabs(tabPaneChats);
    	txtIngresarUsuario.clear();
    }
    
    void cerrarTabs(TabPane panel) {
    	tabPaneChats.getTabs().clear();
        mensajesPrivadosPorTab.clear();
    }
    
    @FXML
    void enviarMensajeGrupal(ActionEvent event) {
    	if (!txtMensajeGrupal.getText().equals("")) {
    		listaMensajesGrupales.add(txtUsuarioGrupal.getText() + obtenerHora() + ": " + txtMensajeGrupal.getText());
    		lvChatGrupal.setItems(listaMensajesGrupales);
            txtMensajeGrupal.clear();
        } else {
        	alertaMensajeVacio();
        }
    } 
    
    public ObservableList<String> getListaMensajesGrupales() {
		return listaMensajesGrupales;
	}
    
    public void imprimirPublico(String mensaje) {
    	  listaMensajesGrupales.add(mensaje);
          lvChatGrupal.setItems(listaMensajesGrupales); 
    }

	String obtenerHora() {
    	Calendar c = Calendar.getInstance();
    	int hora = c.get(Calendar.HOUR_OF_DAY);
    	int minuto = c.get(Calendar.MINUTE);
//    	int dia = c.get(Calendar.DAY_OF_MONTH);
//    	int mes = c.get(Calendar.MONTH)+1;
//    	int anio = c.get(Calendar.YEAR);
//    	Date d = new java.util.Date(anio, mes, dia, hora, minuto);
    	String fechaHora = "(" + hora + ":" + minuto + ")";
    	return fechaHora;
    }

    @FXML
    void enviarMensajePrivado(ActionEvent event) {
    	Tab tabActual = tabPaneChats.getSelectionModel().getSelectedItem(); 
    	AnchorPane panel = (AnchorPane)tabActual.getContent();
    	String nombre = tabActual.getId(); //el nombre de la pestaña
    	ListView<String> chatPrivado = null;
        for (javafx.scene.Node node : panel.getChildren()) {
            if (node instanceof ListView) {
                chatPrivado = (ListView<String>) node;
                break;
            }
        }   
        //obtener mensajes para esa pestaña
        ObservableList<String> listaMensajesPrivados = mensajesPrivadosPorTab.get(tabActual);
        if (!txtMensajePrivado.getText().equals("")) {
        	listaMensajesPrivados.add(txtUsuarioPrivado.getText() + obtenerHora() + ": " + txtMensajePrivado.getText());
            chatPrivado.setItems(listaMensajesPrivados); 
            txtMensajePrivado.clear();
        } else {
        	alertaMensajeVacio();
        }      
    }
    
    public void imprimirPrivado(String usuario, String mensaje) {
    	Tab tabABuscar = null;    	
    	for (Tab t: tabPaneChats.getTabs()) {
    		if (t.getId().equals(usuario)) {
    			AnchorPane panel = (AnchorPane)t.getContent();
    	    	ListView<String> chatPrivado = null;
    	        for (javafx.scene.Node node : panel.getChildren()) {
    	            if (node instanceof ListView) {
    	                chatPrivado = (ListView<String>) node;
    	                break;
    	            }
    	        }
    	        ObservableList<String> listaMensajesPrivados = mensajesPrivadosPorTab.get(t);
    	        listaMensajesPrivados.add(mensaje);
                chatPrivado.setItems(listaMensajesPrivados); 
            	break;
            }
    	}
    }
    
    @FXML
    void irAChatPrivado(ActionEvent event) {
    	if (txtIngresarUsuario.getText().equals("")) {
    		alertaUsuarioInvalido();
    	} else if (añadirUsuario(txtIngresarUsuario.getText())) {
    		irAChat();
        	tabPanePrincipal.getSelectionModel().select(tabChatPrivado);        
    	}
    }

    @FXML
    void irAChatGrupal(ActionEvent event) {
    	if (txtIngresarUsuario.getText().equals("")) {
    		alertaUsuarioInvalido();
    	} else if (añadirUsuario(txtIngresarUsuario.getText())) {
    		irAChat();
            tabPanePrincipal.getSelectionModel().select(tabChatGrupal);  
    	} 
    }
    
    void irAChat() {
    	crearChatsPrivados();
		txtUsuarioGrupal.setText(txtIngresarUsuario.getText());
		txtUsuarioPrivado.setText(txtIngresarUsuario.getText());
//    	tabBienvenida.setDisable(true);
//    	tabChatPrivado.setDisable(false);
//    	tabChatGrupal.setDisable(false);    
    }

    void crearChatsPrivados() {
    	for(String usuario: usuariosActivos) {
    		if (!usuario.equals(txtIngresarUsuario.getText())) {
    			agregarChats(usuario);
    		}
    	}
    }
    
    public void agregarChats(String usuario) {
    	Tab nueva = new Tab();
    	Label label = new Label("Chat con " + usuario);
        label.getStyleClass().add("tab-label"); 
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setStyle("-fx-font-weight: 700");
        nueva.setGraphic(label);
    	AnchorPane contenido = new AnchorPane();
    	ListView<String> lv = new ListView<>();
    	nueva.setId(usuario);
    	lv.setPrefSize(535, 327);
    	contenido.getChildren().add(lv);
    	nueva.setContent(contenido);
    	this.tabPaneChats.getTabs().add(nueva);
    
        ObservableList<String> listaMensajesPrivados = FXCollections.observableArrayList();
        mensajesPrivadosPorTab.put(nueva, listaMensajesPrivados);
    }
    
    public void eliminarChat(String usuario) {
    	Tab tabAEliminar = null;
    	
    	for (Tab t: tabPaneChats.getTabs()) {
    		if (t.getId().equals(usuario)) {
    			tabAEliminar = t;
            	break;
    		}
    	}
        this.tabPaneChats.getTabs().remove(tabAEliminar);
        mensajesPrivadosPorTab.remove(tabAEliminar);
    }
    
    boolean añadirUsuario(String usuario) {
    	boolean puedeAgregar = true;
    	if (usuariosActivos.contains(usuario)) puedeAgregar = false;
    		
    	for(String s: usuariosInactivos) {
    		if (s.equals(usuario) && puedeAgregar) {
    			usuariosInactivos.remove(usuario);
    			break;
    		}
    	}
    	
    	if (puedeAgregar) usuariosActivos.add(usuario);
    	else alertaErrorInicioSesion();
    	
    	return puedeAgregar;
    }
    
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String contenido) {
        Alert alert = new Alert(tipo, contenido, ButtonType.CLOSE);
        alert.setTitle(titulo);
        alert.showAndWait();
    }
    
    void alertaUsuarioInvalido() {
    	mostrarAlerta(Alert.AlertType.WARNING, "Nombre de usuario inválido", "Ingrese un nombre de usuario válido.");
    }
    
    void alertaErrorInicioSesion() {
    	mostrarAlerta(Alert.AlertType.WARNING, "Error de inicio de sesión", "El usuario ya está en el sistema.");
    }
    
    void alertaMensajeVacio() {
    	mostrarAlerta(Alert.AlertType.WARNING, "Error de mensaje", "El mensaje que intenta enviar está vacío.");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmdAbrirChatGrupal != null : "fx:id=\"cmdAbrirChatGrupal\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert cmdAbrirChatPrivado != null : "fx:id=\"cmdAbrirChatPrivado\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert cmdCerrarSesion != null : "fx:id=\"cmdCerrarSesion\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert cmdEnviarMensajeGrupal != null : "fx:id=\"cmdEnviarMensajeGrupal\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert cmdEnviarMensajePrivado != null : "fx:id=\"cmdEnviarMensajePrivado\" was not injected: check your FXML file 'pantalla.fxml'.";
        assert lvChatGrupal != null : "fx:id=\"lvChatGrupal\" was not injected: check your FXML file 'pantalla.fxml'.";
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
        
//        tabChatGrupal.setDisable(true);
//        tabChatPrivado.setDisable(true);
        lvChatGrupal.setItems(listaMensajesGrupales);
    }

}
