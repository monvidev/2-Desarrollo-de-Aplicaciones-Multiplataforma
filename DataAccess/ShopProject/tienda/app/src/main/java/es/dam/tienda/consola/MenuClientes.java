package es.dam.tienda.consola;

import es.dam.tienda.modelo.Cliente;
import es.dam.tienda.servicio.ClienteService;

public class MenuClientes {
    private final ClienteService servicio;
    private final Consola consola;

    public MenuClientes(ClienteService servicio, Consola consola) {
        this.servicio = servicio;
        this.consola = consola;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("""
                    === Clientes ===
                    1. Listar
                    2. Buscar por ID
                    3. Alta
                    4. Baja
                    0. Volver al menú principal""");
            opcion = consola.leerEntero("Opción: ", 0, 4);
            try {
                switch (opcion) {
                    case 1 -> listar();
                    case 2 -> buscar();
                    case 3 -> alta();
                    case 4 -> baja();
                    case 0 -> System.out.println("Volviendo...");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(" Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void listar() {
        var clientes = servicio.listar();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        for (Cliente c : clientes) {
            System.out.println(String.format("%-4d %-20s %-25s %s",
                    c.getId(), c.getNombre(), c.getEmail(), c.getFechaAlta()));
        }
    }

    private void buscar() {
        int id = consola.leerEntero("ID: ", 1, 1_000_000);
        System.out.println(" " + servicio.buscar(id));
    }

    private void alta() {
        int id = consola.leerEntero("ID: ", 1, 1_000_000);
        String nombre = consola.leerTexto("Nombre: ");
        String email = consola.leerTexto("Email: ");
        servicio.alta(new Cliente(id, nombre, email));
        System.out.println(" Cliente dado de alta.");
    }

    private void baja() {
        int id = consola.leerEntero("ID: ", 1, 1_000_000);
        servicio.baja(id);
        System.out.println(" Cliente eliminado.");
    }
}