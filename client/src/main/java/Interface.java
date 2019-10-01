public class Interface {

    private Console console = new Console();

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
        console.write("  C. Create a flight");
        console.write("  R. Read a flight");
        console.write("  U. Update a flight");
        console.write("  D. Delete a flight");
        console.write("  back. Go to flight's menu");
        console.write("  exit. Close");

        switch (console.read().toLowerCase()){
            case "exit": return;
            case "back": goToMenuMain(); break;
            case "c": goToMenuFlightCreate(); break;
            case "r": goToMenuFlightRead(); break;
            case "u": goToMenuFlightUpdate(); break;
            case "d": goToMenuFlightDelete(); break;
            default: console.write("Invalid option, try again\n"); goToMenuFlight(); break;
        }
    }

    private void goToMenuFlightCreate() {
        console.write("\nCreate Flight menu");
        console.write("Work in progress‍, try again in next update.");
        goToMenuFlight();
    }

    private void goToMenuFlightRead() {
        console.write("\nTo Read Flight give a flightNumber");
        Integer flightNumber = (Integer) console.read("flightNumber: ", Integer.class);

        if(console.ask("\nDo you want to read another flight?")){
            goToMenuFlightRead();
        }else{
            goToMenuFlight();
        }
    }

    private void goToMenuFlightUpdate() {
        console.write("\nUpdate Flight menu");
        console.write("Work in progress, try again in next update.");
        goToMenuFlight();
    }

    private void goToMenuFlightDelete() {
        console.write("\nDelete Flight menu");
        console.write("Work in progress, try again in next update.");
        goToMenuFlight();
    }

    private void goToMenuUser() {
        console.write("\nWhat do you want to do?");
        console.write("  C. Create a user");
        console.write("  R. Read a user");
        console.write("  U. Update a user");
        console.write("  D. Delete a user");
        console.write("  back. Go to user's menu");
        console.write("  exit. Close");

        switch (console.read().toLowerCase()){
            case "exit": return;
            case "back": goToMenuMain(); break;
            case "c": goToMenuUserCreate(); break;
            case "r": goToMenuUserRead(); break;
            case "u": goToMenuUserUpdate(); break;
            case "d": goToMenuUserDelete(); break;
            default: console.write("Invalid option, try again\n"); goToMenuFlight(); break;
        }
    }

    private void goToMenuUserCreate() {
        console.write("\nCreate User menu");
        console.write("Work in progress‍, try again in next update.");
        goToMenuUser();
    }

    private void goToMenuUserRead() {
        console.write("\nRead User menu");
        console.write("Work in progress, try again in next update.");
        goToMenuUser();
    }

    private void goToMenuUserUpdate() {
        console.write("\nUpdate User menu");
        console.write("Work in progress, try again in next update.");
        goToMenuUser();
    }

    private void goToMenuUserDelete() {
        console.write("\nDelete User menu");
        console.write("Work in progress, try again in next update.");
        goToMenuUser();
    }
}
