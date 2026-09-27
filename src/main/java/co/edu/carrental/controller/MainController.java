package co.edu.carrental.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;

public class MainController {

  @FXML private StackPane contentArea;

  @FXML
  public void initialize() {
    // Cargar por defecto la primera vista (por ejemplo, Clientes)
    showClientView();
  }

  @FXML
  private void showClientView() {
    loadView("/view/client-view.fxml");
  }

  @FXML
  private void showVehicleView() {
    loadView("/view/vehicle-view.fxml");
  }

  @FXML
  private void showAdministratorView() {
    loadView("/view/administrator-view.fxml");
  }

  private void loadView(String fxmlPath) {
    try {
      URL url = getClass().getResource(fxmlPath);
      if (url == null) {
        System.err.println("No se encontró el archivo FXML: " + fxmlPath);
        return;
      }
      Node node = FXMLLoader.load(url);
      contentArea.getChildren().setAll(node);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
