package com.example.myproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.databinding.CartitemListBinding

// Create adapter for the cart items RecyclerView
class CartAdapter(private var cartItems: List<Toys>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private lateinit var binding: CartitemListBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cartitem_list, parent, false)
        return CartViewHolder(view)
    }

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val toyNameTextView: TextView = binding.listTitle
        val toyDescTextView: TextView = binding.listDesc
        val toyImageView: ImageView = binding.listAvatar
        val toyPriceTextView: TextView = binding.listPrice
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]
        val currentItem = cartItems[position]
        holder.toyNameTextView.text = currentItem.name
        holder.toyDescTextView.text = currentItem.desc
        holder.toyImageView.setImageResource(currentItem.image)
        holder.toyPriceTextView.text = "Rs " + currentItem.price.toString()
    }

    override fun getItemCount(): Int = cartItems.size

    fun updateCartItems(newCartItems: MutableList<Toys>) {
        cartItems = newCartItems
        notifyDataSetChanged()
    }

}