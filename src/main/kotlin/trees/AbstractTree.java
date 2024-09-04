package trees;

abstract public class AbstractTree<E> implements Tree<E> {
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }

    public boolean isExternal(Position<E> p) {
        return numChildren(p) == 0;
    }

    public boolean isRoot(Position<E> p) {
        return p == root();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    int height(Position<E> position){
        int h = 0;
        for (Position<E> p : children(position)){
            h = 1 + height(p);
        }
        return h;
    }
}
