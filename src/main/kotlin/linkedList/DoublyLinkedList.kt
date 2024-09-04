package linkedList

class DoublyLinkedList<T>(
    private var first: DoublyLink<T>? = null,
    private var last: DoublyLink<T>? = null
) {
    fun isEmpty(): Boolean {
        return first == null
    }
    fun getFirst(): DoublyLink<T>? {
        return first
    }

    fun getLast(): DoublyLink<T>? = last

    fun setFirst(link: DoublyLink<T>?) {
        first = link
        if (last == null) {
            last = link
        }
    }

    fun setLast(link: DoublyLink<T>?){
        last = link
        if(first == null){
            first = link
        }
    }

    fun traverseBackwards(){
        // TODO: Pasarle una función como parámetro para procesar cada elemento de la lista
    }

    fun insertFirst(value: T){
        val newLink = DoublyLink<T>(value, next = this.getFirst())
        if(isEmpty()){
            setLast(newLink)
        }else{
            getFirst()!!.setPrevious(newLink)
        }

    }

    fun deleteFirst(){
        if(isEmpty()){
            throw Exception("Cannot delete first element because object is empty")
        }
        val firstLink : DoublyLink<T>? = getFirst()
        first = firstLink?.getNext() as DoublyLink<T>?
        last = first
    }
    fun deleteLast(){
        if(isEmpty()){
            throw Exception("Cannot delete first element because object is empty")
        }
        val lastLink : DoublyLink<T>? = getLast()
        setLast(lastLink?.getPrevious() as DoublyLink<T>?)

    }

    override fun toString(): String {
        return "DoublyLinkedList(first=$first, last=$last)"
    }


}