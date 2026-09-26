package co.edu.carrental.model;

import enums.CoverageType;
import enums.Modality;
import enums.RentState;

public class RentalModalityPremium extends RentalModality{

    private CoverageType coverageType;
    private int  additionalConductors;
    private String specialfeatures;

    public RentalModalityPremium(String code, String name, String description, int minDuration, double dailyCharge, RentState state,
    CoverageType coverageType, int additionalConductors,String specialfeatures) {
        super(code, name, description, minDuration, dailyCharge, state);

        this.coverageType = coverageType;
        this.additionalConductors = additionalConductors;
        this.specialfeatures = specialfeatures;

    if (coverageType==null){
        throw new IllegalArgumentException("The Premium plan requires a specific type of coverage");
    }

    if (additionalConductors<0){
        throw new IllegalArgumentException("This field cannot be negative.");
    }

    }

    @Override
    public Modality getModality() {
        return Modality.PREMIUM;
    }

    @Override
    public double calculatecharge(int minDuration, double baseValue) {
        return baseValue* coverageType.getSurchargePercentage();
    }


    //Getters and setters


    public CoverageType getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(CoverageType coverageType){
        this.coverageType = coverageType;
    }

    public int getAdditionalConductors(){
        return additionalConductors;
    }

    public void setAdditionalConductors(int additionalConductors){
        this.additionalConductors = additionalConductors;
    }

    public String getSpecialfeatures(){
        return specialfeatures;
    }

    public void setSpecialfeatures(String specialfeatures){
        this.specialfeatures = specialfeatures;
    }
}



