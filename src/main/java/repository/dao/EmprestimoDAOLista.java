package repository.dao;

import ed.Listavel;
import models.Emprestimo;

public class EmprestimoDAOLista implements Listavel<Emprestimo> {

    private Emprestimo[] dados;
    private int tamanho;

    // Construtor
    public EmprestimoDAOLista() {
        dados = new Emprestimo[100];
        tamanho = 0;
    }

    @Override
    public void inserir(Emprestimo objeto, int posicao) {

        // Verifica se está cheia
        if (estaCheia()) {
            throw new IllegalStateException("Lista cheia");
        }

        // Verifica posição válida
        if (posicao < 0 || posicao > tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        // Desloca os elementos
        for (int i = tamanho; i > posicao; i--) {
            dados[i] = dados[i - 1];
        }

        dados[posicao] = objeto;
        tamanho++;
    }

    @Override
    public void anexar(Emprestimo objeto) {

        // Adiciona no final
        if (estaCheia()) {
            throw new IllegalStateException("Lista cheia");
        }

        dados[tamanho] = objeto;
        tamanho++;
    }

    @Override
    public Emprestimo selecionar(int posicao) {

        // Retorna elemento pela posição
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        return dados[posicao];
    }

    @Override
    public Emprestimo[] selecionarTodos() {

        // Retorna cópia da lista
        Emprestimo[] copia = new Emprestimo[tamanho];

        for (int i = 0; i < tamanho; i++) {
            copia[i] = dados[i];
        }

        return copia;
    }

    @Override
    public void atualizar(Emprestimo objeto, int posicao) {

        // Atualiza elemento
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        dados[posicao] = objeto;
    }

    @Override
    public Emprestimo remover(int posicao) {

        // Remove elemento
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        Emprestimo removido = dados[posicao];

        // Desloca para esquerda
        for (int i = posicao; i < tamanho - 1; i++) {
            dados[i] = dados[i + 1];
        }

        dados[tamanho - 1] = null;
        tamanho--;

        return removido;
    }

    @Override
    public void limpar() {

        // Limpa lista
        for (int i = 0; i < tamanho; i++) {
            dados[i] = null;
        }

        tamanho = 0;
    }

    @Override
    public int tamanho() {
        // Retorna quantidade de elementos
        return tamanho;
    }

    @Override
    public boolean estaVazia() {
        return tamanho == 0;
    }

    @Override
    public boolean estaCheia() {
        return tamanho == dados.length;
    }

    @Override
    public String imprimir() {

        // Monta string com elementos
        String texto = "";

        for (int i = 0; i < tamanho; i++) {
            texto = texto + dados[i] + "\n";
        }

        return texto;
    }
}
