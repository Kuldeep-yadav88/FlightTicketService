package com.airlines.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RealTimeFlightDTO {
    private String id;
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private String departureAirport;
    private String arrivalAirport;
    private String price;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String airline;
    private String status;
    private Integer availableSeats;
    private Integer totalSeats;
    private String aircraft;
    private String gate;
    private Long delayMinutes;
    private LocalDateTime updatedAt;
}
