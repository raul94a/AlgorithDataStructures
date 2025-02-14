import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


// Monticulo de maximos
// el numero más alto es el que más prioridad tiene, representando el nodo raiz
public class Heapy {

    public static void main(String[] args) {
        var heap = new Heapy();
        heap.insert(1,10);
        heap.insert(00,15);
        heap.insert(2,22);
        System.out.println(heap);

        heap.delete();
        heap.delete();
        heap.delete();

        System.out.println(heap);
    }

    private int capacity;
    private int size = 0;
    private Node[] heap;

    public  Heapy(){
        this.capacity = 10;
        this.heap = new Node[this.capacity];
    }


    private void swap(int i, int j){
        var indexI = findIndex(i);
        var indexJ = findIndex(j);
        if(indexJ == -1 || indexI == -1){
            return;
        }
        var aux = heap[indexI];
        heap[indexI] = heap[indexJ];
        heap[indexJ] = heap[indexI];
    }

    private int findIndex(Integer integer){

        for (var i = 0; i < this.heap.length; i++){
            if (Objects.equals(integer, this.heap[i])){
                return i;
            }
        }

        return -1;
    }


    public void insert(int integer, int priority){

        if (size == heap.length - 1)
            throw new IllegalStateException("El heap está lleno");

        this.heap[size] = new Node(integer,priority);
        upheap(size);
        size++;




    }

    // se borra el nodo raiz
    public void delete(){
        // borrar el de más prioridad, la raiz
        System.out.println("Borrando con size " + (size - 1));
        if(size - 1 == 0){
            this.heap = new Node[capacity];
            return;
        }
        var last = heap[size - 1];
        heap[size - 1] = null;
        size--;
        heap[0] = last;
        System.out.println(this);
        downheap(0);

    }

    // responsable de colocar cada elemento del array en su sitio
    private void upheap(int position){
        if(position > 0){
            var node = heap[position];
            int parentPosition = parent(position);
            var parentNode = heap[parentPosition];
            if(node.priority > parentNode.priority){
                var temp = heap[position]; // hijo en variable temporal
                heap[position] = parentNode;
                heap[parentPosition] = temp;
                upheap(parentPosition);
            }
        }
    }
    private void downheap(int position){

        int positionLeftChild = left(position);
        int positionRightChild = right(position);
        // buscamos por el hijo izquierdo primero
        if(hasLeft(position)){
            int posicionHijoMenor;
            if (hasRight(position)
                    && heap[positionRightChild].priority < heap[positionLeftChild].priority
            ){
                posicionHijoMenor = positionRightChild;
            }
            else{
                posicionHijoMenor = positionLeftChild;
            }

            if(heap[position].priority > heap[posicionHijoMenor].priority ){
                swap(position,posicionHijoMenor);
                downheap(posicionHijoMenor);						// Llamada recursiva sobre el hijo

            }
        }


    }
    // hasLeft
    boolean hasLeft(int position) {
        int leftPosition = left(position);
        return heap[leftPosition] != null;
    }

    // hasRight
    boolean hasRight(int position) {
        int rightPosition = right(position);
        return heap[rightPosition] != null;
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

    @Override
    public String toString() {
        return "Heapy{" +
                "heap=" + Arrays.toString(heap) +
                '}';
    }
}


class Node {
    public int value;
    public int priority;

    public Node(int v, int  p){value = v; priority = p; }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", priority=" + priority +
                '}';
    }
}
