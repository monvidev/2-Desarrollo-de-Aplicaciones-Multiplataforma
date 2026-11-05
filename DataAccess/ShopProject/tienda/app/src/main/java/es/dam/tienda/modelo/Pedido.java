package es.dam.tienda.modelo;

import java.time.LocalDate;

public class Pedido {
    private int numero;
    private LocalDate fecha;
    private EstadoPedido estado;

    public Pedido(int numero, LocalDate fecha, EstadoPedido estado) {
        if (numero <= 0) {
            throw new IllegalArgumentException("El número de pedido debe ser positivo");
        }
        if (estado == null) {
            throw new IllegalArgumentException("El estado del pedido es obligatorio");
        }
        this.numero = numero;
        this.fecha = fecha != null ? fecha : LocalDate.now();
        this.estado = estado;
    }

    public Pedido(int numero, EstadoPedido estado) {
        this(numero, LocalDate.now(), estado);
    }

    public int getNumero() { return numero; }

    public LocalDate getFecha() { return fecha; }

    public EstadoPedido getEstado() { return estado; }

    public void setEstado(EstadoPedido estado) { this.estado = estado; }

    public void setNumero(int numero) { this.numero = numero; }

    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    @Override
    public String toString() {
        return "Pedido[Número=" + numero + ", Fecha=" + fecha + ", Estado=" + estado + "]";
    }
}