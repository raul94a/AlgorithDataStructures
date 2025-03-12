package graphs.wiley_graph;

import java.util.Arrays;

public class Edge<V> {

    private Vertex[] edge;
    private Double weight;

    /**
     * Constructor for a non-weighted Edge
     * @param v incoming vertex
     * @param u target vertex
     */
    public Edge (Vertex<V> v, Vertex<V> u){
        this.edge = new Vertex[]{v,u};
        weight = null;
    }

    /**
     * Constructor for a weighted Edge
     * @param v incoming vertex
     * @param u target vertex
     */
    public Edge (Vertex<V> v, Vertex<V> u, double weight){
        this.edge = new Vertex[]{v,u};
        this.weight = weight;
    }

    public Vertex[] getEdge() {
        return edge;
    }

    public void setEdge(Vertex[] edge) {
        this.edge = edge;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        var eps = getEdge();
        return eps[0] + "->" + eps[1];
    }
}
