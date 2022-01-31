package com.example.hashgenapp.fragment

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hashgenapp.R
import com.example.hashgenapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), AdapterView.OnItemSelectedListener {
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
            lifecycleScope.launch {
                applyTransition()
                navToResultFrag()
            }
        }
        return binding.root
    }

    private fun navToResultFrag() {
        findNavController().navigate(R.id.action_homeFragment_to_resultFragment)
    }

    private suspend fun applyTransition() {
        binding.textView.animate().alpha(0f).duration = 400L
        binding.appCompatSpinner.animate().alpha(0f).translationXBy(1200f).duration = 400L
        binding.generateBtn.animate().alpha(0f).translationXBy(-1200f).duration = 400L
        delay(500L)
        binding.view.animate().alpha(1f).duration = 500L
        delay(500L)
        binding.imageView.animate().alpha(1f).rotation(360f).duration = 900L
        delay(1000L)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        val item = parent?.getItemAtPosition(pos).toString()
        Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.item_menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}