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

    // Construtor da classe Livro
    public Livro(Long id, String nome, String autor, String isbn, String genero,
                 String descricao, LocalDate dataPublicacao) {

        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.isbn = isbn;
        this.genero = genero;
        this.descricao = descricao;
        this.dataPublicacao = dataPublicacao;

        // Ao criar um livro, ele já inicia como disponível
        this.disponivel = true;
    }

    // Retorna o identificador do livro
    public Long getId() {
        return id;
    }

    // Retorna o nome/título do livro
    public String getNome() {
        return nome;
    }

    // Retorna o autor do livro
    public String getAutor() {
        return autor;
    }

    // Retorna o ISBN do livro
    public String getIsbn() {
        return isbn;
    }

    // Retorna o gênero do livro
    public String getGenero() {
        return genero;
    }

    // Retorna a descrição do livro
    public String getDescricao() {
        return descricao;
    }

    // Retorna a data de publicação do livro
    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    // Verifica se o livro está disponível para empréstimo
    public boolean isDisponivel() {
        return disponivel;
    }

    // Altera o status de disponibilidade do livro
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    // Compara dois livros com base no id
    @Override
    public boolean equals(Object obj) {

        // Verifica se é o mesmo objeto na memória
        if (this == obj) {
            return true;
        }

        // Verifica se o objeto é nulo ou de outra classe
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Livro outro = (Livro) obj;

        // Se o id for nulo, não é possível considerar iguais
        if (this.id == null) {
            return false;
        }

        // Dois livros são iguais se tiverem o mesmo id
        return this.id.equals(outro.id);
    }

    // Representação textual do objeto Livro
    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", disponivel=" + disponivel +
                '}';
    }
}
