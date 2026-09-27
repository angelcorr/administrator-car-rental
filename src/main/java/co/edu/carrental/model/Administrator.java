package co.edu.carrental.model;

import java.util.List;
import java.util.ArrayList;

public class Administrator {
  private static Administrator instance;

  private String commercialName;
  private Integer nit;
  private String address;
  private String phoneNumber;
  private String email;
  private String website;
  private List<Client> clientList;
  private List<Vehicle> vehicleList;
  private List<RentalModality> rentalModalityList;
  private final List<AdditionalService> additionalServiceList = new ArrayList<>();
//  private List<Reservations> reservationList;

  private Administrator(Builder builder) {
    this.commercialName = builder.commercialName;
    this.nit = builder.nit;
    this.address = builder.address;
    this.phoneNumber = builder.phoneNumber;
    this.email = builder.email;
    this.website = builder.website;
  }

  public static Administrator getInstance() {
    if (instance == null) {
      instance = new Builder()
          .commercialName("Carrito en renta")
          .nit(1234567891)
          .address("El camino hacia la felicidad")
          .phoneNumber("+12543207593")
          .email("carritos_en_renta@yahoo.io")
          .website("https://bit.ly/4iLI9vO")
          .build();
    }

      return instance;
  }

  //Getters
  public String getCommercialName() {
    return commercialName;
  }

  public Integer getNit() {
    return nit;
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getWebsite() {
    return website;
  }

  public List<Client> getClientList() {
    return clientList;
  }

  public List<Vehicle> getVehicleList() {
    return vehicleList;
  }

  public List<RentalModality> getRentalModalityList() {
    return rentalModalityList;
  }

  public List<AdditionalService> getAdditionalServiceList() {return additionalServiceList;}

  public static class Builder {
    private String commercialName;
    private Integer nit;
    private String address;
    private String phoneNumber;
    private String email;
    private String website;

    public Builder commercialName(String commercialName) {
      this.commercialName = commercialName;
      return this;
    }

    public Builder nit(Integer nit) {
      this.nit = nit;
      return this;
    }

    public Builder address(String address) {
      this.address = address;
      return this;
    }

    public Builder phoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder website(String website) {
      this.website = website;
      return this;
    }

    public Administrator build() {
      if (commercialName == null || nit == null || address == null || phoneNumber == null ||email == null || website == null) {
        throw new IllegalStateException("Commercial name, nit, address, phoneNumber, email and website are required for the creation of the Administrator Class");
      }

      return new Administrator(this);
    }
  }
}
