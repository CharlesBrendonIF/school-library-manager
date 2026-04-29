package br.edu.ifba.service;

import br.edu.ifba.model.*;
import br.edu.ifba.repository.dao.*;


public class UsuarioService {
    private Biblioteca biblioteca;
    private LivroDAOLista acervo;//lista de livros da biblioteca
    private TituloDAOLista listaDeTitulos;
    private Usuario user;

    public UsuarioService(Biblioteca biblioteca, Usuario userLogado) {
        this.biblioteca = biblioteca;
        this.acervo = biblioteca.acervo;
        this.listaDeTitulos = biblioteca.listaDeTitulos;
        this.user = userLogado;
    }

    //pegarEmprestimo
    //devoluçaoDoEmprestimo
    //Mostrar Catalogo de livros: listaDeTitulos


}
