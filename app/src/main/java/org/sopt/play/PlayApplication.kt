package org.sopt.play

import android.app.Application
import org.sopt.play.di.AppContainer

class PlayApplication : Application() {

    val container: AppContainer by lazy {
        AppContainer()
    }
}