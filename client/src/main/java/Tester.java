import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Tester {

    String baseUrl;

    public Tester(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void run(){

        // test("/flights");
        // test("POST", "/flights", "{\"id\":3,\"name\":\"Maurici Abad\"}");
        // test("PUT", "/flights", "{\"id\":0,\"name\":\"Maurici Abad\"}");
        // test("/flights/2");
        // test("DELETE", "/flights/2");
        // test("/flights?origin=BCN");
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

        URI baseURI = UriBuilder.fromUri(baseUrl+url).build();
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

        String rawJson = response.readEntity(String.class);

        String prettyJson = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(rawJson));

        System.out.println(prettyJson);

        return status;
    }
}
