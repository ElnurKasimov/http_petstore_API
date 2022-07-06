package httpUtilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CommonUtilities {
    public static final HttpClient CLIENT = HttpClient.newHttpClient();
    public static final String URL = "https://petstore.swagger.io/v2/";
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static int isObjectExist(String endpoint, String name) throws IOException, InterruptedException {
        String requestURL = String.format("%s%s/%s", URL, endpoint,  name);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
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
        System.out.println(responce.body());
        if (responce.statusCode() == 200 ) {
            System.out.println("Домашнее животное успешно добавлено в базу данных");
        } else {
            System.out.println("Что-то пошло не так и домашнее животное не было добавлено в базу данных");
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
        System.out.println(responce.body());
        if (responce.statusCode() == 200 ) {
            System.out.println("Данные по домашнему животному успешно обновлены");
        } else {
            System.out.println("Что-то пошло не так и данные по домашнему животному не были обновлены");
        }
    }

    public static int  deleteObject (String endpoint, Object object) throws IOException, InterruptedException {
        String requestBody = GSON.toJson(object);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL+endpoint))
                .method("DELETE", HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        System.out.println(CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body());
        int result = CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).statusCode();

        if (result == 200 ) {
            System.out.println("Домашнее животное успешно удалено");
        } else {
            System.out.println("Что-то пошло не так и домашнее животное не было удалено");
        }
        return result;
    }

}
