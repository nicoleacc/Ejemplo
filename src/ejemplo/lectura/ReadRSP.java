/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.lectura;

import java.io.File;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author plaurent
 */
public class ReadRSP {
    public String contents;

    ObservableList<RspData> dbList;

    public ObservableList<RspData> ReadFile(String fileName) {

        dbList = FXCollections.observableArrayList();
        String line;
        try {
            File myFile = new File(fileName);
            try (Scanner inputDB = new Scanner(myFile)) {
                int i = 1;
                while (inputDB.hasNextLine()) {
                    line = inputDB.nextLine();
                    contents=contents+line+"\n";
                    //AQUI TIENE QUE PONER LOS FILTORS QUE HABLAMOS PRIMERO DIVIDIR LA LINEA 
                    //USANDO LAS COMAS 
                    //String[] lineSplit = line.split(",");
//                     switch (lineSplit[0].replaceAll("﻿", "").replaceAll("\\s", "")) {
//                        case "5002"://AQUI UTILIZA LOS CODIGOS QUE VENGAN EN EL PDF QUE LE PASE DEL ARCHIVO RSP
//                            //AQUI HACE UN METODO PARA GUARDAR LA INFO QUE OCUPA DE ESA LINEA
//                            break;
//                        case "5003":
//                              //AQUI HACE UN METODO PARA GUARDAR LA INFO QUE OCUPA DE ESA LINEA
//                            break;
//                        default:
//                            break;}
                                    
//                  AQUI ES UNA PRUEBA  
                    if (i == 12) {
                        data(line, i);
                    }
                    if (i == 20) {
                        data(line, i);
                    }
                    if (i == 30) {
                        data(line, i);
                    }
                    i++;
                }
                inputDB.close();
                return dbList;
            }
        } catch (Exception e) {
            return dbList;
        }
    }

    private void data(String line, int i) {
//        AQUI SE UTILIZA LA CLASE RspData PARA LLENAR LA LISTA dbList ESTA LLENA LA TABLA
        dbList.add(new RspData(line, i, 12.1 * i));
    }

}
