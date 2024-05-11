import java.util.Random;

public class MyHashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 11; // Default number of chains
    private Node<K, V>[] table;
    private int size;

    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashTable(int capacity) {
        table = (Node<K, V>[]) new Node[capacity];
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    public void put(K key, V value) {
        int index = hash(key);
        Node<K, V> newNode = new Node<>(key, value);

        if (table[index] == null) {
            table[index] = newNode;
        } else {
            Node<K, V> current = table[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        Node<K, V> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null; // Key not found
    }

    // Getter for size
    public int size() {
        return size;
    }

    // Node class
    private static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();

        // Test adding 10,000 random elements
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int randomNumber = random.nextInt(10000);
            MyTestingClass key = new MyTestingClass(randomNumber, randomNumber); // Pass arguments to the constructor
            table.put(key, "Value" + randomNumber);
        }

        // Print number of elements in each bucket
        for (int i = 0; i < table.table.length; i++) {
            int count = 0;
            Node<MyTestingClass, String> current = table.table[i];
            while (current != null) {
                count++;
                current = current.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }
    }
}
