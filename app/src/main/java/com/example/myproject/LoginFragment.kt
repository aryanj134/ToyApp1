package com.example.myproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {
    private val mainUsername = "user@gmail.com"
    private val mainPassword = "user123"
    private lateinit var binding: LoginFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflating the layout
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        val homePageFragment = HomePageFragment()

        //Setting up the login button design
        binding.btnLogin.setOnClickListener {
            if (isValidLogin()) {
                // Navigate to home page fragment if login is successful
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment, homePageFragment)
                    .addToBackStack(null)
                    .commit()
            } else {
                // Show invalid message
                Toast.makeText(requireContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }

        //Setting up the register button design
        binding.register.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, RegisterFragment())
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }

    // Method to perform login validation
    private fun isValidLogin(): Boolean {
        // Retrieve username and password from EditText fields
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        // Validate username and password
        return username == mainUsername && password == mainPassword
    }
}