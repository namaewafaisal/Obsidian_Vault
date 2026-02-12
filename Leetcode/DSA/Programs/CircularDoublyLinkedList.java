public class CircularDoublyLinkedList<E> {
    
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data){
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;

    public void insertAtBeginning(E data){
        Node<E> newNode = new Node(data);
        if(head == null){
            head = newNode;
            tail = newNode;
            head.next = tail;
            return;
        }
        tail.next = newNode;
        newNode.next = head;
        head = tail.next;
    }

    public void insertAtEnd(E data){
        Node<E> newNode = new Node(data);
        if(head == null){
            head = newNode;
            tail = newNode;
            head.next = tail;
            return;
        }
        tail.next = newNode;
        newNode.next = head;
        tail = tail.next;
    }
    public void deleteAtBeginning(){
        if(head == null) return;
        if(head.next == head){
            head = null;
            return;
        }
        head = head.next;
        tail.next = head;
    }

    public void display(){
        if(head == null) return;
        Node<E> curr = head;
        
        do{
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        while(curr != head);
    }
    public static void main(String[] args) {
        CircularDoublyLinkedList<Integer> lst = new CircularDoublyLinkedList<>();
        lst.insertAtBeginning(5);
        lst.insertAtBeginning(10);
        lst.insertAtEnd(15);
        lst.display();
        System.out.println();
        lst.deleteAtBeginning();
        lst.display();
    }

    
}
