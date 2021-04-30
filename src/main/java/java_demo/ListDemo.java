package java_demo;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add("2");
        list.add(new Object());
        for (Object o : list) {
            System.out.println(o);
        }

        List<Object> list1 = list;
        for (Object o : list1) {
            System.out.println(o);
        }

    }
}
