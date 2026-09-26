package Discount;

public class PercentageDiscount implements IDiscount {

    private final double percentaje;

    public PercentageDiscount (double percentaje){
        if(percentaje<0 || percentaje >100){
            throw new IllegalArgumentException("The percentage must be between 0 and 100.");
        }
        this.percentaje=percentaje;
    }

    @Override
    public double calculate(double subtotal, int days) {
        return subtotal*(percentaje/100);
    }

    @Override
    public String getDescription() {
        return "promotion discount"+percentaje + "%"  ;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
