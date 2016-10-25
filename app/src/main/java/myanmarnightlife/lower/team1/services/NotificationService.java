package myanmarnightlife.lower.team1.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.activities.MainActivity;

/**
 * Created by user on 10/25/16.
 */

public class NotificationService extends FirebaseMessagingService {
  private static int NOTIFICATION_ID = 0;

  @Override public void onMessageReceived(RemoteMessage remoteMessage) {

    NotificationCompat.Builder mBuilder =
        new NotificationCompat.Builder(this).setSmallIcon(R.drawable.beer)
            .setContentTitle(remoteMessage.getNotification().getTitle())
            .setContentText(remoteMessage.getNotification().getBody());
    // Creates an explicit intent for an Activity in your app
    Intent resultIntent = new Intent(this, MainActivity.class);

    // The stack builder object will contain an artificial back stack for the
    // started Activity.
    // This ensures that navigating backward from the Activity leads out of
    // your application to the Home screen.
    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
    // Adds the back stack for the Intent (but not the Intent itself)
    stackBuilder.addParentStack(MainActivity.class);
    // Adds the Intent that starts the Activity to the top of the stack
    stackBuilder.addNextIntent(resultIntent);
    PendingIntent resultPendingIntent =
        stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    mBuilder.setContentIntent(resultPendingIntent);
    NotificationManager mNotificationManager =
        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    // mId allows you to update the notification later on.
    mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
  }
}
