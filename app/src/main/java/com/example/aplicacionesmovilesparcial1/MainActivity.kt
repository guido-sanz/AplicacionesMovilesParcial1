package com.example.aplicacionesmovilesparcial1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aplicacionesmovilesparcial1.ui.theme.AplicacionesMovilesParcial1Theme
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AplicacionesMovilesParcial1Theme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "retiro") {
                        composable("retiro") {
                            RetiroView(navController)
                        }
                        composable("comprobante/{amount}") { backStackEntry ->
                            val amount = backStackEntry.arguments?.getString("amount") ?: "0"
                            ComprobanteView(amount)
                        }
                }
            }
        }
    }
}

