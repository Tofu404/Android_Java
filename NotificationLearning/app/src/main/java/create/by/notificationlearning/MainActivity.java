package create.by.notificationlearning;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String CHANNEL_ID = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.send_notification).setOnClickListener(this);

    }

    private void sendNotification() {
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);

        builder.setContentTitle("我是标题");
        builder.setContentText("我是内容");
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setSmallIcon(R.drawable.ic_download);
        builder.setAutoCancel(true);
        builder.setProgress(100,80,false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"test", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            managerCompat.createNotificationChannel(channel);
            builder.setChannelId(CHANNEL_ID);
        }

        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
        builder.setContentIntent(pendingIntent);

        managerCompat.notify(12,builder.build());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notification:
                Toast.makeText(this, "发送通知", Toast.LENGTH_SHORT).show();
                sendNotification();
                break;
        }
    }
}