import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Tester {

    public static void main(String[] args) {
        String baseUrl = "http://localhost:8080";

//        test(baseUrl+"/flights");
//        test("POST", baseUrl+"/flights", "{\"id\":3,\"name\":\"Maurici Abad\"}");
//        test("PUT", baseUrl+"/flights", "{\"id\":0,\"name\":\"Maurici Abad\"}");
//        test(baseUrl+"/flights");
//        test(baseUrl+"/flights/2");
//        test("DELETE", baseUrl+"/flights/2");
//        test(baseUrl+"/flights?id=2");
//        test(baseUrl+"/flights/first");
//        test(baseUrl+"/flights/hello");
//        test(baseUrl+"/flights/count");
//        test(baseUrl+"/flights/first");

        System.out.println("Finished");
    }

    public static int test(String url) {
        return test("GET", url, "");
    }

    public static int test(String method, String url) {
        return test(method, url, "");
    }

    public static int test(String method, String url, String body){
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        URI baseURI = UriBuilder.fromUri(url).build();
        WebTarget serviceTarget = client.target(baseURI);

        Builder requestBuilder = serviceTarget.request();
        Response response;
        switch (method){
            case "POST":
                response = requestBuilder.post(Entity.entity(body, MediaType.APPLICATION_JSON));
                break;
            case "PUT":
                response = requestBuilder.put(Entity.entity(body, MediaType.APPLICATION_JSON));
                break;
            case "DELETE":
                response = requestBuilder.delete();
                break;
            case "HEAD":
                response = requestBuilder.head();
                break;
            case "GET":
            default:
                response = requestBuilder.get();
                break;
        }


        int status = response.getStatus();

        System.out.println();
        System.out.println(method + " " + url);
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
