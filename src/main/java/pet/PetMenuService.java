package pet;

import com.google.gson.Gson;
import httpUtilities.CommonUtilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetMenuService {

    public static void findPetById() {
        System.out.print("Введите id : ");
        Scanner sc = new Scanner(System.in);
        long petId = sc.nextLong();
        try {
            if (CommonUtilities.isObjectExist("pet", petId) == 200) {
                Pet pet = PetHttpService.getPetByID(petId);
                System.out.println(pet);
            } else {
                System.out.println("Домашнего животного с таким id не существует");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void printLIstOfPetsByStatus(){
        System.out.print("Введите статус( available, pending, sold) : ");
        Scanner sc = new Scanner(System.in);
        String petStaus = sc.nextLine();
        try {
            List<Pet> pets = PetHttpService.getPetsByStatus(petStaus);
            Gson gson = new Gson();
            System.out.println(gson.toJson(pets));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addPetToDatabase() {
        System.out.println("Введите, пожалуйста, данные по домашнему животному.");
        Pet newPet = inputAllDataOfPet();
        try {
            CommonUtilities.createNewObject("pet", newPet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void uploadPetPhoto () {
        System.out.println("Для добавления фото, внесите необходимые данные)");
        System.out.print("id домашнего животного : ");
        Scanner sc = new Scanner(System.in);
        long idPet = sc.nextLong();
        System.out.print("Введите дополнительные данные для передачи на сервер : ");
        String additionalMetedata = sc.nextLine();
        if( additionalMetedata.equals("")) additionalMetedata = sc.nextLine();
        System.out.print("Для того, чтобы загрузить картинку, поместите файл в корневой каталог программы" +
                " и укажите его имя (имя.расширение) : ");
        String photoUrl = sc.nextLine();
        try {
            if(200 == PetHttpService.addPhotoToPet(idPet, photoUrl, additionalMetedata)) {
                System.out.println("картинка успешно добавлена");
                Pet pet = PetHttpService.getPetByID(idPet);
                System.out.println(pet);
                if (pet.getPhotoUrls() != null ) {
                    System.out.println(pet.getPhotoUrls().toString());
                } else {
                    System.out.println("[]");
                }
                pet.getPhotoUrls().add(photoUrl);
                System.out.println(pet.getPhotoUrls().toString());
                CommonUtilities.updateObject("pet", pet);
            };
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Pet inputAllDataOfPet(){
        System.out.print("id домашнего животного : ");
        Scanner sc = new Scanner(System.in);
        long idPet = sc.nextLong();
        System.out.print("кличка : ");
        String namePet = sc.nextLine();
        if(namePet.equals("")) namePet = sc.nextLine();
        System.out.print("id категории : ");
        long idCategory = sc.nextLong();
        System.out.print("название категории : ");
        String nameCategory = sc.nextLine();
        if(nameCategory.equals("")) nameCategory= sc.nextLine();
        System.out.print("ссылка на фотографию : ");
        String photoUrl = sc.nextLine();
        ArrayList<String> photoUrls = new ArrayList<>();
        photoUrls.add(photoUrl);
        System.out.print("id тэга : ");
        long idTag = sc.nextLong();
        System.out.print("название тэга :");
        String nameTag = sc.nextLine();
        if(nameTag.equals("")) nameTag = sc.nextLine();
        Tag tag = Tag.builder().id(idTag).name(nameTag).build();
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(tag);
        System.out.print("статус домашнего животного (available,pending,sold) : ");
        String petStatus = sc.nextLine();
        return Pet.builder().
                id(idPet).
                category(Category.builder().id(idCategory).name(nameCategory).build()).
                name(namePet).
                photoUrls(photoUrls).
                tags(tags).
                petStatus(petStatus).
                build();
    }
    public static void updatePetByFormData() throws IOException, InterruptedException {
        System.out.print("id домашнего животного : ");
        Scanner sc = new Scanner(System.in);
        long idPet = sc.nextLong();
        System.out.println("Введите, пожалуйста, те данные по домашнему животному, которого желаете обновить. А именно : ");
        System.out.print("новая кличка : ");
        String namePet = sc.nextLine();
        if(namePet.equals("")) namePet = sc.nextLine();
        System.out.print("новый статус домашнего животного (available,pending,sold) : ");
        String petStatus = sc.nextLine();
        if  (PetHttpService.updatePetByFormData(idPet, namePet, petStatus) == 200) {
            System.out.println("Данные успешно обновлены");
            Pet petUpdatedByFormData = PetHttpService.getPetByID(idPet);
            System.out.println(petUpdatedByFormData);
        } else {
            System.out.println("Данные не изменены. Скорее всего был некорректный ввод данных");
        }
    }
    public static void updatePetAllData() throws IOException, InterruptedException {
        System.out.println("Введите, пожалуйста, все данные по домашнему животному, которого желаете обновить.");
        Pet petToUpdateAllData = PetMenuService.inputAllDataOfPet();
        CommonUtilities.updateObject("pet", petToUpdateAllData);
    }
    public static void deletePet() throws IOException, InterruptedException {
        System.out.println("Введите, пожалуйста, данные по домашнему животному, которого желаете удалить из базы.");
        Scanner sc = new Scanner(System.in);
        System.out.print("арi ключ : ");
        String apiKey = sc.nextLine();
        System.out.print("id домашнего животного : ");
        long idPet = sc.nextLong();
        Pet petToDelete = PetHttpService.getPetByID(idPet);
        CommonUtilities.deleteObject("pet/" + idPet, petToDelete);
    }
}
