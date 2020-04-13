package com.kaizenmax.taikinys_icl.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.kaizenmax.taikinys_icl.R;
import com.kaizenmax.taikinys_icl.model.DemoL3DataPushNetworkOperation;
import com.kaizenmax.taikinys_icl.model.DemoL3DataPushNetworkOperationInterface;
import com.kaizenmax.taikinys_icl.pojo.DemoL3Pojo;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.presenter.DemoL3InterimPresenter;
import com.kaizenmax.taikinys_icl.presenter.DemoL3InterimPresenterInterface;
import com.kaizenmax.taikinys_icl.util.CommonConstants;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DemoL3_InterimResult extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    EditText dateOfInterimEditText;
    EditText interimEditText;
    DatePickerDialog picker;

    Button saveBtn;

    Integer demoL3SerialId = null;
    HorizontalStepView setpview5;
    List<StepBean> stepsBeanList = new ArrayList<>();
    DemoL3ActivityPresenterInterface demoL3ActivityPresenterInterface ;
    private static DemoL3_InterimResult instance;
     String stage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_l3__interim_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    /*    FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        instance = this;


// getting ids by vinod on 14/09/2019

        dateOfInterimEditText = findViewById(R.id.dateOfInterim);
        dateOfInterimEditText.setInputType(InputType.TYPE_NULL);
        interimEditText = findViewById(R.id.interim);
        saveBtn = findViewById(R.id.saveBtn);
        //getting ids ends





        demoL3ActivityPresenterInterface = new DemoL3ActivityPresenter();


        Intent i = getIntent();

     //   Toast.makeText(instance, "id Hai : "+i.getStringExtra("DEMO_L3_SERIAL_ID"), Toast.LENGTH_SHORT).show();

        if(i!=null && (i.getStringExtra("DEMO_L3_SERIAL_ID")==null || i.getStringExtra("DEMO_L3_SERIAL_ID").equals(""))) {


            dateOfInterimEditText.setEnabled(false);
            dateOfInterimEditText.setFocusable(false);

            interimEditText.setEnabled(false);
            interimEditText.setFocusable(false);

            saveBtn.setEnabled(false);


        }


        else
        {
            demoL3SerialId = Integer.valueOf(i.getStringExtra("DEMO_L3_SERIAL_ID"));


           // Toast.makeText(instance, "Retrieving id is "+demoL3SerialId, Toast.LENGTH_SHORT).show();

            try {
                stage = demoL3ActivityPresenterInterface.getStage(demoL3SerialId);
              //  Toast.makeText(DemoL3_InterimResult.this, "STAGE " + stage, Toast.LENGTH_SHORT).show();


                if(stage != null && (!stage.equals(CommonConstants.RESULT_INTERIM.toString())))
                {

             //       Toast.makeText(instance, "STAGE IS not null "+stage, Toast.LENGTH_SHORT).show();

                    Cursor cursor = demoL3ActivityPresenterInterface.getDemoL3DataFromID(demoL3SerialId);

                    if (cursor != null && cursor.moveToFirst()) {
                        do {
                            try {


                                String dateOfInterim_retrieved = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_INTERIM));
                                String interimResult_retrieved = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_INTERIM));


                        //        Toast.makeText(instance, "date of interim execution  "+dateOfInterim_retrieved, Toast.LENGTH_SHORT).show();




                                dateOfInterimEditText.setText(dateOfInterim_retrieved);
                                dateOfInterimEditText.setFocusable(false);
                                dateOfInterimEditText.setEnabled(false);

                                interimEditText.setText(interimResult_retrieved);
                                interimEditText.setFocusable(false);
                                interimEditText.setEnabled(false);


                                saveBtn.setEnabled(false);











                            }

                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }




                        } while (cursor.moveToNext());

                        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
                    }
                    // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();





                    cursor.close();

                }


            } catch (Exception e) {
                e.printStackTrace();
            }






        }









        //method for pop-up of calender by vinod  on  14/09/2019
        dateOfInterimEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar clndr = Calendar.getInstance();
                int day = clndr.get(Calendar.DAY_OF_MONTH);
                int month = clndr.get(Calendar.MONTH);
                int year = clndr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(DemoL3_InterimResult.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateOfInterimEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.getDatePicker().setMaxDate(new Date().getTime());



                picker.show();
                dateOfInterimEditText.setEnabled(false);
            }
        });
        //method for pop-up of calender by vinod  on  14/09/2019


          saveBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  String dateOfInterim_entered = dateOfInterimEditText.getText().toString();
                  String interimResult_entered = interimEditText.getText().toString();
                  Calendar cal=Calendar.getInstance();
                  Date modifyDate=cal.getTime();
                  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                  String modifyDate_string = dateFormat.format(modifyDate);
                  Integer stage = CommonConstants.RESULT_YIELD;
                  String uploadFlagStatus = "No";

                  if (dateOfInterim_entered != null && !dateOfInterim_entered.equals("")
                          && interimResult_entered !=null && !interimResult_entered.equals("")
                          && demoL3SerialId != null && !demoL3SerialId.toString().equals("") )
                  {

                      Toast.makeText(DemoL3_InterimResult.this, "Saved successfully", Toast.LENGTH_SHORT).show();

                      DemoL3InterimPresenterInterface demoL3InterimPresenterInterface = new DemoL3InterimPresenter();


                      try {
                          demoL3InterimPresenterInterface.insertDemoL3Interimdata_Update(dateOfInterim_entered,
                                  interimResult_entered,modifyDate_string,
                                  stage,uploadFlagStatus, demoL3SerialId);

                     /*     dateOfInterimEditText.setBackgroundColor(android.R.color.darker_gray);
                          // dateOfActivityEditText.setHintTextColor(android.R.color.darker_gray);
                          dateOfInterimEditText.setTextColor(R.color.blackcolor);
                          dateOfInterimEditText.setEnabled(false);

                          interimEditText.setBackgroundColor(android.R.color.darker_gray);
                          // dateOfActivityEditText.setHintTextColor(android.R.color.darker_gray);
                          interimEditText.setTextColor(R.color.blackcolor);
                          interimEditText.setEnabled(false);

                          saveBtn.setEnabled(false); */




                      } catch (Exception e) {
                          e.printStackTrace();
                      }

                      ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                      if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                              connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                          try {

                              DemoL3DataPushNetworkOperationInterface demoL3DataPushNetworkOperationInterface = new DemoL3DataPushNetworkOperation();
                              demoL3DataPushNetworkOperationInterface.sendingDemoL3DataToWebService();

                              // MandiCampaignDataPushNetworkOperationInterface mandiCampaignDataPushNetworkOperationInterface = new MandiCampaignDataPushNetworkOperation();
                              //mandiCampaignDataPushNetworkOperationInterface.sendingMcDataToWebService();
                          } catch (Exception e) {
                              e.printStackTrace();
                          }
                          //sendingDataToWebService(); to be removed
                      }


                      //      Toast.makeText(DemoL3Activity_Protocol.this, "demol3 serial id is : "+demoL3SerialId, Toast.LENGTH_SHORT).show();


                      Intent intent = new Intent(DemoL3_InterimResult.this, DemoL3Activity_YieldResult.class);
                      intent.putExtra("DEMO_L3_SERIAL_ID", demoL3SerialId.toString());
                      startActivity(intent);
                      finish();




                  }



                  else
                  {
                      if (dateOfInterim_entered == null || dateOfInterim_entered.equals(""))
                          dateOfInterimEditText.setError("Please select date of interim");

                      if (interimResult_entered == null || interimResult_entered.equals(""))
                          interimEditText.setError("Please enter interim result");


                  }
              }
          });







        //code for multiple stepview by vinod on 11/9/2019
        setpview5 = (HorizontalStepView) findViewById(R.id.step_view);

        String s1 = "Farmer"
                +"\n"
                +"Details";

        String s2 = "Protocol";
        String s3 = "Execution";
        String s4 = "Interim"
                +"\n"
                +"Result" ;

        String s5 = "Yield"
                +"\n"
                +"Result" ;

        StepBean stepBean0 = new StepBean(s1,-1);

        StepBean stepBean1 = new StepBean(s2,-1);

        StepBean stepBean2 = new StepBean(s3,-1);

        StepBean stepBean3 = new StepBean(s4,0);

        StepBean stepBean4 = new StepBean(s5,-1);








        if (stage!=null && stage.equals(CommonConstants.PROTOCOL.toString())) {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2, -1);
            stepBean2 = new StepBean(s3, -1);
            stepBean3 = new StepBean(s4, 0);
            stepBean4 = new StepBean(s5, -1);

        } else if (stage!=null && stage.equals(CommonConstants.EXECUTION.toString())) {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2, 1);
            stepBean2 = new StepBean(s3, -1);
            stepBean3 = new StepBean(s4, 0);
            stepBean4 = new StepBean(s5, -1);
        } else if (stage!=null && stage.equals(CommonConstants.RESULT_INTERIM.toString())) {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2, 1);
            stepBean2 = new StepBean(s3, 1);
            stepBean3 = new StepBean(s4, 0);
            stepBean4 = new StepBean(s5, -1);
        } else if (stage!=null && stage.equals(CommonConstants.RESULT_YIELD.toString())) {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2, 1);
            stepBean2 = new StepBean(s3, 1);
            stepBean3 = new StepBean(s4, 1);
            stepBean4 = new StepBean(s5, -1);
        } else if (stage!=null && stage.equals(CommonConstants.CLOSE.toString())) {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2, 1);
            stepBean2 = new StepBean(s3, 1);
            stepBean3 = new StepBean(s4, 1);
            stepBean4 = new StepBean(s5, 1);
        }

        stepsBeanList.add(stepBean0);

        stepsBeanList.add(stepBean1);

        stepsBeanList.add(stepBean2);

        stepsBeanList.add(stepBean3);

        stepsBeanList.add(stepBean4);

        setpview5


                .setStepViewTexts(stepsBeanList)//???

                .setTextSize(12)//set textSize


                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(DemoL3_InterimResult.this, R.color.green))//??StepsViewIndicator??????

                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(DemoL3_InterimResult.this, android.R.color.darker_gray))//??StepsViewIndicator???????

                .setStepViewComplectedTextColor(ContextCompat.getColor(DemoL3_InterimResult.this, android.R.color.black))//??StepsView text??????

                .setStepViewUnComplectedTextColor(ContextCompat.getColor(DemoL3_InterimResult.this, android.R.color.black))//??StepsView text???????

                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(DemoL3_InterimResult.this, R.drawable.tick))//??StepsViewIndicator CompleteIcon

                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(DemoL3_InterimResult.this, R.drawable.default_icon))//??StepsViewIndicator DefaultIcon

                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(DemoL3_InterimResult.this, R.drawable.inprogress))

                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        // Toast.makeText(DemoL3Activity.this, "clicked", Toast.LENGTH_SHORT).show();


                    }
                });

//??StepsViewIndicator AttentionIcon









        //code for multiple stepview ends
















    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.demo_l3__interim_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }  */

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.ifc) {
            // IFC Activity clicked by user


            Intent intent = new Intent(DemoL3_InterimResult.this, IndividualFarmerContactMainActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();


        } else if (id == R.id.fm) {
            Intent intent = new Intent(DemoL3_InterimResult.this, FarmerMeetingActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.fd) {

            Intent intent = new Intent(DemoL3_InterimResult.this, FieldDayActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.dv) {
            Intent intent = new Intent(DemoL3_InterimResult.this, DiagnosticVisitActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        }

        else if(id==R.id.mc){
            Intent intent = new Intent(DemoL3_InterimResult.this, MandiCampaignActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.demol3) {

            Intent intent = new Intent(DemoL3_InterimResult.this, DemoL3Activity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }


        else if (id == R.id.demol3_progress) {

            Intent intent = new Intent(DemoL3_InterimResult.this, DemoL3_InProgressActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.pastRecord) {
            Intent intent = new Intent(DemoL3_InterimResult.this, PastRecordActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void next(View v)
    {

        Intent intent = new Intent(DemoL3_InterimResult.this, DemoL3Activity_YieldResult.class);
        //pgsBar.setVisibility(View.GONE);

        if(demoL3SerialId!=null)
        intent.putExtra("DEMO_L3_SERIAL_ID", demoL3SerialId.toString());
        startActivity(intent);
        finish();

    }


    public void previous(View v)
    {

        Intent intent = new Intent(DemoL3_InterimResult.this, DemoL3Activity_Execution.class);
        //pgsBar.setVisibility(View.GONE);
        if(demoL3SerialId!=null)
        intent.putExtra("DEMO_L3_SERIAL_ID", demoL3SerialId.toString());
        startActivity(intent);
        finish();

    }

    public static DemoL3_InterimResult getInstance() {
        return instance;
    }


}
