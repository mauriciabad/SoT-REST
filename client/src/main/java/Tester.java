import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Tester {

    public void run(){
        String baseUrl = "http://localhost:8080/airline/v1";

        // test(baseUrl+"/flights");
        // test("POST", baseUrl+"/flights", "{\"id\":3,\"name\":\"Maurici Abad\"}");
        // test("PUT", baseUrl+"/flights", "{\"id\":0,\"name\":\"Maurici Abad\"}");
        // test(baseUrl+"/flights/2");
        // test("DELETE", baseUrl+"/flights/2");
        // test(baseUrl+"/flights?origin=BCN");
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

        System.out.println("\n" + method + " " + url);
        System.out.print(status + " ");

        if(status >= 200 && status < 300 ) {
            String entity = response.readEntity(String.class);
            System.out.println(entity);
        } else{
            System.out.println(response.getStatusInfo().getReasonPhrase());
        }

        return status;
    }
}
