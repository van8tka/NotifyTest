package ru.vbr.application

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class WorkManagerModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    private val context = reactContext

    override fun getName(): String {
        return "WorkManagerModule"
    }

    @ReactMethod
    fun startWork(message: String, promise: Promise) {
        try {
             VbrWorker.initWorker(message, context)
        } catch (e: Exception) {
            promise.reject("Error", e)
        }
    }
}

object VbrWorker{
    fun initWorker(message: String, context: Context?) {
        Log.d("API_CALL_VBR", "run startWork")
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val data = Data.Builder()
            .putString("message", message)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<SendRequestWorker>()
            .setConstraints(constraints)
            .setInputData(data).addTag("VBR_WorkManagerModule")
            .build()

        if (context != null) {
            WorkManager.getInstance(context).enqueue(workRequest)
        }
    }
}



