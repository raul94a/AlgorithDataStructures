package graphs.wiley_graph;

import graphs.actividad_grupal.Gasolinera;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // DECLARACIÓN DEL GRAFO
        AdjacencyMapGraph<Gasolinera> grafo = new AdjacencyMapGraph<>();

        // LECTURA DE LOS DATOS DE LAS GASOLINERAS Y CONSTRUCCIÓN DE LOS NODOS DEL GRAFO
        String directorioProyecto = System.getProperty("user.dir");
        String nombreFicheroGasolineras = "gasolineras.csv";
        String separador = System.getProperty("file.separator");
        String pathFicheroGasolineras = directorioProyecto + separador + nombreFicheroGasolineras;

        String[] datosGasolinera = new String[5];
        Gasolinera gasolinera;

        try (FileReader fr = new FileReader(pathFicheroGasolineras, Charset.forName("ISO-8859-1"))) {
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                datosGasolinera = linea.split(";");
                gasolinera = new Gasolinera(datosGasolinera[2], datosGasolinera[4], datosGasolinera[3], datosGasolinera[0], datosGasolinera[1]);
                grafo.insertVertex(gasolinera);

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


        // LECTURA DE LAS DISTANCIAS ENTRE GASOLINERAS Y CONSTRUCCIÓN DE LAS ARISTAS DEL GRAFO
        String nombreFicheroDistanciasGasolineras = "distancias-gasolineras.csv";
        String pathFicheroDistanciasGasolineras = directorioProyecto + separador + nombreFicheroDistanciasGasolineras;
        String[] datosDistanciaGasolineras;
        var gasolineras = grafo.vertices();
        try (FileReader fr = new FileReader(pathFicheroDistanciasGasolineras, Charset.forName("ISO-8859-1"))) {

            BufferedReader br = new BufferedReader(fr);
            String linea;

            while ((linea = br.readLine()) != null) {
                datosDistanciaGasolineras = linea.split(";");
                var gasolineraA = datosDistanciaGasolineras[0];
                var gasolineraB = datosDistanciaGasolineras[1];
                var weight = Double.parseDouble(datosDistanciaGasolineras[2]);
                var gasolinaraObjA = gasolineras.stream().filter(e -> e.getValue().getClave().equals(gasolineraA)).findAny().get();
                var gasolinaraObjB = gasolineras.stream().filter(e -> e.getValue().getClave().equals(gasolineraB)).findAny().get();

                grafo.insertEdge(gasolinaraObjA,gasolinaraObjB,weight);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

       // System.out.println(grafo);
        var gijon122 = gasolineras.stream().filter(i -> i.getValue().getClave().equals("GIJÓN-122")).findFirst().get();
        var aviles15 =  gasolineras.stream().filter(i -> i.getValue().getClave().equals("AVILÉS-15")).findFirst().get();
//        System.out.println("Gijon 2 "+ gijon2);
        //System.out.println(grafo.dijkstra(gijon122).values().stream().sorted().toList());
        System.out.println(grafo.dijkstraSearch(aviles15,gijon122));
       // System.out.println(grafo.dijkstra(gijon2,grafo.dijkstra(gijon2)).keySet().stream().map(e -> e.getValue().getClave()).toList());
    }
}
