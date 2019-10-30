package com.kaizenmax.taikinys_icl.view;

import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.kaizenmax.taikinys_icl.R;
import com.kaizenmax.taikinys_icl.model.DemoL3DataPushNetworkOperation;
import com.kaizenmax.taikinys_icl.model.DemoL3DataPushNetworkOperationInterface;
import com.kaizenmax.taikinys_icl.pojo.DemoL3Pojo;
import com.kaizenmax.taikinys_icl.pojo.FarmerDetailsPojo;
import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.util.CommonConstants;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DemoL3Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {




    EditText dateOfActivityEditText;
    EditText farmerNameEditText;
    EditText farmerMobileEditText;
    EditText farmerLandEditText;
    AutoCompleteTextView villageNameEditText;
    Button uploadButton;
    Button saveButton;
    TextView demoL3SerialIdTextView;
    TextView updateDemoL3TextView;

    DatePickerDialog picker;
    TextView selectedFilesCountTextview;
    String demoL3SerialId_populated;

    //For upload
    int PICK_IMAGE_MULTIPLE = 1;
    List<byte []> attachmentList ;
    AutoCompleteTextView firmName;
    EditText proprietorName;
    EditText retailerMobile;
    String clientName;
    Button addButton;


    private static DemoL3Activity instance;
    private android.support.v7.widget.LinearLayoutCompat parentLinearLayout;
    List<View> viewList;

    DemoL3ActivityPresenterInterface demoL3ActivityPresenterInterface;

    HorizontalStepView setpview5;
    List<StepBean> stepsBeanList ;
    List<RetailerDetailsPojo> retailerDetailsPojoList;

    String stage;
     Integer demoL3SerialId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_l3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      /*  FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */



      //

        attachmentList =new ArrayList<byte []>();
        viewList=new ArrayList<View>();
        stepsBeanList = new ArrayList<>();
        retailerDetailsPojoList=new ArrayList<RetailerDetailsPojo>();

      //







        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        instance = this;


        // code by vinod on 5/9/2019  for adding one retailer detail by default

        parentLinearLayout = findViewById(R.id.parent_linear_layout);

        //  retailerLayout = findViewById(R.id.retailerDetails);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.retailer_details_multiple_layout, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 2);

        // firmName=rowView.findViewById(R.id.firmName);


        viewList.add(rowView);

        setAdapterOnFirmName(rowView); //listener for auto suggestion and auto population of retailer details by vinod on 05/09/2019


//Getting ids for components by vinod on 05/09/2019
        dateOfActivityEditText = findViewById(R.id.dateOfActivity);
        dateOfActivityEditText.setInputType(InputType.TYPE_NULL);
        farmerNameEditText = findViewById(R.id.farmerName);
        farmerMobileEditText = findViewById(R.id.farmerMobile);
        farmerLandEditText = findViewById(R.id.farmerLand);
        villageNameEditText = findViewById(R.id.village);
        uploadButton = findViewById(R.id.upload);
        saveButton = findViewById(R.id.saveBtn);
        selectedFilesCountTextview = findViewById(R.id.filescount);
        demoL3SerialIdTextView = findViewById(R.id.demoL3Id);
        addButton = findViewById(R.id.addButton);
        updateDemoL3TextView = findViewById(R.id.updateDemoL3);






        updateDemoL3TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DemoL3Activity.this, DemoL3_InProgressActivity.class);
                startActivity(intent);
                finish();
            }
        });














//code to open calender by vinod on 05/09/2019
        dateOfActivityEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar clndr = Calendar.getInstance();
                int day = clndr.get(Calendar.DAY_OF_MONTH);
                int month = clndr.get(Calendar.MONTH);
                int year = clndr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(DemoL3Activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateOfActivityEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.getDatePicker().setMaxDate(new Date().getTime());


                picker.show();
            }
        });







        Intent i = getIntent();


        if(i!=null && (i.getStringExtra("DEMO_L3_SERIAL_ID")==null || i.getStringExtra("DEMO_L3_SERIAL_ID").equals(""))) {


          //  Toast.makeText(instance, "intent is not null and id is "+i.getStringExtra("DEMO_L3_SERIAL_ID"), Toast.LENGTH_SHORT).show();

            // getting data from demoL3 by vinod on 16/09/2019
            Integer id = null;
            try {

                Cursor z = demoL3ActivityPresenterInterface.getAllEntriesFromDemoL3();

                if (z != null && z.getCount() == 0) {
                    id = 3001;

                } else {
                    if (z != null && z.moveToFirst()) {
                        do {
                            id = z.getInt(z.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID));

                            id++;
                        } while (z.moveToNext());
                    }
                }

                demoL3SerialIdTextView.setText(id.toString());

                //Toast.makeText(DemoL3Activity.instance, "Entries number " + z.getCount(), Toast.LENGTH_SHORT).show();

                z.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


            //getting data from demoL3 ends


        }


        else
        {
            demoL3SerialId = Integer.valueOf(i.getStringExtra("DEMO_L3_SERIAL_ID"));


          //  Toast.makeText(instance, "Retrieving id is "+demoL3SerialId, Toast.LENGTH_SHORT).show();

            try {
                stage = demoL3ActivityPresenterInterface.getStage(demoL3SerialId);
            //    Toast.makeText(DemoL3Activity.this, "STAGE " + stage, Toast.LENGTH_SHORT).show();


                if(stage != null && !stage.equals(""))
                {

                //    Toast.makeText(instance, "STAGE IS not null "+stage, Toast.LENGTH_SHORT).show();

                    Cursor cursor = demoL3ActivityPresenterInterface.getDemoL3DataFromID(demoL3SerialId);

                    if (cursor != null && cursor.moveToFirst()) {
                        do {
                            try {
                                String dateOfActivity_retrieved = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_ACTIVITY));
                                String village_retrieved = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_VILLAGE));
                                String demoL3SerialId = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID));


                        //        Toast.makeText(instance, "date of activity retrieve "+dateOfActivity_retrieved, Toast.LENGTH_SHORT).show();

                                dateOfActivityEditText.setText(dateOfActivity_retrieved);
                                dateOfActivityEditText.setFocusable(false);
                                dateOfActivityEditText.setEnabled(false);

                                villageNameEditText.setText(village_retrieved);
                                villageNameEditText.setFocusable(false);
                                villageNameEditText.setEnabled(false);

                                demoL3SerialIdTextView.setText(demoL3SerialId);


                              FarmerDetailsPojo farmerDetailsPojo=  demoL3ActivityPresenterInterface.getFarmerDetailsForDemoL3(demoL3SerialId);
                                String farmerName_retrieved = farmerDetailsPojo.getFarmerName();
                                String farmerMobile_retrieved = farmerDetailsPojo.getFarmerMobile();
                                String farmerLand_retreieved= farmerDetailsPojo.getFarmerLand();


                                farmerNameEditText.setText(farmerName_retrieved);
                                farmerNameEditText.setFocusable(false);
                                farmerNameEditText.setEnabled(false);

                                farmerMobileEditText.setText(farmerMobile_retrieved);
                                farmerMobileEditText.setFocusable(false);
                                farmerMobileEditText.setEnabled(false);


                                farmerLandEditText.setText(farmerLand_retreieved);
                                farmerLandEditText.setFocusable(false);
                                farmerLandEditText.setEnabled(false);


                               saveButton.setEnabled(false);
                               addButton.setEnabled(false);
                               uploadButton.setEnabled(false);








                              /*  List<RetailerDetailsPojo> retailerDetailsPojoList = demoL3ActivityPresenterInterface.getRetailerDetailsListForDemoL3(demoL3SerialId);
                                viewList = new ArrayList<View>();
                                 for (int k=0; k<retailerDetailsPojoList.size();k++)
                                 {



                                     addRetailerRow(null);


                                 }


                                 for (int h=0; h<viewList.size();h++)
                                 {
                                     EditText propName = viewList.get(h).findViewById(R.id.propName);
                                     EditText firmName = viewList.get(h).findViewById(R.id.firmName);
                                     EditText retailerMobile = viewList.get(h).findViewById(R.id.retailerMobile);




                                     RetailerDetailsPojo retailerDetailsPojo = retailerDetailsPojoList.get(h);

                                     propName.setText(retailerDetailsPojo.getPropName());
                                     firmName.setText(retailerDetailsPojo.getFirmName());
                                     firmName.setEnabled(false);
                                     retailerMobile.setText(retailerDetailsPojo.getRetailerMobile());



                                 } */




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




  /*      if(i!=null && i.getStringExtra("DEMO_L3_SERIAL_ID")!=null && !i.getStringExtra("DEMO_L3_SERIAL_ID").equals("") ) {
            demoL3SerialId = Integer.valueOf(i.getStringExtra("DEMO_L3_SERIAL_ID"));
            try {
                stage = demoL3ActivityPresenterInterface.getStage(demoL3SerialId);

                Toast.makeText(DemoL3Activity.this, "STAGE " + stage, Toast.LENGTH_SHORT).show();

                if (stage== null || stage.equals("")) {
                    dateOfActivityEditText.setEnabled(true);
                    farmerNameEditText.setEnabled(true);
                    farmerMobileEditText.setEnabled(true);
                    farmerLandEditText.setEnabled(true);
                    villageNameEditText.setEnabled(true);
                    uploadButton.setEnabled(true);
                    saveButton.setEnabled(true);
                    addButton.setEnabled(true);





                } else {

                    Toast.makeText(instance, "", Toast.LENGTH_SHORT).show();

                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
*/























        //SETTING LIST IN VILLAGE Auto Suggestion by vinod on 05/09/2019
        demoL3ActivityPresenterInterface = new DemoL3ActivityPresenter();

        List<String> villageList = new ArrayList<String>();

        // villageList=dbHelper.getVillageList();

        try {
            villageList = demoL3ActivityPresenterInterface.getVillageList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, villageList);
        villageNameEditText.setAdapter(adapter);
        villageNameEditText.setThreshold(1);

//  SETTING LIST IN VILLAGE DROP DOWN ENDS

        //Adding listener to upload image button by vinod on 05/09/2019

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivityForResult(intent,0);

                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

                startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_MULTIPLE);
            }
        });


        //code for multiple stepview by vinod on 11/9/2019
        setpview5 = (HorizontalStepView) findViewById(R.id.step_view);

        String s1 = "Farmer"
                + "\n"
                + "Details";

        String s2 = "Protocol";
        String s3 = "Execution";
        String s4 = "Interim"
                + "\n"
                + "Result";

        String s5 = "Yield"
                + "\n"
                + "Result";

        StepBean stepBean0 = new StepBean(s1, 0);

        StepBean stepBean1 = new StepBean(s2, -1);

        StepBean stepBean2 = new StepBean(s3, -1);

        StepBean stepBean3 = new StepBean(s4, -1);

        StepBean stepBean4 = new StepBean(s5, -1);


        if (stage!=null && stage.equals(CommonConstants.PROTOCOL.toString())) {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2, 0);
            stepBean2 = new StepBean(s3, -1);
            stepBean3 = new StepBean(s4, -1);
            stepBean4 = new StepBean(s5, -1);

        } else if (stage!=null && stage.equals(CommonConstants.EXECUTION.toString())) {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2, 1);
            stepBean2 = new StepBean(s3, -1);
            stepBean3 = new StepBean(s4, -1);
            stepBean4 = new StepBean(s5, -1);
        } else if (stage!=null && stage.equals(CommonConstants.RESULT_INTERIM.toString())) {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2, 1);
            stepBean2 = new StepBean(s3, 1);
            stepBean3 = new StepBean(s4, -1);
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


                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(DemoL3Activity.this, android.R.color.darker_gray))//??StepsViewIndicator??????

                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(DemoL3Activity.this, android.R.color.darker_gray))//??StepsViewIndicator???????

                .setStepViewComplectedTextColor(ContextCompat.getColor(DemoL3Activity.this, android.R.color.black))//??StepsView text??????

                .setStepViewUnComplectedTextColor(ContextCompat.getColor(DemoL3Activity.this, android.R.color.black))//??StepsView text???????

                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(DemoL3Activity.this, R.drawable.tick))//??StepsViewIndicator CompleteIcon

                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(DemoL3Activity.this, R.drawable.default_icon))//??StepsViewIndicator DefaultIcon

                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(DemoL3Activity.this, R.drawable.inprogress));


//??StepsViewIndicator AttentionIcon


        //code for multiple stepview ends


//Adding listener to save button by vinod on 05/09/2019

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateOfActivity_entered = dateOfActivityEditText.getText().toString();
                String farmerName_entered = farmerNameEditText.getText().toString();
                String farmerMobile_entered = farmerMobileEditText.getText().toString();
                String farmerLandAcres_entered = farmerLandEditText.getText().toString();
                String village_entered = villageNameEditText.getText().toString();
                String uploadFlagStatus= "No";
                 demoL3SerialId_populated = demoL3SerialIdTextView.getText().toString();


                if (dateOfActivity_entered != null && !dateOfActivity_entered.equals("")
                        && farmerName_entered != null && !farmerName_entered.equals("")
                        && farmerMobile_entered != null && !farmerMobile_entered.equals("") && farmerMobile_entered.trim().length()==10
                        && farmerLandAcres_entered != null && !farmerLandAcres_entered.equals("")
                        && village_entered != null && !village_entered.equals("")
                        && demoL3SerialId_populated != null && !demoL3SerialId_populated.equals("")
                ) {

                    Toast.makeText(DemoL3Activity.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < viewList.size(); i++) {

                        EditText firmName = (viewList.get(i)).findViewById(R.id.firmName);
                        EditText retailerMobile = (viewList.get(i)).findViewById(R.id.retailerMobile);
                        EditText proprietorName = (viewList.get(i)).findViewById(R.id.propName);
                        // String as=zx.getText().toString();

                        RetailerDetailsPojo retailerDetailsPojo = new RetailerDetailsPojo();
                        retailerDetailsPojo.setFirmName(firmName.getText().toString());
                        retailerDetailsPojo.setRetailerMobile(retailerMobile.getText().toString());
                        retailerDetailsPojo.setPropName(proprietorName.getText().toString());
                        // Toast.makeText(IndividualFarmerContactActivity.this, "FirmName:  " + as, Toast.LENGTH_SHORT).show();


                        retailerDetailsPojoList.add(retailerDetailsPojo);

                    }

                    try {
                        clientName = demoL3ActivityPresenterInterface.getClientName();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    String faOfficeDistrict = "";

                    try {
                        faOfficeDistrict = demoL3ActivityPresenterInterface.getFaOfficeDistrict();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Calendar cal = Calendar.getInstance();
                    Date createdOn = cal.getTime();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String createdOn_string = dateFormat.format(createdOn);
                    String modifyDate_string = createdOn_string;
                    String createdby = "";
                    Integer stage = CommonConstants.PROTOCOL;

                    try {
                        createdby = demoL3ActivityPresenterInterface.getMobileFromSharedpreference();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    try {
                        demoL3ActivityPresenterInterface.insertDemoL3FarmerDetailsdata(demoL3SerialId_populated,
                                dateOfActivity_entered,
                                farmerName_entered, faOfficeDistrict, farmerMobile_entered,
                                farmerLandAcres_entered, village_entered,
                                createdOn_string, createdby, clientName, uploadFlagStatus,
                                retailerDetailsPojoList, attachmentList, modifyDate_string,
                                stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }





              /*          dateOfActivityEditText.setBackgroundColor(android.R.color.darker_gray);
                        // dateOfActivityEditText.setHintTextColor(android.R.color.darker_gray);
                        dateOfActivityEditText.setTextColor(R.color.blackcolor);
                        dateOfActivityEditText.setEnabled(false);

                        farmerNameEditText.setBackgroundColor(android.R.color.darker_gray);
                        //  farmerNameEditText.setHintTextColor(android.R.color.darker_gray);
                        farmerNameEditText.setTextColor(R.color.blackcolor);
                        farmerNameEditText.setEnabled(false);

                        farmerMobileEditText.setBackgroundColor(android.R.color.darker_gray);
                        // farmerMobileEditText.setHintTextColor(android.R.color.darker_gray);
                        farmerMobileEditText.setTextColor(R.color.blackcolor);
                        farmerMobileEditText.setEnabled(false);

                        farmerLandEditText.setBackgroundColor(android.R.color.darker_gray);
                        // farmerLandEditText.setHintTextColor(android.R.color.darker_gray);
                        farmerLandEditText.setTextColor(R.color.blackcolor);
                        farmerLandEditText.setEnabled(false);

                        villageNameEditText.setBackgroundColor(android.R.color.darker_gray);
                        villageNameEditText.setHintTextColor(android.R.color.darker_gray);
                        villageNameEditText.setTextColor(R.color.blackcolor);
                        villageNameEditText.setEnabled(false);

                        addButton.setEnabled(false);
                        addButton.setVisibility(View.GONE);

                        saveButton.setEnabled(false);
                        //  saveButton.setBackgroundColor(android.R.color.darker_gray);


                        for (int m = 0; m < viewList.size(); m++) {
                            EditText firmName = viewList.get(m).findViewById(R.id.firmName);
                            firmName.setBackgroundColor(android.R.color.darker_gray);
                            firmName.setHintTextColor(android.R.color.darker_gray);
                            firmName.setTextColor(R.color.blackcolor);
                            firmName.setEnabled(false);

                            EditText propName = viewList.get(m).findViewById(R.id.propName);
                            propName.setBackgroundColor(android.R.color.darker_gray);
                            //propName.setHintTextColor(android.R.color.darker_gray);
                            propName.setTextColor(R.color.blackcolor);
                            propName.setEnabled(false);

                            EditText retailerMob = viewList.get(m).findViewById(R.id.retailerMobile);
                            retailerMob.setBackgroundColor(android.R.color.darker_gray);
                            //  retailerMob.setHintTextColor(android.R.color.darker_gray);
                            retailerMob.setTextColor(R.color.blackcolor);
                            retailerMob.setEnabled(false);


                            Button deleteBtn = viewList.get(m).findViewById(R.id.deleteButton);
                            deleteBtn.setEnabled(false);
                            deleteBtn.setVisibility(View.GONE);  */




                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                        try {

                         //  MandiCampaignDataPushNetworkOperationInterface mandiCampaignDataPushNetworkOperationInterface = new MandiCampaignDataPushNetworkOperation();
                          // mandiCampaignDataPushNetworkOperationInterface.sendingMcDataToWebService();

                            DemoL3DataPushNetworkOperationInterface demoL3DataPushNetworkOperationInterface = new DemoL3DataPushNetworkOperation();
                            demoL3DataPushNetworkOperationInterface.sendingDemoL3DataToWebService();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //sendingDataToWebService(); to be removed
                    }


                    Intent intent = new Intent(DemoL3Activity.this, DemoL3Activity_Protocol.class);
                    //pgsBar.setVisibility(View.GONE);

                    intent.putExtra("DEMO_L3_SERIAL_ID", demoL3SerialId_populated);
                    startActivity(intent);
                    finish();





                } else {
                    if (dateOfActivity_entered == null || dateOfActivity_entered.equals(""))
                        dateOfActivityEditText.setError("Please select date of activity");
                    if (farmerName_entered == null || farmerName_entered.equals(""))
                        farmerNameEditText.setError("Please enter farmer name");
                    if (farmerLandAcres_entered == null || farmerLandAcres_entered.equals(""))
                        farmerLandEditText.setError("Please enter farmer land");
                    if (village_entered == null || village_entered.equals(""))
                        villageNameEditText.setError("Please enter village");
                    if (farmerMobile_entered != null || farmerMobile_entered.equals(""))
                        farmerMobileEditText.setError("Please enter farmer mobile");
                     if (farmerMobile_entered != null && !farmerMobile_entered.equals("")   && farmerMobile_entered.trim().length()<10)
                        farmerMobileEditText.setError("Please enter 10 digits in farmer mobile");

                }


            }
        });


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
        getMenuInflater().inflate(R.menu.demo_l3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     /*   if (id == R.id.action_settings) {
            return true;
        }   */

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.ifc) {
            // IFC Activity clicked by user


            Intent intent = new Intent(DemoL3Activity.this, IndividualFarmerContactMainActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();


        } else if (id == R.id.fm) {
            Intent intent = new Intent(DemoL3Activity.this, FarmerMeetingActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.fd) {

            Intent intent = new Intent(DemoL3Activity.this, FieldDayActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.dv) {
            Intent intent = new Intent(DemoL3Activity.this, DiagnosticVisitActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        }

        else if(id==R.id.mc){
            Intent intent = new Intent(DemoL3Activity.this, MandiCampaignActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.demol3) {

            Intent intent = new Intent(DemoL3Activity.this, DemoL3Activity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }


        else if (id == R.id.demol3_progress) {

            Intent intent = new Intent(DemoL3Activity.this, DemoL3_InProgressActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }




        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addRetailerRow(View v) {



        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.retailer_details_multiple_layout, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 2);
        //firmName = ((View) rowView.getParent().getParent()).findViewById(R.id.firmName);


        viewList.add(rowView);

        //setAdapterOnFirmName(rowView);
        //  Toast.makeText(IndividualFarmerContactActivity.getInstance(), "FIRM NAME "+rowView.findViewById(R.id.farmerName), Toast.LENGTH_SHORT).show();


        //    Toast.makeText(this, "FIRM NAME " + firmName.getText(), Toast.LENGTH_SHORT).show();
        //  Toast.makeText(this, "Proprietor NAME "+proprietorName.getText(), Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, "Retailer Name "+retailerMobile.getText(), Toast.LENGTH_SHORT).show();
    }


    public void deleteRetailerRow(View v){

        //  Toast.makeText(this, "aaaa ", Toast.LENGTH_SHORT).show();
//https://stackoverflow.com/questions/3995215/add-and-remove-views-in-android-dynamically


        View namebar = ((View) v.getParent().getParent()).findViewById(R.id.retailerDetails);


       // View namebar = findViewById(R.id.retailerDetails);
        ViewGroup parent = (ViewGroup) namebar.getParent();
        if (parent != null) {
            parent.removeView(namebar);
        }

        viewList.remove(namebar);

    }

    public void setAdapterOnFirmName(final View v) {
        List<String> firmNameList = new ArrayList<String>();

        // firmNameList = dbHelper.getFirmNameList(); //to be removed

        demoL3ActivityPresenterInterface = new DemoL3ActivityPresenter();

        try {
            firmNameList = demoL3ActivityPresenterInterface.getFirmNameList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, firmNameList);
        firmName = v.findViewById(R.id.firmName);
        // firmName.setInputType(InputType.TYPE_NULL);

        firmName.setAdapter(adapter);

        firmName.setThreshold(1);
        final View view=v;
        firmName.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
            @Override
            public void onDismiss() {
                // Toast.makeText(IndividualFarmerContactActivity.this, "DISMISS", Toast.LENGTH_SHORT).show();

                // String propName= dbHelper.getPropName(firmName.getText().toString()); to be removed

                //  String retailerMobile=dbHelper.getRetailerMobile(firmName.getText().toString()); to be removed


                String propName= null;
                try {
                    propName = demoL3ActivityPresenterInterface.getPropName(firmName.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String retailerMobile= null;
                try {
                    retailerMobile = demoL3ActivityPresenterInterface.getRetailerMobile(firmName.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }


                if(!propName.equals("") && !retailerMobile.equals("")) {
                    EditText propNameEditText = view.findViewById(R.id.propName);
                    propNameEditText.setText(propName);
                    EditText retailerMobileEditText=view.findViewById(R.id.retailerMobile);
                    retailerMobileEditText.setText(retailerMobile);
                }

                else
                {
                    firmName.setError("Please enter firm name from suggested values only");
                }

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        attachmentList =new ArrayList<byte []>();
        if(data!=null && data.getData()==null) {

        //    Toast.makeText(this, "Request Code " + requestCode, Toast.LENGTH_SHORT).show();
          //  Toast.makeText(this, "Result Code " + resultCode, Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {


         //       Toast.makeText(this, "Data " + data.getClipData(), Toast.LENGTH_SHORT).show();
           //     Toast.makeText(this, "Files count " + data.getClipData().getItemCount(), Toast.LENGTH_SHORT).show();

                ClipData clipData = data.getClipData();


                if(data!=null && data.getClipData()!=null && data.getClipData().getItemCount()==0)
                    selectedFilesCountTextview.setText("");

                if(clipData!=null & clipData.getItemCount()<=5 && clipData.getItemCount()!=0)
                {

                    selectedFilesCountTextview.setText(" "+clipData.getItemCount()+" files selected");

                    for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                        Uri uri = clipData.getItemAt(i).getUri();


                        InputStream iStream = null;
                        try {
                            iStream = getContentResolver().openInputStream(uri);

                            File f = new File(uri.getPath());
                            long size = f.length();
                   //         Toast.makeText(this, "FILE SIZE "+size, Toast.LENGTH_SHORT).show();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            byte[] inputData = getBytes(iStream);


                            //  Toast.makeText(this, "count : "+i+" byte array "+inputData , Toast.LENGTH_SHORT).show();

                            attachmentList.add(inputData);
                            // dbHelper.insertDataSetMaster(inputData);

                        //    Toast.makeText(this, "BYTE ARRAY " + inputData, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }


                }
                else
                    Toast.makeText(this, "Maximum 5 files can be uploaded.", Toast.LENGTH_SHORT).show();



            }

        }
        else if(data!=null)
        {




            Uri singleFileUri = data.getData();


            InputStream iStream = null;
            try {
                iStream = getContentResolver().openInputStream(singleFileUri);
                //   File f = new File(singleFileUri.getPath());
                // long size = f.length();
                //Toast.makeText(this, "FILE SIZE "+size, Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                byte[] inputData = getBytes(iStream);
                attachmentList.add(inputData);
                // dbHelper.insertDataSetMaster(inputData);

                selectedFilesCountTextview.setText(" 1 file selected");

             //   Toast.makeText(this, "SINGLE BYTE ARRAY " + inputData, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }





    }


    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }


    public void next(View v)
    {

        Intent intent = new Intent(DemoL3Activity.this, DemoL3Activity_Protocol.class);


        if(demoL3SerialId!=null)
        intent.putExtra("DEMO_L3_SERIAL_ID", demoL3SerialId.toString());
        //pgsBar.setVisibility(View.GONE);
        startActivity(intent);
        finish();
    }

    public static DemoL3Activity getInstance() {
        return instance;
    }



}
