import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

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
  message: string;
  objectDetails: any;
}

@Injectable({
  providedIn: 'root'
})
export class FlightService {
  private apiUrl = `${environment.apiUrl}/flights`;

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
