package es.dam.tienda;

import es.dam.tienda.consola.Consola;
import es.dam.tienda.consola.MenuClientes;
import es.dam.tienda.consola.MenuPedidos;
import es.dam.tienda.consola.MenuProductos;
import es.dam.tienda.modelo.Cliente;
import es.dam.tienda.modelo.EstadoPedido;
import es.dam.tienda.modelo.Pedido;
import es.dam.tienda.modelo.Producto;
import es.dam.tienda.repositorio.ClienteRepository;
import es.dam.tienda.repositorio.ClienteRepositoryMemoria;
import es.dam.tienda.repositorio.PedidoRepository;
import es.dam.tienda.repositorio.PedidoRepositoryMemoria;
import es.dam.tienda.repositorio.ProductoRepository;
import es.dam.tienda.repositorio.ProductoRepositoryMemoria;
import es.dam.tienda.servicio.ClienteService;
import es.dam.tienda.servicio.PedidoService;
import es.dam.tienda.servicio.ProductoService;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Punto de entrada: crea las piezas, las conecta y arranca el menú. */
public class App {

    public static void main(String[] args) {
        // El único sitio del programa que conoce la implementación concreta
        ProductoRepository productoRepo = new ProductoRepositoryMemoria();
        ProductoService productoServicio = new ProductoService(productoRepo);
        cargarDatosDeEjemplo(productoServicio);

        ClienteRepository clienteRepo = new ClienteRepositoryMemoria();
        ClienteService clienteServicio = new ClienteService(clienteRepo);
        cargarClientesDeEjemplo(clienteServicio);

        PedidoRepository pedidoRepo = new PedidoRepositoryMemoria();
        PedidoService pedidoService = new PedidoService(pedidoRepo);
        cargarPedidosEjemplo(pedidoService);

        Consola consola = new Consola();
        MenuProductos menuProductos = new MenuProductos(productoServicio, consola);
        MenuClientes menuClientes = new MenuClientes(clienteServicio, consola);
        MenuPedidos menuPedidos = new MenuPedidos(pedidoService, consola);

        int opcion;
        do {
            System.out.println("""
                    === MENÚ PRINCIPAL ===
                    1. Gestión de Productos
                    2. Gestión de Clientes
                    3. Gestión de Pedidos
                    0. Salir""");
            opcion = consola.leerEntero("Seleccione una opción: ", 0, 3);
            switch (opcion) {
                case 1 -> menuProductos.mostrar();
                case 2 -> menuClientes.mostrar();
                case 3 -> menuPedidos.mostrar();
                case 0 -> System.out.println("¡Hasta pronto!");
            }
        } while (opcion != 0);
    }

    private static void cargarDatosDeEjemplo(ProductoService servicio) {
        servicio.alta(new Producto("TEC01", "Teclado mecánico", new BigDecimal("59.90"), 12));
        servicio.alta(new Producto("RAT01", "Ratón inalámbrico", new BigDecimal("24.50"), 30));
        servicio.alta(new Producto("MON01", "Monitor 27 pulgadas", new BigDecimal("219.00"), 4));
        servicio.alta(new Producto("CAB01", "Cable USB-C 2 m", new BigDecimal("8.95"), 0));
    }

    private static void cargarClientesDeEjemplo(ClienteService servicio) {
        servicio.alta(new Cliente(1, "Ana López", "ana.lopez@example.com", LocalDate.of(2026, 1, 15)));
        servicio.alta(new Cliente(2, "Luis Gómez", "luis.gomez@example.com", LocalDate.of(2026, 3, 10)));
    }
    
    private static void cargarPedidosEjemplo(PedidoService servicio) {
        servicio.hacerPedido(new Pedido(1001, LocalDate.of(2026, 9, 20), EstadoPedido.PAGADO));
        servicio.hacerPedido(new Pedido(1002, LocalDate.of(2026, 10, 5), EstadoPedido.PENDIENTE));
    }
}