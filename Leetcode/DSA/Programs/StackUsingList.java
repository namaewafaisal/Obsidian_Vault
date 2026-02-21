
public class StackUsingList<E> {

    static class Node<E>{
        E data;
        Node next;

        Node(E data){
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;
    

    
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void push(E data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }
    public E peek(){
        if(isEmpty()) throw new EmptyStackException();
        return (E) head.data;
    }
    public E pop(){
        E value = (E) head.data;
        head = head.next;
    }


}
class StackUsingListMain{
    public static void main(String[] args) {
        StackUsingList stack = new StackUsingList();

    }
}