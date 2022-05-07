/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Navegacion;
import model.User;
import static model.User.checkEmail;
import static model.User.checkPassword;
import java.time.DayOfWeek;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

        
/**
 * FXML Controller class
 *
 * @author javie
 */
public class RegistroController implements Initializable {

    @FXML
    private TextField nombreUsuario;
    @FXML
    private TextField correoUsuario;
    @FXML
    private PasswordField contraUsuario;
    @FXML
    DatePicker edadUsuario=new DatePicker(LocalDate.now());
    @FXML
    private ImageView avatar1;
    @FXML
    private ImageView avatar2;
    @FXML
    private ImageView avatar3;
    @FXML
    private Button cancelarRegistro;
    @FXML
    private Button registrarse;
    @FXML
    private ImageView avatar31;
    @FXML
    private ImageView avatar311;
    @FXML
    private ImageView avatarElegido;
    private Text errorUsuario;
   
    @FXML
    private TextArea mensajeError;
    @FXML
    private Label falloUsuario;
    @FXML
    private Label falloEmail;
    @FXML
    private Label falloPassword;
    
    @FXML
    private Label falloFecha;
        
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    }    

    @FXML
    private void cancelEvent(ActionEvent event) throws IOException{
        Parent cancelarParent = FXMLLoader.load(getClass().getResource("PantallaInicial.fxml"));
         
        Scene menuInicio = new Scene(cancelarParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(menuInicio);
        ventana.setResizable(false);
        ventana.show();
        
    }
    

    @FXML
    private void registEvent(ActionEvent event) {
        try {
            
            Navegacion navegacion = Navegacion.getSingletonNavegacion();
            //Nombre de usuario
            
            String nickname = nombreUsuario.getText();
            if (nickname.equalsIgnoreCase("")) {
                falloUsuario.visibleProperty().set(true);
                mensajeError.setText("El campo usuario esta vacio");
                mensajeError.visibleProperty().set(true);
                return;
            } else {
                falloUsuario.visibleProperty().set(false);
                mensajeError.visibleProperty().set(false);
            }
            if(navegacion.exitsNickName(nickname)){
                falloUsuario.visibleProperty().set(true);
                mensajeError.setText("Ese nombre de usuario ya esta escogido");
                mensajeError.visibleProperty().set(true);
                return;
            }else {
                falloUsuario.visibleProperty().set(false);
                mensajeError.visibleProperty().set(false);
            }
            
            //Correo electronico
            
            String email = correoUsuario.getText();
            if (!checkEmail(email)) {
                falloEmail.visibleProperty().set(true);
                mensajeError.setText("El campo email no cumple el formato");
                mensajeError.visibleProperty().set(true);
                return;
            } else {
                falloEmail.visibleProperty().set(false);
                mensajeError.visibleProperty().set(false);
            }
            
            //Contraseña
            
            String password = contraUsuario.getText();
            if (!checkPassword(password)) {
                falloPassword.visibleProperty().set(true);
                mensajeError.setText("La contraseña debe tener de 8 a 20 caracteres con un@: \n"
                        + "Mayuscula, miniscula, digito, caracter especial(!@#~€)");
                mensajeError.visibleProperty().set(true);
                return;
            } else {
                falloPassword.visibleProperty().set(false);
                mensajeError.visibleProperty().set(false);
            }
            
            //Fecha nacimiento
            
            LocalDate birthdate = edadUsuario.getValue();
            LocalDate edadMinima = LocalDate.now().minusYears(16);
            if (birthdate.isAfter(edadMinima)) {
                falloFecha.visibleProperty().set(true);
                mensajeError.setText("El usuario debe tener mas de 16 años");
                mensajeError.visibleProperty().set(true);
                return;
            } else {
                falloFecha.visibleProperty().set(false);
                mensajeError.visibleProperty().set(false);
            }
            
            //Avatar
            Image avatar = avatar1.getImage();
            
            //Creacion Usuario
            User resultado = navegacion.registerUser(nickname, email, password, avatar, birthdate);
            mensajeError.setText("Usuario registrado correctamente");
            try {
                wait(5000);
                //Cambio a escena principal
                
                
                // TODO
            } catch (InterruptedException ex) {
                Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }

    @FXML
    private void seleccionarAvatar(MouseEvent event) {
        
    }

    @FXML
    private void seleccionDeAvatar(ActionEvent event) {
    }
}
