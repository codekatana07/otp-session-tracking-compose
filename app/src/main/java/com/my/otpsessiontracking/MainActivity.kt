package com.my.otpsessiontracking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.my.otpsessiontracking.screens.LoginScreen
import com.my.otpsessiontracking.screens.OtpScreen
import com.my.otpsessiontracking.screens.SessionScreen
import com.my.otpsessiontracking.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val authViewModel: AuthViewModel = viewModel()
            val state = authViewModel.state

            when {
                state.loggedIn -> SessionScreen(authViewModel)

                state.otpSent -> OtpScreen(authViewModel)

                else -> LoginScreen(authViewModel)
            }
        }
    }
}
