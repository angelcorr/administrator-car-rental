package co.edu.carrental.service;

import co.edu.carrental.model.Administrator;
import co.edu.carrental.model.RentalModality;
import co.edu.carrental.model.RentalModalityEconomy;
import co.edu.carrental.model.RentalModalityExecutive;
import co.edu.carrental.model.RentalModalityPremium;
import enums.CoverageType;
import enums.RentState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentalModalityService {

    private final List<RentalModality> modalities = Administrator.getInstance().getRentalModalityList();

    public void addModality(RentalModality modality) {
        if (modality == null) {
            throw new IllegalArgumentException("The rental modality cannot be null.");
        }
        for (RentalModality m : modalities) {
            if (m.getCode().equalsIgnoreCase(modality.getCode())) {
                throw new IllegalStateException("A modality with this code already exists: " + modality.getCode());
            }
        }
        modalities.add(modality);
    }

    public List<RentalModality> getAllModalities() {
        return new ArrayList<>(modalities);
    }

    public List<RentalModality> getAvailableModalities() {
        return modalities.stream()
                .filter(RentalModality::availability)
                .collect(Collectors.toList());
    }

    public void loadDefaultModalities() {
        if (!modalities.isEmpty()) {
            return;
        }
        modalities.add(new RentalModalityEconomy("M01", "Economy",
                "Basic rental without surcharge", 1, 0, RentState.AVAILABLE));
        modalities.add(new RentalModalityExecutive("M02", "Executive",
                "5% surcharge, for business trips", 3, 0, RentState.AVAILABLE));
        modalities.add(new RentalModalityPremium("M03", "Premium",
                "Broad coverage and one extra driver", 5, 0, RentState.AVAILABLE,
                CoverageType.BROAD, 1, "Airport delivery"));
    }
}
