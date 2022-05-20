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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    
    
    @FXML
    private DatePicker fechaABuscarHasta;
    @FXML
    private TableView<Session> tableView;
    @FXML
    private TableColumn<Session, String> tableViewC1;
    @FXML
    private TableColumn<Session, Integer> tableViewC2;
    @FXML
    private TableColumn<Session, Integer> tableViewC3;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        //Configurar DatePicker bloqueando hasta hoy la busqueda
        
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
        fechaABuscarHasta.setDayCellFactory(param -> new DateCell() {
        private LocalDate now = LocalDate.now();
        @Override
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            if (date != null && !empty) {
                    setDisable(date.compareTo(now) > 0 );
                }
            }
        });
        
        //Mostrar todas las sesiones
        
        sesiones=inicio.getSesion();
        aciertos=0;
        fallos=0;
        for(int i=0;i<sesiones.size();i++){
            Session ses=sesiones.get(i);           
            aciertos+=ses.getHits();
            fallos+=ses.getFaults();  
            tableView.getItems().add(ses);
        }
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
       
        tableViewC1.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLocalDate()).asString());
        tableViewC2.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getHits()).asObject());
        tableViewC3.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFaults()).asObject());
        
        
    }    

    
    
    @FXML+
    private void pulsadoBuscarResultados(ActionEvent event) throws NullPointerException{
        //Coge lista sesiones
        sesiones=inicio.getSesion();
        tableView.getItems().clear();
        LocalDate fechaDeBusqueda=fechaABuscarDesde.getValue();
        LocalDate fechaLimite=fechaABuscarHasta.getValue();
        
        aciertos=0;
        fallos=0;
        for(int i=0;i<sesiones.size();i++){
            Session ses=sesiones.get(i);
            LocalDate dat=ses.getLocalDate();
            if((fechaDeBusqueda.isBefore(dat)||fechaDeBusqueda.isEqual(dat))&&(fechaLimite.isAfter(dat)||fechaLimite.isEqual(dat))){
                aciertos+=ses.getHits();
                fallos+=ses.getFaults();
                tableView.getItems().add(ses);
            }
        }
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
