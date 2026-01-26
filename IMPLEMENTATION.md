# Real-Time Flight Data & New UI Implementation

## Overview
This implementation adds real-time flight data retrieval from cloud sources and a modern, responsive UI for displaying flight information.

## Changes Made

### Backend (FlightTicketService-api)

#### 1. Enhanced Flight Entity
- **File**: `src/main/java/com/airlines/flight/entity/Flight.java`
- Added fields for real-time data:
  - `departureTime`, `arrivalTime` (LocalDateTime)
  - `airline`, `aircraft` (String)
  - `status` (ON_TIME, DELAYED, CANCELLED, BOARDING, IN_FLIGHT)
  - `availableSeats`, `totalSeats` (Integer)
  - `gate` (String)
  - `delayMinutes` (Long)
  - `updatedAt` (LocalDateTime)

#### 2. Real-Time Flight Data Service
- **File**: `src/main/java/com/airlines/flight/service/RealTimeFlightDataService.java`
- Simulates fetching real-time flight data from cloud sources
- Generates realistic flight information including:
  - Multiple airlines (Air India, Qatar Airways, Emirates, etc.)
  - Various flight statuses
  - Dynamic pricing
  - Seat availability
  - Gate assignments
  - Delay information

#### 3. New DTOs
- **File**: `src/main/java/com/airlines/flight/dto/RealTimeFlightDTO.java`
- Comprehensive DTO for real-time flight data transfer

#### 4. Updated Flight Service
- **File**: `src/main/java/com/airlines/flight/service/FlightService.java`
- Added methods:
  - `searchRealTimeFlights()` - Search flights with real-time data
  - `getLiveFlightStatus()` - Get live status for specific flight

#### 5. Updated Flight Controller
- **File**: `src/main/java/com/airlines/flight/controller/FlightController.java`
- New endpoints:
  - `POST /flights/search/realtime` - Search real-time flights
  - `GET /flights/status/{flightNumber}` - Get live flight status
- Added CORS support with `@CrossOrigin(origins = "*")`

#### 6. Configuration Updates
- **File**: `pom.xml`
- Updated Java version from 21 to 17 for compatibility

### Frontend (AirlinesReservation-ui)

#### 1. Flight Service
- **File**: `src/app/services/flight.service.ts`
- Angular service to communicate with backend APIs
- Interfaces for type safety:
  - `RealTimeFlightData`
  - `SearchFlightRequest`
  - `APIResponse`
- Methods for searching flights and getting live status

#### 2. New Flight Results Component
- **Files**: 
  - `src/app/component/flight-results/flight-results.component.ts`
  - `src/app/component/flight-results/flight-results.component.html`
  - `src/app/component/flight-results/flight-results.component.scss`

**Features**:
- Modern, card-based design
- Real-time flight status indicators with color coding:
  - Green: ON_TIME
  - Orange: DELAYED
  - Red: CANCELLED
  - Blue: BOARDING
  - Purple: IN_FLIGHT
- Auto-refresh mechanism (every 30 seconds)
- Live indicator for real-time updates
- Comprehensive flight details:
  - Departure/Arrival times and cities
  - Flight duration visualization
  - Airline and aircraft information
  - Seat availability
  - Gate information
  - Delay notifications
  - Dynamic pricing
- Responsive design for mobile and desktop
- Smooth animations and hover effects

#### 3. Enhanced Home Component
- **Files**:
  - `src/app/component/home/home.component.ts`
  - `src/app/component/home/home.component.html`
- Updated search functionality to navigate to flight results
- Separate form controls for origin and destination
- Date picker integration
- Passenger and cabin class selection

#### 4. App Module Updates
- **File**: `src/app/app.module.ts`
- Added `HttpClientModule` for API communication
- Added `MatProgressSpinnerModule` for loading indicators
- Registered `FlightResultsComponent`

#### 5. Routing Configuration
- **File**: `src/app/app-routing.module.ts`
- Added route for flight results: `/flight-results`

## Key Features

### Real-Time Data
- Simulated cloud-based flight data retrieval
- Live flight status updates
- Auto-refresh capability
- Timestamp tracking for data freshness

### Modern UI Design
- Material Design principles
- Gradient backgrounds
- Card-based layouts
- Status badges with icons
- Visual flight path representation
- Responsive grid system
- Smooth transitions and animations

### User Experience
- Intuitive search interface
- Clear flight information presentation
- Real-time update indicators
- Loading states
- Error handling
- Mobile-responsive design

## API Endpoints

### Search Real-Time Flights
```
POST /flights/search/realtime
Content-Type: application/json

Request Body:
{
  "from": "Delhi",
  "to": "Mumbai",
  "departOn": "2024-01-26",
  "travelClass": "Economy",
  "numberOfPassengers": "1"
}

Response:
{
  "message": "Real-time flights fetched successfully",
  "objectDetails": [/* array of flight objects */]
}
```

### Get Live Flight Status
```
GET /flights/status/{flightNumber}

Response:
{
  "message": "Live flight status fetched successfully",
  "objectDetails": {/* flight status object */}
}
```

## Running the Application

### Backend
```bash
cd FlightTicketService-api
mvn clean install -DskipTests
mvn spring-boot:run
```
Backend runs on: http://localhost:8081

### Frontend
```bash
cd AirlinesReservation-ui
npm install --legacy-peer-deps
npm start
```
Frontend runs on: http://localhost:4200

## Technology Stack

### Backend
- Java 17
- Spring Boot 3.2.2
- Spring Data MongoDB
- Spring Security
- Swagger/OpenAPI
- Lombok

### Frontend
- Angular 16
- Angular Material
- RxJS
- TypeScript
- SCSS

## Future Enhancements
1. Integration with real flight data APIs (e.g., Amadeus, FlightAware)
2. WebSocket support for true real-time updates
3. Flight booking functionality
4. User authentication and booking history
5. Price alerts and notifications
6. Advanced filtering and sorting
7. Flight comparison feature
8. Seat selection visualization
