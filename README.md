# SoT REST assignment | Airline | 2019 Q1 B1 - Fontys ICT

```
     _    _      _ _                 _    ____ ___ 
    / \  (_)_ __| (_)_ __   ___     / \  |  _ \_ _|
   / _ \ | | '__| | | '_ \ / _ \   / _ \ | |_) | | 
  / ___ \| | |  | | | | | |  __/  / ___ \|  __/| | 
 /_/   \_\_|_|  |_|_|_| |_|\___| /_/   \_\_|  |___|
                                                   
```

🛠 This project was develop with GitHub, Intellij Idea and Java 11.<br>
**Source code**: https://github.com/mauriciabad/SoT-REST

**Author**: [Maurici Abad Gutierrez](https://mauriciabad.com)

## Assignment statement

Make a REST service and client(s) for the required case: **searching and buying flight tickets (for travelling with an airplane)**. You may make it simple or advanced, for example: clients can create account, search for flight tickets and buy them, etc.

Detailed assessment criteria can be found here: [SOT Module Description and Assignments](https://github.com/mauriciabad/SoT-REST/blob/master/documentation/SOT_Module_Description_and_Assignments.pdf).

## API Reference

This are all the available resources for Airline's API 1.0.

> **Base URL**: [http://localhost:8080/airline/v1/](http://localhost:8080/airline/v1/)

### Endpoints

#### Flights

| Method | Endpoint | Description | Response |
|--------|----------|-------------|----------|
| GET    | /flights | Get all flights filtered by the Query parameters: <br>`flightId`, `origin`, `destination`, `departure`, `departureBefore`, `departureAfter`, `arrival`, `arrivalBefore`, `arrivalAfter`, `price`, `maxPrice` and/or `airline`. <br>This dates must have this format: `YYYYY-MM-dd` | Array\<Flight\> |
| POST   | /flights | Create a new flight <br>Also accepts `x-www-form-urlencoded` | Flight |
| GET    | /flights/{flightId} | Get information about a specific flight | Flight |
| PUT    | /flights/{flightId} | Update `origin`, `destination`, `departure`, `arrival` and/or `airline` of a flight. | Flight |
| DELETE | /flights/{flightId} | Delete a flight | |

#### Flights Tickets

| Method | Endpoint | Description | Response |
|--------|----------|-------------|----------|
| GET    | /flights/{flightId}/tickets | Get all tickets | Array\<Ticket\> |
| POST   | /flights/{flightId}/tickets/{ticketId}/buy?buyerId={userId} | Buy a specific ticket. <br>Needs `buyerId` Query parameter | Ticket |

#### Users

| Method | Endpoint | Description | Response |
|--------|----------|-------------|----------|
| GET    | /users | Get all users | Array\<User\> |
| POST   | /users | Create a new user | User |
| GET    | /users/{userId} | Get information about a specific user | User |
| PUT    | /users/{userId} | Update a user | User |
| DELETE | /users/{userId} | Delete a user | |

### Objects

#### Flight

| Name | Type |
|------|------|
| id | int |
| origin | String |
| destination | String |
| departure | String `YYYYY-MM-ddThh:mm` |
| arrival | String `YYYYY-MM-ddThh:mm` |
| airline | String |
| tickets | Array\<Ticket\> |
| *price* | int |
| *cheapestTicket* | Ticket |

Notice:
- The attribute `price` is the price of the cheapest ticket.
- The attributes `price` and `cheapestTicket` are redundant information.

#### Ticket

| Name | Type |
|------|------|
| id | int |
| price | int |
| seat | String |
| buyerId | int |
| forSale | bool |

#### User

| Name | Type |
|------|------|
| id | int |
| name | String |

### General comments

- All the data is encoded in `json` format.
- ID's are generated automatically, if you provide one in a POST or PUT request this will be ignored.
- When a list is requested the response contains the header `X-Total-Count` with the amount of items in the list.
- CORS enabled with header `Access-Control-Allow-Origin: *`.

## 1st Client - Java console

![Client demo](documentation/client-demo.gif)

Run the class [`Main`](https://github.com/mauriciabad/SoT-REST/blob/master/client/src/main/java/Main.java) inside `client` module.

## 2nd Client - Web

It's in side the `/web-client` folder. You can use it from the link below if you are running the backend in localhost:

[/web-client/index.html](https://mauriciabad.github.io/SoT-REST/web-client/)

## Error handling

Some errors can occur when:
- A required parameter is missing.
- The specified item does not exist.
- Date parameter has wrong format.
- Try to buy a ticket not for sale.

### Example custom error message

```json
{
  "error": true,
  "message": "Flight with flightId 999 doesn't exist",
  "status": 404,
  "statusName": "Not Found"
}
```

## HTTP messages

Real HTTP messages being transferred between client and server. 

We can see the input and output of some CRUD operations of `/flights` and different kinds of encodings.

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
