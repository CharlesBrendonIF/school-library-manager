package br.edu.ifba.controller.features.bibliotecario;

import br.edu.ifba.models.Usuario;
import br.edu.ifba.service.BibliotecarioService;
import br.edu.ifba.util.Sessao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    private Usuario user;
    private BibliotecarioService bibliotecarioService;
    
    public Label userLabel;
    @FXML private Label lblQtdAcervo;
    @FXML private Label lblQtdEmprestimos;
    @FXML private Label lblQtdHoje;
    @FXML private Label lblQtdAtrasos;
    @FXML private Label lblQtdReservas;
    @FXML private Label lblQtdUsuariosAtraso;
    
    @FXML private VBox bibliotecarioVBox;

    public void handleLogout() {
        try {
            Sessao.encerrarSessao();
            System.out.println("Logout realizado. Redirecionando para a tela de login...");
            
            Parent root = FXMLLoader.load(getClass().getResource("/views/AuthViews/login.fxml"));
            Stage stage = (Stage) bibliotecarioVBox.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            System.err.println("Erro ao fazer logout: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML private void onNavDashboard() {}
    @FXML private void onNavInventario() { navegarPara("/views/bibliotecarioViews/inventario.fxml"); }
    @FXML private void onNavReservas() { navegarPara("/views/bibliotecarioViews/controleDeReservas.fxml"); }
    @FXML private void onNavEmprestimos() { navegarPara("/views/bibliotecarioViews/controleDeEmprestimos.fxml"); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = Sessao.getUsuarioLogado();
        this.userLabel.setText(this.user.getNome());
        
        this.bibliotecarioService = new BibliotecarioService(this.user);
        carregarDadosDashboard();
    }
    
    private void carregarDadosDashboard() {
        lblQtdAcervo.setText(String.valueOf(bibliotecarioService.getTotalLivros()));
        lblQtdEmprestimos.setText(String.valueOf(bibliotecarioService.getNumeroEmprestimosAtivos()));
        lblQtdAtrasos.setText(String.valueOf(bibliotecarioService.getNumeroEmprestimosAtrasados()));
        lblQtdReservas.setText(String.valueOf(bibliotecarioService.getTotalReservas()));
        lblQtdUsuariosAtraso.setText(String.valueOf(bibliotecarioService.getUsuariosComAtraso()));
        lblQtdHoje.setText(String.valueOf(bibliotecarioService.getNumeroEmprestimosHoje()));
    }

    private void navegarPara(String fxmlPath) {
        try {
            System.out.println("Navegando para: " + fxmlPath);
            URL resource = Objects.requireNonNull(getClass().getResource(fxmlPath), "Recurso não encontrado: " + fxmlPath);
            Parent root = FXMLLoader.load(resource);
            Stage stage = (Stage) bibliotecarioVBox.getScene().getWindow();
            
            // Preserva o estado de maximizaçãoP
            boolean estaMaximizada = stage.isMaximized();
            
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            
            // Restaura o estado de maximização
            if (estaMaximizada) {
                stage.setMaximized(false);
                stage.setMaximized(true);
            }
            
            System.out.println("Navegação concluída para: " + fxmlPath);
        } catch (IOException | NullPointerException e) {
            System.err.println("Erro ao navegar para " + fxmlPath + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
