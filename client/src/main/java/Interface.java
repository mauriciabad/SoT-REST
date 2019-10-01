public class Interface {

    private Console console = new Console();

    public void run(){
        console.write(  "     _    _      _ _                 _    ____ ___ \n" +
                "    / \\  (_)_ __| (_)_ __   ___     / \\  |  _ \\_ _|\n" +
                "   / _ \\ | | '__| | | '_ \\ / _ \\   / _ \\ | |_) | | \n" +
                "  / ___ \\| | |  | | | | | |  __/  / ___ \\|  __/| | \n" +
                " /_/   \\_\\_|_|  |_|_|_| |_|\\___| /_/   \\_\\_|  |___|\n" +
                "                                                   ");

        console.write("Welcome to Airline API ✈\n");

        goToMenuMain();

        console.write("\nThank you for using our API 😄");
    }

    private void goToMenuMain() {
        console.write("What Entity you want to work with?");
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
        console.write("What do you want to do?");
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
        console.write("Create Flight menu");
        console.write("Work in progress 🛠‍, try again in next update. 👍");
        goToMenuFlight();
    }

    private void goToMenuFlightRead() {
        console.write("Read Flight menu");
        console.write("Work in progress 🛠, try again in next update. 👍");
        goToMenuFlight();
    }

    private void goToMenuFlightUpdate() {
        console.write("Update Flight menu");
        console.write("Work in progress 🛠, try again in next update. 👍");
        goToMenuFlight();
    }

    private void goToMenuFlightDelete() {
        console.write("Delete Flight menu");
        console.write("Work in progress 🛠, try again in next update. 👍");
        goToMenuFlight();
    }

    private void goToMenuUser() {
        console.write("What do you want to do?");
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
        console.write("Create User menu");
        console.write("Work in progress 🛠‍, try again in next update. 👍");
        goToMenuUser();
    }

    private void goToMenuUserRead() {
        console.write("Read User menu");
        console.write("Work in progress 🛠, try again in next update. 👍");
        goToMenuUser();
    }

    private void goToMenuUserUpdate() {
        console.write("Update User menu");
        console.write("Work in progress 🛠, try again in next update. 👍");
        goToMenuUser();
    }

    private void goToMenuUserDelete() {
        console.write("Delete User menu");
        console.write("Work in progress 🛠, try again in next update. 👍");
        goToMenuUser();
    }
}
