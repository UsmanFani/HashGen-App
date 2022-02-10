package com.example.hashgenapp.fragment

import android.app.AppComponentFactory
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hashgenapp.R
import com.example.hashgenapp.databinding.FragmentHomeBinding
import com.example.hashgenapp.viewmodel.HomeFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class HomeFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var item: String
    private val viewModel: HomeFragmentViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val spinner = binding.appCompatSpinner
        spinner.adapter = ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.hashArray,
            android.R.layout.simple_spinner_dropdown_item
        )
        spinner.onItemSelectedListener = this
        setHasOptionsMenu(true)
        binding.generateBtn.setOnClickListener {
            if (binding.editText.text.isEmpty()) {
                Snackbar.make(requireView(), "Nothing to Convert", Snackbar.LENGTH_SHORT).also {
                    it.setAction("Ok") {}
                        .setActionTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.iris
                            )
                        )
                        .show()
                }
            } else {
                lifecycleScope.launch {
                    val hashData=getHashData()
                    applyTransition()
                    navToResultFrag(hashData)
                }
            }
        }
        return binding.root
    }


    private fun navToResultFrag(hash: String) {
        val directions = HomeFragmentDirections.actionHomeFragmentToResultFragment(hash)
        findNavController().navigate(directions)
    }

    private fun getHashData(): String {
        val plainText = binding.textView.text.toString()
        return viewModel.getHash(plainText, item)
    }

    private suspend fun applyTransition() {
        binding.textView.animate().alpha(0f).duration = 400L
        binding.appCompatSpinner.animate().alpha(0f).translationXBy(1200f).duration = 400L
        binding.generateBtn.animate().alpha(0f).translationYBy(600f).duration = 400L
        binding.editText.animate().alpha(0f).translationXBy(-1200f).duration=400L
        delay(100L)
        binding.imageView.animate().alpha(1f).rotation(360f).duration = 900L
        delay(1200L)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        item = parent?.getItemAtPosition(pos).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.item_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home_menu_clear) binding.editText.text = null
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}