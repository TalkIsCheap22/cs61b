package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>{
    private int first;            // index for the next dequeue or peek
    private int last;              //Index for the next enqueue.
    private T[] rb;             //Array for storing the buffer data;

    //Create a new ArrayRingBuffer with the given capacity.
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        first=last=fillCount=0;
        this.capacity=capacity;
        rb=(T[])new Object[capacity];
    }

    private int frontIndex(int index){return (index+capacity-1)%capacity;}
    private int rearIndex(int index){return (index+1)%capacity;}
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T item) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if(isFull())throw new RuntimeException("Ring buffer overflow");
        rb[last]=item;
        last=rearIndex(last);
        ++fillCount;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if(isEmpty())throw new RuntimeException("Ring buffer underflow");
        T firstItem=rb[first];
        first=rearIndex(first);
        --fillCount;
        return firstItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if(isEmpty())throw new RuntimeException("Ring buffer underflow");
        return rb[first];
    }

    @Override
    // TODO: When you get to part 5, implement the needed code to support iteration.
    public Iterator<T> iterator(){
        return new bufferIterator();
    }

    private class bufferIterator implements Iterator<T>{
        private int index;

        public boolean hasNext(){
            return !(rearIndex(index)==last);
        }

        public T next(){
            T nextItem=rb[index];
            index=rearIndex(index);
            return nextItem;
        }
    }
}
