package models;
import java.time.LocalDate;

import repository.dao.EmprestimoDAOLista;
import repository.dao.LivroDAOLista;
import repository.dao.ReservaDAOFilaDePrioridade;
import repository.dao.ReservaDAOLista;

public class Titulo {
    private String nome;
    private String autor;
    private String isbn;
    private String genero;
    private String descricao;
    private LocalDate dataPublicacao;
    private int quantidade;
    private int quantidaDeReservas;
    private int quantidadeDisponivel;

    public LivroDAOLista listaDeExemplares;
    public ReservaDAOFilaDePrioridade filaDeReservas;
    public EmprestimoDAOLista listaDeEmprestimos;

    public Titulo(LivroDAOLista listaDeExemplares) {
        if(listaDeExemplares==null){//Quando a lista estiver vazia
            throw new NullPointerException();
        }
        this.listaDeExemplares = listaDeExemplares;
        Livro modelo=listaDeExemplares.selecionar(0);

        this.nome = modelo.getNome();
        this.isbn = modelo.getIsbn();
        this.genero = modelo.getGenero();
        this.descricao = modelo.getDescricao();
        this.dataPublicacao = modelo.getDataPublicacao();
        this.autor = modelo.getAutor();
        this.quantidade=listaDeExemplares.getTamanho();
        this.quantidaDeReservas=0;
        this.quantidadeDisponivel=quantidade-1;

        this.listaDeEmprestimos= new EmprestimoDAOLista(quantidade-1);//Quantidade maxima de Emprestimos  precisa ser a a quantidade disponivel menos 1 pois pelo menos um exemplar deve estar presente na biblioteca
        this.filaDeReservas= new ReservaDAOFilaDePrioridade();
    }

    // --- Getters ---
    public String getNome() { return nome; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public String getGenero() { return genero; }
    public String getDescricao() { return descricao; }
    public LocalDate getDataPublicacao() { return dataPublicacao; }
    public int getQuantidadeDeExemplares() { return quantidade; }
    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }


}
