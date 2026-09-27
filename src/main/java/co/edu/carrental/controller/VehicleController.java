package co.edu.carrental.controller;

import co.edu.carrental.model.Vehicle;
import co.edu.carrental.service.VehicleService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class VehicleController {

  @FXML private TextField txtPlate;
  @FXML private TextField txtBrand;
  @FXML private TextField txtModel;
  @FXML private TextField txtDailyCharge;
  @FXML private ComboBox<String> cmbAvailability;

  @FXML private Label lblMessage;

  @FXML private TableView<Vehicle> tblVehicles;
  @FXML private TableColumn<Vehicle, String> colPlate;
  @FXML private TableColumn<Vehicle, String> colBrand;
  @FXML private TableColumn<Vehicle, String> colModel;
  @FXML private TableColumn<Vehicle, Double> colBasePrice;
  @FXML private TableColumn<Vehicle, Boolean> colAvailable;

  private final VehicleService vehicleService = new VehicleService();
  private final ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();

  @FXML
  public void initialize() {
    cmbAvailability.setItems(FXCollections.observableArrayList("Available", "Occupied"));
    cmbAvailability.getSelectionModel().selectFirst();

    colPlate.setCellValueFactory(new PropertyValueFactory<>("plate"));
    colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
    colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
    colBasePrice.setCellValueFactory(new PropertyValueFactory<>("dailyCharge"));

    colAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));

    tblVehicles.setItems(vehicleList);
    loadVehicles();
  }

  @FXML
  private void handleRegisterVehicle() {
    try {
      String plate = txtPlate.getText().trim();
      String brand = txtBrand.getText().trim();
      String model = txtModel.getText().trim();
      String dailyCharge = txtDailyCharge.getText().trim();

      if (plate.isEmpty() || brand.isEmpty() || model.isEmpty() || dailyCharge.isEmpty()) {
        lblMessage.setText("Error: All fields are required.");
        return;
      }

      double basePrice = Double.parseDouble(dailyCharge);
      boolean isAvailable = "Available".equalsIgnoreCase(cmbAvailability.getValue());

      Vehicle vehicle = new Vehicle.Builder()
          .plate(plate)
          .brand(brand)
          .model(model)
          .dailyCharge(basePrice)
          .isAvailable(isAvailable)
          .build();
      vehicleService.addVehicle(vehicle);

      loadVehicles();
      handleClearFields();
      lblMessage.setText("Vehicle registered successfully!");
    } catch (NumberFormatException e) {
      lblMessage.setText("Error: Price must be a valid number.");
    }
  }

  @FXML
  private void handleToggleAvailability() {
    Vehicle selectedVehicle = tblVehicles.getSelectionModel().getSelectedItem();
    if (selectedVehicle == null) {
      lblMessage.setText("Error: Please select a vehicle from the table.");
      return;
    }

    selectedVehicle.setAvailable(!selectedVehicle.isAvailable());
    tblVehicles.refresh();
    lblMessage.setText("Vehicle status updated for plate: " + selectedVehicle.getPlate());
  }

  @FXML
  private void handleClearFields() {
    txtPlate.clear();
    txtBrand.clear();
    txtModel.clear();
    txtDailyCharge.clear();
    cmbAvailability.getSelectionModel().selectFirst();
    lblMessage.setText("");
  }

  private void loadVehicles() {
    vehicleList.setAll(vehicleService.getAllVehicles());
  }
}
