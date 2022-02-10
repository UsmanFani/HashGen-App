package com.example.hashgenapp.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.hashgenapp.databinding.FragmentResultBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ResultFragment : Fragment() {
    private val hashArgus: ResultFragmentArgs by navArgs()
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        binding.textView2.text = hashArgus.hashArgs
        binding.copyBtn.setOnClickListener {

            lifecycleScope.launch {
                copytoClip(hashArgus.hashArgs)
                copiedAnimate()
            }

        }
        return binding.root
    }

    private fun copytoClip(hash: String) {
        val clipboardManager =
            requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipdata = ClipData.newPlainText("Encrypted Text", hash)
        clipboardManager.setPrimaryClip(clipdata)
    }

    private suspend fun copiedAnimate() { //have to work on this --NOt Working
        binding.include.copiedView.animate().alpha(1f).duration=200L
//        binding.include.background.animate().translationY(80f).duration = 200L
//        binding.include.copiedTv.animate().translationY(80f).duration = 200L
        delay(2000L)
        binding.include.copiedView.animate().alpha(0f).duration=200L
//        binding.include.background.animate().translationY(-80f).duration = 500L
//        binding.include.copiedTv.animate().translationY(-80f).duration = 500L
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}