package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T>{
    protected int capacity;
    protected int fillCount;

    public int capacity(){
        return capacity;
    }

    public int fillCount(){
        return fillCount;
    }

    @Override
    public boolean isEmpty(){
        if(fillCount==0){
            return true;
        }return false;
    }

    @Override
    public boolean isFull(){
        if(capacity==fillCount){
            return true;
        }return false;
    }

    public abstract void enqueue(T item);
    public abstract T dequeue();
    public abstract T peek();
}
