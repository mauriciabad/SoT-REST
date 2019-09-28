# SoT-REST

✈ First assignment for SoT 2019 in Fontys.

## Overview

Quickly review all available resources for Our Airline's API 1.0 with this reference overview.

> **Base URL**: http://localhost:8080/airline/v1/

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
