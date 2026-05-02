package models;

import repository.dao.*;

import java.time.LocalDate;

public class Biblioteca {

    private LivroDAOLista acervo;
    private TituloDAOLista listaDeTitulos;
    private EmprestimoDAOLista listaDeEmprestimos;
    private ReservaDAOLista listaDeReservas;//Para a biblioteca ter um controle de todas as reservas
    private UsuarioDAOLista listaDeUsuarios;
    private BibliotecaRepository repositorio = new BibliotecaRepository();
    private static Biblioteca instance;

    private Biblioteca() {
        listaDeEmprestimos = new EmprestimoDAOLista();
        listaDeReservas = new ReservaDAOLista();
        listaDeUsuarios = repositorio.listaDeUsuarios;
        acervo = repositorio.listaDeLivros;
        listaDeTitulos= updateListaDeTitulos(acervo);
    }

    public static Biblioteca getInstance() {
        if (instance == null) {
            instance = new Biblioteca();
        }
        return instance;
    }

    public boolean thisIDIsValid(String id) {
        if (id.charAt(0) == 't') {
            for (String idOfATeacher : repositorio.idsOfTeachers) {
                if (idOfATeacher.equals(id))
                    return true;
            }

        } else if (id.charAt(0) == 's') {
            for (String idsOfStudent : repositorio.idsOfStudents) {
                if (idsOfStudent.equals(id))
                    return true;
            }
        } else if (id.charAt(0) == 'l') {
            for (String idsOfLibrarian : repositorio.idsOfLibrarians) {
                if (idsOfLibrarian.equals(id))
                    return true;
            }
        }
        return false;
    }

    public LivroDAOLista getAcervo() {
        return acervo;
    }

    public EmprestimoDAOLista getListaDeEmprestimos() {
        return listaDeEmprestimos;
    }

    public ReservaDAOLista getListaDeReservas() {
        return listaDeReservas;
    }

    public UsuarioDAOLista getListaDeUsuarios() {
        return listaDeUsuarios;
    }

    public TituloDAOLista getTitulosAtualizados() {
        // Toda vez que alguém pedir os títulos, você re-agrupa para garantir
        // que novos livros adicionados ao acervo apareçam aqui.
        this.listaDeTitulos = updateListaDeTitulos(this.acervo);
        return this.listaDeTitulos;
    }

    private TituloDAOLista updateListaDeTitulos(LivroDAOLista acervo) {
        acervo.ordenar(); // Garante que livros iguais fiquem juntos
        TituloDAOLista novaListaDeTitulos = new TituloDAOLista();

        int i = 0;
        while (i < acervo.quantidade()) {
            Livro modelo = acervo.selecionar(i);
            LivroDAOLista colecaoExemplares = new LivroDAOLista();

            // Agrupa todos os livros que possuem o mesmo ISBN
            while (i < acervo.quantidade() &&
                    acervo.selecionar(i).getIsbn().equals(modelo.getIsbn())) {

                colecaoExemplares.salvar(acervo.selecionar(i));
                i++; // Move para o próximo exemplar
            }

            // Cria um único Título baseado na coleção de exemplares idênticos
            novaListaDeTitulos.salvar(new Titulo(colecaoExemplares));

            // O "i" já está na posição do próximo livro diferente,
            // o loop principal continuará dali.
        }

        return novaListaDeTitulos;
    }
}

class BibliotecaRepository{
    public String[] idsOfStudents= {"s000001","s000002","s000003","s000004"};
    public String[] idsOfTeachers= {"p000001","p000002","p000003","p000004"};
    public String[] idsOfLibrarians= {"l000001"};

    public LivroDAOLista listaDeLivros= new LivroDAOLista();
    public UsuarioDAOLista listaDeUsuarios= new UsuarioDAOLista();


    public BibliotecaRepository(){
        listaDeLivros.salvar(new Livro(
                "Código Limpo",
                "9780132350884",
                "Programação",
                true,
                "Um guia prático para escrever código limpo e sustentável",
                LocalDate.of(2008, 8, 1)
        ));

        listaDeLivros.salvar(new Livro(
                "Estruturas de Dados e Algoritmos",
                "9788575226937",
                "Computação",
                true,
                "Conceitos fundamentais de estruturas de dados",
                LocalDate.of(2010, 5, 10)
        ));

        listaDeLivros.salvar(new Livro(
                "Java: Como Programar",
                "9780134694726",
                "Programação",
                true,
                "Livro completo sobre Java",
                LocalDate.of(2016, 1, 1)
        ));

        listaDeLivros.salvar(new Livro(
                "Padrões de Projeto",
                "9788573076103",
                "Engenharia de Software",
                true,
                "Soluções reutilizáveis para problemas comuns",
                LocalDate.of(1994, 10, 21)
        ));

        listaDeLivros.salvar(new Livro(
                "Algoritmos",
                "9788535236996",
                "Computação",
                true,
                "Estudo de algoritmos e lógica de programação",
                LocalDate.of(2011, 3, 15)
        ));


        listaDeUsuarios.salvar(new Usuario(
                "s000001",
                "João Silva",
                "joao.silva@email.com",
                "123456",
                TipoUsuario.ALUNO
        ));

        listaDeUsuarios.salvar(new Usuario(
                "s000002",
                "Maria Oliveira",
                "maria.oliveira@email.com",
                "abc123",
                TipoUsuario.ALUNO
        ));

        listaDeUsuarios.salvar(new Usuario(
                "t000001",
                "Carlos Souza",
                "carlos.souza@email.com",
                "prof123",
                TipoUsuario.PROFESSOR
        ));

        listaDeUsuarios.salvar(new Usuario(
                "t000002",
                "Ana Pereira",
                "ana.pereira@email.com",
                "senha456",
                TipoUsuario.PROFESSOR
        ));

        listaDeUsuarios.salvar(new Usuario(
                "l000001",
                "Bibliotecário",
                "admin@biblioteca.com",
                "admin",
                TipoUsuario.BIBLIOTECARIO
        ));

    }
}
