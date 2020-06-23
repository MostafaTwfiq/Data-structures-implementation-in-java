import java.util.LinkedList;

public class AVLTree {

    private class AVLNode {
        private int height;
        private int value;
        private AVLNode right;
        private AVLNode left;

        private AVLNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node value: " + value + " and its height is: " + height;
        }
    }

    AVLNode root;

    public void remove(int value) {
        root = remove(root, value);
    }

    private AVLNode remove(AVLNode root, int value) {
        if (root == null)
            return null;


        if (value == root.value) {
            if (root.right == null && root.left == null)
                return null;

            else if (root.right == null) {
                return root.left;
            }

            else {
                AVLNode tempNode = root.right;
                while (tempNode.left != null)
                    tempNode = tempNode.left;

                root.value = tempNode.value;
                root.right = remove(root.right, tempNode.value);
            }

        }
        else if (value > root.value) {
            root.right = remove(root.right, value);
        }
        else {
            root.left = remove(root.left, value);
        }

        root.height = 1 + Math.max(checkIfNodeEmpty(root.left), checkIfNodeEmpty(root.right));

        if (isUnbalanced(root))
            return rightOrLeftHeavy(root);

        return root;
    }

    public void insert(int value) {
        root = insert(this.root, value);
    }

    private AVLNode insert(AVLNode root, int value) {
        if (root == null)
            return new AVLNode(value);

        else if (root.value < value)
            root.right = insert(root.right, value);

        else if (root.value > value)
            root.left = insert(root.left, value);

        root.height = 1 + Math.max(checkIfNodeEmpty(root.left), checkIfNodeEmpty(root.right));

        if (isUnbalanced(root))
          return rightOrLeftHeavy(root);

        return root;
    }

    private boolean isUnbalanced(AVLNode node){
        return (1 + checkIfNodeEmpty(node.left)) - (1 + checkIfNodeEmpty(node.right)) > 1 ||
                (1 + checkIfNodeEmpty(node.left)) - (1 + checkIfNodeEmpty(node.right)) < -1;
    }

    private AVLNode rightOrLeftHeavy(AVLNode node){
        AVLNode root;
        if ((1 + checkIfNodeEmpty(node.left)) - (1 + checkIfNodeEmpty(node.right)) > 1){
            if (checkForLLOrLR(node)) return  leftRotation(node);
            else {
                root = leftRotation(node);
                return rightRotation(root);
            }
        }

        else if ((1 + checkIfNodeEmpty(node.left)) - (1 + checkIfNodeEmpty(node.right)) < -1) {
            if (checkForRROrRL(node)) return rightRotation(node);
            else {
                root = rightRotation(node);
                return leftRotation(root);
            }
        }
        return null;
    }

    private boolean checkForLLOrLR(AVLNode node){
        return (1 + checkIfNodeEmpty(node.left.left)) > (1 + checkIfNodeEmpty(node.left.right));
    }

    private boolean checkForRROrRL(AVLNode node){
        return (1 + checkIfNodeEmpty(node.right.right)) > (1 + checkIfNodeEmpty(node.right.left));
    }

    private AVLNode leftRotation(AVLNode node){
            AVLNode temp = node.left;
            while (temp.right != null) temp = temp.right;
            temp.right = node;
            AVLNode root = node.left;
            node.left = null;
            root = getHeight(root);
            return root;
    }

    private AVLNode rightRotation(AVLNode node){
        AVLNode temp = node.right;
        while (temp.left != null) temp = temp.left;
        temp.left = node;
        AVLNode root = node.right;
        node.right = null;
        root = getHeight(root);
        return root;
    }

    private AVLNode getHeight(AVLNode node){
        if (node == null)
            return node;

        node.left = getHeight(node.left);
        node.right = getHeight(node.right);
        node.height = 1 + Math.max(checkIfNodeEmpty(node.left), checkIfNodeEmpty(node.right));

        if (isUnbalanced(node))
            return rightOrLeftHeavy(node);

        return node;
    }

    private int checkIfNodeEmpty(AVLNode node){
        return (node == null) ? (-1) : (node.height);
    }

    public boolean contains(int value) {
        return contains(value, this.root);
    }

    private boolean contains(int value, AVLNode root) {
        if (root == null) return false;
        else if (root.value == value) return true;
        else return contains(value, root.right) || contains(value, root.left);
    }

    public int getDepth() {
        return getDepth(this.root);
    }

    private int getDepth(AVLNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 0;
        return 1 + Math.max(getDepth(root.left), getDepth(root.right));
    }

    public LinkedList<Integer> getNodesAtDepth(int depth) {
        LinkedList<Integer> arr = new LinkedList<>();
        getNodesAtDepth(depth, arr, this.root);
        return arr;
    }

    private void getNodesAtDepth(int depth, LinkedList<Integer> arr, AVLNode root) {
        if (root == null) return;
        if (depth == 0) {
            arr.addLast(root.value);
        }
        getNodesAtDepth(depth - 1, arr, root.left);
        getNodesAtDepth(depth - 1, arr, root.right);
    }

    public void preOrderTraversal(){
        preOrderTraversal(this.root);
    }

    private void preOrderTraversal(AVLNode root){
        if (root == null) return;
        System.out.println(root.value);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    public void inOrderTraversal(){
        inOrderTraversal(this.root);
    }

    private void inOrderTraversal(AVLNode root){
        if (root == null) return;
        preOrderTraversal(root.left);
        System.out.println(root.value);
        preOrderTraversal(root.right);
    }

    public void postOrderTraversal(){
        postOrderTraversal(this.root);
    }

    private void postOrderTraversal(AVLNode root){
        if (root == null) return;
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.println(root.value);
    }

    public void BreadthFirst() {
        for (int i = 0; i <= getDepth(); i++) {
            for (var temp : getNodesAtDepth(i)) System.out.print(temp + " ");
            System.out.println();
        }
    }
}