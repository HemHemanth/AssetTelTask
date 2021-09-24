package com.hemanth.assetteltask.data.api

import com.hemanth.assetteltask.model.VehicleDetails
import com.hemanth.assetteltask.model.VehicleDetailsRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface EndPoints {
    @POST("/jhsmobileapi/mobile/configure/v1/task")
    fun getVehicleDetails(@Body request: VehicleDetailsRequest): Call<VehicleDetails>
}