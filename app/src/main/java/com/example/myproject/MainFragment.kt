package com.example.myproject

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myproject.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = MainFragmentBinding.inflate(inflater, container, false)

        // Simulate a splash screen delay
        Handler(Looper.getMainLooper()).postDelayed({
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment, LoginFragment())
                .addToBackStack(null)
                .commit()
        }, 3000) // 3-second delay

        return binding.root
    }
}