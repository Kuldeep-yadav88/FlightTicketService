import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface RealTimeFlightData {
  id: string;
  flightNumber: string;
  departureCity: string;
  arrivalCity: string;
  departureAirport: string;
  arrivalAirport: string;
  price: string;
  departureTime: string;
  arrivalTime: string;
  airline: string;
  status: string;
  availableSeats: number;
  totalSeats: number;
  aircraft: string;
  gate: string;
  delayMinutes: number;
  updatedAt: string;
}

export interface SearchFlightRequest {
  from: string;
  to: string;
  departOn: string;
  travelClass: string;
  numberOfPassengers: string;
}

export interface APIResponse {
  status: number;
  message: string;
  data: any;
}

@Injectable({
  providedIn: 'root'
})
export class FlightService {
  private apiUrl = 'http://localhost:8081/flights';

  constructor(private http: HttpClient) { }

  /**
   * Search for real-time flights from cloud data
   */
  searchRealTimeFlights(searchRequest: SearchFlightRequest): Observable<APIResponse> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    
    return this.http.post<APIResponse>(
      `${this.apiUrl}/search/realtime`,
      searchRequest,
      { headers }
    );
  }

  /**
   * Get live status for a specific flight
   */
  getLiveFlightStatus(flightNumber: string): Observable<APIResponse> {
    return this.http.get<APIResponse>(`${this.apiUrl}/status/${flightNumber}`);
  }
}
