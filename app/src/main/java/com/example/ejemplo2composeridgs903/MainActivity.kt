package com.example.ejemplo2composeridgs903

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "inicio"){
                composable("inicio"){ PantallaInicio(navController) }
                composable("detalle/{nombre}") { navBackStackEntry ->
                    val nombre = navBackStackEntry.arguments?.getString("nombre") ?: "Invitado"
                    com.example.ejemplo2composeridgs903.PantallaDetalle(navController, nombre)
                }
            }
        }
    }
}

@Composable
fun PantallaInicio(navController: NavHostController){
    var nombre by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Pantalla de inicio")
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = nombre,
            onValueChange = {nombre = it},
            label = { Text("Ingresa un nombre") },

            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val nombre = nombre.ifBlank { "Cardiel" }
            navController.navigate("detalle/$nombre")
        }) {
            Text("Ir a Detalle con nombre")
        }
    }
}

@Composable
fun PantallaDetalle(navController: NavHostController, nombre: String){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hola $nombre")
        Button(onClick = { navController.navigate("Inicio") }) {
            Text("Volver")
        }
    }
}
