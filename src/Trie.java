import java.util.HashMap;
import java.util.Vector;

public class Trie {

    private class Node {

        private char value;
        private boolean EOW;
        private HashMap<Character, Node> nodes;

        private Node (char value, boolean EOF) {
            this.value = value;
            this.EOW = EOF;
            nodes = new HashMap<>();
        }

        private boolean hasNode(char ch) {
            return nodes.containsKey(ch);
        }

        private void putNode(char ch, boolean EOW) {
            nodes.put(ch, new Node(ch, EOW));
        }

        private Node getNode(char ch) {
            return nodes.get(ch);
        }

        private void removeChild(char ch) {
            nodes.remove(ch);
        }

        private boolean hasChildren() {
            return !nodes.isEmpty();
        }

        private Node[] getNodes() {
            return nodes.values().toArray(new Node[0]);
        }

        @Override
        public String toString() {
            return "Value: " + value + ", EOW: " + EOW;
        }

    }

    private Node root;

    public Trie() {
        root = new Node('\0', false);
    }

    public void insert(String word) {
        insert(word, 0, root);
    }

    private void insert(String word, int index, Node node) {
        if (index == word.length()) {
            node.EOW = true;
            return;
        }

        if (!node.hasNode(word.charAt(index)))
            node.putNode(word.charAt(index), false);

        insert(word, index + 1, node.nodes.get(word.charAt(index)));

    }


    public boolean contains(String word) {
        if (word == null)
            return false;

        return contains(word, 0, root);
    }

    private boolean contains(String word, int index, Node node) {
        if (index == word.length())
            return node.EOW;

        char currentChar = word.charAt(index);
        Node nextNode = node.getNode(currentChar);

        if (nextNode == null)
            return false;

        return contains(word, index + 1, nextNode);
    }

    public void remove(String word) {
        if (word == null)
            return;

        remove(word, 0, root);

    }

    private void remove(String word, int index, Node node) {
        if (index == word.length()) {
            node.EOW = false;
            return;
        }

        char currentChar = word.charAt(index);
        Node nextNode = node.getNode(currentChar);

        if (nextNode == null)
            return;

        remove(word, index + 1, nextNode);

        if (!nextNode.hasChildren() && !nextNode.EOW)
            node.removeChild(currentChar);


    }

    public Vector<String> autoComplete(String word) {
        if (word == null)
            return null;

        Vector<String> words = new Vector<>();
        StringBuffer buff = new StringBuffer();
        Node currentNode = root;

        for (char ch : word.toCharArray()) {
            Node nextNode = currentNode.getNode(ch);
            if (nextNode == null)
                break;

            buff.append(currentNode.value);
            currentNode = nextNode;
        }

        autoComplete(buff, currentNode, words);
        return words;
    }

    private void autoComplete(StringBuffer word, Node node, Vector<String> words) {
        word.append(node.value);

        if (node.EOW)
            words.add(word.toString());

        for (Node currentNode : node.getNodes())
            autoComplete(word, currentNode, words);

        word.deleteCharAt(word.length() - 1);
    }

    public Vector<String> suggestion(String word) {
        Vector<String> words = new Vector<>();

        suggestion(word, new StringBuffer(), 0, root, words);

        return words;
    }

    private void suggestion(String word, StringBuffer sugg, int index, Node node, Vector<String> words) {
        sugg.append(node.value);

        if (index == word.length()) {
            if (node.EOW && word.equals(sugg.toString().trim())) {
                words.add(sugg.toString());
                return;
            }

            if (node.EOW)
                words.add(sugg.toString());

            for (Node currentNode : node.getNodes())
                suggestion(word, sugg, index, currentNode, words);

        } else {
            if (node.hasNode(word.charAt(index))) {
                suggestion(word, sugg, index + 1, node.getNode(word.charAt(index)), words);

            } else {
                for (Node currentNode : node.getNodes())
                    suggestion(word, sugg, index + 1, currentNode, words);

            }
        }

        sugg.deleteCharAt(sugg.length() - 1);
    }

}
