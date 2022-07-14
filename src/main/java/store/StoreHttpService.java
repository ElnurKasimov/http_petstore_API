package store;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class StoreHttpService {
    public static final HttpClient CLIENT = HttpClient.newHttpClient();
    public static final String URL = "https://petstore.swagger.io/v2/store/";
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void getInventory() throws IOException, InterruptedException {
        String requestURL = String.format("%s%s", URL, "inventory");
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(responce.body().replaceAll(",", ",\n"));
    }

    public static Order getOrderByID(String endpoint, long id) throws IOException, InterruptedException {
        String requestURL = String.format("%s%s/%d", URL, endpoint, id);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(responce.body(), Order.class);
    }
}
