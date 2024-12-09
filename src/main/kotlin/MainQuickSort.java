import utils.Runneable;

import java.util.ArrayList;
import java.util.List;

public class MainQuickSort {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>(){{
            add(9);
            add(3);
            add(15);
            add(9);
            add(1);
            add(0);
            add(5);
            add(13);
            add(18);
        }};
        System.out.println(integers);
        Runneable runneable = new Runneable();
        runneable.run(()-> QuickSorter.quickSort(integers));
        System.out.println(integers);

    }
}
