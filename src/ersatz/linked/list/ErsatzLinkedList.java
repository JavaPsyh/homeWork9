package ersatz.linked.list;

public class ErsatzLinkedList<T> implements ErsatzList<T> {

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
    public void addAll(ErsatzList<T> ersatzList) {
        for (int i = 0; i < ersatzList.size(); i++) {
            add(ersatzList.get(i));
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
        Node<T> temp = getFirstNode();
        int index = 0;
        if (!isEmpty()) {
            for (int i = 0; i < size(); i++) {
                if (temp.item == t) {
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

    public Node<T> getFirstNode() {
        return firstNode;
    }

    public Node<T> iterator(int index) {
        Node<T> result = getFirstNode();
        if (verificationOfIndex(index)) {
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        }
        return result;
    }

}
