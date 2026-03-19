package com.example.atividadeavaliativaproffabiano

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TecladoComponente(
    onNumberClick: (String) -> Unit,
    onDeleteClick: () -> Unit
) {
    val botoes = listOf(
        "1", "2", "3", "-",
        "4", "5", "6", ",",
        "7", "8", "9", "DEL",
        "", "0", ".", "ENT"
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF212121))
            .padding(2.dp)
    ) {
        items(botoes) { label ->
            Button(
                onClick = {
                    when(label) {
                        "DEL" -> onDeleteClick()
                        "ENT", "-", ",", "" -> { /* Funções extras não usadas no cálculo */ }
                        else -> onNumberClick(label)
                    }
                },
                modifier = Modifier
                    .height(60.dp)
                    .padding(1.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (label == "DEL" || label == "ENT" || label == "-" || label == ",")
                        Color(0xFF373737) else Color(0xFF424242),
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(text = label, fontSize = 22.sp)
            }
        }
    }
}