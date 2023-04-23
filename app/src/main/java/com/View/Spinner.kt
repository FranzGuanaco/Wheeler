package com.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.wheeler.databinding.SpinnerItemCountryBinding
import com.example.wheeler.R // Ajoutez cette ligne pour importer R correctement

class Spinner(context: Context, objects: List<String>) :
    ArrayAdapter<String>(context, 0, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null) {
            SpinnerItemCountryBinding.inflate(LayoutInflater.from(context), parent, false).apply {
                this.root.tag = this
            }
        } else {
            convertView.tag as SpinnerItemCountryBinding
        }

        getItem(position)?.let { currentItem ->
            val countryInfo = currentItem.split(",")
            val countryCode = countryInfo[0].trim()
            val countryDialCode = countryInfo[1].trim()

            val flagId = context.resources.getIdentifier("flag_${countryCode.toLowerCase()}", "drawable", context.packageName)
            binding.flagImageView.setImageResource(flagId)
            binding.countryNameTextView.text = context.getString(R.string.country_name_with_code, countryCode, countryDialCode)
        }

        return binding.root
    }
}