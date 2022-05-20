/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package poiupv;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Session;

/**
 * FXML Controller class
 *
 * @author jasan
 */
public class MostrarResultadosController implements Initializable {

    @FXML
    private DatePicker fechaABuscarDesde=new DatePicker(LocalDate.now());

    private Text numeroExamenesRealizados;
    @FXML
    private Text numeroAciertos;
    @FXML
    private Text numeroFallos;
    private Text porcentajeDeAciertos;
    @FXML
    private BorderPane borderPaneTarta;
    
    InicioDeSesionController inicio= new InicioDeSesionController();

    private List<Session> sesiones;
    
    int aciertos=0;
    int fallos=0;
    int examenesHechos;
    double porcentajeAciertos;
    @FXML
    private DatePicker fechaABuscarHasta;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fechaABuscarDesde.setDayCellFactory(param -> new DateCell() {
        private LocalDate now = LocalDate.now();
        

        @Override
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            if (date != null && !empty) {
                    setDisable(date.compareTo(now) > 0 );
                }
            }
        });
    }    

    
    @FXML
    private void pulsadoBuscarResultados(ActionEvent event) throws NullPointerException{
        //Coge lista sesiones
        sesiones=inicio.getSesion();
        //Compara las sesiones con la fecha dada y las suma
        //List<Session> sesionesFecha;
        LocalDate fechaDeBusqueda=fechaABuscarDesde.getValue();
        
        
        aciertos=0;
        fallos=0;
        for(int i=0;i<sesiones.size();i++){
            Session ses=sesiones.get(i);
            LocalDate dat=ses.getLocalDate();
            if(fechaDeBusqueda.isBefore(dat)||fechaDeBusqueda.isEqual(dat)){
                aciertos+=ses.getHits();
                fallos+=ses.getFaults();
            }
        }
      
        examenesHechos= aciertos+fallos;
        porcentajeAciertos =(aciertos*100)/examenesHechos;
        String porcentaje=porcentajeAciertos+"%.";
        
        numeroAciertos.setText(String.valueOf(aciertos));
        numeroFallos.setText(String.valueOf(fallos));
        
        
       
        
        //Grafico de tarta
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
           new PieChart.Data( "Aciertos", aciertos),
           new PieChart.Data ("Fallos", fallos)               
        );
        PieChart pieChart = new PieChart (pieChartData);
        pieChart.setTitle("Gráfica de aciertos y fallos");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);
        borderPaneTarta.setCenter(pieChart);
        
        
        
        
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
