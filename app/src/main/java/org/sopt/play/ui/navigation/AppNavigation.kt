package org.sopt.play.ui.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import org.sopt.play.ui.login.LoginScreen
import org.sopt.play.ui.register.RegisterScreen
import org.sopt.play.ui.main.MainScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val backStack = rememberNavBackStack(Route.Login)

    var registeredEmail by rememberSaveable {
        mutableStateOf<String?>(null)
    }
    var registeredPassword by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = {
            if (backStack.size > 1) {
                backStack.removeLastOrNull()
            }
        },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.Login> {
                LoginScreen(
                    onLoginClick = { email, password ->
                        if (
                            email == registeredEmail &&
                            password == registeredPassword
                        ) {
                            backStack.clear()
                            backStack.add(Route.Main)
                        } else {
                            Toast.makeText(
                                context,
                                "이메일 또는 비밀번호가 올바르지 않아요.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    onRegisterClick = {
                        // 회원가입 버튼 연속 클릭으로 회원가입 화면이 중복으로 추가되는 것을 방지
                        if (backStack.lastOrNull() == Route.Login) {
                            backStack.add(Route.Register)
                        }
                    }
                )
            }

            entry<Route.Register> {
                RegisterScreen(
                    onRegisterClick = { email, password ->
                        registeredEmail = email
                        registeredPassword = password

                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<Route.Main> {
                MainScreen()
            }
        }
    )
}