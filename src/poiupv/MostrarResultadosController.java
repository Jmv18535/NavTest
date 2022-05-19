/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package poiupv;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jasan
 */
public class MostrarResultadosController implements Initializable {

    @FXML
    private DatePicker fechaABuscarDesde=new DatePicker(LocalDate.now());
    @FXML
    private Text numeroExamenesRealizados;
    @FXML
    private Text numeroAciertos;
    @FXML
    private Text numeroFallos;
    @FXML
    private Text porcentajeDeAciertos;
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
        //Crear Datos
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
           new PieChart.Data( "Product A", 3000),
           new PieChart.Data ("Product B", 100)
                
        );
        
        PieChart pieChart = new PieChart (pieChartData);
        pieChart.setTitle("Products sold");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);
        
        //vboxParaTarta.setCenter(pieChart);
        
        
        
        
    }

    @FXML
    private void salirALaPantallaPrincipalDesdeResultados(ActionEvent event) throws IOException {
        Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("/poiupv/FXML/PantallaPrincipal.fxml"));
         
        Scene inicioDeSesion = new Scene(inicioSesionParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(inicioDeSesion);
        ventana.setResizable(false);
        ventana.centerOnScreen();
        ventana.setMaximized(false);
        ventana.show();
    }
    
}
