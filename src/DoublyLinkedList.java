public class DoublyLinkedList <T>{

    private class Node <F>{
        F value;
        Node<F> nextNode;
        Node<F> prevNode;

        private Node(F value, Node<F> nextNode, Node<F> prevNode) {
            this.value = value;
            this.nextNode = nextNode;
            this.prevNode = prevNode;
        }

        private Node(F value) {
            this.value = value;
            nextNode = null;
            prevNode = null;
        }

        private Node() {
            this.value = null;
            nextNode = null;
            prevNode = null;
        }

    }

    Node<T> head;
    Node<T> tail;
    int count = 0;

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value, head, null);

        if (isEmpty()) {
            head = tail = newNode;
            count++;
            return;
        }

        head.prevNode = newNode;
        head = newNode;

        count++;
    }

    public void addLast(T value) {
        Node<T> newNode = new Node<>(value, null, tail);

        if (isEmpty()) {
            head = tail = newNode;
            count++;
            return;
        }

        tail.nextNode = newNode;
        tail = newNode;

        count++;
    }

    public void deleteFirst() {
        if (isEmpty())
            throw new IllegalStateException();

        if (head == tail)
            head = tail = null;

        Node<T> tempNode = head;
        head = head.nextNode;
        head.prevNode = null;

        tempNode.nextNode = null;

        count--;
    }

    public void deleteLast() {
        if (isEmpty())
            throw new IllegalStateException();

        if (head == tail)
            head = tail = null;

        Node<T> tempNode = tail;
        tail = tail.prevNode;
        tail.nextNode = null;

        tempNode.prevNode = null;

        count--;
    }

    public void deleteElementAtIndex(int index) {
        Node<T> currentNode = head;

        for (index = index; currentNode != null && index != 0; index--)
            currentNode = currentNode.nextNode;

        if (currentNode == null)
            throw new IndexOutOfBoundsException();
        else if (currentNode == head)
            deleteFirst();
        else if (currentNode == tail)
            deleteLast();
        else {
            currentNode.prevNode.nextNode = currentNode.nextNode;
            currentNode.nextNode.prevNode = currentNode.prevNode;
            currentNode.prevNode = null;
            currentNode.nextNode = null;
        }
        count--;

    }

    public boolean contains(T value) {
        Node<T> currentNode = head;

        while (currentNode != null) {
            if (currentNode.value.equals(value))
                return true;

            currentNode = currentNode.nextNode;
        }

        return false;
    }

    public int getIndex(T value) {
        Node<T> currentNode = head;
        int index = 0;

        while (currentNode != null) {
            if (currentNode.value.equals(value))
                return index;

            currentNode = currentNode.nextNode;
            index++;

        }

        return -1;
    }

    public T get(int index) {
        Node<T> currentNode = head;

        for (index = index; currentNode != null && index != 0; index--)
            currentNode = currentNode.nextNode;

        if (currentNode == null)
            throw new IndexOutOfBoundsException();

        return currentNode.value;
    }

    public Object[] toArray() {
        Object[] array = new Object[count];

        Node<T> currentNode = head;
        for (int i = 0; i < count; i++) {
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

    public int getLength() {
        return count;
    }
    public boolean isEmpty() {
        return head == null && tail == null;
    }

}
