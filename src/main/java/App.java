import consoleMenuService.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import httpUtilities.*;


public class App {
    public static void main(String[] args) throws IOException, InterruptedException {

        MenuService menuService = new MenuService();
        menuService.create();

        int choice;
        do {
            menuService.get("Main").printMenu();
            choice = menuService.get("Main").makeChoice();
            switch (choice) {
                case 1:
                    int choicePet;
                    do {
                        menuService.get("Pet").printMenu();
                        choicePet = menuService.get("Pet").makeChoice();
                        switch (choicePet) {
                            case 1:
                                System.out.print("Введите id : ");
                                Scanner sc11 = new Scanner(System.in);
                                long petId11 = sc11.nextLong();
                                try {
                                    if (CommonUtilities.isObjectExist("pet", petId11) == 200) {
                                        Pet pet = PetService.getPetByID(petId11);
                                        System.out.println(pet);
                                    } else {
                                        System.out.println("Домашнего животного с таким id не существует");
                                    }
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 2:
                                System.out.print("Введите статус( available, pending, sold) : ");
                                Scanner sc12 = new Scanner(System.in);
                                String petStaus = sc12.nextLine();
                                try {
                                        List<Pet> pets = new ArrayList<>();
                                        pets = PetService.getPetsByStatus(petStaus);
                                        System.out.println(pets.toString());
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 3:
                                System.out.println("Введите, пожалуйста, данные по домашнему животному.");
                                Pet newPet = PetService.inputAllDataOfPet();
                                System.out.println(newPet);
                                try {
                                    CommonUtilities.createNewObject("pet", newPet);
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 4:
                                System.out.println("Для добавления фото, внесите необходимые данные)");
                                System.out.print("id домашнего животного : ");
                                Scanner sc14 = new Scanner(System.in);
                                long idPet14 = sc14.nextLong();
                                System.out.print("Введите дополнительные данные для передачи на сервер : ");
                                String additionalMetedata = sc14.nextLine();
                                if( additionalMetedata.equals("")) additionalMetedata = sc14.nextLine();
                                System.out.print("Для того, чтобы загрузить картинку, поместите файл в корневой каталог программы" +
                                        " и укажите его имя (имя.расширение) : ");
                                String photoUrl14 = sc14.nextLine();
                                try {
                                    if(200 == PetService.addPhotoToPet(idPet14, photoUrl14, additionalMetedata)) {
                                        System.out.println("картинка успешно добавлена");
                                        Pet pet = PetService.getPetByID(idPet14);
                                        System.out.println(pet);
                                        if (pet.getPhotoUrls() != null ) {
                                            System.out.println(pet.getPhotoUrls().toString());
                                         } else {
                                            System.out.println("[]");
                                        }
                                        pet.getPhotoUrls().add(photoUrl14);
                                        System.out.println(pet.getPhotoUrls().toString());
                                        CommonUtilities.updateObject("pet", pet);
                                    };
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 5:
                                System.out.print("id домашнего животного : ");
                                Scanner sc15 = new Scanner(System.in);
                                long idPet15 = sc15.nextLong();
                                System.out.println("Введите, пожалуйста, те данные по домашнему животному, которого желаете обновить. А именно : ");
                                System.out.print("новая кличка : ");
                                String namePet15 = sc15.nextLine();
                                if(namePet15.equals("")) namePet15 = sc15.nextLine();
                                System.out.print("новый статус домашнего животного (available,pending,sold) : ");
                                String petStatus15 = sc15.nextLine();
                                /*
                                petToUpdateDataInForm.setName(namePet15);
                                petToUpdateDataInForm.setPetStatus(Pet.PetStatus.valueOf(petStatus15));
                                */
                                if  (PetService.updatePetByFormData(idPet15, namePet15, petStatus15) == 200) {
                                    System.out.println("Данные успешно обновлены");
                                    Pet petUpdatedByFormData = PetService.getPetByID(idPet15);
                                    System.out.println(petUpdatedByFormData);
                                } else {
                                    System.out.println("Данные не изменены. Скорее всего был некорректный ввод данных");
                                }
                                break;
                            case 6:
                                System.out.println("Введите, пожалуйста, все данные по домашнему животному, которого желаете обновить.");
                                Pet petToUpdateAllData = PetService.inputAllDataOfPet();
                                CommonUtilities.updateObject("pet", petToUpdateAllData);
                                break;
                            case 7:
                                System.out.println("Введите, пожалуйста, данные по домашнему животному, которого желаете удалить из базы.");
                                Scanner sc17 = new Scanner(System.in);
                                System.out.print("арi ключ : ");
                                String apiKey = sc17.nextLine();
                                System.out.print("id домашнего животного : ");
                                long idPet17 = sc17.nextLong();
                                int result = CommonUtilities.deleteObject("pet", idPet17);
                        }

                    } while (choicePet != 8);
                    break;
                case 2:
                    int choiceStore;
                    do {
                        menuService.get("Store").printMenu();
                        choiceStore = menuService.get("Store").makeChoice();
                        switch (choiceStore) {
                            case 1:
                                /*
                                System.out.print("Введите id :");
                                Scanner sc21 = new Scanner(System.in);
                                long id = sc21.nextLong();
                                try {
                                    Pet pet = PetService.getPetByID("pet", id);
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                */
                                break;
                            case 2:
                                System.out.println("Обратите внимание, что номер заказа должен быть в пределах от 1 до 10. иначе будет выброшена ошибка ввода.");
                                System.out.print("Введите id :");
                                Scanner sc22 = new Scanner(System.in);
                                long orderId22 = sc22.nextLong();
                                try {
                                    if (CommonUtilities.isObjectExist("store/order", orderId22) == 200) {
                                        Order order = StoreService.getOrderByID("order", orderId22);
                                        System.out.println(order);
                                    } else {
                                        System.out.println("Заказа с таким id не существует");
                                    }
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 3:
                                System.out.print("23");
                                break;
                            case 4:
                                System.out.print("24");
                        }
                    } while (choiceStore != 5);
                    break;
                case 3:
                    int choiceUser;
                    do {
                        menuService.get("User").printMenu();
                        choiceUser = menuService.get("User").makeChoice();
                        switch (choiceUser) {
                            case 1:
                                System.out.println("https://www.baeldung.com/httpclient-post-http-request");
                                break;
                            case 2:
                                System.out.print("32");
                                break;
                            case 3:
                                System.out.print("Введите userName пользователя : ");
                                Scanner sc33 = new Scanner(System.in);
                                String userName33 = sc33.nextLine();
                                try {
                                    if (CommonUtilities.isObjectExist("user", userName33) == 200) {
                                        User user = UserService.getUserByUsername(userName33);
                                        System.out.println(user);
                                    } else {
                                        System.out.println("Пользователя с таким именем не существует");
                                    }

                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 4:
                                System.out.println("Введите, пожалуйста, данные по пользователю.");
                                Scanner sc34 = new Scanner(System.in);
                                System.out.print("userName пользователя :");
                                String userName34 = sc34.nextLine();
                                System.out.print("имя пользователя :");
                                String firstName34 = sc34.nextLine();
                                System.out.print("фамилия пользователя :");
                                String lastName34 = sc34.nextLine();
                                System.out.print("e-mail пользователя :");
                                String email = sc34.nextLine();
                                System.out.print("телефон пользователя :");
                                String phone = sc34.nextLine();
                                System.out.print("статус пользователя :");
                                int userStatus = sc34.nextInt();
                                User newUser = User.builder().
                                        username("Test").
                                        firstName("ivan").
                                        lastName("Ivanov").
                                        email("test@test.com").
                                        password("test1").
                                        phone("056-123-45-67").
                                        userStatus(userStatus).
                                        build();
                                try {
                                    CommonUtilities.createNewObject("user", newUser);
                                    //if ()
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 5:
                                System.out.print("35");
                                break;
                            case 6:
                                System.out.print("36");
                                break;
                            case 7:
                                System.out.print("37");
                        }
                    } while (choiceUser != 8);
                    break;

            }
        } while (choice != 4);
    }
}
