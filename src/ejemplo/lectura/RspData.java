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

    private final StringProperty ruta = new SimpleStringProperty();
    private final StringProperty fecha = new SimpleStringProperty();
    private final StringProperty descripcion = new SimpleStringProperty();
    private final DoubleProperty est_ini = new SimpleDoubleProperty();
    private final DoubleProperty est_fin = new SimpleDoubleProperty();
    private final DoubleProperty iri_izq = new SimpleDoubleProperty();
    private final DoubleProperty iri_cen = new SimpleDoubleProperty();
    private final DoubleProperty iri_der = new SimpleDoubleProperty();
    private final DoubleProperty mri = new SimpleDoubleProperty();
    private final DoubleProperty est = new SimpleDoubleProperty();
    private final StringProperty evento = new SimpleStringProperty();
    private final DoubleProperty latitud = new SimpleDoubleProperty();
    private final DoubleProperty longitud = new SimpleDoubleProperty();
    private final StringProperty notas = new SimpleStringProperty();
    
    public RspData(){
    }
    
    public RspData(String ruta,String fecha,String Descripcion, double est_ini, double est_fin,double iri_izq,double iri_cen,double iri_der,double mri,double est, String evento, double latitud,double longitud, String notas){
        setRuta(ruta);
        setFecha(fecha);
        setDescripcion(Descripcion);
        setEst_ini(est_ini);
        setEst_fin(est_fin);
        setIri_izq(iri_izq);
        setIri_cen(iri_cen);
        setIri_der(iri_der);
        setMri(mri);
        setEst(est);
        setEvento(evento);
        setLatitud(latitud);
        setLongitud(longitud);
        setNotas(notas);
    }

    public final StringProperty rutaProperty() {
        return this.ruta;
    }

    public final java.lang.String getRuta() {
        return this.rutaProperty().get();
    }

    public final void setRuta(final java.lang.String ruta) {
        this.rutaProperty().set(ruta);
    }

    public final StringProperty fechaProperty() {
        return this.fecha;
    }

    public final java.lang.String getFecha() {
        return this.fechaProperty().get();
    }

    public final void setFecha(final java.lang.String fecha) {
        this.fechaProperty().set(fecha);
    }
    
    public final StringProperty descripcionProperty() {
        return this.descripcion;
    }

    public final java.lang.String getDescripcion() {
        return this.descripcionProperty().get();
    }

    public final void setDescripcion(final java.lang.String descripcion) {
        this.descripcionProperty().set(descripcion);
    }
    
    public final DoubleProperty est_iniProperty() {
        return this.est_ini;
    }

    public final java.lang.Double getEst_ini() {
        return this.est_iniProperty().get();
    }

    public final void setEst_ini(final java.lang.Double est_ini) {
        this.est_iniProperty().set(est_ini);
    }
    public void Est_iniProperty() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public final DoubleProperty est_finProperty() {
        return this.est_fin;
    }

    public final java.lang.Double getEst_fin() {
        return this.est_finProperty().get();
    }

    public final void setEst_fin(final java.lang.Double est_fin) {
        this.est_finProperty().set(est_fin);
    } 
    
    
    public final DoubleProperty iri_izqProperty() {
        return this.iri_izq;
    } 
    public final java.lang.Double getIri_izq() {
        return this.iri_izqProperty().get();
    }

    public final void setIri_izq(final java.lang.Double iri_izq) {
        this.iri_izqProperty().set(iri_izq);
    }

    public final DoubleProperty iri_cenProperty() {
        return this.iri_cen;
    } 
    public final java.lang.Double getIri_cen() {
        return this.iri_cenProperty().get();
    }

    public final void setIri_cen(final java.lang.Double iri_cen) {
        this.iri_cenProperty().set(iri_cen);
    }
    
    
    public final DoubleProperty iri_derProperty() {
    return this.iri_der;
    } 
    public final java.lang.Double getIri_der() {
        return this.iri_derProperty().get();
    }

    public final void setIri_der(final java.lang.Double iri_der) {
        this.iri_derProperty().set(iri_der);
    }
    
    public final DoubleProperty mriProperty() {
    return this.mri;
    } 
    public final java.lang.Double getMri() {
        return this.mriProperty().get();
    }

    public final void setMri(final java.lang.Double mri) {
        this.mriProperty().set(mri);
    }

     public final DoubleProperty estProperty() {
    return this.est;
    } 
    public final java.lang.Double getEst() {
        return this.estProperty().get();
    }

    public final void setEst(final java.lang.Double est) {
        this.estProperty().set(est);
    }
 
    public final StringProperty eventoProperty() {
        return this.evento;
    }

    public final java.lang.String getEvento() {
        return this.eventoProperty().get();
    }

    public final void setEvento(final java.lang.String evento) {
        this.eventoProperty().set(evento);
    }
   
    public final DoubleProperty latitudProperty() {
    return this.latitud;
    } 
    public final java.lang.Double getLatitud() {
        return this.latitudProperty().get();
    }

    public final void setLatitud(final java.lang.Double latitud) {
        this.latitudProperty().set(latitud);
    }
    
    
    public final DoubleProperty longitudProperty() {
    return this.longitud;
    } 
    public final java.lang.Double getLongitud() {
        return this.longitudProperty().get();
    }

    public final void setLongitud(final java.lang.Double longitud) {
        this.longitudProperty().set(longitud);
    }
    
    
    public final StringProperty notasProperty() {
        return this.notas;
    }

    public final java.lang.String getNotas() {
        return this.notasProperty().get();
    }

    public final void setNotas(final java.lang.String notas) {
        this.notasProperty().set(notas);
    }   
            
}
