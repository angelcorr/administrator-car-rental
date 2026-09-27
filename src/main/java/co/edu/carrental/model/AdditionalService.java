package co.edu.carrental.model;

public class AdditionalService {

    private String code;
    private String name;
    private String description;
    private double price;
    private boolean availability;


    public AdditionalService(String code, String name, String description, double price, boolean availability){
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.availability = availability;
    }


    //Getters and setters
    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability){
        this.availability = availability;
    }


}
