# SoT REST assignment | Airline

✈ First assignment for SoT 2019 in Fontys.

## Assignment statement

Make a REST service and client(s) for the required case: **searching and buying flight tickets (for travlling with an airplane)**. You may make it simple or advanced, for example: clients can create account, search for flight tickets and buy them, etc.

Detailed assessment criteria can be found here: [SOT Module Description and Assignments](SOT_Module_Description_and_Assignments.pdf) 

## API Reference

Quickly review all available resources for Airline's API 1.0 with this reference overview.

> **Base URL**: [http://localhost:8080/airline/v1/](http://localhost:8080/airline/v1/)

### Endpoints

#### Flights

| Method | Endpoint | Function |
|--------|----------|----------|
| GET    | /flights | Get all flights filtered by the Query parameters: <br>`flight_number`, `origin`, `destination`, `departure`, `departure_before`, `departure_after`, `arrival`, `arrival_before`, `arrival_after`, `price`, `max_price` and/or `airline` |
| POST   | /flights | Create a new flight <br>Also accepts `x-www-form-urlencoded` |
| GET    | /flights/{flight_number} | Get information about a specific flight |
| PUT    | /flights/{flight_number} | Update a flight |
| DELETE | /flights/{flight_number} | Delete a flight |

#### Flights Tickets

| Method | Endpoint | Function |
|--------|----------|----------|
| GET    | /flights/{flight_number}/tickets | Get all tickets |
| POST   | /flights/{flight_number}/tickets | Create a new ticket |
| GET    | /flights/{flight_number}/tickets/{ticket_id} | Get information about a specific ticket |
| PUT    | /flights/{flight_number}/tickets/{ticket_id} | Update a ticket |
| DELETE | /flights/{flight_number}/tickets/{ticket_id} | Delete a ticket |
| POST   | /flights/{flight_number}/tickets/{ticket_id}/buy | Buy a specific ticket |

#### Users

| Method | Endpoint | Function |
|--------|----------|----------|
| GET    | /users | Get all users |
| POST   | /users | Create a new user |
| GET    | /users/{user_id} | Get information about a specific user |
| PUT    | /users/{user_id} | Update a user |
| DELETE | /users/{user_id} | Delete a user |

### Objects

#### Flight

| Name | Type |
|------|------|
| flight_number | int |
| origin | String |
| destination | String |
| departure | String |
| arrival | String |
| airline | String |
| tickets | Array\<Ticket\> |

#### Ticket

| Name | Type |
|------|------|
| ref | int |
| flight_number | int |
| price | int |
| seat | String |
| buyer_id | int |
| for_sale | bool |

#### User

| Name | Type |
|------|------|
| user_id | int |

## HTTP messages

Between client and server. 

| Request | Response |
|:-------:|:--------:|
| ![Burp screenshot 05](documentation/burp000005.png) `GET` `/flights/1` | ![Burp screenshot 06](documentation/burp000006.png) `GET` `/flights/1` |
| ![Burp screenshot 07](documentation/burp000007.png) `GET` `/flights` | ![Burp screenshot 08](documentation/burp000008.png) `GET` `/flights` |
| ![Burp screenshot 09](documentation/burp000009.png) `GET` `/flights?origin=BCN` `query` | ![Burp screenshot 10](documentation/burp000010.png) `GET` `/flights?origin=BCN` `query` |
| ![Burp screenshot 11](documentation/burp000011.png) `POST` `/flights` `json` | ![Burp screenshot 12](documentation/burp000012.png) `POST` `/flights` `json` |
| ![Burp screenshot 13](documentation/burp000013.png) `POST` `/flights` `form` | ![Burp screenshot 14](documentation/burp000014.png) `POST` `/flights` `form` |
| ![Burp screenshot 15](documentation/burp000015.png) `POST` `/flights` `json` | ![Burp screenshot 16](documentation/burp000016.png) `POST` `/flights` `json` |
| ![Burp screenshot 17](documentation/burp000017.png) `PUT` `/flights/1` `json` | ![Burp screenshot 18](documentation/burp000018.png) `PUT` `/flights/1` `json` |
| ![Burp screenshot 19](documentation/burp000019.png) `DELETE` `/flights/1` | ![Burp screenshot 20](documentation/burp000020.png) `DELETE` `/flights/1` |
| ![Burp screenshot 21](documentation/burp000021.png) `GET` `/flights` | ![Burp screenshot 22](documentation/burp000022.png) `GET` `/flights` |
