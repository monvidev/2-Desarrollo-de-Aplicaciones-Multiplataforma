package es.dam.tienda.repositorio;

import es.dam.tienda.modelo.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    void guardar(Cliente cliente);
    Optional<Cliente> buscarPorId(int id);
    List<Cliente> buscarTodos();
    boolean borrar(int id);

    default boolean existe(int id) {
        return buscarPorId(id).isPresent();
    }
}