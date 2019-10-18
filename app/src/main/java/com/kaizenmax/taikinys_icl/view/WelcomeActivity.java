package com.kaizenmax.taikinys_icl.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kaizenmax.taikinys_icl.R;
import com.kaizenmax.taikinys_icl.presenter.WelcomeActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.WelcomeActivityPresenterInterface;


public class WelcomeActivity extends AppCompatActivity {



        EditText mobileEditText,premobileEditText;
        String enteredMobileNumber;
        Button btn;
        ProgressBar pgsBar;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tvp1;
        TextView tvp2;
        TextView tvp3;


     WelcomeActivityPresenterInterface welcomeActivityPresenterInterface;

     static WelcomeActivity instance;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_welcome);
//            getSupportActionBar().hide();

            mobileEditText=findViewById(R.id.mobileNumber);
            premobileEditText=findViewById(R.id.mobileCode);
            btn=findViewById(R.id.signUpButton);
            pgsBar = (ProgressBar)findViewById(R.id.pBar);
            pgsBar.setVisibility(View.GONE);

            tv1=findViewById(R.id.tv1);
            tv2=findViewById(R.id.tv2);
            tv3=findViewById(R.id.tv3);

            tvp1=findViewById(R.id.tvp1);
            tvp2=findViewById(R.id.tvp2);
            tvp3=findViewById(R.id.tvp3);

           premobileEditText.setText("+91");

           instance=this;


            String mobileNumber= null;
            try {
                welcomeActivityPresenterInterface=new WelcomeActivityPresenter();
                mobileNumber = welcomeActivityPresenterInterface.getMobileNumberFromSharedPreference();

            } catch (Exception e) {
                e.printStackTrace();
            }

            if(mobileNumber!=null) {
                Intent intent = new Intent(WelcomeActivity.this, IndividualFarmerContactMainActivity.class);
                startActivity(intent);
                finish();
            }



          btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    enteredMobileNumber=mobileEditText.getText().toString();


                    if(enteredMobileNumber.equals("") || enteredMobileNumber==null )
                    {
                        mobileEditText.setError("Please enter mobile number");
                    }
                    else {

                        if(enteredMobileNumber!=null && enteredMobileNumber.trim().length()<10)
                        {
                            mobileEditText.setError("Please enter 10 digits.");
                        }
                        else {

                          //  Toast.makeText(WelcomeActivity.this, "MOb Number " + enteredMobileNumber, Toast.LENGTH_SHORT).show();





                            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                                premobileEditText.setVisibility(View.GONE);
                                mobileEditText.setVisibility(View.GONE);
                                tv1.setVisibility(View.GONE);
                                tv2.setVisibility(View.GONE);
                                tv3.setVisibility(View.GONE);
                                tvp1.setVisibility(View.GONE);
                                tvp2.setVisibility(View.GONE);
                                tvp3.setVisibility(View.GONE);
                                btn.setVisibility(View.GONE);
                                pgsBar.setVisibility(View.VISIBLE);

                                try {
                                    welcomeActivityPresenterInterface.sendingMobileToWebservice(enteredMobileNumber);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            else
                                Toast.makeText(WelcomeActivity.this, "Sorry, no internet connectivity. Please reconnect and try again.", Toast.LENGTH_LONG).show();










                        }

                    }
                }
            });






        }



    public static WelcomeActivity getInstance() {
        return instance;
    }

public void responseMethod(String response)
{

    if(response!=null && response.equals("UNAUTHORIZED"))
    {
        premobileEditText.setVisibility(View.VISIBLE);
        mobileEditText.setVisibility(View.VISIBLE);
        tv1.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.VISIBLE);
        tv3.setVisibility(View.VISIBLE);
        tvp1.setVisibility(View.VISIBLE);
        tvp2.setVisibility(View.VISIBLE);
        tvp3.setVisibility(View.VISIBLE);
        btn.setVisibility(View.VISIBLE);
        pgsBar.setVisibility(View.GONE);
        mobileEditText.setError("Please enter registered mobile number");

        //Toast.makeText(WelcomeActivity.this, "Welcome Activity Response "+response, Toast.LENGTH_SHORT).show();
    }

    else if(response!=null &&  response.equals("NETWORKERROR"))
    {
        premobileEditText.setVisibility(View.VISIBLE);
        mobileEditText.setVisibility(View.VISIBLE);
        tv1.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.VISIBLE);
        tv3.setVisibility(View.VISIBLE);
        tvp1.setVisibility(View.VISIBLE);
        tvp2.setVisibility(View.VISIBLE);
        tvp3.setVisibility(View.VISIBLE);
        btn.setVisibility(View.VISIBLE);
        pgsBar.setVisibility(View.GONE);

      //  Toast.makeText(WelcomeActivity.this, "Welcome Activity Response "+response, Toast.LENGTH_SHORT).show();

        Toast.makeText(WelcomeActivity.this, "Network error. Please try after sometime.", Toast.LENGTH_SHORT).show();
    }

    else if(response!=null && response.equals("SUCCESS"))
    {

       // Toast.makeText(WelcomeActivity.this, "Response "+response, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(WelcomeActivity.this, OtpVerificationActivity.class);
        intent.putExtra("mobNumber", enteredMobileNumber);
        pgsBar.setVisibility(View.GONE);
        startActivity(intent);
        finish();
    }
}

    }
