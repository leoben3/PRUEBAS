/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Modelo.Cliente;
import Modelo.Lavadora;
import Modelo.Mueble;
import Modelo.Producto;
import Modelo.Televisor;
import Modelo.Venta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daw
 */
public class ProductosService {
     private List<Producto> productos;
     private VentasService ventaGestion;
      
     ProductosService() {
        productos = new ArrayList();
    }

    public void setVentaGestion(VentasService ventaGestion) {
        this.ventaGestion = ventaGestion;
    }
      
    public void introducirProducto(Producto p) {

        try {
            productos.add(p);
        } catch (Exception e) {
            throw new RuntimeException("Error al introducir el producto\n" + e.getMessage());
        }
    }
    
    public void elimninarProducto(int nproducto) {
        try {
            Producto productoEliminar = null;
            this.ventaGestion.eliminarVentasAsociadas(nproducto);
            //Eliminamos el producto
            for (Producto p : productos) {
                if (p.getId() == nproducto) {
                    productoEliminar = p;
                }
            }
            productos.remove(productoEliminar);

        } catch (Exception e) {
            throw new RuntimeException("imposible eliminar producto");
        }

    }
    
//    public Producto buscarProducto(int np) throws Exception {
//        Producto producto = null;
//        boolean encontrado=false;
//        for (int i = 0; i < productos.size()&&encontrado==false; i++) {
//            if (productos.get(i).getId() == np) {
//                producto = productos.get(i);
//                encontrado=true;
//            }
//        }
//
//        if (producto == null) {
//            throw new Exception("El producto no existe.");
//        }
//        return producto;
//    }
    
    public Producto buscarProducto(int nproducto){
         Producto productoVenta = null;
            for (int i = 0; i < productos.size() && productoVenta == null; i++) {
                if (productos.get(i).getId() == nproducto) {
                    productoVenta = productos.get(i);
                }
            }
            if (productoVenta == null) {
                throw new RuntimeException("El producto no existe.");
            }
        return productoVenta;
    }
    
    
    public String imprimirTodosProductos() {
        String res = "";
        if (productos.isEmpty()) {
            res = "No hay productos introducidos.";

        } else {
            for (Producto p : productos) {
                if (p instanceof Televisor) {
                    Televisor t = (Televisor) p;
                    res += "\n ID NOMBRE  PRECIO  MARCA   FABRICANTE  TAMAÑO   TIPO    PULGADAS" + "\n" + t.getId() + "   " + t.getNombre() + "   " + t.getPrecio() + t.getMarca() + "   " + t.getFabricante() + "   " + t.getTamanyo() + t.getTipotel() + "  " + t.getPulgadas();

                }

                if (p instanceof Lavadora) {
                    Lavadora l = (Lavadora) p;
                    res += "\n ID NOMBRE  PRECIO  MARCA   FABRICANTE  REVOLUCIONES  CARGA" + "\n" + l.getId() + "   " + l.getNombre() + "   " + l.getPrecio() + "  " + l.getMarca() + "         " + l.getFabricante() + "   " + l.getTamanyo() + "     " + l.getRevoluciones() + "     " + l.getCarga();

                }

                if (p instanceof Mueble) {
                    Mueble m = (Mueble) p;
                    res += "\n ID NOMBRE  PRECIO     AÑO FABRICACION              MADERA  ESTILO" + "\n" + m.getId() + "   " + m.getNombre() + "   " + m.getPrecio() + "  " + m.getAnyoFab() + "   " + m.getTipoMadera() + "     " + m.getEstilo();

                }
            }
        }
        return res;
    }
     
     
}
