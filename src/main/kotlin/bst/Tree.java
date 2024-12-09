package bst;

public class Tree {

    public Node<Integer> root;

    public Tree(Integer e) {
        root = createNode(e, null);
    }

    private Node<Integer> createNode(Integer e, Node<Integer> parent) {
        return new Node<>(e, parent, null, null);
    }


    boolean isEmpty() {
        return root == null;
    }




    void insert(Integer e) {
        if (isEmpty()) {
            root = createNode(e, null);
        } else {

        }

    }

    void delete(Integer e) {

    }


}
