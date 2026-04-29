package br.edu.ifba.repository.dao;

import br.edu.ifba.model.Emprestimo;
import br.edu.ifba.model.Usuario;
import br.edu.ifba.repository.repository.ListaDinamica;
import br.edu.ifba.repository.repository.Listavel;

public class EmprestimoDAOLista {
    private Listavel listaEmprestimos = new ListaDinamica();

    public void salvar(Emprestimo e) {
        if (e == null) {
            throw new IllegalArgumentException("Empréstimo não pode ser nulo.");
        }
        listaEmprestimos.anexar(e);
    }

    public Emprestimo[] listar() {
        Emprestimo[] arrayRetorno = new Emprestimo[listaEmprestimos.tamanho()];
        for (int i =0; i<listaEmprestimos.tamanho(); i++) {
            arrayRetorno[i] = (Emprestimo) listaEmprestimos.selecionar(i);
        }
        return arrayRetorno;
    }

    public Emprestimo[] buscarPorUsuario(Usuario u) {
        int contador = 0;
        for (int i = 0; i < listaEmprestimos.tamanho(); i++) {
            Emprestimo e = (Emprestimo) listaEmprestimos.selecionar(i);
            if (e.getUsuario().equals(u)) {
                contador++;
            }
        }

        Emprestimo[] arrayRetorno = new Emprestimo[contador];
        int indice = 0;

        for (int i = 0; i < listaEmprestimos.tamanho(); i++) {
            Emprestimo e = (Emprestimo) listaEmprestimos.selecionar(i);
            if (e.getUsuario().equals(u)) {
                arrayRetorno[indice++] = e;
            }
        }
        return arrayRetorno;
    }

    public boolean usuarioTemAtraso(Usuario u) {
        for (int  i = 0; i<listaEmprestimos.tamanho(); i++) {
            Emprestimo e = (Emprestimo) listaEmprestimos.selecionar(i);
            if (e.getUsuario().equals(u) && e.isAtrasado()) {
                return true;
            }
        }
        return false;
    }

    public int contarEmprestimosAtivos(Usuario u) {
        int contador = 0;
        for (int i=0; i<listaEmprestimos.tamanho(); i++) {
            Emprestimo e = (Emprestimo) listaEmprestimos.selecionar(i);
            if (e.getUsuario().equals(u) && !e.getLivro().isDisponivel()) {
                contador++;
            }
        }
        return contador;
    }
}
