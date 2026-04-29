package models;

import repository.dao.EmprestimoDAOLista;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String senha;
    public TipoUsuario tipo;
    private int limiteLivros;
    private EmprestimoDAOLista listaEmprestimos;

    // Construtor padrão
    public Usuario() {}

    // Construtor parametrizado
    public Usuario(String id, String nome, String email, TipoUsuario tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;

        if(tipo==TipoUsuario.ALUNO){
            limiteLivros=3;
        }else if(tipo==TipoUsuario.PROFESSOR){
            limiteLivros=4;
        }
    }

    // Getters
    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public TipoUsuario getTipo() { return tipo; }
    public int getLimiteLivros() { return limiteLivros; }

    public Emprestimo[] getEmprestimos() {  }///Fazer metodo basendo-se em EmprestimoDaoLista

    // Setters
    public void setId(String id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setCategoria(TipoUsuario categoria) { this.tipo = categoria; }
    public void setLimiteLivros(int limiteLivros) { this.limiteLivros = limiteLivros; }

    public void mostrarDados() {
        System.out.println("ID: " + id + " | Nome: " + nome + " | Categoria: " + tipo);
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        this.listaEmprestimos.add(emprestimo);///Usar metodos contido em EmprestimoDaoLista
    }

    /// Criar removerEmprestimo


}
