package com.example.notebookapp.ui.finished

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notebookapp.databinding.FragmentFinishedBinding

class FinishedFragment : Fragment() {
    private lateinit var binding: FragmentFinishedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinishedBinding.inflate(inflater,container,false)
        return binding.root
    }

}