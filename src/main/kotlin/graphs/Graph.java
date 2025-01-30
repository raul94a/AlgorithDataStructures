package graphs;

import java.util.ArrayList;
import java.util.List;

public class Graph<T> {

    private boolean isWeighted;
    private boolean isDirectional;
    private List<Vertex<T>> vertices;

    public Graph(boolean isWeighted, boolean isDirectional) {
        this.isWeighted = isWeighted;
        this.isDirectional = isDirectional;
        this.vertices = new ArrayList<>();
    }

    public boolean isWeighted() {
        return isWeighted;
    }

    public void setWeighted(boolean weighted) {
        isWeighted = weighted;
    }

    public boolean isDirectional() {
        return isDirectional;
    }

    public void setDirectional(boolean directional) {
        isDirectional = directional;
    }

    public List<Vertex<T>> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex<T>> vertices) {
        this.vertices = vertices;
    }

    public Vertex<T> addVertex(T data) {
        var newVertex = new Vertex<>(data);
        this.vertices.add(newVertex);
        return newVertex;
    }

    public void removeVertex(Vertex<T> v) {
        for (var vertex : this.vertices) {
            vertex.removeEdge(v);
        }
        vertices.removeIf(vertex -> vertex == v);


    }

    public void addEdge(Vertex<T> a, Vertex<T> b, Integer weight) {
        if (!isWeighted) {
            weight = null;
        }
        a.addEdge(b, weight);
        if (!isDirectional) {
            b.addEdge(a, weight);
        }
    }

    public boolean removeEdge(Vertex<T> start, Vertex<T> end) {
        boolean removed = start.removeEdge(end);
        if (!isDirectional) {
            removed = end.removeEdge(start);
        }
        return removed;
    }


    public void print() {
        for (var a : vertices) {

            System.out.println("" + a.getData() + " -------> ");
            for (var e : a.getEdges()) {
                System.out.println("\t\t\t " + e.getEnd().getData() + " (" + (e.getWeight() +
                        ")"));
            }
        }
        System.out.println(
        );
        System.out.println();
    }


    public static void main(String[] args) {
        var graph = new Graph<String>(true, false);

        var martosCenter = graph.addVertex("Martos");
        var jaenCenter = graph.addVertex("Jaén");
        var granadaCenter = graph.addVertex("Granada");
        var madrid = graph.addVertex("Madrid");

        graph.addEdge(martosCenter, jaenCenter, 23);
        graph.addEdge(granadaCenter,jaenCenter,100);
        graph.addEdge(martosCenter,granadaCenter,120);
        graph.addEdge(madrid,jaenCenter,300);
       // graph.addEdge(jaenCenter,granadaCenter,140);
        graph.print();

        graph.removeEdge(madrid,jaenCenter);
        graph.print();


    }
}
