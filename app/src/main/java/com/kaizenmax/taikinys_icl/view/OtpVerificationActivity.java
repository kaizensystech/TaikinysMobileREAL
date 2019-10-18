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
import com.kaizenmax.taikinys_icl.presenter.OtpVerificationActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.OtpVerificationActivityPresenterInterface;


public class OtpVerificationActivity extends AppCompatActivity {

        EditText otpEditText;
        Button submitBtn;
        String enteredMobileNumber;
        String enteredOtp;
        ProgressBar pgsBar;
        static OtpVerificationActivity instance;
        TextView otpTextView;



    OtpVerificationActivityPresenterInterface otpVerificationActivityPresenterInterface;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_otp_verification);

            otpEditText=findViewById(R.id.otp);
            submitBtn=findViewById(R.id.otpBtn);
            otpTextView=findViewById(R.id.otptextview);

            pgsBar=findViewById(R.id.pBar);
            pgsBar.setVisibility(View.GONE);


            Intent intent = getIntent();
            enteredMobileNumber = intent.getStringExtra("mobNumber");


            // Toast.makeText(this, "Entered nm is "+enteredMobileNumber, Toast.LENGTH_SHORT).show();
            instance=this;


            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enteredOtp=otpEditText.getText().toString();


                    if(enteredOtp.equals("") || enteredMobileNumber.equals(""))
                    {
                        otpEditText.setError("Please enter OTP.");
                    }
                    else {



                        otpVerificationActivityPresenterInterface=new OtpVerificationActivityPresenter();
                        try {
                            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                                otpTextView.setVisibility(View.GONE);
                                otpEditText.setVisibility(View.GONE);
                                submitBtn.setVisibility(View.GONE);

                                pgsBar.setVisibility(View.VISIBLE);
                                otpVerificationActivityPresenterInterface.sendingMobileandOtpToWebservice(enteredMobileNumber, enteredOtp);

                            }
                            else
                            {
                                Toast.makeText(OtpVerificationActivity.this, "Sorry, no internet connectivity. Please reconnect and try again.", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // sendingMobileandOtpToWebservice(enteredMobileNumber, enteredOtp);
                    }

                }
            });


        }


  /*      private void sendingMobileandOtpToWebservice(final String mobileNumber, final String otp)
        {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjReq=null;

            String url = "https://tvsfinal.herokuapp.com/faces/rest/service/validateUser";

            String url="https://tvsfinal.herokuapp.com/rest/service/userFA_Master";



            final JSONObject postparams = new JSONObject();
            try {
                postparams.put("userName", mobileNumber);
                postparams.put("otp", otp);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    url, postparams,
                    new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {
                             Toast.makeText(IndividualFarmerContactActivity.this, "URL "+url, Toast.LENGTH_SHORT).show();
                           Toast.makeText(OtpVerificationActivity.this, "Success "+response, Toast.LENGTH_SHORT).show();

                           SharedPreferences.Editor editor = sharedpreferences.edit();

                            editor.putString("mobilePhone",mobileNumber );
                            editor.putString("otp",otp );
                            editor.commit();


     JSONObject jsonObject= (JSONObject) response;

                            try {
                                String faFirstName=jsonObject.getString("faFirstName");
                                String faLastName=jsonObject.getString("faLastName");
                            String faOfficeTerritory=jsonObject.getString("fAOfficeTerritory");
                            String clientName=jsonObject.getString("clientName");
                            String faValidityFrom=jsonObject.getString("faValidityFrom");
                            String faValidityTo=jsonObject.getString("faValidityTo");
                            String faDistrict=jsonObject.getString("fAOfficeLocation");
                            String headquarter=jsonObject.getString("headquarter");
                            String faOfficeRegionalOffice=jsonObject.getString("fAOfficeRegionalOffice");
                            String faOfficeState=jsonObject.getString("fAOfficeState");
                            String status=jsonObject.getString("status");


                            String userName=jsonObject.getString("userName");
                            String password=jsonObject.getString("password");
                            String otp=jsonObject.getString("otp");
                            String userStatus=jsonObject.getString("userStatus");

                                JSONArray jsonArray=null;


String j2=jsonObject.getString("villageName");

jsonArray=j2.optJSONArray("villageName");

                                if(j2.equals("null"))
                                {
                                    Toast.makeText(OtpVerificationActivity.this, "obj "+j2, Toast.LENGTH_SHORT).show();
                                }
                           else
                                {
                                    jsonArray =jsonObject.getJSONArray("villageName");
                                }


                                // jsonArray =jsonObject.getJSONArray("villageName");
                               Toast.makeText(OtpVerificationActivity.this, "jsonArray "+jsonArray, Toast.LENGTH_SHORT)
                                        .show();


               if(jsonArray!=null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                    Get current json object
                      String villageName = jsonArray.getString(i);
                     dbHelper.insertVillagesData(villageName);
               }

      }



                              Toast.makeText(OtpVerificationActivity.this, "faFirstName "+faFirstName
                                        +"faLastName "+faLastName
                                        +"faOfficeTerritory "+faOfficeTerritory
                                        +"clientName "+clientName
                                        +"faValidityFrom "+faValidityFrom
                                        +"faValidityTo "+faValidityTo
                                        +"faDistrict "+faDistrict
                                        +"headquarter "+headquarter
                                        +"faOfficeRegionalOffice "+faOfficeRegionalOffice
                                        +"faOfficeState "+faOfficeState
                                        +"status "+status

                                        , Toast.LENGTH_SHORT).show();

                             Toast.makeText(OtpVerificationActivity.this, "userName "+userName
                                        +" password "+password
                                        +" otp "+otp
                                        +" userStatus "+userStatus , Toast.LENGTH_SHORT).show();



                           Toast.makeText(OtpVerificationActivity.this, "District "+faDistrict
                                        +" Client "+clientName, Toast.LENGTH_SHORT).show();


                                dbHelper.insertFAMasterData(faFirstName,faLastName,
                                        faOfficeTerritory,clientName,faValidityFrom,
                                        faValidityTo,faDistrict,headquarter,faOfficeRegionalOffice,
                                        faOfficeState,status
                                        );

                               dbHelper.insertUsersData(userName,otp,password,userStatus);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }










                            Intent intent = new Intent(OtpVerificationActivity.this, IndividualFarmerContactActivity.class);
                            intent.putExtra("userName",enteredMobileNumber);
                            startActivity(intent);
                            finish();













                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Failure Callback
                            otpEditText.setVisibility(View.VISIBLE);
                            submitBtn.setVisibility(View.VISIBLE);
                            pgsBar.setVisibility(View.GONE);
                            otpEditText.setError("Please enter valid OTP.");
                            Toast.makeText(OtpVerificationActivity.this, "Error "+error.networkResponse.statusCode, Toast.LENGTH_SHORT).show();
                        }
                    });
            Adding the request to the queue along with a unique string tag

            requestQueue.add(jsonObjReq);



        }    */





    public void successResponseMethod(String responseResult,Object response)
    {
        if(responseResult!=null && responseResult.equals("SUCCESS") && response!=null)
        {

            //  Toast.makeText(IndividualFarmerContactActivity.this, "URL "+url, Toast.LENGTH_SHORT).show();
            // Toast.makeText(OtpVerificationActivity.this, "Success "+response, Toast.LENGTH_SHORT).show();
     try {
    otpVerificationActivityPresenterInterface.savingMobileAndOtpInSharedPreference(enteredMobileNumber, enteredOtp);
       }
     catch (Exception e)
         {
    e.printStackTrace();
         }

            try {
                otpVerificationActivityPresenterInterface.savingFaMasterInLocalDatabase(response);
            } catch (Exception e) {
                e.printStackTrace();
            }


            Intent intent = new Intent(OtpVerificationActivity.this, IndividualFarmerContactMainActivity.class);
            intent.putExtra("userName",enteredMobileNumber);
            startActivity(intent);
            finish();









        }
    }


    public void errorResponseMethod(String response)
    {

        if(response!=null && response.equals("UNAUTHORIZED"))
        {
            otpTextView.setVisibility(View.VISIBLE);
            otpEditText.setVisibility(View.VISIBLE);
            submitBtn.setVisibility(View.VISIBLE);
            pgsBar.setVisibility(View.GONE);
            otpEditText.setError("Please enter valid OTP.");

        }

        else if(response!=null &&  response.equals("NETWORKERROR"))
        {
            otpTextView.setVisibility(View.VISIBLE);
            otpEditText.setVisibility(View.VISIBLE);
            submitBtn.setVisibility(View.VISIBLE);
            pgsBar.setVisibility(View.GONE);

            //  Toast.makeText(WelcomeActivity.this, "Welcome Activity Response "+response, Toast.LENGTH_SHORT).show();

            Toast.makeText(OtpVerificationActivity.this, "Network error. Please try after sometime.", Toast.LENGTH_SHORT).show();
        }


    }

    public static OtpVerificationActivity getInstance() {
        return instance;
    }


    }
