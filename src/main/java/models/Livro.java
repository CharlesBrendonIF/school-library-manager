package models;

import java.time.LocalDate;

public class Livro {

    private boolean disponivel;

    private String nome;
    private String isbn;
    private String autor;
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

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public String getAutor() {
        return autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getGenero() {
        return genero;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getNome() {
        return nome;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }


}
