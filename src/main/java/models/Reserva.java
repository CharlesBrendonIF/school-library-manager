package models;

import java.time.LocalDateTime;

public class Reserva {
    private Usuario usuario;
    private Titulo titulo;
    private LocalDateTime dataReserva;

    // Construtor padrão
    public Reserva() {
        this.dataReserva = LocalDateTime.now();
    }

    // Construtor parametrizado
    public Reserva(Usuario usuario, Titulo titulo) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.dataReserva = LocalDateTime.now();
    }

    // Getters

    public Usuario getUsuario() {
        return usuario;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataReserva() {
        return dataReserva;
    }

    // Setters

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public void setDataReserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }

    public void mostrarDados() {
        System.out.println("Reserva efetuada em: " + dataReserva);
        if (usuario != null) System.out.println("Usuário: " + usuario.getNome());
        if (titulo != null) System.out.println("Título: " + titulo.getNome());
    }
}

