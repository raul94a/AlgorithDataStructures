class Link<T>(
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
        return "Link(data=$data, Link: $next)"
    }

}
