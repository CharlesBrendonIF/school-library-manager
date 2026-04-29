package models;

import repository.dao.EmprestimoDAOLista;
import repository.dao.LivroDAOLista;
import repository.dao.ReservaDAOLista;
import repository.dao.TituloDAOLista;

public class Biblioteca {
    private String[] idsOfStudents= {"s000001","s000002","s000003","s000004"};
    private String[] idsOfTeachers= {"p000001","p000002","p000003","p000004"};
    private String[] idsOfLibrarians= {"l000001"};

    public LivroDAOLista acervo;
    public TituloDAOLista listaDeTitulos;
    public EmprestimoDAOLista listaDeEmprestimos;
    public ReservaDAOLista ListaDeReservas;
    public UsuarioDaoLista listaDeUsuarios;

    //public ArrayList<Usuario> usuariosList= ArrayList<Usuario>;
    public boolean thisIDIsValid(String id){
        if(id.charAt(0)=='t'){
            for(String idOfATeacher: idsOfTeachers){
                if(idOfATeacher.equals(id))
                    return true;
            }

        }else if(id.charAt(0)=='s') {
            for (String idOfATeacher : idsOfTeachers) {
                if (idOfATeacher.equals(id))
                    return true;
            }
        }else if(id.charAt(0)=='l'){
            for(String idOfATeacher: idsOfTeachers){
                if(idOfATeacher.equals(id))
                    return true;
            }
        }
        return false;
    }

    public Usuario[] getUsuarios(){

    }
}
