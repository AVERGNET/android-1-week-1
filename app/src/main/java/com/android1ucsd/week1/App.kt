package com.android1ucsd.week1

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.android1ucsd.week1.screen.list.AppListScreenDataSource

/**
 * Created by rjaylward on 4/5/19
 */

class App : Application() {

    companion object {

        @JvmStatic
        lateinit var dependencies: Dependencies
            private set

        @JvmStatic
        @VisibleForTesting
        fun init(dependencies: Dependencies) {
            this.dependencies = dependencies
        }

    }

    override fun onCreate() {
        super.onCreate()
        init(Dependencies(AppListScreenDataSource()))
    }

}