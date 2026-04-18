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


    public void insertAtBeginning(E data){
        Node<E> newNode = new Node(data);
        if(first == null || last == null){
            first = newNode;
            last = newNode;
            return;
        }
        newNode.next = first;
        first.prev = newNode;
        first = newNode;

    }

    public void insertAtEnd(E data){
        Node<E> newNode = new Node(data);
        
        if(last == null){
            first = newNode;
            last = newNode;
            return;
        }
        last.next = newNode;
        newNode.prev = last;
        last = newNode;
    }

    public void insertAtPosition(E data, int pos){
        if(pos < 0){
            return;
        }
        if (pos == 0) {
            insertAtBeginning(data);
            return;
        }
        Node<E> curr = first;
        
        /*[] done
        [0] 
        [0,1]
        [0,1,2]
         */ 
        for(int i = 0; i < pos -1; i++){
            if(curr == null) return;
            curr = curr.next;
            
        }
        if(curr == null) return;
        if(curr == last){
            insertAtEnd(data);
            return;
        }
        Node<E> newNode = new Node(data);

        newNode.next = curr.next;
        newNode.prev = curr;
        curr.next = newNode;
        newNode.next.prev = newNode;

    }

    public void deleteAtBeginning(){
        if(first == null) return;
        if(first == last){
            first = null;
            last = null;
            return;
        }
        first = first.next;
        first.prev = null;
    }
   
    public void deleteAtEnd(){
        if (last == null) {
            return;
        }
        if(first == last) {
            first = null;
            last = null;
            return;
        }
        last = last.prev;
        last.next = null;
    }

    public void deleteAtPosition(int pos){
        Node<E> curr = first;
        if(pos<0){
            return;
        }
        if(pos == 0) {
            deleteAtBeginning();
            return;
        }
        for(int i = 0; i < pos; i++){ 
            if(curr == null){
                return;
            }
            curr = curr.next;
            
        }
        if(curr == null){
            return;
        }
        if(curr == last){
            deleteAtEnd();
            return;
        }

        curr.next.prev = curr.prev;
        curr.prev.next = curr.next;
    }

    public boolean contains(E data){
        Node<E> curr = first;
        while(curr != null){
            if(curr.data.equals(data)){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public int indexOf(E data){
        Node<E> curr = first;
        int index = 0;
        while(curr != null){
            if(curr.data.equals(data)){
                return index;
            }
            curr = curr.next;
            index++;

        }
        return -1;
    }

    public void display(){
        Node<E> curr = first;
        while(curr != null){
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null");

    }
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList();

        list.insertAtBeginning(10);
        list.insertAtBeginning(5);
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        list.display();



    }
}
