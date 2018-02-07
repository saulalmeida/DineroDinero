package almeida.saul.com.pdfcreation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Elite desk 700g1 on 16/01/2018.
 */

public class Solicitudbean implements Serializable {

    private String Orden;
    private String Proyecto;
    private String Fecha;
    private String Estancia;
    private String Actividades;
    private String Total;
    private String Cantidad;
    private String Precio;
    private List<EquiposModel> Equipos;

    public Solicitudbean() {

    }

    public String getPersonas() {
        return Personas;
    }

    public void setPersonas(String personas) {
        Personas = personas;
    }

    private String Personas;

    public String getOrden() {
        return Orden;
    }

    public void setOrden(String orden) {
        Orden = orden;
    }

    public String getProyecto() {
        return Proyecto;
    }

    public void setProyecto(String proyecto) {
        Proyecto = proyecto;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getEstancia() {
        return Estancia;
    }

    public void setEstancia(String estancia) {
        Estancia = estancia;
    }

    public String getActividades() {
        return Actividades;
    }

    public void setActividades(String actividades) {
        Actividades = actividades;
    }


    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public List<EquiposModel> getEquipos() {
        return Equipos;
    }

    public void setEquipos(List<EquiposModel> equipos) {
        Equipos = equipos;
    }


}
