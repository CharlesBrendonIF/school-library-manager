package models;

import java.time.LocalDate;

public class Emprestimo {
    private long id;
    private static long idCount=0;
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean atrasado;

    public Emprestimo() {
        this.dataEmprestimo = LocalDate.now();
    }

    public Emprestimo(Usuario usuario, Livro livro) {
        this.id = ++idCount;
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        if (usuario.getTipo()==TipoUsuario.ALUNO)
            this.dataDevolucao = this.dataEmprestimo.plusDays(7);
        else
            this.dataDevolucao = this.dataEmprestimo.plusDays(10);
        this.atrasado = false;
    }

    // Getters

    public boolean isAtrasado() {
        if (!atrasado && dataDevolucao != null && LocalDate.now().isAfter(dataDevolucao)) {
            this.atrasado = true;
        }
        return atrasado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public long getId() {
        return id;
    }



    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    //  Setters
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setAtrasado(boolean atrasado) {
        this.atrasado = atrasado;
    }

    public void mostrarDados() {
        System.out.println("Empréstimo ID: " + id +
                " | Livro: " + (livro != null ? livro.getTitulo().getNome() : "N/A") +
                " | Devolução: " + dataDevolucao +
                " | Atrasado: " + (isAtrasado() ? "Sim" : "Não"));
    }
}
