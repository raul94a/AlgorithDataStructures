package graphs.wiley_graph;

public interface Graph<V> {
    int numVertices();
    int numEdges();
    int outgoingDegree(Vertex<V> v);
    int incomingDegree(Vertex<V> v);

    Iterable<Edge<V>> outgoingEdges(Vertex<V> v);
    Iterable<Edge<V>> incomingEdges(Vertex<V> v);
    Iterable<Vertex<V>> vertices();
    Iterable<Edge<V>> edges();
    Edge<V> getEdge(Vertex<V> u, Vertex<V> v);

    Vertex<V> insertVertex(V value);
    Edge<V> insertEdge(Vertex<V> v, Vertex<V> u, Double weight);

    boolean removeVertex(Vertex<V> v);
    boolean removeEdge(Edge<V> e);

}
