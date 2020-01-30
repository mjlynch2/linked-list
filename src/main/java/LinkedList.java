public interface LinkedList<T> {
    public boolean isEmpty();

    public int size();

    public T get(int index);

    public void insert(int location, T value);

    public void append(T value);

    public T remove(int index);
}