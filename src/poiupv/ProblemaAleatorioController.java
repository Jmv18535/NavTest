/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jasan
 */
public class ProblemaAleatorioController implements Initializable {

    @FXML
    private TextArea enunciadoProblema;
    @FXML
    private Label enunciadoA;
    @FXML
    private RadioButton aSolucion;
    @FXML
    private Label enunciadoB;
    @FXML
    private RadioButton bSolucion;
    @FXML
    private Label enunciadoC;
    @FXML
    private RadioButton cSolucion;
    @FXML
    private Label enunciadoD;
    @FXML
    private RadioButton dSolucion;
    @FXML
    private Button salirPrincipio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void marcarA(ActionEvent event) {
    }

    @FXML
    private void marcarB(ActionEvent event) {
    }

    @FXML
    private void marcarC(ActionEvent event) {
    }

    @FXML
    private void marcarD(ActionEvent event) {
    }

    @FXML
    private void pulsarSalirprincipio(ActionEvent event) throws IOException {
        Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("PantallaPrincipal.fxml"));
         
        Scene inicioDeSesion = new Scene(inicioSesionParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(inicioDeSesion);
        ventana.setResizable(true);
        ventana.show();
    }
    
}
