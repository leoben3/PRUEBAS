/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Modelo.Cliente;
import Modelo.Mayorista;
import Modelo.Particular;
import Modelo.Venta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daw
 */
public class ClientesService {
    private List<Cliente> clientes;
    private VentasService ventaGestion;
    
    ClientesService() {
        clientes = new ArrayList();
    }

    public void setVentaGestion(VentasService ventaGestion) {
        this.ventaGestion = ventaGestion;
    }
    
    public void introducirCliente(Cliente c) {
        try {
            clientes.add(c);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
    
    public Cliente buscarCliente(int nCliente) {
        Cliente clienteVenta = null;
        for (int i = 0; i < clientes.size() && clienteVenta == null; i++) {
            if (clientes.get(i).getIdCliente() == nCliente) {
                clienteVenta = clientes.get(i);
            }
        }
        if (clienteVenta == null) {
            throw new RuntimeException("El cliente no existe.");
        }
        return clienteVenta;
    }
    
    public void eliminarCliente(int numCliente) {
        try {
   
            this.ventaGestion.eliminarVentasClientes(numCliente);
            // Al eliminar un cliente también eliminamos las ventas asociadas a el

            //Eliminamos el cliente
            Cliente clienteBorrar = null;
            for (int i = 0; i < clientes.size() && clienteBorrar == null; i++) {
                if (clientes.get(i).getIdCliente() == numCliente) {
                    clienteBorrar = clientes.get(i);
                }
            }

            clientes.remove(clienteBorrar);

        } catch (Exception e) {
            throw new RuntimeException("imposible eliminar cliente");
        }

    }
    public String imprimirTodosClientes() {
        String res = "";
        if (clientes.isEmpty()) {
            res = "No hay clientes introducidos.";

        } else {
            for (Cliente c : clientes) {

                if (c instanceof Mayorista) {
                    Mayorista m = (Mayorista) c;
                    res += "\n ID NOMBRE  RAZON SOCIAL  CIF   TIPO  DESCUENTO" + "\n" + m.getIdCliente() + "   " + m.getNombre() + "   " + m.getRazonSocial() + "   " + m.getCif() + "   " + m.getTipoMayorista() + "   " + m.getDescuento();

                }
                if (c instanceof Particular) {
                    Particular p = (Particular) c;
                    res += "\n ID NOMBRE  RAZON SOCIAL  DNI" + "\n" + p.getIdCliente() + "   " + p.getNombre() + "      " + p.getRazonSocial() + "   " + p.getDni();

                }
            }
        }
        return res;
    }
}
