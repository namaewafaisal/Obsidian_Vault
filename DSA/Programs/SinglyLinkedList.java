public class SinglyLinkedList {
    
    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;


    public void insertAtBeginning(int data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }
    public int size(){
        return size;
    }

    public void insertAtEnd(int data){
        Node newNode = new Node(data);
        
        if(head == null){
            head = newNode;
            size++;
            return;
        }
        Node curr = head;
        while(curr.next != null){
            curr = curr.next;
        }
        curr.next = newNode;
        size++;
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
        size++;
    }

    public void deleteAtBeginning(){
        if(head != null)
            head = head.next;
        size--;
    }
    public void deleteAtEnd(){
        Node curr = head;
        if(curr == null){
            return;
        }
        if(curr.next == null){
            head = null;
            size--;
            return;
        }
        while(curr.next.next != null){
            curr = curr.next;
        }
        curr.next = null;
        size--;
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
        size--;
    }

    public boolean contains(int data){
        Node curr = head;
        while(curr != null){
            if(curr.data == data){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public int indexOf(int data){
        Node curr = head;
        int index = 0;
        while(curr != null){
            if(curr.data == data){
                return index;
            }
            curr = curr.next;
            index++;

        }
        return -1;
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
        System.out.println(list.size());
        list.display();
        list.insertAtPosition(15, 2); // insert at index 2
        list.display();
        list.deleteAtBeginning();
        list.display();
        list.deleteAtEnd();
        System.out.println(list.size());
        list.display();
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        list.deleteAtPosition(2);
        list.display();
        System.out.println(list.size());

        System.out.println(list.contains(20));
        System.out.println(list.indexOf(30));

    }
}
