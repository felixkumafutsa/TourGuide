package com.example.tourguide;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class ReceiveSMS extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "New SMS Received!", Toast.LENGTH_SHORT).show();

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs;
            String from_number;

            if (bundle != null){
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        from_number = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();

                        // if (from_number.toLowerCase().equals("airtelmoney") && (msgBody.contains("991512441"))) {

                        if (from_number.equals("+265980143067")){
                            String[] msg_arr = msgBody.split(" ");
                            String amount = msg_arr[13].replace(".00.", "").replace("MK", "");
                            String transID = msg_arr[2].replace(",", "").replace(".", "");

                            int notificationId = 0;

                            Toast.makeText(context, "amount = "+amount, Toast.LENGTH_LONG).show();

                            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "NOTIFICATION_CHANNEL_ID")

                                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                                    //.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
                                    .setContentTitle("Payment successful")
                                    .setContentText(":"+amount+"ID:"+transID)
                                    .setAutoCancel(true)
                                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                                    .setDefaults(NotificationCompat.DEFAULT_ALL);

                            //set a tone when notification appears
                            Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                            builder.setSound(path);
                            Intent resultIntent = new Intent(context, PaymentDone.class);//notification_b.class
                            resultIntent.putExtra("amount_paid", amount).addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            resultIntent.putExtra("transaction_id", transID);
                            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, resultIntent, 0);
                            builder.setContentIntent(pendingIntent);
                            //call notification manager so it can build and deliver the notification to the OS
                            NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

                            //Android 8 introduced a new requirement of setting the channelId property by using a NotificationChannel.
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                String channelId = "NOTIFICATION_CHANNEL_ID";
                                NotificationChannel channel = new NotificationChannel(channelId,
                                        "notif_channel",
                                        NotificationManager.IMPORTANCE_DEFAULT);
                                notificationManager.createNotificationChannel(channel);
                                builder.setChannelId(channelId);
                            }

                            notificationManager.notify(notificationId, builder.build());
                            ///end of notifications
                        }
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
