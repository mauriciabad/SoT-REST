# SoT-REST

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

### Tickets

| Method | Endpoint | Function |
|--------|----------|----------|
| GET    | /tickets | Get all tickets |
| POST   | /tickets | Create a new ticket |
| GET    | /tickets/{ticket_id} | Get information about a specific ticket |
| PATCH  | /tickets/{ticket_id} | Update the settings for a ticket |
| DELETE | /tickets/{ticket_id} | Delete a ticket |
