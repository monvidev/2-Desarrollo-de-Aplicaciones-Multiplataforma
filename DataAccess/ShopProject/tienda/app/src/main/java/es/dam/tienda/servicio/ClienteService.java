package es.dam.tienda.servicio;

import es.dam.tienda.modelo.Cliente;
import es.dam.tienda.repositorio.ClienteRepository;
import java.util.Comparator;
import java.util.List;

public class ClienteService {
    private final ClienteRepository repositorio;

    public ClienteService(ClienteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void alta(Cliente cliente) {
        if (repositorio.existe(cliente.getId())) {
            throw new IllegalStateException("Ya existe un cliente con el ID " + cliente.getId());
        }
        repositorio.guardar(cliente);
    }

    public List<Cliente> listar() {
        return repositorio.buscarTodos().stream()
                .sorted(Comparator.comparing(Cliente::getId))
                .toList();
    }

    public Cliente buscar(int id) {
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe ningún cliente con el ID " + id));
    }

    public void baja(int id) {
        if (!repositorio.borrar(id)) {
            throw new IllegalArgumentException("No existe ningún cliente con el ID " + id);
        }
    }
}