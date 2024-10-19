package priority_queues;

import java.util.ArrayList;

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    ArrayList<Entry<K, V>> heap = new ArrayList<>();
    public HeapPriorityQueue() {}
    /**
     * Creates a priority queue initialized with the given key-value pairs.
     */
    public HeapPriorityQueue(K[] keys, V[] values) {
        super();
        for (int j = 0; j < Math.min(keys.length, values.length); j++)
            heap.add(new PQEntry<>(keys[j], values[j]));
        heapify();
    }

    /**
     * Performs a bottom-up construction of the heap in linear time.
     */
    protected void heapify() {
        int startIndex = parent(size() - 1); // start at PARENT of last entry
        for (int j = startIndex; j >= 0; j--) // loop until processing the root
            downheap(j);
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Entry<K, V> min() {
        return null;
    }


    // hasLeft
    boolean hasLeft(int position) {
        int leftPosition = left(position);
        return size() > leftPosition;
    }

    // hasRight
    boolean hasRight(int position) {
        int rightPosition = right(position);
        return size() > rightPosition;
    }

    // parent
    int parent(int position) {
        return (position - 1) / 2;
    }

    // left
    int left(int position) {
        return position * 2 + 1;
    }

    // Right
    int right(int position) {
        return position * 2 + 2;
    }
    // swap

    void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }


    // insert
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newest = new PQEntry<>(key, value);
        heap.add(newest);
        upheap(heap.size() - 1);
        return newest;
    }

    // remove
    @Override
    public Entry<K, V> removeMin() {
        // Eliminamos la PK mas prioritaria pero esta es el root y no puede darse este caso
        Entry<K, V> root = heap.getFirst();
        // Cuando nos queda hay que hacer esta comprobación
        if (heap.size() == 1){
           return heap.removeLast();
        }
        var last = heap.removeLast();

        heap.set(0, last);
        downheap(0);

        return root;
    }

    // bubbleUp
    protected void upheap(int j) {
        while (j > 0) {
            // Obtengo el padre P para saber si la K es más prioritaria que la de P
            int parent = parent(j);
            var parentEntry = heap.get(parent);
            var currentEntry = heap.get(j);
            if (compare(currentEntry, parentEntry) >= 0) {
                // se cumple la condición
                break;
            }
            // tenemos que hacer un swap entre p y j
            swap(j, parent);
            j = parent;
        }
    }

    // bubble down
    void downheap(int j) {
        while (hasLeft(j)) {
            // obtenemos el entry de la posicion actual
            var currentEntry = heap.get(j);
            // obtenemos el de la izquierda
            var leftIndex = left(j);
            var smallPriorityChildEntry = heap.get(leftIndex);
            var smallPriorityChildIndex = leftIndex;
            if (hasRight(j)) {
                var rightIndex = right(j);
                var rightEntry = heap.get(rightIndex);
                if (compare(rightEntry, smallPriorityChildEntry) < 0) {
                    smallPriorityChildEntry = heap.get(rightIndex);
                    smallPriorityChildIndex = rightIndex;
                }
            }
            // se comprueba el valor entre la posición actual (j) y el hijo seleccionado
            // Si current entry es menor que la prioridad del hijo, se acaba el juego
            if (compare(currentEntry, smallPriorityChildEntry) <= 0) {
                break;
            }
            swap(j, smallPriorityChildIndex);
            j = smallPriorityChildIndex;
        }
    }


}
