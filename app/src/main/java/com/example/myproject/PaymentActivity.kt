package com.example.myproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import org.json.JSONObject

class PaymentActivity: AppCompatActivity(), PaymentResultWithDataListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Retrieve total amount from intent
        val totalAmt = intent.getIntExtra("totalAmount", 0)
        //Starting the payment process
        startPayment(totalAmt)
    }

    private fun startPayment(totalAmt: Int) {
            val co = Checkout()
            co.setKeyID("rzp_test_VDOivRVmFCaVfJ")

            val options = JSONObject().apply {
                put("name", "Toy Company")
                put("description", "Purchase Description")
                put("currency", "INR")
                put("amount", (totalAmt*100).toString()) //Amount is in paise
            }

            val prefill = JSONObject().apply {
                put("email", "aryanjain@bitlasoft.com")
                put("contact", "7984972866")
            }

            options.put("prefill", prefill)
            try {
                co.open(this, options)
            } catch (e: Exception) {
                Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        // Handle payment success
        Toast.makeText(this, "Payment successful: $p0", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        // Handle payment failure
        Toast.makeText(this, "Payment failed: $p1", Toast.LENGTH_SHORT).show()
        finish()
    }
}