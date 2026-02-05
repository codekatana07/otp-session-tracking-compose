package com.my.otpsessiontracking.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.my.otpsessiontracking.viewmodel.AuthViewModel

@Composable
fun OtpScreen(viewModel: AuthViewModel) {

    val state = viewModel.state
    var otpCode by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Enter OTP",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = otpCode,
            onValueChange = { otpCode = it },
            label = { Text("6 Digit OTP") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.verifyOtp(otpCode)
            }
        ) {
            Text("Verify OTP")
        }

        Spacer(modifier = Modifier.height(15.dp))

        state.errorMessage?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(20.dp))

        TextButton(
            onClick = {
                viewModel.sendOtp()
            }
        ) {
            Text("Resend OTP")
        }
    }
}
