/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Navegacion;
import model.User;
import static model.User.checkEmail;
import static model.User.checkNickName;
import static model.User.checkPassword;
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
    
    private final LocalDate edadMinima = LocalDate.now().minusYears(16);
    
    InicioDeSesionController inicio= new InicioDeSesionController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Bind del TextLabel de la contraseña. Para verla y no verla
        textoContraseña.managedProperty().bind(checkBox.selectedProperty());
        textoContraseña.visibleProperty().bind(checkBox.selectedProperty());
        contraUsuario.managedProperty().bind(checkBox.selectedProperty().not());
        contraUsuario.visibleProperty().bind(checkBox.selectedProperty().not());
        textoContraseña.textProperty().bindBidirectional(contraUsuario.textProperty());
        //Carga los datos del Usuario
        usuario=inicio.getUser();
        nombreUsuario.setText(usuario.getNickName());
        contraUsuario.setText(usuario.getPassword());
        avatarElegido.setImage(usuario.getAvatar());
        correoUsuario.setText(usuario.getEmail());
        edadUsuario.setValue(usuario.getBirthdate());
        
        }

    @FXML
    private void cancelEvent(ActionEvent event) throws IOException {
        //Volver a Pantalla Principal
        Parent cancelarParent = FXMLLoader.load(getClass().getResource("/poiupv/FXML/PantallaPrincipal.fxml"));
         
        Scene menuInicio = new Scene(cancelarParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(menuInicio);
        ventana.setResizable(false);
        ventana.show();
    }

    @FXML
    private void registEvent(ActionEvent event) throws NavegacionDAOException, IOException {
        //Recoger los datos y actualizar el Usuario
        String nickname = nombreUsuario.getText();
            
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
        
        LocalDate birthdate = edadUsuario.getValue();
            if(birthdate == null){
                falloFecha.visibleProperty().set(true);
                mensajeError.setText("Selecciona nacimiento\n"+"Si pones la fecha manual dale al intro al acabar");
                mensajeError.visibleProperty().set(true);
                return;
            }
            if (birthdate.isAfter(edadMinima)) {
                falloFecha.visibleProperty().set(true);
                mensajeError.setText("El usuario debe tener mas de 16 años\n"+"Si pones la fecha manual dale al intro al acabar");
                mensajeError.visibleProperty().set(true);
                return;
            } else {
                falloFecha.visibleProperty().set(false);
                mensajeError.visibleProperty().set(false);
            }
            
        Image avatar = avatarElegido.getImage();
           
        inicio.setUser(email, password, avatar, birthdate);  
        
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.initStyle(StageStyle.UTILITY);
        alerta.setTitle("Modificación Perfil");
        alerta.setHeaderText("Perfil modificado correctamente");
        
        
        Optional <ButtonType> result = alerta.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("Aceptar");
            Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("/poiupv/FXML/PantallaPrincipal.fxml"));
         
            Scene inicioDeSesion = new Scene(inicioSesionParent);
        
            Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
            ventana.setScene(inicioDeSesion);
            ventana.setResizable(true);
            ventana.show();       
        } 
    }
        

    @FXML
    private void seleccionarAvatar(MouseEvent event) {
    }

    @FXML
    private void pulsarAvatar1(ActionEvent event) throws FileNotFoundException {
        File img = new File("src/resources/avatars/avatar1.gif");
        InputStream isImage = (InputStream) new FileInputStream(img);
        avatarElegido.setImage(new Image(isImage,100,100,false,false));
    }

    @FXML
    private void pulsarAvatar2(ActionEvent event) throws FileNotFoundException {
        File img = new File("src/resources/avatars/avatar2.png");
        InputStream isImage = (InputStream) new FileInputStream(img);
        avatarElegido.setImage(new Image(isImage,100,100,false,false));
    }

    @FXML
    private void pulsarAvatarArchivo(ActionEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images","*.jpg", "*.png","*.gif","*.jpeg"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
            );
        
        File f=fc.showOpenDialog(null);
        InputStream isImage = (InputStream) new FileInputStream(f);
        Image perfilEscogido=new Image(isImage,100,100,false,false);
        avatarElegido.setImage(perfilEscogido);
    }

    @FXML
    private void pulsarAvatar3(ActionEvent event) throws FileNotFoundException {
        File img = new File("src/resources/avatars/avatar3.png");
        InputStream isImage = (InputStream) new FileInputStream(img);
        avatarElegido.setImage(new Image(isImage,100,100,false,false));
    }

    private void pulsarAvatar4(ActionEvent event) throws FileNotFoundException {
        File img = new File("src/resources/avatars/avatar4.png");
        InputStream isImage = (InputStream) new FileInputStream(img);
        avatarElegido.setImage(new Image(isImage,100,100,false,false));
    }
    
}
