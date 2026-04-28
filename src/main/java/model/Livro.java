package model;

public class Livro {
    private String id;
    private boolean disponivel;
    private Titulo titulo;

    // Construtor padrão
    public Livro() {}

    // Construtor parametrizado
    public Livro(String id, Titulo titulo) {
        this.id = id;
        this.titulo = titulo;
        this.disponivel = true;
    }

    // Getters
    public String getId() {
        return id;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void mostrarDados() {
        String nomeTitulo = (titulo != null) ? titulo.getNome() : "Título não definido";
        System.out.println("ID Exemplar: " + id +
                " | Título: " + nomeTitulo +
                " | Status: " + (disponivel ? "Disponível" : "Emprestado"));
    }
}
