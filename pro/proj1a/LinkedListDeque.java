public class LinkedListDeque<stuff>{

    private class Node{
        stuff item;
        Node prev;
        Node next;

        public Node(stuff item){
            this.item=item;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel=new Node(null);
        sentinel.next=sentinel.prev=sentinel;
        size=0;
    }

    public void addFirst(stuff addItem){
        Node newNode=new Node(addItem);
        newNode.next=sentinel.next;
        sentinel.next.prev=newNode;
        sentinel.next=newNode;
        newNode.prev=sentinel;

        ++size;
    }

    public void addLast(stuff addItem){
        Node newNode=new Node(addItem);
        newNode.next=sentinel;
        sentinel.prev.next=newNode;
        newNode.prev=sentinel.prev;
        sentinel.prev=newNode;

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
        Node nowNode=sentinel.next;
        while(nowNode!=null){
            StdOut.print(nowNode.item);
            StdOut.print(' ');
        }
    }

    public stuff removeFirst(){
        stuff firstItem=sentinel.next.item;
        sentinel.next=sentinel.next.next;

        return firstItem;
    }

    public stuff removeLast(){
        stuff lastItem=sentinel.prev.item;
        sentinel.prev=sentinel.prev.prev;

        return lastItem;
    }

    public stuff get(int index){
        Node nowNode=sentinel.next;
        for(int i=0;i<index;++i){
            nowNode=nowNode.next;
        }

        return nowNode.item;
    }

    private stuff getRecursiveHelper(Node starter,int index){
        if(index==0){
            return starter.item;
        }
        return getRecursiveHelper(starter.next,index-1);
    }

    public stuff getRecursive(int index){
        return getRecursiveHelper(sentinel.next,index);
    }
}
