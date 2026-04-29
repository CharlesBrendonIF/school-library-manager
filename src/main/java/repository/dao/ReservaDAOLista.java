package repository.dao;
import models.Livro;
import models.Reserva;
import models.Titulo;
import models.Usuario;
import repository.repository.ListaDinamica;
import repository.repository.Listavel;

public class ReservaDAOLista {

    private Listavel listaReservas = new ListaDinamica();

    public void salvar(Reserva r) {
        if (r == null) {
            throw new IllegalArgumentException("Reserva não pode ser nula.");
        }
        listaReservas.anexar(r);
    }

    public Reserva[] listar() {
        Reserva[] arrayRetorno = new Reserva[listaReservas.tamanho()];
        for (int i = 0; i < listaReservas.tamanho(); i++) {
            arrayRetorno[i] = (Reserva) listaReservas.selecionar(i);
        }
        return arrayRetorno;
    }

    public Reserva[] buscarPorTitulo(Titulo t) {
        int contador = 0;
        for (int i = 0; i < listaReservas.tamanho(); i++) {
            Reserva r = (Reserva) listaReservas.selecionar(i);
            if (r.getTitulo().equals(t)) {
                contador++;
            }
        }

        Reserva[] arrayRetorno = new Reserva[contador];
        int indice = 0;

        for (int i = 0; i< listaReservas.tamanho(); i++) {
            Reserva r = (Reserva) listaReservas.selecionar(i);
            if (r.getTitulo().equals(t)) {
                arrayRetorno[indice++] = r;
            }
        }
        return arrayRetorno;
    }

    public Reserva[] buscarPorUsuario(Usuario u) {
        int contador = 0;
        for (int i = 0; i < listaReservas.tamanho(); i++) {
            Reserva r = (Reserva) listaReservas.selecionar(i);
            if (r.getUsuario().equals(u)) {
                contador++;
            }
        }

        Reserva[] arrayRetorno = new Reserva[contador];
        int indice = 0;

        for (int i = 0; i< listaReservas.tamanho(); i++) {
            Reserva r = (Reserva) listaReservas.selecionar(i);
            if (r.getUsuario().equals(u)) {
                arrayRetorno[indice++] = r;
            }
        }
        return arrayRetorno;
    }

    public Reserva apagar(){

    }
}
