/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    @FXML
    private AnchorPane anchorPane;

    InicioDeSesionController inicio= new InicioDeSesionController();
    @FXML
    private ImageView fotoUsuario;
    @FXML
    private Label nomUsuario;
    
    User usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        usuario = inicio.getUser();
        nomUsuario.setText(usuario.getNickName());
        fotoUsuario.setImage(usuario.getAvatar());
    }    

    @FXML
    private void pulsadoListaDeProblemas(ActionEvent event) throws IOException {
       Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("ProblemaAleatorio.fxml"));
         
        Scene inicioDeSesion = new Scene(inicioSesionParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(inicioDeSesion);
        ventana.setResizable(true);
        ventana.show();
    }

    @FXML
    private void pulsadoProblemaAleatorio(ActionEvent event) throws IOException {
        Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("ProblemaAleatorio.fxml"));
         
        Scene inicioDeSesion = new Scene(inicioSesionParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(inicioDeSesion);
        ventana.setResizable(true);
        ventana.show();
    }

    @FXML
    private void pulsadoMostrarResultados(ActionEvent event) throws IOException {
        Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("ProblemaAleatorio.fxml"));
         
        Scene inicioDeSesion = new Scene(inicioSesionParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(inicioDeSesion);
        ventana.setResizable(true);
        ventana.show();
    }

    @FXML
    private void pulsadoModPerfil(ActionEvent event) throws IOException {
        Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("ModificarPerfil.fxml"));
         
        Scene inicioDeSesion = new Scene(inicioSesionParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(inicioDeSesion);
        ventana.setResizable(true);
        ventana.show();
    }

    @FXML
    private void pulsadoCerrarSesion(ActionEvent event) throws IOException {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.initStyle(StageStyle.UTILITY);
        alerta.setTitle("Cerrar Sesión");
        alerta.setHeaderText("¿Estás segur@ de que quieres cerrar la sesión?");
        
        
        Optional <ButtonType> result = alerta.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("Aceptar");
            Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("PantallaInicial.fxml"));
         
            Scene inicioDeSesion = new Scene(inicioSesionParent);
        
            Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
            ventana.setScene(inicioDeSesion);
            ventana.setResizable(true);
            ventana.show();
            
        } else {
            System.out.println("Cancelar");
        }
    }
    
}
 /*hola MUNDO*/
