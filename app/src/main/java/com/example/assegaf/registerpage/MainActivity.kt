package com.example.assegaf.registerpage

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isRegistrationClickable = false
    var isAtLeast8 = false
    var hasUppercase = false
    var hasNumber = false
    var hasSymbol = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputChange()
        setOnClickRegistration()
    }

    private fun inputChange() {
        email_input_field.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                registrationDataCheck()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        password_input_field.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                registrationDataCheck()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

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
            p_item_3_icon_parent.setBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)))
        } else {
            hasNumber = false
            p_item_3_icon_parent.setBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)))
        }
        if (pwd.matches("^(?=.*[_.()]).*$".toRegex())){
            hasSymbol = true
            p_item_4_icon_parent.setBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)))
        }else{
            hasSymbol = false
            p_item_4_icon_parent.setBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)))
        }

        checkAllData(email)
    }

    @SuppressLint("ResourceType")
    private fun checkAllData(email: String) {
        if (isAtLeast8 && hasUppercase && hasNumber && hasSymbol && email.length >0){
            isRegistrationClickable = true
            registration_button_parent.setBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)))
        }else{
            isRegistrationClickable = false
            registration_button_parent.setBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)))
        }
    }

    private fun checkEmpty(email: String, pwd: String) {
        if (email.length > 0 && email_error_text.visibility == View.VISIBLE) {
            email_error_text.visibility == View.GONE
        }
        if (pwd.length > 0 && password_error_text.visibility == View.VISIBLE) {
            password_error_text.visibility == View.GONE
        }
    }

    private fun setOnClickRegistration() {
        registration_button.setOnClickListener {
            val email = email_input_field.text.toString()
            val pwd = password_input_field.text.toString()

            if (email.length > 0 && pwd.length > 0) {
                if (isRegistrationClickable) {
                    Toast.makeText(this, getString(R.string.registration_title), Toast.LENGTH_LONG).show()
                }
            } else {
                if (email.length == 0) {
                    email_error_text.visibility == View.VISIBLE
                }
                if (pwd.length == 0) {
                    password_error_text.visibility == View.VISIBLE
                }
            }
        }
    }
}
