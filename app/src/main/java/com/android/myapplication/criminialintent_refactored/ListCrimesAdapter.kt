package com.android.myapplication.criminialintent_refactored

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.myapplication.criminialintent_refactored.databinding.ListItemCrimeBinding

class ListCrimesAdapter(
    val onCrimeClickListener: (CrimeModel) -> Unit
) : ListAdapter<CrimeModel, ListCrimesAdapter.CrimesViewHolder>(CrimesDiffUtil()) {
    companion object {
        private const val TAG = "ListCrimesAdapter"
    }
    class CrimesViewHolder private constructor(
        val binding: ListItemCrimeBinding,
        val onCrimeClickListener: (CrimeModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun getInstance(
                parent: ViewGroup,
                onCrimeClickListener: (CrimeModel) -> Unit
            ): CrimesViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
                return CrimesViewHolder(binding, onCrimeClickListener)
            }
        }

        fun bind(crime: CrimeModel) {
            binding.crime = crime
            binding.viewHolder = this
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimesViewHolder =
        CrimesViewHolder.getInstance(parent,onCrimeClickListener)

    override fun onBindViewHolder(holder: CrimesViewHolder, position: Int) {
        val crimeModel = getItem(position)
        crimeModel?.let {
            holder.bind(it)
            Log.d(TAG, "onBindViewHolder: ${it}")
        }
    }

}

class CrimesDiffUtil : DiffUtil.ItemCallback<CrimeModel>() {
    override fun areItemsTheSame(oldItem: CrimeModel, newItem: CrimeModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CrimeModel, newItem: CrimeModel): Boolean {
        //data class compares each of the property to see if they are equal
        return oldItem == newItem


    }

}