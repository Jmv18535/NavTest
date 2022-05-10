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
import javafx.stage.Stage;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pulsadoListaDeProblemas(ActionEvent event) {
    }

    @FXML
    private void pulsadoProblemaAleatorio(ActionEvent event) {
    }

    @FXML
    private void pulsadoMostrarResultados(ActionEvent event) {
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
    
}
