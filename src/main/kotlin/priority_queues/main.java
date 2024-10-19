package priority_queues;

import java.util.concurrent.ArrayBlockingQueue;

public class main {

    public static void main(String[] args) {

        var pq = new UnsortedPriorityQueue<Integer, String>();

        pq.insert(6,"Raul");
        pq.insert(1, "Ana");
        pq.insert(5,"pepa");
        pq.insert(2,"FLUTTER");

        while(!pq.isEmpty()){
            var min = pq.removeMin();
            System.out.println(min.getValue());
        }
    }
}
