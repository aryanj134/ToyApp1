package com.example.myproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    private val _cartItems = MutableLiveData<MutableList<Toys>>(mutableListOf())
    val cartItems: LiveData<MutableList<Toys>> get() = _cartItems
    fun addToCart(toy: Toys) {
        _cartItems.value?.add(toy)
    }
    fun removeFromCart(toy: Toys) {
        _cartItems.value?.remove(toy)
    }

}