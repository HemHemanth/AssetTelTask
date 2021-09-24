package com.hemanth.assetteltask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hemanth.assetteltask.R
import com.hemanth.assetteltask.ui.adapters.SpinnerAdapter
import com.hemanth.assetteltask.databinding.ActivityMainBinding
import com.hemanth.assetteltask.model.VehicleDetailsRequest
import com.hemanth.assetteltask.viewmodels.VehicleDetailsViewModel
import androidx.core.app.ActivityCompat.startActivityForResult




class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val vehicleDetailsViewModel: VehicleDetailsViewModel =
            ViewModelProvider(this).get(com.hemanth.assetteltask.viewmodels.VehicleDetailsViewModel::class.java)
        val vehicleDetailsRequest = VehicleDetailsRequest("11", "1007", "9889897789", "2300")
        vehicleDetailsViewModel.loadVehicleDetails(vehicleDetailsRequest).observe(this, Observer {
            val fuelAdapter = SpinnerAdapter(it.fuel_type, this, "FUEL")
            binding.spinnerFuelType.adapter = fuelAdapter

            val capacityAdapter = SpinnerAdapter(it.vehicle_capacity, this, "CAPACITY")
            binding.spinnerVehicleCapacity.adapter = capacityAdapter

            val makeAdapter = SpinnerAdapter(it.vehicle_make, this, "MAKE")
            binding.spinnerVehicleMake.adapter = makeAdapter

            val manufactureYearAdapter = SpinnerAdapter(it.manufacture_year, this, "YEAR")
            binding.spinnerManufactureYear.adapter = manufactureYearAdapter

            val list = it.vehicle_type

            if (list.size > 3) {
                for ( i in 0 until 3) {
                    createVehicleTypeItem(R.drawable.vehicle, it.vehicle_type[i].text)
                }
                createVehicleTypeItem(R.drawable.plus, "+")
            } else {
                //implement logic
            }

            binding.edtImei.setOnTouchListener(object: View.OnTouchListener {
                override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                    val DRAWABLE_RIGHT = 2;

                    if(event?.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (binding.edtImei.getRight() - binding.edtImei.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            Toast.makeText(this@MainActivity, "Image Clicked", Toast.LENGTH_LONG).show()
                            val i = Intent(this@MainActivity, ScannerActivity::class.java)
                            startActivityForResult(i, 1)
                            return true;
                        }
                    }
                    return false;
                }

            })

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            binding.edtImei.setText(data?.getStringExtra("imei"))
        }
    }

    private fun createVehicleTypeItem(resource: Int, type: String) {
        val linearLayout = LinearLayout(this)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            1.0f)
        linearLayout.layoutParams = layoutParams
        linearLayout.orientation = LinearLayout.VERTICAL
        val imageView = ImageView(this)
        val params = LinearLayout.LayoutParams(
            binding.layoutVehiceType.width / 8,
            binding.layoutVehiceType.width / 6
        )
        params.gravity = Gravity.CENTER_HORIZONTAL
        imageView.layoutParams = params
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL
        imageView.setImageResource(resource)
        val textView = TextView(this)
        textView.gravity = Gravity.CENTER_HORIZONTAL
        textView.text = type
        linearLayout.addView(imageView)
        linearLayout.addView(textView)
        binding.layoutVehiceType.addView(linearLayout)
    }
}