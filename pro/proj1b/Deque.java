public interface Deque<T> {

    public void addFirst(T addItem);
    public void addLast(T addItem);

    public T removeFirst();
    public T removeLast();

    public int size();
    public boolean isEmpty();

    public T get(int index);
}
