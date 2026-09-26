    package enums;

//is used by class RentalModalityPremium

    public enum CoverageType {
        BASIC(0.0),
        BROAD(0.10),
        TOTAL(0.20);


        private final double surchargePercentage;

        CoverageType(double surchargePercentage) {
            this.surchargePercentage = surchargePercentage;
        }

        public double getSurchargePercentage() {
            return surchargePercentage;
        }
    }