package com.example.shemajamebeli_6.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SecurityViewModel : ViewModel() {
    private var _securityStateState = MutableStateFlow<List<Int>>(listOf())
    val securityStateState = _securityStateState.asStateFlow()

    private var _verifyState = MutableStateFlow(false)
    val verifyState = _verifyState.asStateFlow()

    private val correctPasscode = listOf(0, 9, 3, 4)

    fun onEvent(event: SecurityEvent) {
        when (event) {
            is SecurityEvent.AddPasscode -> addPasscode(event.code)
            is SecurityEvent.ClearPasscode -> clearPasscode()
            is SecurityEvent.VerifyPasscode -> verifyPasscode()
        }
    }

    private fun addPasscode(code: Int) {
        viewModelScope.launch {
            val list = _securityStateState.value.toMutableList()
            if(list.size <= 3){
                list.add(code)
            } else {
                verifyPasscode()
                list.clear()
            }
            _securityStateState.value = list

        }
    }

    private fun clearPasscode() {
        viewModelScope.launch {
            val list = _securityStateState.value.toMutableList()
            if(list.isNotEmpty() && list.size != 4){
                list.removeLast()
                _securityStateState.value = list
            }

        }
    }

    private fun verifyPasscode() {
        viewModelScope.launch {
            _verifyState.value = _securityStateState.value == correctPasscode
        }
    }
}



