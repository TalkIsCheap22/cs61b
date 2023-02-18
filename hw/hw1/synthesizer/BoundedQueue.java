package synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T>{
    int capacity();//The size of the buffer
    int fillCount();//number of items in the buffer
    void enqueue(T item);//add item to the last of the queue
    T dequeue();//delete and return the first item
    T peek();//return but not delete the first item

    default boolean isEmpty(){
        if(fillCount()==0){
            return true;
        }return false;
    }

    default boolean isFull(){
        if(fillCount()==capacity()){
            return true;
        }return false;
    }

    Iterator<T> iterator();
}
