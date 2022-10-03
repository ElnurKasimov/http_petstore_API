package consoleMenuService;

import pet.PetMenuService;

public class PetMenuHandler {
    private MenuService menuService;
    private static final int EXIT_FROM_PET_NENU = 8;

    public PetMenuHandler(MenuService menuService) {
        this.menuService = menuService;

    }

    public void launch() {
        int choicePet;
        do {
            menuService.getMenuObjectByName("Pet").printMenu();
            choicePet = menuService.getMenuObjectByName("Pet").makeChoice();
            switch (choicePet) {
                case 1:
                    PetMenuService.findPetById();
                    break;
                case 2:
                    PetMenuService.printLIstOfPetsByStatus();
                    break;
                case 3:
                    PetMenuService.addPetToDatabase();
                    break;
                case 4:
                    PetMenuService.uploadPetPhoto();
                    break;
                case 5:
                    PetMenuService.updatePetByFormData();
                    break;
                case 6:
                    PetMenuService.updatePetAllData();
                    break;
                case 7:
                    PetMenuService.deletePet();
                    break;
                default:
                    System.out.println("Введено неверное значение - пожалуйста, повторите");
                    break;
            }
        } while (choicePet != EXIT_FROM_PET_NENU);
    }

}

