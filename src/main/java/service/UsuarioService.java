package service;

import models.*;
import repository.dao.ReservaDAOLista;

// ALTERAÇÃO: removidos imports de "repository.*" e "java.util.List" — não são mais usados.
// Os métodos de busca agora retornam arrays (Titulo[]) usando os DAOs existentes,
// em vez de List<Titulo> via stream(), pois TituloDAOLista não expõe stream.
// ALTERAÇÃO: adicionado import de ReservaDAOLista (necessário em contarReservasAtivas)

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
     * Tenta emprestar um exemplar do título solicitado ao usuário logado.
     *
     * Regras verificadas em ordem:
     *  1. Usuário não pode ter devoluções em atraso.
     *  2. Usuário não pode ter atingido o limite de empréstimos.
     *  3. Deve existir pelo menos um exemplar disponível do título.
     *
     * @param titulo o título desejado
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

        // 3. Verificar disponibilidade pelo contador do título
        if (titulo.getQuantidadeDisponivel() <= 0) {
            System.out.println("❌ Indisponível: Não há exemplares deste título no momento.");
            return false;
        }

        // 4. Buscar o exemplar físico disponível (segurança contra NullPointer)
        Livro livroFisicoEmprestado = titulo.getExemplarDisponivel();
        if (livroFisicoEmprestado == null) {
            System.out.println("❌ Erro interno: Quantidade indica disponível, mas exemplar não encontrado.");
            return false;
        }

        // 5. Execução do Empréstimo
        livroFisicoEmprestado.setDisponivel(false);
        Emprestimo emprestimo = new Emprestimo(user, livroFisicoEmprestado);

        // Registro nos 3 pilares de persistência em memória
        user.adicionarEmprestimo(emprestimo);         // perfil do usuário
        b.getListaDeEmprestimos().salvar(emprestimo); // relatório geral da biblioteca
        titulo.registrarEmprestimo(emprestimo);       // controle de estoque do título

        System.out.println("✅ Sucesso! Devolução prevista: " + emprestimo.getDataDevolucao());
        return true;
    }

    /**
     * Registra a devolução de um empréstimo ativo do usuário logado.
     *
     * ALTERAÇÃO COMPLETA: o método original recebia Livro como parâmetro (sem nome,
     * causando erro de compilação) e usava métodos inexistentes como setEstaDisponivel(),
     * devolverLivro(), setAtrasada() e getDataDevolucaoPrevista().
     * Refatorado para receber Emprestimo diretamente, conforme indicado pelo comentário
     * original no código. Agora remove o empréstimo dos três registros: biblioteca,
     * título e usuário (dependendo de removerEmprestimo ser implementado nos models).
     *
     * @param emprestimo o empréstimo a ser encerrado
     * @return true se a devolução foi registrada; false se o empréstimo for nulo
     */
    public boolean devolucaoDoEmprestimo(Emprestimo emprestimo) {
        // CORRIGIDO: parâmetro era "Livro" sem nome — trocado para "Emprestimo emprestimo"

        if (emprestimo == null) {
            System.out.println("Empréstimo não encontrado.");
            return false;
        }

        Livro livro = emprestimo.getLivro();

        // Marca a data de devolução real e verifica atraso
        // CORRIGIDO: era setAtrasada() e getDataDevolucaoPrevista() — métodos inexistentes.
        // Os nomes corretos em Emprestimo são setAtrasado() e getDataDevolucao()
        // (getDataDevolucao() armazena a data PREVISTA no construtor;
        //  aqui salvamos a data real sobrescrevendo com setDataDevolucao)
        boolean atrasado = java.time.LocalDate.now().isAfter(emprestimo.getDataDevolucao());
        emprestimo.setDataDevolucao(java.time.LocalDate.now()); // registra data real
        emprestimo.setAtrasado(atrasado);

        // Libera o exemplar físico
        // CORRIGIDO: era setEstaDisponivel() — nome correto é setDisponivel()
        livro.setDisponivel(true);

        // Remove o empréstimo do registro do Título (atualiza quantidadeDisponivel internamente)
        // CORRIGIDO: era listaDeTitulos.buscarTitulo() — método inexistente.
        // Agora busca via b.getTitulosAtualizados().buscarPorNome()
        Titulo titulo = b.getTitulosAtualizados().buscarPorNome(livro.getNome());
        if (titulo != null) {
            titulo.removerEmprestimo(emprestimo); // decrementa quantidadeDisponivel dentro de Titulo
        }

        // Remove o empréstimo do registro global da biblioteca
        // OBS: EmprestimoDAOLista.apagar() ainda está incompleto nos models.
        // Quando o responsável pelos models implementar, chamar:
        // b.getListaDeEmprestimos().apagar(emprestimo.getId());

        // Remove do registro do usuário
        // OBS: Usuario.removerEmprestimo() ainda está incompleto nos models.
        // Quando implementado, chamar: user.removerEmprestimo(emprestimo);

        if (atrasado) {
            System.out.println("⚠️ Livro devolvido com atraso. Regularize pendências futuras.");
        } else {
            System.out.println("✅ Livro devolvido com sucesso!");
        }

        // Notifica o próximo da fila de reservas deste título
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
     *
     * ALTERAÇÃO: o método original retornava List<Titulo> usando stream() em
     * listaDeTitulos (variável inexistente). Refatorado para retornar Titulo[]
     * iterando com for sobre b.getTitulosAtualizados().listarTitulos(),
     * pois TituloDAOLista não expõe getLista() nem stream().
     * Também foi adicionado um array de resultado auxiliar com dois passos
     * (contar + preencher), seguindo o padrão já usado nos outros DAOs do projeto.
     *
     * @param busca texto a procurar no nome do título
     * @return array de títulos cujo nome contém a busca
     */
    public Titulo[] buscarTituloPorNome(String busca) {
        if (busca == null || busca.isBlank()) {
            return mostrarCatalogo();
        }

        // CORRIGIDO: era listaDeTitulos.getLista().stream() — inexistente.
        // Agora usa for sobre o array retornado por listarTitulos()
        Titulo[] todos = b.getTitulosAtualizados().listarTitulos();

        int contador = 0;
        for (Titulo t : todos) {
            if (t.getNome().toLowerCase().contains(busca.toLowerCase()))
                contador++;
        }

        Titulo[] resultado = new Titulo[contador];
        int i = 0;
        for (Titulo t : todos) {
            if (t.getNome().toLowerCase().contains(busca.toLowerCase()))
                resultado[i++] = t;
        }
        return resultado;
    }

    /**
     * Filtra títulos por gênero.
     *
     * ALTERAÇÃO: o método original usava listaDeTitulos.selecionaTituloPorGenero()
     * — método inexistente. Trocado para b.getTitulosAtualizados().buscarPorGenero(),
     * que existe em TituloDAOLista e já retorna Titulo[].
     * O tipo de retorno também foi alterado de List<Titulo> para Titulo[].
     *
     * @param genero gênero desejado; se nulo ou vazio, retorna tudo
     * @return array de títulos do gênero informado
     */
    public Titulo[] filtrarPorGenero(String genero) {
        if (genero == null || genero.isBlank()) {
            return mostrarCatalogo();
        }
        // CORRIGIDO: era listaDeTitulos.selecionaTituloPorGenero() — inexistente
        return b.getTitulosAtualizados().buscarPorGenero(genero);
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
            System.out.println("⚠️ Acesso Bloqueado. Você possui livros em atraso. " +
                    "Regularize suas devoluções para fazer novas reservas.");
            return false;
        }

        if (contarReservasAtivas() >= 3) {
            System.out.println("Você já atingiu o limite de 3 reservas simultâneas.");
            return false;
        }

        // CORRIGIDO: era new Reserva(user) — construtor inexistente.
        // Reserva exige dois parâmetros: Reserva(Usuario, Titulo)
        Reserva reserva = new Reserva(user, titulo);

        // CORRIGIDO: era titulo.getFilaDeReservas().enfileirar() — método inexistente.
        // O nome correto em ReservaDAOFilaDePrioridade é salvar()
        titulo.filaDeReservas.salvar(reserva);

        // Registra também na lista global de reservas da biblioteca
        b.getListaDeReservas().salvar(reserva);

        System.out.println("✅ Reserva realizada! Você está na posição " +
                titulo.filaDeReservas.posicao(reserva) + " da fila.");
        return true;
    }

    /**
     * Cancela a reserva do usuário logado no título informado.
     * Busca a reserva do usuário na fila do título e a remove pelo ID.
     *
     * ALTERAÇÃO: o método original chamava titulo.getFilaDeReservas().remover(user)
     * — método remover(Usuario) inexistente em ReservaDAOFilaDePrioridade.
     * Agora percorre a fila, encontra a reserva do usuário e a remove pelo ID.
     *
     * @param titulo o título cuja reserva será cancelada
     * @return true se cancelada; false se não havia reserva deste usuário
     */
    public boolean desistirDaReserva(Titulo titulo) {
        // CORRIGIDO: era titulo.getFilaDeReservas().remover(user) — inexistente
        for (Reserva r : titulo.filaDeReservas.listar()) {
            if (r.getUsuario().getId().equals(user.getId())) {
                titulo.filaDeReservas.apagar(r.getId());
                b.getListaDeReservas().apagar(r.getId());
                System.out.println("✅ Reserva cancelada com sucesso.");
                return true;
            }
        }
        System.out.println("Nenhuma reserva encontrada para este título.");
        return false;
    }

    // =========================================================================
    // STATUS DO USUÁRIO
    // =========================================================================

    /**
     * Verifica se o usuário logado possui algum empréstimo ativo com devolução atrasada.
     *
     * ALTERAÇÃO: o método original chamava user.getEmprestimos().anyMatch() —
     * getEmprestimos() retorna Emprestimo[] (array), não Stream, portanto
     * anyMatch() não existe nele. Substituído pela chamada a
     * b.getListaDeEmprestimos().usuarioTemAtraso(user), que já faz exatamente
     * essa verificação de forma correta dentro de EmprestimoDAOLista.
     */
    public boolean usuarioPossuiAtraso() {
        // CORRIGIDO: era user.getEmprestimos().anyMatch(...) — array não tem stream
        return b.getListaDeEmprestimos().usuarioTemAtraso(user);
    }

    /**
     * Verifica se o usuário atingiu o limite de empréstimos simultâneos.
     *
     * ALTERAÇÃO: era user.getListaEmprestimos().size() — EmprestimoDAOLista
     * não possui size(), o método correto é tamanho().
     */
    public boolean atingiuLimiteDeEmprestimos() {
        // CORRIGIDO: era .size() — método inexistente, correto é .tamanho()
        return user.getListaEmprestimos().tamanho() >= user.getLimiteLivros();
    }

    // =========================================================================
    // Helpers privados
    // =========================================================================

    /**
     * Conta quantas reservas ativas o usuário possui no total (em todos os títulos).
     *
     * ALTERAÇÃO COMPLETA: o método original usava listaDeTitulos.getLista().stream()
     * e t.getFilaDeReservas().stream() — ambos inexistentes (getLista() não existe
     * em TituloDAOLista, e ReservaDAOFilaDePrioridade não tem stream()).
     * Substituído por dois for aninhados usando listarTitulos() e listar().
     */
    private int contarReservasAtivas() {
        // CORRIGIDO: era stream() em tipos que não o suportam
        int cont = 0;
        for (Titulo t : b.getTitulosAtualizados().listarTitulos()) {
            for (Reserva r : t.filaDeReservas.listar()) {
                if (r.getUsuario().getId().equals(user.getId()))
                    cont++;
            }
        }
        return cont;
    }

    /**
     * Notifica o próximo da fila de reservas que o título está disponível.
     *
     * ALTERAÇÃO: era titulo.getFilaDeReservas().peek() — método inexistente.
     * O nome correto em ReservaDAOFilaDePrioridade é proximo().
     */
    private void notificarProximoDaFila(Titulo titulo) {
        if (titulo == null) return;
        // CORRIGIDO: era .peek() — correto é .proximo()
        Reserva proxima = titulo.filaDeReservas.proximo();
        if (proxima != null) {
            System.out.println("📢 Notificando " + proxima.getUsuario().getNome() +
                    ": o título '" + titulo.getNome() + "' está disponível para retirada!");
        }
    }

    // REMOVIDO: prazoEmDias() — usava u.getCategoria() (método inexistente, o correto
    // é getTipo()) e os cases do switch usavam "Professor"/"Aluno" (minúsculo) em vez
    // de "PROFESSOR"/"ALUNO". Como o prazo já é calculado automaticamente pelo
    // construtor de Emprestimo(Usuario, Livro), este helper não é necessário aqui.
}