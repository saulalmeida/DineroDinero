package almeida.saul.com.pdfcreation;

import java.io.Serializable;

/**
 * Created by Elite desk 700g1 on 18/01/2018.
 */

public class EquiposModel implements Serializable{

    private String Cantidad;
    private String Precio;
    private String Equipo;
    private String Total;
    private Float Importe;


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

    public String getEquipo() {
        return Equipo;
    }

    public void setEquipo(String equipo) {
        Equipo = equipo;
    }

    public Float getImporte() {
        return Importe = Float.valueOf(Cantidad)*Float.valueOf(Precio);
    }

    public void setImporte(Float importe) {
        Importe = importe;
    }
}
