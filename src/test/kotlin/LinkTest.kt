import org.junit.jupiter.api.Assertions.*

class LinkTest {
    private val link: Link<Int> = Link(1, null)

    @org.junit.jupiter.api.Test
    fun getData() {
        assertEquals(link.getData(),1)
    }

    @org.junit.jupiter.api.Test
    fun getNext() {
        assertEquals(link.getNext(),null)
    }

    @org.junit.jupiter.api.Test
    fun setNext() {
        link.setNext(Link(99,null))
        assertNotEquals(link.getNext(), null)
        assertEquals(link.getNext()!!.getData(),99)
    }

    @org.junit.jupiter.api.Test
    fun setData() {
        link.setData(100)
        assertEquals(link.getData(),100)
    }

    @org.junit.jupiter.api.Test
    fun isLast() {
        assertEquals(link.isLast(),true)
        link.setNext(Link(99,null))
        assertEquals(link.isLast(),false)
        assertEquals(link.getNext()!!.isLast(),true)
    }
}