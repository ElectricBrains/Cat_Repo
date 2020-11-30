package com.example.myapplication.platform

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.myapplication.R
import com.example.myapplication.infrastruture.utils.IDownloadQueue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.newFixedThreadPoolContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class CatDownloaderService : IntentService("CatDownloaderService"), CoroutineScope {
    @Inject
    lateinit var downloadQueue: IDownloadQueue

    override val coroutineContext: CoroutineContext =
        newFixedThreadPoolContext(1, "CatDownloaderService")


    companion object {
        const val CHANNEL_ID = "CatDownloaderService"
        const val NOTIF_ID = 1555

    }

    private val mChanelId by lazy {
        createNotificationChannel(CHANNEL_ID, CHANNEL_ID)
    }

    override fun onHandleIntent(intent: Intent?) {
        startForeground(NOTIF_ID, buildNotificationForCat())

        while (downloadQueue.hasNext()) {
            val cat = downloadQueue.getNext()
            updateNotification("download for ${cat.name}", "left: ${downloadQueue.count()}")
//            Log.i("CatDownloaderService", "download cat $cat left cats ${downloadQueue.count()}")
            Thread.sleep(1000)
        }
        stopSelf()
    }

    private fun buildNotificationForCat(first: String = "", second: String = ""): Notification {
        val notificationLayout = RemoteViews(packageName, R.layout.cat_dowload_layout_colapsed)
        val notificationLayoutExpanded = RemoteViews(packageName, R.layout.cat_dowload_layout_expanded)


        notificationLayout.setTextViewText(R.id.cat_complete, first);
        notificationLayout.setTextViewText(R.id.cat_download_left_cats, second);

        notificationLayoutExpanded.setTextViewText(R.id.cat_complete, first);
        notificationLayoutExpanded.setTextViewText(R.id.cat_download_left_cats, second);


        return NotificationCompat.Builder(this, mChanelId)
            .setSmallIcon(R.drawable.ic_download)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationLayout)
            .setCustomBigContentView(notificationLayoutExpanded)
            .build()
    }

    private fun updateNotification(first: String = "", second: String = "") {
            val notification: Notification = buildNotificationForCat(first, second)
            val mNotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mNotificationManager.notify(NOTIF_ID, notification)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String{
        val chan = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_NONE)
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }

}