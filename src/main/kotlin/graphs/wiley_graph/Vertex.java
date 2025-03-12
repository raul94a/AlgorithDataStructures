package graphs.wiley_graph;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class Vertex<V> {
    private String id;
    private V value;
    HashMap<Vertex<V>, Edge<V>> incoming, outgoing;

    public Vertex(@NotNull V value, boolean isDirected){
        this.value = value;
        this.id = UUID.randomUUID().toString();
        outgoing = new HashMap<>();
        if(isDirected){
            incoming = new HashMap<>();
        }
        else {incoming = outgoing;}

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
