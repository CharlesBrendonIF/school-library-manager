package br.edu.ifba.models;

import br.edu.ifba.enums.TipoUsuario;

import java.time.LocalDate;
import java.time.ZoneId;

public class Emprestimo {
    private long id;
    private static long idCount = 0;
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean atrasado;

    // Fuso horário fixo
    private static final ZoneId ZONA = ZoneId.of("America/Sao_Paulo");

    public Emprestimo() {
        /// Define data atual no momento da criação
        this.dataEmprestimo = LocalDate.now(ZONA);
    }

    public Emprestimo(Usuario usuario, Livro livro) {
        /// Gera ID automático
        this.id = ++idCount;

        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now(ZONA);

        /// Define prazo de devolução conforme tipo de usuário
        if (usuario != null && usuario.getTipo() == TipoUsuario.ALUNO) {
            this.dataDevolucao = this.dataEmprestimo.plusDays(7);
        } else {
            this.dataDevolucao = this.dataEmprestimo.plusDays(10);
        }

        this.atrasado = false;
    }

    // Getters

    public long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isAtrasado() {
        /// Atualiza automaticamente o status de atraso
        if (!atrasado && dataDevolucao != null && LocalDate.now(ZONA).isAfter(dataDevolucao)) {
            this.atrasado = true;
        }
        return atrasado;
    }

    // Setters

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
        /// Exibe informações do empréstimo
        System.out.println("Empréstimo ID: " + id +
                " | Livro: " + (livro != null && livro.getNome() != null ? livro.getNome() : "N/A") +
                " | Devolução: " + dataDevolucao +
                " | Atrasado: " + (isAtrasado() ? "Sim" : "Não"));
    }
}
