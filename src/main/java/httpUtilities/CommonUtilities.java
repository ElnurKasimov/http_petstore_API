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
        if (responce.statusCode() == 200 ) {
            System.out.println(newObject.getClass().getName().replaceAll("httpUtilities.", "") + " успешно добавлен в базу данных");
        } else {
            System.out.println("Что-то пошло не так и " + newObject.getClass().getName().replaceAll("httpUtilities.", "") + " не был добавлен в базу данных");
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
        //System.out.println(responce.body());
        if (responce.statusCode() == 200 ) {
            System.out.println("Данные по " + newObject.getClass().getName().replaceAll("httpUtilities.", "") + " успешно обновлены");
        } else {
            System.out.println("Что-то пошло не так и данные по "  + newObject.getClass().getName().replaceAll("httpUtilities.", "") + " не были обновлены");
        }
    }

    public static void deleteObject (String endpoint, Object object) throws IOException, InterruptedException {
        System.out.println(object);
        System.out.println(URL+endpoint);
        String requestBody = GSON.toJson(object);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL+endpoint))
                .method("DELETE", HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(responce.body());
        int result = responce.statusCode();
        System.out.println(result);
        if (result == 200 ) {
            System.out.println(object.getClass().getName().replaceAll("httpUtilities.", "") + " успешно удален");
        } else {
            System.out.println("Что-то пошло не так и "  + object.getClass().getName().replaceAll("httpUtilities.", "") + " не был удален");
        }
    }

}
