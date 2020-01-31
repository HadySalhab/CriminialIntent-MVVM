package com.android.myapplication.criminialintent_refactored


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.myapplication.criminialintent_refactored.databinding.FragmentCrimeBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 */
class CrimeFragment : Fragment() {
    companion object {
        private const val TAG = "CrimeFragment"
    }
    private lateinit var binding: FragmentCrimeBinding
    private val args by navArgs<CrimeFragmentArgs>()
    private val viewModel: CrimeDetailViewModel by viewModel {
        parametersOf(args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCrimeBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.aboutFragment).setVisible(false)
        inflater.inflate(R.menu.fragment_crime, menu)
        menu.findItem(R.id.delete).isVisible = args.id != null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.warningMessage.observe(viewLifecycleOwner){showMessage->
            if(showMessage){
                Snackbar.make(binding.root,"Please Add Title and Suspect for your Crime",Snackbar.LENGTH_SHORT).show()
                viewModel.resetWarningMessage()
            }
        }

        viewModel.navigateUp.observe(viewLifecycleOwner){ navigateUp->
            if(navigateUp){
                findNavController().navigateUp()
                viewModel.resetNavigateUp()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {
                saveCrime()
                true
            }
            R.id.delete-> {
                deleteCrime()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun saveCrime(){
        viewModel.saveCrime()
    }
    fun deleteCrime(){
        viewModel.deleteCrime()
    }


}
