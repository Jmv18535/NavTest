/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package poiupv;

import DBAccess.NavegacionDAOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Answer;
import model.Navegacion;
import model.Problem;

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

    private int problemaAleatorio=1;
    private List<Problem> listaProblemas;
    private Problem problemaElegido;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ToggleGroup respuestas;
    
    private Answer a;
    private Answer b;
    private Answer c;
    private Answer d;
    
    PantallaPrincipalController inicio= new PantallaPrincipalController();
    @FXML
    private ImageView aciertoFalloA;
    @FXML
    private ImageView aciertoFalloB;
    @FXML
    private ImageView aciertoFalloC;
    @FXML
    private ImageView aciertoFalloD;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
            Navegacion navegacion = Navegacion.getSingletonNavegacion();
            listaProblemas=navegacion.getProblems();
            problemaAleatorio = (int) (Math.random()*listaProblemas.size());
           
            problemaElegido=listaProblemas.get(problemaAleatorio);
            
            enunciadoProblema.setText(problemaElegido.getText());
            enunciadoProblema.setWrapText​(true);
            List<Answer> respuestas = problemaElegido.getAnswers();
            a=respuestas.get(0);
            b=respuestas.get(1);
            c=respuestas.get(2);
            d=respuestas.get(3);
            
            enunciadoA.setText(a.getText());
            enunciadoB.setText(b.getText());
            enunciadoC.setText(c.getText());
            enunciadoD.setText(d.getText());
            
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(ProblemaAleatorioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("/poiupv/FXML/PantallaPrincipal.fxml"));
         
        Scene inicioDeSesion = new Scene(inicioSesionParent);
        
        Stage ventana= (Stage)((Node)event.getSource()).getScene().getWindow();
        ventana.setScene(inicioDeSesion);
        ventana.setResizable(false);
        ventana.centerOnScreen();
        ventana.setMaximized(false);
        ventana.show();
    }

    @FXML
    private void corregirRespuestas(ActionEvent event) throws FileNotFoundException {
        if(aSolucion.isSelected()){
            if(a.getValidity()){
                inicio.aumentoAciertos();
                File img = new File("src/resources/Tick.png");
                InputStream isImage = (InputStream) new FileInputStream(img);
                aciertoFalloA.setImage(new Image(isImage,40,40,false,false));
                aciertoFalloA.setVisible(true);
            }else{
                inicio.aumentoFallos();
                File img = new File("src/resources/Cross.png");
                InputStream isImage = (InputStream) new FileInputStream(img);
                aciertoFalloA.setImage(new Image(isImage,40,40,false,false));
                aciertoFalloA.setVisible(true);
            }
        }
        if(bSolucion.isSelected()){
            if(b.getValidity()){
                inicio.aumentoAciertos();
                File img = new File("src/resources/Tick.png");
                InputStream isImage = (InputStream) new FileInputStream(img);
                aciertoFalloB.setImage(new Image(isImage,40,40,false,false));
                aciertoFalloB.setVisible(true);
            }else{
                inicio.aumentoFallos();
                File img = new File("src/resources/Cross.png");
                InputStream isImage = (InputStream) new FileInputStream(img);
                aciertoFalloB.setImage(new Image(isImage,40,40,false,false));
                aciertoFalloB.setVisible(true);
                }
            }
        if(cSolucion.isSelected()){
            if(c.getValidity()){
                inicio.aumentoAciertos();
                File img = new File("src/resources/Tick.png");
                InputStream isImage = (InputStream) new FileInputStream(img);
                aciertoFalloC.setImage(new Image(isImage,40,40,false,false));
                aciertoFalloC.setVisible(true);
            }else{
                inicio.aumentoFallos();
                File img = new File("src/resources/Cross.png");
                InputStream isImage = (InputStream) new FileInputStream(img);
                aciertoFalloC.setImage(new Image(isImage,40,40,false,false));
                aciertoFalloC.setVisible(true);
            }
        }
        if(dSolucion.isSelected()){
            if(d.getValidity()){
                inicio.aumentoAciertos();
                File img = new File("src/resources/Tick.png");
                InputStream isImage = (InputStream) new FileInputStream(img);
                aciertoFalloD.setImage(new Image(isImage,40,40,false,false));
                aciertoFalloD.setVisible(true);
            }else{
                inicio.aumentoFallos();
                File img = new File("src/resources/Cross.png");
                InputStream isImage = (InputStream) new FileInputStream(img);
                aciertoFalloD.setImage(new Image(isImage,40,40,false,false));
                aciertoFalloD.setVisible(true);
            }
        }
        
        
    }

    @FXML
    private void pulsarMenos(ActionEvent event) {
    }

    @FXML
    private void pulsarMas(ActionEvent event) {
    }
    
}
