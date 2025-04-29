package com.example.aplicacionesmovilesparcial1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aplicacionesmovilesparcial1.ui.theme.AplicacionesMovilesParcial1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RetiroView(navController: NavController) {
    var saldo by remember { mutableStateOf(10000.0) }
    var montoIngresado by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Retiro de Dinero") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Saldo actual: $${String.format("%.2f", saldo)}",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = montoIngresado,
                onValueChange = { montoIngresado = it },
                label = { Text("Monto a retirar") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (mensajeError.isNotEmpty()) {
                Text(
                    text = mensajeError,
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val monto = montoIngresado.toDoubleOrNull()
                    if (monto == null || monto <= 0) {
                        mensajeError = "Ingrese un monto válido."
                    } else if (monto > saldo) {
                        mensajeError = "Fondos insuficientes."
                    } else {
                        saldo -= monto
                        navController.navigate("comprobante/${monto}")
                    }
                }
            ) {
                Text("Retirar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RetiroPreview() {
    AplicacionesMovilesParcial1Theme {
        RetiroView(
            rememberNavController()
        )
    }
}