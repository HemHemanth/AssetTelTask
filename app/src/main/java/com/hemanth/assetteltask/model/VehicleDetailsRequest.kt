package com.hemanth.assetteltask.model

data class VehicleDetailsRequest(
    val clientid: String,
    val enterprise_code: String,
    val mno: String,
    val passcode: String
)
