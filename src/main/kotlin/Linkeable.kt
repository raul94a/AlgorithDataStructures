interface Linkeable<T> {

    fun getData(): T

    fun setData(data: T)

    fun getNext(): Linkeable<T>?

    fun setNext(next: Linkeable<T>?)

    fun isLast(): Boolean

}