package poiupv;

import DBAccess.NavegacionDAOException;
import static java.awt.SystemColor.text;
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
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    
    private Line linea;
    
    private double inicioXTrans;

    private double inicioYTrans;

    private double baseX;

    private double baseY;
    
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
    
    private Circle punto;
    
    private double inicioXArc;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField grosor;
    @FXML
    private ImageView imagenFondo;
    @FXML
    private Button eliminarBoton;
    @FXML
    private ImageView transportador;
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
        
        choiceBox.getItems().add("Punto");
        choiceBox.getItems().add("Línea");
        choiceBox.getItems().add("Arco");
        choiceBox.getItems().add("Texto");
        choiceBox.getItems().add("Extremos");
        choiceBox.getItems().add("Mover");
        
        
        grosor.setText("15");
        

        
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
    private void cartaDragged(MouseEvent event) {
        scrollPane.addEventFilter(MouseEvent.MOUSE_DRAGGED,evt -> {
            if (evt.getButton() == MouseButton.SECONDARY) {
                evt.consume();
            }
        });
        if ("Arco".equals(choiceBox.getSelectionModel().getSelectedItem())){
        double radio = Math.abs(event.getX()- inicioXArc);
        circlePainting.setRadius(radio);
        event.consume();
        } else if ("Línea".equals(choiceBox.getSelectionModel().getSelectedItem())) {
            linea.setEndX(event.getX());
            linea.setEndY(event.getY());
            event.consume();
        }
    }

    @FXML
    private void cartaClicked(MouseEvent event) {
         scrollPane.addEventFilter(MouseEvent.MOUSE_CLICKED,evt -> {
            if (evt.getButton() == MouseButton.SECONDARY) {
                evt.consume();
            }
        });
        if ("Punto".equals(choiceBox.getSelectionModel().getSelectedItem())){
            punto= new Circle(3);
            zoomGrupo.getChildren().add(punto);
            punto.setStroke(colorPicker.getValue());
            String textoTam = grosor.getText();
            int tamLin= Integer.valueOf(textoTam);
            punto.setStrokeWidth(tamLin);  
            punto.setCenterX(event.getX());
            punto.setCenterY(event.getY());
            punto.setOnContextMenuRequested(e -> {
                ContextMenu menuContext = new ContextMenu();
                MenuItem borrarItem = new MenuItem("eliminar");
                MenuItem colorItem = new MenuItem("Cambio Color");
                menuContext.getItems().add(borrarItem);
                menuContext.getItems().add(colorItem);
                borrarItem.setOnAction(ev -> {
                    zoomGrupo.getChildren().remove((Node)e.getSource());
                    ev.consume();
                });
                colorItem.setOnAction(ev -> {
                    Circle elegida = (Circle) e.getSource();
                    elegida.setFill(colorPicker.getValue());
                    ev.consume();
                });
                menuContext.show(punto, e.getSceneX(), e.getSceneY());
                e.consume();
            });
        }else if ("Extremos".equals(choiceBox.getSelectionModel().getSelectedItem())){
           // Line lineaEx1 = new Line(event.getX(), event.getY(), imagenFondo.getX(), 0);
            //Line lineaEx2 = new Line(event.getX(), event.getY(), 0, imagenFondo.getY());
            Line lineaEx1 = new Line();
            Line lineaEx2 = new Line();
            
            lineaEx1.setStartX(event.getX());
            lineaEx1.setStartY(0);
            lineaEx1.setEndX(event.getX());
            lineaEx1.setEndY(1000000);
            
            lineaEx2.setStartX(0);
            lineaEx2.setStartY(event.getY());
            lineaEx2.setEndX(100000);
            lineaEx2.setEndY(event.getY());
            
            String textoTam = grosor.getText();
            int tamLin= Integer.valueOf(textoTam);
            lineaEx1.setStrokeWidth(tamLin);
            lineaEx1.setStroke(colorPicker.getValue());
            lineaEx2.setStrokeWidth(tamLin);
            lineaEx2.setStroke(colorPicker.getValue());
            zoomGrupo.getChildren().add(lineaEx1);
            zoomGrupo.getChildren().add(lineaEx2);
            lineaEx2.setOnContextMenuRequested(e -> {
                ContextMenu menuContext = new ContextMenu();
                MenuItem borrarItem = new MenuItem("eliminar");
                MenuItem colorItem = new MenuItem("Cambio Color");
                menuContext.getItems().add(borrarItem);
                menuContext.getItems().add(colorItem);
                borrarItem.setOnAction(ev -> {
                    zoomGrupo.getChildren().remove((Node)e.getSource());
                    ev.consume();
                });
                colorItem.setOnAction(ev -> {
                    Line elegida = (Line) e.getSource();
                    elegida.setStroke(colorPicker.getValue());
                    ev.consume();
                });
                menuContext.show(lineaEx2, e.getSceneX(), e.getSceneY());
                e.consume();
            });
            lineaEx1.setOnContextMenuRequested(e -> {
                ContextMenu menuContext = new ContextMenu();
                MenuItem borrarItem = new MenuItem("eliminar");
                MenuItem colorItem = new MenuItem("Cambio Color");
                menuContext.getItems().add(borrarItem);
                menuContext.getItems().add(colorItem);
                borrarItem.setOnAction(ev -> {
                    zoomGrupo.getChildren().remove((Node)e.getSource());
                    ev.consume();
                });
                colorItem.setOnAction(ev -> {
                    Line elegida = (Line) e.getSource();
                    elegida.setStroke(colorPicker.getValue());
                    ev.consume();
                });
                menuContext.show(lineaEx1, e.getSceneX(), e.getSceneY());
                e.consume();
            });
        }
        
    }

    @FXML
    private void cartaPressed(MouseEvent event) {
        scrollPane.addEventFilter(MouseEvent.MOUSE_PRESSED,evt -> {
            if (evt.getButton() == MouseButton.SECONDARY) {
                evt.consume();
            }
        });
        if ("Arco".equals(choiceBox.getSelectionModel().getSelectedItem())){
            circlePainting = new Circle(1);
            circlePainting.setStroke(colorPicker.getValue());
            circlePainting.setFill(Color.TRANSPARENT);
            
            String textoTam = grosor.getText();
            int tamLin= Integer.valueOf(textoTam);
            circlePainting.setStrokeWidth(tamLin);
            zoomGrupo.getChildren().add(circlePainting);
            circlePainting.setCenterX(event.getX());
            circlePainting.setCenterY(event.getY());
            inicioXArc = event.getX();
            
            circlePainting.setOnContextMenuRequested(e -> {
                ContextMenu menuContext = new ContextMenu();
                MenuItem borrarItem = new MenuItem("eliminar");
                MenuItem colorItem = new MenuItem("Cambio Color");
                menuContext.getItems().add(borrarItem);
                menuContext.getItems().add(colorItem);
                borrarItem.setOnAction(ev -> {
                    zoomGrupo.getChildren().remove((Node)e.getSource());
                    ev.consume();
                });
                colorItem.setOnAction(ev -> {
                    Circle elegida = (Circle) e.getSource();
                    elegida.setStroke(colorPicker.getValue());
                    ev.consume();
                });
                menuContext.show(circlePainting, e.getSceneX(), e.getSceneY());
                e.consume();
            });
            
        } else if ("Línea".equals(choiceBox.getSelectionModel().getSelectedItem())) {
            linea = new Line(event.getX(), event.getY(), event.getX(), event.getY());
            String textoTam = grosor.getText();
            int tamLin= Integer.valueOf(textoTam);
            linea.setStrokeWidth(tamLin);
            linea.setStroke(colorPicker.getValue());
            zoomGrupo.getChildren().add(linea);
            linea.setOnContextMenuRequested(e -> {
                ContextMenu menuContext = new ContextMenu();
                MenuItem borrarItem = new MenuItem("eliminar");
                MenuItem colorItem = new MenuItem("Cambio Color");
                menuContext.getItems().add(borrarItem);
                menuContext.getItems().add(colorItem);
                borrarItem.setOnAction(ev -> {
                    zoomGrupo.getChildren().remove((Node)e.getSource());
                    ev.consume();
                });
                colorItem.setOnAction(ev -> {
                    Line elegida = (Line) e.getSource();
                    elegida.setStroke(colorPicker.getValue());
                    ev.consume();
                });
                menuContext.show(linea, e.getSceneX(), e.getSceneY());
                e.consume();
            });
        } else if ("Texto".equals(choiceBox.getSelectionModel().getSelectedItem())) {
            
            TextField texto = new TextField();
            zoomGrupo.getChildren().add(texto);
            texto.setLayoutX(event.getX());
            texto.setLayoutY(event.getY());
            texto.requestFocus();
            texto.setOnAction(e -> {
                Text textoT = new Text(texto.getText());
                textoT.setX(texto.getLayoutX());
                textoT.setY(texto.getLayoutY());
                textoT.setFill(colorPicker.getValue());
                String textoTam = grosor.getText();
                Font fuente= new Font(Integer.valueOf(textoTam));
                textoT.setFont(fuente);
                zoomGrupo.getChildren().add(textoT);
                zoomGrupo.getChildren().remove(texto);
                
                textoT.setOnContextMenuRequested(ea -> {
                    ContextMenu menuContext = new ContextMenu();
                    MenuItem borrarItem = new MenuItem("eliminar");
                    MenuItem colorItem = new MenuItem("Cambio Color");
                    menuContext.getItems().add(borrarItem);
                    menuContext.getItems().add(colorItem);
                    borrarItem.setOnAction(ev -> {
                        zoomGrupo.getChildren().remove((Node)ea.getSource());
                        ev.consume();
                     });
                    colorItem.setOnAction(ev -> {
                        Text elegida = (Text) ea.getSource();                    
                        elegida.setFill(colorPicker.getValue());
                        ev.consume();
                });
                menuContext.show(textoT, ea.getSceneX(), ea.getSceneY());
                e.consume();
               
            });
                e.consume();
            });
            
            
        }
    }

    @FXML
    private void transportador(ActionEvent event) {
        if (transportador.isVisible() == false) {
            transportador.setVisible(true);
            transportador.setDisable(false);
        } else {
            transportador.setVisible(false);
            transportador.setDisable(false);

        }
    }
 
    @FXML
    private void limpiar(ActionEvent event) {
  
        for(int i=zoomGrupo.getChildren().size()-1;i>=1;i--){
            zoomGrupo.getChildren().remove(i);
        }
        
    }

    @FXML
    private void pulsarEliminar(ActionEvent event) {
        if(zoomGrupo.getChildren().size()-1 >=1){
        zoomGrupo.getChildren().remove(zoomGrupo.getChildren().size()-1);}
    else{
    eliminarBoton.setDisable(true);

}
}

    @FXML
    private void transportReleased(MouseEvent event) {
    }
    
    @FXML
    private void transportDragged(MouseEvent event) {
        double despX = event.getSceneX() - inicioXTrans;
        double despY = event.getSceneY() - inicioYTrans;
        transportador.setTranslateX(baseX + despX);
        transportador.setTranslateY(baseY + despY);
        event.consume();
    }

    @FXML
    private void transportPressed(MouseEvent event) {
        inicioXTrans = event.getSceneX();
        inicioYTrans = event.getSceneY();
        baseX = transportador.getTranslateX();
        baseY = transportador.getTranslateY();
        event.consume();
    }

    @FXML
    private void nuevoProblemaAleatorio(ActionEvent event) {
            aciertoFalloA.setVisible(false);
            aciertoFalloB.setVisible(false);
            aciertoFalloC.setVisible(false);
            aciertoFalloD.setVisible(false);
            aSolucion.setSelected(false);
            bSolucion.setSelected(false);
            cSolucion.setSelected(false);
            dSolucion.setSelected(false);
            
            
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
            
            
        }
}
