package bst;

public interface ITree<E> {

    int size();
    int height();
    boolean isEmpty();
    boolean contains(E e);
    E rootValue();
    Node<E> leftSubtree(E e);
    Node<E> rightSubtree(E e);
}
