package com.udea.gestiondevuelos.service;

import com.udea.gestiondevuelos.domain.dto.FlightDTO;

import java.util.List;

public interface IFlightService {
    FlightDTO createFlight(FlightDTO input);

    List<FlightDTO> filterFlights(String departureCity, String destinationCity, String departureDate,
            String arrivalDate);

    FlightDTO getFlightById(Long id);

    FlightDTO updateFlight(Long id, FlightDTO input);

    void deleteFlight(Long id);
}
