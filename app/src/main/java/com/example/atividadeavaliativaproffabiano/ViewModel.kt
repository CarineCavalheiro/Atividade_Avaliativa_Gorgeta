package com.example.atividadeavaliativaproffabiano

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TipViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TipUiState())
    val uiState: StateFlow<TipUiState> = _uiState.asStateFlow()

    fun updateAmount(digit: String) {
        val current = _uiState.value.amountInput
        if (current.length < 9) {
            _uiState.update { it.copy(amountInput = current + digit) }
            calculateValues()
        }
    }

    fun deleteLast() {
        val current = _uiState.value.amountInput
        if (current.isNotEmpty()) {
            _uiState.update { it.copy(amountInput = current.dropLast(1)) }
            calculateValues()
        }
    }

    fun updatePercent(newPercent: Float) {
        _uiState.update { it.copy(customPercent = newPercent) }
        calculateValues()
    }

    private fun calculateValues() {
        val amount = _uiState.value.amountInput.toDoubleOrNull()?.div(100.0) ?: 0.0
        val customP = _uiState.value.customPercent / 100.0

        _uiState.update { it.copy(
            tip15 = amount * 0.15,
            total15 = amount * 1.15,
            tipCustom = amount * customP,
            totalCustom = amount * (1 + customP)
        ) }
    }
}