package com.airlines.flight.controller;


import com.airlines.flight.dto.AddFlightDTO;
import com.airlines.flight.dto.SearchFlightDTO;
import com.airlines.flight.repository.FlightRepository;
import com.airlines.flight.service.FlightService;
import com.airlines.common.dto.APIResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/flights")
@Tag(name = "flight", description = "flight controller APIs. it handles it upcoming request")
@CrossOrigin(origins = "*")
@Slf4j
public class FlightController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private FlightRepository repository;

    /**
     * This API will help me to schedule a flight for booking. with
     * there information
     *
     * @param addFlightDTO
     * @return
     */
    @PostMapping("/add")
    public APIResponseDTO addFlight(@RequestBody AddFlightDTO addFlightDTO) {
        return null;
    }

    /**
     * This API will help me to get information by passing the id.
     *
     * @param searchFlightDTO
     * @return
     */
    @GetMapping("/get/flights/details")
    public APIResponseDTO getFlightInfo(@RequestBody SearchFlightDTO searchFlightDTO) {
        return null;
    }

    /**
     * Search real-time flights from cloud data sources
     *
     * @param searchFlightDTO search criteria
     * @return real-time flight data
     */
    @PostMapping("/search/realtime")
    @Operation(summary = "Search real-time flights", description = "Fetches real-time flight data from cloud sources")
    public APIResponseDTO searchRealTimeFlights(@Valid @RequestBody SearchFlightDTO searchFlightDTO) {
        log.info("Received real-time flight search request: {} to {}", 
            searchFlightDTO.getFrom(), searchFlightDTO.getTo());
        return flightService.searchRealTimeFlights(searchFlightDTO);
    }

    /**
     * Get live status for a specific flight
     *
     * @param flightNumber flight number
     * @return live flight status
     */
    @GetMapping("/status/{flightNumber}")
    @Operation(summary = "Get live flight status", description = "Fetches live status for a specific flight")
    public APIResponseDTO getLiveFlightStatus(@PathVariable String flightNumber) {
        log.info("Received live status request for flight: {}", flightNumber);
        return flightService.getLiveFlightStatus(flightNumber);
    }

    /**
     * This API will help me to update the flight information.
     *
     * @return
     */
    @PutMapping("/updateInfo")
    public APIResponseDTO updateFlight() {
        return null;

    }

    /**
     * This API will delete the schedule flight.
     * this is soft-delete.
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Schedule flight ", description = " Request contains flight ID ")
    public ResponseEntity<Void> deleteFlight(@Valid @PathVariable String id) {
        log.info("Request ");
        return null;
    }

}
