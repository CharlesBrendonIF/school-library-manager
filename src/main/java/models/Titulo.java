package models;
import java.time.LocalDate;
import ed.FilaPrioridadeReserva;
import repository.dao.LivroDAOLista;
import repository.dao.ReservaDAOLista;

public class Titulo {
    private String nome;
    private String autor;
    private String isbn;
    private String genero;
    private String descricao;
    private LocalDate dataPublicacao;
    private int quantidade;
    private int quantidadeDisponivel;
    private int quantidadeReservas;

    private LivroDAOLista listaDeExemplares= new LivroDAOLista();


    public Titulo(int quantidade, String nome, LivroDAOLista listaDeExemplares, String isbn, String genero,
                  String descricao, LocalDate dataPublicacao, String autor) {

        this.quantidade = quantidade;
        this.nome = nome;
        this.listaDeExemplares = listaDeExemplares;
        this.isbn = isbn;
        this.genero = genero;
        this.descricao = descricao;
        this.dataPublicacao = dataPublicacao;
        this.autor = autor;
        this.quantidade=quantidade;

        this.quantidadeReservas=0;
        this.quantidadeDisponivel=quantidade;
    }

    //criar contrutor da classe livro que utiliza um objeto da classe Livro

    /*public Livro[] allExemplares() {
        if (listaDeExemplares.getListLivro() == null) return new Livro[0];
        return listaDeExemplares.getListLivro().toArray(new Livro[0]);///Pegar metodo de LivroDaoLista para retornar o array de livros
    }

    public Livro[] getExemplaresIndisponiveis() {
        return listaDeExemplares.getListLivro().stream()///Pegar metodo de LivroDaoLista para retornar o array de livros
                .filter(l -> !l.isDisponivel())
                .toArray(size -> new Livro[size]);
    }

    public Livro[] getExemplaresDisponiveis() {
        return listaDeExemplares.getListLivro().stream()///Pegar metodo de LivroDaoLista para retornar o array de livros
                .filter(l -> l.isDisponivel())
                .toArray(size -> new Livro[size]);
    }*/



    // --- Getters ---
    public String getNome() { return nome; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public String getGenero() { return genero; }
    public String getDescricao() { return descricao; }
    public LocalDate getDataPublicacao() { return dataPublicacao; }
    public int getQuantidade() { return quantidade; }
    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public int getQuantidadeReservas() { return quantidadeReservas; }

    public Reserva[] getReservas(){
        ReservaDAOLista listaDeReservas= new
    }///Implementar metodo se basenado em FilaDeReservas

    // --- Setters ---
    public void setNome(String nome) { this.nome = nome; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setDataPublicacao(LocalDate dataPublicacao) { this.dataPublicacao = dataPublicacao; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public void setQuantidadeDisponivel(int quantidadeDisponivel) { this.quantidadeDisponivel = quantidadeDisponivel; }
    public void setQuantidadeReservas(int quantidadeReservas) { this.quantidadeReservas = quantidadeReservas; }

    public Livro[] getExemplaresIndisponiveis(){}// Lista todos os exemplares deste do livro com este titulo que foram emprestados

    public Livro[] getExemplaresDisponiveis(){}// Lista todos os exemplares deste do livro que estão disponiveis

    public FilaPrioridadeReserva filaDeReservas; /// não fazer ainda !!!

    public Usuario[] getUsuarioNaListaDeReservas;///não fazer ainda!!!

    public void diminuirDisponivel() {
        if (this.quantidadeDisponivel > 0) {
            this.quantidadeDisponivel--;
        }
    }

    public void aumentarDisponivel() {
        if (this.quantidadeDisponivel < quantidade) {
            this.quantidadeDisponivel++;
        }
    }

    public void diminuirReservas(){
        //Diminuir quantidade e excluir a frente. Consultar FilaDeReservas
    }

    public void aumentarReservas(){
        //Aumentar quantidade e excluir a frente. Consultar FilaDeReservas
    }
}
