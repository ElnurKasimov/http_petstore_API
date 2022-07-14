package user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class UserHttpService {
    public static final HttpClient CLIENT = HttpClient.newHttpClient();
    public static final String URL = "https://petstore.swagger.io/v2/user";
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void logsUser(String userName, String password) throws IOException, InterruptedException {
        String requestURL = String.format("%s/login?username=%s&password=%s", URL, userName, password);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        if (responce.statusCode() == 200 ) {
            System.out.println("Авторизация " +  userName + " прошла успешно");
        } else {
            System.out.println("Что-то пошло не так и  "  + userName + " не авторизовался в системе");
        }
    }
    public static void logsOutUser() throws IOException, InterruptedException {
        String requestURL = String.format("%s/logout", URL);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        if (responce.statusCode() == 200 ) {
            System.out.println("Пользователь с текущей авторизацией успешно вышел из системы");
        } else {
            System.out.println("Что-то пошло не так и пользователь с текущей авторизацией  не  вышел из системы");
        }
    }
    public static int isUserExist(String userName) throws IOException, InterruptedException {
        String requestURL = String.format("%s/%s", URL, userName);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }
    public static User getUserByUsername(String userName) throws IOException, InterruptedException {
        String requestURL = String.format("%s/%s", URL, userName);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(responce.body(), User.class);
    }
    public static void createListOfNewUsers (List<User> newUsers) throws IOException, InterruptedException {
        String requestURL = String.format("%s%s", URL, "/createWithList");
        String requestBody = GSON.toJson(newUsers);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestURL))
                .header("Content-Type", "application/json; charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        if (responce.statusCode() == 200 ) {
            System.out.println(newUsers);
            System.out.println("список " + newUsers.get(0).getClass().getName().replaceAll("httpUtilities.", "") + "-ов успешно добавлен в базу данных");
        } else {
            System.out.println("Что-то пошло не так и список " + newUsers.get(0).getClass().getName().replaceAll("httpUtilities.", "") + "-ов не был добавлен в базу данных");
        }
    }
    public static List<User> createListOfUsersForTest() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User newUser = User.builder().
                    id(123123123+i).
                    username("name" + i).
                    firstName("firstName" + i).
                    lastName("lastName" + i).
                    email("test" + i + "@gmail.com").
                    password("password" + i).
                    phone("067-123-45-0" + i).
                    userStatus(1).
                    build();
            users.add(newUser);
        }
        return users;
    }
}
