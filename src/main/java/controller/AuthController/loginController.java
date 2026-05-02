package controller.AuthController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController {

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoSenha;

    @FXML
    public void login(ActionEvent event) {
        String usuario = campoUsuario.getText();
        String senha = campoSenha.getText();

        System.out.println("Login acionado");
        System.out.println("Usuário: " + usuario);
        System.out.println("Senha: " + senha);

        // ⚠ Aqui entraria autenticação real (não implementado)
    }

    @FXML
    public void irParaRecuperacaoSenha(ActionEvent event) {
        try {
            System.out.println("Ir para recuperação de senha");

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/recuperar.fxml") // mantém padrão simples
            );

            Stage stage = (Stage) campoUsuario.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
