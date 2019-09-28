# SoT REST assignment | Airline

✈ First assignment for SoT 2019 in Fontys.

## Assignment statement

Make a REST service and client(s) for the required case: **searching and buying flight tickets (for travlling with an airplane)**. You may make it simple or advanced, for example: clients can create account, search for flight tickets and buy them, etc.

Detailed assessment criteria can be found here: [SOT Module Description and Assignments](SOT_Module_Description_and_Assignments.pdf) 

## API Reference

Quickly review all available resources for Airline's API 1.0 with this reference overview.

> **Base URL**: [http://localhost:8080/airline/v1/](http://localhost:8080/airline/v1/)

### API Root

| Method | Endpoint | Function |
|--------|----------|----------|
| GET    | /        | API Root |

### Flights

| Method | Endpoint | Function |
|--------|----------|----------|
| GET    | /flights | Get all flights |
| POST   | /flights | Create a new flight |
| GET    | /flights/{flight_number} | Get information about a specific flight |
| PATCH  | /flights/{flight_number} | Update a flight |
| DELETE | /flights/{flight_number} | Delete a flight |

### Flights Tickets

| Method | Endpoint | Function |
|--------|----------|----------|
| GET    | /flights/{flight_number}/tickets | Get all tickets |
| POST   | /flights/{flight_number}/tickets | Create a new ticket |
| GET    | /flights/{flight_number}/tickets/{ticket_id} | Get information about a specific ticket |
| PATCH  | /flights/{flight_number}/tickets/{ticket_id} | Update a ticket |
| DELETE | /flights/{flight_number}/tickets/{ticket_id} | Delete a ticket |
| POST   | /flights/{flight_number}/tickets/{ticket_id}/buy | Buy a specific ticket |

### Users

| Method | Endpoint | Function |
|--------|----------|----------|
| GET    | /users | Get all users |
| POST   | /users | Create a new user |
| GET    | /users/{user_id} | Get information about a specific user |
| PATCH  | /users/{user_id} | Update a user |
| DELETE | /users/{user_id} | Delete a user |
