package com.example.freitassantiagojuanexamen3tAdultos.ui.screens.Ejercicio14Screen.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.concurrent.atomic.AtomicInteger

class ShoppingProduct(
    val productName: String,
    val key: Int = identifier.incrementAndGet(), // (3)
) {
    var checked by mutableStateOf(false)   // (1)

    companion object { // (2)
        val identifier: AtomicInteger = AtomicInteger(0)  // (3)

        fun getDummyShoppingProducts() =
            List(4) { i -> ShoppingProduct(productName = "Producto $i") }
    }
}

/**
 * (1) La clase ShoppingProduct tiene la definición de key y name como propiedades inmutables
 * en su constructor primario, pero checked pasa a ser una propiedad mutable que podrá ser
 * modificada desde el viewModel. La alternativa sería eliminar y añadir de nuevo un elemento cuando
 * este se modifica. Esto obliga a que la clase no pueda ser una data class.
 *
 *
 * (2) Kotlin utiliza companion objects para encapsular miembros de clase (lo que en java serían static).
 * Se trata de un objeto que acompaña a la clase y por tanto contiene todos lo miembros de clase,
 * siendo ese objeto común a todos los objetos de la clase.
 * En este caso. El índice de identificadores es común a todos los objetos de la clase.
 *
 *
 * (3) Variable autoincrementada
 * https://stackoverflow.com/questions/24305830/java-auto-increment-id
 * https://stackoverflow.com/questions/70309293/each-instance-of-class-worker-should-have-an-own-id-starting-at-1-and-get-inc
 *
 */