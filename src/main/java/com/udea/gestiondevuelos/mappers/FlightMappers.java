package com.udea.gestiondevuelos.mappers;

import com.udea.gestiondevuelos.domain.dto.FlightDTO;
import com.udea.gestiondevuelos.domain.model.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightMappers {
    public Flight toFlightEntity(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setId(flightDTO.getId());
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setFlightType(flightDTO.getFlightType());
        flight.setDepartureCity(flightDTO.getDepartureCity());
        flight.setDestinationCity(flightDTO.getDestinationCity());
        /* Aircraft has to be linked at service layer */
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setArrivalDate(flightDTO.getArrivalDate());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight.setPrice(flightDTO.getPrice());
        flight.setTaxPercentage(flightDTO.getTaxPercentage());
        flight.setSurcharge(flightDTO.getSurcharge());
        return flight;
    }

    public FlightDTO toFlightDTO(Flight flight) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(flight.getId());
        flightDTO.setFlightNumber(flight.getFlightNumber());
        flightDTO.setFlightType(flight.getFlightType());
        flightDTO.setDepartureCity(flight.getDepartureCity());
        flightDTO.setDestinationCity(flight.getDestinationCity());
        flightDTO.setAircraftId(flight.getAircraft().getId());
        flightDTO.setDepartureDate(flight.getDepartureDate());
        flightDTO.setArrivalDate(flight.getArrivalDate());
        flightDTO.setDepartureTime(flight.getDepartureTime());
        flightDTO.setArrivalTime(flight.getArrivalTime());
        flightDTO.setPrice(flight.getPrice());
        flightDTO.setTaxPercentage(flight.getTaxPercentage());
        flightDTO.setSurcharge(flight.getSurcharge());
        return flightDTO;
    }
}
