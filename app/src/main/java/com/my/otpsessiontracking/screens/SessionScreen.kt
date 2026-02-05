package com.my.otpsessiontracking.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.my.otpsessiontracking.viewmodel.AuthViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SessionScreen(viewModel: AuthViewModel) {

    val state = viewModel.state
    val startTime = state.sessionStartTime ?: return

    var duration by remember { mutableStateOf(0L) }

    //Timer survives recomposition
    LaunchedEffect(Unit) {
        while (true) {
            duration =
                (System.currentTimeMillis() - startTime) / 1000
            delay(1000)
        }
    }

    val mins = duration / 60
    val secs = duration % 60

    val formatter = SimpleDateFormat("hh:mm:ss a", Locale.getDefault())
    val formattedStart = formatter.format(Date(startTime))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Session Active",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Session Start Time: $formattedStart")

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Duration: %02d:%02d".format(mins, secs),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.logout()
            }
        ) {
            Text("Logout")
        }
    }
}
