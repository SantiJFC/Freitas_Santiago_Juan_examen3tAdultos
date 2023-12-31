package com.example.freitassantiagojuanexamen3tAdultos.ui.screens.Ejercicio14Screen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.freitassantiagojuanexamen3tAdultos.R
import com.example.freitassantiagojuanexamen3tAdultos.ui.screens.Ejercicio14Screen.state.ShoppingListViewModel

@Composable
fun Ejercicio14Screen() {
    val viewModel: ShoppingListViewModel = viewModel()  // (1)

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
    ) { paddingValues ->

        Column(Modifier.padding(paddingValues)) {

            AddBlock { viewModel.add(it) }
            ShoppingList(
                list = viewModel.list,
                onChangeChecked = { viewModel.changeChecked(it) },
                onRemoveItem = { viewModel.remove(it) },
            )
        }
    }
}

/**
 * (1) Podemos acceder a nuestro ViewModel desde cualquier función composable llamando a la
 * función viewModel(), pero para ello necesitamos incorpor la dependencia (ver build.gradle).
 *
 */