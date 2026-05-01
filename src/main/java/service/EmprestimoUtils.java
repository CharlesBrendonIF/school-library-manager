package service;

/**
 * Utilitários compartilhados entre os services de empréstimo.
 * Centraliza a geração de IDs para evitar duplicação de código
 * entre UsuarioService e BibliotecarioService.
 */
public class EmprestimoUtils {

    private EmprestimoUtils() {
        // Classe utilitária — não instanciar
    }

    /**
     * Gera um ID único para um novo empréstimo.
     *
     * @return String no formato "EMP-{timestamp}"
     */
    public static String gerarId() {
        return "EMP-" + System.currentTimeMillis();
    }
}
