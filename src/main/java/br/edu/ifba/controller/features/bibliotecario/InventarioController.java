package br.edu.ifba.controller.features.bibliotecario;

import br.edu.ifba.util.Sessao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class InventarioController implements Initializable {
    
    @FXML
    private void handleLogout(MouseEvent event) {
        try {
            Sessao.encerrarSessao();
            System.out.println("Logout realizado. Redirecionando para login...");

            String fxmlPath = "/views/AuthViews/login.fxml";
            URL resource = Objects.requireNonNull(getClass().getResource(fxmlPath), "Recurso não encontrado: " + fxmlPath);

            Parent root = FXMLLoader.load(resource);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            System.err.println("Erro ao fazer logout: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAdicionarLivro(MouseEvent event) {
        navegarPara("/views/bibliotecarioViews/adicionarLivro.fxml", event);
    }

    private void navegarPara(String fxmlPath, MouseEvent event) {
        try {
            System.out.println("Navegando para: " + fxmlPath);
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            System.err.println("Erro ao navegar para " + fxmlPath + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Inventário do Bibliotecário inicializado");
    }
}
