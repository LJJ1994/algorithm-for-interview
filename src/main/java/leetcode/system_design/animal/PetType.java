package leetcode.system_design.animal;

public enum PetType {
    DOG("dog", 1),
    CAT("cat", 2),
    BIRD("bird", 3);
    private int seq;
    private String name;
    private PetType(String name, int seq) {
        this.name = name;
        this.seq = seq;
    }

    public int getSeq() {
        return seq;
    }

    public String getName() {
        return name;
    }
}
