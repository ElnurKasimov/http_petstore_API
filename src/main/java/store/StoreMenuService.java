package store;

import com.google.gson.Gson;
import httpUtilities.CommonUtilities;

import java.util.Scanner;

public class StoreMenuService {
    public static void findOrderById() {
        System.out.println("Обратите внимание, что номер заказа должен быть в пределах от 1 до 10. иначе будет выброшена ошибка ввода.");
        System.out.print("Введите id : ");
        Scanner sc = new Scanner(System.in);
        long orderId = sc.nextLong();
        try {
            if (CommonUtilities.isObjectExist("pet/order", orderId) == 200) {
                Order order = StoreHttpService.getOrderByID("store", orderId);
                Gson gson = new Gson();
                System.out.println(gson.toJson(order));
            } else {
                System.out.println("Заказа с таким id не существует");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
        return Order.builder().
                id(idPet).
                petId(petId).
                quantity(quantity).
                shipDate(shipDate).
                STATUS(Order.Status.valueOf(status)).
                complete("true").
                build();
    }
    public static void placeOrder() {
        System.out.println("Введите, пожалуйста, данные по заказу, который надо разместить.");
        Order newOrder = inputAllDataOfOrder();
        try {
            CommonUtilities.createNewObject("pet/order", newOrder);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteOrder() {
        System.out.print("Введите, пожалуйста, данные по заказу, который желаете удалить из базы.");
        Scanner sc = new Scanner(System.in);
        System.out.print("id домашнего животного (1 - 9) : ");
        long orderId = sc.nextLong();
        try {
            CommonUtilities.deleteObject("pet/order/" + orderId, StoreHttpService.getOrderByID("store", orderId));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
