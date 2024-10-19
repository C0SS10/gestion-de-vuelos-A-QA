package com.udea.gestiondevuelos.aircraft;

import com.udea.gestiondevuelos.domain.dto.AircraftDTO;
import com.udea.gestiondevuelos.domain.enums.AircraftModel;
import com.udea.gestiondevuelos.domain.enums.SeatConfiguration;
import com.udea.gestiondevuelos.domain.model.Aircraft;
import com.udea.gestiondevuelos.mappers.AircraftMappers;
import com.udea.gestiondevuelos.repository.IAircraftRepository;
import com.udea.gestiondevuelos.service.AircraftService;
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
public class AircraftServiceTest {
    @Mock
    private IAircraftRepository aircraftRepository;

    @Mock
    private AircraftMappers aircraftMappers;

    @InjectMocks
    private AircraftService aircraftService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAircraft() {
        System.out.println("Test createAircraft");
        AircraftDTO aircraftDTO = new AircraftDTO();
        Aircraft aircraft = new Aircraft();
        when(aircraftMappers.toAircraftEntity(any(AircraftDTO.class))).thenReturn(aircraft);
        when(aircraftRepository.save(any(Aircraft.class))).thenReturn(aircraft);
        when(aircraftMappers.toAircraftDTO(any(Aircraft.class))).thenReturn(aircraftDTO);

        AircraftDTO result = aircraftService.createAircraft(aircraftDTO);

        assertNotNull(result);
        verify(aircraftRepository, times(1)).save(any(Aircraft.class));
        System.out.println("Test createAircraft success");
    }

    @Test
    void filterAircraftTest() {
        System.out.println("Test filterAircraftTest");
        Aircraft aircraft = new Aircraft();
        AircraftDTO aircraftDTO = new AircraftDTO();

        when(aircraftRepository.findAll(any(Specification.class))).thenReturn(Collections.singletonList(aircraft));
        when(aircraftMappers.toAircraftDTO((any(Aircraft.class)))).thenReturn(aircraftDTO);

        List<AircraftDTO> result = aircraftService.filterAircrafts(AircraftModel.A320.toString(), 300,
                SeatConfiguration.TWO_FOUR_TWO.toString());

        assertEquals(1, result.size());

        verify(aircraftRepository, times(1)).findAll(any(Specification.class));
        System.out.println("Test filterAircraftTest success");
    }

    @Test
    void testGetAircraftById_NotFound() {
        System.out.println("Test getAircraftById_NotFound");
        when(aircraftRepository.findById(anyLong())).thenReturn(Optional.empty());
        // Se simula que el repositorio no encuentra ninguna aeronave con el ID dado.

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            aircraftService.getAircraftById(1L);
        });
        // Verifica que se lanza una excepción cuando no se encuentra la aeronave.

        assertEquals("El avion con el ID 1 no fue encontrado", exception.getMessage());
        // Verifica que el mensaje de la excepción es el correcto.
        System.out.println("Test getAircraftById_NotFound success");
    }

    @Test
    void testUpdateAircraft() {
        System.out.println("Test updateAircraft");
        Aircraft aircraft = new Aircraft(); // Se crea una entidad Aircraft simulada.
        AircraftDTO dto = new AircraftDTO(); // Se crea un DTO de prueba.

        when(aircraftRepository.findById(anyLong())).thenReturn(Optional.of(aircraft));
        // Se simula que el repositorio encuentra la aeronave por su ID.

        when(aircraftRepository.save(any(Aircraft.class))).thenReturn(aircraft);
        // Se simula que el repositorio guarda la entidad actualizada.

        when(aircraftMappers.toAircraftDTO(any(Aircraft.class))).thenReturn(dto);

        AircraftDTO result = aircraftService.updateAircraft(1L, dto);
        // Se ejecuta el metodo update

        assertNotNull(result);
        // Verifica que el resultado no sea nulo.

        verify(aircraftRepository, times(1)).findById(1L);

        verify(aircraftRepository, times(1)).save(any(Aircraft.class));
        System.out.println("Test updateAircraft success");
    }

    @Test
    void testDeleteAircraft_NotFound() {
        System.out.println("Test deleteAircraft_NotFound");
        when(aircraftRepository.existsById(anyLong())).thenReturn(false);
        // Se simula que el repositorio no encuentra ninguna aeronave con el ID dado.

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            aircraftService.deleteAircraft(1L);
        });
        // Verifica que se lanza una excepción cuando no se encuentra la aeronave para
        // eliminar.

        assertEquals("El avion con el ID 1 no fue encontrado", exception.getMessage());
        // Verifica que el mensaje de la excepción es el correcto.
        System.out.println("Test deleteAircraft_NotFound success");
    }

    @Test
    void testDeleteAircraft_Success() {
        System.out.println("Test deleteAircraft_Success");
        when(aircraftRepository.existsById(anyLong())).thenReturn(true);
        // Se simula que el repositorio encuentra la aeronave por su ID.

        doNothing().when(aircraftRepository).deleteById(anyLong());
        // Se simula que el metodo deleteById no hace nada (ya que no hay retorno
        // esperado).

        assertDoesNotThrow(() -> aircraftService.deleteAircraft(1L));
        // Verifica que no se lanza ninguna excepción durante la eliminación.

        verify(aircraftRepository, times(1)).deleteById(1L);
        // Verifica que el metodo deleteById del repositorio fue llamado una vez.
        System.out.println("Test deleteAircraft_Success success");
    }
}
