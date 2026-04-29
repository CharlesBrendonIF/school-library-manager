package br.edu.ifba.repository.dao;

import br.edu.ifba.model.Livro;
import br.edu.ifba.repository.repository.ListaDinamica;
import br.edu.ifba.repository.repository.Listavel;

public class LivroDAOLista {
    private Listavel listaLivros = new ListaDinamica();
    
    public void salvar(Livro l) {
        if (l == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo.");
        }
        listaLivros.anexar(l);
    }

    public Livro[] listar() {
        Livro[] arrayRetorno = new Livro[listaLivros.tamanho()];
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            arrayRetorno[i] = (Livro) listaLivros.selecionar(i);
        }
        return arrayRetorno;
    }

    public int contarExemplares(String nome) {
        int contador = 0;
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro l = (Livro) listaLivros.selecionar(i);
            if (l.getTitulo().getNome().equalsIgnoreCase(nome)) {
                contador++;
            }
        }
        return contador;
    }

    public int contarDisponiveis(String nome) {
        int contador = 0;
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro l = (Livro) listaLivros.selecionar(i);
            if (l.getTitulo().getNome().equalsIgnoreCase(nome) && l.isDisponivel()) {
                contador++;
            }
        }
        return contador;
    }

    public void ordenar() {
        for (int i = 0; i < listaLivros.tamanho() - 1; i++) {
            for (int j = 0; j < listaLivros.tamanho() - i - 1; j++) {
                Livro livro1 = (Livro) listaLivros.selecionar(j);
                Livro livro2 = (Livro) listaLivros.selecionar(j + 1);
                
                if (livro1.getTitulo().getNome().compareToIgnoreCase(livro2.getTitulo().getNome()) > 0) {
                    
                    listaLivros.atualizar(livro2, j);
                    listaLivros.atualizar(livro1, j + 1);
                }
            }
        }
    }
}