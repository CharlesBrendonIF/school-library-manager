package controller.AuthController;

// Imports para o JavaFX para funcionar corretamente
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

import service.AuthService;
import models.Usuario;
import util.Sessao;
import util.Tools;

import static util.Tools.enviarAlerta;

public class loginController {

    // O nome "campoEmail" deve ser exatamente o mesmo do "fx:id"
    @FXML
    private TextField campoEmail;

    // O nome "campoSenha" deve ser exatamente o mesmo do "fx:id"
    @FXML
    private PasswordField campoSenha;

    @FXML
    private Button entrarBtn;

    @FXML
    private Hyperlink cadastroLink;

    // Método chamado quando o botão "Entrar" é acionado
    @FXML
    public void login(ActionEvent event) {
        // Armazena o texto do usuário em variáveis
        String email = campoEmail.getText();
        String senha = campoSenha.getText();

        // Para conferir se o código conseguiu ler o que foi digitado - apenas para testes no compilador
        System.out.println("Login acionado");
        System.out.println("Usuário: " + email);
        System.out.println("Senha: " + senha);

        Usuario userLogado = AuthService.login(email,senha);

        if(userLogado!=null){
            Sessao.setUsuarioLogado(userLogado);
            Tools.mudarTela(event, "/views/usuarioViews/Catalogo.fxml");
        }else{
            enviarAlerta("Usuario não encontrado");
        }
    }

    @FXML
    public void fazerCadastro(ActionEvent event){
        Tools.mudarTela(event,"/views/AuthViews/cadastro.fxml");
    }


    /*// Direciona a página recuperarSenha - verificar se vamos implementar (continua no login.Contoller
    @FXML
    public void irParaRecuperacaoSenha(ActionEvent event) {
        try {
            System.out.println("Esqueci minha senha");

            // Para localizar o arquivo da tela recuperarSenha.fxml
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/recuperar.fxml")
            );

            // Hierarquia do JavaFX
            // Para indicar ao Controller a tela atual
            Stage stage = (Stage) campoUsuario.getScene().getWindow();
            // Guia para csair da tela de login e ir para tela de recuperarSenha
            stage.setScene(new Scene(loader.load()));

        }

        // Se o arquivo .fxml não for encontrado, Exception indica erro
        catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro de Sistema");
            alerta.setHeaderText("Não foi possível mudar de tela.");
            alerta.setContentText("Ocorreu um problema técnico ao carregar a página de recuperação.");
            alerta.showAndWait(); // Trava tela até usuário dar ok
        }
    }*/
}
