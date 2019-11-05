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
import com.kaizenmax.taikinys_icl.presenter.DemoL3YieldPresenter;
import com.kaizenmax.taikinys_icl.presenter.DemoL3YieldPresenterInterface;
import com.kaizenmax.taikinys_icl.util.CommonConstants;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DemoL3Activity_YieldResult extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

   EditText dateOfYieldEditText;
   EditText yieldResultEditText;
   EditText expensesEditText;
   Button saveButton;



    Integer demoL3SerialId = null;

    HorizontalStepView setpview5;
    List<StepBean> stepsBeanList = new ArrayList<>();
    DatePickerDialog picker;
    DemoL3ActivityPresenter demoL3ActivityPresenterInterface;
    private static DemoL3Activity_YieldResult instance;
     String stage;

     String stage2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_l3__yield_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
  /*      FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


//getting ids by vinod on 14/09/2019

       dateOfYieldEditText = findViewById(R.id.dateOfYield);
       dateOfYieldEditText.setInputType(InputType.TYPE_NULL);

       yieldResultEditText = findViewById(R.id.yield);
       expensesEditText = findViewById(R.id.expenses);
       saveButton = findViewById(R.id.saveBtn);




        //getting ids by vinod ends


        instance = this ;



        demoL3ActivityPresenterInterface = new DemoL3ActivityPresenter();

        Intent i = getIntent();

       // Toast.makeText(instance, "id Hai : "+i.getStringExtra("DEMO_L3_SERIAL_ID"), Toast.LENGTH_SHORT).show();

        if(i!=null && (i.getStringExtra("DEMO_L3_SERIAL_ID")==null || i.getStringExtra("DEMO_L3_SERIAL_ID").equals(""))) {


            dateOfYieldEditText.setEnabled(false);
            dateOfYieldEditText.setFocusable(false);


            yieldResultEditText.setEnabled(false);
            yieldResultEditText.setFocusable(false);

            expensesEditText.setEnabled(false);
            expensesEditText.setFocusable(false);

            saveButton.setEnabled(false);


        }


        else
        {
            demoL3SerialId = Integer.valueOf(i.getStringExtra("DEMO_L3_SERIAL_ID"));


          //  Toast.makeText(instance, "Retrieving id is "+demoL3SerialId, Toast.LENGTH_SHORT).show();

            try {
                stage = demoL3ActivityPresenterInterface.getStage(demoL3SerialId);
            //    Toast.makeText(DemoL3Activity_YieldResult.this, "STAGE " + stage, Toast.LENGTH_SHORT).show();


                if(stage != null && (!stage.equals(CommonConstants.RESULT_YIELD.toString())))
                {

                 //   Toast.makeText(instance, "STAGE IS not null "+stage, Toast.LENGTH_SHORT).show();

                    Cursor cursor = demoL3ActivityPresenterInterface.getDemoL3DataFromID(demoL3SerialId);

                    if (cursor != null && cursor.moveToFirst()) {
                        do {
                            try {


                                String dateOfYield_retrieved = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_INTERIM));
                                String yieldResult_retrieved = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_INTERIM));
                                String expenses_retrieved = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_EXPENSES));

                       //         Toast.makeText(instance, "date of yield   "+dateOfYield_retrieved, Toast.LENGTH_SHORT).show();




                                dateOfYieldEditText.setText(dateOfYield_retrieved);
                                dateOfYieldEditText.setFocusable(false);
                                dateOfYieldEditText.setEnabled(false);

                                yieldResultEditText.setText(yieldResult_retrieved);
                                yieldResultEditText.setFocusable(false);
                                yieldResultEditText.setEnabled(false);

                                expensesEditText.setText(expenses_retrieved);
                                expensesEditText.setFocusable(false);
                                expensesEditText.setEnabled(false);


                                saveButton.setEnabled(false);











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








//method for pop-up of calender by vinod  on  13/08/2019
        dateOfYieldEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar clndr = Calendar.getInstance();
                int day = clndr.get(Calendar.DAY_OF_MONTH);
                int month = clndr.get(Calendar.MONTH);
                int year = clndr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(DemoL3Activity_YieldResult.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateOfYieldEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.getDatePicker().setMaxDate(new Date().getTime());



                picker.show();
                dateOfYieldEditText.setEnabled(false);
            }
        });







        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateOfYield_entered = dateOfYieldEditText.getText().toString();
                String yieldResult_entered = yieldResultEditText.getText().toString();
                String expense_entered = expensesEditText.getText().toString();
                Calendar cal=Calendar.getInstance();
                Date modifyDate=cal.getTime();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String modifyDate_string = dateFormat.format(modifyDate);
                Integer stage = CommonConstants.CLOSE;
                String uploadFlagStatus = "No";



                if (dateOfYield_entered != null && !dateOfYield_entered.equals("")
                        && yieldResult_entered !=null && !yieldResult_entered.equals("")
                        && demoL3SerialId != null && !demoL3SerialId.toString().equals("") )
                {
                    Toast.makeText(DemoL3Activity_YieldResult.this, "Saved successfully", Toast.LENGTH_SHORT).show();

                    DemoL3YieldPresenterInterface demoL3YieldPresenterInterface = new DemoL3YieldPresenter();

                    try {
                        demoL3YieldPresenterInterface.insertDemoL3Yielddata_Update(dateOfYield_entered,yieldResult_entered,expense_entered,
                                   demoL3SerialId, modifyDate_string, uploadFlagStatus, stage );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    try {
                       stage2 = demoL3ActivityPresenterInterface.getStage(demoL3SerialId);

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

                        StepBean stepBean3 = new StepBean(s4,-1);

                        StepBean stepBean4 = new StepBean(s5,0);



                        if (stage2!=null && stage2.equals(CommonConstants.PROTOCOL.toString())) {
                            stepBean0 = new StepBean(s1, 1);
                            stepBean1 = new StepBean(s2, -1);
                            stepBean2 = new StepBean(s3, -1);
                            stepBean3 = new StepBean(s4, -1);
                            stepBean4 = new StepBean(s5, 0);

                        } else if (stage2!=null && stage2.equals(CommonConstants.EXECUTION.toString())) {
                            stepBean0 = new StepBean(s1, 1);
                            stepBean1 = new StepBean(s2, 1);
                            stepBean2 = new StepBean(s3, -1);
                            stepBean3 = new StepBean(s4, -1);
                            stepBean4 = new StepBean(s5, 0);
                        } else if (stage2!=null && stage2.equals(CommonConstants.RESULT_INTERIM.toString())) {
                            stepBean0 = new StepBean(s1, 1);
                            stepBean1 = new StepBean(s2, 1);
                            stepBean2 = new StepBean(s3, 1);
                            stepBean3 = new StepBean(s4, -1);
                            stepBean4 = new StepBean(s5, 0);
                        } else if (stage2!=null && stage2.equals(CommonConstants.RESULT_YIELD.toString())) {
                            stepBean0 = new StepBean(s1, 1);
                            stepBean1 = new StepBean(s2, 1);
                            stepBean2 = new StepBean(s3, 1);
                            stepBean3 = new StepBean(s4, 1);
                            stepBean4 = new StepBean(s5, 0);
                        } else if (stage2!=null && stage2.equals(CommonConstants.CLOSE.toString())) {
                            stepBean0 = new StepBean(s1, 1);
                            stepBean1 = new StepBean(s2, 1);
                            stepBean2 = new StepBean(s3, 1);
                            stepBean3 = new StepBean(s4, 1);
                            stepBean4 = new StepBean(s5, 1);
                        }






                        stepsBeanList = new ArrayList<>();



                        stepsBeanList.add(stepBean0);

                        stepsBeanList.add(stepBean1);

                        stepsBeanList.add(stepBean2);

                        stepsBeanList.add(stepBean3);

                        stepsBeanList.add(stepBean4);

                        setpview5


                                .setStepViewTexts(stepsBeanList)//???

                                .setTextSize(12)//set textSize


                                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(DemoL3Activity_YieldResult.this, R.color.green))//??StepsViewIndicator??????

                                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(DemoL3Activity_YieldResult.this, android.R.color.darker_gray))//??StepsViewIndicator???????

                                .setStepViewComplectedTextColor(ContextCompat.getColor(DemoL3Activity_YieldResult.this, android.R.color.black))//??StepsView text??????

                                .setStepViewUnComplectedTextColor(ContextCompat.getColor(DemoL3Activity_YieldResult.this, android.R.color.black))//??StepsView text???????

                                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(DemoL3Activity_YieldResult.this, R.drawable.tick))//??StepsViewIndicator CompleteIcon

                                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(DemoL3Activity_YieldResult.this, R.drawable.default_icon))//??StepsViewIndicator DefaultIcon

                                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(DemoL3Activity_YieldResult.this, R.drawable.inprogress))

                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {


                                        // Toast.makeText(DemoL3Activity.this, "clicked", Toast.LENGTH_SHORT).show();


                                    }
                                });


                        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                            try {
                                // MandiCampaignDataPushNetworkOperationInterface mandiCampaignDataPushNetworkOperationInterface = new MandiCampaignDataPushNetworkOperation();
                                //mandiCampaignDataPushNetworkOperationInterface.sendingMcDataToWebService();

                                DemoL3DataPushNetworkOperationInterface demoL3DataPushNetworkOperationInterface = new DemoL3DataPushNetworkOperation();
                                demoL3DataPushNetworkOperationInterface.sendingDemoL3DataToWebService();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            //sendingDataToWebService(); to be removed
                        }


                        //      Toast.makeText(DemoL3Activity_Protocol.this, "demol3 serial id is : "+demoL3SerialId, Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(DemoL3Activity_YieldResult.this, DemoL3Activity.class);
                        //intent.putExtra("DEMO_L3_SERIAL_ID", "0");
                        startActivity(intent);
                        finish();







                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }


                else
                {

                    if (dateOfYield_entered == null || dateOfYield_entered.equals(""))
                        dateOfYieldEditText.setError("Please select date of yield");

                    if (yieldResult_entered == null || yieldResult_entered.equals(""))
                        yieldResultEditText.setError("Please enter yield result");

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

        StepBean stepBean3 = new StepBean(s4,-1);

        StepBean stepBean4 = new StepBean(s5,0);



        if (stage!=null && stage.equals(CommonConstants.PROTOCOL.toString())) {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2, -1);
            stepBean2 = new StepBean(s3, -1);
            stepBean3 = new StepBean(s4, -1);
            stepBean4 = new StepBean(s5, 0);

        } else if (stage!=null && stage.equals(CommonConstants.EXECUTION.toString())) {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2, 1);
            stepBean2 = new StepBean(s3, -1);
            stepBean3 = new StepBean(s4, -1);
            stepBean4 = new StepBean(s5, 0);
        } else if (stage!=null && stage.equals(CommonConstants.RESULT_INTERIM.toString())) {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2, 1);
            stepBean2 = new StepBean(s3, 1);
            stepBean3 = new StepBean(s4, -1);
            stepBean4 = new StepBean(s5, 0);
        } else if (stage!=null && stage.equals(CommonConstants.RESULT_YIELD.toString())) {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2, 1);
            stepBean2 = new StepBean(s3, 1);
            stepBean3 = new StepBean(s4, 1);
            stepBean4 = new StepBean(s5, 0);
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


                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(DemoL3Activity_YieldResult.this, R.color.green))//??StepsViewIndicator??????

                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(DemoL3Activity_YieldResult.this, android.R.color.darker_gray))//??StepsViewIndicator???????

                .setStepViewComplectedTextColor(ContextCompat.getColor(DemoL3Activity_YieldResult.this, android.R.color.black))//??StepsView text??????

                .setStepViewUnComplectedTextColor(ContextCompat.getColor(DemoL3Activity_YieldResult.this, android.R.color.black))//??StepsView text???????

                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(DemoL3Activity_YieldResult.this, R.drawable.tick))//??StepsViewIndicator CompleteIcon

                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(DemoL3Activity_YieldResult.this, R.drawable.default_icon))//??StepsViewIndicator DefaultIcon

                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(DemoL3Activity_YieldResult.this, R.drawable.inprogress))

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
        getMenuInflater().inflate(R.menu.demo_l3_activity__yield_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
    /*    if (id == R.id.action_settings) {
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


            Intent intent = new Intent(DemoL3Activity_YieldResult.this, IndividualFarmerContactMainActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();


        } else if (id == R.id.fm) {
            Intent intent = new Intent(DemoL3Activity_YieldResult.this, FarmerMeetingActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.fd) {

            Intent intent = new Intent(DemoL3Activity_YieldResult.this, FieldDayActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.dv) {
            Intent intent = new Intent(DemoL3Activity_YieldResult.this, DiagnosticVisitActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        }

        else if(id==R.id.mc){
            Intent intent = new Intent(DemoL3Activity_YieldResult.this, MandiCampaignActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.demol3) {

            Intent intent = new Intent(DemoL3Activity_YieldResult.this, DemoL3Activity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }


        else if (id == R.id.demol3_progress) {

            Intent intent = new Intent(DemoL3Activity_YieldResult.this, DemoL3_InProgressActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void previous(View v)
    {

        Intent intent = new Intent(DemoL3Activity_YieldResult.this, DemoL3_InterimResult.class);
        //pgsBar.setVisibility(View.GONE);
        if(demoL3SerialId!=null)
        intent.putExtra("DEMO_L3_SERIAL_ID", demoL3SerialId.toString());
        startActivity(intent);
        finish();

    }

    public static DemoL3Activity_YieldResult getInstance() {
        return instance;
    }

}
