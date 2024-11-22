package com.application

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import ru.vbr.application.VbrWorker

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("APP_CALL", "onReceive BroadcastReceiver start")
        VbrWorker.initWorker("test BROdCAST message",context)
    }
}