package ru.vbr.application

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.wix.reactnativenotifications.core.AppLifecycleFacadeHolder
import com.wix.reactnativenotifications.core.notification.IPushNotification
import com.wix.reactnativenotifications.core.notification.PushNotification
import ru.vbr.application.WorkManagerModule.Companion.initWorker


class NfMessageService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        val bundle = message.toIntent().extras
        Log.d("API_CALL", "New message from FCM: $bundle")
        try {
             val lifecycleHolder = AppLifecycleFacadeHolder.get();

            val notification = PushNotification.get(applicationContext, bundle);
            notification.onReceived();

            if (!lifecycleHolder.isAppVisible){
                // нужно тут вызывать Worker а метод который снаружи (в JS/TS, Notification.event...) не сработает
                // так как надо прокидывать ReactContext который доступен
                // только при запуске приложения, его можно хранить например и обрабатывать вызовы в broadcastreceiver
                // но полсе перезагрузки он будет равен null до запуска приложения что не позволит дергать JS код reacta
                initWorker("Test", applicationContext);
            }

//           val notification = NfMessageHandler.get(this, bundle)
//            notification.onReceived()
        } catch (e: IPushNotification.InvalidNotificationException) {
            Log.v("API_CALL", "FCM message handling aborted", e)
        }
    }

    companion object {
        private const val TAG = "API_CALL"
    }
}
