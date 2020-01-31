package com.android.myapplication.criminialintent_refactored


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.myapplication.criminialintent_refactored.databinding.FragmentCrimesListBinding

/**
 * A simple [Fragment] subclass.
 */
class ListCrimesFragment : Fragment() {
    private lateinit var binding:FragmentCrimesListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCrimesListBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}
