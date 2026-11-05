package es.dam.tienda.consola;

import es.dam.tienda.modelo.EstadoPedido;
import es.dam.tienda.modelo.Pedido;
import es.dam.tienda.servicio.PedidoService;

public class MenuPedidos {
    private final PedidoService servicio;
    private final Consola consola;

    public MenuPedidos(PedidoService servicio, Consola consola) {
        this.servicio = servicio;
        this.consola = consola;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("""
                    === Pedidos ===
                    1. Listar pedidos
                    2. Buscar por número
                    3. Hacer pedido
                    4. Eliminar pedido
                    0. Volver al menú principal""");
            opcion = consola.leerEntero("Opción: ", 0, 4);
            try {
                switch (opcion) {
                    case 1 -> listar();
                    case 2 -> buscar();
                    case 3 -> hacerPedido();
                    case 4 -> eliminarPedido();
                    case 0 -> System.out.println("Volviendo...");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(" Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void listar() {
        var pedidos = servicio.listar();
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        for (Pedido p : pedidos) {
            System.out.println(String.format("%-6d %-12s %s",
                    p.getNumero(), p.getFecha(), p.getEstado()));
        }
    }

    private void buscar() {
        int numero = consola.leerEntero("Número de pedido: ", 1, 1_000_000);
        System.out.println(" " + servicio.buscar(numero));
    }

    private void hacerPedido() {
        int numero = consola.leerEntero("Número de pedido: ", 1, 1_000_000);
        System.out.println("Seleccione el estado inicial del pedido:");
        System.out.println("1. PENDIENTE");
        System.out.println("2. PAGADO");
        int seleccionEstado = consola.leerEntero("Opción (1-2): ", 1, 2);
        EstadoPedido estado = (seleccionEstado == 1) ? EstadoPedido.PENDIENTE : EstadoPedido.PAGADO;

        servicio.hacerPedido(new Pedido(numero, estado));
        System.out.println(" Pedido realizado con éxito.");
    }

    private void eliminarPedido() {
        int numero = consola.leerEntero("Número de pedido a eliminar: ", 1, 1_000_000);
        servicio.eliminarPedido(numero);
        System.out.println(" Pedido eliminado con éxito.");
    }
}