package priority_queues;

import java.util.ArrayList;

public class HeapMain {

    public static void main(String[] args) {
        var heapPQ = new HeapPriorityQueue();

        heapPQ.insert(10, 3);
        heapPQ.insert(2, 5);
        heapPQ.insert(3, 7);
        heapPQ.insert(2, 9);
        heapPQ.insert(3, 8);
        heapPQ.insert(4, 10);
        heapPQ.insert(1, 11);
        System.out.println(heapPQ.heap);

        heapPQ.removeMin(); // remove K 1
        heapPQ.removeMin();
        heapPQ.removeMin();
        heapPQ.removeMin();
        heapPQ.removeMin();
        heapPQ.removeMin();
        System.out.println(heapPQ.heap);


        ArrayList<Integer> list = new ArrayList() {{
            add(10);
            add(3);
            add(2);
            add(1);
            add(5);
            add(9);
            add(4);
        }};

        System.out.println(list);
        list = (ArrayList<Integer>) HeapSorter.heapSort(list,false);
        System.out.println(list);
    }
}
