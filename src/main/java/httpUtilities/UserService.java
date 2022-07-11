package httpUtilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class UserService {
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
            System.out.println("Пользхователь с текущей авторизацией успешно вышел из системы");
        } else {
            System.out.println("Что-то пошло не так и пользхователь с текущей авторизацией  не  вышел из системы");
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

    public static User inputAllDataOfUser () {
        Scanner sc34 = new Scanner(System.in);
        System.out.print("userName пользователя :");
        String userName34 = sc34.nextLine();
        System.out.print("имя пользователя :");
        String firstName34 = sc34.nextLine();
        System.out.print("фамилия пользователя :");
        String lastName34 = sc34.nextLine();
        System.out.print("e-mail пользователя :");
        String email = sc34.nextLine();
        System.out.print("пароль пользователя :");
        String password = sc34.nextLine();
        System.out.print("телефон пользователя :");
        String phone = sc34.nextLine();
        System.out.print("статус пользователя :");
        int userStatus = sc34.nextInt();
        User newUser = User.builder().
                username(userName34).
                firstName(firstName34).
                lastName(lastName34).
                email(email).
                password(password).
                phone(phone).
                userStatus(userStatus).
                build();
        return newUser;
    }


}
