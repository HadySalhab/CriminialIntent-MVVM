package com.android.myapplication.criminialintent_refactored

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("crimesList")
fun RecyclerView.submitList(crimes: List<CrimeModel>?) {
    val adapter = this.adapter as ListCrimesAdapter
    adapter.submitList(crimes)
}


