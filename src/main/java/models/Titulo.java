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
        this.quantidade=listaDeExemplares.quantidade();
        this.quantidaDeReservas=0;
        this.quantidadeDisponivel=quantidade;

        this.listaDeEmprestimos= new EmprestimoDAOLista(quantidadeDisponivel);//Quantidade maxima de Emprestimos  precisa ser a a quantidade disponivel menos 1 pois pelo menos um exemplar deve estar presente na biblioteca
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

    public Livro getExemplarDisponivel(){
        for(Livro l:listaDeExemplares.listar()){
            if(l.isDisponivel()){
                return l;
            }
        }
        return null;
    }


    public void registrarEmprestimo(Emprestimo novoEmprestimo){/// Retornar exception caso quantidade disponvel seja null
        listaDeEmprestimos.salvar(novoEmprestimo);
        quantidadeDisponivel--;

    }


    public Emprestimo removerEmprestimo(Emprestimo e){/// Fazer esse metodo aqui rsrsrsrs
        if(novoEmprestimo==null){/// Retornar exception caso quantidade disponvel seja null
            return null;
        }

        for(Emprestimo emprestimo: listaDeEmprestimos.listar()){
            if(e.getId()==emprestimo.getId()){

            }
        }
        quantidadeDisponivel++;

    }

}
