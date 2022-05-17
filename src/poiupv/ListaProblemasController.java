/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Navegacion;
import model.Problem;

/**
 * FXML Controller class
 *
 * @author jasan
 */
public class ListaProblemasController implements Initializable {

    @FXML
    private TextField textoEnunciados;
    private List<Problem> listaProblemas;
    private Problem E1;
    private Problem E2;
    private Problem E3;
    private Problem E4;  
    private Problem E5;
    private Problem E6;
    private Problem E7;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Navegacion navegacion = null;
        try {
            navegacion = Navegacion.getSingletonNavegacion();
            listaProblemas=navegacion.getProblems();
            E1=listaProblemas.get(1);
            E2=listaProblemas.get(1);
            E3=listaProblemas.get(2);
            E4=listaProblemas.get(3);
            E5=listaProblemas.get(4);
            E6=listaProblemas.get(5);
            E7=listaProblemas.get(6);
            
            
            
            
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(ListaProblemasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void pulsar1(ActionEvent event) {
        textoEnunciados.setText(E1.getText());
    }

    @FXML
    private void pulsar4(ActionEvent event) {
    }

    @FXML
    private void pulsar7(ActionEvent event) {
    }

    @FXML
    private void pulsar2(ActionEvent event) {
    }

    @FXML
    private void pulsar5(ActionEvent event) {
    }

    @FXML
    private void pulsar8(ActionEvent event) {
    }

    @FXML
    private void pulsar10(ActionEvent event) {
    }

    @FXML
    private void pulsar13(ActionEvent event) {
    }

    @FXML
    private void pulsar16(ActionEvent event) {
    }

    @FXML
    private void pulsar11(ActionEvent event) {
    }

    @FXML
    private void pulsar14(ActionEvent event) {
    }

    @FXML
    private void pulsar17(ActionEvent event) {
    }

    @FXML
    private void pulsar3(ActionEvent event) {
    }

    @FXML
    private void pulsar6(ActionEvent event) {
    }

    @FXML
    private void pulsar9(ActionEvent event) {
    }

    @FXML
    private void pulsar12(ActionEvent event) {
    }

    @FXML
    private void pulsar15(ActionEvent event) {
    }

    @FXML
    private void pulsar18(ActionEvent event) {
    }

    @FXML
    private void volverMenuPrincipal(ActionEvent event) throws IOException {
         Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("/poiupv/FXML/PantallaPrincipal.fxml"));
         
        Scene inicioDeSesion = new Scene(inicioSesionParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(inicioDeSesion);
        ventana.setResizable(true);
        ventana.show();
    }
    }
    

