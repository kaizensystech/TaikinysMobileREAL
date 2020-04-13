package java.com.kaizenmax.taikinys_icl.util;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


public class App extends Application {
    public static final String CHANNEL_ID = "exampleServiceChannel";

    public static final String CHANNEL_ID2 = "downloadDataServiceChannel";

 
    @Override
    public void onCreate() {
        super.onCreate();
 
        createNotificationChannel();

        createNotificationChannelForDownloadinData();
    }
 
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Example Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
 
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }


    private void createNotificationChannelForDownloadinData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID2,
                    "Downloading Data Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager2 = getSystemService(NotificationManager.class);
            manager2.createNotificationChannel(serviceChannel);
        }
    }


}