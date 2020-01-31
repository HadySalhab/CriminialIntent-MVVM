package com.android.myapplication.criminialintent_refactored


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.android.myapplication.criminialintent_refactored.databinding.FragmentCrimesListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ListCrimesFragment : Fragment() {
    private lateinit var binding:FragmentCrimesListBinding
    private val viewModel:ListCrimesViewModel by viewModel()
    private lateinit var crimeAdapter :ListCrimesAdapter

    private val onCrimeClickListener: (CrimeModel) -> Unit = { crime ->
       //should navigate
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        crimeAdapter = ListCrimesAdapter(onCrimeClickListener)
        binding = FragmentCrimesListBinding.inflate(layoutInflater,container,false)
        binding.recyclerView.apply {
            adapter =crimeAdapter
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


}
