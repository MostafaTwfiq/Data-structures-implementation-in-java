public class BinaryTree {

    private class Node{
        private int value;
        private Node rightNode;
        private Node leftNode;

        private Node(int value) {
            this.value = value;
        }

        @Override
        public String toString(){
            return "Value = " + value;
        }
    }
    private Node root;

    public void insert(int item){
        if (root == null) {
            root = new Node(item);
            return;
        }
        Node current = root;
        while(true){
            if (current.value <= item) {
                if (current.rightNode == null){
                    current.rightNode = new Node(item);
                    return;
                }
                current = current.rightNode;
            }
            else {
                if (current.leftNode == null){
                    current.leftNode = new Node(item);
                    return;
                }
                current = current.leftNode;
            }
        }
    }

    public boolean find(int item){
        Node current = root;
        while (current != null){
            if (current.value == item) return true;
            else if (current.value < item) current = current.rightNode;
            else current = current.leftNode;
        }

        return false;
    }

    public void remove(int item) {
        root = remove(root, item);
    }

    private Node remove(Node root, int item) {
        if (root == null)
            return null;

        if (root.value == item) {
            if (root.rightNode == null && root.leftNode == null)
                return null;

            else if (root.rightNode == null)
                return root.leftNode;

            else {
                Node tempNode = root.rightNode;
                while (tempNode.leftNode != null)
                    tempNode = tempNode.leftNode;

                root.value = tempNode.value;
                root.rightNode = remove(root.rightNode, tempNode.value);

            }

        }
        else if (root.value < item) {
            root.rightNode = remove(root.rightNode, item);
        }
        else if (root.value > item) {
            root.leftNode = remove(root.leftNode, item);
        }

        return root;
    }

    public void preOrderTraversal(){
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node root){
        if (root == null) return;
        System.out.println(root.value);
        preOrderTraversal(root.leftNode);
        preOrderTraversal(root.rightNode);
    }

    public void inOrderTraversal(){
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node root){
        if (root == null) return;
        else if (root.rightNode == null && root.leftNode == null){
            System.out.println(root.value);
            return;
        }
        inOrderTraversal(root.leftNode);
        System.out.println(root.value);
        inOrderTraversal(root.rightNode);
    }

    public void postOrderTraversal(){
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node root){
        if (root == null) return;
        else if (root.rightNode == null && root.leftNode == null){
            System.out.println(root.value);
            return;
        }
        postOrderTraversal(root.leftNode);
        postOrderTraversal(root.rightNode);
        System.out.println(root.value);
    }

    public int minimumValue(){
        return minimumValue(root);
    }
    private int minimumValue(Node root){
        if (root == null) return 0;
        if (root.leftNode == null && root.rightNode == null) return root.value;
        return Math.max(root.value, Math.max(minimumValue(root.leftNode), minimumValue(root.rightNode)));
    }

    public Node rootGetter(){
        return root;
    }

    public boolean isEquals(BinaryTree tree){
        return isEquals(this.root, tree.rootGetter());
    }

    private boolean isEquals(Node firstRoot, Node secondRoot){
        if (firstRoot == null || secondRoot == null) return firstRoot == null && secondRoot == null;
        else if (firstRoot.value == secondRoot.value) {
            return isEquals(firstRoot.leftNode, secondRoot.leftNode) && isEquals(firstRoot.rightNode, secondRoot.rightNode);
        }
        else return false;
    }

    public void KDistanceFromTheRoot(int distance){
        KDistanceFromTheRoot(root, distance);
    }

    private void KDistanceFromTheRoot(Node root, int distance){
        if (root == null)  return;
        if (distance == 0) System.out.println(root.value);
        KDistanceFromTheRoot(root.leftNode, distance - 1);
        KDistanceFromTheRoot(root.rightNode, distance - 1);
    }
}