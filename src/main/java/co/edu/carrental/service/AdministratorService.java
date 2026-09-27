package co.edu.carrental.service;

import co.edu.carrental.model.Administrator;

public class AdministratorService {

  public Administrator getCompanyProfile() {
    return Administrator.getInstance();
  }

}