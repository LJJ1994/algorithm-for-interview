package leetcode.system_design.animal.pet;

import java.util.Date;

public abstract class Pet {
    private String name;
    private int seq;
    private Date end; // 寄养结束时间

    public abstract void feed();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String toString() {
        return this.seq + " " + this.name;
    }
}
