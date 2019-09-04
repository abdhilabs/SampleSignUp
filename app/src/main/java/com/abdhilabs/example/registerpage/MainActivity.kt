package com.abdhilabs.example.registerpage

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isRegistrationClickable = false
    private var isAtLeast8 = false
    private var hasUppercase = false
    private var hasNumber = false
    private var hasSymbol = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputChange()
        setOnClickRegistration()
    }

    private fun inputChange() {
        email_input_field.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = registrationDataCheck()

        })

        password_input_field.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = registrationDataCheck()

        })
    }

    @SuppressLint("ResourceType")
    private fun registrationDataCheck() {
        val email = email_input_field.text.toString()
        val pwd = password_input_field.text.toString()

        checkEmpty(email, pwd)

        if (pwd.length >= 8) {
            isAtLeast8 = true
            p_item_1_icon_parent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)))
        } else {
            isAtLeast8 = false
            p_item_1_icon_parent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)))
        }
        if (pwd.matches("(.*[A-Z].*)".toRegex())) {
            hasUppercase = true
            p_item_2_icon_parent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)))
        } else {
            hasUppercase = false
            p_item_2_icon_parent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)))
        }
        if (pwd.matches("(.*[0-9].*)".toRegex())) {
            hasNumber = true
            p_item_3_icon_parent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)))
        } else {
            hasNumber = false
            p_item_3_icon_parent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)))
        }
        if (pwd.matches("(.*[!@#$%^&*()_+=,.<>/?;:'|].*)".toRegex())){
            hasSymbol = true
            p_item_4_icon_parent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)))
        }else{
            hasSymbol = false
            p_item_4_icon_parent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)))
        }

        checkAllData(email)
    }

    @SuppressLint("ResourceType")
    private fun checkAllData(email: String) {
        if (isAtLeast8 && hasUppercase && hasNumber && hasSymbol && email.isNotEmpty()){
            isRegistrationClickable = true
            registration_button_parent.setBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)))
        }else{
            isRegistrationClickable = false
            registration_button_parent.setBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)))
        }
    }

    private fun checkEmpty(email: String, pwd: String) {
        if (email.isNotEmpty() && email_error_text.visibility == View.VISIBLE) {
            email_error_text.visibility = View.GONE
        }
        if (pwd.isNotEmpty() && password_error_text.visibility == View.VISIBLE) {
            password_error_text.visibility = View.GONE
        }
    }

    private fun setOnClickRegistration() {
        registration_button.setOnClickListener {
            val email = email_input_field.text.toString()
            val pwd = password_input_field.text.toString()

            if (email.isNotEmpty() && pwd.isNotEmpty()) {
                if (isRegistrationClickable) {
                    Toast.makeText(this, getString(R.string.registration_title), Toast.LENGTH_LONG).show()
                }
            } else {
                if (email.isEmpty()) {
                    email_error_text.visibility = View.VISIBLE
                }
                if (pwd.isEmpty()) {
                    password_error_text.visibility = View.VISIBLE
                }
            }
        }
    }
}
