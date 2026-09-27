package co.edu.carrental.service;

import co.edu.carrental.model.AdditionalService;
import co.edu.carrental.model.Administrator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdditionalServiceService {

    // Shared list stored in the Administrator singleton
    private final List<AdditionalService> services =
            Administrator.getInstance().getAdditionalServiceList();

    // Registrar un nuevo servicio adicional
    public void addService(AdditionalService service) {
        if (service == null) {
            throw new IllegalArgumentException("The additional service cannot be null.");
        }
        verifyIfServiceExists(service.getCode());
        services.add(service);
    }

    // Verificar si no existe otro codigo de servicio
    public void verifyIfServiceExists(String code) {
        if (findByCode(code).isPresent()) {
            throw new IllegalStateException("A service with this code already exists: " + code);
        }
    }

    //buscar un servicio por el codigo
    public Optional<AdditionalService> findByCode(String code) {
        if (code == null) {
            return Optional.empty();
        }
        return services.stream()
                .filter(service -> service.getCode().equalsIgnoreCase(code))
                .findFirst();
    }

    // servicios registrados
    public List<AdditionalService> getAllServices() {
        return new ArrayList<>(services);
    }

    public List<AdditionalService> getAvailableServices() {
        return services.stream()
                .filter(AdditionalService::isAvailability)
                .collect(Collectors.toList());
    }

    // Habilita o desabilita
    public boolean updateAvailability(String code, boolean available) {
        Optional<AdditionalService> serviceOpt = findByCode(code);
        if (serviceOpt.isPresent()) {
            serviceOpt.get().setAvailability(available);
            return true;
        }
        return false;
    }

    public void loadDefaultServices() {
        if (!services.isEmpty()) {
            return;
        }
        services.add(new AdditionalService("S01", "GPS", "Satellite navigation", 15000, true));
        services.add(new AdditionalService("S02", "Baby seat", "Certified child seat", 12000, true));
        services.add(new AdditionalService("S03", "Additional driver", "Allows one extra driver", 20000, true));
        services.add(new AdditionalService("S04", "Complementary insurance", "Extra coverage", 30000, true));
    }
}