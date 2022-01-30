package com.example.hashgenapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hashgenapp.R
import com.example.hashgenapp.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private var _binding:FragmentResultBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentResultBinding.inflate(inflater,container,false)
        return binding.root
    }

}