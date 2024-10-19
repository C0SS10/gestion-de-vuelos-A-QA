package com.udea.gestiondevuelos.controller;

import com.udea.gestiondevuelos.domain.dto.AircraftDTO;
import com.udea.gestiondevuelos.service.IAircraftService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AircraftController {

    @Autowired
    private IAircraftService aircraftService;

    @QueryMapping(name = "getAircraftsByFilters")
    public List<AircraftDTO> getAircraftsByFilters(
            @Argument(name = "aircraftModel") String aircraftModel,
            @Argument(name = "maxSeats") Integer maxSeats,
            @Argument(name = "seatConfiguration") String seatConfiguration) {
        return aircraftService.filterAircrafts(aircraftModel, maxSeats, seatConfiguration);
    }

    @QueryMapping(name = "aircraftById")
    public AircraftDTO aircraftById(@Argument(name = "id") Long id) {
        return aircraftService.getAircraftById(id);
    }

    @MutationMapping(name = "createAircraft")
    public AircraftDTO createAircraft(@Argument AircraftDTO input) {
        return aircraftService.createAircraft(input);
    }

    @MutationMapping(name = "updateAircraft")
    public AircraftDTO updateAircraft(@Argument(name = "id") Long id, @Argument(name = "input") AircraftDTO input) {
        return aircraftService.updateAircraft(id, input);
    }

    @MutationMapping(name = "deleteAircraft")
    public Boolean deleteAircraft(@Argument(name = "id") Long id) {
        aircraftService.deleteAircraft(id);
        return true;
    }

}
