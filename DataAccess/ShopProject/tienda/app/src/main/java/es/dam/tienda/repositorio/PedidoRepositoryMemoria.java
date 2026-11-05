package es.dam.tienda.repositorio;

import es.dam.tienda.modelo.Pedido;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PedidoRepositoryMemoria implements PedidoRepository {
    private final Map<Integer, Pedido> pedidos = new LinkedHashMap<>();

    @Override
    public void guardar(Pedido pedido) {
        pedidos.put(pedido.getNumero(), pedido);
    }

    @Override
    public Optional<Pedido> buscarPorNumero(int numero) {
        return Optional.ofNullable(pedidos.get(numero));
    }

    @Override
    public List<Pedido> buscarTodos() {
        return List.copyOf(pedidos.values());
    }

    @Override
    public boolean borrar(int numero) {
        return pedidos.remove(numero) != null;
    }
}