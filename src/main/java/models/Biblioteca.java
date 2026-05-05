package models;

import repository.DataBaseSeed; // Importa a nova classe
import repository.dao.*;

public class Biblioteca {
    private LivroDAOLista acervo;
    private TituloDAOLista listaDeTitulos;
    private EmprestimoDAOLista listaDeEmprestimos;
    private ReservaDAOLista listaDeReservas;
    private UsuarioDAOLista listaDeUsuarios;

    private static Biblioteca instance;

    private Biblioteca() {
        // Inicializa as listas vazias
        this.acervo = new LivroDAOLista();
        this.listaDeUsuarios = new UsuarioDAOLista();
        this.listaDeEmprestimos = new EmprestimoDAOLista();
        this.listaDeReservas = new ReservaDAOLista();

        // Chama a "Semente" de dados para preencher as listas acima
        DataBaseSeed.popularDadosIniciais(this.acervo, this.listaDeUsuarios);

        // Gera os títulos baseados no acervo populado
        this.listaDeTitulos = updateListaDeTitulos(this.acervo);
    }

    public static Biblioteca getInstance() {
        if (instance == null) {
            instance = new Biblioteca();
        }
        return instance;
    }

    public boolean thisIDIsValid(String id) {
        if (id == null || id.isEmpty()) return false;

        char prefixo = id.charAt(0);
        String[] listaParaComparar;

        // Busca os IDs da nossa nova classe repository
        if (prefixo == 'p') listaParaComparar = DataBaseSeed.IDS_TEACHERS;
        else if (prefixo == 's') listaParaComparar = DataBaseSeed.IDS_STUDENTS;
        else if (prefixo == 'l') listaParaComparar = DataBaseSeed.IDS_LIBRARIANS;
        else return false;

        for (String validId : listaParaComparar) {
            if (validId.equals(id)) return true;
        }
        return false;
    }

    public LivroDAOLista getAcervo() {
        return acervo;
    }

    public EmprestimoDAOLista getListaDeEmprestimos() {
        return listaDeEmprestimos;
    }

    public ReservaDAOLista getListaDeReservas() {
        return listaDeReservas;
    }

    public UsuarioDAOLista getListaDeUsuarios() {
        return listaDeUsuarios;
    }

    public TituloDAOLista getTitulosAtualizados() {
        // Toda vez que alguém pedir os títulos, você re-agrupa para garantir
        // que novos livros adicionados ao acervo apareçam aqui.
        this.listaDeTitulos = updateListaDeTitulos(this.acervo);
        return this.listaDeTitulos;
    }
    private TituloDAOLista updateListaDeTitulos(LivroDAOLista acervo) {
        acervo.ordenar();
        TituloDAOLista novaListaDeTitulos = new TituloDAOLista();

        int i = 0;
        while (i < acervo.quantidade()) {
            Livro modelo = acervo.selecionar(i);
            String isbnAtual = modelo.getIsbn();
            LivroDAOLista colecaoExemplares = new LivroDAOLista();

            while (i < acervo.quantidade() && acervo.selecionar(i).getIsbn().equals(isbnAtual)) {
                colecaoExemplares.salvar(acervo.selecionar(i));
                i++;
            }

            // --- A MÁGICA ACONTECE AQUI ---
            // Filtramos as listas globais para pegar apenas o que é deste ISBN
            EmprestimoDAOLista emprestimosFiltrados = filtrarEmprestimosPorIsbn(isbnAtual);
            ReservaDAOFilaDePrioridade reservasFiltradas = filtrarReservasPorIsbn(isbnAtual);

            // Criamos o título com as suas respectivas listas já vindo da persistência global
            novaListaDeTitulos.salvar(new Titulo(colecaoExemplares, emprestimosFiltrados, reservasFiltradas));
        }
        return novaListaDeTitulos;
    }

    // Métodos auxiliares dentro da Biblioteca para ajudar no filtro:
    private EmprestimoDAOLista filtrarEmprestimosPorIsbn(String isbn) {
        EmprestimoDAOLista filtrada = new EmprestimoDAOLista();
        for (Emprestimo e : this.listaDeEmprestimos.listar()) {
            if (e.getLivro().getIsbn().equals(isbn)) {
                filtrada.salvar(e);
            }
        }
        return filtrada;
    }

    private ReservaDAOFilaDePrioridade filtrarReservasPorIsbn(String isbn) {
        ReservaDAOFilaDePrioridade filtrada = new ReservaDAOFilaDePrioridade();
        for (Reserva r : this.listaDeReservas.listar()) {
            if (r.getTitulo().getIsbn().equals(isbn)) {
                filtrada.salvar(r);
            }
        }
        return filtrada;
    }
    public int contarTotalEmprestimos() {
        return listaDeEmprestimos.tamanho();
    }

    public int contarTotalReservas() {
        return listaDeReservas.tamanho();
    }
}
