/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.lectura;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author plaurent
 */
public class RspData {

    private final StringProperty station = new SimpleStringProperty();
    private final IntegerProperty repetition = new SimpleIntegerProperty();
    private final DoubleProperty stress = new SimpleDoubleProperty();
    
    public RspData(){
    }
    
    public RspData(String station, int repetition,double stress){
        setStation(station);
        setRepetitions(repetition);
        setStress(stress);
    }

    public final StringProperty stationProperty() {
        return this.station;
    }

    public final java.lang.String getStation() {
        return this.stationProperty().get();
    }

    public final void setStation(final java.lang.String station) {
        this.stationProperty().set(station);
    }

    public final IntegerProperty repetitionProperty() {
        return this.repetition;
    }

    public final java.lang.Integer getRepetition() {
        return this.repetitionProperty().get();
    }

    public final void setRepetitions(final java.lang.Integer repetition) {
        this.repetitionProperty().set(repetition);
    }

    public final DoubleProperty stressProperty() {
        return this.stress;
    }

    public final java.lang.Double getStress() {
        return this.stressProperty().get();
    }

    public final void setStress(final java.lang.Double stress) {
        this.stressProperty().set(stress);
    }

}
