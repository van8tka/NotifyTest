/*package ru.vbr.application

import android.content.Intent
import android.util.Log
import com.application.NotificationReceiver
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // If the application is in the foreground handle or display both data and notification FCM messages here.
        // Here is where you can display your own notifications built from a received FCM message.
        super.onMessageReceived(remoteMessage)
        if (remoteMessage.data.size > 0) {
            Log.d(TAG, "Message data: " + remoteMessage.data)
            val intent = Intent(this, NotificationReceiver::class.java)
            intent.setAction("${applicationInfo.packageName}.ACTION_RECEIVE_NOTIFICATION")
            sendBroadcast(intent)
        }
        if (remoteMessage.notification != null) {
            Log.d(
                TAG, "Message Notification Body: " + remoteMessage.notification!!
                    .body
            )
        }
    }

    companion object {
        private const val TAG = "API_CALL"
    }
}
*/
