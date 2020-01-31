package com.android.myapplication.criminialintent_refactored


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
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
        val action = ListCrimesFragmentDirections.actionListCrimesFragmentToCrimeFragment(crime.id)
        findNavController().navigate(action)
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
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.navigate.observe(this.viewLifecycleOwner){navigate->
            if(navigate){
                viewModel.resetNavigation()
                val action = ListCrimesFragmentDirections.actionListCrimesFragmentToCrimeFragment(null)
                findNavController().navigate(action)
            }
        }

    }


}
