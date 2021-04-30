package datastructure.heap;

public class MinHeap {
    // default datastructure.heap capacity
    private static final int DEFAULT_CAPACITY = 10;
    // the numbers of datastructure.heap elements
    private int currentSize;
    // store datastructure.heap elements
    private int[] arrays;

    public MinHeap() {
        arrays = new int[DEFAULT_CAPACITY];
    }

    public MinHeap(int capacity) {
        arrays = new int[capacity];
    }

    public MinHeap(int[] items) {
        currentSize = items.length;
        arrays = new int[currentSize + 1];

        // datastructure.heap element started at index 1.
        int i = 1;
        for (int item : items) {
            arrays[i++] = item;
        }
        buildHeap();
    }

    public void insert(int value) {
        if (currentSize == arrays.length - 1) {
            lagerCapacity(currentSize * 2 + 1);
        }
        int hole = ++currentSize;
        for (arrays[0] = value; value - arrays[hole / 2] < 0; hole /= 2) {
            arrays[hole] = arrays[hole / 2];
        }
        arrays[hole] = value;
    }

    public int findMin() {
        return arrays[1];
    }

    public int deleteMin() {
        if (isEmpty()) {
            throw new UnderflowException("empty datastructure.heap");
        }
        int min = findMin();
        // put the last element to the top datastructure.heap
        arrays[1] = arrays[currentSize--];
        percolateDown(1);

        return min;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        for (int item : arrays) {
            item = 0;
        }
    }

    private void percolateDown(int hole) {
        int child;
        int tmp = arrays[hole];

        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize && (arrays[child + 1] < arrays[child])) {
                child++;
            }
            if (arrays[child] < tmp) {
                arrays[hole] = arrays[child];
            } else {
                break;
            }
        }
        arrays[hole] = tmp;
    }

    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    private void lagerCapacity(int newSize) {
        int[] old = arrays;
        arrays = new int[newSize];
        System.arraycopy(old, 1, arrays, 1, currentSize);
    }

    public static void main(String[] args) {
        int[] nonSorted = { 150, 80, 40, 30, 10, 70, 110, 100, 20, 90, 60, 50, 120, 140, 130, 100};
        MinHeap minHeap = new MinHeap(nonSorted);
        System.out.println(minHeap.deleteMin()); // 10
        System.out.println(minHeap.deleteMin()); // 20
        System.out.println(minHeap.deleteMin()); // 30
        minHeap.insert(45);
        System.out.println(minHeap.deleteMin()); // 40
    }
}