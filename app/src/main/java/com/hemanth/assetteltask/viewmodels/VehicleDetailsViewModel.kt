package com.hemanth.assetteltask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hemanth.assetteltask.model.VehicleDetails
import com.hemanth.assetteltask.model.VehicleDetailsRequest
import com.hemanth.assetteltask.repository.VehicleDetailsRepository

class VehicleDetailsViewModel: ViewModel() {

    fun loadVehicleDetails(vehicleDetailsRequest: VehicleDetailsRequest): LiveData<VehicleDetails> {
        val repository = VehicleDetailsRepository()
        return repository.getVehicleDetails(vehicleDetailsRequest)
    }
}