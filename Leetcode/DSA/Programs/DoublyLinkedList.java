public class DoublyLinkedList<E> {
    
    private static class Node<E>{
        E data;
        Node<E> next;
        Node<E> prev;

        Node(E data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<E> first;
    private Node<E> last;


    public void insertAtBeginning(int data){
        Node<E> newNode = new Node(data);
        newNode.next = first;
        first = newNode;
        if(last == null){
            last = first;
        }
    }

    public void insertAtEnd(int data){
        Node<E> newNode = new Node(data);
        
        if(first == null){
            first = newNode;
            return;
        }
        Node curr = first;
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
        Node curr = first;

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
        if(first != null)
            first = first.next;
    }
    public void deleteAtEnd(){
        Node curr = first;
        if(curr == null){
            return;
        }
        if(curr.next == null){
            first = null;
            return;
        }
        while(curr.next.next != null){
            curr = curr.next;
        }
        curr.next = null;
    }

    public void deleteAtPosition(int pos){
        Node curr = first;
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

    public boolean contains(int data){
        Node curr = first;
        while(curr != null){
            if(curr.data == data){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public int indexOf(int data){
        Node curr = first;
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
        Node curr = first;
        while(curr != null){
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null");

    }
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

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

        System.out.println(list.contains(20));
        System.out.println(list.indexOf(30));

    }
}
