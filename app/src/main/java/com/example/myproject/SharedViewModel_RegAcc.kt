package com.example.myproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel_RegAcc: ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _dob = MutableLiveData<String>()
    val dob: LiveData<String> get() = _password

    private val _mobileNum = MutableLiveData<String>()
    val mobileNum: LiveData<String> get() = _mobileNum

    fun setName(name: String) {
        _name.value = name
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setMobileNum(mobileNum: String) {
        _mobileNum.value = mobileNum
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setDob(dob: String) {
        _dob.value = dob
    }
}