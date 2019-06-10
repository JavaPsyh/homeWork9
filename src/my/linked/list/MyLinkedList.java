package my.linked.list;

import java.util.Objects;

public class MyLinkedList<T> implements MyList<T> {

    private Node<T> lastNode;
    private Node<T> firstNode;
    private int size = 0;

    @Override
    public void add(T value) {
        Node<T> newNode = new Node<T>(null, value, null);
        if (isEmpty()) {
            firstNode = newNode;
            lastNode = firstNode;
        } else {
            newNode.prev = lastNode;
            lastNode.next = newNode;
            lastNode = newNode;
        }
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (verificationOfIndex(index)) {
            Node<T> newNode = new Node<>(null, value, null);
            if (isEmpty() || index == size()) {
                add(value);
            } else {
                Node<T> indexNode = iterator(index);
                newNode.prev = indexNode.prev;
                newNode.next = indexNode;
                indexNode.prev.next = newNode;
                indexNode.prev = newNode;
                size++;
            }
        }
    }

    @Override
    public void addAll(MyList<T> myList) {
        for (int i = 0; myList != null && i < myList.size(); i++) {
            add(myList.get(i));
            // if (isEmpty(myList)) nothing happens and program will be finished correctly;
        }
    }

    @Override
    public T get(int index) {
        T indexValue = null;
        if (verificationOfIndex(index)) {
            indexValue = iterator(index).item;
        }
        return indexValue;
    }

    @Override
    public void set(T value, int index) {
        if (verificationOfIndex(index)) {
            iterator(index).item = value;
        }
    }

    @Override
    public T remove(int index) {
        T result = null;
        if (verificationOfIndex(index) && !isEmpty()) {
            Node<T> indexNode = iterator(index);
            result = indexNode.item;
            indexNode.prev.next = indexNode.next;
            indexNode.next.prev = indexNode.prev;
            indexNode = null;
            size--;
        }
        return result;
    }

    @Override
    public T remove(T t) {
        Node<T> temp = firstNode;
        int index = 0;
        if (!isEmpty()) {
            for (int i = 0; i < size(); i++) {
                if (temp.item.equals(t)) {
                    index = i;
                    break;
                }
                temp = temp.next;
            }
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean verificationOfIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Incorrect index!");
        }
        return true;
    }

    public Node<T> iterator(int index) {
        Node<T> result = firstNode;
        if (verificationOfIndex(index)) {
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyLinkedList<?> that = (MyLinkedList<?>) o;
        return size == that.size &&
                Objects.equals(lastNode, that.lastNode) &&
                Objects.equals(firstNode, that.firstNode);
    }


    private static class Node<T> {

        private Node<T> prev;
        private T item;
        private Node<T> next;

        Node(Node<T> prev, T element, Node<T> next) {
            this.prev = prev;
            this.item = element;
            this.next = next;
        }
    }
}
