package com.airlines.flight.service;

import com.airlines.common.dto.APIResponseDTO;
import com.airlines.flight.dto.RealTimeFlightDTO;
import com.airlines.flight.dto.SearchFlightDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is service class for flight management
 * it returns response to the controller.
 */
@Slf4j
@Service
public class FlightService {

    private final RealTimeFlightDataService realTimeFlightDataService;

    public FlightService(RealTimeFlightDataService realTimeFlightDataService) {
        this.realTimeFlightDataService = realTimeFlightDataService;
    }

    /**
     * Add latest flight in db.
     * @return
     */
    public APIResponseDTO addFlight() {
        return null;

    }

    /**
     * This service class update the flights
     * @return
     */
    public APIResponseDTO updateFlight() {
        return null;
    }

    /**
     * This service cancel the flight or delete
     * this is soft delete
     * @return
     */
    public APIResponseDTO cancelFlight() {
        return null;
    }

    /**
     * This service give search flights
     * @return list of flights
     */

    public APIResponseDTO getFlightId() {
        return null;
    }

    /**
     * Search real-time flights from cloud data
     * @param searchFlightDTO search criteria
     * @return API response with real-time flight data
     */
    public APIResponseDTO searchRealTimeFlights(SearchFlightDTO searchFlightDTO) {
        log.info("Searching real-time flights from {} to {}", searchFlightDTO.getFrom(), searchFlightDTO.getTo());
        
        try {
            List<RealTimeFlightDTO> flights = realTimeFlightDataService.fetchRealTimeFlights(
                searchFlightDTO.getFrom(),
                searchFlightDTO.getTo(),
                searchFlightDTO.getDepartOn()
            );
            
            APIResponseDTO response = new APIResponseDTO();
            response.setMessage("Real-time flights fetched successfully");
            response.setObjectDetails(flights);
            
            return response;
        } catch (Exception e) {
            log.error("Error fetching real-time flights", e);
            APIResponseDTO response = new APIResponseDTO();
            response.setMessage("Error fetching real-time flight data: " + e.getMessage());
            return response;
        }
    }

    /**
     * Get live status for a specific flight
     * @param flightNumber flight number
     * @return API response with live flight status
     */
    public APIResponseDTO getLiveFlightStatus(String flightNumber) {
        log.info("Getting live status for flight: {}", flightNumber);
        
        try {
            RealTimeFlightDTO flightStatus = realTimeFlightDataService.getLiveFlightStatus(flightNumber);
            
            APIResponseDTO response = new APIResponseDTO();
            response.setMessage("Live flight status fetched successfully");
            response.setObjectDetails(flightStatus);
            
            return response;
        } catch (Exception e) {
            log.error("Error fetching live flight status", e);
            APIResponseDTO response = new APIResponseDTO();
            response.setMessage("Error fetching live flight status: " + e.getMessage());
            return response;
        }
    }

}

