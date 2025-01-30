package graphs;

public class Edge<T> {

    private Integer weight;
    private Vertex<T> start;
    private Vertex<T> end;

    public Edge(Vertex<T> start, Vertex<T> end, Integer weight){
        this.weight = weight;
        this.start = start;
        this.end = end;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Vertex<T> getStart() {
        return start;
    }

    public void setStart(Vertex<T> start) {
        this.start = start;
    }

    public Vertex<T> getEnd() {
        return end;
    }

    public void setEnd(Vertex<T> end) {
        this.end = end;
    }
}
