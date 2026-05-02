package service;

import models.*;
import repository.*;
import java.time.LocalDate;
import java.util.List;

public class UsuarioService {

    private Biblioteca b;
    private Usuario user;

    public UsuarioService(Usuario userLogado) {
        this.b = Biblioteca.getInstance();
        this.user = userLogado;
    }

    // =========================================================================
    // EMPRÉSTIMO
    // =========================================================================

    /**
     * Tenta emprestar um exemplar do livro solicitado ao usuário logado.
     *
     * Regras verificadas (na ordem da tela do livro do PDF):
     *  1. Usuário não pode ter devoluções em atraso.
     *  2. Usuário não pode ter atingido o limite de empréstimos.
     *  3. Deve existir pelo menos um exemplar disponível do livro.
     *
     * @param livro o exemplar a ser emprestado
     * @return true se o empréstimo foi efetuado; false caso contrário
     */
    public boolean pegarEmprestimo(Titulo titulo) {
        // 1. Verificar atraso
        if (usuarioPossuiAtraso()) {
            System.out.println("⚠️ Acesso Bloqueado: Regularize seus atrasos.");
            return false;
        }

        // 2. Verificar limite do plano do usuário
        if (user.getListaEmprestimos().tamanho() >= user.getLimiteLivros()) {
            System.out.println("⚠️ Limite atingido: Você já possui " + user.getLimiteLivros() + " livros.");
            return false;
        }

        // 3. CORREÇÃO: Verificar se realmente NÃO há livros
        if (titulo.getQuantidadeDisponivel() <= 0) {
            System.out.println("❌ Indisponível: Não há exemplares deste título no momento.");
            return false;
        }

        // 4. Buscar o exemplar físico (segurança contra NullPointer)
        Livro livroFisicoEmprestado = titulo.getExemplarDisponivel();
        if (livroFisicoEmprestado == null) {
            System.out.println("❌ Erro interno: Quantidade indica disponível, mas exemplar não encontrado.");
            return false;
        }

        // 5. Execução do Empréstimo
        livroFisicoEmprestado.setDisponivel(false);
        Emprestimo emprestimo = new Emprestimo(user, livroFisicoEmprestado);

        // Registro nos 3 pilares de persistência em memória
        user.adicionarEmprestimo(emprestimo);         // Para o perfil do aluno
        b.getListaDeEmprestimos().salvar(emprestimo); // Para o relatório geral da biblioteca
        titulo.registrarEmprestimo(emprestimo);       // Para o controle do estoque do título


        System.out.println("✅ Sucesso! Devolução prevista: " + emprestimo.getDataDevolucao());
        return true;
    }

    /**
     * Registra a devolução de um exemplar emprestado ao usuário logado.
     *
     * @param livro o exemplar a ser devolvido
     * @return true se a devolução foi registrada; false se o usuário não
     *         possui este livro em sua lista de empréstimos
     */
    public boolean devolucaoDoEmprestimo(Livro) {

        /// Refazer esse metodo considerando um objeto da classe Emprestimo. OBS: Se atenatr que é necessrio remover os emprestimos de
        ///do registro da biblioteca, Titulo e Usuario.listaDeEmprestimos
        Emprestimo emprestimo = encontrarEmprestimoAtivo(livro);
        if (emprestimo == null) {
            System.out.println("Nenhum empréstimo ativo encontrado para este livro.");
            return false;
        }

        LocalDate hoje = LocalDate.now();
        emprestimo.setDataDevolucao(hoje);
        emprestimo.setAtrasada(hoje.isAfter(emprestimo.getDataDevolucaoPrevista()));

        livro.setEstaDisponivel(true);
        user.devolverLivro(livro);

        Titulo titulo = listaDeTitulos.buscarTitulo(livro.getNome());
        if (titulo != null) {
            titulo.setQuantidadeDisponivel(titulo.getQuantidadeDisponivel() + 1);
        }

        if (emprestimo.isAtrasada()) {
            System.out.println("Livro devolvido com atraso. Por favor, regularize pendências futuras.");
        } else {
            System.out.println("Livro devolvido com sucesso!");
        }

        notificarProximoDaFila(titulo);
        return true;
    }

    // =========================================================================
    // CATÁLOGO
    // =========================================================================

    /**
     * Retorna todos os títulos do catálogo ordenados por nome.
     */
    public Titulo[] mostrarCatalogo() {
        return b.getTitulosAtualizados().listarTitulos();
    }

    /**
     * Busca títulos cujo nome contenha o texto informado (case-insensitive).
     * Prioridade: se o texto não for vazio, filtra por nome; caso contrário,
     * delega para filtrarPorGenero.
     *
     * @param busca texto a procurar no nome do título
     * @return lista filtrada de títulos
     */
    public List<Titulo> buscarTituloPorNome(String busca) {
        if (busca == null || busca.isBlank()) {
            return mostrarCatalogo();
        }
        return listaDeTitulos.getLista().stream()
                .filter(t -> t.getNome().toLowerCase().contains(busca.toLowerCase()))
                .toList();
    }

    /**
     * Filtra títulos por gênero.
     *
     * @param genero gênero desejado; se nulo ou vazio, retorna tudo
     * @return lista filtrada de títulos
     */
    public List<Titulo> filtrarPorGenero(String genero) {
        if (genero == null || genero.isBlank()) {
            return mostrarCatalogo();
        }
        return listaDeTitulos.selecionaTituloPorGenero(genero);
    }

    // =========================================================================
    // RESERVAS
    // =========================================================================

    /**
     * Faz uma reserva para o usuário logado no título informado.
     * O usuário pode ter no máximo 3 reservas simultâneas.
     *
     * @param titulo o título desejado
     * @return true se a reserva foi efetuada; false caso contrário
     */
    public boolean fazerReserva(Titulo titulo) {
        if (usuarioPossuiAtraso()) {
            // CORRIGIDO: mensagem agora menciona "reservas" em vez de "empréstimos"
            System.out.println("⚠️ Acesso Bloqueado. Você possui livros em atraso. " +
                    "Regularize suas devoluções para fazer novas reservas.");
            return false;
        }

        long reservasAtivas = contarReservasAtivas();
        if (reservasAtivas >= 3) {
            System.out.println("Você já atingiu o limite de 3 reservas simultâneas.");
            return false;
        }

        Reserva reserva = new Reserva(user);
        titulo.getFilaDeReservas().enfileirar(reserva);

        System.out.println("Reserva realizada! Você está na posição " +
                titulo.getFilaDeReservas().posicao(reserva) + " da fila.");
        return true;
    }

    /**
     * Cancela a reserva do usuário logado no título informado.
     *
     * @param titulo o título cuja reserva será cancelada
     * @return true se cancelada; false se não havia reserva
     */
    public boolean desistirDaReserva(Titulo titulo) {
        boolean removida = titulo.getFilaDeReservas().remover(user);
        if (removida) {
            System.out.println("Reserva cancelada com sucesso.");
        } else {
            System.out.println("Nenhuma reserva encontrada para este título.");
        }
        return removida;
    }

    // =========================================================================
    // STATUS DO USUÁRIO
    // =========================================================================

    /**
     * Verifica se o usuário logado possui algum empréstimo ativo com devolução atrasada.
     * CORRIGIDO: filtra apenas empréstimos ainda não devolvidos (dataDevolucao == null).
     */

    /// refatorar esse metodo utilizando user.getEmprestimos
    public boolean usuarioPossuiAtraso() {
        return user.getEmprestimos()
                .anyMatch(e -> e.getLivro() != null
                        && user.getListaEmprestimos().contains(e.getLivro())
                        && e.getDataDevolucao() == null
                        && e.isAtrasada());
    }

    /**
     * Verifica se o usuário atingiu o limite de empréstimos.
     */
    public boolean atingiuLimiteDeEmprestimos() {
        return user.getListaEmprestimos().size() >= user.getLimiteLivros();
    }

    // =========================================================================
    // Helpers privados
    // =========================================================================

    private Emprestimo encontrarEmprestimoAtivo(Livro livro) {
        return biblioteca.listaDeEmprestimos.getLista().stream()
                .filter(e -> e.getLivro().getId().equals(livro.getId())
                        && e.getDataDevolucao() == null)
                .findFirst()
                .orElse(null);
    }

    private long contarReservasAtivas() {
        return listaDeTitulos.getLista().stream()
                .flatMap(t -> t.getFilaDeReservas().stream())
                .filter(r -> r.getUsuario().getId().equals(user.getId()))
                .count();
    }

    private void notificarProximoDaFila(Titulo titulo) {
        if (titulo == null) return;
        Reserva proxima = titulo.getFilaDeReservas().peek();
        if (proxima != null) {
            System.out.println("Notificando " + proxima.getUsuario().getNome() +
                    ": o livro '" + titulo.getNome() + "' está disponível para retirada!");
        }
    }

    /** Retorna o prazo de empréstimo em dias conforme a categoria do usuário. */
    private int prazoEmDias() {
        return switch (user.getCategoria()) {
            case Professor -> 14;
            case Aluno -> 7;
            default -> 7; // Bibliotecario
        };
    }
}
