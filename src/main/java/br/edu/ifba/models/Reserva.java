package br.edu.ifba.models;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Reserva {
    private Usuario usuario;
    private Titulo titulo;
    private LocalDateTime dataReserva;
    private static long idCount=0;
    private long id;
    private static long Maximo_Reservas=1_000_000;

    // Fuso horário fixo
    private static final ZoneId ZONA = ZoneId.of("America/Sao_Paulo");

    // Construtor padrão
    public Reserva() {
        this.dataReserva = LocalDateTime.now(ZONA);
    }

    // Construtor parametrizado
    public Reserva(Usuario usuario, Titulo titulo) {
        if(idCount == Maximo_Reservas){
            throw new IllegalStateException("Limite máximo de reservas atingido");
        }

        this.usuario = usuario;
        this.titulo = titulo;
        this.dataReserva = LocalDateTime.now(ZONA);
        this.id= ++idCount;
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

    public long getId() {
        return id;
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
        if (titulo != null && titulo.getNome() != null) System.out.println("Título: " + titulo.getNome());
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "usuario=" + usuario.getNome() +
                '}';
    }
}
