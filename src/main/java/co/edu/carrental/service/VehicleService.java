package co.edu.carrental.service;

import co.edu.carrental.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VehicleService {
  private final List<Vehicle> vehicles = new ArrayList<>();

  public void addVehicle(Vehicle vehicle) {
    verifyIfVehicleExists(vehicle.getPlate());

    vehicles.add(vehicle);
  }

  public void verifyIfVehicleExists(String plate) {
    if (findVehicleByPlate(plate).isPresent()) {
      throw new IllegalStateException("A vehicle with this plate already exists: " + plate);
    }
  }

  private Optional<Vehicle> findVehicleByPlate(String plate) {
    return vehicles.stream()
        .filter(vehicle -> vehicle.getPlate().equals(plate))
        .findFirst();
  }

  private List<Vehicle> getAvailableVehicles() {
    return vehicles.stream()
        .filter(Vehicle::getAvailability)
        .collect(Collectors.toList());
  }

  public boolean updateAvailability(String plate, boolean available) {
    Optional<Vehicle> vehicleOpt = findVehicleByPlate(plate);
    if (vehicleOpt.isPresent()) {
      vehicleOpt.get().setAvailable(available);
      return true;
    }
    return false;
  }
}
