package com.udea.gestiondevuelos.domain.model;

import com.udea.gestiondevuelos.domain.enums.AircraftModel;
import com.udea.gestiondevuelos.domain.enums.SeatConfiguration;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AircraftModel aircraftModel;

    private Integer maxSeats;

    private SeatConfiguration seatConfiguration;

    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL)
    private List<Flight> flights = new ArrayList<>();

    public Aircraft() {
    }

    public Aircraft(Long id, AircraftModel aircraftModel, Integer maxSeats, SeatConfiguration seatConfiguration) {
        this.id = id;
        this.aircraftModel = aircraftModel;
        this.maxSeats = maxSeats;
        this.seatConfiguration = seatConfiguration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AircraftModel getAircraftModel() {
        return aircraftModel;
    }

    public void setAircraftModel(AircraftModel aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    public Integer getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(Integer maxSeats) {
        this.maxSeats = maxSeats;
    }

    public SeatConfiguration getSeatConfiguration() {
        return seatConfiguration;
    }

    public void setSeatConfiguration(SeatConfiguration seatConfiguration) {
        this.seatConfiguration = seatConfiguration;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}