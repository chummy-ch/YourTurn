package com.example.yourturn

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.yourturn.auth.AuthFragment
import com.example.yourturn.auth.AuthManager
import com.example.yourturn.main.MainFragment
import com.example.yourturn.network.RetrofitClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    companion object {
        val AUTH_MANAGER = AuthManager(RetrofitClient())

    }

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_navigation)

        findViewById<Button>(R.id.log_out_button).setOnClickListener {
            AUTH_MANAGER.logout()
        }

        lifecycleScope.launch {
            AUTH_MANAGER.user.collect { user ->
                /*if (user == null) {
                    bottomNav.isVisible = false
                    supportFragmentManager.commit {
                        replace(R.id.fragment_container, AuthFragment())
                        setReorderingAllowed(true)
                    }
                } else {
                    supportFragmentManager.popBackStack()
                    bindNavigationButton()
                }*/
                supportFragmentManager.popBackStack()
                bindNavigationButton()
            }
        }
    }

    private fun bindNavigationButton() {
        bottomNav.isVisible = true
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.main -> setCurrentFragment(MainFragment())
                R.id.profile -> setCurrentFragment(AuthFragment())
                R.id.rest -> setCurrentFragment(AuthFragment())
                else -> throw IllegalAccessException()
            }
            true
        }
        bottomNav.selectedItemId = R.id.main
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment).commit()
        }
}
