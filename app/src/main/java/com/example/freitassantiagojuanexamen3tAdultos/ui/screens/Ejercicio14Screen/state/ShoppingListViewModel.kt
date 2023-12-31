package com.example.freitassantiagojuanexamen3tAdultos.ui.screens.Ejercicio14Screen.state

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ShoppingListViewModel : ViewModel() {
    private val _list = mutableStateListOf<ShoppingProduct>()
    //    .apply { addAll(getDummyShoppingProducts()) }  // (2)
    val list get() = _list  // (1)

    private fun add(item: ShoppingProduct) =
        _list.add(item) // (3)

    fun add(productString: String) =  // (4)
        if (_list.none { productString == it.productName }) //Esta es la sentencia que comprueba que el producto no esté duplicado
            add(ShoppingProduct(productString))
        else false  // (3)


    fun remove(item: ShoppingProduct) {
        _list.remove(item)
    }

    fun changeChecked(product: ShoppingProduct) {
        product.checked = !product.checked
    }

}

/**
 * (1)
 *
 * Definimos una propiedad interna _list que solo será modificable desde dentro del ViewModel y
 * otra list, que será la que expongamos hacia fuera (pública y de solo lectura).
 * Esto se hace para no poder modificar la lista desde fuera del ViewModel.
 *
 * list es una API pública, mientras que _list es un detalle de implementación.
 *
 * https://developer.android.com/codelabs/jetpack-compose-state#11
 * https://stackoverflow.com/a/68016417
 *
 *
 * (2)
 *
 * Añadimos elementos para poder testear. Es importante hacerlo así si queremos poder añadir
 * usar la misma lista y añadirle elementos.
 * Si usamos getDummyShoppingProducts().toMutableList(), la primera función añade elementos usando
 * unas claves, pero al convertir a MutableList es otro objetov y no se mantiene la misma lista de
 * AtomicInteger.
 *
 * La scope function apply crea un contexto donde "this" es el objeto desde el que se llama y que
 * devuelve el mismo objeto después de ser modificado al ejecutar el bloque. Necesitamos utilizarla
 * para poder modificar la lista pero asignarla a la variable _list. La alternativa en estilo
 * imperativo sería:

private var _list = mutableStateListOf<ShoppingProduct>()
init { list.addAll(getDummyShoppingProducts()) }

 * donde init define un bloque que se ejecuta al instanciar la clase (simplifica sobreescribir el
 * constructor)
 *
 *
 * (3)
 *
 * Aplico la lógica de las collections en Java: el método add de cualquier colection devuelve true
 * si esa colección de datos ha cambiado tras la operación. En una lista, siempre devuelde true, pero
 * en un Set (conjunto) devolvería false si ese elemento ya pertenece al Set ya que, por definición,
 * un conjunto no permite elementos duplicados.
 *
 * https://stackoverflow.com/questions/24173117/why-does-list-adde-return-boolean-while-list-addint-e-returns-void
 *
 * Por eso en este caso opto por devolver false si el elemento ya devuelve en la lista.
 *
 * Podría hacerse más explícito utilizando una excepción personalizada:
 *
fun addListElement(item: ShoppingListProduct) {
    shoppingList.find { item.productName == it.productName }?.let {
        throw ProductAlreadyExistsException()
    } ?: shoppingList.add(item)
}
class ProductAlreadyExistsException : RuntimeException()
 *
 * Además, para mejorar la legibilidad, en lugar de find, utilizo la función none:
 * https://kotlinlang.org/docs/collection-filtering.html#test-predicates
 *
 * Se está, por tanto, utilizando una lista y superponiéndole la lógica de un Set. Cabría plantearse
 * entonces utilizar directamente un Set. Sin embargo, no he encontrado que exista un "mutableStateSet".
 *
 * Imagino que será posible utilizar un Set encapsulado con LiveData o Flows (TODO)
 *
 *
 * (4)
 *  Misma función en estilo imperativo:

fun addProductImperativeStyle(productString: String): Boolean {
    if (_list.none { productString == it.productName })
        return add(ShoppingProduct(productString))
    else return false
}

 *
 *
*/
