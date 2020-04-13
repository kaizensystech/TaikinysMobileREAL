package java.com.kaizenmax.taikinys_icl.backgroundservices;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.kaizenmax.taikinys_icl.model.DemoL3DataPushNetworkOperation;
import com.kaizenmax.taikinys_icl.model.DemoL3DataPushNetworkOperationInterface;
import com.kaizenmax.taikinys_icl.model.MandiCampaignDataPushNetworkOperation;
import com.kaizenmax.taikinys_icl.model.MandiCampaignDataPushNetworkOperationInterface;
import com.kaizenmax.taikinys_icl.presenter.IndividualFarmerContactActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.IndividualFarmerContactActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.view.IndividualFarmerContactMainActivity;

import static com.kaizenmax.taikinys_icl.util.App.CHANNEL_ID;


public class ExampleService extends Service {
Intent exampleServiceIntent;
    @Override
    public void onCreate() {

        super.onCreate();
    }
 
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        exampleServiceIntent = intent;

        String input = intent.getStringExtra("inputExtra");
 
        Intent notificationIntent = new Intent(this, IndividualFarmerContactMainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
 
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example Service")
                .setContentText(input)
                .setContentIntent(pendingIntent)
                .build();
 
        startForeground(12, notification);

       // Toast.makeText(this, "ssssssssssssssssssssQWET", Toast.LENGTH_SHORT).show();


        final Handler handler = new Handler();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                good();
                handler.postDelayed(this, 1800000);
            }
        }, 1800000);



        return START_NOT_STICKY;
    }

    public void good()
    {

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
        {
            IndividualFarmerContactActivityPresenterInterface individualFarmerContactActivityPresenterInterface =new IndividualFarmerContactActivityPresenter();


            try {
                individualFarmerContactActivityPresenterInterface.sendingDataToWebService();
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                MandiCampaignDataPushNetworkOperationInterface mandiCampaignDataPushNetworkOperationInterface = new MandiCampaignDataPushNetworkOperation();
                mandiCampaignDataPushNetworkOperationInterface.sendingMcDataToWebService();




            } catch (Exception e) {
                e.printStackTrace();
            }


            try{
                DemoL3DataPushNetworkOperationInterface demoL3DataPushNetworkOperationInterface = new DemoL3DataPushNetworkOperation();
                demoL3DataPushNetworkOperationInterface.sendingDemoL3DataToWebService();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            //  Toast.makeText(this, "UPLOAD SERVICE", Toast.LENGTH_SHORT).show();
        }
        else {
           // Toast.makeText(this, "Internet Off hai ", Toast.LENGTH_SHORT).show();


        }


        //stopService(exampleServiceIntent);

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }
 
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}