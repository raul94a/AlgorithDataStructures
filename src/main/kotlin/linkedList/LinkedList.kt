package linkedList

import java.lang.Exception

open class LinkedList<T> {
    private var _first: Link<T>? = null

    fun getFirst(): Link<T>? {
        return _first
    }

    open fun setFirst(link: Link<T>?) {
        this._first = link
    }

    fun getNext(): Link<T>? {
        return this.getFirst()
    }

    fun setNext(link: Link<T>?) {
        return this.setFirst(link)
    }


    fun isEmpty(): Boolean {
        return this.getFirst() == null
    }

    fun first(): T {
        if (isEmpty()) {
            throw Exception("LinkedList is empty")
        }
        return getFirst()!!.getData()
    }

    fun transverse(callback: (Linkeable<T>) -> Unit) {
        var link: Linkeable<T>? = getFirst()
        while (link != null) {
            callback(link)
            link = link.getNext()
        }
    }

    fun length(): Int {
        var length = 0
        var link: Linkeable<T>? = getFirst()
        while (link != null) {
            length++
            link = link.getNext()
        }

        return length

    }

    // Implementation of find, search, insert, insertAfter
    fun find(test: (Link<T>) -> Boolean): Link<T>? {
        var link: Link<T>? = getFirst()
        while (link != null) {
            if (test(link)) return link
            link = link.getNext() as Link<T>?
        }

        return null

    }

    fun search(test: (Link<T>) -> Boolean): T? {
        return find(test)?.getData()
    }

    fun insert(value: T) {
        val previousLink = getFirst()
        val newLink = Link(value, previousLink)
        setFirst(newLink)
    }

    open fun insertAfter(value: T, test: (Link<T>) -> Boolean): Boolean {

        val link = find(test) ?: return false

        val newLink = Link(value, link.getNext())
        link.setNext(newLink)
        return true

    }

    open fun deleteFirst(): T? {
        if (isEmpty()) return null

        val link = getFirst()
        val newLink = link?.getNext()
        setFirst(newLink as Link<T>)

        return link.getData()
    }

    open fun delete(test: (Link<T>) -> Boolean): T? {

        if (isEmpty()) throw Exception("LinkedList is empty")
        // We need keep track of the previous value to be tested in order to modify the linked list with
        // the correct next item (without the deleted one)
        var previous: Link<T>? = getFirst()

        while (previous?.getNext() != null) {
            // current item to be tested
            val link = previous.getNext() as Link<T>
            if (test(link)) {
                // In this case we need to set the next value of the previous item to the correct link
                previous.setNext(link.getNext() as Link<T>?)
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



