package models;

import repository.dao.*;

import java.time.LocalDate;

public class Biblioteca {

    public LivroDAOLista acervo;
    public TituloDAOLista listaDeTitulos;
    public EmprestimoDAOLista listaDeEmprestimos;
    public ReservaDAOLista listaDeReservas;//Para a biblioteca ter um controle de todas as reservas
    public UsuarioDAOLista listaDeUsuarios;
    private BibliotecaRepository repositorio=new BibliotecaRepository();

    public Biblioteca(){
        listaDeEmprestimos=new EmprestimoDAOLista();
        listaDeReservas=new ReservaDAOLista();
        listaDeUsuarios=repositorio.listaDeUsuarios;
        acervo=repositorio.listaDeLivros;
    }

    public boolean thisIDIsValid(String id){
        if(id.charAt(0)=='t'){
            for(String idOfATeacher: repositorio.idsOfTeachers){
                if(idOfATeacher.equals(id))
                    return true;
            }

        }else if(id.charAt(0)=='s') {
            for (String idsOfStudent: repositorio.idsOfStudents) {
                if (idsOfStudent.equals(id))
                    return true;
            }
        }else if(id.charAt(0)=='l'){
            for(String idsOfLibrarian: repositorio.idsOfLibrarians){
                if(idsOfLibrarian.equals(id))
                    return true;
            }
        }
        return false;
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
