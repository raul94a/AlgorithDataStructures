# Positional Lists

## Introducción

El objetivo es implementar una lista que no dependa de un índice. Para esto
es muy útil pensar en la Linked Lists, ya que funcionan de esta misma forma.
Por tanto, tendremos que ingeniar alguna manera de evitar utilizar índices para
identificar posiciones en la lista. Esto lo podemos hacer con el concepto
de posición o nodo.

En la implementación, podríamos establecer que nuestro nodo esté linkado
únicamente a la primera posición. Esto resultaría en una 
Single Linked List, donde un nodo no tiene referencia al nodo anterior.
No obstante, es de mayor interés que sí haya referencia en ambos sentidos,
formante una double linked list.

Para generar un ADT para la implementación de las Linked Lists,
vamos a crear las interfaces necesarias para las Positional Lists.

* Position
* PositionalList

### LinkedPositionalList

Implementando PositionalList tendremos una mejora en la performance
con respecto a las listas, pero solo en lo que se refiere a la inserción
y la eliminación.

En listas normales, si eliminamos la primera posición (peor caso)
todas las demás posiciones se tienen que mover hacia la izquierda,
por lo que tendríamos una performance aproximada de O(n).

Pasaría igual si insertáramos en la primera posición. Los demás elementos
de la lista deben moverse una posición hacia la derecha, resultando en
una performance de O(n).


| Method                          | Running Time  |  
|---------------------------------|---------------|
| size()                          | O(1)          |
| isEmpty( )                      | O(1)          | 
| first( ), last( )               | O(1)          | 
| before(p), after(p)             | O(1)          | 
| addFirst(e), addLast(e)         | O(1)          | 
| addBefore(p, e), addAfter(p, e) | O(1)          | 
| set(p, e)                       | O(1)          | 
| set(p, e)                       | O(1)          | 