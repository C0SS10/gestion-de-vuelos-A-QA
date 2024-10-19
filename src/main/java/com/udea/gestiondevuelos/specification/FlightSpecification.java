package com.udea.gestiondevuelos.specification;

import com.udea.gestiondevuelos.domain.model.Flight;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class FlightSpecification {
    // Agregar un constructor privado para ocultar el constructor público
    private FlightSpecification() {
    }

    public static Specification<Flight> filterByDepartureCity(String departureCity) {
        return (root, query, cb) -> {
            if (departureCity == null || departureCity.isEmpty()) {
                return cb.conjunction();
            }
            return cb.equal(root.get("departureCity"), departureCity);
        };
    }

    public static Specification<Flight> filterByDestinationCity(String destinationCity) {
        return (root, query, cb) -> {
            if (destinationCity == null || destinationCity.isEmpty()) {
                return cb.conjunction();
            }
            return cb.equal(root.get("destinationCity"), destinationCity);
        };
    }

    public static Specification<Flight> filterByDepartureDate(LocalDate departureDate) {
        return (root, query, cb) -> {
            if (departureDate == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("departureDate"), departureDate);
        };
    }

    public static Specification<Flight> filterByArrivalDate(LocalDate arrivalDate) {
        return (root, query, cb) -> {
            if (arrivalDate == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("arrivalDate"), arrivalDate);
        };
    }
}
