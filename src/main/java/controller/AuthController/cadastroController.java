package controller.AuthController;

// Imports para o JavaFX funcionar corretamente
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import models.Usuario;
import service.AuthService;
import util.Sessao;
import util.Tools;

public class cadastroController {

    // O nome deve ser exatamente o mesmo do "fx:id" no Scene Builder
    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoEmail;

    @FXML
    private TextField campoId;

    @FXML
    private PasswordField campoSenha;

    // Deve haver uma verificação d eigualdade com a senha anterior
    @FXML
    private PasswordField campoConfirmarSenha;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Hyperlink linkFazerLogin;

    // Método chamado quando o botão "Finalizar" é acionado
    @FXML
    public void cadastrar(ActionEvent event) {
        // Armazena os dados digitados em variáveis para processamento
        String nome = campoNome.getText();
        String email = campoEmail.getText();
        String id = campoId.getText();
        String senha = campoSenha.getText();
        String confirmaSenha = campoConfirmarSenha.getText();

        // Verificação de igualdade entre as senhas
        if (!senha.equals(confirmaSenha)) {

            // Cria um alerta de aviso para o usuário
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Erro no Cadastro");
            alerta.setHeaderText("As senhas não coincidem!");
            alerta.setContentText("Por favor, verifique se digitou a mesma senha nos dois campos.");
            alerta.showAndWait(); // Bloqueia a execução do Controller

            return;
        }

        // Para conferir se o código conseguiu ler tudo - apenas para testes no compilador
        System.out.println("Tentativa de Cadastro:");
        System.out.println("Nome: " + nome);
        System.out.println("E-mail: " + email);
        System.out.println("Matrícula: " + id);


        Usuario novoUsuario= AuthService.cadastro(nome, email, senha, id );
        if(novoUsuario!=null){
            Tools.enviarAlerta("Registro Realizado com sucesso");
            Sessao.setUsuarioLogado(novoUsuario);
            Tools.navegarPara(event,"/views/usuarioViews/Catalogo.fxml");
            // Se o Catalogo.fxml estiver dentro de usuarioViews

        }else{
            Tools.enviarAlerta("Registro Realizado não sucedido");
        }
    }

    // Direciona de volta para a tela de Login através do Hyperlink "Fazer Login"
    @FXML
    public void irParaLogin(ActionEvent event) {
        Tools.navegarPara(event,"/views/AuthViews/login.fxml");
    }
}
