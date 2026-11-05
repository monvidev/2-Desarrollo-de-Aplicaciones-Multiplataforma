package es.dam.tienda.repositorio;

import es.dam.tienda.modelo.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository {
    void guardar(Pedido pedido);
    Optional<Pedido> buscarPorNumero(int numero);
    List<Pedido> buscarTodos();
    boolean borrar(int numero);

    default boolean existe(int numero) {
        return buscarPorNumero(numero).isPresent();
    }
}