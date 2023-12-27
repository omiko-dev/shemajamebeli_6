package com.example.shemajamebeli_6.presentation

sealed class SecurityEvent {
    data class AddPasscode(val code: Int) : SecurityEvent()
    data object ClearPasscode : SecurityEvent()
    data object VerifyPasscode : SecurityEvent()
}
