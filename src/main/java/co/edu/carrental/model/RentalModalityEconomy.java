package co.edu.carrental.model;

import enums.Modality;
import enums.RentState;

public class RentalModalityEconomy extends RentalModality {

    //clase economica hereda de la clase padre rental modality
    public RentalModalityEconomy(String code, String name, String description, int minDuration, double dailyCharge, RentState state){
        super  (code, name, description, minDuration, dailyCharge, state);
    }


    @Override
    public Modality getModality() {
        return Modality.ECONOMY;
    }

    @Override
    public double calculatecharge(int minDuration, double baseValue ) {
        return 0;
    }
}
