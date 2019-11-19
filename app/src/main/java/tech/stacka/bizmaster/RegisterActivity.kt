package tech.stacka.bizmaster

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btn_register.setOnClickListener { view ->
            val name = et_reg_name.text.toString()
            val email = et_reg_email.text.toString()
            val phone = et_reg_phone.text.toString()
            val place = et_reg_place.text.toString()

            when {
                TextUtils.isEmpty(name) -> Snackbar.make(
                    view,
                    "please enter your name",
                    Snackbar.LENGTH_SHORT
                ).show()
                TextUtils.isEmpty(email) -> Snackbar.make(
                    view,
                    "please enter your email",
                    Snackbar.LENGTH_SHORT
                ).show()
                TextUtils.isEmpty(phone) -> Snackbar.make(
                    view,
                    "please enter phone number",
                    Snackbar.LENGTH_SHORT
                ).show()
                TextUtils.isEmpty(place) -> Snackbar.make(
                    view,
                    "please enter your place",
                    Snackbar.LENGTH_SHORT
                ).show()

                else -> {
                    prefs = getSharedPreferences("com.stackatech.bizmaster", MODE_PRIVATE)

                    val prefEditor = prefs.edit()

                    prefEditor.putString("name", name)
                    prefEditor.putString("email", email)
                    prefEditor.putString("phone number", phone)
                    prefEditor.putString("place", place)
                    prefEditor.apply()
                }

            }

        }

    }
}
