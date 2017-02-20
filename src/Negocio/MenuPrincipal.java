package Negocio;

import Modelo.Cliente;
import Modelo.FormatoFechaErroneo;
import Modelo.Lavadora;
import Modelo.Mayorista;
import Modelo.Mueble;
import Modelo.Particular;
import Modelo.Producto;
import Modelo.Televisor;
import Modelo.TipoMayorista;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class MenuPrincipal {

    VentasService vServicio;
    ClientesService cServicio;
    ProductosService pServicio;
    

    MenuPrincipal() {
        vServicio = new VentasService();
        cServicio = new ClientesService();
        pServicio = new ProductosService();
        
        pServicio.setVentaGestion(vServicio);
        cServicio.
    }

    public void inciarAplicacion() {
        try {
            // menu Principal
            int opcion = -1;
            do {
                System.out.println("1.Productos");
                System.out.println("2.Clientes");
                System.out.println("3.Ventas");
                System.out.println("0. Para Salir");
                System.out.println("Introduzca la opcion");
                Scanner sc = new Scanner(System.in);
                opcion = sc.nextInt();
                if (opcion == 1) {
                    menuProductos();
                }
                if (opcion == 2) {
                    menuClientes();
                }
                if (opcion == 3) {
                    menuVentas();
                }

            } while (opcion != 0);

            System.out.println("Gracias por usar nuestra aplicacion");
            System.out.println("Hasta la proxima");

        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.inciarAplicacion();
        }
    }

    private void menuProductos() {
        try {
            int opcionProductos = -1;
            do {
                System.out.println("1.Introducir Producto");
                System.out.println("2.Dar de baja un producto");
                System.out.println("3.Imprimir los datos de un producto");
                System.out.println("4.Imprimir todos los productos");
                System.out.println("0. Salir del menu");
                Scanner sc = new Scanner(System.in);
                opcionProductos = sc.nextInt();

                if (opcionProductos == 1) {
                    Producto p = datosProducto();
                    pServicio.introducirProducto(p);
                }
                if (opcionProductos == 2) {
                    System.out.println("Introduzca el número de producto: ");
                    int num = sc.nextInt();
                    pServicio.elimninarProducto(num);
                }
                if (opcionProductos == 3) {
                    System.out.println("Introduzca el número de producto: ");
                    int nprod = sc.nextInt();
                    Producto p = pServicio.buscarProducto(nprod);
                    System.out.println(p.imprimirProducto());
                }
                if (opcionProductos == 4) {
                    System.out.println(pServicio.imprimirTodosProductos());
                }

            } while (opcionProductos != 0);
        } catch (Exception e) {
            System.out.println("Opcion no valida" + e.getMessage());
            this.menuProductos();
        }
    }

    public Producto datosProducto() throws Exception {
        Scanner sc = new Scanner(System.in);
        Producto producto = null;
        String nombre;
        double precio;
        int opcion = -1;
        do {
            System.out.println("Introduzca el nombre: ");
            nombre = sc.nextLine();

            System.out.println("Introduzca precio base: ");
            precio = Double.parseDouble(sc.nextLine());

            System.out.println("Introduzca el tipo de producto: ");
            System.out.println("1. Mueble");
            System.out.println("2. Lavadora");
            System.out.println("3. Televisor");
            opcion = sc.nextInt();
            if (opcion == 1) {
                producto = pedirMueble();
            }
            if (opcion == 2) {
                producto = pedirLavadora();
            }
            if (opcion == 3) {
                producto = pedirTelevisor();
            }
            if (producto != null) {
                producto.setNombre(nombre);
                producto.setPrecio(precio);
            }

        } while (opcion != 1 && opcion != 2 && opcion != 3);

        return producto;
    }

    public Mueble pedirMueble() throws ParseException {
        Mueble m = new Mueble();
        Scanner sc = new Scanner(System.in);

        m.setTipoMadera(pedirMadera());

        System.out.println("Introduzca el estilo:");
        m.setEstilo(sc.nextLine());

        System.out.println("Introduzca la fecha (dd-MMMM-yy): ");
       // Habría que introducir un do while para que te pida introducir la fecha
       // hasta que ésta sea correcta. Si no, el objeto mueble aunque se creará,
        try {
            m.setAnyoFab(this.validarFecha(sc.nextLine()));
        } catch (FormatoFechaErroneo f) {
            System.out.println(f.getMessage());
        }

        return m;

    }

    public Lavadora pedirLavadora() {
        Scanner sc = new Scanner(System.in);
        Lavadora l = new Lavadora();

        System.out.println("Introduzca las revoluciones(rpm): ");
        int rev = Integer.parseInt(sc.nextLine());
        l.setRevoluciones(rev);

        System.out.println("Introduzca la capacidad (kg): ");
        int c = Integer.parseInt(sc.nextLine());
        l.setCarga(c);

        return l;
    }

    public Televisor pedirTelevisor() throws ParseException {
        Televisor t = new Televisor();
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca las pulgadas: ");
        double pulgadas = Double.parseDouble(sc.nextLine());
        t.setPulgadas(pulgadas);

        t.setTipotel(pedirTipoTelevisor());

        return t;

    }

    public Televisor.TipoTelevisor pedirTipoTelevisor() {
        Scanner sc = new Scanner(System.in);
        Televisor tv = new Televisor();
        Televisor.TipoTelevisor t = null;
        String opcion;
        
        do {
            System.out.println("Introduzca el tipo de Televisor");
            int num = 0;
            for(Televisor.TipoTelevisor tel: Televisor.TipoTelevisor.values()){
                ++num;
                System.out.println(num+"."+ tel);
            }

            opcion = sc.nextLine();

        } while (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3") && !opcion.equals("4"));

        if (opcion.equals("1")) {
            t = Televisor.TipoTelevisor.PLASMA;
        }
        if (opcion.equals("2")) {
            t = Televisor.TipoTelevisor.LED;
        }
        if (opcion.equals("3")) {
            t = Televisor.TipoTelevisor.LCD;
        }
        if (opcion.equals("4")) {
            t = Televisor.TipoTelevisor.OLED;
        }

        return t;
    }

    private Mueble.Madera pedirMadera() {
        Mueble.Madera m = null;
        String opcion;
        Scanner sc = new Scanner(System.in);
        int num = 0;
        int opcionI;
        Mueble.Madera mad;
        do {
            System.out.println("Introduzca el tipo de Madera");
            for(Mueble.Madera mue: Mueble.Madera.values()){
                ++num;
                System.out.println(num+"."+ mue);
                
            }
            
            opcion = sc.nextLine();
           
            mad=Mueble.Madera.valueOf(opcion);
            
            
        } while (opcion!=mad);
        
        if (opcion.equals("1")) {
            m = Mueble.Madera.PINO;
        }
        if (opcion.equals("2")) {
            m = Mueble.Madera.ROBLE;
        }
        if (opcion.equals("3")) {
            m = Mueble.Madera.HAYA;
        }
        
        return m;
    }

    private void menuClientes() {
        Scanner sc = new Scanner(System.in);

        try {
            int opcionClientes = -1;
            do {
                System.out.println("1.Introducir Cliente");
                System.out.println("2.Dar de baja un cliente");
                System.out.println("3.Imprimir los datos de un cliente");
                System.out.println("4.Imprimir todos los clientes");
                System.out.println("0. Salir del menu");

                opcionClientes = sc.nextInt();
                if (opcionClientes == 1) {
                    Cliente c = datosCliente();
                    cServicio.introducirCliente(c);
                }
                if (opcionClientes == 2) {
                    System.out.println("Introduzca el número de cliente: ");
                    int num = sc.nextInt();
                    cServicio.eliminarCliente(num);
                }
                if (opcionClientes == 3) {
                    System.out.println("Introduzca el número de cliente: ");
                    int nclient = sc.nextInt();
                    System.out.println(cServicio.buscarCliente(nclient));
                }
                if (opcionClientes == 4) {
                    System.out.println(cServicio.imprimirTodosClientes());
                }

            } while (opcionClientes != 0);
        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.menuClientes();
        }

    }

    public Cliente datosCliente() throws Exception {
        Scanner sc = new Scanner(System.in);
        Cliente cliente = null;
        String nombre;
        String razonSocial;
        int opcion = -1;
        do {
            System.out.println("Introduzca el nombre: ");
            nombre = sc.nextLine();

            System.out.println("Introduzca razón social: ");
            razonSocial = sc.nextLine();

            System.out.println("Introduzca el tipo de cliente: ");
            int num = 0;
            for(TipoMayorista tip: TipoMayorista.values()){
                ++num;
                System.out.println(num+"."+ tip);
            }
            
            opcion = sc.nextInt();
            if (opcion == 1) {
                cliente = Mayoristas();
            }
            if (opcion == 2) {
                cliente = Particulares();
            }

            if (cliente != null) {
                cliente.setNombre(nombre);
                cliente.setRazonSocial(razonSocial);
            }

        } while (opcion != 1 && opcion != 2);

        return cliente;
    }

    public Mayorista Mayoristas() {
        Scanner sc = new Scanner(System.in);
        Mayorista m = new Mayorista();
        String opcion;

        System.out.println("Introduzca el CIF: ");
        String cif = sc.nextLine();
        m.setCif(cif);

        do {
            System.out.println("Introduzca el tipo de mayorista");
            int num = 0;
            for(TipoMayorista tip: TipoMayorista.values()){
                ++num;
                System.out.println(num+"."+ tip);
            }

            opcion = sc.nextLine();

        } while (!opcion.equals("1") && !opcion.equals("2"));

        if (opcion.equals("1")) {
            m.setTipoMayorista(TipoMayorista.GRAN_SUPERFICIE);
        }
        if (opcion.equals("2")) {
            m.setTipoMayorista(TipoMayorista.TIENDA);
        }

        System.out.println("Introduzca el descuento: ");
        double descuento = Double.parseDouble(opcion);
        m.setDescuento(descuento);

        return m;
    }

    public Particular Particulares() {
        Scanner sc = new Scanner(System.in);
        Particular p = new Particular();
        String opcion;

        System.out.println("Introduzca el dni: ");
        String dni = sc.nextLine();
        p.setDni(dni);

        return p;
    }
    
    public String calculoLetraDNI(){
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        System.out.println("Introduzca la letra del DNI: ");
        Scanner sc = new Scanner(System.in);
        String letra = sc.nextLine();
        int index = dni - (Math.abs(dni / 23) * 23);
        System.out.println("Su letra de DNI es: " + letras.charAt(index));
    
    }
    
    
    private void menuVentas() {
        Scanner sc = new Scanner(System.in);

        try {
            String opcionVentas = "-1";
            do {
                System.out.println("1.Introducir Venta");
                System.out.println("2.Dar de baja una venta");
                System.out.println("3.Imprimir los datos de una venta");
                System.out.println("4.Imprimir todas las ventas");
                System.out.println("0. Salir del menu");
                opcionVentas = sc.nextLine();

                if (opcionVentas.equals("1")) {
                    System.out.println("Introduce el número de cliente.");
                    int nv = Integer.parseInt(sc.nextLine());
                    System.out.println("Introduce el número de producto.");
                    int np = Integer.parseInt(sc.nextLine());
                    System.out.println("Introduce el nombre del vendedor: ");
                    String v = sc.nextLine();
                    vServicio.introducirVenta(nv, np, v);
                }
                if (opcionVentas.equals("2")) {
                    System.out.println("Introduzca número de venta: ");
                    int nv = Integer.parseInt(sc.nextLine());
                    vServicio.eliminarVenta(nv);
                }
                if (opcionVentas.equals("3")) {
                    System.out.println("Introduzca número de venta: ");
                    int nv = Integer.parseInt(sc.nextLine());
                    vServicio.buscarVenta(nv);
                }
                if (opcionVentas.equals("4")) {
                    System.out.println(vServicio.imprimirtodasVentas());
                }

            } while (!opcionVentas.equals("0"));

        } catch (Exception e) {
            System.out.println("Opcion no valida"+e.getMessage());
            this.menuVentas();
        }

    }

    private LocalDate validarFecha(String fecha) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MMMM-yy");
        LocalDate fec = null;
        try {
            fec = LocalDate.parse(fecha, formato);
        } catch (DateTimeParseException e) {
            throw new FormatoFechaErroneo("La fecha introducida " + fecha + "es incorrecta");
        }

        return fec;
    }

}

// DNI 8 letras: Calculamos la letra. Si tiene 9 calcula la letra comparamos si no es igual
// DNI ERRONEO throw
//Metodo comprobar NIF
