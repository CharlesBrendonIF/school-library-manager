package repository.dao;

import models.Emprestimo;
import models.Usuario;
import ed.ListaDinamica;
import ed.Listavel;

public class EmprestimoDAOLista {
    private Listavel<Emprestimo> listaEmprestimos = new ListaDinamica<>(0);

    public void salvar(Emprestimo e) {
        if (e == null) {
            throw new IllegalArgumentException("Empréstimo não pode ser nulo.");
        }
        listaEmprestimos.anexar(e);
    }

    public Emprestimo buscarPorId(long id) {
        for (int i = 0; i < listaEmprestimos.tamanho(); i++) {
            Emprestimo e = listaEmprestimos.selecionar(i);
            if (e.getId()==id) {
                return e;
            }
        }
        return null;
    }

    public Emprestimo[] listar() {
        Emprestimo[] arrayRetorno = new Emprestimo[listaEmprestimos.tamanho()];
        for (int i =0; i<listaEmprestimos.tamanho(); i++) {
            arrayRetorno[i] = listaEmprestimos.selecionar(i);
        }
        return arrayRetorno;
    }

    public Emprestimo[] buscarPorUsuario(Usuario u) {
        int contador = 0;
        for (int i = 0; i < listaEmprestimos.tamanho(); i++) {
            Emprestimo e = listaEmprestimos.selecionar(i);
            if (e.getUsuario().equals(u)) {
                contador++;
            }
        }

        Emprestimo[] arrayRetorno = new Emprestimo[contador];
        int indice = 0;

        for (int i = 0; i < listaEmprestimos.tamanho(); i++) {
            Emprestimo e = listaEmprestimos.selecionar(i);
            if (e.getUsuario().equals(u)) {
                arrayRetorno[indice++] = e;
            }
        }
        return arrayRetorno;
    }

    public void atualizar(long id, Emprestimo emprestimoAtualizado) {
        for (int i = 0; i < listaEmprestimos.tamanho(); i++) {
            Emprestimo e = listaEmprestimos.selecionar(i);
            if (e.getId()==id) {
                listaEmprestimos.atualizar(emprestimoAtualizado, i);
                return;
            }
        }
        throw new IllegalArgumentException("Empréstimo com ID " + id + " não encontrado.");
    }

    public Emprestimo apagarPorId(long id) {
        for (int i = 0; i < listaEmprestimos.tamanho(); i++) {
            Emprestimo e = listaEmprestimos.selecionar(i);
            if (e.getId()==id) {
                return listaEmprestimos.apagar(i);
            }
        }
        return null;
    }

    public boolean usuarioTemAtraso(Usuario u) {
        for (int  i = 0; i<listaEmprestimos.tamanho(); i++) {
            Emprestimo e = listaEmprestimos.selecionar(i);
            if (e.getUsuario().equals(u) && e.isAtrasado()) {
                return true;
            }
        }
        return false;
    }

    public int contarEmprestimosAtivos(Usuario u) {
        int contador = 0;
        for (int i=0; i<listaEmprestimos.tamanho(); i++) {
            Emprestimo e = listaEmprestimos.selecionar(i);
            if (e.getUsuario().equals(u) && !e.getLivro().isDisponivel()) {
                contador++;
            }
        }
        return contador;
    }
}
