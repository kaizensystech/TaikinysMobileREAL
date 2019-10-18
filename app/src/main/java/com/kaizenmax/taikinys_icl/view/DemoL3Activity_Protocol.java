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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.kaizenmax.taikinys_icl.R;
import com.kaizenmax.taikinys_icl.model.DemoL3DataPushNetworkOperation;
import com.kaizenmax.taikinys_icl.model.DemoL3DataPushNetworkOperationInterface;
import com.kaizenmax.taikinys_icl.pojo.DemoL3Pojo;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ProtocolPresenter;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ProtocolPresenterInterface;
import com.kaizenmax.taikinys_icl.util.ActivityNames;
import com.kaizenmax.taikinys_icl.util.CommonConstants;
import com.kaizenmax.taikinys_icl.util.MultiSelectionSpinner;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DemoL3Activity_Protocol extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    EditText dateOfProtocolEditText;
    Spinner cropCategorySpinner;
    Spinner cropFocusSpinner;
    Spinner meetingPurposeSpinner;
    MultiSelectionSpinner productFocusMultiSelectSpinner;
    DatePickerDialog picker;
    Button saveBtn;
    List<String> cropCategories=new ArrayList<String>();
    List<String> cropFocusList=new ArrayList<String>();


    String selectedCropCategory;
    String selectedCropFocus;
    String selectedMeetingPurpose;


    Integer demoL3SerialId = null;

    private static DemoL3Activity_Protocol instance;

    List<String> meetingPurposeList = new ArrayList<String>();

    HorizontalStepView setpview5;
    List<StepBean> stepsBeanList = new ArrayList<>();

    DemoL3ActivityPresenterInterface demoL3ActivityPresenterInterface ;
    String clientName;
    String faOfficeState;

    String stage;

    Integer disabledStatus=0;
    String cropFocus_retrieved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_l3__protocol);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     /*   FloatingActionButton fab = findViewById(R.id.fab);
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




demoL3ActivityPresenterInterface = new DemoL3ActivityPresenter();

instance = this ;



//getting ids by vinod on 13/09/2019
        dateOfProtocolEditText = findViewById(R.id.dateOfProtocol);
        dateOfProtocolEditText.setInputType(InputType.TYPE_NULL);
        cropCategorySpinner = findViewById(R.id.cropCategory);
        cropFocusSpinner = findViewById(R.id.cropFocus);
        meetingPurposeSpinner = findViewById(R.id.meetingpurpose);
        productFocusMultiSelectSpinner = findViewById(R.id.productFocus);
        saveBtn = findViewById(R.id.saveBtn);













        //getting ids by vinod on 13/09/2019 ends

        //method for pop-up of calender by vinod  on  13/08/2019
        dateOfProtocolEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar clndr = Calendar.getInstance();
                int day = clndr.get(Calendar.DAY_OF_MONTH);
                int month = clndr.get(Calendar.MONTH);
                int year = clndr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(DemoL3Activity_Protocol.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateOfProtocolEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.getDatePicker().setMaxDate(new Date().getTime());



                picker.show();
            }
        });



        try {
            clientName = demoL3ActivityPresenterInterface.getClientName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            faOfficeState=demoL3ActivityPresenterInterface.getStateName();
        } catch (Exception e) {
            e.printStackTrace();
        }





        //SETTING LIST IN PRODUCT FOCUS
        List<String> productlist = new ArrayList<String>();
        productlist.add("ASD");

//String clientName = dbHelper.getClientName();
//String faOfficeState=dbHelper.getStateName();


        try {

            productlist=demoL3ActivityPresenterInterface.getProductFocusList(clientName,faOfficeState);
            productFocusMultiSelectSpinner.setItems(productlist);


        } catch (Exception e) {
            e.printStackTrace();
        }




        //  Toast.makeText(IndividualFarmerContactActivity.this, "ClientName "+clientName +" State"+faOfficeState, Toast.LENGTH_SHORT).show();

//SETTING LIST IN PRODUCT FOCUS ENDS



//GETTING CROP CATEGORIES

        try {
            cropCategories=demoL3ActivityPresenterInterface.getCropCategories();
        } catch (Exception e) {
            e.printStackTrace();
        }

// GETTINNG  CROP CATEGORIES ENDS


        //Adding List to Crop Category

        ArrayAdapter<String> cropCategoryDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cropCategories);


        cropCategoryDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cropCategorySpinner.setAdapter(cropCategoryDataAdapter);

        //Adding List Crop Category Ends


        // Adding List to CropFocus



        //Adding Listener to Crop category so that crop Focus list can be according to selected crop category

        cropCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCropCategory= cropCategories.get(position);

                // cropFocusList=   dbHelper.getCropFocus(selectedCropCategory);

               // Toast.makeText(DemoL3Activity_Protocol.this, "CROP CATEGORY CLICK HUA", Toast.LENGTH_SHORT).show();

                if(disabledStatus==101)
                {
                    cropFocusList = new ArrayList<String>();

                    cropFocusList.add(cropFocus_retrieved);

                    ArrayAdapter<String> cropFocusDataAdapter = new ArrayAdapter<String>(DemoL3Activity_Protocol.this, android.R.layout.simple_spinner_dropdown_item, cropFocusList);


                    cropFocusDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    cropFocusSpinner.setAdapter(cropFocusDataAdapter);


                }

                else {

                    try {
                        cropFocusList = demoL3ActivityPresenterInterface.getCropFocus(selectedCropCategory);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ArrayAdapter<String> cropFocusDataAdapter = new ArrayAdapter<String>(DemoL3Activity_Protocol.this, android.R.layout.simple_spinner_dropdown_item, cropFocusList);


                    cropFocusDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    cropFocusSpinner.setAdapter(cropFocusDataAdapter);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Adding Listener to Crop category so that crop Focus list can be according to selected crop category

        //Adding Listener to CropFocus to get selectedCropFocus

        cropFocusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedCropFocus= cropFocusList.get(position);

              //  Toast.makeText(DemoL3Activity_Protocol.this, "CROP FOCUS CLICK HUA", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Adding Listener to CropFocus to get selectedCropFocus Ends



        //Adding method to get meeting purposes by vinod on 13/08/2019


        // meetingPurposeList.add("Select Meeting Purpose*");
        //meetingPurposeList.add("A");

//String clientName = dbHelper.getClientName();
//String faOfficeState=dbHelper.getStateName();


        try {

            meetingPurposeList=demoL3ActivityPresenterInterface.getMeetingPurposeList(ActivityNames.DEMOL3);

            ArrayAdapter<String> meetingPurposeDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, meetingPurposeList);


            meetingPurposeDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            meetingPurposeSpinner.setAdapter(meetingPurposeDataAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }




        //Adding listener on meetingPurpose by vinod on 16/08/2019

        final List<String> finalMeetingPurposeList = meetingPurposeList;
        meetingPurposeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedMeetingPurpose=   finalMeetingPurposeList.get(position);

                //  Toast.makeText(FarmerMeetingActivity.this, ""+selectedMeetingPurpose, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        //  Toast.makeText(IndividualFarmerContactActivity.this, "ClientName "+clientName +" State"+faOfficeState, Toast.LENGTH_SHORT).show();

//SETTING LIST IN PRODUCT FOCUS ENDS








        Intent i = getIntent();

    //    Toast.makeText(instance, "id Hai : "+i.getStringExtra("DEMO_L3_SERIAL_ID"), Toast.LENGTH_SHORT).show();

        if(i!=null && (i.getStringExtra("DEMO_L3_SERIAL_ID")==null || i.getStringExtra("DEMO_L3_SERIAL_ID").equals(""))) {


            dateOfProtocolEditText.setFocusable(false);
            dateOfProtocolEditText.setEnabled(false);
            cropCategorySpinner.setEnabled(false);
            cropFocusSpinner.setEnabled(false);
            meetingPurposeSpinner.setEnabled(false);
            productFocusMultiSelectSpinner.setEnabled(false);
            saveBtn.setEnabled(false);
        }


        else
        {
            demoL3SerialId = Integer.valueOf(i.getStringExtra("DEMO_L3_SERIAL_ID"));


          //  Toast.makeText(instance, "Retrieving id is "+demoL3SerialId, Toast.LENGTH_SHORT).show();

            try {
                stage = demoL3ActivityPresenterInterface.getStage(demoL3SerialId);
              //  Toast.makeText(DemoL3Activity_Protocol.this, "STAGE " + stage, Toast.LENGTH_SHORT).show();


                if(stage != null && (!stage.equals(CommonConstants.PROTOCOL.toString())))
                {

                    disabledStatus=101;
                 //   Toast.makeText(instance, "STAGE IS not null "+stage, Toast.LENGTH_SHORT).show();

                    Cursor cursor = demoL3ActivityPresenterInterface.getDemoL3DataFromID(demoL3SerialId);

                    if (cursor != null && cursor.moveToFirst()) {
                        do {
                            try {


                                String dateOfProtocol_retrieved = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_PROTOCOL));
                                String cropCategory_retrieved = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_CROP_CATEGORY));

                                 cropFocus_retrieved = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_CROP_FOCUS));
                                String meetingPurpose_retrieved = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DEMO_PURPOSE));


                             //   Toast.makeText(instance, "date of protocol retrieve  "+dateOfProtocol_retrieved, Toast.LENGTH_SHORT).show();


                                dateOfProtocolEditText.setText(dateOfProtocol_retrieved);
                                dateOfProtocolEditText.setEnabled(false);
                                dateOfProtocolEditText.setFocusable(false);
                                int x= cropCategories.indexOf(cropCategory_retrieved);
                                cropCategorySpinner.setSelection(x);
                                cropCategorySpinner.setEnabled(false);
                                cropFocusSpinner.setEnabled(false);


                             //   TextView errorText = (TextView) cropFocusSpinner.getSelectedView();
                               // errorText.setText(cropFocus_retrieved);
                             //   cropFocusSpinner.setEnabled(false);


                                int y= meetingPurposeList.indexOf(meetingPurpose_retrieved);
                                meetingPurposeSpinner.setSelection(y);
                                meetingPurposeSpinner.setEnabled(false);



                                saveBtn.setEnabled(false);





                                List<String> selectedProductList = demoL3ActivityPresenterInterface.getSelectedProductList(demoL3SerialId);
                                productFocusMultiSelectSpinner.setSelection(selectedProductList);
                                productFocusMultiSelectSpinner.setEnabled(false);









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

























        // Adding method to get meeting purposes ends



        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateOfProtocol_entered = dateOfProtocolEditText.getText().toString();
                String cropCategory_entered = selectedCropCategory;
                String cropFocus_entered = selectedCropFocus;
                String meetingPurpose_entered = selectedMeetingPurpose;
                List<String> selectedProductList = productFocusMultiSelectSpinner.getSelectedStrings();
                Calendar cal=Calendar.getInstance();
                Date modifyDate=cal.getTime();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String modifyDate_string = dateFormat.format(modifyDate);
                Integer stage = CommonConstants.EXECUTION;

                String uploadFlagStatus = "No";





                if (dateOfProtocol_entered != null && !dateOfProtocol_entered.equals("")
                        && cropCategory_entered !=null && !cropCategory_entered.equals("")
                        && cropFocus_entered != null && !cropFocus_entered.equals("")
                        && meetingPurpose_entered != null && !meetingPurpose_entered.equals("")
                        && selectedProductList.size() != 0 && !(selectedProductList.size() == 1 && selectedProductList.get(0).equals("Select Product Focus*"))
                        && demoL3SerialId != null && !demoL3SerialId.toString().equals("") ){

                    Toast.makeText(DemoL3Activity_Protocol.this, "Saved successfully", Toast.LENGTH_SHORT).show();

                    DemoL3ProtocolPresenterInterface demoL3ProtocolPresenterInterface = new DemoL3ProtocolPresenter();


                    try {
                        //this interface belongs to DemoLeProtocol activity i.e demoL3ProtocolPresenterInterface

                        demoL3ProtocolPresenterInterface.insertDemoL3Protocoldata_Update(dateOfProtocol_entered,
                                 cropCategory_entered, cropFocus_entered,  meetingPurpose_entered, selectedProductList,
                                 demoL3SerialId,  uploadFlagStatus, modifyDate_string, stage);



                     /*   dateOfProtocolEditText.setBackgroundColor(android.R.color.darker_gray);
                        // dateOfActivityEditText.setHintTextColor(android.R.color.darker_gray);
                        dateOfProtocolEditText.setTextColor(R.color.blackcolor);
                        dateOfProtocolEditText.setEnabled(false);
                        cropCategorySpinner.setEnabled(false);
                        cropFocusSpinner.setEnabled(false);
                        meetingPurposeSpinner.setEnabled(false);
                        productFocusMultiSelectSpinner.setEnabled(false);
                        saveBtn.setEnabled(false);*/

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


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


                    Intent intent = new Intent(DemoL3Activity_Protocol.this, DemoL3Activity_Execution.class);
                    //pgsBar.setVisibility(View.GONE);

                    intent.putExtra("DEMO_L3_SERIAL_ID", demoL3SerialId.toString());
                    startActivity(intent);
                    finish();

                }


                else
                {
                    if (dateOfProtocol_entered == null || dateOfProtocol_entered.equals(""))
                        dateOfProtocolEditText.setError("Please select date of protocol");


                    if (cropCategory_entered == null || cropCategory_entered.equals("") || cropCategory_entered.equals("Select Crop Category*")) {
                        TextView errorText = (TextView) cropCategorySpinner.getSelectedView();
                        errorText.setError("Please select crop category");
                    }

                    if (cropFocus_entered == null || cropFocus_entered.equals("") || cropFocus_entered.equals("Select Crop Focus*")) {
                        TextView errorText = (TextView) cropFocusSpinner.getSelectedView();
                        errorText.setError("Please select crop focus");
                    }

                    if (meetingPurpose_entered == null || meetingPurpose_entered.equals("") || meetingPurpose_entered.equals("Select Meeting Purpose*")) {
                        TextView errorText = (TextView) meetingPurposeSpinner.getSelectedView();
                        errorText.setError("Please select meeting purpose");
                    }

                    if (selectedProductList.size() == 0 || (selectedProductList.size() == 1 && selectedProductList.get(0).equals("Select Product Focus*"))) {
                        TextView errorText = (TextView) productFocusMultiSelectSpinner.getSelectedView();
                        errorText.setError("Please select product focus");
                    }
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


        StepBean stepBean0 = new StepBean(s1, -1);
        StepBean stepBean1 = new StepBean(s2,0);

        StepBean stepBean2 = new StepBean(s3,-1);

        StepBean stepBean3 = new StepBean(s4,-1);

        StepBean stepBean4 = new StepBean(s5,-1);
        if (stage!=null && stage.equals(CommonConstants.PROTOCOL.toString())) {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2,0);
            stepBean2 = new StepBean(s3,-1);
            stepBean3 = new StepBean(s4,-1);
            stepBean4 = new StepBean(s5,-1);

        }

        else if(stage!=null && stage.equals(CommonConstants.EXECUTION.toString()))
        {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2,1);
            stepBean2 = new StepBean(s3,-1);
            stepBean3 = new StepBean(s4,-1);
            stepBean4 = new StepBean(s5,-1);
        }

        else if(stage!=null && stage.equals(CommonConstants.RESULT_INTERIM.toString()))
        {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2,1);
            stepBean2 = new StepBean(s3,1);
            stepBean3 = new StepBean(s4,-1);
            stepBean4 = new StepBean(s5,-1);
        }


        else if(stage!=null && stage.equals(CommonConstants.RESULT_YIELD.toString()))
        {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2,1);
            stepBean2 = new StepBean(s3,1);
            stepBean3 = new StepBean(s4,1);
            stepBean4 = new StepBean(s5,-1);
        }

        else if(stage!=null && stage.equals(CommonConstants.CLOSE.toString()))
        {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2,1);
            stepBean2 = new StepBean(s3,1);
            stepBean3 = new StepBean(s4,1);
            stepBean4 = new StepBean(s5,1);
        }






        stepsBeanList.add(stepBean0);

        stepsBeanList.add(stepBean1);

        stepsBeanList.add(stepBean2);

        stepsBeanList.add(stepBean3);

        stepsBeanList.add(stepBean4);

        setpview5


                .setStepViewTexts(stepsBeanList)//???

                .setTextSize(12)//set textSize


                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(DemoL3Activity_Protocol.this, R.color.green))//??StepsViewIndicator??????

                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(DemoL3Activity_Protocol.this, android.R.color.darker_gray))//??StepsViewIndicator???????

                .setStepViewComplectedTextColor(ContextCompat.getColor(DemoL3Activity_Protocol.this, android.R.color.black))//??StepsView text??????

                .setStepViewUnComplectedTextColor(ContextCompat.getColor(DemoL3Activity_Protocol.this, android.R.color.black))//??StepsView text???????

                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(DemoL3Activity_Protocol.this, R.drawable.tick))//??StepsViewIndicator CompleteIcon

                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(DemoL3Activity_Protocol.this, R.drawable.default_icon))//??StepsViewIndicator DefaultIcon

                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(DemoL3Activity_Protocol.this, R.drawable.inprogress))

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
        getMenuInflater().inflate(R.menu.demo_l3_activity__protocol, menu);
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
        } */

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.ifc) {
            // IFC Activity clicked by user


            Intent intent = new Intent(DemoL3Activity_Protocol.this, IndividualFarmerContactMainActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();


        } else if (id == R.id.fm) {
            Intent intent = new Intent(DemoL3Activity_Protocol.this, FarmerMeetingActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.fd) {

            Intent intent = new Intent(DemoL3Activity_Protocol.this, FieldDayActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.dv) {
            Intent intent = new Intent(DemoL3Activity_Protocol.this, DiagnosticVisitActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        }

        else if(id==R.id.mc){
            Intent intent = new Intent(DemoL3Activity_Protocol.this, MandiCampaignActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.demol3) {

            Intent intent = new Intent(DemoL3Activity_Protocol.this, DemoL3Activity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.demol3_progress) {

            Intent intent = new Intent(DemoL3Activity_Protocol.this, DemoL3_InProgressActivity.class);
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

        Intent intent = new Intent(DemoL3Activity_Protocol.this, DemoL3Activity_Execution.class);
        //pgsBar.setVisibility(View.GONE);
        if(demoL3SerialId!=null)
        intent.putExtra("DEMO_L3_SERIAL_ID", demoL3SerialId.toString());
        startActivity(intent);
        finish();
    }


    public void previous(View v)
    {

        Intent intent = new Intent(DemoL3Activity_Protocol.this, DemoL3Activity.class);

        if(demoL3SerialId!=null)
        intent.putExtra("DEMO_L3_SERIAL_ID", demoL3SerialId.toString());
        startActivity(intent);
        finish();

    }


    public static DemoL3Activity_Protocol getInstance() {
        return instance;
    }

}
