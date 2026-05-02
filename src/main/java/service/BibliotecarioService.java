package service;

import models.*;
import repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BibliotecarioService {

    private Biblioteca b;

    public BibliotecarioService(Usuario userLogado) {
        Usuario user=userLogado;
        b=Biblioteca.getInstance();
    }

    // =========================================================================
    // DASHBOARD — dados gerais (tela do bibliotecário)
    // =========================================================================

    /** Total de exemplares (livros) no acervo. */
    public int getTotalLivros() {
        return b.getAcervo().quantidade();
    }

    /** Número de empréstimos ativos. */
    public int getNumeroEmprestimosAtivos() {
        return b.getListaDeEmprestimos().tamanho();
    }

    /**
     * Número de empréstimos atrasados.
     * evitando contar devoluções antigas que foram marcadas como atrasadas.
     */
    public int getNumeroEmprestimosAtrasados() {/// ATUALIZAR METODO COM OS METODOS DE BIBLIOTECA e Emprestimo
        int cont=0;
        for()
    }

    /** Número de usuários com pelo menos um empréstimo atrasado. */
    public int getUsuariosComAtraso() {/// ATUALIZAR METODO COM OS METODOS DE BIBLIOTECA e Emprestimo
        return (int) listaDeUsuarios.getLista().stream()
                .filter(u -> possuiAtraso(u))
                .count();
    }

    /** Total de reservas ativas em todas as filas. */
    public int getTotalReservas() {/// ATUALIZAR METODO COM OS METODOS DE BIBLIOTECA e Emprestimo
        return listaDeTitulos.getLista().stream()
                .mapToInt(t -> t.getFilaDeReservas().tamanho())
                .sum();
    }

    // =========================================================================
    // DEVOLUÇÕES — tela de controle de devoluções
    // =========================================================================

    /**
     * Lista todos os empréstimos com seus dados completos.
     */

    public boolean registrarDevolucao(Emprestimo e) {

        if (e == null) {
            System.out.println("Empréstimo não encontrado ou já encerrado.");
            return false;
        }

        Livro livro = e.getLivro();
        livro.setDisponivel(true);

        // Atualiza contadores e lista de emprestimo em título;
        for(Titulo titulo: b.getTitulosAtualizados().listarTitulos()  ){
            if(livro.getIsbn().equalsIgnoreCase(titulo.getIsbn())){
                titulo.removerEmprestimo(e);
            }
        }
        /// Completar e utilizar metodos presentes em models e repository ////
    }

    // =========================================================================
    // INVENTÁRIO — tela de inventário
    // =========================================================================

    /**
     * Lista todos os títulos do acervo, ordenados por nome.
     */
    public Titulo[] listarInventario() {/// ATUALIZAR METODO COM OS METODOS DE BIBLIOTECA e TituloDAOLista

    }

    /**
     * Lista todos os exemplares (livros) do acervo, ordenados por nome.
     */
    public Livro[] listarExemplares() {/// ATUALIZAR METODO COM OS METODOS DE BIBLIOTECA, LivroDaoLista

    }

    // =========================================================================
    // CONTROLE DE RESERVAS — tela de controle de reservas
    // =========================================================================


//////////////////////Atualizar com Metodos de Repository e Models////////////////////////////////////////
    /**
     * Retorna, para cada título, o primeiro usuário da fila de reservas.
     */
    public Reserva[] listarPrimeirosDasFilaDeReservasDeCadaTitulo() {
        ///OBS: Aqui recomendo em um loop percorrer todos os objetos Titulo em listaDeTitulos e biblioteco, e capturar
        /// o primeiro elemento da lista de reserva de cada Titulo. Essas reservas devem ser guardadas em uma
        ///listaDeReservas(ReservasDAOLista)
        /// Por fim retornar listaDeResercas.listar, que vai retornar um array de reservas
    }

    public Reserva[] listarFilaDeReserva(Titulo t) {
        ///Este metodo irá simplesmente retornar um array de das reservas de um Titulo, ou seja,
        /// retornar um array de Titulo.listaDeReservas
    }

    /**
     * Efetua o empréstimo para o primeiro da fila de reservas de um título.
     * O vínculo usuário↔livro é mantido por beneficiario.pegarLivro(exemplar),
     * pois a entidade Emprestimo não possui campo de usuário (conforme modelo do projeto).
     *
     * @param titulo o título cujo primeiro da fila será atendido
     * @return true se o empréstimo foi gerado; false se a fila estiver vazia
     *         ou não houver exemplar disponível
     */
    public boolean atenderPrimeirosDaFila(Titulo titulo) {
        Reserva proxima = titulo.getFilaDeReservas().peek();
        if (proxima == null) {
            System.out.println("Fila de reservas vazia para: " + titulo.getNome());
            return false;
        }

        // Busca um exemplar disponível
        Livro exemplar = listaDeLivros.getLista().stream()
                .filter(l -> l.getNome().equalsIgnoreCase(titulo.getNome()) && l.isEstaDisponivel())
                .findFirst()
                .orElse(null);

        if (exemplar == null) {
            System.out.println("Nenhum exemplar disponível de: " + titulo.getNome());
            return false;
        }

        titulo.getFilaDeReservas().desenfileirar();
        Usuario beneficiario = proxima.getUsuario();

        LocalDate hoje = LocalDate.now();
        LocalDate dataDevolucaoPrevista = hoje.plusDays(prazoEmDias(beneficiario));
        Emprestimo emprestimo = new Emprestimo(gerarIdEmprestimo(), exemplar, hoje, dataDevolucaoPrevista);

        exemplar.setEstaDisponivel(false);
        beneficiario.pegarLivro(exemplar); // vínculo usuário↔livro mantido aqui
        listaDeEmprestimos.adicionar(emprestimo);
        titulo.setQuantidadeDisponivel(titulo.getQuantidadeDisponivel() - 1);

        System.out.println("Empréstimo gerado para " + beneficiario.getNome() +
                " — devolução prevista: " + dataDevolucaoPrevista);
        return true;
    }

    // =========================================================================
    // GESTÃO DE USUÁRIOS
    // =========================================================================

    /** Retorna a lista completa de usuários. */


    /** Busca um usuário pelo ID. */
    public Usuario buscarUsuarioPorId(String id) {
        return listaDeUsuarios.getLista().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // =========================================================================
    // GESTÃO DO ACERVO
    // =========================================================================

    /**
     * Adiciona um novo exemplar (livro) ao acervo e atualiza o título correspondente.
     *
     * @param livro o exemplar a ser adicionado
     */
    public void adicionarLivro(Livro livro) {
        listaDeLivros.adicionar(livro);

        Titulo titulo = listaDeTitulos.buscarTitulo(livro.getNome());
        if (titulo == null) {
            titulo = new Titulo(
                    livro.getNome(),
                    livro.getAutor(),
                    livro.getDataDePublicacao(),
                    livro.getIsbn(),
                    livro.getGenero(),
                    livro.getDescricao()
            );
            titulo.setQuantidade(1);
            titulo.setQuantidadeDisponivel(1);
            listaDeTitulos.adicionar(titulo);
        } else {
            titulo.setQuantidade(titulo.getQuantidade() + 1);
            if (livro.isEstaDisponivel()) {
                titulo.setQuantidadeDisponivel(titulo.getQuantidadeDisponivel() + 1);
            }
        }

        System.out.println("Livro '" + livro.getNome() + "' adicionado ao acervo.");
    }

    /**
     * Remove um exemplar do acervo pelo ID.
     *
     * @param idLivro ID do exemplar a ser removido
     * @return true se removido; false se não encontrado
     */
    public boolean removerLivro(String idLivro) {
        Livro livro = listaDeLivros.getLista().stream()
                .filter(l -> l.getId().equals(idLivro))
                .findFirst()
                .orElse(null);

        if (livro == null) {
            System.out.println("Exemplar não encontrado.");
            return false;
        }

        listaDeLivros.remover(livro);

        Titulo titulo = listaDeTitulos.buscarTitulo(livro.getNome());
        if (titulo != null) {
            titulo.setQuantidade(titulo.getQuantidade() - 1);
            if (livro.isEstaDisponivel()) {
                titulo.setQuantidadeDisponivel(titulo.getQuantidadeDisponivel() - 1);
            }
            if (titulo.getQuantidade() <= 0) {
                listaDeTitulos.remover(titulo);
            }
        }

        System.out.println("Exemplar removido com sucesso.");
        return true;
    }

    // =========================================================================
    // Helpers privados
    // =========================================================================

    private boolean possuiAtraso(Usuario u) {
        return listaDeEmprestimos.getLista().stream()
                .anyMatch(e -> e.getLivro() != null
                        && u.getListaEmprestimos().contains(e.getLivro())
                        && e.getDataDevolucao() == null
                        && e.isAtrasada());
    }

    private int prazoEmDias(Usuario u) {
        return switch (u.getCategoria()) {
            case Professor -> 14;
            case Aluno -> 7;
            default -> 7; // Bibliotecario
        };
    }

    private String gerarIdEmprestimo() {
        return EmprestimoUtils.gerarId();
    }
}
