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
    public Vehicle (String plate, String brand, String model, int age, VehicleType type, double dailyCharge) {
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.age = age;
        this.type =type;
    }

    //Getters and setters
    public String getPlate(){
        return plate;
    }

    public void setFullName(String plate) {
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
