package service;

import models.Biblioteca; // Corrigido para plural
import models.Usuario;// Corrigido para plural
import models.TipoUsuario;
import repository.dao.UsuarioDAOLista;

// Login e cadastro
public class AuthService {
    private Biblioteca b;


    public AuthService(){
        b= Biblioteca.getInstance();

    }
    /**
     * Realiza o login do usuário.
     * Verifica se o email e a senha fornecidos correspondem a algum usuário
     * já cadastrado em Biblioteca.listaDeUsuarios.
     *
     * param b a instância da Biblioteca (fonte dos dados de usuários)
     * return o Usuario autenticado, ou null se as credenciais forem inválidas
     */
    public Usuario login(String email, String senha) {
        for (Usuario u : b.getListaDeUsuarios().listar()) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getSenha().equals(senha)) {
                System.out.println("Login realizado com sucesso! Bem-vindo(a), " + u.getNome() + ".");
                return u;
            }
        }

        System.out.println("Email ou senha inválidos.");
        return null;
    }

    /**
     * Realiza o cadastro de um novo usuário.
     * Verifica se o ID fornecido é válido consultando Biblioteca.thisIdIsValid().
     * Verifica também se o email já não está em uso.
     * param b a instância da Biblioteca
     * @return o novo Usuario cadastrado, ou null se o cadastro falhar
     */
    public Usuario cadastro(String nome,String email, String senha, String id) {

        // Valida o ID junto ao banco de IDs da biblioteca
        if (!b.thisIDIsValid(id)) {
            return null;
        }

        // Verifica se o email já está cadastrado
        for (Usuario u : b.getListaDeUsuarios().listar()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Este email já está em uso.");
                return null;
            }
        }

        // Determina a categoria com base no prefixo/formato do ID
        TipoUsuario categoria = resolverCategoria(id);

        Usuario novoUsuario = new Usuario(id, nome, email, senha, categoria);
        b.getListaDeUsuarios().salvar(novoUsuario);

        System.out.println("Cadastro realizado com sucesso! Bem-vindo(a), " + nome + ".");
        return novoUsuario;
    }

    /**
     * Infere a categoria do usuário a partir do ID validado.
     * Ajuste a lógica conforme a convenção de IDs do projeto.
     */

    private TipoUsuario resolverCategoria(String id) {
        if (id.charAt(0)=='t') {
            return TipoUsuario.PROFESSOR;
        } else if (id.charAt(0)=='l') {
            return TipoUsuario.BIBLIOTECARIO;
        } else {
            return TipoUsuario.ALUNO;
        }
    }
}
