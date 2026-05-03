package models;

import java.time.LocalDate;

public class Livro {

    private Long id;
    private String nome;
    private String autor;
    private String isbn;
    private String genero;
    private String descricao;
    private LocalDate dataPublicacao;
    private boolean disponivel;

    // Construtor
    public Livro(Long id, String nome, String autor, String isbn, String genero,
                 String descricao, LocalDate dataPublicacao) {

        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.isbn = isbn;
        this.genero = genero;
        this.descricao = descricao;
        this.dataPublicacao = dataPublicacao;
        this.disponivel = true;
    }

    // --- Getters ---
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenero() {
        return genero;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    // Retorna se o livro está disponível
    public boolean isDisponivel() {
        return disponivel;
    }

    // Define se o livro está disponível
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    // Compara dois livros pelo id
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Livro outro = (Livro) obj;

        if (this.id == null) {
            return false;
        }

        return this.id.equals(outro.id);
    }

    // Gera código hash baseado no id
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // Retorna representação em texto
    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", disponivel=" + disponivel +
                '}';
    }
}
