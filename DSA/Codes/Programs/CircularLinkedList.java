public class CircularLinkedList<E> {
    
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data){
            this.data = data;
        }
    }

    private Node<E> head;
    private Node<E> tail;

    public void insertAtBeginning(E data){
        Node<E> newNode = new Node(data);
        if(head == null){
            head = tail = newNode;
            newNode.next = head;
            return;
        }
        newNode.next = head;
        tail.next = newNode;
        head = newNode;
    }

    public void insertAtEnd(E data){
        Node<E> newNode = new Node(data);
        if(head == null){
            head = tail = newNode;
            newNode.next = head;
            return;
        }
        tail.next = newNode;
        newNode.next = head;
        tail = newNode;
    }
    public void deleteAtBeginning(){
        if(head == null) return;
        if(head == tail){
            head = tail = null;
            return;
        }
        head = head.next;
        tail.next = head;
    }

    public void display(){
        if(head == null) {
            System.out.println("Empty");
            return;
        }
        Node<E> curr = head;
        
        do{
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        while(curr != head);
        System.out.println("(back to head)");
    }
    public static void main(String[] args) {
        CircularLinkedList<Integer> lst = new CircularLinkedList<>();
        lst.insertAtBeginning(5);
        lst.insertAtBeginning(10);
        lst.insertAtEnd(15);
        lst.display();
        System.out.println();
        lst.deleteAtBeginning();
        lst.display();
    }

    
}
