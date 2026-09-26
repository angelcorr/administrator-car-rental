package co.edu.carrental.model;

import enums.Modality;
import enums.RentState;

public class RentalModalityExecutive  extends RentalModality{

    public static final double SURCHARGERATE = 0.05;

    public RentalModalityExecutive (String code, String name, String description, int minDuration, double dailyCharge, RentState state){
        super(code,name,description,minDuration,dailyCharge,state);
    }

    @Override
    public Modality getModality() {
        return Modality.EXECUTIVE;
    }

    //  el recargo lo vamos a manejar como un porcentaje del total
    @Override
    public double calculatecharge(int minDuration, double baseValue) {
        return baseValue*SURCHARGERATE;
    }
}
