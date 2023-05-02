package io.github.bradpatras.justworkout

import android.app.Application
import timber.log.Timber

class WorkoutApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}