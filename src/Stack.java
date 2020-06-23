import java.util.Arrays;

public class Stack <T>{
    private Object[] arr;
    private int count;

    public Stack() {
        arr = new Object[10];
        count = 0;
    }

    public Stack(int initialLength) {
        arr = new Object[initialLength];
        count = 0;
    }

    public void push(T item){
        if (item == null)
            return;

        if(arr.length == count)
            arr = Arrays.copyOf(arr, arr.length * 2);

        arr[count++] = item;
    }

    public T pop(){
        emptyStack();

        return (T) arr[--count];
    }

    public T peek(){
        emptyStack();

        return (T) arr[count - 1];
    }

    public boolean isEmpty(){
        return count == 0;
    }

    private void emptyStack(){
        if(isEmpty())
            throw new IllegalArgumentException();

    }
}