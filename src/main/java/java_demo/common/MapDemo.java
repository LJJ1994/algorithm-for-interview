package java_demo.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MapDemo {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Map<String, String> map = new HashMap<>();
//        map.put("hollis1", "hollischuang");
//        map.put("hollis2", "hollischuang");
//        map.put("hollis3", "hollischuang");
//        map.put("hollis4", "hollischuang");
//        map.put("hollis5", "hollischuang");
//        map.put("hollis6", "hollischuang");
//        map.put("hollis7", "hollischuang");
//        map.put("hollis8", "hollischuang");
//        map.put("hollis9", "hollischuang");
//        map.put("hollis10", "hollischuang");
//        map.put("hollis11", "hollischuang");
//        map.put("hollis12", "hollischuang");
//        Class<?> mapType = map.getClass();
//        Method capacity = mapType.getDeclaredMethod("capacity");
//        capacity.setAccessible(true);
//        System.out.println("capacity : " + capacity.invoke(map));
//        Field size = mapType.getDeclaredField("size");
//        size.setAccessible(true);
//        System.out.println("size: " + size.get(map));
//
//        Field threshold = mapType.getDeclaredField("threshold");
//        threshold.setAccessible(true);
//        System.out.println("threshold : " + threshold.get(map));
//        Field loadFactor = mapType.getDeclaredField("loadFactor");
//        loadFactor.setAccessible(true);
//        System.out.println("loadFactor : " + loadFactor.get(map));
//
//        map.put("hollis13", "hollischuang");
//        Method capacity1 = mapType.getDeclaredMethod("capacity");
//        capacity1.setAccessible(true);
//        System.out.println("capacity : " + capacity1.invoke(map));
//        Field size1 = mapType.getDeclaredField("size");
//        size1.setAccessible(true);
//        System.out.println("size : " + size1.get(map));
//        Field threshold1 = mapType.getDeclaredField("threshold");
//        threshold1.setAccessible(true);
//        System.out.println("threshold : " + threshold1.get(map));
//        Field loadFactor1 = mapType.getDeclaredField("loadFactor");
//        loadFactor1.setAccessible(true);
//        System.out.println("loadFactor : " + loadFactor1.get(map));
//
//        test1();
//
//        int cap = 5;
//        int firstBiggerNum = getFirstBiggerNum(cap);
//        System.out.println(firstBiggerNum);

        Map<String, String> map = new HashMap<>();
        map.put("name", "LJJ");
        map.put("age", "18");
//        String name = map.computeIfAbsent("age", new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s.toLowerCase();
//            }
//        });
//        System.out.println(name);
//        System.out.println(map.get("age"));

//        String name1 = map.computeIfPresent("name", new BiFunction<String, String, String>() {
//            @Override
//            public String apply(String s, String s2) {
//                return s2.toLowerCase(Locale.ROOT);
//            }
//        });
//        System.out.println(name1);
//        System.out.println(map.get("name"));
//        System.out.println(map.get("NAME"));

//        String name = map.compute("name", new BiFunction<String, String, String>() {
//            @Override
//            public String apply(String s, String s2) {
//                return s2.toLowerCase();
//            }
//        });
//        System.out.println(name);
//
//        String age = map.compute("age", new BiFunction<String, String, String>() {
//            @Override
//            public String apply(String s, String s2) {
//                return s2 + 3;
//            }
//        });
//        System.out.println(age);

//        String merge = map.merge("name", "hello", (s, s2) -> s + ", " + s2);
//        System.out.println(merge);
//        map.replaceAll((s, s2) -> {
//            if (s.equals("age")) {
//                int n = Integer.parseInt(s2) + 10;
//                return String.valueOf(n);
//            }
//            return s2.toLowerCase();
//        });
//        System.out.println(map.get("name"));
//        System.out.println(map.get("age"));

        map.forEach((s, s2) -> {
            if (s.equals("age")) {
                int n = Integer.parseInt(s2) + 10;
                map.put(s, String.valueOf(n));
            } else {
                map.put(s, s2.concat(" Go!"));
            }
        });
        System.out.println(map.get("name"));
        System.out.println(map.get("age"));

    }

    private static void test1() {
        String str = "abc";
        int code = str.hashCode();
        int len = 32;
        int h1 = (code & (len - 1));
        int h2 = code % len;
        System.out.println("h1: " + h1);
        System.out.println("h2: " + h2);
    }

    private static int getFirstBiggerNum(int cap) {
        int n = cap - 1;
        int MAX_CAPACITY = 1 << 30;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n < 0 ? 1 : (n > MAX_CAPACITY ? MAX_CAPACITY : n + 1);
    }
}
