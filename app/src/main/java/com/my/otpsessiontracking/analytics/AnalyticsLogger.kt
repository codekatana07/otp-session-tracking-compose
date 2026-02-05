package com.example.otpsessiontracking.analytics

import timber.log.Timber

object AnalyticsLogger {

    fun logOtpCreated(email: String) {
        Timber.d("OTP Generated for $email")
    }

    fun logOtpSuccess(email: String) {
        Timber.d("OTP Success for $email")
    }

    fun logOtpFailed(email: String) {
        Timber.d("OTP Failed for $email")
    }

    fun logLogout(email: String) {
        Timber.d("User Logged Out: $email")
    }
}
