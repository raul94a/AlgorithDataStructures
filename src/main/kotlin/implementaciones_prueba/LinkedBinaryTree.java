package implementaciones_prueba;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBinaryTree<T> extends AbstractBinaryTree<T> {


    static class Node<T> extends Position<T> {
        private T element;
        private Node<T> parent;
        private Node<T> left;
        private Node<T> right;

        public Node(T e, Node<T> above, Node<T> leftChild, Node<T> rightChild) {
            super(e);
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        @Override
        public T getElement() {
            return element;
        }

        @Override
        public void setElement(T element) {
            this.element = element;
        }

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }
        // Fin clase nested
    }


    protected Node<T> root = null;
    private int size = 0;

    // constructor
    public LinkedBinaryTree() {
    }

    // Factory
    protected Node<T> createNode(T element, Node<T> left, Node<T> right, Node<T> parent) {
        return new Node<>(element, parent, left, right);
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    protected Node<T> validate(Position<T> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Not valid position type");

        }
        Node<T> node = (Node<T>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
        {
            throw new IllegalArgumentException("p is no longer in the tree");
        }


        return node;
    }


    @Override
    public Position<T> parent(Position<T> p) {
        Node<T> node = validate(p);
        return node.getParent();
    }

    @Override
    public Position<T> left(Position<T> p) {
        Node<T> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<T> right(Position<T> p) {
        Node<T> node = validate(p);
        return node.getRight();
    }

    // Specific implementation methods
    void addRoot(T e) throws IllegalStateException {
        if (root == null) {
            Node<T> node = createNode(e, null, null, null);
            root = node;
            size = 1;
        } else {

            throw new IllegalStateException("Tree is not empty");
        }
    }

    public Position<T> addLeft(Position<T> pos, T e) {
        Node<T> node = validate(pos);
        if (node.getLeft() != null) {
            throw new IllegalStateException("Position already has left Position");
        }
        var leftNode = createNode(e, null, null, node);
        node.setLeft(leftNode);
        size++;
        return leftNode;
    }

    public Position<T> addRight(Position<T> pos, T e) {
        Node<T> node = validate(pos);
        if (node.getRight() != null) {
            throw new IllegalStateException("Position already has right Position");
        }
        var rightNode = createNode(e, null, null, node);
        node.setRight(rightNode);
        size++;
        return rightNode;
    }

    public T set(Position<T> pos, T e) {
        Node<T> node = validate(pos);
        T previousValue = node.getElement();
        node.setElement(e);
        return previousValue;
    }

    public void attach(Position<T> p, LinkedBinaryTree<T> t1, LinkedBinaryTree<T> t2) throws IllegalStateException {

        var node = validate(p);
        if (isInternal(p)) throw new IllegalStateException("Must be a leaf");

        // el tamaño debe ser calculado con ambos subarboles
        size += t1.size + t2.size;
        if (!t1.isEmpty()) {
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if (!t2.isEmpty()) {
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }


    public T remove(Position<T> p) throws IllegalStateException {
        var node = validate(p);
        if (numChildren(p) == 2) {
            //ojo que si se podría hacer
            throw new IllegalStateException("Cannot remove this position: has children");
        }
        Node<T> child = (node.getLeft() != null ? node.getLeft() : node.getRight());

        if (child != null) {
            // El abuelo será el padre
            child.setParent(node.parent);
        }

        // casos especiales
        if (node == root) {
            root = child;
        } else {
            Node<T> parent = node.getParent();
            if (parent.getLeft() == node) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }

        size--;
        // deallocation para ayudar a Garbage Collector
        T temp = node.getElement();
        node.setElement(null);
        node.setRight(null);
        node.setLeft(null);
        node.setParent(node);
        return temp;


    }


    public Iterable<T> preorder() {
        List<T> queue = new ArrayList<>();
        preorderSubtree(queue, root);
        return queue;
    }

    public void preorderSubtree(List<T> queue, Position<T> pos) {
        queue.add(pos.getElement());
        for (Position<T> child : children(pos)) {
            var node = validate(child);
            preorderSubtree(queue, child);
        }
    }

    public Iterable<T> postorder() {
        List<T> queue = new ArrayList<>();
        postorderSubtree(queue, root);
        return queue;
    }


    public void postorderSubtree(List<T> queue, Position<T> pos) {
        for (Position<T> child : children(pos)) {
            postorderSubtree(queue, child);
        }
        queue.add(pos.getElement());
    }

    public Iterable<T> breadfirst() {
        List<T> snapshot = new ArrayList<>();
        if (size != 0) {
            Queue<Position<T>> queue = new LinkedBlockingQueue<>();
            queue.add(getRoot());

            while (!queue.isEmpty()) {
                Position<T> p = queue.remove();
                snapshot.add(p.getElement());
                for (Position<T> pos : children(p)) {
                    queue.add(pos);
                }
            }
        }

        return snapshot;
    }

    public Iterable<T> inOrder() {
        List<T> snapshot = new ArrayList<>();
        inorderSubtree(getRoot(), snapshot);
        return snapshot;
    }

    public void inorderSubtree(Position<T> p, List<T> snapshot) {

        var node = validate(p);
        if (node.getLeft() != null) {
            inorderSubtree(node.getLeft(), snapshot);
        }
        snapshot.add(node.getElement());
        if (node.getRight() != null) {
            inorderSubtree(node.getRight(), snapshot);
        }
    }


}
