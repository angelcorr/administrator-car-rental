package co.edu.carrental.model;

import enums.Modality;
import enums.RentState;

public class RentalModalityExecutive  extends RentalModality{

    public RentalModalityExecutive (String code, String name, String description, int minDuration, double dailyCharge, RentState state){
        super(code,name,description,minDuration,dailyCharge,state);
    }

    @Override
    public Modality getModality() {
        return Modality.EXECUTIVE;
    }

    @Override
    public double calculatecharge(int minDuration, int dailyCharge) {
        return 0;
    }
}
