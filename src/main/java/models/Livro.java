package models;

import java.time.LocalDate;

public class Livro {

    private boolean disponivel;
    ///Adaptar a classe, adicionando metodos getters e setters para esses atributos
    private String nome;
    private String isbn;
    private String genero;
    private String descricao;
    private LocalDate dataPublicacao;
    private static long idCount=0;
    private long id;

    public Livro(String nome, String isbn, String genero, boolean disponivel, String descricao,
                 LocalDate dataPublicacao) {
        this.nome = nome;
        this.isbn = isbn;
        this.genero = genero;
        this.disponivel = disponivel;
        this.descricao = descricao;
        this.dataPublicacao = dataPublicacao;
        this.id= ++idCount;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /// Refazer com os atributos atuais
    public void mostrarDados() {
    }
}
