package my.linked.list;

public interface MyList<T> {

    void add(T value);

    void add(T value, int index);

    void addAll(MyList<T> myList);

    T get(int index);

    void set(T value, int index);

    T remove(int index);

    T remove(T t);

    int size();

    boolean isEmpty();
}
