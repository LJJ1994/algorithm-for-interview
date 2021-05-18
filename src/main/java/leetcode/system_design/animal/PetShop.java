package leetcode.system_design.animal;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import leetcode.system_design.animal.pet.Bird;
import leetcode.system_design.animal.pet.Cat;
import leetcode.system_design.animal.pet.Dog;
import leetcode.system_design.animal.pet.Pet;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PetShop {
    private Pet[] pets;
    private final int MAX_CAPACITY = 10;
    private int capacity;
    private int counter = 0;
    public PetShop(int capacity) {
        if (capacity > MAX_CAPACITY) {
            this.capacity = MAX_CAPACITY;
        }
        this.capacity = capacity;
        pets = new Pet[this.capacity];
    }

    // 寄养宠物
    public boolean carePet(int num, int days) {
        if (num < 1 || num > 3) {
            return false;
        }
        Date now = new Date();
        Calendar instance = new GregorianCalendar();
        instance.setTime(now);
        instance.add(Calendar.DAY_OF_MONTH, days);

        if (num == PetType.DOG.getSeq()) {
            // 1 dog
            String name = this.counter + " " + PetType.DOG.getName();
            Dog dog = new Dog(name, counter);
            dog.setEnd(instance.getTime());

            pets[counter] = dog;
            counter++;
        } else if (num == PetType.CAT.getSeq()) {
            String name = this.counter + " " + PetType.CAT.getName();
            Cat cat = new Cat(name, counter);
            cat.setEnd(instance.getTime());
            pets[counter] = cat;
            counter++;
        } else if (num == PetType.BIRD.getSeq()) {
            String name = this.counter + " " + PetType.BIRD.getName();
            Bird bird = new Bird(name, counter);
            bird.setEnd(instance.getTime());

            pets[counter] = bird;
            counter++;
        } else {
            System.out.println("不支持寄养该类别宠物，序号：" + num);
            return false;
        }
        return true;
    }

    // 根据名称查询宠物
    // @Param name: seq name, 如2 dog
    public void search(String name) {
        String[] strings = name.split(" ");
        String seq = strings[0];
        String regx = "\\d+";
        if (!seq.matches(regx)) {
            System.out.println("请输入合法的宠物名字，格式为: 序号 + 空格 + 宠物名字，如:1 dog");
            return;
        }
        if ("".equals(strings[1]) || strings[1] == null) {
            System.out.println("宠物名不能为空");
            return;
        }
        int seqParse = Integer.parseInt(seq);
        for (Pet pet : pets) {
            if (pet == null) continue;
            if (pet.getSeq() == seqParse && pet.getName().equals(strings[1])) {
                System.out.println("宠物类别: " + pet.getName() + ", " + "宠物序号: " + pet.getSeq());
            }
        }
        System.out.println("没有找到该宠物");
    }

    // 根据类型输出宠物信息
    public void printPetInfo(String type) {
        for (Pet pet : pets) {
            if (pet == null) continue;
            String[] s = pet.getName().split(" ");
            if (s[1].equals(type)) {
                System.out.println("序号：" + pet.getSeq() + "，名称：" + pet.getName());
            }
        }
    }

    // 根据寄养序号输出宠物信息
    public void printPetInfo(int seq) {
        for (Pet pet : pets) {
            // 为什么要有这个判断？因为用数组存储，全部初始化为null，碰到Null就跳过
            if (pet == null) continue;
            if (pet.getSeq() == seq) {
                System.out.println("序号：" + pet.getSeq() + "，名称：" + pet.getName());
            }
        }
    }

    // 喂养宠物
    public void feed(Pet pet) {
        pet.feed();
    }

    // 根据对象实例子输出某个宠物的信息
    public void printOnePet(Pet pet) {
        if (pet == null) {
            System.out.println("无效的宠物信息");
            return;
        }
        if (pet instanceof Dog) {
            System.out.println(pet.toString());
        } else if (pet instanceof Cat) {
            System.out.println(pet.toString());
        } else if (pet instanceof Bird) {
            System.out.println(pet.toString());
        } else {
            System.out.println("无该宠物信息");
        }
    }

    // 领取宠物
    public Pet take(Pet pet) {
        TakePet takePet = new TakePet(this);
        return takePet.take(pet);
    }

    public Pet[] getPets() {
        return pets;
    }

}
