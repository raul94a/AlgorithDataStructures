import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class LinkedListTest {
    val linkedList = LinkedList<Int>()

    @Test
    fun getFirst() {
    }

    @Test
    fun setFirst() {
    }

    @Test
    fun getNext() {
    }

    @Test
    fun setNext() {
    }

    @Test
    fun isEmpty() {
    }

    @Test
    fun first() {
    }


    @Test
    fun transverse() {
        val link = Link(1, Link(2, null))
        linkedList.setFirst(link)
        linkedList.transverse {
            val data = it.getData()
            val str = "Data is $data"
            println(str)
        }
    }

    @Test
    fun length() {
        assertEquals(linkedList.length(), 0)
        val link = Link(1, Link(2, null))
        linkedList.setFirst(link)
        val length = linkedList.length()
        assertEquals(length, 2)
    }

    @Test
    fun find() {
        val link = Link(1, Link(2, null))
        linkedList.setFirst(link)

        var value = linkedList.find {
            it.getData() == 3
        }
        assertEquals(value, null)

        value = linkedList.find {
            it.getData() == 1
        }
        assertEquals(value!!.getNext()!!.getData(), 2)


    }

    @Test
    fun search() {
        val link = Link(1, Link(2, null))
        linkedList.setFirst(link)

        val value = linkedList.search {
            it.getData() == 2
        }
        assertEquals(value, 2)
    }

    @Test
    fun insert() {
        val link = Link(1, Link(2, null))
        linkedList.setFirst(link)

        linkedList.insert(90)
        assertEquals(linkedList.getFirst()?.getData(), 90)
        assertEquals(linkedList.getFirst()?.getNext()?.getData(), 1)

    }

    @Test
    fun insertAfter() {
        val link = Link(1, Link(2, Link(66, null)))
        linkedList.setFirst(link)
        linkedList.insertAfter(100) {
            it.getData() == 2
        }
        val first = linkedList.getFirst()
        assertEquals(first?.getData(), 1)
        assertEquals(first?.getNext()?.getData(), 2)
        assertEquals(first?.getNext()?.getNext()?.getData(), 100)
        assertEquals(first?.getNext()?.getNext()?.getNext()?.getData(), 66)
        assertEquals(first?.getNext()?.getNext()?.getNext()?.getNext(), null)

    }

    @Test
    fun insertAfterFail() {
        val link = Link(1, Link(2, Link(66, null)))
        linkedList.setFirst(link)
        val hasInserted = linkedList.insertAfter(100) {
            it.getData() == 2555
        }
        assertEquals(hasInserted,false)
    }

    @Test
    fun delete() {
        val link = Link(1, Link(2, Link(66, null)))
        linkedList.setFirst(link)
        linkedList.insertAfter(100) {
            it.getData() == 2
        }

        val deletedLink = linkedList.delete {
            it.getData() == 2
        }
        assertEquals(deletedLink, 2)
        assertEquals(linkedList.length(),3)

    }
    @Test
    fun deleteFirstElementWithDelete() {
        val link = Link(1, Link(2, Link(66, null)))
        linkedList.setFirst(link)
        linkedList.insertAfter(100) {
            it.getData() == 2
        }
        linkedList.insertAfter(101) {
            it.getData() == 100
        }
        linkedList.insertAfter(67) {
            it.getData() == 66
        }

        val deletedLink = linkedList.delete {
            it.getData() ==  67
        }

        assertEquals(deletedLink, 67)
        assertEquals(linkedList.length(),5)
        assertEquals(linkedList.first(), 1)

    }
    @Test
    fun deleteLastElementWithDelete() {
        val link = Link(1, Link(2, Link(66, null)))
        linkedList.setFirst(link)
        linkedList.insertAfter(100) {
            it.getData() == 2
        }
        linkedList.insertAfter(101) {
            it.getData() == 100
        }

        val deletedLink = linkedList.delete {
            it.getData() == 66
        }
        linkedList.transverse { println(it) }
        assertEquals(deletedLink, 66)
        assertEquals(linkedList.length(),4)
        assertEquals(linkedList.first(), 1)

        var lastLink = linkedList.getFirst()
        while (lastLink?.getNext() != null){
            lastLink = lastLink.getNext() as Link
        }

        assertEquals(lastLink?.getData(), 101)

    }
}