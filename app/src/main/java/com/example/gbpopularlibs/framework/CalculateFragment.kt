package com.example.gbpopularlibs.framework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gbpopularlibs.databinding.FragmentCalculateBinding
import com.example.gbpopularlibs.mvp.CalculateContract
import com.example.gbpopularlibs.mvp.CalculatePresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CalculateFragment : MvpAppCompatFragment(), CalculateContract.View {
    private var _binding : FragmentCalculateBinding? = null
    private val binding get() = _binding!!
    private val presenter by moxyPresenter {
        CalculatePresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calculateButton.setOnClickListener {
            onButtonCalculateClick()
        }
    }
    override fun onButtonCalculateClick() {
        var number = binding.numberEditText.text.toString()
        presenter.squareNumber(number = number.toLong())
    }

    override fun showCalculateResult(text: String) {
        binding.resultTextView.text = text
    }

}