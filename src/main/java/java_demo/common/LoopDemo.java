package java_demo.common;

import java.util.*;
import java.util.function.BiConsumer;

public class LoopDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
//        for (Integer i : list) {
//            if (2 == i) {
//                list.remove(i);
//            }
//        }

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 2) {
                iterator.remove();
            }
        }

        System.out.println(list);

        Map<String, String> map = new HashMap<>();
        map.put("name", "ljj");
        map.put("age", "18");
        map.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String key, String value) {
                System.out.println("key: " + key);
                System.out.println("value: " + value);
                System.out.println("===================");
            }
        });
    }
}
