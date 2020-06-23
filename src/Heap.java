import java.util.Arrays;

public class Heap{

    private int arr[];
    private int count;

    public Heap(int initialLength) {
        arr = new int[initialLength];
        count = 0;
    }

    public Heap () {
        arr = new int[10];
        count = 0;
    }

    public void insert(int value) {
        if (count == arr.length)
            arr = Arrays.copyOf(arr, arr.length * 2);

        arr[count++] = value;
        insertP(count - 1, ((count - 2) / 2));
    }

    private void insertP(int currentIndex, int parentIndex) {
        if (currentIndex <= 0 || arr[currentIndex] < arr[parentIndex])
            return;

        swap(currentIndex, parentIndex);
        insertP(parentIndex, ((parentIndex - 1) / 2));
    }

    public void remove() {
        if (count == 0)
            throw new IllegalStateException();

        arr[0] = arr[--count];
        remove(0);
    }

    private void remove(int parentIndex) {
        if (parentIndex * 2 + 1 < count && arr[parentIndex * 2 + 1] > arr[parentIndex]) {
            swap(parentIndex, parentIndex * 2 + 1);
            remove(parentIndex * 2 + 1);
        }

        else if (parentIndex * 2 + 2 < count && arr[parentIndex * 2 + 2] > arr[parentIndex]) {
            swap(parentIndex, parentIndex * 2 + 2);
            remove(parentIndex * 2 + 2);
        }

    }

    void swap(int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public void printHeap() {
        System.out.print("[");
        for (int i = 0; i < count; i++)
            System.out.print(arr[i] + (i != count - 1 ? ", " : ""));

        System.out.println("]");
    }
}
