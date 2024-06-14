package com.example.myproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.databinding.HomepageFragmentBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePageFragment : Fragment() {
    private lateinit var adapter: ToysAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: HomepageFragmentBinding
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflating the layout
            binding = HomepageFragmentBinding.inflate(inflater, container, false)
            // Set layout manager
            binding.recyclerview.layoutManager = LinearLayoutManager(activity)

            // Initialize adapter and set it to RecyclerView
            adapter = ToysAdapter(getToyData()){ toys -> addToCart(toys)}
            binding.recyclerview.adapter = adapter

            binding.bottomNavigation.apply {
                setOnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.menu_categories -> {
                            navigateToFragment(CategoriesFragment())
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
                menu.findItem(R.id.menu_home)?.isChecked = false
            }
            return binding.root
        }

    private fun addToCart(toys: Toys) {
        Toast.makeText(context, "Added ${toys.name} to cart", Toast.LENGTH_SHORT).show()
        sharedViewModel.addToCart(toys)
        navigateToFragment(CartFragment())
    }

    private fun navigateToFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment, fragment)
            ?.addToBackStack(null)
            ?.commit() 
    }

    private fun getToyData(): List<Toys> {
        // Prepare toy data
        val toyData = mutableListOf<Toys>()
        val toyNames = arrayOf(
            "Dinosaur",
            "Monkey",
            "Robot",
            "Panda",
            "Vegetables",
            "Statue in pair"
        )
        val descriptions = arrayOf(
            "Toy 1 description",
            "Toy 2 description",
            "Toy 3 description",
            "Toy 4 description",
            "Toy 5 description",
            "Toy 6 description"
        )
        val images = arrayOf(
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6
        )
        val prices = arrayOf(
            280,
            200,
            320,
            430,
            360,
            290
        )

        // Create Toys objects and add to list
        for (i in toyNames.indices) {
            toyData.add(Toys(toyNames[i], descriptions[i], images[i], prices[i]))
        }

        return toyData
    }

}




//            //Setting up the banner design
//            view?.findViewById<ImageView>(R.id.banner)?.setOnClickListener {
//                parentFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.fragment, BannerFragment())
//                    .addToBackStack(null)
//                    .commit()
//            }
//
//            view?.findViewById<MaterialTextView>(R.id.category1)?.setOnClickListener {
//                parentFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.fragment, Category1Fragment())
//                    .addToBackStack(null)
//                    .commit()
//            }
//
//            view?.findViewById<MaterialTextView>(R.id.category2)?.setOnClickListener {
//                parentFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.fragment, Category2Fragment())
//                    .addToBackStack(null)
//                    .commit()
//            }
//
//            view?.findViewById<MaterialTextView>(R.id.category3)?.setOnClickListener {
//                parentFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.fragment, Category3Fragment())
//                    .addToBackStack(null)
//                    .commit()
//            }
