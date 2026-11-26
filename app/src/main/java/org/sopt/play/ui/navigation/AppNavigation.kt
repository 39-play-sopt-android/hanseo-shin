package org.sopt.play.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import org.sopt.play.ui.login.LoginRoute
import org.sopt.play.ui.main.MainScreen
import org.sopt.play.ui.post.PostRoute
import org.sopt.play.ui.register.RegisterRoute

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(Route.Login)

    var registeredEmail by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    var registeredPassword by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    var registeredName by rememberSaveable {
        mutableStateOf("")
    }

    var authorizationCode by rememberSaveable {
        mutableStateOf("")
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
                    onNavigateToHome = { code ->
                        authorizationCode = code

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
                    onRegisterClick = { email, password, name ->
                        registeredEmail = email
                        registeredPassword = password
                        registeredName = name

                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<Route.Main> {
                MainScreen(
                    onNavigateToPost = {
                        if (backStack.lastOrNull() == Route.Main) {
                            backStack.add(Route.Post)
                        }
                    }
                )
            }

            entry<Route.Post> {
                PostRoute(
                    authorizationCode = authorizationCode,
                    author = registeredName
                )
            }
        }
    )
}