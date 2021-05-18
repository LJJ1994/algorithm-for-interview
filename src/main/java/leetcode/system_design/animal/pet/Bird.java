package leetcode.system_design.animal.pet;

public class Bird extends Pet{

    public Bird(String name, int seq) {
        this.setName(name);
        this.setSeq(seq);
    }
    public Bird() {
    }

    @Override
    public void feed() {
        System.out.println("喂养鸟");
    }
}
