package co.edu.carrental.controller;

import co.edu.carrental.model.Administrator;
import co.edu.carrental.service.AdministratorService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdministratorController {

  @FXML private Label lblCommercialName;
  @FXML private Label lblNit;
  @FXML private Label lblAddress;
  @FXML private Label lblPhoneNumber;
  @FXML private Label lblEmail;
  @FXML private Label lblWebsite;

  private final AdministratorService adminService = new AdministratorService();

  @FXML
  public void initialize() {
    loadCompanyProfile();
  }

  private void loadCompanyProfile() {
    Administrator admin = adminService.getCompanyProfile();
    if (admin != null) {
      lblCommercialName.setText(admin.getCommercialName());
      lblNit.setText(admin.getNit() != null ? admin.getNit().toString() : "");
      lblAddress.setText(admin.getAddress());
      lblPhoneNumber.setText(admin.getPhoneNumber());
      lblEmail.setText(admin.getEmail());
      lblWebsite.setText(admin.getWebsite());
    }
  }
}
