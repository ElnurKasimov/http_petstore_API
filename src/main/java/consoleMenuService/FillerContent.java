package consoleMenuService;

import java.util.HashMap;

public class FillerContent {

    public HashMap<Integer, String> fill(String name) {
        HashMap<Integer, String> contentMenu = new HashMap<>();
        switch(name) {
            case "Main":
                contentMenu.put(1, "Pet");
                contentMenu.put(2, "Store");
                contentMenu.put(3, "User");
                contentMenu.put(4, "Выйти из программы");
                break;
            case "Pet":
                contentMenu.put(1, "найти домашнее животное по id");
                contentMenu.put(2, "найти домашних животных по статусу" );
                contentMenu.put(3, "добавить домашнее животное на склад");
                contentMenu.put(4, "загрузить фото домашнего животного");
                contentMenu.put(5, "обновить данные по домашнему животному c помощью формы данных");
                contentMenu.put(6, "обновить данные по существующему в базе домашнему животному");
                contentMenu.put(7, "удалить домашнее животное");
                contentMenu.put(8, "перейти в верхнее меню");
                break;
            case "Store":
                contentMenu.put(1, "получить инвентаризацию домашних животных по статусу");
                contentMenu.put(2, "найти заказ на покупку по id");
                contentMenu.put(3, "разместить заказ на покупку");
                contentMenu.put(4, "удалить заказ на покупку по id");
                contentMenu.put(5, "перейти в верхнее меню");
                break;
            case "User":
                contentMenu.put(1, "зарегистрировать пользователя в системе");
                contentMenu.put(2, "вывести из текущей сессии зарегистрированного пользователя" );
                contentMenu.put(3, "найти пользователя по имени");
                contentMenu.put(4, "добавить пользователя");
                contentMenu.put(5, "добавить пользователей по предоставленному списку");
                contentMenu.put(6, "обновить данные по существующему пользователю");
                contentMenu.put(7, "удалить пользователя");
                contentMenu.put(8, "перейти в верхнее меню");
        }
        return contentMenu;
    }
}
