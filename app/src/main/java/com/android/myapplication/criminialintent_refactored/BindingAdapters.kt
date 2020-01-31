package com.android.myapplication.criminialintent_refactored

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("notesList")
fun RecyclerView.submitList(notes: List<CrimeModel>?) {
    val adapter = this.adapter as ListCrimesAdapter
    adapter.submitList(notes)
}


