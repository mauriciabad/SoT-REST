public class Interface {

    private Console console = new Console();
    private Tester tester = new Tester("http://localhost:8080/airline/v1");

    public Interface() {}

    public void run(){
        console.write(  "     _    _      _ _                 _    ____ ___ \n" +
                "    / \\  (_)_ __| (_)_ __   ___     / \\  |  _ \\_ _|\n" +
                "   / _ \\ | | '__| | | '_ \\ / _ \\   / _ \\ | |_) | | \n" +
                "  / ___ \\| | |  | | | | | |  __/  / ___ \\|  __/| | \n" +
                " /_/   \\_\\_|_|  |_|_|_| |_|\\___| /_/   \\_\\_|  |___|\n" +
                "                                                   ");

        console.write("Welcome to Airline API");

        goToMenuMain();

        console.write("\nThank you for using our API :D");
    }

    private void goToMenuMain() {
        console.write("\nWhat Entity you want to work with?");
        console.write("  1. Flights");
        console.write("  2. Users");
        console.write("  exit. Close");

        switch (console.read().toLowerCase()){
            case "exit": return;
            case "1": goToMenuFlight(); break;
            case "2": goToMenuUser(); break;
            default: console.write("Invalid option, try again\n"); goToMenuMain(); break;
        }
    }

    private void goToMenuFlight() {
        console.write("\nWhat do you want to do?");
        console.write("  A. Read all flights");
        console.write("  C. Create a flight");
        console.write("  R. Read a flight");
        console.write("  U. Update a flight");
        console.write("  D. Delete a flight");
        console.write("  back. Go to flight's menu");
        console.write("  exit. Close");

        switch (console.read().toLowerCase()){
            case "exit": return;
            case "back": goToMenuMain(); break;
            case "a": goToMenuFlightAll(); break;
            case "c": goToMenuFlightCreate(); break;
            case "r": goToMenuFlightRead(); break;
            case "u": goToMenuFlightUpdate(); break;
            case "d": goToMenuFlightDelete(); break;
            default: console.write("Invalid option, try again\n"); goToMenuFlight(); break;
        }
    }

    private void goToMenuFlightAll() {
        // Run request & show result
        tester.test("GET", "/flights");

        goToMenuFlight();
    }

    private void goToMenuFlightCreate() {
        // Display instructions
        console.write("\nEnter the following values:");
        console.write("airline:"); String airline = console.read();
        console.write("arrival:"); String arrival = console.read();
        console.write("departure:"); String departure = console.read();
        console.write("destination:"); String destination = console.read();
        console.write("origin:"); String origin = console.read();
        console.write("amount of tickets:"); Integer amountOfTickets = (Integer) console.read(Integer.class);
        console.write("price per ticket: (EUR)"); Integer pricePerTickets = (Integer) console.read(Integer.class);

        console.write("\nYou entered this values:");
        console.write("airline: "+airline);
        console.write("arrival: "+arrival);
        console.write("departure: "+departure);
        console.write("destination: "+destination);
        console.write("origin: "+origin);
        console.write("tickets: "+amountOfTickets+" tickets of "+pricePerTickets+"€ each");
        // Ask for confirmation
        boolean answerConfirmation = console.ask("\nDo you want to create a flight with this values?");
        if(answerConfirmation) {

            String ticketsJson = buildTicketsJson(amountOfTickets, pricePerTickets);

            String body = "{"+
                    "\"airline\":\""+airline+"\","+
                    "\"arrival\":\""+arrival+"\","+
                    "\"departure\":\""+departure+"\","+
                    "\"destination\":\""+destination+"\","+
                    "\"origin\":\""+origin+"\","+
                    "\"tickets\":"+ticketsJson+""+
                    "}";

            // Run request & show result
            tester.test("POST", "/flights", body);
        }

        // Ask next action
        boolean answerRepeat = console.ask("\nDo you want to create another flight?");
        if(answerRepeat) goToMenuFlightCreate();
        else goToMenuFlight();
    }

    private String buildTicketsJson(Integer amountOfTickets, Integer pricePerTickets) {
        String ticketsJson = "[";
        for (int i = 0; i < amountOfTickets; i++) {
            ticketsJson += "{"+
                    "\"ref\":\"TK000"+i+"\","+
                    "\"price\":\""+pricePerTickets+"\","+
                    "\"seat\":\""+(char)('A'+(i%6))+(i/6 + 1)+"\""+
                    "}";
            if (i < amountOfTickets -1) ticketsJson += ",";
        }
        ticketsJson += "]";
        return ticketsJson;
    }

    private void goToMenuFlightRead() {
        // Display instructions
        console.write("\nEnter a flightNumber:");

        // Get input
        Integer flightNumber = (Integer) console.read(Integer.class);

        // Run request & show result
        tester.test("GET", "/flights/"+flightNumber);

        // Ask next action
        boolean answerRepeat = console.ask("\nDo you want to read another flight?");
        if(answerRepeat) goToMenuFlightRead();
        else goToMenuFlight();
    }

    private void goToMenuFlightUpdate() {
        // Display instructions
        console.write("\nEnter a flightNumber:");

        // Get input
        Integer flightNumber = (Integer) console.read(Integer.class);

        // Check that flight exists
        int statusGet = tester.test("GET", "/flights/"+flightNumber);
        if (statusGet >= 200 && statusGet < 300){

            // Ask for confirmation
            boolean answerConfirmation = console.ask("\nDo you want to update this flight?");
            if(answerConfirmation) {

                console.write("\nWhat attribute do you want to update?");
                console.write("  airline. Airline name");
                console.write("  arrival. Arrival date (YYYY-MM-ddThh:mm)");
                console.write("  departure. Departure date (YYYY-MM-ddThh:mm)");
                console.write("  destination. Destination airport IATA code");
                console.write("  origin. Origin airport IATA code");
                //console.write("  tickets. Tickets");
                console.write("  cancel. Don't update any attribute");

                String attribute = console.read().toLowerCase();
                String askValueMessage = "\nEnter the new value for "+attribute;
                String body;
                switch (attribute){
                    // The API doesn't support updating tickets, because of a serialization bug, but the code from the client sends a valid request
                    /* case "tickets":
                        console.write("Enter the amount of tickets:");
                        Integer amountOfTickets = (Integer) console.read(Integer.class);
                        console.write("Enter the price per ticket: (EUR)");
                        Integer pricePerTickets = (Integer) console.read(Integer.class);
                        String ticketsJson = buildTicketsJson(amountOfTickets, pricePerTickets);

                        body = "{\""+attribute+"\":"+ticketsJson+"}";

                        // Run request & show result
                        tester.test("PUT", "/flights/"+flightNumber, body);
                        break; */
                    case "arrival":
                    case "departure":
                        askValueMessage += " with this format: YYYY-MM-ddThh:mm";
                    case "airline":
                    case "destination":
                    case "origin":
                        console.write(askValueMessage);
                        String value = console.read();

                        body = "{\""+attribute+"\":\""+value+"\"}";

                        // Run request & show result
                        tester.test("PUT", "/flights/"+flightNumber, body);
                        break;
                    case "cancel":
                        break;
                    default:
                        console.write("\nInvalid attribute");
                        break;
                }
            }
        }

        // Ask next action
        boolean answerRepeat = console.ask("\nDo you want to update another flight?");
        if(answerRepeat) goToMenuFlightUpdate();
        else goToMenuFlight();
    }

    private void goToMenuFlightDelete() {
        // Display instructions
        console.write("\nEnter a flightNumber:");

        // Get input
        Integer flightNumber = (Integer) console.read(Integer.class);

        // Check that flight exists
        int statusGet = tester.test("GET", "/flights/"+flightNumber);
        if (statusGet >= 200 && statusGet < 300){

            // Ask for confirmation
            boolean answerConfirmation = console.ask("\nDo you want to delete this flight?");
            if(answerConfirmation) {

                // Run request & show result
                tester.test("DELETE", "/flights/"+flightNumber);
            }
        }

        // Ask next action
        boolean answerRepeat = console.ask("\nDo you want to delete another flight?");
        if(answerRepeat) goToMenuFlightDelete();
        else goToMenuFlight();
    }

    private void goToMenuUser() {
        console.write("\nWhat do you want to do?");
        console.write("  A. Read all users");
        console.write("  C. Create a user");
        console.write("  R. Read a user");
        console.write("  U. Update a user");
        console.write("  D. Delete a user");
        console.write("  back. Go to user's menu");
        console.write("  exit. Close");

        switch (console.read().toLowerCase()){
            case "exit": return;
            case "back": goToMenuMain(); break;
            case "a": goToMenuUserAll(); break;
            case "c": goToMenuUserCreate(); break;
            case "r": goToMenuUserRead(); break;
            case "u": goToMenuUserUpdate(); break;
            case "d": goToMenuUserDelete(); break;
            default: console.write("Invalid option, try again\n"); goToMenuFlight(); break;
        }
    }

    private void goToMenuUserAll() {
        // Run request & show result
        tester.test("GET", "/users");

        goToMenuUser();
    }

    private void goToMenuUserCreate() {
        // Display instructions
        console.write("\nEnter the following values:");
        console.write("name:"); String name = console.read();

        console.write("\nYou entered this values:");
        console.write("name: "+name);

        // Ask for confirmation
        boolean answerConfirmation = console.ask("\nDo you want to create a user with this values?");
        if(answerConfirmation) {

            String body = "{\"name\":\""+name+"\"}";

            // Run request & show result
            tester.test("POST", "/users", body);
        }

        // Ask next action
        boolean answerRepeat = console.ask("\nDo you want to create another user?");
        if(answerRepeat) goToMenuUserCreate();
        else goToMenuUser();
    }

    private void goToMenuUserRead() {
        // Display instructions
        console.write("\nEnter a userId:");

        // Get input
        Integer userId = (Integer) console.read(Integer.class);

        // Run request & show result
        tester.test("GET", "/users/"+userId);

        // Ask next action
        boolean answerRepeat = console.ask("\nDo you want to read another user?");
        if(answerRepeat) goToMenuUserRead();
        else goToMenuUser();
    }

    private void goToMenuUserUpdate() {
        // Display instructions
        console.write("\nEnter a userId:");

        // Get input
        Integer userId = (Integer) console.read(Integer.class);

        // Check that user exists
        int statusGet = tester.test("GET", "/users/"+userId);
        if (statusGet >= 200 && statusGet < 300){

            // Ask for confirmation
            boolean answerConfirmation = console.ask("\nDo you want to update this user?");
            if(answerConfirmation) {

                console.write("\nWhat attribute do you want to update?");
                console.write("  name. User's name");
                console.write("  cancel. Don't update any attribute");

                String attribute = console.read().toLowerCase();
                String askValueMessage = "\nEnter the new value for "+attribute;
                switch (attribute){
                    case "name":
                        console.write(askValueMessage);
                        String value = console.read();

                        String body = "{\""+attribute+"\":\""+value+"\"}";

                        // Run request & show result
                        tester.test("PUT", "/users/"+userId, body);
                        break;
                    case "cancel":
                        break;
                    default:
                        console.write("\nInvalid attribute");
                        break;
                }
            }
        }

        // Ask next action
        boolean answerRepeat = console.ask("\nDo you want to update another user?");
        if(answerRepeat) goToMenuUserUpdate();
        else goToMenuUser();
    }

    private void goToMenuUserDelete() {
        // Display instructions
        console.write("\nEnter a userId:");

        // Get input
        Integer userId = (Integer) console.read(Integer.class);

        // Check that user exists
        int statusGet = tester.test("GET", "/users/"+userId);
        if (statusGet >= 200 && statusGet < 300){

            // Ask for confirmation
            boolean answerConfirmation = console.ask("\nDo you want to delete this user?");
            if(answerConfirmation) {

                // Run request & show result
                tester.test("DELETE", "/users/"+userId);
            }
        }

        // Ask next action
        boolean answerRepeat = console.ask("\nDo you want to delete another user?");
        if(answerRepeat) goToMenuUserDelete();
        else goToMenuUser();
    }
}
