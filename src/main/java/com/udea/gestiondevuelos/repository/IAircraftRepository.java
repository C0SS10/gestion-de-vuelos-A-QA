package com.udea.gestiondevuelos.repository;

import com.udea.gestiondevuelos.domain.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IAircraftRepository extends JpaRepository<Aircraft, Long>, JpaSpecificationExecutor<Aircraft> {
}
