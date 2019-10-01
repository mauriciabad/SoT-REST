import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Tester {

    private Console console = new Console();

    public void run(){
        String baseUrl = "http://localhost:8080/airline/v1";

        // test(baseUrl+"/flights");
//        test("POST", baseUrl+"/flights", "{\"id\":3,\"name\":\"Maurici Abad\"}");
//        test("PUT", baseUrl+"/flights", "{\"id\":0,\"name\":\"Maurici Abad\"}");
//        test(baseUrl+"/flights/2");
//        test("DELETE", baseUrl+"/flights/2");
//        test(baseUrl+"/flights?origin=BCN");


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

        String action = console.read();

        switch (action){
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

    public int test(String url) {
        return test("GET", url, "");
    }

    public int test(String method, String url) {
        return test(method, url, "");
    }

    public int test(String method, String url, String body){
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        URI baseURI = UriBuilder.fromUri(url).build();
        WebTarget serviceTarget = client.target(baseURI);

        Invocation.Builder requestBuilder = serviceTarget.request();
        Response response;
        switch (method){
            case "POST":   response = requestBuilder.post(Entity.entity(body, MediaType.APPLICATION_JSON)); break;
            case "PUT":    response = requestBuilder.put(Entity.entity(body, MediaType.APPLICATION_JSON)); break;
            case "DELETE": response = requestBuilder.delete(); break;
            case "HEAD":   response = requestBuilder.head(); break;
            default:       response = requestBuilder.get(); break;
        }

        int status = response.getStatus();

        console.write();
        console.write(method + " " + url);
        System.out.print(status + " ");

        if(status >= 200 && status < 300 ) {
            String entity = response.readEntity(String.class);
            console.write(entity);
        } else{
            console.write(response.getStatusInfo().getReasonPhrase());
        }

        return status;
    }
}
