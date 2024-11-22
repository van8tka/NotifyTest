package ru.vbr.application

import android.util.Log
import com.application.NfMessageHandler
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.wix.reactnativenotifications.core.notification.IPushNotification


class NfMessageService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        val bundle = message.toIntent().extras
        Log.d("API_CALL", "New message from FCM: $bundle")
        try {
            val notification = NfMessageHandler.get(this, bundle)
            notification.onReceived()
        } catch (e: IPushNotification.InvalidNotificationException) {
            Log.v("API_CALL", "FCM message handling aborted", e)
        }
    }

    companion object {
        private const val TAG = "API_CALL"
    }
}
