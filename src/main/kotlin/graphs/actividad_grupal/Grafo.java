package graphs.actividad_grupal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Grafo {

    private double[][] matriz;

    private int numNodos;
    private int numAristas;

    private LinkedList<String> claves;
    private LinkedList<NodoGrafo> nodos;

    public Grafo() {

        matriz = new double[10][10];  // Tamaño inicial, si es necesario se duplica el tamaño

        numNodos = 0;
        numAristas = 0;

        claves = new LinkedList<>();
        nodos = new LinkedList<>();

    }

    // MÉTODO DE INSERCIÓN DE UN NUEVO NODO EN EL GRAFO
    // EL NODO SE INSERTA SI NO EXISTE PREVIAMENTE
    // EL NODO SE INSERTA DESCONECTADO DE TODOS LOS DEMÁS
    public boolean insertarNodo(NodoGrafo nodo) {

        String clave = nodo.getClave();   // Obtener la clave del nodo
        double[][] nuevaMatriz = null;    // Matriz por si necesitamos aumentar el tamaño

        // Consultar la existencia de la clave en el grafo
        // Si la clave existe, se sale del método devolviendo false
        if (claves.contains(clave))
            return false;

        // La clave no existe => se inserta el nuevo nodo
        claves.add(clave);    // Añadir la clave a la lista de claves
        nodos.add(nodo);    // Añadir el nodo a la lista de nodos


        // Duplicar el tamaño de la matriz si es necesario
        if (numNodos > matriz.length) {

            nuevaMatriz = new double[numNodos * 2][numNodos * 2];

            // Copiar la matriz actual en la nueva matriz creada
            for (int i = 0; i < numNodos - 1; i++)
                for (int j = 0; j < numNodos - 1; j++)
                    nuevaMatriz[i][j] = matriz[i][j];

            // Actualizar la matriz de adyacencia
            matriz = nuevaMatriz;

        }
        numNodos++;            // Incrementar el número de nodos
        return true;
    }


    // MÉTODO QUE COMPRUEBA SI UNA ARISTA EXISTE EN EL GRAFO
    public boolean existeArista(String clave1, String clave2) {

        // Comprobar la existencia de las claves
        // Si no existe alguna de las claves, se termina el método devolviendo false
        if (!claves.contains(clave1) || !claves.contains(clave2))
            return false;

        int indiceClave1 = claves.indexOf(clave1);
        int indiceClave2 = claves.indexOf(clave2);

        return matriz[indiceClave1][indiceClave2] != 0;

    }


    // MÉTODO QUE AÑADE UNA NUEVA ARISTA AL GRAFO
    // SÓLO SE AÑADE SI EXISTEN LAS CLAVES Y NO EXISTE LA ARISTA
    public boolean insertarArista(String clave1, String clave2, Double peso) {

        // Comprobar la existencia de las claves
        // Si no existe alguna de las claves, se termina el método devolviendo false
        if (!claves.contains(clave1) || !claves.contains(clave2))
            return false;

        // Comprobar la existencia de la arista
        // Es suficiente con comprobar uno de los sentidos de la arista porque
        // la inserción siempre añade los dos sentidos,
        // Si existeArista(clave1, clave2)==true, seguro que existeArista(clave2, clave1)==true
        // Si existe la arista se termina el método devolviendo false
        if (existeArista(clave1, clave2))
            return false;

        // Obtener los índices de las claves para acceder a la matriz
        int indiceClave1 = claves.indexOf(clave1);
        int indiceClave2 = claves.indexOf(clave2);

        // Actualizar la matrid de adyacencia para que tenga la nueva arista
        matriz[indiceClave1][indiceClave2] = peso;
        matriz[indiceClave2][indiceClave1] = peso;

        numAristas++;
        return true;
    }

    // MÉTODO DE BORRADO DE UN NODO EN EL GRAFO
    // AL BORRAR EL NODO SE BORRAN AUTOMÁTICAMANTE LAS ARISTAS ENTRANTES O SALIENTES DEL NODO
    public boolean borrarNodo(String clave) {

        int indice;   // Índice del nodo que se quiere borrar

        // Consultar la existencia de la clave en el grafo
        // Si la clave no existe, se sale del método devolviendo false
        if (!claves.contains(clave))
            return false;

        // Obtener el índice de la clave
        indice = claves.indexOf(clave);

        matriz = reducirMatrizCuadrada(matriz, indice);   // Reducir la matriz

        claves.remove(indice);    // Eliminar la clave a la lista de claves
        nodos.remove(indice);    // Eliminar el nodo a la lista de nodos
        numNodos--;                // Decrementar el número de nodos

        return true;
    }


    // MÉTODO PARA ELIMIMIAR DE UNA MATRIZ CUADRADA UNA FILA Y UNA COLUMNA, AMBAS CON EL MISMO ÍNDICE
    private double[][] reducirMatrizCuadrada(double[][] mat, int indice) {

        // Comprobaciones previas
        if (mat == null || mat.length == 1 || indice < 0 || indice >= mat.length)
            return null;

        // Crear la matriz reducida con una fila y una columna menos que la original
        int len = mat.length;
        double[][] matReducida = new double[len - 1][len - 1];

        int fila = 0;     // índice para recorrer las filas en la matriz reducida
        int columna = 0;  // índice para recorrer las columnas en la matriz reducida

        // Copiar filas y columnas excepto la fila y columna en "indice"
        for (int i = 0; i < len; i++) {
            if (i == indice) continue; // Saltar la fila a eliminar

            for (int j = 0; j < len; j++) {
                if (j == indice) continue; // Saltar la columna a eliminar

                matReducida[fila][columna] = mat[i][j];
                columna++;
            }
            fila++;
        }
        return matReducida;
    }


    // MÉTODO DE BORRADO DE UNA ARISTA
    // SI NO EXISTE LA ARISTA, TERMINA EL MÉTODO Y DEVUELVE FALSE
    // SI EXISTE, SE BORRA Y SE DEVUELVE TRUE
    public boolean borrarArista(String clave1, String clave2) {

        // Comprobar la existencia de las claves
        // Si no existen las dos claves se termina el método devolviendo false
        if (!claves.contains(clave1) && !claves.contains(clave2))
            return false;

        // Comprobar la existencia de la arista
        // Si NO existe la arista se termina el método devolviendo false
        if (!existeArista(clave1, clave2))
            return false;

        // Obtener los índices de las claves
        int indiceClave1 = claves.indexOf(clave1);
        int indiceClave2 = claves.indexOf(clave2);

        // Actualizar la matrid de adyacencia para borrar la arista EN LOS DOS SENTIDOS
        matriz[indiceClave1][indiceClave2] = 0;
        matriz[indiceClave2][indiceClave1] = 0;

        numAristas--;
        return true;
    }

    private ArrayList<String> adyacenciaNodo(String clave) {
        if (!claves.contains(clave)) {
            return null;
        }

        var adyacencia = new ArrayList<String>();

        int indiceNodo = claves.indexOf(clave);

        for (int i = 0; i < numNodos; i++) {
            if (this.matriz[indiceNodo][i] > 0)
                adyacencia.add(claves.get(i));
        }
        return adyacencia;
    }

    // Supuestamente es una implemntación directa del algoritmo visto en clase
    public List<Double> dijkstra(String clave) {
        List<Double> d = new ArrayList<>(this.nodos.size());
        List<String> visitados = new ArrayList<>();
        List<String> noVisitados = new ArrayList<>();
        String actual = "";
        int i;
        int indiceClave = this.claves.indexOf(clave);

        if (indiceClave < 0) {
            return null;
        }

        for (i = 0; i < numNodos; i++) {
            if (i == indiceClave) {
                d.add(0.0);
            } else {
                d.add(Double.POSITIVE_INFINITY);
            }
        }
        noVisitados.add(clave);

        while (!noVisitados.isEmpty()) {
            double costeMenor = Double.POSITIVE_INFINITY;
            double costeNodo = 0;
            int posicionNodo = 0;
            for (String claveNodo : noVisitados) {
                posicionNodo = claves.indexOf(claveNodo);
                costeNodo = d.get(posicionNodo);
                if (costeNodo < costeMenor) {
                    costeMenor = costeNodo;
                    actual = claveNodo;
                }
            }
            noVisitados.remove(actual);

            visitados.add(actual);

            var adyacencia = adyacenciaNodo(actual);

            for (String claveAdyacente : adyacencia) {
                if (!visitados.contains(claveAdyacente)) {
                    int indiceActual = this.claves.indexOf(actual);
                    int indiceAdyacente = this.claves.indexOf(claveAdyacente);

                    double costeTentativo = costeMenor + matriz[indiceActual][indiceAdyacente];
                    double costeDijkstra = d.get(indiceAdyacente);

                    if (costeTentativo < costeDijkstra) {
                        d.set(indiceAdyacente, costeTentativo);
                    }
                    if (!noVisitados.contains(claveAdyacente)) {
                        noVisitados.add(claveAdyacente);
                    }

                }
            }
        }
        return d;

    }

    public List<String> dijkstra(String clave1, String clave2) {

        List<Double> d = this.dijkstra(clave1);
        System.out.println("camino:  " + d);
        ArrayList<String> adyacencia;
        int indiceActual, indiceAdyacente;
        String actual;

        if (!claves.contains(clave1) && !this.claves.contains(clave2)) {
            return null;
        }

        var camino = new LinkedList<String>();

        camino.add(clave2);
        actual = clave2;
        double total = 0;
        while (!actual.equals(clave1)) {
            adyacencia = adyacenciaNodo(actual);
            for (String nodoCamino : camino) {
                adyacencia.remove(nodoCamino);
            }
            indiceActual = claves.indexOf(actual);
            for (String nodoAdyacente : adyacencia) {
                indiceAdyacente = claves.indexOf(nodoAdyacente);

                double costeCamino = d.get(indiceAdyacente) + matriz[indiceActual][indiceAdyacente];
                if (d.get(indiceActual) == costeCamino) {
                    System.out.println("Para nodo " + nodoAdyacente  +  " "  + matriz[indiceActual][indiceAdyacente]);
                    total += matriz[indiceActual][indiceAdyacente];
                    camino.addFirst(nodoAdyacente);
                    actual = nodoAdyacente;
                    break;
                }
            }
        }
        System.out.println("El camino total recorrido es de " + total);
        return camino;
    }


    // MÉTODO QUE DEVUELVE EL NÚMERO DE NODOS DEL GRAFO
    public int numeroNodos() {

        return numNodos;
    }

    // MÉTODO QUE DEVUELVE EL NÚMERO DE ARISTAS DEL GRAFO
    public int numeroAristas() {

        return numAristas;
    }


    // MÉTODO QUE DEVUELVE LA REPRESENTACIÓN EN STRING DEL GRAFO
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        if (numNodos == 0) {
            return "Grafo vacío";
        }

        for (int i = 0; i < numNodos; i++) {
            String clave = claves.get(i);
            sb.append(clave).append(": ");
            for (int j = 0; j < numNodos; j++) {
                if (matriz[i][j] > 0) {
                    sb.append(claves.get(j));
                    sb.append("(");
                    sb.append(matriz[i][j]);
                    sb.append(") ");
                }
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }

}
