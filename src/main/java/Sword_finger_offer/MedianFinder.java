package Sword_finger_offer;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {
    private final PriorityQueue<Integer> min;
    private final PriorityQueue<Integer> max;
    /** initialize your data structure here. */
    public MedianFinder() {
        min = new PriorityQueue<>();
        max = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }

    public void addNum(int num) {
        // 总数目为偶数，将num插入最小堆
        if (((min.size() + max.size()) & 1) == 0) {
            if (max.size() > 0 && num < max.peek()) {
                max.offer(num);
                num = max.poll();
            }
            min.offer(num);
        } else { // 总数目为奇数，插入最大堆
            if (min.size() > 0 && num > min.peek()) {
                min.offer(num);
                num = min.poll();
            }
            max.offer(num);
        }
    }

    public double findMedian() {
        int size = min.size() + max.size();
        if (size == 0) {
            throw new RuntimeException("No numbers are available");
        }
        double median;
        if ((size & 1) == 1) {
            median = min.peek();
        } else {
            median = (min.peek() + max.peek()) / 2.0;
        }
        return median;
    }

    public static void main(String[] args) {
        double a = 2.0, b = 3.0;
        System.out.println((a + b) / 2);
    }
}
