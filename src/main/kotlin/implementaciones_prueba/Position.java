package implementaciones_prueba;

public class Position<E> {

    private E element;

    public Position(E element) {
        this.element = element;
    }

    public E getElement() { return element; }

    public void setElement(E element) { this.element = element; }
}
