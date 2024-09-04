package linkedList

interface Linkeable<T> {

    fun getData(): T

    fun setData(data: T)

    fun getNext(): Linkeable<T>?

    fun setNext(next: Linkeable<T>?)

    fun isLast(): Boolean

}

abstract interface DoublyLinkeable<T> {

    fun getPrevious(): DoublyLinkeable<T>?

    fun setPrevious(link: DoublyLinkeable<T>)

    fun getData(): T

     fun setData(data: T)

     fun getNext(): DoublyLinkeable<T>?

    fun setNext(next: DoublyLinkeable<T>?)

     fun isLast(): Boolean
}