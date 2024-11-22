package ru.vbr.application

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class SendRequestWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    private val TAG = "SendRequestWorker"

    override fun doWork(): Result {
        Log.d("API_CALL_VBR", "start doWork in SendRequestWorker")
        val message = inputData.getString("message")

        try {
            val url = URL("http://192.168.31.149:3101/api/v1/microfinanceOrganization/payment")
            val urlConnection = url.openConnection() as HttpURLConnection
            Log.d("API_CALL_VBR", "message from callApiMethod: $message")
            try {
                val data = urlConnection.inputStream.bufferedReader().readText();
                Log.d("API_CALL_VBR", "data: $data")
            }
            finally {
                urlConnection.disconnect()
            }
        } catch (e: IOException) {
            Log.e("API_CALL_VBR", "Error calling API", e)
        }

        return Result.success()
    }
}
