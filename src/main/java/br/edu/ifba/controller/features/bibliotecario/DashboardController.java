package br.edu.ifba.controller.features.bibliotecario;

import br.edu.ifba.models.Usuario;
import br.edu.ifba.util.Sessao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    private Usuario user;
    public Label userLabel;

    public void handleLogout() {
        System.out.println("Logout realizado. Redirecionando para a tela de login...");
    }

    @FXML private void onNavDashboard() {}
    @FXML private void onNavInventario() { navegarPara("/views/bibliotecarioViews/inventario.fxml"); }
    @FXML private void onNavReservas() { navegarPara("/views/bibliotecarioViews/controleDeReservas.fxml"); }
    @FXML private void onNavEmprestimos() { navegarPara("/views/bibliotecarioViews/controleDeEmprestimos.fxml"); }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = Sessao.getUsuarioLogado();
        this.userLabel.setText(this.user.getNome());
    }

    private void navegarPara(String fxmlPath) {
        System.out.println("Navegando para: " + fxmlPath);
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
//            Stage stage = (Stage) listaLivrosContainer.getScene().getWindow();
//            stage.setScene(new Scene(root));
//
//        } catch (IOException e) {
//            System.err.println("Erro ao navegar para: " + fxmlPath);
//            e.printStackTrace();
//        }
    }
}
