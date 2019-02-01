package com.bignerdranch.android.osm.core.domain

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

class MainThreadExecutor : Executor {
    override fun execute(command: Runnable?) {
        Handler(Looper.getMainLooper()).post(command)
    }
}