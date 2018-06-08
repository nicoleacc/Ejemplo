/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author nicolecajina
 */
public class Ejemplo extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Ejemplo.class.getResource("UMP-Style.css").toExternalForm());
        //LE AGREGUE UN ICONO FAVOR CREAR UN ICONO DIFERENTE PARA ESTA APLICACION
        stage.getIcons().add(new Image("ejemplo/imagenes/ICON.png"));
        //PARA CAMBIAR EL TITULO MODIFIQUE ESTE STRING
        stage.setTitle("Prueba de Nicole");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
