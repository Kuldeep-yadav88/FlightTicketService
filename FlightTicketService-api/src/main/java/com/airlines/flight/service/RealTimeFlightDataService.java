package com.airlines.flight.service;

import com.airlines.flight.dto.RealTimeFlightDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Service to fetch real-time flight data from cloud sources
 * This simulates fetching data from external flight data APIs
 */
@Slf4j
@Service
public class RealTimeFlightDataService {

    private final Random random = new Random();
    private final List<String> statuses = Arrays.asList("ON_TIME", "DELAYED", "BOARDING", "IN_FLIGHT");
    private final List<String> airlines = Arrays.asList("Air India", "Qatar Airways", "Emirates", "Lufthansa", "British Airways");
    private final List<String> aircrafts = Arrays.asList("Boeing 737", "Airbus A320", "Boeing 777", "Airbus A380");

    /**
     * Fetch real-time flight data based on search criteria
     * In production, this would call external flight data API
     */
    public List<RealTimeFlightDTO> fetchRealTimeFlights(String from, String to, String departDate) {
        log.info("Fetching real-time flight data from {} to {} on {}", from, to, departDate);
        
        List<RealTimeFlightDTO> flights = new ArrayList<>();
        
        // Simulate 5-10 flight results
        int numFlights = 5 + random.nextInt(6);
        
        for (int i = 0; i < numFlights; i++) {
            RealTimeFlightDTO flight = new RealTimeFlightDTO();
            flight.setId("FLT" + System.currentTimeMillis() + i);
            flight.setFlightNumber(generateFlightNumber());
            flight.setDepartureCity(from);
            flight.setArrivalCity(to);
            flight.setDepartureAirport(from + " International");
            flight.setArrivalAirport(to + " International");
            flight.setAirline(airlines.get(random.nextInt(airlines.size())));
            flight.setAircraft(aircrafts.get(random.nextInt(aircrafts.size())));
            
            // Generate realistic times
            LocalDateTime baseTime = LocalDateTime.now().plusHours(2 + i * 2);
            flight.setDepartureTime(baseTime);
            flight.setArrivalTime(baseTime.plusHours(2 + random.nextInt(4)));
            
            // Set status and delay
            String status = statuses.get(random.nextInt(statuses.size()));
            flight.setStatus(status);
            flight.setDelayMinutes(status.equals("DELAYED") ? (long) (15 + random.nextInt(120)) : 0L);
            
            // Set pricing
            flight.setPrice(String.valueOf(5000 + random.nextInt(15000)));
            
            // Set seats
            int totalSeats = 150 + random.nextInt(200);
            flight.setTotalSeats(totalSeats);
            flight.setAvailableSeats(random.nextInt(totalSeats));
            
            // Set gate
            flight.setGate(String.valueOf((char)('A' + random.nextInt(5))) + (random.nextInt(20) + 1));
            
            flight.setUpdatedAt(LocalDateTime.now());
            
            flights.add(flight);
        }
        
        return flights;
    }

    /**
     * Get live status update for a specific flight
     */
    public RealTimeFlightDTO getLiveFlightStatus(String flightNumber) {
        log.info("Fetching live status for flight: {}", flightNumber);
        
        RealTimeFlightDTO flight = new RealTimeFlightDTO();
        flight.setFlightNumber(flightNumber);
        flight.setStatus(statuses.get(random.nextInt(statuses.size())));
        flight.setDelayMinutes((long) random.nextInt(60));
        flight.setGate(String.valueOf((char)('A' + random.nextInt(5))) + (random.nextInt(20) + 1));
        flight.setUpdatedAt(LocalDateTime.now());
        
        return flight;
    }

    private String generateFlightNumber() {
        String[] prefixes = {"AI", "QR", "EK", "LH", "BA", "6E", "SG", "UK"};
        return prefixes[random.nextInt(prefixes.length)] + (100 + random.nextInt(900));
    }
}
