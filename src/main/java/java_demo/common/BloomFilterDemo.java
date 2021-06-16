package java_demo.common;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 有一千万个随机整数，随机落在1到亿之间，现在要求写一个算法，判断给定的某个数是否在这一千万个整数里？
 */
public class BloomFilterDemo {
    private static final int COUNT_INTEGERS = 100 * 10000;
    private static final int COUNT_RANGES = 10000 * 10000;
    public static boolean testBitSet(int n) {
        List<Integer> list = new ArrayList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < COUNT_INTEGERS; i++) {
            list.add(random.nextInt(COUNT_RANGES));
        }
        BitSet bitSet = new BitSet(COUNT_RANGES);
        for (int i = 0; i < COUNT_INTEGERS; i++) {
            bitSet.set(list.get(i));
        }

        return bitSet.get(n);
    }

    public static boolean testBloomFilter(Integer n) {
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), COUNT_RANGES, 0.1);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < COUNT_INTEGERS; i++) {
            bloomFilter.put(random.nextInt(COUNT_INTEGERS));
        }
        boolean b = bloomFilter.mightContain(n);
        return b;
    }

    public static void main(String[] args) {
//        int n = 1001;
//        System.out.println(testBloomFilter(n));
//        System.out.println(testBitSet(n));
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            System.out.print(random.nextInt(10000) + ", ");
        }
    }
}
