package com.hemanth.assetteltask.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.hemanth.assetteltask.R
import com.hemanth.assetteltask.model.FuelType
import com.hemanth.assetteltask.model.ManufactureYear
import com.hemanth.assetteltask.model.VehicleCapacity
import com.hemanth.assetteltask.model.VehicleMake

class SpinnerAdapter(private val itemList: List<*>, private val context: Context, private val type: String): BaseAdapter() {

    private class ItemHolder(row: View?) {
        val txtSpinnerItem: TextView = row?.findViewById(R.id.txtSpinnerItem) as TextView
    }

    override fun getCount(): Int = itemList.size

    override fun getItem(position: Int): Any = itemList[position]!!

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.spinner_item, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        if (type == "FUEL")
            vh.txtSpinnerItem.text = (itemList[position] as FuelType).text

        if (type == "CAPACITY")
            vh.txtSpinnerItem.text = (itemList[position] as VehicleCapacity).text

        if (type == "MAKE")
            vh.txtSpinnerItem.text = (itemList[position] as VehicleMake).text

        if (type == "YEAR")
            vh.txtSpinnerItem.text = (itemList[position] as ManufactureYear).text

        return view
    }
}