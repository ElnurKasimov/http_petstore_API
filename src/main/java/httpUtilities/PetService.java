package httpUtilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static Pet inputAllDataOfPet(){
        System.out.print("id домашнего животного : ");
        Scanner sc13 = new Scanner(System.in);
        long idPet = sc13.nextLong();
        System.out.print("кличка : ");
        String namePet13 = sc13.nextLine();
        if(namePet13.equals("")) namePet13 = sc13.nextLine();
        System.out.print("id категории : ");
        long idCategory = sc13.nextLong();
        System.out.print("название категории : ");
        String nameCategory = sc13.nextLine();
        if(nameCategory.equals("")) nameCategory= sc13.nextLine();
        System.out.print("ссылка на фотографию : ");
        String photoUrl = sc13.nextLine();
        ArrayList<String> photoUrls = new ArrayList<>();
        photoUrls.add(photoUrl);
        System.out.print("id тэга : ");
        long idTag = sc13.nextLong();
        System.out.print("название тэга :");
        String nameTag = sc13.nextLine();
        if(nameTag.equals("")) nameTag = sc13.nextLine();
        Tag tag = Tag.builder().id(idTag).name(nameTag).build();
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(tag);
        System.out.print("статус домашнего животного (available,pending,sold) : ");
        String petStatus = sc13.nextLine();
        Pet newPet = Pet.builder().
                id(idPet).
                category(Category.builder().id(idCategory).name(nameCategory).build()).
                name(namePet13).
                photoUrls(photoUrls).
                tags(tags).
                petStatus(petStatus).
                build();
        return newPet;
    }

    public static int addPhotoToPet (long idPet, String petUrl, String additionalMetadata) throws IOException, InterruptedException {
        String requestURL = String.format("%s%d/%s", URL, idPet, "uploadImage");
        File imgToLoad = new File(petUrl);
        InputStream fis = new FileInputStream(imgToLoad);
        byte[] allBytes = fis.readAllBytes();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpEntity multipart = MultipartEntityBuilder.create().
                addTextBody("additionalMetadata", additionalMetadata, ContentType.TEXT_PLAIN).
                addBinaryBody("file", allBytes, ContentType.DEFAULT_BINARY, imgToLoad.getName()).
                build();
        HttpPost httpPost = new HttpPost(requestURL);
        httpPost.setEntity(multipart);
        int statusCode=0;
        try {
            client.execute(httpPost);
            ResponseHandler<String> handler = new BasicResponseHandler();
            String body = client.execute(httpPost, handler);
            statusCode = client.execute(httpPost).getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return statusCode;
    }

    public static int updatePetByFormData (long idPet, String newName, String newStatus) throws IOException, InterruptedException {
        String requestURL = String.format("%s%d", URL, idPet);
        CloseableHttpClient client = HttpClients.createDefault();
        //https://www.programcreek.com/java-api-examples/?class=org.apache.http.client.entity.UrlEncodedFormEntity&method=setContentType


        HttpPost httpPost = new HttpPost(requestURL);
        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        nameValuePairList.add(new BasicNameValuePair("name", newName));
        nameValuePairList.add(new BasicNameValuePair("status", newStatus));

        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
        formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.setEntity(formEntity);

        int statusCode=0;
        try {
            client.execute(httpPost);
            ResponseHandler<String> handler = new BasicResponseHandler();
            String body = client.execute(httpPost, handler);
            statusCode = client.execute(httpPost).getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return statusCode;

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