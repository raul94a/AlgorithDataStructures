package implementaciones_prueba;

public class AVLTree extends AbstractBinaryTree<Integer> {

    @Override
    public String toString() {
        return "AVLTree{" +
                "root=" + root +
                '}';
    }

    static class Node extends Position<Integer> {
        private Integer element;
        private Node parent;
        private Node left;
        private Node right;

        public Node(Integer e, Node above, Node leftChild, Node rightChild) {
            super(e);
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        @Override
        public Integer getElement() {
            return element;
        }

        @Override
        public void setElement(Integer element) {
            this.element = element;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    ", element=" + element +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
        // Fin clase nested
    }

    Node root = null;


    private Node createNode(Integer i){
        return new Node(i,null,null,null);
    }

    @Override
    public Position<Integer> left(Position<Integer> p) {
        return null;
    }

    @Override
    public Position<Integer> right(Position<Integer> p) {
        return null;
    }

    public void insert(Integer i){
        if(root == null){
            root = createNode(i);
        }
        else {
            Node current = root;
            Node parent = null;
            boolean isLeft = false;
            while(current != null){
               Integer value = current.element;
                parent = current;
                if(i >= value){
                    isLeft = false;
                    current = current.getRight();

               }
               else{
                   isLeft = true;
                   current = current.getLeft();
               }
            }

            current = createNode(i);
            if(isLeft){
                parent.setLeft(current);
            }
            else{
                parent.setRight(current);
            }
            current.setParent(parent);

        }
        // después de la inserción se rebalancea
    }

}
