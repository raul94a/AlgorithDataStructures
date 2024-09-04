package linkedList

import java.lang.Exception

open class DoublyLinkedList<T> : LinkedList<T>(){
   private var _first: Link<T>? = null
    private var _last: Link<T>? = null

    override fun getFirst(): Link<T>? {
        return _first
    }

     fun setFirst(link: Link<T>?) {
         _first = link
         if (_last == null){
             _last = link
         }
    }

    fun setLast(link: Link<T>?){
        _last = link
        if(_first == null){
            _first = link
        }
    }

    override fun getNext(): Link<T>? {
        return this.getFirst()
    }

    fun setNext(link: Link<T>?) {

        return this.setFirst(link)
    }


    override fun isEmpty(): Boolean {
        return this.getFirst() == null
    }

    override fun first(): T {
        if (isEmpty()) {
            throw Exception("LinkedList is empty")
        }
        return getFirst()!!.getData()
    }

    override fun transverse(callback: (Linkeable<T>) -> Unit) {
        var link: Linkeable<T>? = getFirst()
        while (link != null) {
            callback(link)
            link = link.getNext()
        }
    }

    override fun length(): Int {
        var length = 0
        var link: Linkeable<T>? = getFirst()
        while (link != null) {
            length++
            link = link.getNext()
        }

        return length

    }

    // Implementation of find, search, insert, insertAfter
    override fun find(test: (Linkeable<T>) -> Boolean): Linkeable<T>? {
        var link: Linkeable<T>? = getFirst()
        while (link != null) {
            if (test(link)) return link
            link = link.getNext() as Link<T>?
        }

        return null

    }

    override fun search(test: (Linkeable<T>) -> Boolean): T? {
        return find(test)?.getData()
    }

    override fun insert(value: T) {
        val previousLink = getFirst()
        val newLink = Link(value, previousLink)
        setFirst(newLink)
    }

    fun insertFirst(value: T) {
        val previousLink = getFirst()

        if(isEmpty()){
            _first = Link(value,null)

        }
        else {
           // getFirst().setPrevious()
        }
    }

    override fun insertAfter(value: T, test: (Linkeable<T>) -> Boolean): Boolean {

        val link = find(test) ?: return false

        val newLink = Link(value, link.getNext())
        link.setNext(newLink)
        return true

    }

    override fun deleteFirst(): T? {
        if (isEmpty()) return null

        val link = getFirst()
        val newLink = link?.getNext()
        setFirst(newLink as Link<T>)

        return link.getData()
    }

    override fun delete(test: (Linkeable<T>) -> Boolean): T? {

        if (isEmpty()) throw Exception("LinkedList is empty")
        // We need keep track of the previous value to be tested in order to modify the linked list with
        // the correct next item (without the deleted one)
        var previous: Linkeable<T>? = getFirst()

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
        return "LinkedList(_Link=$_first)"
    }
}

fun main() {
    val doublylinked = LinkedList<Int>()

    doublylinked.setFirst(Link(1))

    println(doublylinked)

    doublylinked.setNext(Link(2))

    println(doublylinked)
}