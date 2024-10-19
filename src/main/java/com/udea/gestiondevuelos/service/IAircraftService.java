package com.udea.gestiondevuelos.service;

import com.udea.gestiondevuelos.domain.dto.AircraftDTO;

import java.util.List;

public interface IAircraftService {
    AircraftDTO createAircraft(AircraftDTO aircraftDTO);

    List<AircraftDTO> filterAircrafts(String aircraftModel, Integer maxSeats, String seatConfiguration);

    AircraftDTO getAircraftById(Long id);

    AircraftDTO updateAircraft(Long id, AircraftDTO aircraftDTO);

    void deleteAircraft(Long id);
}
