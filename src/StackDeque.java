import java.util.Stack;

public class StackDeque {
    private Stack<Integer> fStack = new Stack<>();
    private Stack<Integer> sStack = new Stack<>();

    public void enqueue(int item){
        fStack.push(item);
    }

    public int dequeue(){
        if (isEmpty()) throw new IllegalStateException();
        moveStack1ToStack2();
        return sStack.pop();
    }

    public int peek(){
        if (fStack.empty() && sStack.empty())
            throw new IllegalStateException();

        moveStack1ToStack2();
        return sStack.peek();
    }

    public boolean isEmpty(){
        return fStack.empty() && sStack.empty();
    }

    private void moveStack1ToStack2(){
        if(sStack.empty())
            while (!fStack.empty()) sStack.push(fStack.pop());
    }
}