package leetcode.system_design.animal.pet;

import javax.print.DocFlavor;

public class Dog extends Pet{
    public Dog(String name, int seq) {
        this.setName(name);
        this.setSeq(seq);
    }

    public Dog() {
    }

    @Override
    public void feed() {
        System.out.println("喂养狗");
    }
}
