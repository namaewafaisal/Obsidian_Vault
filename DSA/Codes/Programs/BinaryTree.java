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
    private Queue<Node<T>> queue;

    public void insert(T data){
        if(queue == null) {
            queue = new ArrayDeque<>();
            root = new Node<T>(data);
            queue.offer(root);
            return;
        }
        Node<T> newNode = new Node<T>(data);
        while(!queue.isEmpty()){
            if(queue.peek().left == null){
                queue.peek().left = newNode;
                queue.offer(newNode);
                return;
            }
            else if(queue.peek().right == null){
                queue.peek().right = newNode;
                queue.offer(newNode);
                return;
            }
            else{
                queue.poll();
            }
        }
        
    }

    public void inOrder(){
        inOrder(root);
        System.out.println();
    }
    private void inOrder(Node<T> root){
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }
    public void preOrder(){
        preOrder(root);
        System.out.println();
    }
    private void preOrder(Node<T> root){
        if(root == null) return;  
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
    public void postOrder(){
        postOrder(root);
        System.out.println();
    }
    private void postOrder(Node<T> root){
        if(root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }
    public void levelOrder(){
        levelOrder(root);
        System.out.println();
    }
    private void levelOrder(Node<T> root){
        if(root == null) return;
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
            System.out.print(n.data + " ");
            q.poll();
        }
    }
    public int height(){
        return height(root);
    }
    private int height(Node<T> root){
        if(root == null) return -1;
        return 1 + Math.max(height(root.left),height(root.right));
    }
    public int size(){
        return size(root);
    }
    private int size(Node<T> root){
        if(root == null) return 0;
        return 1 + size(root.left) + size(root.right);
    }
    public static void main(String[] args) {
        BinaryTree<Character> tree = new BinaryTree<>();
        tree.insert('A');
        tree.insert('B');
        tree.insert('C');
        tree.insert('D');
        tree.insert('E');
        tree.insert('F');
        tree.inOrder();
        tree.preOrder();
        tree.postOrder();
        tree.levelOrder();
        System.out.println(tree.height());
        System.out.println(tree.size());
    }
}
