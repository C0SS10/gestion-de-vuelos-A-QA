package com.udea.gestiondevuelos.flight;

import com.udea.gestiondevuelos.domain.dto.AircraftDTO;
import com.udea.gestiondevuelos.domain.dto.FlightDTO;
import com.udea.gestiondevuelos.domain.model.Aircraft;
import com.udea.gestiondevuelos.domain.model.Flight;
import com.udea.gestiondevuelos.mappers.AircraftMappers;
import com.udea.gestiondevuelos.mappers.FlightMappers;
import com.udea.gestiondevuelos.repository.IFlightRepository;
import com.udea.gestiondevuelos.service.AircraftService;
import com.udea.gestiondevuelos.service.FlightService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    private IFlightRepository flightRepository;

    @Mock
    private FlightMappers flightMappers;

    @Mock
    private AircraftMappers aircraftMappers;

    @Mock
    private AircraftService aircraftService;

    @InjectMocks
    private FlightService flightService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateFlight() {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setAircraftId(1L);
        Flight flight = new Flight();
        flight.setId(1L);
        Aircraft aircraft = new Aircraft();
        aircraft.setId(1L);

        // Simula el mapeo del DTO a la entidad
        when(flightMappers.toFlightEntity(any(FlightDTO.class))).thenReturn(flight);

        // Simula la llamada para obtener el avión
        when(aircraftService.getAircraftById(1L)).thenReturn(new AircraftDTO());
        when(aircraftMappers.toAircraftEntity(any(AircraftDTO.class))).thenReturn(aircraft);

        // Simula el repositorio guardando el vuelo
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        // Simula el mapeo de vuelta a DTO
        when(flightMappers.toFlightDTO(any(Flight.class))).thenReturn(flightDTO);

        // Llama al metodo de servicio
        FlightDTO result = flightService.createFlight(flightDTO);

        // Verifica que no sea nulo y que se haya guardado el vuelo
        assertNotNull(result);
        verify(flightRepository, times(1)).save(any(Flight.class));
    }

    @Test
    void testFilterFlights() {
        Flight flight = new Flight();
        FlightDTO flightDTO = new FlightDTO();

        when(flightRepository.findAll(any(Specification.class))).thenReturn(Collections.singletonList(flight));
        when(flightMappers.toFlightDTO(any(Flight.class))).thenReturn(flightDTO);

        List<FlightDTO> result = flightService.filterFlights("CityA", "CityB", "2023-10-15", "2023-10-16");

        assertEquals(1, result.size());
        verify(flightRepository, times(1)).findAll(any(Specification.class));
    }

    @Test
    void testGetFlightById_NotFound() {
        when(flightRepository.findById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            flightService.getFlightById(1L);
        });

        assertEquals("El vuelo con el ID 1 no fué encontrado", exception.getMessage());
    }

    @Test
    void testUpdateFlight() {
        Flight flight = new Flight();
        FlightDTO dto = new FlightDTO();
        dto.setFlightNumber("FL123");

        when(flightRepository.findById(anyLong())).thenReturn(Optional.of(flight));
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);
        when(flightMappers.toFlightDTO(any(Flight.class))).thenReturn(dto);

        FlightDTO result = flightService.updateFlight(1L, dto);

        assertNotNull(result);
        assertEquals("FL123", result.getFlightNumber());
        verify(flightRepository, times(1)).findById(1L);
        verify(flightRepository, times(1)).save(any(Flight.class));
    }

    @Test
    void testDeleteFlight_NotFound() {
        when(flightRepository.existsById(anyLong())).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            flightService.deleteFlight(1L);
        });

        assertEquals("El vuelo con el ID 1 no fue encontrado", exception.getMessage());
    }

    @Test
    void testDeleteFlight_Success() {
        when(flightRepository.existsById(anyLong())).thenReturn(true);

        assertDoesNotThrow(() -> flightService.deleteFlight(1L));

        verify(flightRepository, times(1)).deleteById(1L);
    }
}
