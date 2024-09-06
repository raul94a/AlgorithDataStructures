package priority_queues;

import java.util.ArrayList;
import java.util.Comparator;


public class UnsortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    /**
     * primary collection of priority queue entries
     */
    private ArrayList<Entry<K, V>> list = new ArrayList<>();

    /**
     * Creates an empty priority queue based on the natural ordering of its keys.
     */
    public UnsortedPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     */
    public UnsortedPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Returns the Position of an entry having minimal key.
     */
    private Entry<K, V> findMin() { // only called when nonempty
        Entry<K, V> small = list.get(0);
        for (Entry<K, V> walk : list) {

            if (compare(walk, small) < 0)
                small = walk; // found an even smaller key
        }
        return small;
    }

    /**
     * Inserts a key-value pair and returns the entry created.
     */
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key); // auxiliary key-checking method (could throw exception)
        Entry<K, V> newest = new PQEntry<>(key, value);
        list.addLast(newest);
        return newest;
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     */
    public Entry<K, V> min() {
        if (list.isEmpty()) return null;
        return findMin();
    }

    /**
     * Removes and returns an entry with minimal key.
     */
    public Entry<K, V> removeMin() {
        if (list.isEmpty()) return null;
        var min = findMin();
        list.remove(min);
        return min;

    }

    /**
     * Returns the number of items in the priority queue.
     */
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}