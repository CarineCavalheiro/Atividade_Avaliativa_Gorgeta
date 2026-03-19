package com.example.atividadeavaliativaproffabiano

data class TipUiState(
    val amountInput: String = "",
    val customPercent: Float = 18f,
    val tip15: Double = 0.0,
    val total15: Double = 0.0,
    val tipCustom: Double = 0.0,
    val totalCustom: Double = 0.0
)