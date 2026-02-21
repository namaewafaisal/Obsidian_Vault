import java.util.EmptyStackException;

public class StackUsingList<E> {

    static class Node<E>{
        E data;
        Node<E> next;

        Node(E data){
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> head;
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
        return head.data;
    }
    public E pop(){
        if(isEmpty()) throw new EmptyStackException();
        E value = head.data;
        head = head.next;
        size--;
        return value;
    }
    public void display(){
        Node curr = head;
        if(curr == null) {System.out.println("Nothing to display"); return;}
        while(curr != null){
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }


}
class StackUsingListMain{
    public static void main(String[] args) {
        StackUsingList<Integer> stack = new StackUsingList<>();
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.display();
        System.out.println(stack.size());
        System.out.println(stack.isEmpty());
        stack.pop();
        stack.peek();
        stack.pop();
        stack.pop();
        System.out.println(stack.size());
        stack.display();

    }
}