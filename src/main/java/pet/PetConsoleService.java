package pet;

import com.google.gson.Gson;
import httpUtilities.CommonUtilities;

import java.util.ArrayList;
import java.util.Scanner;

public class PetConsoleService {
    private static final int STATUS_CODE_OK = 200;

    public static void findPetById() {
        System.out.print("Enter id(only digits) : ");
        Scanner sc = new Scanner(System.in);
        long petId = Long.parseLong(sc.nextLine());
        try {
            if ((CommonUtilities.isObjectExist("pet", petId) / STATUS_CODE_OK) == 1) {
                Pet pet = PetHttpService.getPetByID(petId);
                System.out.println(pet);
            } else {
                System.out.println("There is no pet with such id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printListOfPetsByStatus() {
        System.out.print("Enter status (available, pending, sold) : ");
        Scanner sc = new Scanner(System.in);
        String petStatus = sc.nextLine();
        try {
            System.out.println(new Gson().toJson(PetHttpService.getPetsByStatus(petStatus)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addPetToDatabase() {
        System.out.println("Enter, please, data for pet.");
        Pet newPet = inputAllDataOfPet();
        try {
            CommonUtilities.createNewObject("pet", newPet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void uploadPetPhoto() {
        System.out.println("To upload image, please, enter required data:");
        System.out.print("pet id : ");
        Scanner sc = new Scanner(System.in);
        long idPet = sc.nextLong();
        System.out.print("Enter  additional Metada (if necessary): ");
        String additionalMetedata = sc.nextLine();
        if (additionalMetedata.equals("")) additionalMetedata = sc.nextLine();
        System.out.print("For uploading the image, place file into root directory of the project  and enter file name (in format 'name.expension') : ");
        String photoUrl = sc.nextLine();
        try {
            if (200 == PetHttpService.addPhotoToPet(idPet, photoUrl, additionalMetedata)) {
                System.out.println("image has been successfully uploaded");
                Pet pet = PetHttpService.getPetByID(idPet);
                pet.getPhotoUrls().add(photoUrl);
                System.out.println("So far the list of links for images like this:");
                System.out.println(pet.getPhotoUrls().toString());
                CommonUtilities.updateObject("pet", pet);
            }
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Pet inputAllDataOfPet() {
        System.out.print(" pet id: ");
        Scanner sc = new Scanner(System.in);
        long idPet = sc.nextLong();
        System.out.print("name : ");
        String namePet = sc.nextLine();
        if (namePet.equals("")) namePet = sc.nextLine();
        System.out.print("category id: ");
        long idCategory = sc.nextLong();
        System.out.print("category name : ");
        String nameCategory = sc.nextLine();
        if (nameCategory.equals("")) nameCategory = sc.nextLine();
        System.out.print("link to the image : ");
        String photoUrl = sc.nextLine();
        ArrayList<String> photoUrls = new ArrayList<>();
        photoUrls.add(photoUrl);
        System.out.print("tag id : ");
        long idTag = sc.nextLong();
        System.out.print("tag name :");
        String nameTag = sc.nextLine();
        if (nameTag.equals("")) nameTag = sc.nextLine();
        Tag tag = Tag.builder().id(idTag).name(nameTag).build();
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(tag);
        System.out.print("pet status (available,pending,sold) : ");
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

    public static void updatePetByFormData() {
        System.out.print("pet id : ");
        Scanner sc = new Scanner(System.in);
        long idPet = sc.nextLong();
        System.out.println("Enter, please, the data You want to update. Namely : ");
        System.out.print("new name : ");
        String namePet = sc.nextLine();
        if (namePet.equals("")) namePet = sc.nextLine();
        System.out.print("new pet status (available,pending,sold) : ");
        String petStatus = sc.nextLine();
        try {
            if ((PetHttpService.updatePetByFormData(idPet, namePet, petStatus) / STATUS_CODE_OK) == 1) {
                System.out.println("Data has been successfully updated.");
                Pet petUpdatedByFormData = PetHttpService.getPetByID(idPet);
                Gson gson = new Gson();
                System.out.println(gson.toJson(petUpdatedByFormData));
            } else {
                System.out.println("Data hasn't been updated.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updatePetAllData() {
        System.out.println("Enter, please, all data for pet You want to update.");
        Pet petToUpdateAllData = PetConsoleService.inputAllDataOfPet();
        try {
            CommonUtilities.updateObject("pet", petToUpdateAllData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deletePet() {
        System.out.println("Enter, please, data for pet You want to delete from database.");
        Scanner sc = new Scanner(System.in);
        System.out.print("арi key : ");
        String apiKey = sc.nextLine();
        System.out.print("pet id : ");
        long idPet = sc.nextLong();
        try {
            Pet petToDelete = PetHttpService.getPetByID(idPet);
            CommonUtilities.deleteObject("pet/" + idPet, petToDelete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

