package java_demo;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoader loader = ClassLoaderDemo.class.getClassLoader();
        ClassLoader parent = loader.getParent();
        ClassLoader parent1 = parent.getParent();
        System.out.println(loader.toString());
        System.out.println(parent.toString());
        System.out.println(parent1.toString());

    }
}
