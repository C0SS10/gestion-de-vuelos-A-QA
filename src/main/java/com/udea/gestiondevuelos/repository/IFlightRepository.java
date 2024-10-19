package com.udea.gestiondevuelos.repository;

import com.udea.gestiondevuelos.domain.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IFlightRepository extends JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {
}
