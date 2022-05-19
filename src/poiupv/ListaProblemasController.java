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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Navegacion;
import model.Problem;

/**
 * FXML Controller class
 *
 * @author jasan
 */
public class ListaProblemasController implements Initializable {

    private List<Problem> listaProblemas;
    private Problem E1;
    private Problem E2;
    private Problem E3;
    private Problem E4;  
    private Problem E5;
    private Problem E6;
    private Problem E7;
    private Problem E8;
    private Problem E9;
    private Problem E10;
    private Problem E11;  
    private Problem E12;
    private Problem E13;
    private Problem E14;
    private Problem E15;
    private Problem E16;  
    private Problem E17;

    @FXML
    private TextArea enunciadoLista;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        enunciadoLista.setWrapText​(true);
         Navegacion navegacion = null;
        try {
            navegacion = Navegacion.getSingletonNavegacion();
            listaProblemas=navegacion.getProblems();
            E1=listaProblemas.get(1);
            E2=listaProblemas.get(2);
            E3=listaProblemas.get(3);
            E4=listaProblemas.get(4);
            E5=listaProblemas.get(5);
            E6=listaProblemas.get(6);
            E7=listaProblemas.get(7);
            E8=listaProblemas.get(8);
            E9=listaProblemas.get(9);
            E10=listaProblemas.get(10);
            E11=listaProblemas.get(11);
            E12=listaProblemas.get(12);
            E13=listaProblemas.get(13);
            E14=listaProblemas.get(14);
            E15=listaProblemas.get(15);
            E16=listaProblemas.get(16);
            E17=listaProblemas.get(17);
            
            
            
            
            
            
            
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(ListaProblemasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void pulsar1(ActionEvent event) {
       enunciadoLista.setText(E1.getText());
    }

    @FXML
    private void pulsar4(ActionEvent event) {
        enunciadoLista.setText(E4.getText());
    }

    @FXML
    private void pulsar7(ActionEvent event) {
        enunciadoLista.setText(E7.getText());
    }

    @FXML
    private void pulsar2(ActionEvent event) {
        enunciadoLista.setText(E2.getText());
    }

    @FXML
    private void pulsar5(ActionEvent event) {
       enunciadoLista.setText(E5.getText());
    }

    @FXML
    private void pulsar8(ActionEvent event) {
        enunciadoLista.setText(E8.getText());
    }

    @FXML
    private void pulsar10(ActionEvent event) {
        enunciadoLista.setText(E10.getText());
    }

    @FXML
    private void pulsar13(ActionEvent event) {
        enunciadoLista.setText(E13.getText());
    }

    @FXML
    private void pulsar16(ActionEvent event) {
        enunciadoLista.setText(E16.getText());
    }

    @FXML
    private void pulsar11(ActionEvent event) {
        enunciadoLista.setText(E11.getText());
    }

    @FXML
    private void pulsar14(ActionEvent event) {
        enunciadoLista.setText(E14.getText());
    }

    @FXML
    private void pulsar17(ActionEvent event) {
        enunciadoLista.setText(E17.getText());
    }

    @FXML
    private void pulsar3(ActionEvent event) {
        enunciadoLista.setText(E3.getText());
    }

    @FXML
    private void pulsar6(ActionEvent event) {
        enunciadoLista.setText(E6.getText());
    }

    @FXML
    private void pulsar9(ActionEvent event) {
        enunciadoLista.setText(E9.getText());
    }

    @FXML
    private void pulsar12(ActionEvent event) {
        enunciadoLista.setText(E12.getText());
    }

    @FXML
    private void pulsar15(ActionEvent event) {
        enunciadoLista.setText(E15.getText());
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
    

