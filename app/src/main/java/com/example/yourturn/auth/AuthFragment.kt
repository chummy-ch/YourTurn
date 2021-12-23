package com.example.yourturn.auth

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.yourturn.MainActivity
import com.example.yourturn.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private lateinit var loginField: EditText
    private lateinit var pswField: EditText
    private lateinit var button: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginField = view.findViewById(R.id.login_edit_text)
        pswField = view.findViewById(R.id.psw_edit_text)
        button = view.findViewById(R.id.login_button)

        button.setOnClickListener {
            val login = loginField.text.toString()
            val psw = pswField.text.toString()

            if (login.length < 4) {
                Snackbar.make(view, getString(R.string.login_short_error), 2000).show()
            } else if (psw.length < 4) {
                Snackbar.make(view, getString(R.string.psw_short_error), 2000).show()
            } else {
                login(login, psw)
            }
        }
    }

    private fun login(l: String, p: String) {
        lifecycleScope.launch {
            MainActivity.AUTH_MANAGER.login(l, p)
        }
    }
}