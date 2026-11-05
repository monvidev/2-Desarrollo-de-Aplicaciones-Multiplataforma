package es.dam.tienda.modelo;

import java.time.LocalDate;


public class Cliente {
    private int id;
    private String nombre;
    private String email;
    private LocalDate fechaAlta;

    public Cliente(int id, String nombre, String email, LocalDate fechaAlta) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El email debe contener un '@'");
        }
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.fechaAlta = fechaAlta != null ? fechaAlta : LocalDate.now();
    }

    public Cliente(int id, String nombre, String email) {
        this(id, nombre, email, LocalDate.now());
    }

    public int getId() { return id; }

    public String getNombre() { return nombre; }

    public LocalDate getFechaAlta() { return fechaAlta; }

    public String getEmail() { return email; }

    public void setFechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }

    public void setId(int id) { this.id = id; }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El email debe contener un '@'");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente[ID=" + id + ", Nombre=" + nombre + ", Email=" + email + ", Fecha Alta=" + fechaAlta + "]";
    }
}