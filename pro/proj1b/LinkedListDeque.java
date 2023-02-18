public class LinkedListDeque<T> implements Deque<T>{

    private class Node{
        T item;
        Node prev;
        Node next;

        public Node(T item){
            this.item=item;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel=new Node(null);
        sentinel.prev=sentinel.next=sentinel;
        size=0;
    }

    @Override
    public void addFirst(T addItem){
        Node newNode=new Node(addItem);
        newNode.next=sentinel.next;
        newNode.prev=sentinel;
        sentinel.next.prev=newNode;
        sentinel.next=newNode;
        ++size;
    }

    @Override
    public void addLast(T addItem){
        Node newNode=new Node(addItem);
        newNode.next=sentinel;
        newNode.prev=sentinel.prev;
        sentinel.prev=newNode;
        newNode.prev.next=newNode;
        ++size;
    }

    @Override
    public T removeFirst(){
        T firstItem=sentinel.next.item;
        sentinel.next=sentinel.next.next;
        sentinel.next.prev=sentinel;
        --size;

        return firstItem;
    }

    @Override
    public T removeLast(){
        T lastItem=sentinel.prev.item;
        sentinel.prev=sentinel.prev.prev;
        sentinel.prev.next=sentinel;
        --size;

        return lastItem;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        if(size==0){
            return true;
        }return false;
    }

    public T get(int index){
        assert index<size;
        Node nowNode=sentinel.next;
        for(int i=0;i<index;++i){
            nowNode=nowNode.next;
        }
        return nowNode.item;
    }
}
