
package datastructure.sorts;

import java.util.Arrays;

/**
 * Heap sort, based on Max datastructure.heap.
 */
public class HeapSort {

    public void sort(int[] arr) {
        // build datastructure.heap
        for (int i = arr.length / 2 - 1; i >= 0 ; i--) {
            perDown(arr, i, arr.length);
        }

        // delete Max from datastructure.heap util remain last element,
        // put the last element arr[i] to top index,
        // and put the max element to the tail of the Array
        for (int i = arr.length - 1; i > 0 ; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;

            perDown(arr, 0, i);
        }
    }

    /**
     *
     * @param arr
     * @param hole the position which to percolate down
     * @param n the logical size of datastructure.heap
     */
    private void perDown(int[] arr, int hole, int n) {
        int tmp = arr[hole];
        int child;
        for (; leftChild(hole) < n; hole = child ) {
            child = leftChild(hole);
            // compare left right child.
            if (child != (n - 1) && arr[child] < arr[child + 1]) {
                child++;
            }
            // compare arr[hole] and arr[child]
            if (tmp < arr[child]) {
                arr[hole] = arr[child];
            }else {
                break;
            }
        }
        arr[hole] = tmp;
    }

    /**
     * the datastructure.heap started at index 0
     * @param i parent index
     * @return left-child index
     */
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    public static void main(String[] args) {
        int[] arr = {31, 41, 59, 26, 53, 58, 97};
        HeapSort heapSort = new HeapSort();
        heapSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}