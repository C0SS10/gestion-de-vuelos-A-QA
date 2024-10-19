package com.udea.gestiondevuelos.service;

import com.udea.gestiondevuelos.domain.dto.FlightDTO;
import com.udea.gestiondevuelos.domain.model.Aircraft;
import com.udea.gestiondevuelos.domain.model.Flight;
import com.udea.gestiondevuelos.mappers.AircraftMappers;
import com.udea.gestiondevuelos.mappers.FlightMappers;
import com.udea.gestiondevuelos.repository.IFlightRepository;
import com.udea.gestiondevuelos.specification.FlightSpecification;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService implements IFlightService {

    @Autowired
    public IFlightRepository flightRepository;

    @Autowired
    public FlightMappers flightMappers;

    @Autowired
    public AircraftMappers aircraftMappers;

    @Autowired
    public AircraftService aircraftService;

    @Override
    public FlightDTO createFlight(FlightDTO input) {
        Flight flight = flightMappers.toFlightEntity(input);
        Aircraft aircraft = aircraftMappers.toAircraftEntity(aircraftService.getAircraftById(input.getAircraftId()));
        flight.setAircraft(aircraft);
        return flightMappers.toFlightDTO(flightRepository.save(flight));
    }

    @Override
    public List<FlightDTO> filterFlights(String departureCity, String destinationCity, String departureDate,
            String arrivalDate) {
        Specification<Flight> specification = Specification.where(null);

        if (departureCity != null) {
            specification = specification.and(FlightSpecification.filterByDepartureCity(departureCity));
        }

        if (destinationCity != null) {
            specification = specification.and(FlightSpecification.filterByDestinationCity(destinationCity));
        }

        if (departureDate != null) {
            specification = specification
                    .and(FlightSpecification.filterByDepartureDate(LocalDate.parse(departureDate)));
        }

        if (arrivalDate != null) {
            specification = specification.and(FlightSpecification.filterByArrivalDate(LocalDate.parse(arrivalDate)));
        }

        return flightRepository.findAll(specification).stream()
                .map(flight -> flightMappers.toFlightDTO(flight))
                .toList();
    }

    @Override
    public FlightDTO getFlightById(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("El vuelo con el ID %d no fué encontrado", id)));
        return flightMappers.toFlightDTO(flight);
    }

    @Override
    public FlightDTO updateFlight(Long id, FlightDTO input) {
        Flight flight = flightRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("El vuelo con el ID %d no fue encontrado", id)));
        if (input.getFlightNumber() != null) {
            flight.setFlightNumber(input.getFlightNumber());
        }
        if (input.getFlightType() != null) {
            flight.setFlightType(input.getFlightType());
        }
        if (input.getDepartureCity() != null) {
            flight.setDepartureCity(input.getDepartureCity());
        }
        if (input.getDestinationCity() != null) {
            flight.setDestinationCity(input.getDestinationCity());
        }
        if (input.getAircraftId() != null) {
            Aircraft aircraft = aircraftMappers
                    .toAircraftEntity(aircraftService.getAircraftById(input.getAircraftId()));
            if (!aircraft.equals(flight.getAircraft())) {
                flight.getAircraft().getFlights().remove(flight);
                flight.setAircraft(aircraft);
                aircraft.getFlights().add(flight);
            }
        }
        if (input.getDepartureDate() != null) {
            flight.setDepartureDate(input.getDepartureDate());
        }
        if (input.getArrivalDate() != null) {
            flight.setArrivalDate(input.getArrivalDate());
        }
        if (input.getDepartureTime() != null) {
            flight.setDepartureTime(input.getDepartureTime());
        }
        if (input.getArrivalTime() != null) {
            flight.setArrivalTime(input.getArrivalTime());
        }
        if (input.getPrice() != null) {
            flight.setPrice(input.getPrice());
        }
        if (input.getTaxPercentage() != null) {
            flight.setTaxPercentage(input.getTaxPercentage());
        }
        if (input.getSurcharge() != null) {
            flight.setSurcharge(input.getSurcharge());
        }
        return flightMappers.toFlightDTO(flightRepository.save(flight));
    }

    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("El vuelo con el ID %d no fue encontrado", id));
        }
        flightRepository.deleteById(id);
    }
}
