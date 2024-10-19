package com.udea.gestiondevuelos.specification;

import com.udea.gestiondevuelos.domain.enums.AircraftModel;
import com.udea.gestiondevuelos.domain.enums.SeatConfiguration;
import com.udea.gestiondevuelos.domain.model.Aircraft;
import org.springframework.data.jpa.domain.Specification;

public class AircraftSpecification {
    // Agregar un constructor privado para ocultar el constructor público
    private AircraftSpecification() {
    }

    public static Specification<Aircraft> filterByModel(AircraftModel model) {
        return (root, query, cb) -> {
            if (model == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("aircraftModel"), model);
        };
    }

    public static Specification<Aircraft> filterByMaxSeats(Integer maxSeats) {
        return (root, query, cb) -> {
            if (maxSeats == null) {
                return cb.conjunction();
            }
            return cb.greaterThanOrEqualTo(root.get("maxSeats"), maxSeats);
        };
    }

    public static Specification<Aircraft> filterBySeatConfiguration(SeatConfiguration seatConfiguration) {
        return (root, query, cb) -> {
            if (seatConfiguration == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("seatConfiguration"), seatConfiguration);
        };
    }
}
