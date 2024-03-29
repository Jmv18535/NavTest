/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import poiupv.InicioDeSesionController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Session;
import model.User;

/**
 * FXML Controller class
 *
 * @author javie
 */
public class PantallaPrincipalController implements Initializable {

    @FXML
    private Button listaDeProblemas;
    @FXML
    private Button problemaAleatorio;
    @FXML
    private Button mostrarResultados;
    @FXML
    private Button modPerfil;
    @FXML
    private Button cerrarSesion;

    InicioDeSesionController inicio= new InicioDeSesionController();
    @FXML
    private ImageView fotoUsuario;
    @FXML
    private Label nomUsuario;
    
    User usuario;
    @FXML
    private VBox boxMenus;
    @FXML
    private VBox boxImagen;
    @FXML
    private HBox hBox1;
    @FXML
    private HBox hBox2;
    @FXML
    private HBox hBox3;
    @FXML
    private VBox vBoxAvatar;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        usuario = inicio.getUser();
        nomUsuario.setText(usuario.getNickName());
        fotoUsuario.setImage(usuario.getAvatar());
        
        nomUsuario.setId("nomUsuario");
        vBoxAvatar.setId("Avatar");
    }    

    @FXML
   
    private void pulsadoListaDeProblemas(ActionEvent event) throws IOException {
        Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("/poiupv/FXML/ListaProblemas.fxml"));
         
        Scene inicioDeSesion = new Scene(inicioSesionParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(inicioDeSesion);
      
        ventana.setResizable(true);
        ventana.setOnCloseRequest(event1 -> {
            
                Session session= new Session(LocalDateTime.now(),inicio.getAciertos(),inicio.getFallos());
     
                try {
                    inicio.anadirSesion(session);
                } catch (NavegacionDAOException ex) {
                    Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        ventana.setResizable(false);
        ventana.centerOnScreen();
        ventana.show();
    }

    @FXML
    private void pulsadoProblemaAleatorio(ActionEvent event) throws IOException {
       
        Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("/poiupv/FXML/ProblemaAleatorio.fxml"));
         
        Scene inicioDeSesion = new Scene(inicioSesionParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(inicioDeSesion);
      
        ventana.setResizable(true);
        ventana.setMaximized(true);
        ventana.setOnCloseRequest(event1 -> {
            
                Session session= new Session(LocalDateTime.now(),inicio.getAciertos(),inicio.getFallos());
     
                try {
                    inicio.anadirSesion(session);
                } catch (NavegacionDAOException ex) {
                    Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        ventana.show();
    }

    @FXML
    private void pulsadoMostrarResultados(ActionEvent event) throws IOException, NavegacionDAOException {
        Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("/poiupv/FXML/MostrarResultados.fxml"));
       //FXMLLoader loader = new FXMLLoader(getClass().getResource("MostrarResultados.fxml"));
       Scene inicioDeSesion = new Scene(inicioSesionParent);
       // loader.setController(new inicioDeSesion("/poiupv/FXML/MostrarResultados.fxml"));
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(inicioDeSesion);
        ventana.setResizable(false);
        ventana.setOnCloseRequest(event1 -> {
            
                Session session= new Session(LocalDateTime.now(),inicio.getAciertos(),inicio.getFallos());
     
                try {
                    inicio.anadirSesion(session);
                } catch (NavegacionDAOException ex) {
                    Logger.getLogger(PantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        ventana.centerOnScreen();
        ventana.show();
    }

    @FXML
    private void pulsadoModPerfil(ActionEvent event) throws IOException {
        Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("/poiupv/FXML/ModificarPerfil.fxml"));
         
        Scene inicioDeSesion = new Scene(inicioSesionParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(inicioDeSesion);
        ventana.setResizable(false);
        ventana.centerOnScreen();
        ventana.show();
    }

    @FXML
    private void pulsadoCerrarSesion(ActionEvent event) throws IOException, NavegacionDAOException {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.initStyle(StageStyle.UTILITY);
        alerta.setTitle("Cerrar Sesión");
        alerta.setHeaderText("¿Estás segur@ de que quieres cerrar la sesión?");
        
        
        Optional <ButtonType> result = alerta.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("Aceptar");
            
            Session session= new Session(LocalDateTime.now(),inicio.getAciertos(),inicio.getFallos());
     
            inicio.anadirSesion(session);
            
            
            
            Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("/poiupv/FXML/PantallaInicial.fxml"));
         
            Scene inicioDeSesion = new Scene(inicioSesionParent);
        
            Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
            ventana.setScene(inicioDeSesion);
            ventana.setResizable(false);
            ventana.show();
            
        } else {
            System.out.println("Cancelar");
        }
    }
    
    
}
 
