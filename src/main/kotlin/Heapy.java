import utils.HeapMinimun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


// Monticulo de maximos
// el numero más alto es el que más prioridad tiene, representando el nodo raiz
public class Heapy {

    public static void main(String[] args) {
        var heap = new Heapy();
        var array = new Integer[]{8, 62, 20, 57, 4, 24};
        for (Integer i : array) {
            heap.insert(i, i);
        }
        System.out.println(heap);

        int length = heap.size;

        for (int i = 0; i < length; i++){
            heap.delete();
        }
    }

    private int capacity;
    private int size = 0;
    private Node[] heap;

    public  Heapy(){
        this.capacity = 10;
        this.heap = new Node[this.capacity];
    }


    private void swap(int i, int j) {

        var aux = heap[i];
        heap[i] = heap[j];
        heap[j] = aux;
//        System.out.println("Cambiando el nodo " + heap[i] + " con " + aux);
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
        if(size - 1 == 0){
            System.out.println("Borrando " + heap[0]);

            this.heap = new Node[capacity];
            return;
        }
        var last = heap[size - 1];
        heap[size - 1] = null;
        size--;
        System.out.println("Borrando " + heap[0]);
        heap[0] = last;
        downheap(0);

    }

    // responsable de colocar cada elemento del array en su sitio
    private void upheap(int position){
        if(position > 0){
            var node = heap[position];
            int parentPosition = parent(position);
            var parentNode = heap[parentPosition];
            if(node.priority > parentNode.priority){
                swap(position,parentPosition);
                upheap(parentPosition);
            }
        }
    }
    private void downheap(int position){

        int positionLeftChild = left(position);
        int positionRightChild = right(position);
        // buscamos por el hijo izquierdo primero
        if(hasLeft(position)){
            int posicionHijoMayor;
            if (hasRight(position)
                    && heap[positionRightChild].priority > heap[positionLeftChild].priority
            ){
                posicionHijoMayor = positionRightChild;
            }
            else{
                posicionHijoMayor = positionLeftChild;
            }

            if(heap[position].priority < heap[posicionHijoMayor].priority ){
                swap(position,posicionHijoMayor);
                downheap(posicionHijoMayor);

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
