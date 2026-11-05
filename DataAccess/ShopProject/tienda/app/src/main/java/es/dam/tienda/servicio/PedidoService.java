package es.dam.tienda.servicio;

import es.dam.tienda.modelo.Pedido;
import es.dam.tienda.repositorio.PedidoRepository;
import java.util.Comparator;
import java.util.List;

public class PedidoService {
    private final PedidoRepository repositorio;

    public PedidoService(PedidoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void hacerPedido(Pedido pedido) {
        if (repositorio.existe(pedido.getNumero())) {
            throw new IllegalStateException("Ya existe un pedido con el número " + pedido.getNumero());
        }
        repositorio.guardar(pedido);
    }

    public List<Pedido> listar() {
        return repositorio.buscarTodos().stream()
                .sorted(Comparator.comparing(Pedido::getNumero))
                .toList();
    }

    public Pedido buscar(int numero) {
        return repositorio.buscarPorNumero(numero)
                .orElseThrow(() -> new IllegalArgumentException("No existe ningún pedido con el número " + numero));
    }

    public void eliminarPedido(int numero) {
        if (!repositorio.borrar(numero)) {
            throw new IllegalArgumentException("No existe ningún pedido con el número " + numero);
        }
    }
}