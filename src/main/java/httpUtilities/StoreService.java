package httpUtilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class StoreService {
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

    public static Order inputAllDataOfOrder(){
        System.out.print("id заказа (от 1 до 9 включительно) : ");
        Scanner sc = new Scanner(System.in);
        long idPet = sc.nextLong();
        System.out.print("id домашнего животного : ");
        long petId = sc.nextLong();
        System.out.print("количество : ");
        int quantity = sc.nextInt();
        System.out.print("дата отгрузки (ГГГГ-ММ-ДД) : ");
        String shipDate = sc.nextLine();
        if(shipDate.equals("")) shipDate = sc.nextLine();
        System.out.print("статус заказа (placed, approved, delivered) : ");
        String status = sc.nextLine();
        Order newOrder = Order.builder().
                id(idPet).
                petId(petId).
                quantity(quantity).
                shipDate(shipDate).
                status(Order.Status.valueOf(status)).
                build();
        return newOrder;
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
    public static List<User> getUserInformationByUsername(String endpoint, String username ) throws IOException, InterruptedException {
        String requestURL = String.format("%s%s?username=%s", URL, endpoint, username);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> responce =  CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(responce.body(), new TypeToken<List<User>>(){}.getType());
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