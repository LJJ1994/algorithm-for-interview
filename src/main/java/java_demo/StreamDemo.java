package java_demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        List<String> results = Arrays.asList("hello", "", "world", "!", "");
        results.stream().filter(item -> !"".equals(item)).forEach(System.out::println);

        List<Integer> lists = Arrays.asList(2, 1, 4, 3, 5);
//        lists.stream().map(i -> i * i).limit(2).forEach(System.out::println);
        lists.stream().map(i -> i * i).skip(2).forEach(System.out::println);
        System.out.println("====================");
        lists.stream().sorted().forEach(System.out::println);


        System.out.println("====================");

        List<Integer> list = Arrays.asList(1, 1, 3, 4, 2, 2, 4);
        list.stream().distinct().forEach(System.out::println);

        System.out.println("************************");

        List<String> str1 = Arrays.asList("hello", "hello", "world", "how", "you", "however", "dare");
//        Stream<Integer> stream = str1.stream().filter(s -> s.length() >= 3).map(String::length).sorted().distinct();
//        stream.forEach(System.out::println);
//        System.out.println(stream.count());
        List<String> h = str1.stream().filter(s -> s.startsWith("h")).distinct().collect(Collectors.toList());
        System.out.println(h);


    }
}
