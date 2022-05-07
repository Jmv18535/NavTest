/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poiupv;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    private DatePicker edadUsuario;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    //asdasdasdas d

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
    }
}
