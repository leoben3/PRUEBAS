/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Modelo.Cliente;
import Modelo.Producto;
import Modelo.Venta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daw
 */
public class VentasService {
    private List<Venta> ventas;
    private ClientesService gestionClientes;
    private ProductosService gestionProductos;
    
    VentasService() {
        ventas = new ArrayList();
    }

    public void setGestionClientes(ClientesService gestionClientes) {
        this.gestionClientes = gestionClientes;
    }

    public void setGestionProductos(ProductosService gestionProductos) {
        this.gestionProductos = gestionProductos;
    }
    
    public void introducirVenta(int ncliente, int nproducto, String vend) {
        try {
            Cliente c= this.gestionClientes.buscarCliente(ncliente);
            Producto p= this.gestionProductos.buscarProducto(nproducto);
            
            Venta v = new Venta();
            v.setCliente(c);
            v.setVendedor(vend);
           
            v.setProducto(p);
            v.setPrecioVenta(); //calcula el precio de la venta segun el cliente-mayorista

            ventas.add(v);
            c.getCompras().add(v);
            p.getVentas().add(v);

        } catch (Exception e) {
            throw new RuntimeException("No ha sido posible introducir la venta" + e.getMessage());
        }

    }
    
    public void buscarVenta(int nv) {
        try {

            Venta venta = null;
            boolean encontrado=false;
            for (int i = 0; i < ventas.size()&&encontrado==false; i++) {
                if (ventas.get(i).getIdVenta() == nv) {
                    venta = ventas.get(i);
                    encontrado=true;
                }
            }
            if (venta == null) {
                throw new Exception("La venta con id: " + nv + " no existe");
            }
            venta.imprimirVenta();
        } catch (Exception e) {
            throw new RuntimeException("No ha sido posible imprimir la venta" + e.getMessage());
        }
    }
    
    public boolean eliminarVentasAsociadas (int nproducto){
         //Eliminamos de ventas el producto seleccionado
            List<Venta> ventasEliminar = new ArrayList();
            for (Venta v : ventas) {
                if (v.getProducto().getId() == nproducto) {
                    ventasEliminar.add(v);
                }
            }
            return ventas.removeAll(ventasEliminar);
    }
    
    public boolean eliminarVentasClientes (int numCliente){
    // Al eliminar un cliente también eliminamos las ventas asociadas a el
            //Eliminamos las ventas del cliente seleccionado
            List<Venta> ventasEliminar = new ArrayList();
            for (Venta v : ventas) {
                if (v.getCliente().getIdCliente() == numCliente) {
                    ventasEliminar.add(v);
                }
            }
        return ventas.removeAll(ventasEliminar);
    }
            
    public void eliminarVenta(int nv) {

        try {

            Venta ventaBorrar = null;
            for (int i = 0; i < ventas.size() && ventaBorrar==null; i++) {

                if (ventas.get(i).getIdVenta() == nv) {
                    ventaBorrar = ventas.get(i);

                }
            }
            if (ventaBorrar == null) {
                // hacemos saltar una excepcion que nos lleva directamente al catch
                throw new Exception("No existe ninguna venta con ese Id");
            }
            // este código solo se ejecuta si todo va bien
            ventas.remove(ventaBorrar);

        } catch (Exception e) {
            throw new RuntimeException("imposible eliminar la venta");
        }

    }
    
    public String imprimirtodasVentas() {
        String res = "";
        if (ventas.isEmpty()) {
            res = "No hay ventas introducidas.";

        } else {
            for (Venta v : ventas) {
                res += "\n ID VENTA VENDEDOR  CLIENTE PRODUCTO PRECIO VENTA" + "\n" + v.getIdVenta() + "   " + v.getVendedor() + "   " + v.getCliente().getIdCliente() + "  " + v.getProducto().getId() + "   " + v.getPrecioVenta();

            }
            
        }
        return res;
    }
    
    
}
