package httpUtilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class CommonUtilities {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final String URL = "https://petstore.swagger.io/v2/";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final int RESPONCE_CODE_OK = 200;

    public static int isObjectExist(String endpoint, String name) throws IOException, InterruptedException {
        String requestURL = String.format("%s%s/%s", URL, endpoint,  name);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }
    public static int isObjectExist(String endpoint, long id) throws IOException, InterruptedException {
        String requestURL = String.format("%s%s/%d", URL, endpoint,  id);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }
    public static void createNewObject (String endpoint, Object newObject) throws IOException, InterruptedException {
        String requestURL = String.format("%s%s", URL, endpoint);
        String requestBody = GSON.toJson(newObject);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestURL))
                .header("Content-Type", "application/json; charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        if ( (responce.statusCode() / RESPONCE_CODE_OK ) == 1) {
            System.out.println(newObject);

            System.out.println(newObject.getClass().getName().split("\\.")[1] + " successfully added into the database");
        } else {
            System.out.println("Something went wrong and " + newObject.getClass().getName().split("\\.")[1] + " hasn't been added into the database");
        }
    }

    public static void updateObject(String endpoint, Object newObject) throws IOException, InterruptedException {
        String requestBody = GSON.toJson(newObject);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + endpoint))
                .header("Content-Type", "application/json; charset=utf-8")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        if ( (responce.statusCode() / RESPONCE_CODE_OK ) == 1) {
            System.out.println("Data for " + newObject.getClass().getName().split("\\.")[1] + " successfully have been updated.");
        } else {
            System.out.println("Something went wrong and "  + newObject.getClass().getName().split("\\.")[1] + " haven't been updated.");
        }
    }
    public static void deleteObject (String endpoint, Object object) throws IOException, InterruptedException {
        System.out.println(object);
        String requestBody = GSON.toJson(object);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL+endpoint))
                .method("DELETE", HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        if (responce.statusCode() == RESPONCE_CODE_OK ) {
            System.out.println(object.getClass().getName().split("\\.")[1] + " has been successfully deleted");
        } else {
            System.out.println("Something went wrong and "  + object.getClass().getName().split("\\.")[1]+ " hasn't been deleted.");
        }
    }
}
