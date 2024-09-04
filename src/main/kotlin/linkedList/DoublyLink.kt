package linkedList


class DoublyLink<T>(
    private var data: T,
    private var previous: DoublyLinkeable<T>? = null,
    private var next: DoublyLinkeable<T>? = null
) : DoublyLinkeable<T> {
    override fun getPrevious(): DoublyLinkeable<T>? {
        return previous
    }

    override fun setPrevious(link: DoublyLinkeable<T>) {
        previous = link
    }

    override fun getData(): T {
        return data
    }

    override fun getNext(): DoublyLinkeable<T>? {
        return next
    }

    override fun setNext(next: DoublyLinkeable<T>?) {
        this.next = next
    }


    override fun setData(data: T) {
        this.data = data
    }

    override fun isLast(): Boolean {
        return next == null
    }

    override fun toString(): String {
        return "DoublyLink(data=$data, previous=$previous, next=$next)"
    }

}

