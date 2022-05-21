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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Answer;
import model.Navegacion;
import model.Problem;

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
    private int respuestaAleatoria=1;
    private int respuestaAleatoria1=1;
    private int respuestaAleatoria2=1;
    private int respuestaAleatoria3=1;
    
    private List<Problem> listaProblemas;
    private Problem problemaElegido;
    @FXML
    private ScrollPane scrollPane;
    
    private Group zoomGrupo;
    @FXML
    private ToggleGroup respuestas;
    
    private Answer a;
    private Answer b;
    private Answer c;
    private Answer d;
       
    InicioDeSesionController inicio= new InicioDeSesionController();
    
    @FXML
    private ImageView aciertoFalloA;
    @FXML
    private ImageView aciertoFalloB;
    @FXML
    private ImageView aciertoFalloC;
    @FXML
    private ImageView aciertoFalloD;
    @FXML
    private Slider zoomSlider;
    
    private Circle circlePainting;
    private double inicioXArc;
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
            List<Answer> respuestas1 = new ArrayList<>(respuestas);

            
            respuestaAleatoria = (int) (Math.random()*respuestas1.size());
            a = respuestas1.get(respuestaAleatoria);
            respuestas1.remove(respuestaAleatoria);
                       
            respuestaAleatoria1 = (int) (Math.random()*respuestas1.size());
            b = respuestas1.get(respuestaAleatoria1);
            respuestas1.remove(respuestaAleatoria1);
            
            respuestaAleatoria2 = (int) (Math.random()*respuestas1.size());
            c = respuestas1.get(respuestaAleatoria2);
            respuestas1.remove(respuestaAleatoria2);
            
            respuestaAleatoria3 = (int) (Math.random()*respuestas1.size());
            d = respuestas1.get(respuestaAleatoria3);
            respuestas1.remove(respuestaAleatoria3);           
            
            enunciadoA.setText(a.getText());
            enunciadoB.setText(b.getText());
            enunciadoC.setText(c.getText());
            enunciadoD.setText(d.getText());
            
        } catch (NavegacionDAOException ex) {
            Logger.getLogger(ProblemaAleatorioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        zoomSlider.setMin(0.5);
        zoomSlider.setMax(1.5);
        zoomSlider.setValue(1.0);
        zoomSlider.valueProperty().addListener((o, oldVal, newVal) -> zoom((Double) newVal));
        
        Group contentGroup = new Group();
        zoomGrupo = new Group();
        contentGroup.getChildren().add(zoomGrupo);
        zoomGrupo.getChildren().add(scrollPane.getContent());
        scrollPane.setContent(contentGroup);
        
        
        
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
        aciertoFalloA.setVisible(false);
        aciertoFalloB.setVisible(false);
        aciertoFalloC.setVisible(false);
        aciertoFalloD.setVisible(false);
        
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
        double sliderVal = zoomSlider.getValue();
        zoomSlider.setValue(sliderVal + -0.1);
    }

    @FXML
    private void pulsarMas(ActionEvent event) {
        double sliderVal = zoomSlider.getValue();
        zoomSlider.setValue(sliderVal += 0.1);
        
    }
    
    private void zoom(double scaleValue) {
        double scrollH = scrollPane.getHvalue();
        double scrollV = scrollPane.getVvalue();
        zoomGrupo.setScaleX(scaleValue);
        zoomGrupo.setScaleY(scaleValue);
        scrollPane.setHvalue(scrollH);
        scrollPane.setVvalue(scrollV);
    
    }

    @FXML
    private void arrastrarArco(MouseEvent event) {
        double radio = Math.abs(event.getX()- inicioXArc);
        circlePainting.setRadius(radio);
        event.consume();
    }

    @FXML
    private void pulsarArco(MouseEvent event) {
         circlePainting = new Circle(1);
        circlePainting.setStroke(Color.RED);
        circlePainting.setFill(Color.TRANSPARENT);
        zoomGrupo.getChildren().add(circlePainting);
        circlePainting.setCenterX(event.getX());
        circlePainting.setCenterY(event.getY());
        inicioXArc = event.getX();
        
    }
}
