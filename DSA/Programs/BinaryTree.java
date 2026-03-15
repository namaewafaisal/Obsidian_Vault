public class BinaryTree<T> {
    class Node<T>{
        T data;
        Node<T> left;
        Node<T> right;
        
        Node(T data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node<T> root;

    public void insert(T data){
        if(root == null) {
            root = new Node<T>(data);
            return;
        }
        Queue
    }

    public void inOrder(Node<T> root){
        inOrder(root.left);
        System.out.print(root.data);
        inOrder(root.right);
    }
    public void preOrder(Node<T> root){
        System.out.print(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }
    public void postOrder(Node<T> root){
        postOrder(root.left);
        postOrder(root.right);
        System.out.print();
    }
    public void levelOrder(){

    }
    public int height(){

    }
    public int size(){

    }
    public static void main(String[] args) {
        
    }
}
