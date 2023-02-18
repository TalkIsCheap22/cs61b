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
        endIndex=0;
    }

    private void resize(int newCapacity){
        stuff[] newArray=(stuff[])new Object[newCapacity];
        int index=startIndex;
        for(int i=0;i<capacity;++i) {
            newArray[i] = array[index];
            index = nextIndex(index);
        }
        array=newArray;

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

        startIndex=newStartIndex;

        ++size;
    }

    public void addLast(stuff addItem){
        if(size==capacity){
            resize(2*capacity);
        }
        array[endIndex]=addItem;

        int newEndIndex=nextIndex(endIndex);
        endIndex=newEndIndex;

        ++size;
    }

    public boolean isEmpty(){
        if(size==0){
            return true;
        }return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        StdOut.printf("size:%d\n",size);
        StdOut.printf("start:%d\n",startIndex);
        StdOut.printf("end:%d\n",endIndex);

        int index=startIndex;
        for(int i=0;i<size;++i){
            StdOut.print(array[index]);
            StdOut.print(' ');
            index=nextIndex(index);
        }
        StdOut.println();
        StdOut.println();
    }

    public stuff removeFirst(){
        assert !isEmpty();
        if(size*4<capacity){
            resize(capacity/2);
        }
        stuff firstItem=array[startIndex];
        startIndex=nextIndex(startIndex);

        --size;

        return firstItem;
    }

    public stuff removeLast(){
        assert !isEmpty();
        stuff lastItem=array[prevIndex(endIndex)];
        endIndex=prevIndex(endIndex);

        --size;

        return lastItem;
    }

    public stuff get(int index){
        assert index<size;
        return array[(startIndex+index)%capacity];
    }
}