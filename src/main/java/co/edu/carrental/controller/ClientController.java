package co.edu.carrental.controller;

import co.edu.carrental.model.Client;
import co.edu.carrental.service.ClientService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class ClientController {
  @FXML private TextField txtFullName;
  @FXML private TextField txtIdDocument;
  @FXML private TextField txtPhone;
  @FXML private TextField txtEmail;
  @FXML private TextField txtAge;

  @FXML private Label lblMessage;

  @FXML private TableView<Client> tblClients;
  @FXML
  private TableColumn<Client, String> colDocument;
  @FXML private TableColumn<Client, String> colFullName;
  @FXML private TableColumn<Client, String> colPhone;
  @FXML private TableColumn<Client, String> colEmail;
  @FXML private TableColumn<Client, Integer> colAge;

  private final ClientService clientService = new ClientService();
  private final ObservableList<Client> clientList = FXCollections.observableArrayList();

  @FXML
  public void initialize() {
    colDocument.setCellValueFactory(new PropertyValueFactory<>("id"));
    colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    colAge.setCellValueFactory(new PropertyValueFactory<>("age"));

    tblClients.setItems(clientList);
  }

  @FXML
  private void handleRegisterClient() {
    try {
      String fullName = txtFullName.getText();
      String id = txtIdDocument.getText();
      String phone = txtPhone.getText();
      String email = txtEmail.getText();
      String age = txtAge.getText();

      if (fullName.isEmpty() || id.isEmpty() || phone.isEmpty() || email.isEmpty() || age.isEmpty()) {
        lblMessage.setText("Error: Fields cannot be empty.");
        return;
      }

      Client client = new Client.Builder()
          .fullName(fullName)
          .id(id)
          .phone(phone)
          .email(email)
          .age(age)
          .createAt(LocalDate.now())
          .build();

      clientService.addClient(client);

      clientList.setAll(clientService.getAllClients());
      handleClearFields();
      lblMessage.setText("Client registered successfully!");
    } catch (NumberFormatException e) {
      lblMessage.setText("Error: Age must be a valid number.");
    }
  }

  @FXML
  private void handleCheckPerfectNumber() {
    Client selectedClient = tblClients.getSelectionModel().getSelectedItem();
    String phoneToCheck = (selectedClient != null) ? selectedClient.getPhone() : txtPhone.getText();

    if (phoneToCheck == null || phoneToCheck.trim().isEmpty()) {
      lblMessage.setText("Error: Select a client or enter a phone number.");
      return;
    }

    boolean isPerfect = clientService.isClientPhonePerfect(phoneToCheck);
    if (isPerfect) {
      lblMessage.setText("Phone " + phoneToCheck + " IS a Perfect Number!");
    } else {
      lblMessage.setText("Phone " + phoneToCheck + " is NOT a Perfect Number.");
    }
  }

  @FXML
  private void handleClearFields() {
    txtFullName.clear();
    txtIdDocument.clear();
    txtPhone.clear();
    txtEmail.clear();
    txtAge.clear();
    lblMessage.setText("");
  }
}
