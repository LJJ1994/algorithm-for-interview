package java_demo.generic;

import java.util.ArrayList;
import java.util.List;

public class CatDemo {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        List<JiaFeiCat> jiaFeiCats = new ArrayList<>();

        animals.add(new Animal());
        cats.add(new Cat());
        jiaFeiCats.add(new JiaFeiCat());

        // List<? extends Cat> extendsCatFromAnimal = animals; // 无法编译
        List<? super Cat> superCatFromAnimal = animals;

        List<? extends Cat> extendsCatFromCat = cats;
        List<? super Cat> superCatFromCat = cats;

        List<? extends Cat> extendsCatFromJIAFEI = jiaFeiCats;
        // List<? super Cat> superCatFromJIAFEI = jiaFeiCats; // 无法编译

        // 只能读取，不能添加，均无法编译
//        extendsCatFromCat.add(new Animal());
//        extendsCatFromCat.add(new Cat());
//        extendsCatFromCat.add(new JiaFeiCat());

        // 只能添加T的当前类及其子类
        // superCatFromCat.add(new Animal()); // 无法编译
        superCatFromCat.add(new Cat());
        superCatFromCat.add(new JiaFeiCat());

        extendsCatFromJIAFEI.get(0);
    }
}
