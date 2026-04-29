package service;
import models.Biblioteca;
import models.Usuario;
//Login e cadastro
public class AuthService {


    //Durante o login, deve ser verificado se os dados inseridos(email e senha) são compativeis com algum usuario
    // já cadastrado. a Lista de usuarios esta em Biblioteca.listaDeUsuarios, da uma olhada nela para costruir os
    // metodos dessa classe. Como parametro dos metodos de login e cadastro sugiro colocar um om objeto da classe
    // biblioteca, para acessar os dados necessarios, exemplo: Usuario login(Biblioteca b)
    //Em cadastro, deve se verificar se o Id inserido no parametro é valido, ou seja se pertence ao banco de dados
    //de dados de id(Biblioteca.thisIdIsValid()). Exemplo sugerido: Usuario cadastro(Biblioteca b)

    ////>>>Parte de Kaique, precisa ser revisado ainda!!!
    public Usuario login(Biblioteca b, String email, String senha) {

        // -------------------------------------------------------------------
        // PASSO 1 — Validação básica de entrada (null)
        // -------------------------------------------------------------------
        if (email == null || email.isBlank() || senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("E-mail e senha são obrigatórios.");
        }

        // -------------------------------------------------------------------
        // PASSO 2 — Busca na listaDeUsuarios da Biblioteca
        //.findFirst() trata corretamente o caso em que nenhum usuário é encontrado
        // -------------------------------------------------------------------
        return b.getListaDeUsuarios(///
                .stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email)
                        && u.getSenha().equals(senha))
                //email indifere maiúsculas e minúsculas
                //senhas obrigam uso exato de maiúsculas e minúsculas
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(MSG_LOGIN_INVALIDO)
                );
    }


    public void cadastro(Biblioteca b, Usuario novo) {

        // -------------------------------------------------------------------
        // PASSO 1 — Verificar se o ID pertence ao banco de IDs válidos.
        // --------------------------------------------------------------------------------
        // thisIdIsValid() bloqueia cadastros de IDs inventados ou de pessoas sem vínculo com a escola.
        // -------------------------------------------------------------------
        if (!b.thisIdIsValid(novo.getId())) {
            throw new IllegalArgumentException(
                    "ID institucional '" + novo.getId() + "' não encontrado no sistema. "
                            + "Verifique o número de matrícula/registro."
            );
        }

        // -------------------------------------------------------------------
        // PASSO 2 — Garantir unicidade do e-mail.
        // -------------------------------------------------------------------
        boolean emailJaEmUso = b.getListaDeUsuarios()
                .stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(novo.getEmail()));

        if (emailJaEmUso) {
            throw new IllegalArgumentException(
                    "O e-mail '" + novo.getEmail() + "' já está cadastrado no sistema."
            );
        }
        // -------------------------------------------------------------------
        // PASSO 3 — Garantir unicidade do ID institucional
        // -------------------------------------------------------------------
        boolean idJaCadastrado = b.getListaDeUsuarios()
                .stream()
                .anyMatch(u -> u.getId().equals(novo.getId()));

        if (idJaCadastrado) {
            throw new IllegalArgumentException(
                    "Já existe um usuário cadastrado com o ID '" + novo.getId() + "'."
            );
        }

        // -------------------------------------------------------------------
        // PASSO 4 — Todas as validações passaram: adiciona à lista de emails cadastrados
        // --------------------------------------------------------------------------------
        b.getListaDeUsuarios().add(novo);
    }
}
