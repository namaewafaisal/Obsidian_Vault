import java.util.ArrayDeque;
import java.util.Queue;

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
        System.out.print(root.data + " ");
        inOrder(root.right);
        System.out.println();
    }
    public void preOrder(Node<T> root){
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
        System.out.println();
    }
    public void postOrder(Node<T> root){
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
        System.out.println();
    }
    public void levelOrder(Node<T> root){
        Queue<Node<T>> q = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()){
            Node<T> n = q.peek();
            if(n.left != null){
                q.offer(n.left);
            }
            if(n.right != null){
                q.offer(n.right);
            }
            System.out.println(n.data);
            q.poll();
        }
    }
    public int height(){

    }
    public int size(){

    }
    public static void main(String[] args) {
        
    }
}
