package implementaciones_prueba;

public class MainAVL {
    public static void main(String[] args) {

        var avlTree = new AVLTree()
                ;

        avlTree.insert(10);
        System.out.println(avlTree);
        avlTree.insert(20);
        System.out.println(avlTree);
        avlTree.insert(5);
        System.out.println(avlTree);
        avlTree.insert(12);
        System.out.println(avlTree);


    }
}
