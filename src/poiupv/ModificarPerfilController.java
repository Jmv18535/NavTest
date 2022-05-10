/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Navegacion;
import model.User;
import poiupv.InicioDeSesionController;
/**
 * FXML Controller class
 *
 * @author javie
 */
public class ModificarPerfilController implements Initializable {

    @FXML
    private TextArea mensajeError;
    @FXML
    private Button cancelarRegistro;
    @FXML
    private Button registrarse;
    @FXML
    private TextField nombreUsuario;
    @FXML
    private Label falloUsuario;
    @FXML
    private TextField correoUsuario;
    @FXML
    private Label falloEmail;
    @FXML
    private TextField textoContraseña;
    @FXML
    private PasswordField contraUsuario;
    @FXML
    private Label falloPassword;
    @FXML
    private CheckBox checkBox;
    @FXML
    private DatePicker edadUsuario;
    @FXML
    private Label falloFecha;
    @FXML
    private ImageView avatarElegido;
    
    private User usuario;
    
    InicioDeSesionController inicio= new InicioDeSesionController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Navegacion navegacion;
        try {
            navegacion = Navegacion.getSingletonNavegacion();
            usuario=inicio.getUser();
            nombreUsuario.setText(usuario.getNickName());
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(ModificarPerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void cancelEvent(ActionEvent event) {
    }

    @FXML
    private void registEvent(ActionEvent event) {
    }

    @FXML
    private void seleccionarAvatar(MouseEvent event) {
    }

    @FXML
    private void pulsarAvatar1(ActionEvent event) {
    }

    @FXML
    private void pulsarAvatar2(ActionEvent event) {
    }

    @FXML
    private void pulsarAvatarArchivo(ActionEvent event) {
    }

    @FXML
    private void pulsarAvatar3(ActionEvent event) {
    }

    @FXML
    private void pulsarAvatar4(ActionEvent event) {
    }
    
}
