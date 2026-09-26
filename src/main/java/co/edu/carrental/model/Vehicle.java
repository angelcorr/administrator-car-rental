package co.edu.carrental.model;

import enums.VehicleType;

import java.time.LocalDate;

public class Vehicle {
    private String plate;
    private String brand;
    private String model;
    private int age;
    private VehicleType type;
    private double dailyCharge;

    // constructor
    public Vehicle(String plate, String brand, String model, int age, VehicleType type, double dailyCharge) {
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.age = age;
        this.type = type;
        this.dailyCharge = dailyCharge;

        //validaciones
        if(plate ==null||plate.isBlank()||
                brand ==null||brand.isBlank()||
                model ==null||model.isBlank()||
                type ==null) {
            throw new IllegalArgumentException("Todos los datos del vehículo son necesarios.");
        }

        if(age< 0) {
            throw new IllegalArgumentException("La edad/año del vehículo no es válida.");
        }

        if(dailyCharge <=0) {
            throw new IllegalArgumentException("La tarifa diaria debe ser mayor a igual a cero.");
        }
    }

    //Getters and setters
    public String getPlate(){
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age=age;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public double getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(double dailyCharge) {
        this.dailyCharge=dailyCharge;
    }
}
