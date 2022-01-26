package com.example.hashgenapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.hashgenapp.R
import com.example.hashgenapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home),AdapterView.OnItemSelectedListener {
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
        spinner.onItemSelectedListener=this

        return binding.root
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
       val item= parent?.getItemAtPosition(pos).toString()
        Toast.makeText(context,item,Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}