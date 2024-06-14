package com.example.myproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myproject.databinding.CategoriesBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CategoriesFragment : Fragment() {
    private lateinit var binding: CategoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflating the layout
        binding = CategoriesBinding.inflate(inflater, container, false)


        binding.bottomNavigation?.apply {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_home -> {
                        navigateToFragment(HomePageFragment())
                        true
                    }

                    R.id.menu_account -> {
                        navigateToFragment(AccountFragment())
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
            menu.findItem(R.id.menu_categories)?.setChecked(false)
        }
        return view
    }

    private fun navigateToFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}
