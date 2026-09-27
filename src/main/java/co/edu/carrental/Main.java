package co.edu.carrental;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    URL fxmlLocation = Main.class.getResource("/view/main-view.fxml");

    if (fxmlLocation == null) {
      throw new IllegalStateException("No se pudo localizar 'main-view.fxml'. Revisa la ruta.");
    }

    FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
    Scene scene = new Scene(fxmlLoader.load(), 1000, 650);

    primaryStage.setTitle("Administrator Car Rental System");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}