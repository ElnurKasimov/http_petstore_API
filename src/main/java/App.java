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
                                System.out.print("id домашнего животного :");
                                Scanner sc13 = new Scanner(System.in);
                                long idPet = sc13.nextLong();
                                System.out.print("кличка :");
                                String petName13 = sc13.nextLine();
                                if(petName13.equals("")) petName13 = sc13.nextLine();
                                System.out.print("id категории :");
                                long idCategory = sc13.nextLong();
                                System.out.print("название категории :");
                                String nameCategory = sc13.nextLine();
                                if(nameCategory.equals("")) nameCategory= sc13.nextLine();
                                System.out.print("ссылка на фотографию :");
                                String photoUrl = sc13.nextLine();
                                ArrayList<String> photoUrls = new ArrayList<>();
                                photoUrls.add(photoUrl);
                                System.out.print("id тэга :");
                                long idTag = sc13.nextLong();
                                System.out.print("название тэга :");
                                String nameTag = sc13.nextLine();
                                if(nameTag.equals("")) nameTag = sc13.nextLine();
                                Tag tag = Tag.builder().id(idTag).name(nameTag).build();
                                ArrayList<Tag> tags = new ArrayList<>();
                                tags.add(tag);
                                System.out.print("статус домашнего животного (available,pending,sold) :");
                                String petStatus = sc13.nextLine();

                                Pet newPet = Pet.builder().
                                        id(idPet).
                                        category(Category.builder().id(idCategory).name(nameCategory).build()).
                                        name(nameTag).photoUrls(photoUrls).
                                        tags(tags).
                                        petStatus(Pet.PetStatus.valueOf(petStatus)).
                                        build();
                                try {
                                    CommonUtilities.createNewObject("pet", newPet);

                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 4:
                                System.out.print("id домашнего животного :");
                                Scanner sc14 = new Scanner(System.in);
                                long idPet14 = sc14.nextLong();
                                System.out.print("Введите дополнительные данные для передачи на сервер : ");
                                String additionalMetedata = sc14.nextLine();
                                if( additionalMetedata.equals("")) additionalMetedata = sc14.nextLine();
                                System.out.print("Для того, чтобы загрузить картинку, поместите файл в корневой каталог программы" +
                                        " и укажите его имя (имя.расширение) : ");
                                String photoUrl14 = sc14.nextLine();
                                try {
                                    if(PetService.addPhotoToPet(idPet14, photoUrl14) == 200) {
                                        System.out.println("картинка успешно добавлена");
                                        Pet pet = PetService.getPetByID(idPet14);
                                        System.out.println(pet.getPhotoUrls().toString());
                                        pet.getPhotoUrls().add(photoUrl14);
                                        System.out.println(pet.getPhotoUrls().toString());
                                        //todo вызвать метод update
                                    };
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }



                                break;
                            case 5:
                                System.out.print("15");
                                break;
                            case 6:
                                System.out.print("16");
                                break;
                            case 7:
                                System.out.print("17");
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
                                System.out.println("31");
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
