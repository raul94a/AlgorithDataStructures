package graphs;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class Vertex<T> implements Comparator<Vertex<T>> {
    private final String id = UUID.randomUUID().toString();
    private T data;
    private List<Edge<T>> edges;

    public Vertex(T data) {
        if(data == null){
            throw new IllegalStateException("Data must not be null");
        }
        this.data = data;
        edges = new ArrayList<>();
    }

    public String getId(){
        return id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public void addEdge( Vertex<T> end, Integer weight) {
        this.edges.add(new Edge<T>(this, end, weight));
    }

    public boolean removeEdge(Vertex<T> end) {
        return this.edges.removeIf(edge -> edge.getEnd() == end);
    }



    @Override
    public String toString() {
        return "" + data;
    }



    @Override
    public int compare(Vertex<T> o1, Vertex<T> o2) {
        return o1.getId().equals(o2.getId()) ? 0 : 1;
    }
}
