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

        goToMainMenu();

        console.write("\nThank you for using our API 😄");
    }

    private void goToMainMenu() {
        console.write("What do you want to do?");
        console.write("  1. Create a flight");
        console.write("  2. Delete a flight");
        console.write("  3. Read a flight");
        console.write("  exit. Close");

        String action = console.read();

        switch (action){
            case "exit": return;
            case "1":
                console.write("You selected CREATE");
                break;
            case "2":
                console.write("You selected DELETE");
                break;
            case "3":
                console.write("You selected READ");
                break;
            default:
                console.write("Invalid option, try again\n");
                goToMainMenu();
                break;
        }

    }
}
