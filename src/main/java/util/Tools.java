package util;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class Tools {

    public static  void enviarAlerta(String alerta){
        Alert aviso= new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Aviso");
        aviso.setHeaderText(null);
        aviso.setContentText(alerta);
        aviso.showAndWait();
    }

    public static void navegarPara(ActionEvent event, String caminhoFXML) {
        try {
            // Carrega o novo arquivo
            Parent root = FXMLLoader.load(Tools.class.getResource(caminhoFXML));

            // Pega o Stage atual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Define a nova cena
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException e) {
            Tools.enviarAlerta("Erro ao carregar a tela: " + e);
            System.err.println("Erro ao carregar a tela: " + caminhoFXML);
            e.printStackTrace();
        }
    }


}
