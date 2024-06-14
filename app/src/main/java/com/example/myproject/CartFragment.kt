package com.example.myproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.databinding.CartBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CartFragment : Fragment(){
    private var cartItems = mutableListOf<Toys>() //List for the selected items
    private lateinit var adapter: CartAdapter //Adapter for the RecyclerView
    private lateinit var sharedPreferences : SharedPreferences
    private val sharedViewModel : SharedViewModel by activityViewModels()
    private lateinit var binding: CartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflating the layout
        binding = CartBinding.inflate(inflater, container, false)

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("CartItems", Context.MODE_PRIVATE)

        // Retrieve cart items from SharedPreferences
        val json = sharedPreferences.getString("cartItems", null)
        cartItems = json?.let { ToyUtils.fromJsonToList(it) } ?: mutableListOf()

        // Initialize RecyclerView and adapter
        val recyclerView = binding.cartRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = CartAdapter(cartItems)
        recyclerView.adapter = adapter

//        val receivedItems = arguments?.getParcelableArrayList<Toys>("cartItems")
//        receivedItems?.let { cartItems.addAll(it) }
//        adapter.notifyDataSetChanged()

        //Total cost of the cartItems
        var totalAmt = calculateTotalAmt(cartItems)

        // Reflecting changes in the cart
        sharedViewModel.cartItems.observe(viewLifecycleOwner, Observer { cartItems ->
            adapter.updateCartItems(cartItems)
            totalAmt = calculateTotalAmt(cartItems)
            binding.btnPay.text = "Pay Rs $totalAmt"
        })



        // Set up swipe-to-delete functionality
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = cartItems[position]

                // Show confirmation dialog
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Confirm Delete")
                    .setMessage("Are you sure you want to delete this item?")
                    .setPositiveButton("Yes") { _, _ ->
                        // Remove item from the list and update adapter
                       sharedViewModel.removeFromCart(item)

//                        totalAmt = calculateTotalAmt(cartItems)
//                        payBtn?.text = "Pay Rs $totalAmt"

                        //saving the updated data in sharedPreferences
                        val json = ToyUtils.fromListToJson(cartItems)
                        sharedPreferences.edit().putString("cartItems", json).apply()
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        adapter.notifyItemChanged(position)
                        dialog.dismiss()
                    }
                    .setCancelable(false)
                    .show()
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)

        binding.btnPay.text = "Pay Rs $totalAmt"
        binding.btnPay.setOnClickListener {
            val intent = Intent(requireActivity(), PaymentActivity::class.java)
            intent.putExtra("totalAmount", totalAmt)
            startActivity(intent)
        }

        binding.bottomNavigation?.apply {
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

                    R.id.menu_account -> {
                        navigateToFragment(AccountFragment())
                        true
                    }
                    // Handle other menu items here
                    else -> false
                }
            }
            menu.findItem(R.id.menu_cart)?.isChecked = false
        }
        return binding.root
    }

    private fun navigateToFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onStop() {
        super.onStop()
        // Save cart items to SharedPreferences when the Cart fragment is stopped
        val json = ToyUtils.fromListToJson(cartItems)
        sharedPreferences.edit().putString("cartItems", json).apply()
    }

    private fun calculateTotalAmt(items: List<Toys>): Int {
        return items.sumBy { it.price }
    }

}