package positional_lists.interfaces

interface PositionalList<E> {

    fun first(): Position<E>?
    fun last(): Position<E>?
    fun before(p: Position<E>): Position<E>
    fun after(p: Position<E>): Position<E>
    fun isEmpty(): Boolean
    fun size(): Int

    fun addLast(e: E): Position<E>
    fun addFirst(e: E): Position<E>
    fun addBefore(p: Position<E>,e: E): Position<E>
    fun addAfter(p: Position<E>,e: E): Position<E>

    fun set(p: Position<E>, e: E): E
    fun remove(p: Position<E>): E


}