package es.dam.tienda.repositorio;

import es.dam.tienda.modelo.Cliente;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ClienteRepositoryMemoria implements ClienteRepository {
    private final Map<Integer, Cliente> clientes = new LinkedHashMap<>();

    @Override
    public void guardar(Cliente cliente) {
        clientes.put(cliente.getId(), cliente);
    }

    @Override
    public Optional<Cliente> buscarPorId(int id) {
        return Optional.ofNullable(clientes.get(id));
    }

    @Override
    public List<Cliente> buscarTodos() {
        return List.copyOf(clientes.values());
    }

    @Override
    public boolean borrar(int id) {
        return clientes.remove(id) != null;
    }
}