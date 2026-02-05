package com.my.otpsessiontracking.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.my.otpsessiontracking.viewmodel.AuthViewModel

@Composable
fun LoginScreen(viewModel: AuthViewModel) {

    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Email Login",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = state.email,
            onValueChange = { viewModel.updateEmail(it) },
            label = { Text("Enter Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.sendOtp()
            },
            enabled = state.email.isNotBlank()
        ) {
            Text("Send OTP")
        }
    }
}
