/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.lectura;

import java.io.File;
import java.io.FileNotFoundException;
//import java.util.Arrays;
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

                double l_Est_ini = 0;
                double l_Est_Fin = 0;
                double l_IRI_izq = 0;
                double l_IRI_cen = 0;
                double l_IRI_der = 0;
                double l_MRI = 0;
                double l_Est = 0;
                String l_Evento = "0";
                String l_Notas = "";
                String l_Ruta = "No muestra ruta";
                String l_Fecha = "No muestra fecha";
                String l_Descripcion = "No muestra descripcion";
                double Latitud;
                double Longitud;
                boolean fistLine = true;

                while (inputDB.hasNextLine()) {
                    line = inputDB.nextLine();
                    String[] lineSplit = line.split(",");
                    String indicador = lineSplit[0].replaceAll("﻿", "").replaceAll("\\s", "");

                    switch (indicador) {//CAMBIE EL IF POR UN SWITCH ES MAS EFICIENTE
                        case "5280":
                            if (fistLine) {
                                break;
                            } else {
                                Latitud = Double.parseDouble(lineSplit[5].replaceAll("﻿", "").replaceAll("\\s", ""));
                                Longitud = Double.parseDouble(lineSplit[6].replaceAll("﻿", "").replaceAll("\\s", ""));
                                dbList.add(new RspData(l_Ruta, l_Fecha, l_Descripcion, l_Est_ini, l_Est_Fin, l_IRI_izq, l_IRI_cen, l_IRI_der, l_MRI, l_Est, l_Evento, Latitud, Longitud, l_Notas));
                                l_Est_ini = 0;
                                l_Est_Fin = 0;
                                l_IRI_izq = 0;
                                l_IRI_cen = 0;
                                l_IRI_der = 0;
                                l_MRI = 0;
                                l_Est = 0;
                                l_Evento = "0";
                                l_Notas = "";
                                break;
                            }
                        case "5406":
                            l_Est_ini = Double.parseDouble(lineSplit[1].replaceAll("﻿", "").replaceAll("\\s", "")) * 1000;
                            l_Est_Fin = Double.parseDouble(lineSplit[2].replaceAll("﻿", "").replaceAll("\\s", "")) * 1000;
                            l_IRI_izq = Double.parseDouble(lineSplit[3].replaceAll("﻿", "").replaceAll("\\s", ""));
                            l_IRI_cen = Double.parseDouble(lineSplit[4].replaceAll("﻿", "").replaceAll("\\s", ""));
                            l_IRI_der = Double.parseDouble(lineSplit[5].replaceAll("﻿", "").replaceAll("\\s", ""));
                            l_MRI = (l_IRI_izq + l_IRI_der) / 2;
                            fistLine = false;
                            break;
                        case "5416":
                            l_Est = Double.parseDouble(lineSplit[1].replaceAll("﻿", "").replaceAll("\\s", "")) * 1000;
                            l_Evento = lineSplit[2].replaceAll("\"", "");
                            ;
                            fistLine = false;
                            break;
                        case "5029":
                            l_Notas = lineSplit[1].replaceAll("\"", "");
                            ;
                            fistLine = false;
                            break;
                        case "5031":
                            l_Ruta = lineSplit[1].replaceAll("\"", "");
                            ;
                            fistLine = false;
                            break;
                        case "5011":
                            l_Fecha = lineSplit[3] + "/" + lineSplit[4] + "/" + lineSplit[5];
                            fistLine = false;
                            break;
                        case "5032":
                            l_Descripcion = lineSplit[1].replaceAll("\"", "");
                            fistLine = false;
                            break;
                        default:
                            break;
                    }

                }
                inputDB.close();

                return dbList;

            } catch (FileNotFoundException ex) {//  FALTABA CERRAR EL CATCH
                System.out.println("Error");
                System.out.println(ex);
                return null;// SE RETORNA NULL POR QUE HAY UN ERROR NO DEBERIA RETORNAR EL DBLIST
            }
        } catch (NumberFormatException e) {
            System.out.println("Error");
            System.out.println(e);
            return null;// SE RETORNA NULL POR QUE HAY UN ERROR NO DEBERIA RETORNAR EL DBLIST
        }
    }

}
