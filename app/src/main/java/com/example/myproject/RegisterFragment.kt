package com.example.myproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myproject.databinding.RegisterFragmentBinding

class RegisterFragment : Fragment() {
    private lateinit var binding: RegisterFragmentBinding
    private val sharedViewModel : SharedViewModel_RegAcc by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflating the layout
        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        //Setting up the save details button design
        binding.btnReg.setOnClickListener {

            // Get entered data from EditText
            val newName = binding.name.text.toString()
            val newPassword = binding.password.text.toString()
            val newMobileNum = binding.mobileNum.text.toString()
            val newEmail = binding.userEmail.text.toString()
            val newDob = binding.userDob.text.toString()


            if(newName.isNotEmpty() && newPassword.isNotEmpty() && newMobileNum.isNotEmpty() && newEmail.isNotEmpty() && newDob.isNotEmpty()) {
                // Set data in the shared ViewModel
                sharedViewModel.setName(newName)
                sharedViewModel.setPassword(newPassword)
                sharedViewModel.setMobileNum(newMobileNum)
                sharedViewModel.setEmail(newEmail)
                sharedViewModel.setDob(newDob)

                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment, HomePageFragment())
                    .addToBackStack(null)
                    .commit()

                Toast.makeText(context, "Welcome! $newName", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(context, "All fields are mandatory", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}