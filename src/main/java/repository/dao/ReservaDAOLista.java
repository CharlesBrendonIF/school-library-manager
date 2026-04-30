package repository.dao;
import models.Livro;
import models.Reserva;
import models.Titulo;
import models.Usuario;
import ed.ListaDinamica;
import ed.Listavel;


public class ReservaDAOLista {

    private Listavel<Reserva> listaReservas = new ListaDinamica<>();

    public void salvar(Reserva r) {
        if (r == null) {
            throw new IllegalArgumentException("Reserva não pode ser nula.");
        }
        listaReservas.anexar(r);
    }

    public Reserva[] listar() {
        Reserva[] arrayRetorno = new Reserva[listaReservas.tamanho()];
        for (int i = 0; i < listaReservas.tamanho(); i++) {
            arrayRetorno[i] = listaReservas.selecionar(i);
        }
        return arrayRetorno;
    }

    public Reserva[] buscarPorTitulo(Titulo t) {
        int contador = 0;
        for (int i = 0; i < listaReservas.tamanho(); i++) {
            Reserva r = listaReservas.selecionar(i);
            if (r.getTitulo().equals(t)) {
                contador++;
            }
        }

        Reserva[] arrayRetorno = new Reserva[contador];
        int indice = 0;

        for (int i = 0; i< listaReservas.tamanho(); i++) {
            Reserva r = listaReservas.selecionar(i);
            if (r.getTitulo().equals(t)) {
                arrayRetorno[indice++] = r;
            }
        }
        return arrayRetorno;
    }

    public Reserva[] buscarPorUsuario(Usuario u) {
        int contador = 0;
        for (int i = 0; i < listaReservas.tamanho(); i++) {
            Reserva r = listaReservas.selecionar(i);
            if (r.getUsuario().equals(u)) {
                contador++;
            }
        }

        Reserva[] arrayRetorno = new Reserva[contador];
        int indice = 0;

        for (int i = 0; i< listaReservas.tamanho(); i++) {
            Reserva r = listaReservas.selecionar(i);
            if (r.getUsuario().equals(u)) {
                arrayRetorno[indice++] = r;
            }
        }
        return arrayRetorno;
    }

    public void atualizar(String id, Reserva reservaAtualizada) {
        for (int i = 0; i < listaReservas.tamanho(); i++) {
            Reserva r = listaReservas.selecionar(i);
            if (r.getId().equals(id)) {
                listaReservas.atualizar(reservaAtualizada, i);
                return;
            }
        }
        throw new IllegalArgumentException("Reserva com ID " + id + " não encontrado.");
    }

    public Reserva apagar(String id) {
        for (int i = 0; i < listaReservas.tamanho(); i++) {
            Reserva r = listaReservas.selecionar(i);
            if (r.getId().equals(id)) {
                return listaReservas.apagar(i);
            }
        }
        return null;
    }
}
