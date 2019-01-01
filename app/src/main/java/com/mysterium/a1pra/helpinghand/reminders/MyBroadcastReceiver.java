package com.mysterium.a1pra.helpinghand.reminders;
/*
 * Author: Kartik Bhardwaj
 */

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.TextView;

import com.mysterium.a1pra.helpinghand.R;

import java.util.Calendar;

public class MyBroadcastReceiver extends BroadcastReceiver {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView td1;
    TextView td2;
    TextView td3;
    TextView td4;
    TextView dt1;
    TextView dt2;
    TextView dt3;
    TextView dt4;


    String reminder;
    Calendar cal=Calendar.getInstance();

    SharedPreferences sharedPreferences;
    int hour1;
    int minute1;
    int year;
    int month=1;
    int date;
    @Override
    public void onReceive(Context context, Intent intent) {



        Vibrator vibrator=(Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);
        String reminder =intent.getStringExtra("reminder");
        CameraManager cameraManager = (CameraManager)context.getSystemService(Context.CAMERA_SERVICE);


        String channelId="default_channel_id";
        String channelDescription="default channel";
        NotificationManager notif=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            int importance =NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel;//=notif.getNotificationChannel(channelId);
            notificationChannel=new NotificationChannel(channelId,channelDescription,importance);
            notif.createNotificationChannel(notificationChannel);


            Notification notify=new Notification.Builder
                    (context).setContentTitle("Reminder").setContentText(reminder).
                    setContentTitle("**ALERT**").setSmallIcon(R.drawable.ic_launcher_foreground).setChannelId(channelId).build();
            int notificationID=(int)System.currentTimeMillis();
            notify.flags |= Notification.FLAG_AUTO_CANCEL;



            notif.notify(notificationID, notify);



        }
        else{


            Notification notify=new Notification.Builder
                    (context).setContentTitle("Reminder").setContentText(reminder).
                    setContentTitle("**ALERT**").setSmallIcon(R.drawable.ic_launcher_foreground).build();
            int notificationID=(int)System.currentTimeMillis();
            notify.flags |= Notification.FLAG_AUTO_CANCEL;



            notif.notify(notificationID, notify);




        }
        // please ignore the comments


        /*int importance =NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel;//=notif.getNotificationChannel(channelId);
        notificationChannel=new NotificationChannel(channelId,channelDescription,importance);
        notif.createNotificationChannel(notificationChannel);


        Notification notify=new Notification.Builder
                (context).setContentTitle("Reminder").setContentText(reminder).
                setContentTitle("**ALERT**").setSmallIcon(R.drawable.ic_launcher_foreground).setChannelId(channelId).build();
        int notificationID=(int)System.currentTimeMillis(); notify.flags |= Notification.FLAG_AUTO_CANCEL;



        notif.notify(notificationID, notify);



        */






        try {

            String cameraId = cameraManager.getCameraIdList()[0];
            String myString = "0101010101";

            long blinkDelay = 50;
            for (int i = 0; i < myString.length(); i++) {
                if (myString.charAt(i) == '0') {
                    cameraManager.setTorchMode(cameraId, true);
                }
                else {

                    cameraManager.setTorchMode(cameraId, false);

                }
                try {
                    Thread.sleep(blinkDelay);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }catch(CameraAccessException e){}
    }







}

