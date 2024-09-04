package trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    //---------------- nested Node class ----------------


    @Override
    public String toString() {
        return "LinkedBinaryTree{" + "root=" + root + ", size=" + size + '}';
    }

    protected static class Node<E> implements Position<E> {
        private E element; // an element stored at this node

        private Node<E> parent; // a reference to the parent node (if any)

        private Node<E> left; // a reference to the left child (if any)

        private Node<E> right; // a reference to the right child (if any)


        /**
         * Constructs a node with the given element and neighbors.
         */
        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }// accessor methods

        public E getElement() {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setElement(E e) {
            element = e;
        }

        public void setParent(Node<E> parentNode) {
            parent = parentNode;
        }

        public void setLeft(Node<E> leftChild) {
            left = leftChild;
        }

        public void setRight(Node<E> rightChild) {
            right = rightChild;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    public LinkedBinaryTree() {
    }

    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    // LinkedBinaryTree instance variables
    protected Node<E> root = null; // root of the tree
    private int size = 0; // number of nodes in the tree

    // constructor

    // nonpublic utility

    /**
     * Validates the position and returns it as a node.
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }
    // accessor methods (not already implemented in AbstractBinaryTree)

    /**
     * Returns the number of nodes in the tree.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     */
    public Position<E> root() {
        return root;
    }

    /**
     * Returns the Position of p's parent (or null if p is root).
     */
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     */
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     */
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }


// update methods supported by this class

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    /**
     * Creates a new left child of Position p storing element e; returns its Position.
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null) throw new IllegalArgumentException("p already has a left child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    /**
     * Creates a new right child of Position p storing element e; returns its Position.
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null) {
            System.out.println(parent.getRight());
            throw new IllegalArgumentException("p already has a right child");
        }
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    /**
     * Replaces the element at Position p with e and returns the replaced element.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    /**
     * Attaches trees t1 and t2 as left and right subtrees of external p.
     */


    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
        size += t1.size() + t2.size();
        if (!t1.isEmpty()) { // attach t1 as left subtree of node
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;

        }
        if (!t2.isEmpty()) { // attach t2 as right subtree of node
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;

        }
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     */


    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2) throw new IllegalArgumentException("p has two children");
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null) child.setParent(node.getParent()); // child’s grandparent becomes its parent
        if (node == root) root = child; // child becomes root
        else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft()) parent.setLeft(child);
            else parent.setRight(child);

        }
        size--;
        E temp = node.getElement();
        node.setElement(null); // help garbage collection
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node); // our convention for defunct node
        return temp;
    }

    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();

        public boolean hasNext() {
            return posIterator.hasNext();
        }

        public E next() {
            return posIterator.next().getElement();
        } // return element!

        public void remove() {
            posIterator.remove();
        }
    }

    /**
     * Returns an iterator of the elements stored in the tree.
     */
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    public Iterable<Position<E>> positions() {
        return preorder();
    }

    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            preorderSubtree(root(), snapshot); // fill the snapshot recursively
        }
        return snapshot;
    }

    /**
     * Adds positions of the subtree rooted at Position p to the given snapshot.
     */
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p); // for preorder, we add position p before exploring subtrees
        for (Position<E> c : children(p))
            preorderSubtree(c, snapshot);
    }

    public void printParanthetic() {

        preorderParanthetic(root, 0);
    }

    private void preorderParanthetic(Position<E> p, int depth) {
        // vamos a usar el preorder alg
        var children = children(p);
        var isRoot = isRoot(p);
        var hasChildren = this.children(p).iterator().hasNext();

        var openElement = (isRoot || hasChildren) ? " (" : "";
        // 1. Pintamos el elemento
        System.out.println((" ".repeat(depth * 2)) + p.getElement() + openElement);
        // 2. Para cada hijo, llamamos a printParanthethic
        for (Position<E> pos : children) {
            preorderParanthetic(pos, depth + 1);
        }
        var closeElement = " ".repeat(depth * 2) + ")";
        if (isRoot || hasChildren) {
            System.out.println(closeElement);
        }


    }

    private int inOrderLeftDepth(Node<E> node, int depth) {
        if (node.getLeft() != null) {

            depth = inOrderLeftDepth(node.getLeft(), depth + 1);
        }

        return depth;

    }

    private void preorderDrawing(Node<E> p, int depth) {
        int leftDepth = inOrderLeftDepth(p, depth);
        final int HORIZONTAL_BRANCH_LENGTH = 5;
        final int VERTICAL_BRANCH_LENGTH = 1;
        // vamos a usar el preorder alg
        var children = children(p);
        var isRoot = isRoot(p);
        var hasChildren = this.children(p).iterator().hasNext();

        // Colocamos el elemento raiz
        if (isRoot) {
            final int horizontalGap = leftDepth * HORIZONTAL_BRANCH_LENGTH;
            System.out.println(" ".repeat(horizontalGap) + p.getElement());
            for (int i = 0; i < VERTICAL_BRANCH_LENGTH; i++) {
                System.out.println(" ".repeat(horizontalGap) + "|");
            }

        }
        final int leftGap = (leftDepth - depth - 1) * HORIZONTAL_BRANCH_LENGTH;
        final String leftGapSpaces = leftGap > 0 ? " ".repeat(leftGap) : "";
        if (p.getLeft() != null) {
            System.out.print(leftGapSpaces + "-".repeat((HORIZONTAL_BRANCH_LENGTH)));

        }
        if (p.getRight() != null) {
            System.out.print(" " + "-".repeat((HORIZONTAL_BRANCH_LENGTH)));
        }
        final Node<E> leftElement = p.getLeft();
        final Node<E> rightElement = p.getRight();
        final boolean isLeftNull = leftElement == null;
        final boolean isRightNull = rightElement == null;
        System.out.println();

        final String verticalBranches = leftGapSpaces + (isLeftNull ? " " : "|") + " ".repeat(HORIZONTAL_BRANCH_LENGTH * 2 - 1) + (isRightNull ? " " : "|");
        System.out.println(verticalBranches);
        final String verticalBranchesValues = leftGapSpaces +
                (isLeftNull ? " " : leftElement.getElement()) +
                " ".repeat(HORIZONTAL_BRANCH_LENGTH * 2 - 1) +
                (isRightNull ? " " : rightElement.getElement()
                );
        System.out.println(verticalBranchesValues);


        // 2. Para cada hijo, llamamos a printParanthethic
        for (Position<E> pos : children) {
            preorderDrawing((Node<E>) pos, depth + 1);
        }


    }

    public void printDraw() {
        preorderDrawing(root, 0);
    }

    // inOrder Algorithm

    private void inOrderSubtree(Node<E> p, List<E> snapshot) {

        if (p.getLeft() != null) {
            inOrderSubtree(p.getLeft(), snapshot);
        }
        snapshot.add(p.getElement());
        if (p.getRight() != null) {
            inOrderSubtree(p.getRight(), snapshot);
        }
    }

    private Iterable<E> inOrder() {
        var snapshot = new ArrayList<E>();
        if (!isEmpty()) {
            inOrderSubtree(root, snapshot);
        }
        return snapshot;
    }

    public Iterable<E> inOrderPositions() {
        return inOrder();
    }

}
