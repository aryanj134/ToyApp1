package com.example.myproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Category1Fragment : Fragment() {

    private fun navigateToFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}