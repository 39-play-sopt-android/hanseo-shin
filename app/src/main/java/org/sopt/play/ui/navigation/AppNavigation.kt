package org.sopt.play.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import org.sopt.play.ui.login.LoginRoute
import org.sopt.play.ui.register.RegisterRoute
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
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.Login> {
                LoginRoute(
                    registeredEmail = registeredEmail,
                    registeredPassword = registeredPassword,
                    onNavigateToHome = {
                        backStack.clear()
                        backStack.add(Route.Main)
                    },
                    onNavigateToRegister = {
                        if (backStack.lastOrNull() == Route.Login) {
                            backStack.add(Route.Register)
                        }
                    }
                )
            }

            entry<Route.Register> {
                RegisterRoute(
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
