package co.edu.carrental.controller;

import Discount.FullPrice;
import Discount.IDiscount;
import Discount.LongTermDiscount;
import Discount.PercentageDiscount;
import co.edu.carrental.model.AdditionalService;
import co.edu.carrental.model.Booking;
import co.edu.carrental.model.Client;
import co.edu.carrental.model.RentalModality;
import co.edu.carrental.model.Vehicle;
import co.edu.carrental.service.AdditionalServiceService;
import co.edu.carrental.service.BookingService;
import co.edu.carrental.service.ClientService;
import co.edu.carrental.service.RentalModalityService;
import co.edu.carrental.service.VehicleService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.util.StringConverter;
import co.edu.carrental.model.RentalModalityPremium;
import enums.CoverageType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingController {

    // Formulario
    @FXML private ComboBox<Client> cmbClient;
    @FXML private ComboBox<Vehicle> cmbVehicle;
    @FXML private ComboBox<RentalModality> cmbModality;
    @FXML private ComboBox<IDiscount> cmbDiscount;
    @FXML private DatePicker dpStart;
    @FXML private DatePicker dpEnd;
    @FXML private FlowPane boxServices;

    @FXML private Label lblMessage;
    @FXML private Label lblSummary;

    // Tabla
    @FXML private TableView<Booking> tblBookings;
    @FXML private TableColumn<Booking, String> colCode;
    @FXML private TableColumn<Booking, String> colClient;
    @FXML private TableColumn<Booking, String> colVehicle;
    @FXML private TableColumn<Booking, String> colModality;
    @FXML private TableColumn<Booking, LocalDate> colStart;
    @FXML private TableColumn<Booking, LocalDate> colEnd;
    @FXML private TableColumn<Booking, String> colTotal;
    @FXML private ComboBox<CoverageType> cmbCoverage;

    // Ingresos por periodo
    @FXML private DatePicker dpIncomeFrom;
    @FXML private DatePicker dpIncomeTo;
    @FXML private Label lblIncome;

    // Servicios (todos leen del Singleton Administrator, asi los datos no se pierden)
    private final BookingService bookingService = new BookingService();
    private final ClientService clientService = new ClientService();
    private final VehicleService vehicleService = new VehicleService();
    private final RentalModalityService modalityService = new RentalModalityService();
    private final AdditionalServiceService additionalServiceService = new AdditionalServiceService();

    private final ObservableList<Booking> bookingList = FXCollections.observableArrayList();
    private final List<CheckBox> serviceChecks = new ArrayList<>();

    @FXML
    public void initialize() {
        // Datos por defecto (solo se cargan la primera vez)
        additionalServiceService.loadDefaultServices();
        modalityService.loadDefaultModalities();

        configureComboConverters();
        configureTable();
        loadFormData();
        refreshTable();

        dpStart.setValue(LocalDate.now());
        dpEnd.setValue(LocalDate.now().plusDays(3));
        dpIncomeFrom.setValue(LocalDate.now().withDayOfMonth(1));
        dpIncomeTo.setValue(LocalDate.now().plusMonths(1));

        if (cmbClient.getItems().isEmpty() || cmbVehicle.getItems().isEmpty()) {
            showError("Register at least one client and one available vehicle first.");
        }
        cmbCoverage.setItems(FXCollections.observableArrayList(CoverageType.values()));
        cmbCoverage.getSelectionModel().select(CoverageType.BASIC);
        cmbCoverage.setDisable(true);
        cmbModality.valueProperty().addListener((obs, oldValue, newValue) ->
                cmbCoverage.setDisable(!(newValue instanceof RentalModalityPremium)));
    }

    // ---------- Configuracion ----------

    // Client y Vehicle no tienen toString(), asi que le decimos al ComboBox como mostrarlos
    private void configureComboConverters() {
        cmbClient.setConverter(new StringConverter<>() {
            @Override
            public String toString(Client c) {
                return c == null ? "" : c.getFullName() + " (" + c.getId() + ")";
            }

            @Override
            public Client fromString(String s) {
                return null;
            }
        });

        cmbVehicle.setConverter(new StringConverter<>() {
            @Override
            public String toString(Vehicle v) {
                return v == null ? "" : v.getPlate() + " - " + v.getBrand() + " " + v.getModel()
                        + " (" + money(v.getDailyCharge()) + "/day)";
            }

            @Override
            public Vehicle fromString(String s) {
                return null;
            }
        });
    }

    private void configureTable() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colStart.setCellValueFactory(new PropertyValueFactory<>("starDate"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        // Columnas que muestran datos de objetos relacionados o calculados
        colClient.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getClient().getFullName()));
        colVehicle.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getVehicle().getPlate()));
        colModality.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getRentalModality().toString()));
        colTotal.setCellValueFactory(data ->
                new SimpleStringProperty(money(bookingService.calculateTotal(data.getValue()))));

        tblBookings.setItems(bookingList);
    }

    private void loadFormData() {
        cmbClient.setItems(FXCollections.observableArrayList(clientService.getAllClients()));
        loadVehicles();
        cmbModality.setItems(FXCollections.observableArrayList(modalityService.getAvailableModalities()));

        // Strategy: cada descuento es una implementacion de IDiscount
        cmbDiscount.setItems(FXCollections.observableArrayList(
                new FullPrice(),
                new LongTermDiscount(),
                new PercentageDiscount(10)
        ));
        cmbDiscount.getSelectionModel().selectFirst();

        // Un CheckBox por cada servicio adicional disponible
        boxServices.getChildren().clear();
        serviceChecks.clear();
        for (AdditionalService service : additionalServiceService.getAvailableServices()) {
            CheckBox check = new CheckBox(service.getName() + " (" + money(service.getPrice()) + ")");
            check.setUserData(service);
            serviceChecks.add(check);
            boxServices.getChildren().add(check);
        }
    }


    // Solo se pueden reservar vehiculos disponibles
    private void loadVehicles() {
        cmbVehicle.setItems(FXCollections.observableArrayList(vehicleService.getAvailableVehicles()));
    }
    private RentalModality applyCoverage(RentalModality modality) {
        if (modality instanceof RentalModalityPremium premium && cmbCoverage.getValue() != null) {
            return new RentalModalityPremium(
                    premium.getCode(), premium.getName(), premium.getDescription(),
                    premium.getMinDuration(), premium.getDailyCharge(), premium.getState(),
                    cmbCoverage.getValue(), premium.getAdditionalConductors(), premium.getSpecialfeatures());
        }
        return modality;
    }

    private void refreshTable() {
        bookingList.setAll(bookingService.getAllBookings());
    }


    // ---------- Acciones de los botones ----------

    @FXML
    private void handleCalculateTotal() {
        Booking preview = buildBookingFromForm("PREVIEW");
        if (preview == null) {
            return;
        }
        lblSummary.setText(buildSummary(preview));
        showSuccess("Total calculated. Press 'Confirm Booking' to save it.");
    }

    @FXML
    private void handleConfirmBooking() {
        String code = nextCode();
        Booking booking = buildBookingFromForm(code);
        if (booking == null) {
            return;
        }

        try {
            bookingService.addBooking(booking);
        } catch (IllegalArgumentException | IllegalStateException e) {
            showError(e.getMessage());
            return;
        }

        // El vehiculo reservado deja de estar disponible
        vehicleService.updateAvailability(booking.getVehicle().getPlate(), false);

        lblSummary.setText(buildSummary(booking));
        refreshTable();
        loadVehicles();
        clearSelections();
        showSuccess("Booking " + code + " registered successfully!");
    }

    @FXML
    private void handleCalculateIncome() {
        try {
            double income = bookingService.calculateIncomeByPeriod(dpIncomeFrom.getValue(), dpIncomeTo.getValue());
            lblIncome.setText("Income from " + dpIncomeFrom.getValue() + " to " + dpIncomeTo.getValue()
                    + ": " + money(income));
        } catch (IllegalArgumentException e) {
            lblIncome.setText("Error: " + e.getMessage().trim());
        }
    }

    @FXML
    private void handleClearFields() {
        clearSelections();
        lblSummary.setText("");
        lblMessage.setText("");
    }

    // ---------- Metodos de apoyo ----------

    // Arma la reserva con los datos del formulario. Devuelve null si algo esta mal.
    private Booking buildBookingFromForm(String code) {
        Client client = cmbClient.getValue();
        Vehicle vehicle = cmbVehicle.getValue();
        RentalModality modality = cmbModality.getValue();
        IDiscount discount = cmbDiscount.getValue();
        LocalDate start = dpStart.getValue();
        LocalDate end = dpEnd.getValue();

        if (client == null || vehicle == null || modality == null || discount == null) {
            showError("Select a client, a vehicle, a modality and a discount.");
            return null;
        }
        if (start == null || end == null) {
            showError("Select the start date and the end date.");
            return null;
        }
        if (!end.isAfter(start)) {
            showError("The end date must be after the start date (minimum 1 day).");
            return null;
        }

        Booking.Builder builder = new Booking.Builder()
                .code(code)
                .starDate(start)
                .endDate(end)
                .client(client)
                .vehicle(vehicle)
                .rentalModality(applyCoverage(modality))
                .discount(discount);

        for (CheckBox check : serviceChecks) {
            if (check.isSelected()) {
                builder.addAditionalService((AdditionalService) check.getUserData());
            }
        }

        try {
            Booking booking = builder.build();
            // calculateTotal valida la duracion minima de la modalidad
            bookingService.calculateTotal(booking);
            return booking;
        } catch (IllegalArgumentException e) {
            showError(e.getMessage().trim());
            return null;
        }
    }

    // Desglose del valor usando los metodos de BookingService
    private String buildSummary(Booking b) {
        int days = bookingService.calculateDays(b);
        double vehicleCost = bookingService.calculateVehicleCost(b);
        double surcharge = bookingService.calculateModalitySurcharge(b);
        double services = bookingService.calculateServicesCost(b);
        double subtotal = bookingService.calculateSubtotal(b);
        double total = bookingService.calculateTotal(b);
        double discount = subtotal - total;

        return "Days: " + days
                + "\nVehicle (" + money(b.getVehicle().getDailyCharge()) + " x " + days + " days): " + money(vehicleCost)
                + "\nModality surcharge (" + b.getRentalModality().getName() + "): " + money(surcharge)
                + "\nAdditional services: " + money(services)
                + "\nSubtotal: " + money(subtotal)
                + "\nDiscount (" + b.getDiscount().getDescription() + "): -" + money(discount)
                + "\nTOTAL: " + money(total);
    }

    private String nextCode() {
        return String.format("RES-%03d", bookingService.getAllBookings().size() + 1);
    }

    private void clearSelections() {
        cmbClient.getSelectionModel().clearSelection();
        cmbVehicle.getSelectionModel().clearSelection();
        cmbModality.getSelectionModel().clearSelection();
        cmbDiscount.getSelectionModel().selectFirst();
        cmbCoverage.getSelectionModel().select(CoverageType.BASIC);
        dpStart.setValue(LocalDate.now());
        dpEnd.setValue(LocalDate.now().plusDays(3));
        for (CheckBox check : serviceChecks) {
            check.setSelected(false);
        }
    }

    private String money(double value) {
        return String.format("$%,.0f", value);
    }

    private void showError(String text) {
        lblMessage.setStyle("-fx-font-weight: bold; -fx-text-fill: #c62828;");
        lblMessage.setText("Error: " + text);
    }

    private void showSuccess(String text) {
        lblMessage.setStyle("-fx-font-weight: bold; -fx-text-fill: #2e7d32;");
        lblMessage.setText(text);
    }
}
