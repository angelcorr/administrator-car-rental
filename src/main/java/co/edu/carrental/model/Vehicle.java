package co.edu.carrental.model;

import enums.VehicleType;

import java.time.Year;

public class Vehicle {
    private String plate;
    private String brand;
    private String model;
    private Year year;
    private VehicleType type;
    private double dailyCharge;
    private boolean isAvailable = true;

    private Vehicle (Builder builder) {
        this.plate = builder.plate;
        this.brand = builder.brand;
        this.model = builder.model;
        this.year = builder.year;
        this.type = builder.type;
        this.dailyCharge = builder.dailyCharge;
    }

    //Getters and setters
    public String getPlate(){
        return plate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Year getAge() {
        return year;
    }

    public VehicleType getType() {
        return type;
    }

    public double getDailyCharge() {
        return dailyCharge;
    }

    public boolean getAvailability() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public static class Builder {
        private String plate;
        private String brand;
        private String model;
        private Year year;
        private VehicleType type;
        private double dailyCharge;
        private boolean isAvailable;

        public Builder plate(String plate) {
            this.plate = plate;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder year(Year year) {
            this.year = year;
            return this;
        }

        public Builder type(VehicleType type) {
            this.type = type;
            return this;
        }

        public Builder dailyCharge(double dailyCharge) {
            this.dailyCharge = dailyCharge;
            return this;
        }

        public Builder isAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }
}
