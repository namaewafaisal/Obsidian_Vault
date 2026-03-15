public class BinarySearchTree<T extends Comparable<T>>{
    static class Node{
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data){
            this.data = data;
            left = null;
            right = null;
        }

        private Node root;

        public Node build(int data){
            Node newNode = new Node(data);
            if(root == null){
                root = newNode;
                return root;
            }
            if(data < root.data){
                if()
            }
            else if(data > root.data){

            }

        }
    }
}