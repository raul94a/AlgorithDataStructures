package linkedList

import org.junit.jupiter.api.Assertions.*

class DoublyLinkedListTest {

    @org.junit.jupiter.api.Test
    fun isEmpty() {
    }

    @org.junit.jupiter.api.Test
    fun getFirst() {
    }

    @org.junit.jupiter.api.Test
    fun getLast() {
    }

    @org.junit.jupiter.api.Test
    fun setFirst() {

        var doublyLink = DoublyLinkedList<Int>()

        val next = DoublyLink(88)
        doublyLink.setFirst(next)

        doublyLink.insertFirst(7)

        val firsts = doublyLink.getFirst()

        print(firsts?.getData())
        print(firsts?.getNext()?.getData())



    }

    @org.junit.jupiter.api.Test
    fun setLast() {
    }

    @org.junit.jupiter.api.Test
    fun traverseBackwards() {
    }

    @org.junit.jupiter.api.Test
    fun insertFirst() {
    }

    @org.junit.jupiter.api.Test
    fun deleteFirst() {
    }

    @org.junit.jupiter.api.Test
    fun deleteLast() {
    }
}