package graphs.wiley_graph;

import priority_queues.Entry;
import priority_queues.HeapAdaptablePriorityQueue;
import priority_queues.HeapPriorityQueue;
import java.util.*;

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
    public List<Vertex<V>> vertices() {
        return this.vertices;
    }

    @Override
    public List<Edge<V>> edges() {
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
//        System.out.println("Insertando el edge " + newEdge);
        u.incoming.put(v,newEdge);
        v.outgoing.put(u,newEdge);
        this.edges.add(newEdge);
        return newEdge;
    }

    // TEST if any problem with outgoings and incomings of v
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

    @Override
    public String toString() {
        for(var v : vertices){
//            System.out.println("Vertice " + v);
//            System.out.println("Values: " + v.outgoing.values());
            for(var e: v.incoming.values()){
                System.out.println(e);
            }
        }
        return "" + vertices;
    }

    /**
     * Shortest-paths
     * @param start
     * @return
     */
    public Map<Vertex<V>,Double> dijkstra(Vertex<V> start){

        Map<Vertex<V>, Double> unvisitedNodes = new HashMap<>();
        Map<Vertex<V>, Double> visitedNodes = new HashMap<>();

        HeapAdaptablePriorityQueue<Double,Vertex<V>> pq = new HeapAdaptablePriorityQueue<>();


        Map<Vertex<V>, Entry<Double,Vertex<V>>> pqTokens = new HashMap<>();



        for (Vertex<V> v: vertices()){
            if(v.equals(start)){
                unvisitedNodes.put(v,0d);
            }
            else{
                unvisitedNodes.put(v,Double.POSITIVE_INFINITY);
            }
            var entry = pq.insert(unvisitedNodes.get(v),v);

            pqTokens.put(v,entry);
        }
        System.out.println("==== Preparación para Dijkstra ======");
        System.out.println("HashMap de inicialización de valores: " + unvisitedNodes);
        System.out.println("HashMap cloud: " + visitedNodes);
        System.out.println("Priority Queue: " + pq);
        System.out.println("=====================================");


        // empezamos a expandir los nodos

        while(!pq.isEmpty()){
            var entry = pq.removeMin();
            Double key = entry.getKey();
            var u = entry.getValue();
            visitedNodes.put(u,key);
            System.out.println("Actualización de cloud: " + visitedNodes);
            pqTokens.remove(u);
            for (var e: outgoingEdges(u)){
                var v = opposite(u,e);
                if(visitedNodes.get(v) == null){
//                    System.out.print("Vertice " + v);
                    double weight = e.getWeight();
                    double tentativeCost = unvisitedNodes.get(u) + weight;
//                    System.out.print(" distancia tentativa: " + tentativeCost + " vs " + " diatancia nodo no visitado " + unvisitedNodes.get(v));


                    if(tentativeCost < unvisitedNodes.get(v)){
                       // System.out.println("Actualizando distancias tentativas: " + v  + " => " + tentativeCost);
                        unvisitedNodes.put(v, tentativeCost);
                        pq.replaceKey(pqTokens.get(v),unvisitedNodes.get(v));
                      //  pq.insert(unvisitedNodes.get(v),v);
                    }
                }
            }
        }

        return visitedNodes;

    }

    public List<V> dijkstraSearch(Vertex<V> start, Vertex<V> end){

        Map<Vertex<V>,Double> distanceMap = this.dijkstra(start);
        System.out.println("FINISH DISTANCE MAP");
        List<V> list = new ArrayList<>();
        list.add(end.getValue());
        Vertex<V> actual = end;
        while(!actual.getId().equals(start.getId())){
            double tentativeCost = distanceMap.get(actual);
            System.out.println("Tentative cost for " + actual.getValue() + ": " + tentativeCost);
            System.out.println("Incoming: " + actual.incoming.values());
            for (var edge: actual.incoming.values()){
                double vertexCost = edge.getWeight();
                System.out.println("Vertex cost: " + vertexCost);
                Vertex<V> incomingVertex = opposite(actual,edge);
                var incomingVertextTentativeCost = distanceMap.get(incomingVertex);
                System.out.println("Temptative Vertex cost: " + incomingVertextTentativeCost + " for " + incomingVertex);
                System.out.println("Vertex cost: " + vertexCost + " for " + incomingVertex);

                if(vertexCost + incomingVertextTentativeCost == tentativeCost){
                    actual = incomingVertex;
                    list.addFirst(incomingVertex.getValue());
                    break;
                }
            }

        }
        return list;
    }

    public Map<Vertex<V>, Edge<V>> dijkstra(Vertex<V> s, Map<Vertex<V>, Double> d){
        Map<Vertex<V>, Edge<V>> tree= new HashMap<>();
        for(var v : d.keySet()){
            if (!v.equals(s)){
                for(var e: incomingEdges(v)){
                    var u = opposite(v,e);
                    var weight = e.getWeight();
                    if(d.get(v) == d.get(u) + weight){
                        tree.put(v,e);
                    }
                }
            }
        }
        return tree;
    }


    public Vertex<V> opposite(Vertex<V> v, Edge<V> edge){
        var endpoints = edge.getEdge();
        if(endpoints[0].equals(v)){
            return endpoints[1];
        }
        else if(endpoints[1].equals(v)){
            return endpoints[0];
        }
        else throw new IllegalStateException("No edge");
    }
}
