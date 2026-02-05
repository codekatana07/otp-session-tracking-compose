package com.my.otpsessiontracking.viewmodel
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.otpsessiontracking.data.OtpManager
import com.example.otpsessiontracking.analytics.AnalyticsLogger
import com.my.otpsessiontracking.data.OtpResult

class AuthViewModel : ViewModel() {

    private val otpManager = OtpManager()

    //State:UI State.
    //One way data flow// UI can read state but cannot modify directly// UI → events → ViewModel → state → UI
    var state by mutableStateOf(AuthState())
        private set

    //New email
    //copy() because authState is immutable
    fun updateEmail(newEmail: String) {
        state = state.copy(email = newEmail)
    }

    //Otp send
    fun sendOtp() {
        otpManager.generateOtp(state.email)

        state = state.copy(

            //ui updated
            otpSent = true,
            errorMessage = null
        )
    }

    //Otp verification
    fun verifyOtp(inputOtp: String) {

        when(val result = otpManager.validateOtp(state.email, inputOtp)) {

            is OtpResult.Success -> {
                AnalyticsLogger.logOtpSuccess(state.email)

                state = state.copy(
                    loggedIn = true,
                    sessionStartTime = System.currentTimeMillis(),
                    errorMessage = null
                )
            }

            is OtpResult.WrongOtp -> {
                state = state.copy(
                    errorMessage =
                        "Wrong OTP. Attempts left: ${result.attemptsLeft}"
                )
            }

            OtpResult.Expired -> {
                state = state.copy(
                    errorMessage = "OTP Expired. Please resend."
                )
            }

            OtpResult.AttemptsExceeded -> {
                state = state.copy(
                    errorMessage = "Too many attempts. Resend OTP."
                )
            }

            OtpResult.NoOtpGenerated -> {
                state = state.copy(
                    errorMessage = "Generate OTP first."
                )
            }
        }
    }

    //Logout:
    fun logout() {
        AnalyticsLogger.logLogout(state.email)

        //resetting everything back to initial state.
        state = AuthState()
    }
}
