package linkedList

open class Link<T>(
    private var data: T,
    private var next: Linkeable<T>? = null
) : Linkeable<T> {

    override fun getData(): T {
        return data
    }

    override fun getNext(): Linkeable<T>? {
        return next
    }

    override fun setNext(next: Linkeable<T>?) {
        this.next = next
    }

    override fun setData(data: T) {
        this.data = data
    }

    override fun isLast(): Boolean {
        return next == null
    }

    override fun toString(): String {
        return "Link(data=$data, linkedList.Link: $next)"
    }

}

class DoublyLink<T>(
    private var data: T,
    private var previous: Linkeable<T>? = null,
    private var next: Linkeable<T>? = null
): Linkeable<T> {
    override fun getData(): T {
        return data
    }

    override fun getNext(): Linkeable<T>? {
        return next
    }
    override fun setNext(next: Linkeable<T>?) {
        this.next = next
    }
    fun getPrevious(): Linkeable<T>? {
        return previous
    }
    fun setPrevious(previous: Linkeable<T>?) {
        this.previous = previous
    }

    override fun isLast(): Boolean {
        return next == null
    }

    fun isFirst(): Boolean = previous == null

    override fun setData(data: T) {
       this.data = data
    }

    override fun toString(): String {
        return "DoublyLink(data=$data, previous=$previous, next=$next)"
    }

}
