package com.example.atividadeavaliativaproffabiano

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

@Composable
fun PaginaGorjeta(viewModel: TipViewModel) {
    val state by viewModel.uiState.collectAsState()
    val format = NumberFormat.getCurrencyInstance(Locale.US)
    val displayAmount = (state.amountInput.toDoubleOrNull() ?: 0.0) / 100.0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0E0E0))
            .padding(16.dp)
    ) {
        // Linha do Valor (Amount)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Valor", modifier = Modifier.width(80.dp), fontSize = 18.sp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFCFD8DC))
                    .padding(8.dp)
            ) {
                Text(format.format(displayAmount), fontSize = 20.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Linha do Custom %
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Custom %", modifier = Modifier.width(80.dp), fontSize = 18.sp)
            Slider(
                value = state.customPercent,
                onValueChange = { viewModel.updatePercent(it) },
                valueRange = 0f..30f,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Cabeçalho das porcentagens
        Row(modifier = Modifier.fillMaxWidth().padding(start = 80.dp)) {
            Text("15%", modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            Text("${state.customPercent.toInt()}%", modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        }

        // Linha da Gorjeta (Tip)
        ResultRow("Gorjeta", format.format(state.tip15), format.format(state.tipCustom))

        // Linha do Total
        ResultRow("Total", format.format(state.total15), format.format(state.totalCustom))

        Spacer(modifier = Modifier.weight(1f))

        // Teclado
        TecladoComponente(
            onNumberClick = { viewModel.updateAmount(it) },
            onDeleteClick = { viewModel.deleteLast() }
        )
    }
}

@Composable
fun ResultRow(label: String, value15: String, valueCustom: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, modifier = Modifier.width(80.dp), textAlign = androidx.compose.ui.text.style.TextAlign.End, fontSize = 18.sp)
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = Modifier.weight(1f).background(Color(0xFFCFD8DC)).padding(8.dp), contentAlignment = Alignment.Center) {
            Text(value15)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = Modifier.weight(1f).background(Color(0xFFCFD8DC)).padding(8.dp), contentAlignment = Alignment.Center) {
            Text(valueCustom)
        }
    }
}