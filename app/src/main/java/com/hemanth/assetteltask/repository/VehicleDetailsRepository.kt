package com.hemanth.assetteltask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hemanth.assetteltask.data.api.AssetTelBasePath
import com.hemanth.assetteltask.model.VehicleDetails
import com.hemanth.assetteltask.model.VehicleDetailsRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VehicleDetailsRepository {

    fun getVehicleDetails(vehicleDetailsRequest: VehicleDetailsRequest): LiveData<VehicleDetails> {
        val vehicleDetailsLiveData = MutableLiveData<VehicleDetails>()
        val call = AssetTelBasePath().getAssetTelServices().getVehicleDetails(vehicleDetailsRequest)
        call.enqueue(object: Callback<VehicleDetails> {
            override fun onResponse(
                call: Call<VehicleDetails>,
                response: Response<VehicleDetails>
            ) {
               if (response.isSuccessful)
                   vehicleDetailsLiveData.value = response.body()
            }

            override fun onFailure(call: Call<VehicleDetails>, t: Throwable) {
                t.printStackTrace()
            }

        })
        return vehicleDetailsLiveData
    }
}