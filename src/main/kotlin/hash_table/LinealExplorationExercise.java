package hash_table;


import kotlin.text.MatchGroup;
import kotlin.text.Regex;

import java.util.Arrays;
import java.util.IllegalFormatWidthException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * En una tabla hash cerrada con gestión de colisiones mediante exploración lineal,
 * implementada sobre un vector de soporte de tamaño 11, se almacena información de vehículos,
 * en concreto, el nombre de sus propietarios, siendo la clave de cada vehículo su matrícula.
 * Una matrícula está formada por 3 letras mayúsculas y 4 dígitos.
 * La función hash se define como la suma de los valores ASCII de las letras más la suma de los 4 dígitos módulo 11.
 * Por ejemplo:
 * hash (ABB-8888) = (65+66+66+8+8+8+8) mod 11 = 9
 *
 */
public class LinealExplorationExercise {

    public static void main(String[] args) {
        var carPlateHashTable = new CarPlateHashTable();

        carPlateHashTable.insert("ABB-8888","Ana");
        carPlateHashTable.insert("BBB-1100","Daniel");
        carPlateHashTable.insert("AAA-0001","Laura");
        carPlateHashTable.insert("CCC-6000","Alberto");
        carPlateHashTable.insert("CCC-6000","Alberto II");
        carPlateHashTable.insert("CCC-6000","Alberto III");
        carPlateHashTable.insert("CCC-6000","Alberto III");



        System.out.println(carPlateHashTable);


    }
}

class CarPlateHashTable extends HashTableLinealExploration<String, String> {


    @Override
    public int hashFunction(String key) {
        Pattern pattern = Pattern.compile("^[A-Z]{3}-[0-9]{4}$");
        Matcher matcher = pattern.matcher(key);
        if(!matcher.find()){
            throw new IllegalStateException("Plate format must be XYZ-NNNN");

        }
        var strings = key.split("-");

        var letters = strings[0];
        var numbers = 0;
        int sumLetter = (letters.chars().sum());
        for (int i = 0; i < strings[1].length(); i++){
            char c = strings[1].charAt(i);
            numbers += Integer.parseInt(String.valueOf(c));
        }
        System.out.println("Sum letters " + sumLetter);
        System.out.println("Numbers " + numbers);
        int hash = (sumLetter + numbers) % vector.length;
        System.out.println("Hash para " + key + " es " + hash);
        return hash;
    }
}


abstract class HashTableLinealExploration<K,V> {

    static class Element<K,V> {
        K key;
        V value;
        public Element(K k, V v){
            this.key = k;
            this.value = v;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    protected Element<K,V>[] vector = new Element[11];



    public HashTableLinealExploration(){}


    abstract public int hashFunction(K k);
    public void insert(K k, V v){
        int length = vector.length;
        int hashIndex = hashFunction(k);
        boolean explored = false;

        for (int i = 0 ; i < length; i++){
            int nextIndex = hashIndex + i;

            if(nextIndex > length - 1){
                if(explored){
                    break;
                }
                hashIndex = 0;
                i = 0;
                explored = true;
                nextIndex = hashIndex;
            }
            System.out.println("For key " + k + " nextIndex is: " + nextIndex );
            var element = vector[nextIndex];
            if (element == null){
                vector[nextIndex] = new Element<>(k, v);
                return;
            }
        }

        throw new IllegalStateException("Suppport vector is full!");

    }

    /*public V delete(K k){
        return (V) -1;
    }

    public V get(K k){}*/


    @Override
    public String toString() {
        return "HashTableLinealExploration{" +
                "vector=" + Arrays.toString(vector) +
                '}';
    }
}
