package controller.AuthController;

// Imports para o JavaFX funcionar corretamente
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class cadastroController {

    // O nome deve ser exatamente o mesmo do "fx:id" no Scene Builder
    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoEmail;

    @FXML
    private TextField campoMatricula;

    @FXML
    private PasswordField campoSenha;

    // Deve haver uma verificação d eigualdade com a senha anterior
    @FXML
    private PasswordField campoConfirmarSenha;

    // Método chamado quando o botão "Finalizar" é acionado
    @FXML
    public void cadastrar(ActionEvent event) {
        // Armazena os dados digitados em variáveis para processamento
        String nome = campoNome.getText();
        String email = campoEmail.getText();
        String matricula = campoMatricula.getText();
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
        System.out.println("Matrícula: " + matricula);

        // AQUI ENTRARIA A LÓGICA PARA SALVAR NO BANCO DE DADOS
    }

    // Direciona de volta para a tela de Login através do Hyperlink "Fazer Login"
    @FXML
    public void irParaLogin(ActionEvent event) {
        try {
            System.out.println("Retornando para a tela de login...");

            // Localiza o arquivo da tela de login original
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/login.fxml")
            );

            // Hierarquia do JavaFX: identifica a janela atual através de um dos campos
            Stage stage = (Stage) campoEmail.getScene().getWindow();

            // Troca o cenário de Cadastro pelo cenário de Login
            stage.setScene(new Scene(loader.load()));

        } catch (Exception e) {
            // Caso o arquivo fxml de login tenha sido movido ou renomeado
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro de Navegação");
            alerta.setHeaderText("Não foi possível retornar ao login.");
            alerta.setContentText("Ocorreu um problema técnico ao carregar a página de acesso.");
            alerta.showAndWait();
        }
    }
}
