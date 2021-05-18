package leetcode.system_design.animal.pet;

public class Cat extends Pet {
    public Cat(String name, int seq) {
        this.setName(name);
        this.setSeq(seq);
    }

    public Cat() {
    }

    @Override
    public void feed() {
        System.out.println("喂养猫");
    }
}
