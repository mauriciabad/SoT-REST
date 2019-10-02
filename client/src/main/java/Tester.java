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

    private String baseUrl;
    private Client client = ClientBuilder.newClient(new ClientConfig());


    public Tester(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void run(){
        // test("/flights");
        // test("/flights/2");
        // test("/flights?origin=BCN");
        // test("DELETE", "/flights/2");
        /* test("POST", "/flights", "{\n" +
                "  \"airline\": \"Ryanair\",\n" +
                "  \"arrival\": \"2020-9-28 10:20\",\n" +
                "  \"departure\": \"2020-9-28 8:20\",\n" +
                "  \"destination\": \"GRO\",\n" +
                "  \"origin\": \"MAD\",\n" +
                "  \"tickets\": []\n" +
                "}");
        */
        /* test("PUT", "/flights/1", "{\n" +
                "  \"airline\": \"New Airline\",\n" +
                "}");
        */
    }

    public int test(String url) { return test("GET", url, ""); }
    public int test(String method, String url) { return test(method, url, ""); }
    public int test(String method, String url, String body){
        URI baseURI = UriBuilder.fromUri(baseUrl+url).build();
        Invocation.Builder requestBuilder = client.target(baseURI).request();

        Response response;
        switch (method){
            case "POST":   response = requestBuilder.post(Entity.entity(body, MediaType.APPLICATION_JSON)); break;
            case "PUT":    response = requestBuilder.put(Entity.entity(body, MediaType.APPLICATION_JSON)); break;
            case "DELETE": response = requestBuilder.delete(); break;
            case "HEAD":   response = requestBuilder.head(); break;
            default:       response = requestBuilder.get(); break;
        }

        String rawJson = response.readEntity(String.class);

        String prettyJson;
        try{ prettyJson = prettifyJson(rawJson);
        }catch (Exception e){ prettyJson = rawJson; }

        System.out.println("\n" + method + " " + url);
        System.out.println(response.getStatus() + " " + response.getStatusInfo().getReasonPhrase());
        System.out.println(prettyJson);

        return response.getStatus();
    }

    private String prettifyJson(String rawJson) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(rawJson));
    }
}
