package co.edu.carrental.model;
import Discount.IDiscount;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;


import java.time.LocalDate;
public class Booking {

    private final String code;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Client client;
    private final Vehicle vehicle;
    private final RentalModality rentalModality;
    private final IDiscount discount;
    private final List<AditionalService> additionalServices;


    //constructor
    public Booking(String code, LocalDate startDate, LocalDate endDate, Client client, Vehicle vehicle, RentalModality rentalModality
            , IDiscount discount, List<AditionalService> additionalServices) {
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
        this.vehicle = vehicle;
        this.rentalModality = rentalModality;
        this.discount = discount;
        this.additionalServices = additionalServices;

    }


    //Builder
    private Booking(Builder builder) {
        //constructor builder
        this.code = builder.code;
        this.startDate = builder.starDate;
        this.endDate = builder.endDate;
        this.client = builder.client;
        this.vehicle = builder.vehicle;
        this.rentalModality = builder.rentalModality;
        this.discount = builder.discount;
        this.additionalServices = builder.additionalServices;
    }

    public static class Builder {
        private String code;
        private LocalDate starDate;
        private LocalDate endDate;
        private Client client;
        private Vehicle vehicle;
        private RentalModality rentalModality;
        private IDiscount discount;
        private List<AditionalService> additionalServices = new ArrayList<>();


        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder starDate(LocalDate starDate) {
            this.starDate = starDate;
            return this;
        }

        public Builder endDate(LocalDate endDate){
            this.endDate = endDate;
            return this;
        }

        public Builder client(Client client){
            this.client = client;
            return this;
        }

        public Builder vehicle (Vehicle vehicle){
            this.vehicle = vehicle;
            return  this;
        }
        public Builder rentalModality(RentalModality rentalModality){
            this.rentalModality = rentalModality;
            return this;
        }

        public Builder discount (IDiscount discount){
            this.discount = discount;
            return this;
        }

        public Builder addAditionalService(AditionalService service) {
            if (service != null && service.isAvailability()) {
                this.additionalServices.add(service);
            }
            return this;
        }


public Booking build(){
    if (code == null || code.isBlank() || starDate == null || endDate == null ||
            client == null || vehicle == null || rentalModality == null || discount == null) {
        throw new IllegalArgumentException("All required fields must be completed.\n");
    }

    if (endDate.isBefore(starDate)) {
        throw new IllegalArgumentException("The end date cannot be earlier than the start date.");
    }

    return new Booking(this);
}






    }


// calcular dias
    //calcular subtotal
    //calcular total
    //servicios adicionales

    //Getters
    public String getCode() {
        return code;
    }

    public LocalDate getStarDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Client getClient() {
        return client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public RentalModality getRentalModality() {
        return rentalModality;
    }

    public IDiscount getDiscount() {
        return discount;
    }


    public List<AditionalService> getAdditionalServices() {
        return additionalServices;
    }
}
