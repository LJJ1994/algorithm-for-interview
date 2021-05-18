package leetcode.system_design.animal;

import leetcode.system_design.animal.pet.Bird;
import leetcode.system_design.animal.pet.Dog;
import leetcode.system_design.animal.pet.Pet;

public class PetShopTest {
    public static void main(String[] args) {
        PetShop petShop = new PetShop(20);
        petShop.carePet(1, 8);
        petShop.carePet(2, 5);
        petShop.carePet(3, 11);
        petShop.carePet(1, 10);
//        for (Pet pet : petShop.getPets()) {
//            System.out.println(pet);
//        }

        petShop.printPetInfo(1);
        petShop.printPetInfo("dog");
        petShop.feed(new Dog());
//        petShop.printOnePet(new Bird());
        petShop.search("1 cat");
    }
}
