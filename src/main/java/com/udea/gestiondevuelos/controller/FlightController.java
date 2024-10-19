package com.udea.gestiondevuelos.controller;

import com.udea.gestiondevuelos.domain.dto.FlightDTO;
import com.udea.gestiondevuelos.service.IFlightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FlightController {

    @Autowired
    public IFlightService flightService;

    @QueryMapping(name = "getFlightsByFilters")
    public List<FlightDTO> getFlightsByFilters(
            @Argument(name = "departureCity") String departureCity,
            @Argument(name = "destinationCity") String destinationCity,
            @Argument(name = "departureDate") String departureDate,
            @Argument(name = "arrivalDate") String arrivalDate) {
        return flightService.filterFlights(departureCity, destinationCity, departureDate, arrivalDate);
    }

    @QueryMapping(name = "flightById")
    public FlightDTO flightById(@Argument(name = "id") Long id) {
        return flightService.getFlightById(id);
    }

    @MutationMapping(name = "createFlight")
    public FlightDTO createFlight(@Argument(name = "input") FlightDTO input) {
        return flightService.createFlight(input);
    }

    @MutationMapping(name = "updateFlight")
    public FlightDTO updateFlight(@Argument(name = "id") Long id, @Argument(name = "input") FlightDTO input) {
        return flightService.updateFlight(id, input);
    }

    @MutationMapping(name = "deleteFlight")
    public boolean deleteFlight(@Argument(name = "id") Long id) {
        flightService.deleteFlight(id);
        return true;
    }
}
