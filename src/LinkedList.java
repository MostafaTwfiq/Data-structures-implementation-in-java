public class LinkedList<T> {

    private class Node <F> {
        F value;
        Node<F> nextNode;

        private Node (F value) {
            this.value = value;
        }
        private Node (F value, Node nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }

        @Override
        public String toString(){
            return "Node value: " + value;
        }

    }

    private Node<T> head;
    private Node<T> tail;
    private int count;


    public void addFirst(T value){
        Node<T> tempNode = new Node<>(value, head);

        if (isEmpty())
            tail = tempNode;

        head = tempNode;
        count++;
    }

    public void addLast(T value){
        Node<T> tempNode = new Node<>(value, null);

        if (isEmpty()) {
            tail = head = tempNode;
            count++;
            return;
        }

        tail.nextNode = tempNode;
        tail = tempNode;
        count++;
    }

    public void removeFirst(){
        if (isEmpty())
            throw new IllegalStateException();

        Node<T> nodeToRemove = head;
        head = head.nextNode;

        if (head == null)
            tail = null;

        nodeToRemove.nextNode = null;
        count--;
    }

    public void removeLast(){
        if (isEmpty())
            throw new IllegalStateException();

        Node<T> currentNode = head;
        Node<T> prevNode = null;

        while (currentNode.nextNode != null) {
            prevNode = currentNode;
            currentNode = currentNode.nextNode;
        }

        if (prevNode == null)
            head = tail = null;
        else {
            tail = prevNode;
            tail.nextNode = null;
        }

        count--;
    }

    public void removeByIndex(int index){
        if (index < 0 || index >= getLength())
            throw new IndexOutOfBoundsException();

        Node<T> currentNode = head;
        Node<T> prevNode = null;

        for (int i = 0; i < index; i++) {
            prevNode = currentNode;
            currentNode = currentNode.nextNode;
        }

        if (prevNode == null)
            removeFirst();
        else if (currentNode.nextNode == null)
            removeLast();
        else {
            prevNode.nextNode = currentNode.nextNode;
            currentNode.nextNode = null;
        }

        count--;
    }

    public T get(int index){
        if (index < 0 || index >= getLength())
            throw new IndexOutOfBoundsException();

        Node<T> currentNode = head;

        for (int i = 0; i < index; i++)
            currentNode = currentNode.nextNode;

        return currentNode.value;
    }

    public boolean contains(T object) {
        if (object == null)
            throw new NullPointerException();

        Node<T> currentNode = head;

        while (currentNode != null) {
            if (currentNode.equals(object))
                return true;

            currentNode = currentNode.nextNode;
        }

        return false;
    }

    public int IndexOf(T object) {
        if (object == null)
            throw new NullPointerException();

        Node<T> currentNode = head;
        int index = 0;

        while (currentNode != null) {
            if (currentNode.equals(object))
                return index;

            currentNode = currentNode.nextNode;
            index++;

        }

        return -1;
    }

    public Object[] toArray() {
        int length = getLength();

        Object[] array = new Object[length];

        Node<T> currentNode = head;
        for (int i = 0; i < length; i++) {
            array[i] = currentNode.value;
            currentNode = currentNode.nextNode;
        }

        return array;

    }

    public T[] toArray(T[] arr) {
        int length = getLength();

        if (arr.length < length)
            arr = (T[])java.lang.reflect.Array.newInstance(arr.getClass().getComponentType(), length);

        Object[] array = arr;

        Node<T> currentNode = head;
        for (int i = 0; i < length; i++) {
            array[i] = currentNode.value;
            currentNode = currentNode.nextNode;
        }

        if (arr.length > length)
            arr[length] = null;

        return arr;

    }

    public void reverse(){
        if (isEmpty())
            throw new IllegalStateException();

        else if (count == 1)
            return;

        else if (count == 2) {
            head.nextNode.nextNode = head;
            head.nextNode = null;
            Node<T> tempNode = head;
            tail = head;
            head = tempNode;
        }

        Node<T> firstNode = head, secondNode = head.nextNode, thirdNode = head.nextNode.nextNode;

        while (thirdNode.nextNode != null) {
            secondNode.nextNode = firstNode;
            firstNode = secondNode;
            secondNode = thirdNode;
            thirdNode = thirdNode.nextNode;
        }

        secondNode.nextNode = firstNode;
        thirdNode.nextNode = secondNode;
        Node<T> tempNode = tail;
        tail = head;
        head = tempNode;
        tail.nextNode = null;

    }

    public int getLength(){
        return count;
    }

    public boolean isEmpty(){
        return head == null && tail == null;
    }
}
