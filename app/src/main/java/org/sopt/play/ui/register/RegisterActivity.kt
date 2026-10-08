package org.sopt.play.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.sopt.play.ui.theme.PlaySoptTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PlaySoptTheme {
                RegisterScreen(
                    onRegisterClick = { email, password ->
                        val result = Intent().apply {
                            putExtra(EXTRA_EMAIL, email)
                            putExtra(EXTRA_PASSWORD, password)
                        }

                        setResult(RESULT_OK, result)
                        finish()
                    }
                )
            }
        }
    }

    companion object {
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_PASSWORD = "extra_password"
    }
}
