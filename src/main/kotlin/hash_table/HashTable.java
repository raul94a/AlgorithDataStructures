package hash_table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class HashTable<K extends Serializable,V> {

    static private class Node<K,V> {
        K key;
        V value;
        protected Node(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString(){
            return "(" + key + "=> " + value +  ")";
        }
    }

    public HashTable(){
        int hashTableInitialLength = 100;
        for (int i = 0; i < hashTableInitialLength; i++){
            hashTable.add(new ArrayList<>());
        }
    }
    ArrayList<ArrayList<Node<K,V>>> hashTable = new ArrayList<>();

    private int hashFunction(K key){
        int length = hashTable.size();
        if(length == 0){
            length = 1;
        }
        int index = key.toString().chars().map((e) -> e).sum() % length;
        System.out.println("Key " + key + " index is " + index);
        return index;
    }

    public void insert(K key, V value){
        final int index = hashFunction(key);
        var list = hashTable.get(index);
        for(int i = 0; i < list.size(); i++){
            var node = list.get(i);
            if(node.key.equals(key)){
                node.value = value;
                return;
            }
        }
        list.add(new Node<>(key, value));
    }

    public V get(K key){
        final int index = hashFunction(key);
        ArrayList<Node<K,V>> list = hashTable.get(index);
        for (Node<K,V> node: list){
            if (node.key.equals(key)){
                return node.value;
            }
        }
        return null;
    }

    public V delete(K key){
        final int index = hashFunction(key);
        ArrayList<Node<K,V>> list = hashTable.get(index);
        int indexToDelete = -1;
        for (int i = 0; i < list.size(); i++){
            var node = list.get(i);
            if (node.key.equals(key)){
                indexToDelete = i;
                break;
            }
        }

        return indexToDelete == -1 ? null  :list.remove(indexToDelete).value;
    }

    @Override
    public String toString(){
        return "HashTable: " + hashTable.stream().filter((e) -> !e.isEmpty()).toList();
    }

    public static void main(String[] args) {
        var hashTable = new HashTable<String, Integer>();
        for (int i = 0; i < 10; i++){
            var uuid = true ? "HOLA"  :UUID.randomUUID().toString();
            hashTable.insert(uuid, i);
        }
        System.out.println(hashTable);
        System.out.println(hashTable.get("HOLA"));
        hashTable.delete("HOLA");
        System.out.println(hashTable);
    }
}
