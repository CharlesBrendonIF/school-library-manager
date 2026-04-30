package repository.dao;

import models.Livro;
import ed.ListaDinamica;
import ed.Listavel;

public class LivroDAOLista {
    private Listavel<Livro> listaLivros = new ListaDinamica<>();

    public void salvar(Livro l) {
        if (l == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo.");
        }
        listaLivros.anexar(l);
    }

    public Livro buscarPorId(String id) {
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro l = listaLivros.selecionar(i);
            if (l.getId().equals(id)) {
                return l;
            }
        }
        return null;
    }

    public Livro[] listar() {
        Livro[] arrayRetorno = new Livro[listaLivros.tamanho()];
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            arrayRetorno[i] = listaLivros.selecionar(i);
        }
        return arrayRetorno;
    }

    public void atualizar(String id, Livro livroAtualizado) {
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro l = listaLivros.selecionar(i);
            if (l.getId().equals(id)) {
                listaLivros.atualizar(livroAtualizado, i);
                return;
            }
        }
        throw new IllegalArgumentException("Livro com ID " + id + " não encontrado.");
    }

    public Livro apagar(String id) {
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro l = listaLivros.selecionar(i);
            if (l.getId().equals(id)) {
                return listaLivros.apagar(i);
            }
        }
        return null;
    }

    public int contarExemplares(String nome) {
        int contador = 0;
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro l = listaLivros.selecionar(i);
            if (l.getTitulo().getNome().equalsIgnoreCase(nome)) {
                contador++;
            }
        }
        return contador;
    }

    public int contarDisponiveis(String nome) {
        int contador = 0;
        for (int i = 0; i < listaLivros.tamanho(); i++) {
            Livro l = listaLivros.selecionar(i);
            if (l.getTitulo().getNome().equalsIgnoreCase(nome) && l.isDisponivel()) {
                contador++;
            }
        }
        return contador;
    }

    public void ordenar() {
        for (int i = 0; i < listaLivros.tamanho() - 1; i++) {
            for (int j = 0; j < listaLivros.tamanho() - i - 1; j++) {
                Livro livro1 = listaLivros.selecionar(j);
                Livro livro2 = listaLivros.selecionar(j + 1);

                if (livro1.getTitulo().getNome().compareToIgnoreCase(livro2.getTitulo().getNome()) > 0) {

                    listaLivros.atualizar(livro2, j);
                    listaLivros.atualizar(livro1, j + 1);
                }
            }
        }
    }
}