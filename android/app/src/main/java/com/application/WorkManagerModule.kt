package ru.vbr.application

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class WorkManagerModule{
    companion object {
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
}



