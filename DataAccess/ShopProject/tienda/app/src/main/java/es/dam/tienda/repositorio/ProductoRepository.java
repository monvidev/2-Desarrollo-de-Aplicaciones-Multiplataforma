package es.dam.tienda.repositorio;

import es.dam.tienda.modelo.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoRepository {

    void guardar(Producto producto);                     // alta, o reemplazo si el código ya existe

    Optional<Producto> buscarPorCodigo(String codigo);   // Optional: ver apartado 0.7

    List<Producto> buscarTodos();

    boolean borrar(String codigo);                       // true si existía

    default boolean existe(String codigo) {
        return buscarPorCodigo(codigo).isPresent();
    }
}