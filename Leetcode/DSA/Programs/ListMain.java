class Node{
    int data;
    Node next;

    Node(int data){
        this.data = data;
        this.next = null;
    }

    public int size(){
        Node temp = this;
        int size = 0;
        while(temp != null){
            size++;
            temp.next = t
        }
        return size;
    }
    public void display(){
        Node temp = this;
        while(temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

}
public class ListMain{
    public static void main(String[] args){
        Node head = new Node(10);
        Node temp = new Node(20);
        head.next = temp;

        head.display();
        System.out.println(head.size());
        
    }
}