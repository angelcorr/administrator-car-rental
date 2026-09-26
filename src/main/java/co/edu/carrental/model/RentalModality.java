package co.edu.carrental.model;

import enums.Modality;
import enums.RentState;

public abstract class RentalModality {

    private String code;
    private String name;
    private String description;
    private int minDuration;
    private double dailyCharge;
    public RentState state;

    public RentalModality(String code,String name,String description,int minDuration, double dailyCharge,RentState state){
    this.code = code;
    this.name = name;
    this.description = description;
    this.minDuration = minDuration;
    this.dailyCharge = dailyCharge;
    this.state= state;
}


public abstract Modality getModality();
public abstract double calculatecharge(int minDuration,double baseValue);


//validar ingreso de dias
public void validateDuration(int dias) {
    if (dias < minDuration) {
        throw new IllegalArgumentException("La modalidad " + name + "Debe de tener Mínimo " + minDuration + "Dias y se ingresaron " + dias);
    }

}


//calcular valor
public final double calculateValue(int dias){
validateDuration(dias);
double base= dailyCharge*dias;
return base+ calculatecharge(dias,dailyCharge);
    }

// disponilidad

    public boolean availability(){
    return state == RentState.AVAILABLE;
    }

    //getters and setters
    public String getCode(){
    return code;
}
    public void setcode(String code){
        this.code = code ;
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
    public int getMinDuration(){
        return minDuration;
    }

    public void setMinDuration(int minDuration){
        this.minDuration = minDuration;
    }

    public double getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(double dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public RentState getState() {
        return state;
    }

    public void setState(RentState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return name + " [" + getModality() + "]";
    }
}

