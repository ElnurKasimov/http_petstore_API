package httpUtilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.List;

public class PetService {
    public static final HttpClient CLIENT = HttpClient.newHttpClient();
    public static final String URL = "https://petstore.swagger.io/v2/pet/";
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    public static Pet getPetByID(long id) throws IOException, InterruptedException {
        String requestURL = String.format("%s%d", URL, id);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(responce.body(), Pet.class);
    }

    public static int isPetExist(long petId) throws IOException, InterruptedException {
        String requestURL = String.format("%s%d", URL, petId);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        return response.statusCode();
    }

    public static List<Pet> getPetsByStatus(String petStatus ) throws IOException, InterruptedException {
        String requestURL = String.format("%s%s?status=%s", URL, "findByStatus", petStatus);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> responce =  CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(responce.body(), new TypeToken<List<Pet>>(){}.getType());
    }


}
/*
    public static void createNewObject (String endpoint, User myUser) throws IOException, InterruptedException {
        String requestBody = GSON.toJson(myUser);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL+endpoint))
                .header("Content-Type", "application/json; charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> responce =  CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Проверяем тело ответа. Если id у возвращенного объекта будет 11  - то все Ok.");
        System.out.println(responce.body());
    }
    public static void updateObject (String endpoint, User myUser) throws IOException, InterruptedException {
        String requestBody = GSON.toJson(myUser);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL+endpoint))
                .header("Content-Type", "application/json; charset=utf-8")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> responce =  CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Проверяем тело ответа. Если телефон у возвращенного объекта будет 123-45-67-890  - то все Ok.");
        System.out.println(responce.body());
    }
    public static int  deleteObject (String endpoint, User myUser) throws IOException, InterruptedException {
        String requestBody = GSON.toJson(myUser);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL+endpoint))
                .method("DELETE", HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        return CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).statusCode();
    }
    public static List<User> getInformationAboutAllUsers (String endpoint) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(URL+endpoint)).
                GET().
                build();
        HttpResponse<String> responce =  CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(responce.body(), new TypeToken<List<User>>(){}.getType());
    }
    public static User getUserInformationByID(String endpoint, int id ) throws IOException, InterruptedException {
        String requestURL = String.format("%s%s/%d", URL, endpoint, id);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> responce =  CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(responce.body());
        return GSON.fromJson(responce.body(), User.class);
    }

    }
    // Методы для  второго задания
    public static List<Post> getAllPostsDeterminedUser(String endpoint) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(URL+endpoint)).
                GET().
                build();
        HttpResponse<String> responce =  CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(responce.body(), new TypeToken<List<Post>>(){}.getType());
    }
    public static String getAllCommentsOnMaxPost(String endpoint) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(URL+endpoint)).
                GET().
                build();
        HttpResponse<String> responce =  CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return responce.body();
    }
    // Метод для  третьего задания
    public static void getAllOpenTasks(String endpoint) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(URL + endpoint)).
                GET().
                build();
        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<Task> allTasks = GSON.fromJson(responce.body(), new TypeToken<List<Task>>() {
        }.getType());
        allTasks.stream().
                filter(task -> task.completed.equals("false")).
                forEach(System.out::println);
    }
}
 */