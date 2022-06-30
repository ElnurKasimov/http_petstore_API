package httpUtilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserService {
    public static final HttpClient CLIENT = HttpClient.newHttpClient();
    public static final String URL = "https://petstore.swagger.io/v2/user";
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    public static void createNewUser (User myUser) throws IOException, InterruptedException {
        String requestBody = GSON.toJson(myUser);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type", "application/json; charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(responce.body());
    }

    public static User getUserByUsername(String userName) throws IOException, InterruptedException {
        String requestURL = String.format("%s/%s", URL, userName);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(responce.body());
        return GSON.fromJson(responce.body(), User.class);
    }





}
