package priority_queues;

import java.util.ArrayList;
import java.util.List;

public class HeapSorter {

    public static <E extends Comparable<E>> List<E> heapSort(List<E> list, boolean reverse){

        var heapPQ   = new HeapPriorityQueue<E,E>();
        var orderedList = new ArrayList<E>();
        for (E item : list){
            heapPQ.insert(item,null);
        }

        for (int i = 0; i < list.size(); i++){
            var item =  heapPQ.removeMin();
            if(reverse){
                orderedList.addFirst(item.getKey());
            }
            else {
                orderedList.add(item.getKey());
            }
        }
    
        return orderedList;





    }
}
