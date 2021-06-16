package java_demo.common;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class GenericDemo {
    public static void testGen(List<? extends Fruit> list) {
//        list.add(new Apple());
        for (Fruit fruit : list) {
            System.out.println(fruit);
        }
    }

    // PECS ==> Producer-extends Consumer-super
    public static void testGen1(List<? super Fruit> list) {
        list.add(new Apple());
        for (Object o : list) {

        }
    }

    public static void main(String[] args) {
        String s = "hello";
        HashFunction hashFunction = Hashing.murmur3_32();
        HashCode hashCode = hashFunction.hashBytes(s.getBytes(StandardCharsets.UTF_8));
        System.out.println(hashCode);

    }
}
