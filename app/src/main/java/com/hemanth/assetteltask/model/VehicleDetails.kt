package com.hemanth.assetteltask.model

data class VehicleDetails(
    val fuel_type: List<FuelType>,
    val manufacture_year: List<ManufactureYear>,
    val message: String,
    val status: Int,
    val vehicle_capacity: List<VehicleCapacity>,
    val vehicle_make: List<VehicleMake>,
    val vehicle_type: List<VehicleType>
)