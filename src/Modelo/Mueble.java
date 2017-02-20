package Modelo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Mueble extends Producto {

    public enum Madera {
        PINO, ROBLE, HAYA
    }

    private LocalDate anyoFab;
    private Madera tipoMadera;
    private String estilo;

    public Mueble() {
        super();
    }


    @Override
    public String imprimirProducto() {
        
        String res = super.imprimirProducto() + "el año de fabricación: " + this.getAnyoFab() + " el tipo de madera: " + this.tipoMadera + "el estilo: " + getEstilo();
        return res;

    }

    @Override
    public void setPrecio(double precioBase) {
        
        if(this.tipoMadera.equals(tipoMadera.ROBLE)){
            this.precio=precioBase*1.1;
        }
        if(this.tipoMadera.equals(tipoMadera.PINO)){
            this.precio=precioBase*0.9;
        }
       
    }

    public String getAnyoFab() {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd-MMMM-yy");
        return sdf.format(anyoFab);
    }

    public void setAnyoFab(LocalDate anyoFab) {
        this.anyoFab = anyoFab;
    }

    public Madera getTipoMadera() {
        return tipoMadera;
    }

    public void setTipoMadera(Madera madera) {
        this.tipoMadera = madera;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

}
