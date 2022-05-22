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
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Navegacion;
import model.Problem;
import model.Session;
import model.User;
import static model.User.checkNickName;
/**
 * FXML Controller class
 *
 * @author javie
 */
public class InicioDeSesionController implements Initializable {

    @FXML
    private TextArea textoError;
    @FXML
    private TextField nomUsuario;
    @FXML
    private PasswordField contraUsuario;
    @FXML
    private TextField textoContraseña;
    @FXML
    private CheckBox checkBox;
    
    private static int aciertos;
    private static int fallos;
    
    public static User user;
    private Label titulo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        textoContraseña.managedProperty().bind(checkBox.selectedProperty());
        textoContraseña.visibleProperty().bind(checkBox.selectedProperty());
        contraUsuario.managedProperty().bind(checkBox.selectedProperty().not());
        contraUsuario.visibleProperty().bind(checkBox.selectedProperty().not());
        textoContraseña.textProperty().bindBidirectional(contraUsuario.textProperty());
        
        aciertos=0;
        fallos=0;
        
        
    }    

    @FXML
    private void PulsadoCancelar(ActionEvent event) throws IOException {
        Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("/poiupv/FXML/PantallaInicial.fxml"));
         
        Scene inicioDeSesion = new Scene(inicioSesionParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(inicioDeSesion);
        ventana.setResizable(false);
        ventana.show();
    }

    @FXML
    private void PulsadoEntrar(ActionEvent event) throws IOException, NavegacionDAOException {
        

        String nickname = nomUsuario.getText();
        String vacio="";
        String password = contraUsuario.getText();
        Navegacion navegacion = Navegacion.getSingletonNavegacion();
        user = navegacion.loginUser(nickname, password);
        if( user == null){
            if(nickname.equals(vacio)){
                textoError.setText("Ingresa usuario");
                textoError.visibleProperty().set(true);
                return;
            }
            if (!checkNickName(nickname)) {                
                textoError.setText("El usuario no existe");
                textoError.visibleProperty().set(true);
                return;
            }
            if(password.equals(vacio)){
                textoError.setText("Ingresa contraseña");
                textoError.visibleProperty().set(true);
                return;
            }
            else{
                textoError.setText("Contraseña Incorrecta");
                textoError.visibleProperty().set(true);
                return;  
             }
        }
        
        
        
        Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("/poiupv/FXML/PantallaPrincipal.fxml"));
         
        Scene inicioDeSesion = new Scene(inicioSesionParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(inicioDeSesion);
        ventana.setResizable(true);
        ventana.setOnCloseRequest(event1 -> {
        System.out.println("me cago  en todo");
        // Save file
        });
        ventana.show();
    }
    
    public User getUser(){
        return user;
    }
        
    public void setUser(String email,String password,Image avatar,LocalDate birthdate) throws NavegacionDAOException{
        user.setEmail(email);
        user.setAvatar(avatar);
        user.setBirthdate(birthdate);
        user.setPassword(password);
    }
    public void anadirSesion(Session sesion) throws NavegacionDAOException{
        user.addSession(sesion);
    }
    public List<Session> getSesion(){
        return user.getSessions();
    }
    public int getAciertos(){
        return aciertos;
    }
    public int getFallos(){
        return fallos;
    }
    public void aumentoFallos(){
        fallos++;
    }
    public void aumentoAciertos(){
        aciertos++;
    }
}
