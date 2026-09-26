package Discount;

public class FullPrice implements IDiscount{

    @Override
    public double calculate(double subtotal, int days) {
        return 0;
    }

    @Override
    public String getDescription() {
        return "FullPrice";
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
