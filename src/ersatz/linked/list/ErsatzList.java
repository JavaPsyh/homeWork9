package ersatz.linked.list;

public interface ErsatzList<T> {

    void add(T value);

    void add(T value, int index);

    void addAll(ErsatzList<T> ersatzList);

    T get(int index);

    void set(T value, int index);

    T remove(int index);

    T remove(T t);

    int size();

    boolean isEmpty();
}
