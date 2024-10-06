package positional_lists.impl

import positional_lists.interfaces.Position
import positional_lists.interfaces.PositionalList

class LinkedPositionalList<E>() : PositionalList<E> {

    // ----- INTERNAL CLASS
    public class Node<E>(
        private var element: E? = null,
        private var prev: Node<E>? = null,
        private var next: Node<E>? = null,
    ) : Position<E> {

        override fun getElement(): E {
            if (element == null) {
                throw NoSuchElementException()
            }
            return element!!
        }


        fun getPrev(): Node<E>? {
            return prev
        }

        fun getNext(): Node<E>? {
            return next
        }

        fun setElement(e: E?) {
            element = e
        }

        fun setPrev(e: Node<E>?) {
            prev = e
        }

        fun setNext(e: Node<E>?) {
            next = e
        }
    }
    // --- END OF INTERNAL CLASS


    private var header: Node<E> = Node(null, null, null)
    private var tail: Node<E> = Node(null, header, null)
    private var size: Int = 0

    // Aquí conseguimos el link doble (tail ya estaba linkado con header (prev)
    // Ahora se consigue que el header tenga un link hacia el siguiente nodo (tail)
    init {
        header.setNext(tail)
    }

    private fun validate(p: Position<E>): Node<E> {
        if (p !is Node<E>) {
            throw IllegalArgumentException("Not valid position")
        }
        val node: Node<E> = p
        if (node.getElement() == null) {
            throw IllegalArgumentException("Node $p is not in the list")
        }
        return node


    }

    private fun position(node: Node<E>?): Position<E>? {
        if (node == tail || node == header || node == null) {
            return null
        }
        return node


    }

    override fun first(): Position<E>? {
        if (header.getNext() == null) {
            return null
        }
        return position(header.getNext()!!)
    }

    override fun last(): Position<E>? {
        if (tail.getPrev() == null) {
            return null
        }
        return position(tail.getPrev()!!)
    }

    override fun before(p: Position<E>): Position<E> {
        var node: Node<E> = validate(p)

        return position(node.getPrev()) ?: throw IllegalArgumentException()
    }

    override fun after(p: Position<E>): Position<E> {
        var node: Node<E> = validate(p)

        return position(node.getNext()) ?: throw IllegalArgumentException()
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun size(): Int {
        return size
    }


    private fun addBetween(e: E, pred: Node<E>?, succ: Node<E>?): Position<E> {
        val newest: Node<E> = Node(e, pred, succ) // create and link a new node
        // Previous <-> new node <-> Next
        pred?.setNext(newest)
        succ?.setPrev(newest)
        size++
        return newest
    }

    override fun addFirst(e: E): Position<E> {
        return addBetween(e, header, header.getNext())
    }

    override fun addLast(e: E): Position<E> {
        return addBetween(e, tail.getPrev(), tail)
    }

    override fun addBefore(p: Position<E>, e: E): Position<E> {
        val node: Node<E> = validate(p)
        return addBetween(e, node.getPrev(), node)
    }

    override fun addAfter(p: Position<E>, e: E): Position<E> {
        val node: Node<E> = validate(p)
        return addBetween(e, node, node.getNext())
    }

    override fun remove(p: Position<E>): E {
        val node: Node<E> = validate(p);
        val predecessor: Node<E>? = node.getPrev()
        val successor: Node<E>? = node.getNext()
        predecessor?.setNext(successor)
        successor?.setPrev(predecessor)
        size -= 1
        val answer: E = node.getElement()
        node.setElement(null)
        node.setNext(null)
        node.setPrev(null)
        return answer
    }

    override fun set(p: Position<E>, e: E): E {
        val node: Node<E> = validate(p)
        val answer: E = node.getElement()
        node.setElement(e)
        return answer
    }


}