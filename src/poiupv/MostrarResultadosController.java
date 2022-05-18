/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package poiupv;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author jasan
 */
public class MostrarResultadosController implements Initializable {

    @FXML
    private DatePicker fechaABuscarDesde;
    @FXML
    private Text numeroExamenesRealizados;
    @FXML
    private Text numeroAciertos;
    @FXML
    private Text numeroFallos;
    @FXML
    private HBox porcentajeDeAciertos;
    @FXML
    private VBox vboxParaTarta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pulsadoBuscarResultados(ActionEvent event) {
    }

    @FXML
    private void salirALaPantallaPrincipalDesdeResultados(ActionEvent event) {
    }
    
}
