package co.edu.carrental.service;

import co.edu.carrental.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientService {
  private final List<Client> clients = new ArrayList<>();

  public void addClient(Client client) {
    verifyIfClientExists(client.getId());

    clients.add(client);
  }

  public List<Client> getAllClients() {
    return new ArrayList<>(clients);
  }

  public String searchByPhoneNumberAndVerifyPerfectNumber(String phoneNumber) {
    Client client = findClientByPhoneNumber(phoneNumber).orElseThrow(() -> new IllegalStateException("This client with the provided phone number does not exists" + phoneNumber));
    Boolean isNumberPerfect = isClientPhonePerfect(client.getPhone());
    
    String returnValue;
    if (isNumberPerfect) {
      returnValue = "Phone number " + client.getPhone() + " is a perfect number and belongs to client " + client.getFullName();
    } else {
      returnValue = "Phone number " + client.getPhone() + " is a not perfect number and belongs to client " + client.getFullName();
    }

    return returnValue;
  }

  public Optional<Client> findClientByPhoneNumber(String phoneNumber) {
    return clients.stream()
        .filter(client -> client.getPhone().equals(phoneNumber))
        .findFirst();
  }

  public void verifyIfClientExists(String id) {
    if (findClientById(id).isPresent()) {
      throw new IllegalStateException("A client with this id already exists: " + id);
    }
  }

  private Optional<Client> findClientById(String id) {
    return clients.stream()
        .filter(client -> client.getId().equals(id))
        .findFirst();
  }

  public boolean isPerfectNumber(long number) {
    if (number <= 1) return false;

    long sum = 0;

//     we divide by two to split the phone number and save time
//    by only needing to compare and verify in less iterations
    for (long i = 1; i <= number / 2; i++) {
      if (number % i == 0) {
        sum += i;
      }
    }
    return sum == number;
  }

  public boolean isClientPhonePerfect(String phoneNumber) {
    try {
//      We use the regex to remove anything that is not a number
//      to only retrieve the number
      String digitsOnly = phoneNumber.replaceAll("\\D+", "");
      if (digitsOnly.isEmpty()) return false;

      long phoneAsNumber = Long.parseLong(digitsOnly);
      return isPerfectNumber(phoneAsNumber);
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
