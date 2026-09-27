package co.edu.carrental;

import co.edu.carrental.model.Administrator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    URL fxmlLocation = Main.class.getResource("/view/vehicle-view.fxml");

    if (fxmlLocation == null) {
      fxmlLocation = Thread.currentThread().getContextClassLoader().getResource("/view/vehicle-view.fxml");
    }

    if (fxmlLocation == null) {
      throw new IllegalStateException("No se pudo localizar 'client-view.fxml'. Ejecuta 'compile' en Maven.");
    }

    FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
    Scene scene = new Scene(fxmlLoader.load(), 850, 550);

    primaryStage.setTitle("Administrator Car Rental - Client Management");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
