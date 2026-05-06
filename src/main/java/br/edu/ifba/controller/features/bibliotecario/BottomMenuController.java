package br.edu.ifba.controller.features.bibliotecario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class BottomMenuController {

    @FXML
    private void irDashboard(MouseEvent event) {
        carregarTela(event, "/views/bibliotecarioViews/Dashboard.fxml");
    }

    @FXML
    private void irInventario(MouseEvent event) {
        carregarTela(event, "/views/bibliotecarioViews/Inventario.fxml");
    }

    @FXML
    private void irFilaReserva(MouseEvent event) {
        carregarTela(event, "/views/bibliotecarioViews/FilaReserva.fxml");
    }

    @FXML
    private void irDevolucoes(MouseEvent event) {
        carregarTela(event, "/views/bibliotecarioViews/Devolucoes.fxml");
    }

    private void carregarTela(MouseEvent event, String caminhoFXML) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(caminhoFXML));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Aviso: A tela " + caminhoFXML + " ainda não foi criada.");
            // e.printStackTrace(); // Descomente para ver o erro completo no console
        }
    }
}
