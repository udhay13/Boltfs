package org.monora.uprotocol.client.android.util

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

/**
 * Created by: veli
 * Date: 4/28/17 2:22 AM
 */
class DynamicNotification(
    val context: Context,
    val manager: NotificationManagerCompat,
    notificationChannel: String,
    val notificationId: Int
) : NotificationCompat.Builder(context, notificationChannel) {
    fun cancel(): DynamicNotification {
        manager.cancel(notificationId)
        return this
    }

    fun show(): DynamicNotification {
        manager.notify(notificationId, build())
        return this
    }

    fun updateProgress(max: Int, percent: Int, indeterminate: Boolean): DynamicNotification {
        setProgress(max, percent, indeterminate)
        show()
        return this
    }
}