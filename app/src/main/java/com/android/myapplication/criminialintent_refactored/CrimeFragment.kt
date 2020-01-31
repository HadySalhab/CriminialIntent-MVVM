package com.android.myapplication.criminialintent_refactored


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.android.myapplication.criminialintent_refactored.databinding.FragmentCrimeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 */
class CrimeFragment : Fragment() {
    private lateinit var binding:FragmentCrimeBinding
    private val args by navArgs<CrimeFragmentArgs>()
    private val viewModel:CrimeDetailViewModel by viewModel {
        parametersOf(args.id)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCrimeBinding.inflate(layoutInflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.aboutFragment).setVisible(false)
        inflater.inflate(R.menu.fragment_crime,menu)
    }


}
