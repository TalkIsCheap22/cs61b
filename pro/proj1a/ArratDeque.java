import java.lang.reflect.Array;

public class ArrayDeque<stuff>{

    private stuff[] array;
    private int size;
    private int capacity;
    private int startIndex;
    private int endIndex;

    public ArrayDeque(){
        array=(stuff[])new Object[8];
        size=0;
        capacity=8;
        startIndex=0;
        endIndex=1;
    }

    private void resize(int newCapacity){
        stuff[] newArray=(stuff[])new Object[newCapacity];
        int index=startIndex;
        for(int i=0;i<capacity;++i) {
            newArray[i] = array[index];
            index = nextIndex(index);
        }
        startIndex=0;
        endIndex=capacity;
        capacity=newCapacity;
    }

    private int prevIndex(int index){
        return (index+capacity-1)%capacity;
    }

    private int nextIndex(int index){
        return (index+1)%capacity;
    }

    public void addFirst(stuff addItem){
        if(size==capacity){
            resize(2*capacity);
        }
        int newStartIndex=prevIndex(startIndex);
        array[newStartIndex]=addItem;

        startIndex=prevIndex(startIndex);

        ++size;
    }

    public void addLast(stuff addItem){
        if(size==capacity){
            resize(2*capacity);
        }
        int newEndIndex=nextIndex(startIndex);
        array[newEndIndex]=addItem;

        endIndex=nextIndex(endIndex);

        ++size;
    }

    public boolean isEmpty(){
        if(size==0){
            return false;
        }
        return true;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int index=startIndex;
        while(index!=endIndex){
            StdOut.print(array[index]);
            StdOut.print(' ');
            index=nextIndex(index);
        }
    }

    public stuff removeFirst(){
        assert !isEmpty();
        startIndex=nextIndex(startIndex);
        --size;
    }

    public stuff removeLast(){
        assert !isEmpty();
        endIndex=prevIndex(endIndex);
        --size;
    }

    public stuff get(int index){
        assert index<size;
        return array[(startIndex+index)%capacity]
    }
}