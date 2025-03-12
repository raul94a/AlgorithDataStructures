package graphs.wiley_graph;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMapGraph<V> implements Graph<V>{
    private boolean isDirected = false;
    private List<Vertex<V>> vertices;
    private List<Edge<V>> edges;

    public AdjacencyMapGraph(){
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public AdjacencyMapGraph(boolean directed){
        this.isDirected = directed;
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public int numEdges() {
        return edges.size();
    }

    @Override
    public int outgoingDegree(Vertex<V> v) {
        return v.outgoing.entrySet().size();
    }

    @Override
    public int incomingDegree(Vertex<V> v) {
        return v.incoming.entrySet().size();
    }

    @Override
    public Iterable<Edge<V>> outgoingEdges(Vertex<V> v) {
        return v.outgoing.values();
    }

    @Override
    public Iterable<Edge<V>> incomingEdges(Vertex<V> v) {
        return v.incoming.values();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return this.vertices;
    }

    @Override
    public Iterable<Edge<V>> edges() {
        return this.edges;
    }

    @Override
    public Edge<V> getEdge(Vertex<V> u, Vertex<V> v) {
       return v.incoming.get(u);
    }

    @Override
    public Vertex<V> insertVertex(V value) {
        Vertex<V> newVertex = new Vertex<>(value,isDirected);
        this.vertices.add(newVertex);
        return newVertex;
    }

    @Override
    public Edge<V> insertEdge(Vertex<V> v, Vertex<V> u, Double weight) {
        Edge<V> newEdge;
        if(weight == null){
            newEdge = new Edge<>(v,u);
        }
        else{
            newEdge = new Edge<>(v,u,weight);
        }
        this.edges.addLast(newEdge);
        return newEdge;
    }

    @Override
    public boolean removeVertex(Vertex<V> v) {
        boolean removed =  this.vertices.removeIf(el -> el.getId().equals(v.getId()));
        this.edges.removeIf(e ->{
            var edge = e.getEdge();
            var testId = v.getId();
            return edge[0].getId().equals(testId) || edge[1].getId().equals(testId);
        });
        return removed;
    }

    @Override
    public boolean removeEdge(Edge<V> e) {
        return this.edges.removeIf(test -> test.equals(e));
    }
}
