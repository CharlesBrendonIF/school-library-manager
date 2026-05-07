package br.edu.ifba.controller.features.bibliotecario;

import br.edu.ifba.util.Sessao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class TopBarController {

    @FXML
    private ImageView irParaLogin;

    @FXML
    private Label NomeUsuario;

    @FXML
    void handleSair(MouseEvent event) {
        try {
            // Encerrar a sessão
            Sessao.encerrarSessao();
            System.out.println("Sessão encerrada com sucesso");

            Parent root = FXMLLoader.load(getClass().getResource("/views/AuthViews/login.fxml"));

            // Pega a janela (Stage) atual a partir do evento do clique
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            System.err.println("Erro ao carregar a tela de login: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {

    }
}
