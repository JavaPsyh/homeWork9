package ersatz.linked.list;

public class Node<T> {

    Node<T> prev;
    T item;
    Node<T> next;

    Node(Node<T> prev, T element, Node<T> next) {
        this.prev = prev;
        this.item = element;
        this.next = next;
    }
}
