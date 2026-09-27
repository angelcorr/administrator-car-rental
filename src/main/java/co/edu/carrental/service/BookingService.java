package co.edu.carrental.service;
import co.edu.carrental.model.Booking;
import co.edu.carrental.model.AdditionalService;
import co.edu.carrental.model.Administrator;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class BookingService {

    //Lista que guarda las reservas
    private final List<Booking> bookings = Administrator.getInstance().getBookingList();

  //Validacion de si la reserva existe
    private void verifyIfBookingExists(String code){
        for(Booking b:bookings){
            if(b.getCode().equalsIgnoreCase(code)){
                throw new IllegalArgumentException("A reservation with this code already exists");
            }
        }
    }

//agregar reserva
    public void addBooking(Booking booking){
        if(booking==null){
            throw new IllegalArgumentException("La reserva no puede ser nula.");
        }
        // Verifica si ya existe usando
        verifyIfBookingExists(booking.getCode());

        // Agrega la reserva a la LISTA
        this.bookings.add(booking);
    }
    public int calculateDays(Booking booking) {
        return (int) ChronoUnit.DAYS.between(booking.getStarDate(), booking.getEndDate());
    }
// costo del vehiculo
    public double calculateVehicleCost(Booking booking) {
        int days = calculateDays(booking);
        return booking.getVehicle().getDailyCharge() * days;
    }

    //costo de la modalidad

    public double calculateModalitySurcharge(Booking booking) {
        int days = calculateDays(booking);
        double baseCost = calculateVehicleCost(booking);
        return booking.getRentalModality().calculatecharge(days, baseCost);
    }
//calcular servicios adicioanles
    public double calculateServicesCost(Booking booking) {
        double total = 0;
        if (booking.getAdditionalServices() != null) {
            for (AdditionalService service : booking.getAdditionalServices()) {
                total += service.getPrice();
            }
        }
        return total;
    }

    public double calculateSubtotal(Booking booking) {
        return calculateVehicleCost(booking) + calculateModalitySurcharge(booking) + calculateServicesCost(booking);
    }

    public double calculateTotal(Booking booking) {
        int days = calculateDays(booking);

        // Validación de duración según la modalidad de alquiler
        booking.getRentalModality().validateDuration(days);

        double subtotal = calculateSubtotal(booking);
        double discountAmount = booking.getDiscount().calculate(subtotal, days);
        return subtotal - discountAmount;
    }
    //calcular ingresos del periodo
    public double calculateIncomeByPeriod(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("\n" +
                    "You must enter the start date and the end date.");
        }
        if (to.isBefore(from)) {
            throw new IllegalArgumentException("The end date cannot be before the start date.");
        }

        double income = 0;

        // Recorremos todas las reservas registradas
        for (Booking booking : bookings) {
            LocalDate start = booking.getStarDate();

            // Si la fecha de inicio esta entre from y to (incluyendo los extremos), se suma
            if (!start.isBefore(from) && !start.isAfter(to)) {
                income += calculateTotal(booking);
            }
        }

        return income;
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }
}
