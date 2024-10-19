# Repositorio provisional para el módulo de gestión de vuelos - Backend

## AIRLINE-API

[Spring Boot](http://projects.spring.io/spring-boot/) application

## GraphQL API Architecture

| Name                   | GraphQL Query/Mutation        | Description                                      | Required Fields                                      | Implemented |
| ---------------------- | ----------------------------- | ------------------------------------------------ | ---------------------------------------------------- | ----------- |
| Get Flights            | `query { allFlights }`           | Get information of all flights             | N/A                                                  | ✅          |
| Get Flight by ID       | `query { flightById (id) }`        | Get information of a specific flight by ID | `id` (ID of the flight)                              | ✅          |
| Create Flight          | `mutation { createFlight (input)}`   | Create a flight                 | `input` (FlightDTO with all necessary flight details) | ✅          |
| Update Flight by ID    | `mutation { updateFlight(id, input) }` | Update the flight info by its ID      | `id` (ID of the flight), `input` (updated flight data) | ✅          |
| Delete Flight by ID    | `mutation { deleteFlight(id) }` | Delete a flight by its ID                        | `id` (ID of the flight)                              | ✅          |
| Get Aircrafts          | `query { allAircrafts }`         | Get information of all aircrafts           | N/A                                                  | ✅          | 
| Get Aircraft by ID     | `query { aircraftById (id) }`      | Get information of a specific aircraft by ID | `id` (ID of the aircraft)                          | ✅          |
| Create Aircraft        | `mutation { createAircraft (input)}` | Create an aircraft                              | `input` (AircraftDTO with necessary aircraft details) | ✅          | 
| Update Aircraft by ID  | `mutation { updateAircraft(id, input) }` | Update an aircraft by its ID                   | `id` (ID of the aircraft), `input` (updated aircraft data) | ✅          |
| Delete Aircraft by ID  | `mutation { deleteAircraft(id) }` | Delete an aircraft by its ID                   | `id` (ID of the aircraft)                            | ✅          | 

### Campos requeridos:
- **FlightDTO** para `createFlight` y `updateFlight`: Contiene todos los detalles necesarios para crear o actualizar un vuelo.
- **AircraftDTO** para `createAircraft` y `updateAircraft`: Contiene los detalles para crear o actualizar una aeronave
