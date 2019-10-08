import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RequestManager {

    // Attributes
    private String baseUrl;
    private Client client = ClientBuilder.newClient();

    // Constructors
    public RequestManager(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    // Methods
    public int request(String url) { return request("GET", url, ""); }
    public int request(String method, String url) { return request(method, url, ""); }
    public int request(String method, String url, String body){
        Invocation.Builder requestBuilder = client.target(baseUrl+url).request();

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

    // Helper methods
    private String prettifyJson(String rawJson) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(rawJson));
    }
}
