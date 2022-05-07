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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author javie
 */
public class PantallaInicialController implements Initializable {

    @FXML
    private Button botonInicio;
    @FXML
    private Button botonRegistro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pulsadoIniciarSesion(ActionEvent event) throws IOException {
        //Codigo para Cambiar el Stage Inicial->Inicio de Sesion
        
        Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("InicioDeSesion.fxml"));
         
        Scene inicioDeSesion = new Scene(inicioSesionParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(inicioDeSesion);
        ventana.setResizable(true);
        ventana.show();
        
        //
    }

    @FXML
    private void pulsadoRegistrarse(ActionEvent event) throws IOException {
        
        Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("Registro.fxml"));
         
        Scene inicioDeSesion = new Scene(inicioSesionParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(inicioDeSesion);
        ventana.setResizable(true);
        ventana.show();
    }
    
}
