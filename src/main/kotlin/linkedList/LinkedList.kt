package linkedList

import java.lang.Exception

open class LinkedList<T> {
    private var _first: Linkeable<T>? = null

    open fun getFirst(): Linkeable<T>? {
        return _first
    }

    open fun setFirst(link: Linkeable<T>?) {
        this._first = link
    }

    open fun getNext(): Linkeable<T>? {
        return this.getFirst()
    }

    open fun setNext(link: Linkeable<T>?) {
        return this.setFirst(link)
    }


    open fun isEmpty(): Boolean {
        return this.getFirst() == null
    }

    open fun first(): T {
        if (isEmpty()) {
            throw Exception("LinkedList is empty")
        }
        return getFirst()!!.getData()
    }

    open fun transverse(callback: (Linkeable<T>) -> Unit) {
        var link: Linkeable<T>? = getFirst()
        while (link != null) {
            callback(link)
            link = link.getNext()
        }
    }

    open fun length(): Int {
        var length = 0
        var link: Linkeable<T>? = getFirst()
        while (link != null) {
            length++
            link = link.getNext()
        }

        return length

    }

    // Implementation of find, search, insert, insertAfter
    open fun find(test: (Linkeable<T>) -> Boolean): Linkeable<T>? {
        var link: Linkeable<T>? = getFirst()
        while (link != null) {
            if (test(link)) return link
            link = link.getNext() as Linkeable<T>?
        }

        return null

    }

    open fun search(test: (Linkeable<T>) -> Boolean): T? {
        return find(test)?.getData()
    }

    open fun insert(value: T) {
        val previousLink = getFirst()
        val newLink = Link(value, previousLink)
        setFirst(newLink)
    }

    open fun insertAfter(value: T, test: (Linkeable<T>) -> Boolean): Boolean {

        val link = find(test) ?: return false

        val newLink = Link(value, link.getNext())
        link.setNext(newLink)
        return true

    }

    open fun deleteFirst(): T? {
        if (isEmpty()) return null

        val link = getFirst()
        val newLink = link?.getNext()
        setFirst(newLink as Linkeable<T>)

        return link.getData()
    }

    open fun delete(test: (Linkeable<T>) -> Boolean): T? {

        if (isEmpty()) throw Exception("LinkedList is empty")
        // We need keep track of the previous value to be tested in order to modify the linked list with
        // the correct next item (without the deleted one)
        var previous: Linkeable<T>? = getFirst()

        while (previous?.getNext() != null) {
            // current item to be tested
            val link = previous.getNext() as Linkeable<T>
            if (test(link)) {
                // In this case we need to set the next value of the previous item to the correct link
                previous.setNext(link.getNext() as Linkeable<T>?)
                return link.getData()
            }
            previous = link
        }
        return null
    }

    override fun toString(): String {
        return "LinkedList(_first=$_first)"
    }
}



