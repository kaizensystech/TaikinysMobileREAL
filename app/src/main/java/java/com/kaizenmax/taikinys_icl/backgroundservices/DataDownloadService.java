package java.com.kaizenmax.taikinys_icl.backgroundservices;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.kaizenmax.taikinys_icl.model.DemoL3DataPushNetworkOperation;
import com.kaizenmax.taikinys_icl.model.DemoL3DataPushNetworkOperationInterface;
import com.kaizenmax.taikinys_icl.model.MasterDateSynchronizationNetworkOperation;
import com.kaizenmax.taikinys_icl.model.MasterDateSynchronizationNetworkOperationInterface;
import com.kaizenmax.taikinys_icl.model.OtpVerificationActivityNetworkOperationInterface;
import com.kaizenmax.taikinys_icl.model.OtpVerificationNetworkOperation;
import com.kaizenmax.taikinys_icl.util.SharedPreferenceUtil;
import com.kaizenmax.taikinys_icl.view.IndividualFarmerContactMainActivity;

import static com.kaizenmax.taikinys_icl.util.App.CHANNEL_ID2;


public class DataDownloadService extends Service {

    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");

        Intent notificationIntent = new Intent(this, IndividualFarmerContactMainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID2)
                .setContentTitle("Data Download Service")
                .setContentText(input)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(13, notification);

        // Toast.makeText(this, "ssssssssssssssssssssQWET", Toast.LENGTH_SHORT).show();


        final Handler handler = new Handler();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                good();
                handler.postDelayed(this, 3600000);
            }
        }, 3600000);



        return START_NOT_STICKY;
    }

    public void good()
    {
        String mobile=null;
        String otp=null;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
        {
           IndividualFarmerContactMainActivity.getInstance().getDataSetFromCloudAndLocalSaveBackGroundService();




           SharedPreferences sharedpreferences= IndividualFarmerContactMainActivity.getInstance().getSharedPreferences(SharedPreferenceUtil.PREFERENCES, Context.MODE_PRIVATE);
                if(sharedpreferences!=null)
                {
                    mobile=   sharedpreferences.getString(SharedPreferenceUtil.MOBILEKEY,null);
                    otp =sharedpreferences.getString(SharedPreferenceUtil.OTPKEY,null);
                }

            MasterDateSynchronizationNetworkOperationInterface masterDateSynchronizationNetworkOperationInterface = new MasterDateSynchronizationNetworkOperation();
            try {

                masterDateSynchronizationNetworkOperationInterface.faSpecificDataSynchronization(mobile);
            } catch (Exception e) {
                e.printStackTrace();
            }


            //Toast.makeText(this, "DOWNLOAD SERVICE", Toast.LENGTH_SHORT).show();

            try {

            DemoL3DataPushNetworkOperationInterface  demoL3DataPushNetworkOperationInterface = new DemoL3DataPushNetworkOperation();

            demoL3DataPushNetworkOperationInterface.gettingDemoL3DataFromWebService(mobile);

            } catch (Exception e) {
                e.printStackTrace();
            }







        }
        else {
            //Toast.makeText(this, "Internet Off hai ", Toast.LENGTH_SHORT).show();


        }
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
