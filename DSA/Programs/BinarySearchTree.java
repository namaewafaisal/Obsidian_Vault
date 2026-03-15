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
    public void delete(T data){
        root = delete(root,data); 
    }
    private Node<T> delete(Node<T> root, T data){
        if(root == null) return null;
        if(data.compareTo(root.data) < 0) root.left = delete(root.left, data);
        else if(data.compareTo(root.data) > 0) root.right = delete(root.right, data);
        else{
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;

            root.data = min(root.right);
            root.right = delete(root.right, root.data);
        }
        return root;
    }
    
    public T max(){
        if (root == null) throw new RuntimeException("Tree is empty");
        return max(root);
    }
    private T max(Node<T> root) {
        if(root.right == null) return root.data;
        return max(root.right);
    }
    public T min(){
        if (root == null) throw new RuntimeException("Tree is empty");
        return min(root);
    }
    private T min(Node<T> root){
        if(root.left == null) return root.data;
        return min(root.left);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(2);
        bst.insert(3);
        bst.insert(4);
        bst.insert(1);
        bst.insert(5);
        bst.inOrder();
        System.out.println(bst.min());
        System.out.println(bst.max());
        bst.delete(2);
        bst.inOrder();
    }

}