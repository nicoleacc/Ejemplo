/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo;

import ejemplo.lectura.ReadRSP;
import ejemplo.lectura.RspData;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import util.DragSelectionCellFactory;
import util.EditCell;
import util.EditCellNumberDouble;
import util.KeyboardSelect;
import util.TableEditOneKey;
import util.TableUtils;

/**
 *
 * @author nicolecajina
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ToggleButton showRSP;
    @FXML
    private SplitPane divider;
    @FXML
    private Button openRSP;
    @FXML
    private TextArea contents;
    @FXML
    private Button saveRSP;
    @FXML
    private TableColumn<RspData, String> dataRuta;
    @FXML
    private TableColumn<RspData, String> dataFecha;
    @FXML
    private TableColumn<RspData, String> dataDescripcion;
    @FXML
    private TableColumn<RspData, Number> dataEst_ini;
    @FXML
    private TableColumn<RspData, Number> dataEst_fin;
    @FXML
    private TableColumn<RspData, Number> dataIri_izq;
    @FXML
    private TableColumn<RspData, Number> dataIri_cen;
    @FXML
    private TableColumn<RspData, Number> dataIri_der;
    @FXML
    private TableColumn<RspData, Number> dataMri;
    @FXML
    private TableColumn<RspData, Number> dataEst;
    @FXML
    private TableColumn<RspData, String> dataEvento;
    @FXML
     private TableColumn<RspData, Number> dataLatitud;
    @FXML
    private TableColumn<RspData, Number> dataLongitud;
    @FXML
    private TableColumn<RspData, String> dataNotas;
    @FXML
    private TableView<RspData> dataBaseTable;

    public RspData dataBaseItem;
    ObservableList<RspData> dataList;
    String rootDirectory = "";
    String rootFileName = "";
    StringBuilder printElements;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // AQUI CUALQUIER COSA QUE NECESITE CARGAR AL INICIAR LA APLICACIÓN
        //EN ESTE CASO INICIALIZAMOS UN OBJETO DASE DE DATOS REVISAR CLASE RSPDATA
        dataList = FXCollections.observableArrayList();
        printElements = new StringBuilder();
        dataList.clear();
        divider.setDividerPositions(1.0);
    }

    //TODO COMANDO DE ACCION ENTRE JAVA Y SCENE BUILDER LLEVA EL TAG @FXML
    @FXML
    public void openRSP(ActionEvent event) {
        //ASI SE DECLARA EL FILECHOOSER
        FileChooser fileChooser = new FileChooser();
        //AQUI EL TITULO
        fileChooser.setTitle("Abrir archivo");
        //AQUI LA UBICACION INICIAL
        fileChooser.setInitialDirectory(new File((System
                .getProperty("user.home"))));
//        COMENTE LA SECCION PARA AÑADIR FILTRO AL FILE CHOOSER 
//        POR QUE NO TENGO ARCHIVOS RSP PARA PROBAR, CUANDO USTED
//        QUIERA LO ACTIVA Y ASÍ SOLO VISUALIZA LOS ARCHIVOS RSP
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("Datos RSP (*.rsp)", "*.rsp");
        fileChooser.getExtensionFilters().add(extFilter);
        //AQUI MUESTRA EL FILE CHOOSER
        File file = fileChooser.showOpenDialog(openRSP.getScene().getWindow());
        if (file != null) {
            rootDirectory = file.getParent();
            rootFileName = removeExtension(file.getName());
            printElements = new StringBuilder();
            System.out.println(rootDirectory);
            //AQUI CREA UN OBJETO DE LECTURA VER CLASE READRSP
            ReadRSP rsp = new ReadRSP();
            //UNA VEZ ABIERTO SE ASIGNA AL OBJETO BASE DE DATOS GLOBAL
            dataList = rsp.ReadFile(file.getAbsolutePath());
            //AQUI IMPREME RESULTADOS DE BASE DE DATOS
            //AQUI SE MUESTRAN LOS DATOS ADQUIRIDOS DESDE EL ARCHIVO EN LA TABLA
            setTable(dataList);

            contents.setText(rsp.contents);

        }
    }

    //  CONFIGURACION DE LA TABLA PARA QUE MUESTRE LOS DATOS DE LA CLASE RSPDATA
    private void setTable(ObservableList<RspData> dataList) {
        //PARA CADA TIPO DE COLUMNA SE DEBE LIGAR EL TIPO DE DATOS CON EL PROPERTY DE LA CLASE
        //PARA dataRuta SE LIGA CON EL rutaProperty()
        System.out.println("hola");
        
        dataRuta.setCellValueFactory(cellData -> cellData.getValue().rutaProperty());
        //PARA CADA TIPO DE DATOS DE LA COLUMNA EXISTE UN FACTORY Y DEBE LIGARSE AL APROPIADO
        //PARA dataRuta EN LA CLASE RspData SE DEFINIO QUE LA ESTACION ES UN STRING POR ESO SE UTILIZA EL EditCell.createStringEditCell()
        dataRuta.setCellFactory(new DragSelectionCellFactory<>(column -> EditCell.createStringEditCell()));
        
        dataFecha.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        dataFecha.setCellFactory(new DragSelectionCellFactory<>(column -> EditCell.createStringEditCell()));
        
        dataDescripcion.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());
        dataDescripcion.setCellFactory(new DragSelectionCellFactory<>(column -> EditCell.createStringEditCell()));
             
        dataEst_ini.setCellValueFactory(cellData -> cellData.getValue().est_iniProperty());
        dataEst_ini.setCellFactory(new DragSelectionCellFactory<>(column -> EditCellNumberDouble.createStringEditCell()));
        
        dataEst_fin.setCellValueFactory(cellData -> cellData.getValue().est_finProperty());
        dataEst_fin.setCellFactory(new DragSelectionCellFactory<>(column -> EditCellNumberDouble.createStringEditCell()));
        
        dataIri_izq.setCellValueFactory(cellData -> cellData.getValue().iri_izqProperty());
        dataIri_izq.setCellFactory(new DragSelectionCellFactory<>(column -> EditCellNumberDouble.createStringEditCell()));
        
        dataIri_cen.setCellValueFactory(cellData -> cellData.getValue().iri_cenProperty());
        dataIri_cen.setCellFactory(new DragSelectionCellFactory<>(column -> EditCellNumberDouble.createStringEditCell()));
        
        dataIri_der.setCellValueFactory(cellData -> cellData.getValue().iri_derProperty());
        dataIri_der.setCellFactory(new DragSelectionCellFactory<>(column -> EditCellNumberDouble.createStringEditCell()));
        
        dataMri.setCellValueFactory(cellData -> cellData.getValue().mriProperty());
        dataMri.setCellFactory(new DragSelectionCellFactory<>(column -> EditCellNumberDouble.createStringEditCell()));
        
        dataEst.setCellValueFactory(cellData -> cellData.getValue().estProperty());
        dataEst.setCellFactory(new DragSelectionCellFactory<>(column -> EditCellNumberDouble.createStringEditCell()));
       
        dataEvento.setCellValueFactory(cellData -> cellData.getValue().eventoProperty());
        dataEvento.setCellFactory(new DragSelectionCellFactory<>(column -> EditCell.createStringEditCell()));
        
        dataLatitud.setCellValueFactory(cellData -> cellData.getValue().latitudProperty());
        dataLatitud.setCellFactory(new DragSelectionCellFactory<>(column -> EditCellNumberDouble.createStringEditCell()));
        
        dataLongitud.setCellValueFactory(cellData -> cellData.getValue().longitudProperty());
        dataLongitud.setCellFactory(new DragSelectionCellFactory<>(column -> EditCellNumberDouble.createStringEditCell()));

        dataNotas.setCellValueFactory(cellData -> cellData.getValue().notasProperty());
        dataNotas.setCellFactory(new DragSelectionCellFactory<>(column -> EditCell.createStringEditCell()));
        
        
        dataBaseTable.setItems(dataList);
        dataBaseTable.refresh();
        dataBaseTable.setEditable(false);
        dataBaseTable.getSelectionModel().setCellSelectionEnabled(true);
        dataBaseTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        TableUtils.installCopyPasteHandler(dataBaseTable);
        TableEditOneKey.installEventFilter(dataBaseTable);
        dataBaseTable.addEventHandler(KeyEvent.KEY_RELEASED, new KeyboardSelect(dataBaseTable));
        dataBaseTable.setEditable(true);
        setEditableCols(false);
    }
    
// APAGA LA EDICION DE LAS COLUMNAS DE LA TABLA
    private void setEditableCols(boolean enabled) {
        dataRuta.setEditable(enabled);
        dataFecha.setEditable(enabled);
        dataDescripcion.setEditable(enabled);
        dataEst_ini.setEditable(enabled);
        dataEst_fin.setEditable(enabled);
        dataIri_izq.setEditable(enabled);
        dataIri_cen.setEditable(enabled);
        dataIri_der.setEditable(enabled);
        dataMri.setEditable(enabled);
        dataEst.setEditable(enabled);
        dataEvento.setEditable(enabled);
        dataLatitud.setEditable(enabled);
        dataLongitud.setEditable(enabled);
        dataNotas.setEditable(enabled);

    }

    
    // BOTON PARA ACTIVAR EDICION
    @FXML
    public void editRSP(ActionEvent event) {
        if (printElements.toString() != null) {
            setEditableCols(true);
        } else {
        }
        
    }

    //BOTON DE GUARDAR DESACTIVA LA EDICIÓN
    @FXML
    public void saveRSP(ActionEvent event) {
        if (!printElements.toString().isEmpty()) {
            setEditableCols(false);
        }
    }

    //MUESTRA VENTANA CON EL CONTENIDO DEL ARCHIVO
    @FXML
    public void showContents(ActionEvent event) {

        if (!rootDirectory.isEmpty()) {
            if (showRSP.isSelected()) {
                divider.setDividerPositions(0.8);
            } else {
                divider.setDividerPositions(1.0);
            }

        } else {
            showRSP.setSelected(false);
        }
    }

    //EXPORTA EL CONTENIDO DE LA TABLE 
    @FXML
    public void exportRSP(ActionEvent event) {
        printElements = printElements();
        if (printElements.toString().isEmpty()) {
            System.out.println("empty");
        } else {
            //ASI SE DECLARA EL FILECHOOSER
            FileChooser fileChooser = new FileChooser();
            //AQUI EL TITULO
            fileChooser.setTitle("Exportar archivo CSV");
            //AQUI LA UBICACION INICIAL
            fileChooser.setInitialDirectory(new File(rootDirectory));
            FileChooser.ExtensionFilter extFilter
                    = new FileChooser.ExtensionFilter("Archivo CSV (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName(rootFileName);
            File file = fileChooser.showSaveDialog(saveRSP.getScene().getWindow());
            if (file != null) {
                try {
                    PrintWriter pw;
                    pw = new PrintWriter(new File(file.getAbsoluteFile().toString()));
                    pw.write(printElements.toString());
                    pw.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText("El archivo " + file.getName() + " se escribio con exito.");
                    alert.showAndWait();
                } catch (FileNotFoundException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Se encontro un error escribiendo el archivo");
                    alert.showAndWait();
                }
            }
        }
    }

    //CREA UN CONCATENADO DE LOS DATOS
    private StringBuilder printElements() {
        StringBuilder sb;
        sb = new StringBuilder();
        dataBaseItem = new RspData();
        sb.append("Ruta,Fecha,Descripcion,Est_ini,Est_Fin,IRI_Izq,IRI_Cen,IRI_Der,MRI,Est,Evento,Latitud,Longitud,Notas");
        sb.append("\n");
        for (int i = 0; i < dataBaseTable.getItems().size(); i++) {
            dataBaseItem = dataBaseTable.getItems().get(i);
            
            sb.append(dataBaseItem.getRuta());
            sb.append(",");
            sb.append(dataBaseItem.getFecha());
            sb.append(",");
            sb.append(dataBaseItem.getDescripcion());
            sb.append(",");
            sb.append(dataBaseItem.getEst_ini());
            sb.append(",");
            sb.append(dataBaseItem.getEst_fin());
            sb.append(",");
            sb.append(dataBaseItem.getIri_izq());
            sb.append(",");
            sb.append(dataBaseItem.getIri_cen());
            sb.append(",");
            sb.append(dataBaseItem.getIri_der());
            sb.append(",");
            sb.append(dataBaseItem.getMri());
            sb.append(",");
            sb.append(dataBaseItem.getEst());
            sb.append(",");
            sb.append(dataBaseItem.getEvento());
            sb.append(",");
            sb.append(dataBaseItem.getLatitud());
            sb.append(",");
            sb.append(dataBaseItem.getLongitud());
            sb.append(",");
            sb.append(dataBaseItem.getNotas());
            sb.append("\n");
            
        }
        return sb;
    }

    //ELIMINA LA EXTESION DE LOS ARCHIVOS
    public static String removeExtension(String s) {
        String separator = System.getProperty("file.separator");
        String filename;

        // Remove the path upto the filename.
        int lastSeparatorIndex = s.lastIndexOf(separator);
        if (lastSeparatorIndex == -1) {
            filename = s;
        } else {
            filename = s.substring(lastSeparatorIndex + 1);
        }

        // Remove the extension.
        int extensionIndex = filename.lastIndexOf(".");
        if (extensionIndex == -1) {
            return filename;
        }

        return filename.substring(0, extensionIndex);
    }

}
