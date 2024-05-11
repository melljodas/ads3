import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTree<K extends Comparable<K>, V> {
    private Node root;
    private int size;

    private class Node {
        private final K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Constructor, put, get, delete, and size methods
    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    // Other methods such as get, delete, iterator, etc.

    public int size() {
        return size;
    }

    public Iterable<Node> iterator() {
        return new InOrderIterable();
    }

    private class InOrderIterable implements Iterable<Node> {
        private Stack<Node> stack;

        public InOrderIterable() {
            stack = new Stack<>();
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        public Iterator<Node> iterator() {
            return new Iterator<Node>() {
                @Override
                public boolean hasNext() {
                    return !stack.isEmpty();
                }

                @Override
                public Node next() {
                    Node current = stack.pop();
                    pushLeft(current.right);
                    return current;
                }
            };
        }
    }
}
