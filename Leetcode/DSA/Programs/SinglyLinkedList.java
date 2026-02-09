public class SinglyLinkedList {
    
    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    public Node head;


    public void insertAtBeginning(int data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void insertAtEnd(int data){
        Node newNode = new Node(data);
        
        if(head == null){
            head = newNode;
            return;
        }
        Node curr = head;
        while(curr.next != null){
            curr = curr.next;
        }
        curr.next = newNode;
    }

    public void insertAtPosition(int data, int pos){
        if(pos < 0){
            return;
        }
        Node newNode = new Node(data);
        Node curr = head;

        for(int i = 1; i < pos; i++){
            if(curr == null){
                return;
            }
            curr = curr.next;
        }
        if(curr == null) return;

        newNode.next = curr.next;
        curr.next = newNode;
    }

    public void deleteAtBeginning(){
        if(head != null)
            head = head.next;
    }
    public void deleteAtEnd(){
        Node curr = head;
        if(curr == null){
            return;
        }
        if(curr.next == null){
            head = null;
            return;
        }
        while(curr.next.next != null){
            curr = curr.next;
        }
        curr.next = null;
    }

    public void deleteAtPosition(int pos){
        Node curr = head;
        if(pos<0){
            return;
        }
        if(pos == 0) {
            deleteAtBeginning();
            return;
        }
        for(int i = 1; i < pos; i++){ 
            if(curr == null){
                return;
            }
            curr = curr.next;
        }
        if(curr==null || curr.next == null){
            return;
        }
        curr.next = curr.next.next;
    }

    public void display(){
        Node curr = head;
        while(curr != null){
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null");

    }
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        list.insertAtBeginning(10);
        list.insertAtBeginning(5);
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        list.display();
        list.insertAtPosition(15, 2); // insert at index 2
        list.display();
        list.deleteAtBeginning();
        list.display();
        list.deleteAtEnd();
        list.display();
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        list.deleteAtPosition(2);
        list.display();
    }
}
