package com.udea.gestiondevuelos.domain.model;

import com.udea.gestiondevuelos.domain.enums.FlightType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;

    @Enumerated(EnumType.STRING)
    private FlightType flightType;

    private String departureCity;

    private String destinationCity;

    @ManyToOne
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    private LocalDate departureDate;

    private LocalDate arrivalDate;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private Float price;

    private Float taxPercentage;

    private Float surcharge;

    public Flight() {
    }

    public Flight(Long id, String flightNumber, FlightType flightType, String departureCity, String destinationCity,
            Aircraft aircraft, LocalDate departureDate, LocalDate arrivalDate, LocalTime departureTime,
            LocalTime arrivalTime, Float price, Float taxPercentage, Float surcharge) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.flightType = flightType;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.aircraft = aircraft;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.taxPercentage = taxPercentage;
        this.surcharge = surcharge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public FlightType getFlightType() {
        return flightType;
    }

    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(Float taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public Float getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(Float surcharge) {
        this.surcharge = surcharge;
    }
}
