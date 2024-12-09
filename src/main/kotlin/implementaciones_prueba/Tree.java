package implementaciones_prueba;

import java.util.List;

public interface Tree<E> {


    Position<E> root();

    Position<E> parent(Position<E> p);

    List<Position<E>> children(Position<E> p);

    int numChildren(Position<E> p);

    int size();

    boolean isInternal(Position<E> p);

    boolean isExternal(Position<E> p);

    boolean isRoot(Position<E> p);

    boolean isEmpty();

    Iterable<Position<E>> iterator();

    Iterable<Position<E>> positions();
}
