package com.my.otpsessiontracking.viewmodel

data class AuthState(
    val email: String = "",
    val otpSent: Boolean = false,
    val loggedIn: Boolean = false,
    val errorMessage: String? = null,
    val sessionStartTime: Long? = null
)