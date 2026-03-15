public class BinarySearchTree<T extends Comparable<T>>{
    private static class Node<T>{
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data){
            this.data = data;
            left = null;
            right = null;
        }
    }
    private Node<T> root;
    
    public void insert(T data){
        root = insert(root,data);
    }
    private Node<T> insert(Node<T> root, T data){
        if(root == null) return new Node<T>(data);
        if(data.compareTo(root.data) < 0) root.left = insert(root.left, data);
        else if(data.compareTo(root.data) > 0) root.right = insert(root.right, data);
        return root;
    }
    public boolean search(T data){
        return search(root,data);
    }
    private boolean search(Node<T> root, T data){
        if(root == null) return false;
        if(data.compareTo(root.data) < 0) return search(root.left, data);
        if(data.compareTo(root.data) > 0) return search(root.right, data);
        return true;
    }
    public 


}