package linkedList

import java.lang.Exception

class DoubleLinkedList<T> : LinkedList<T>() {
    private var _last: Linkeable<T>? = null

    fun getLast(): Linkeable<T>? {
        return _last
    }

    fun last(): T {
        if (_last == null) throw Exception("No last element in DoubleLinkedList")
        return _last!!.getData()
    }

    override fun setFirst(link: Linkeable<T>?) {
        super.setFirst(link)
        if (getLast() == null) _last = link
    }

    fun insertLast(value: T) {
        if (isEmpty()) {
            insert(value)
            return
        }
        val link = Link(value, null)
        _last?.setNext(link)
        _last = link
    }

    override fun toString(): String {
        return "DoubleLinkedList(${super.toString()} _last=$_last)"
    }

    override fun delete(test: (Linkeable<T>) -> Boolean): T? {

        if (isEmpty()) throw Exception("LinkedList is empty")
        // We need keep track of the previous value to be tested in order to modify the linked list with
        // the correct next item (without the deleted one)
        var previous: Linkeable<T>? = getFirst()

        while (previous?.getNext() != null) {
            // current item to be tested
            val link = previous.getNext() as Linkeable<T>
            if (test(link)) {
                if(link == _last){
                    _last = previous
                }
                // In this case we need to set the next value of the previous item to the correct link
                previous.setNext(link.getNext() as Linkeable<T>?)
                return link.getData()
            }
            previous = link
        }
        return null
    }

    override fun deleteFirst(): T? {
        if (isEmpty()) return null

        val link = getFirst()
        val newLink = link?.getNext()
        setFirst(newLink as Linkeable<T>)

        return link.getData()
    }
   override fun insertAfter(value: T, test: (Linkeable<T>) -> Boolean): Boolean {

        val link = find(test) ?: return false

        val newLink = Link(value, link.getNext())
        if(link == _last){
            insertLast(value)
            return true
        }
        link.setNext(newLink)
        return true

    }
}

fun main() {
    val doublelink = DoubleLinkedList<Int>()
    // Maybe we have to handle the possibility to delete the `last` element if needed.
    // The deletion is only working when we have > 2 elements
    doublelink.setFirst(Link(8, null))
    doublelink.insert(9)


    doublelink.insertLast(1)
//    doublelink.delete {
//        it.getData() == 1
//    }

    doublelink.insertAfter(99){
        it.getData() == 9
    }


    println(doublelink)




}