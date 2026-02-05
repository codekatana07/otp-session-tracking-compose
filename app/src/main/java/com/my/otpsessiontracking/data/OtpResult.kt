package com.my.otpsessiontracking.data

sealed class OtpResult {

    object Success : OtpResult()

    object Expired : OtpResult()

    object AttemptsExceeded : OtpResult()

    object NoOtpGenerated : OtpResult()

    data class WrongOtp(val attemptsLeft: Int) : OtpResult()
}