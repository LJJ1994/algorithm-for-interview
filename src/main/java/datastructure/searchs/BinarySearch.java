
package datastructure.searchs;

public class BinarySearch {
    /**
     * non-recursive Binary Search
     * @param arr Array source
     * @param value the value of searching
     * @return index of value
     */
    public static int search(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Recursive Binary search
     * @param arr the Array source
     * @param low start position of Array
     * @param high end position of Array
     * @param value for searching value
     * @return index of value
     */
    public static int searchRecursive(int[] arr, int low, int high, int value) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (arr[mid] == value) {
            return mid;
        } else if (arr[mid] < value) {
            return searchRecursive(arr, mid + 1, high, value);
        } else {
            return searchRecursive(arr, low, mid - 1, value);
        }
    }

    /**
     *  Find the element whose first value is equal to the given value.
     * @param arr Array source.
     * @param value giving value for search.
     * @return
     */
    public static int searchForFirstFounded(int[] arr, int value) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == 0) || (arr[mid - 1] != value)) return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Find the element whose last value is equal to the given value
     * @param arr Array source
     * @param value giving value for searching
     * @return index of value
     */
    public static int searchForLastFounded(int[] arr, int value) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == n - 1) || (arr[mid + 1] != value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Find the first element greater than or equal to a given value
     * @param arr Array source
     * @param value giving value for searching
     * @return index of value
     */
    public static int searchFirstEqualOrGreaterElement(int[] arr, int value) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] >= value) {
                if ((mid == 0) || (arr[mid - 1] < value)) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Find the last element less than or equal to a given value
     * @param arr Array source
     * @param value giving value for searching
     * @return index of value
     */
    public static int searchLastLessThanOrEqualsElement(int[] arr, int value) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] <= value) {
                if ((mid == n - 1) || (arr[mid + 1] > value)) return mid;
                else low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 8, 8, 10, 11, 11, 14, 15};
//        System.out.println(search(arr, 6));
//        System.out.println(search(arr, 20));

//        System.out.println(searchRecursive(arr, 0, arr.length - 1, 6));
//        System.out.println(searchRecursive(arr, 0, arr.length - 1, 20));

//        System.out.println(searchForFirstFounded(arr, 8));

//        System.out.println(searchForLastFounded(arr, 8));
//        System.out.println(searchForLastFounded(arr, 11));

//        System.out.println(searchFirstEqualOrGreaterElement(arr, 9));
//        System.out.println(searchFirstEqualOrGreaterElement(arr, 12));

        System.out.println(searchLastLessThanOrEqualsElement(arr, 12));
        System.out.println(searchLastLessThanOrEqualsElement(arr, 9));
        System.out.println(searchLastLessThanOrEqualsElement(arr, 11));
        System.out.println(searchLastLessThanOrEqualsElement(arr, 16));
    }
}