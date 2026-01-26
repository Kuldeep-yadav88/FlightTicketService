import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FlightService, RealTimeFlightData } from '../../services/flight.service';
import { interval } from 'rxjs';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-flight-results',
  templateUrl: './flight-results.component.html',
  styleUrls: ['./flight-results.component.scss']
})
export class FlightResultsComponent implements OnInit {
  flights: RealTimeFlightData[] = [];
  loading: boolean = false;
  error: string = '';
  searchParams: any = {};
  autoRefresh: boolean = true;

  constructor(
    private flightService: FlightService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Get search params from route
    this.route.queryParams.subscribe(params => {
      this.searchParams = params;
      this.searchFlights();
    });

    // Auto-refresh every 30 seconds if enabled
    if (this.autoRefresh) {
      interval(30000).pipe(
        switchMap(() => this.flightService.searchRealTimeFlights({
          from: this.searchParams.from,
          to: this.searchParams.to,
          departOn: this.searchParams.departOn,
          travelClass: this.searchParams.travelClass || 'Economy',
          numberOfPassengers: this.searchParams.passengers || '1'
        }))
      ).subscribe({
        next: (response) => {
          if (response.objectDetails) {
            this.flights = response.objectDetails;
          }
        },
        error: (err) => {
          console.error('Auto-refresh error:', err);
        }
      });
    }
  }

  searchFlights(): void {
    this.loading = true;
    this.error = '';

    const searchRequest = {
      from: this.searchParams.from || 'Delhi',
      to: this.searchParams.to || 'Mumbai',
      departOn: this.searchParams.departOn || new Date().toISOString(),
      travelClass: this.searchParams.travelClass || 'Economy',
      numberOfPassengers: this.searchParams.passengers || '1'
    };

    this.flightService.searchRealTimeFlights(searchRequest).subscribe({
      next: (response) => {
        this.loading = false;
        if (response.objectDetails) {
          this.flights = response.objectDetails;
        } else {
          this.error = response.message || 'Failed to fetch flights';
        }
      },
      error: (err) => {
        this.loading = false;
        this.error = 'Error connecting to flight service. Please try again later.';
        console.error('Flight search error:', err);
      }
    });
  }

  getStatusColor(status: string): string {
    switch (status) {
      case 'ON_TIME': return '#4caf50';
      case 'DELAYED': return '#ff9800';
      case 'CANCELLED': return '#f44336';
      case 'BOARDING': return '#2196f3';
      case 'IN_FLIGHT': return '#9c27b0';
      default: return '#757575';
    }
  }

  getStatusIcon(status: string): string {
    switch (status) {
      case 'ON_TIME': return 'check_circle';
      case 'DELAYED': return 'schedule';
      case 'CANCELLED': return 'cancel';
      case 'BOARDING': return 'flight_takeoff';
      case 'IN_FLIGHT': return 'flight';
      default: return 'info';
    }
  }

  formatTime(dateTime: string): string {
    const date = new Date(dateTime);
    return date.toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' });
  }

  formatDate(dateTime: string): string {
    const date = new Date(dateTime);
    return date.toLocaleDateString('en-US', { month: 'short', day: 'numeric' });
  }

  calculateDuration(departure: string, arrival: string): string {
    const dep = new Date(departure);
    const arr = new Date(arrival);
    const diff = arr.getTime() - dep.getTime();
    const hours = Math.floor(diff / (1000 * 60 * 60));
    const minutes = Math.floor((diff % (1000 * 60 * 60)) / (1000 * 60));
    return `${hours}h ${minutes}m`;
  }

  toggleAutoRefresh(): void {
    this.autoRefresh = !this.autoRefresh;
  }

  refreshFlights(): void {
    this.searchFlights();
  }
}
