
package datastructure.sorts;

import java.util.Arrays;

public class CountingSort {
    public static int[] sort(int[] A) {
        int k = 100;
        int[] B = new int[A.length];

        sort(A,B, k);

        // output array
        return B;
    }

    public static void sort(int[] A, int[] B, int k) {
        // temp array
        int[] C = new int[k];
        for (int i = 0; i < A.length; i++) {
            int a = A[i];
            C[a] += 1;
        }

        for (int i = 1; i < k; i++) {
            C[i] = C[i] + C[i - 1];
        }

        for (int j = A.length - 1; j >= 0 ; j--) {
            int a = A[j];
            B[C[a] - 1] = a;
            C[a] -= 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
        int[] sort = sort(arr);
        System.out.println(Arrays.toString(sort));

    }
}
