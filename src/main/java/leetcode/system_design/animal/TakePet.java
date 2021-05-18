package leetcode.system_design.animal;

import leetcode.system_design.animal.pet.Pet;

import java.util.Date;

public class TakePet {
    private PetShop petShop;
    public TakePet(PetShop petShop) {
        this.petShop = petShop;
    }

    public Pet take(Pet pet) {
        System.out.println("Care time out.please take " + pet.getName() + "out.");
        Pet[] pets  = petShop.getPets();
        Date now = new Date();
        if (now.before(pet.getEnd())) {
            System.out.println("寄养时间未到");
            return null;
        } else {
            System.out.println("已过寄养时间，可领走。");
            for (int i = 0; i < pets.length; i++) {
                if (pets[i].getSeq() == pet.getSeq() && pets[i].getName().equals(pet.getName())) {
                    pets[i] = null;
                    return pet;
                }
            }
        }
        return null;
    }
}
