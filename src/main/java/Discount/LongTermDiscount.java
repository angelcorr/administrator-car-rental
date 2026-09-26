package Discount;

public class LongTermDiscount implements IDiscount{
    // se agrega funcionalidad por de descuentos a largo plazo


    @Override
    public double calculate(double subtotal, int days) {
        if (days>=15){
            return  subtotal * 0.10;
        }
        if (days>=7){
            return subtotal * 0.05;
        }

        return 0;
    }

    @Override
    public String getDescription() {
        return "Long-term discount (5% ≥7 days, 10% ≥15 days)";
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
