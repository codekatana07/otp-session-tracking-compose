package com.example.otpsessiontracking.data

import com.example.otpsessiontracking.analytics.AnalyticsLogger
import com.my.otpsessiontracking.data.OtpData
import com.my.otpsessiontracking.data.OtpResult
import timber.log.Timber

class OtpManager {

    //Otp stored in respective email
    private val otpStorage = mutableMapOf<String, OtpData>()

    //Generating otp
    fun generateOtp(email: String): String {

        val otp = (100000..999999).random().toString()

        Timber.d("OTP Generated: $otp for $email")
        otpStorage[email] = OtpData(
            otp = otp,
            createdTime = System.currentTimeMillis(),
            remainingAttempts = 3
        )
        AnalyticsLogger.logOtpCreated(email)

        return otp
    }

    //func:Validation
    fun validateOtp(email: String, enteredOtp: String): OtpResult {

        val otpDetails = otpStorage[email]

        if(otpDetails == null) {
            return OtpResult.NoOtpGenerated
        }

        //Checking expiry
        //timestamp comparison is efficient and recomposition-safe compared to timers
        val secondsSinceCreation = (System.currentTimeMillis() - otpDetails.createdTime) / 1000

        if (secondsSinceCreation > 60) {
            return OtpResult.Expired
        }

        //Attempts
        if (otpDetails.remainingAttempts <= 0) {
            return OtpResult.AttemptsExceeded
        }

        //Otp match
        if (enteredOtp == otpDetails.otp) {
            return OtpResult.Success
        }
        else {
            otpDetails.remainingAttempts--
            AnalyticsLogger.logOtpFailed(email)

            return OtpResult.WrongOtp(otpDetails.remainingAttempts)
        }
    }
}
