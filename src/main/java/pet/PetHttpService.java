package pet;

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

public class PetHttpService {
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

    public static List<Pet> getPetsByStatus(String petStatus ) throws IOException, InterruptedException {
        String requestURL = String.format("%s%s?status=%s", URL, "findByStatus", petStatus);
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(requestURL)).
                GET().
                build();
        HttpResponse<String> responce =  CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(responce.body(), new TypeToken<List<Pet>>(){}.getType());
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
