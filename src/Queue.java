import java.util.Arrays;

public class Queue <T>{

    Object[] arr;
    int count;
    int pointer;

    public Queue(int initialLength) {
        arr = new Object[initialLength];
        count = pointer = 0;
    }

    public Queue() {
        arr = new Object[10];
        count = pointer = 0;
    }

    public void enqueue(T item) {
        if (count == arr.length)
            arr = Arrays.copyOf(Arrays.copyOfRange(arr, pointer, count), arr.length * 2);


        arr[count++] = item;
    }

    public T dequeue() {
        if (isEmpty())
            throw new IllegalStateException();

        return (T) arr[pointer++];
    }
    public T peek() {
        if (isEmpty())
            throw new IllegalStateException();

        return (T) arr[pointer];
    }

    public int getLength() {
        return count - pointer;
    }

    public boolean isEmpty() {
        return count == pointer;
    }
}
