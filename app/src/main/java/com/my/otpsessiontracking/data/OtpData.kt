package com.my.otpsessiontracking.data

data class OtpData (
    val otp: String,
    val createdTime: Long,
    var remainingAttempts: Int
)