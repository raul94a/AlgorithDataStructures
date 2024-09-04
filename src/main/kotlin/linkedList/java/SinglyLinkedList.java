package linkedList.java;


public class SinglyLinkedList<T> {

    private Node<T> head;

    private Node<T> tail;

    private int size = 0;


    public SinglyLinkedList(T value) {
        this.head = new Node<>(value);
        size++;
    }

    public Node<T> getHead(){
        return head;
    }
    void addFirst(T value){
        Node<T> node = new Node<>(value);
        node.setNext(head);
        this.head = node;
        if(this.tail == null){
            this.tail = this.head;
        }

        size++;
    }

    public void addLast(T value){

        var head = getHead();



        while(head != null){
            var lastItem = head;
            head = head.getNext();
            if(head == null){
                var tail = new Node<>(value);
                lastItem.setNext(tail);
                this.tail = tail;
                size++;
            }

        }

    }

    public void deleteFirst(){
        var head = getHead();
        this.head = head.getNext();
        if(size > 0){
            size--;
        }
    }

    public void deleteLast(){
       var head = getHead();
        while (head.getNext() != null) {

            var lastItem = head.getNext();
            if (lastItem.getNext() == null) {
                head.setNext(null);
                this.tail = head;
                size--;
            }
            head = lastItem;
        }


    }

    public void delete(IDeleteNodeLinkedList deleteFun) {
        var head = getHead();
        if(deleteFun.test(head)){

            deleteFirst();
            return;
        }
        while(head.getNext() != null){

            var lastItem = head.getNext();
            if(deleteFun.test(lastItem)){
                head.setNext(lastItem.getNext());
                size--;
            }
            head = lastItem;
        }
    }

    public void transverse(ITransverseLinkedList<T> traversable){

        var head = getHead();
        while (head != null){
            traversable.transverse(head);
            head = head.getNext();
        }
    }


    public static void main(String[] args) {
        var linkedList = new SinglyLinkedList<>(1);

        linkedList.addFirst(2);
        linkedList.addLast(19);
        linkedList.addFirst(60);
        linkedList.addLast(7);
        System.out.println(linkedList);
    
        linkedList.deleteLast();
        System.out.println(linkedList);






    }


    @Override
    public String toString() {
        return "SinglyLinkedList{" +
                "head=" + head +
                '}';
    }

    public int getSize() {
        return size;
    }
}
