package com.example.myproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myproject.databinding.AccountBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class AccountFragment : Fragment() {
    private val sharedViewModel: SharedViewModel_RegAcc by activityViewModels()
    private lateinit var binding: AccountBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = AccountBinding.inflate(inflater, container, false)

        // Observe the LiveData from the SharedViewModel
        sharedViewModel.apply{
            name.observe(viewLifecycleOwner) { newName ->
                binding.newName.text = "Name: $newName"
            }
            password.observe(viewLifecycleOwner) { newPassword ->
                binding.newPassword.text = "Password: $newPassword"
            }
            mobileNum.observe(viewLifecycleOwner) { newMobileNum ->
                binding.newMobileNum.text = "Mobile No.: $newMobileNum"
            }
            email.observe(viewLifecycleOwner) { newEmail ->
                binding.newEmail.text = "Email: $newEmail"
            }
            dob.observe(viewLifecycleOwner) { newDob ->
                binding.newDob.text = "DOB: $newDob"
            }
        }


        val bottomNavigationView = view?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView?.apply {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_home -> {
                        navigateToFragment(HomePageFragment())
                        true
                    }

                    R.id.menu_categories -> {
                        navigateToFragment(CategoriesFragment())
                        true
                    }

                    R.id.menu_cart -> {
                        navigateToFragment(CartFragment())
                        true
                    }
                    // Handle other menu items here
                    else -> false
                }
            }
            menu.findItem(R.id.menu_account)?.setChecked(false)
        }
        return binding.root
    }

    private fun navigateToFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}
