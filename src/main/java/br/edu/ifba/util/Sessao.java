package br.edu.ifba.util;

import br.edu.ifba.models.Usuario;

public class Sessao {
    // O 'static' garante que o dado seja o mesmo para o programa inteiro
    private static Usuario usuarioLogado;

    public static void setUsuarioLogado(Usuario usuario) {
        usuarioLogado = usuario;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    // Método útil para o botão "Sair"
    public static void encerrarSessao() {
        usuarioLogado = null;
    }
}
