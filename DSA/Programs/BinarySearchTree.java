public class BinarySearchTree {
    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
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