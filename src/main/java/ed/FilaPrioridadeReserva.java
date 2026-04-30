package ed;

import models.Reserva;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FilaPrioridadeReserva  {

    private PriorityQueue<Reserva> fila;

    public FilaPrioridadeReserva() {

        this.fila = new PriorityQueue<>(new ReservaComparator());
    }


    public void adicionar(Reserva r) {
        fila.add(r);
    }


    public Reserva proximo() {
        return fila.peek(); // apenas visualiza
    }

    public Reserva removerProximo() {
        return fila.poll(); // remove o primeiro  e retorna o próximo da fila
    }


    public boolean estaVazia() {
        return fila.isEmpty();
    }


    public int posicao(Reserva r) {
        List<Reserva> lista = new ArrayList<>(fila);

        // Ordena com o mesmo critério da fila
        lista.sort(fila.comparator());

        return lista.indexOf(r);
    }
}