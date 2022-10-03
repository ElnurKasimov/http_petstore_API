package pet;

import console.MenuService;

public class PetMenuHandler {
    private MenuService menuService;
    private static final int EXIT_FROM_PET_MENU = 8;

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
                    PetConsoleService.findPetById();
                    break;
                case 2:
                    PetConsoleService.printListOfPetsByStatus();
                    break;
                case 3:
                    PetConsoleService.addPetToDatabase();
                    break;
                case 4:
                    PetConsoleService.uploadPetPhoto();
                    break;
                case 5:
                    PetConsoleService.updatePetByFormData();
                    break;
                case 6:
                    PetConsoleService.updatePetAllData();
                    break;
                case 7:
                    PetConsoleService.deletePet();
                    break;
                default:
                    System.out.println("Unappropriated number has been entered, please enter correct data.");
                    break;
            }
        } while (choicePet != EXIT_FROM_PET_MENU);
    }

}

