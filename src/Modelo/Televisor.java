
package Modelo;


public class Televisor extends Electrodomestico {

    public enum TipoTelevisor{
        PLASMA, LED, LCD, OLED
    }
    
    protected TipoTelevisor tipotel;
    protected double pulgadas;
    

    public Televisor() {
        super();
    }

    @Override
    public void setPrecio(double precioBase) {

        this.precio = 0;

        if (this.pulgadas > 40) {
            this.precio = precioBase * 1.1;
        }
        if (this.tipotel.equals(tipotel.PLASMA)) {
            this.precio= precioBase*0.9;
        }
        if((this.pulgadas>40)&&(this.tipotel.equals(tipotel.PLASMA))){
            this.precio= ((precioBase*1.1)+(precioBase*0.9))/2;
        }    
        
    }

    @Override
    public String imprimirProducto() {
        String res = super.imprimirProducto() + "tipo de TV: " + this.tipotel + "con " + this.pulgadas + " pulgadas";
        return res;

    }

    public double getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(double pulgadas) {
        this.pulgadas = pulgadas;
    }

    public TipoTelevisor getTipotel() {
        return tipotel;
    }

    public void setTipotel(TipoTelevisor tipotel) {
        this.tipotel = tipotel;
    }

    
}
