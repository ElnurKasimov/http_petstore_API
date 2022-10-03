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
    private static final int STATUS_CODE_OK = 200;

    public static void logsUser(String userName, String password) throws IOException, InterruptedException {
        String requestURL = String.format("%s/login?username=%s&password=%s", URL, userName, password);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        if ((responce.statusCode() / STATUS_CODE_OK) == 1 ) {
            System.out.println("Authorization of " +  userName + " successfully completed");
        } else {
            System.out.println("Something went wrong and  "  + userName + "hasn't been  authorized in the system.");
        }
    }
    public static void logOutUser() throws IOException, InterruptedException {
        String requestURL = String.format("%s/logout", URL);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> responce = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        if ((responce.statusCode() / STATUS_CODE_OK) == 1 ) {
            System.out.println("User with current authorization successfully logs out from the system");
        } else {
            System.out.println("Something went wrong and user with current authorization doesn't  log out from the system");
        }
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
        if ((responce.statusCode() / STATUS_CODE_OK) == 1 ) {
            System.out.println(newUsers);
            System.out.printf("List of  %ss  has been successfully added in the database.\n",
                    newUsers.get(0).getClass().getName().split("\\.")[1]);
        } else {
            System.out.printf("Something went wrong and list of  %ss hasn't been added in the database.\n",
                    newUsers.get(0).getClass().getName().split("\\.")[1]);
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
