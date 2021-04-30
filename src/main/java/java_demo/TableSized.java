package java_demo;

import java.util.ArrayList;
import java.util.TreeMap;

public class TableSized {
    public static int forTableSize(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    public static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) {
//        int cap = 11;
//        System.out.println(forTableSize(cap));
//
//        int cap1 = 17;
//        System.out.println(forTableSize(cap1));
//
//        int cap2 = 3;
//        System.out.println(forTableSize(cap2));
//
//        int cap3 = 6;
//        System.out.println(forTableSize(cap3));
//
//        int n = 3;
//        int m = 10;
//        System.out.println(n % m);
//        System.out.println();
        int oldCap = 16;
        for (int i = 0; i < 21; i++) {
            int hash = hash(i);
            int result = (oldCap & i);
            System.out.println("i: " + hash + ", result: " +  result);
        }


    }
}
