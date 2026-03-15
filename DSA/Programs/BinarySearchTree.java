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
        insert(root,data);
    }
}