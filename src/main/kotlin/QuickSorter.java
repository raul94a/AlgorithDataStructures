import java.util.ArrayList;
import java.util.List;

public class QuickSorter {

    public static <T extends Comparable<T>> void quickSort (List<T> list) {

        int n = list.size();
        if(n < 2) return;

        T pivot = list.getFirst();

        List<T> less = new ArrayList<>();
        List<T> equals = new ArrayList<>();
        List<T> greater = new ArrayList<>();

        while(!list.isEmpty()){
            T element = list.removeFirst();
            if(element.compareTo(pivot) > 0){
                greater.add(element);
            }
            else if(element.compareTo(pivot) < 0){
                less.add(element);
            }
            else{
                equals.add(element);
            }
        }

        // recursive
        quickSort(less);
        quickSort(greater);

        while (!less.isEmpty()) {
            list.add(less.removeFirst());
        }
        while (!equals.isEmpty()) {
            list.add(equals.removeFirst());
        }
        while (!greater.isEmpty()) {
            list.add(greater.removeFirst());
        }

    }
}
