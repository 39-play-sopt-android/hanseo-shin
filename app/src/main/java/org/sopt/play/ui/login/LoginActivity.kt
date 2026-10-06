package org.sopt.play.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.play.MainActivity
import org.sopt.play.ui.register.RegisterActivity
import org.sopt.play.ui.theme.PlaySoptTheme

class LoginActivity : ComponentActivity() {
    private var registeredEmail: String? = null
    private var registeredPassword: String? = null

    private val registerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            registeredEmail =
                result.data?.getStringExtra(RegisterActivity.EXTRA_EMAIL)
            registeredPassword =
                result.data?.getStringExtra(RegisterActivity.EXTRA_PASSWORD)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registeredEmail =
            savedInstanceState?.getString(RegisterActivity.EXTRA_EMAIL)
        registeredPassword =
            savedInstanceState?.getString(RegisterActivity.EXTRA_PASSWORD)

        setContent {
            PlaySoptTheme {
                LoginScreen(
                    onLoginClick = { email, password ->
                        if (
                            email == registeredEmail &&
                            password == registeredPassword
                        ) {
                            val intent = Intent(this, MainActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK
                            } // 심화 과제 3

                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this,
                                "이메일 또는 비밀번호가 올바르지 않아요.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    onRegisterClick = {
                        registerLauncher.launch(
                            Intent(this, RegisterActivity::class.java)
                        )
                    }
                )
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(
            RegisterActivity.EXTRA_EMAIL,
            registeredEmail
        )
        outState.putString(
            RegisterActivity.EXTRA_PASSWORD,
            registeredPassword
        )
        super.onSaveInstanceState(outState)
    }
}