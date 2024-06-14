package com.example.myproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.databinding.ItemListBinding

class ToysAdapter(private val toysList: List<Toys>, private val onAddToCartClickListener: (Toys) -> Unit)
    : RecyclerView.Adapter<ToysAdapter.ViewHolder>() {
    private lateinit var binding: ItemListBinding
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val toyNameTextView: TextView = binding.listTitle
        val toyDescTextView: TextView = binding.listDesc
        val toyImageView: ImageView = binding.listAvatar
        val toyPriceTextView: TextView = binding.listPrice
        val addToCartButton: Button = binding.btnATC

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = toysList[position]
        holder.toyNameTextView.text = currentItem.name
        holder.toyDescTextView.text = currentItem.desc
        holder.toyImageView.setImageResource(currentItem.image)
        holder.toyPriceTextView.text = "Rs " + currentItem.price.toString()

        // Set click listener for Add To Cart button
        holder.addToCartButton.setOnClickListener {
            onAddToCartClickListener(currentItem)
        }
    }
    override fun getItemCount() = toysList.size
}