package java.com.kaizenmax.taikinys_icl.view;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.kaizenmax.taikinys_icl.presenter.DemoL3ActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ExecutionPresenter;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ExecutionPresenterInterface;
import com.kaizenmax.taikinys_icl.util.CommonConstants;
import com.kaizenmax.taikinys_icl.view.DemoL3Activity;
import com.kaizenmax.taikinys_icl.view.DemoL3Activity_Protocol;
import com.kaizenmax.taikinys_icl.view.DemoL3_InProgressActivity;
import com.kaizenmax.taikinys_icl.view.DemoL3_InterimResult;
import com.kaizenmax.taikinys_icl.view.DiagnosticVisitActivity;
import com.kaizenmax.taikinys_icl.view.FarmerMeetingActivity;
import com.kaizenmax.taikinys_icl.view.FieldDayActivity;
import com.kaizenmax.taikinys_icl.view.IndividualFarmerContactMainActivity;
import com.kaizenmax.taikinys_icl.view.MandiCampaignActivity;


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

public class DemoL3Activity_Execution extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



EditText dateOfExecutionEditText;
EditText doseEditText;
EditText demoAreaEditText;
Button uploadButton;
TextView selectedFilesCountTextView;
Button saveButton;
    String stage;
    DatePickerDialog picker;

    HorizontalStepView setpview5;
    List<StepBean> stepsBeanList = new ArrayList<>();

    int PICK_IMAGE_MULTIPLE = 1;
    List<byte []> attachmentList =new ArrayList<byte []>();

    Integer demoL3SerialId = null;

    DemoL3ActivityPresenterInterface demoL3ActivityPresenterInterface ;

    private static DemoL3Activity_Execution instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_l3__execution);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* FloatingActionButton fab = findViewById(R.id.fab);
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


        instance = this;

        demoL3ActivityPresenterInterface = new DemoL3ActivityPresenter();

        //getting ids by vinod on 13/09/2019

        dateOfExecutionEditText = findViewById(R.id.dateOfExecution);
        dateOfExecutionEditText.setInputType(InputType.TYPE_NULL);
        doseEditText = findViewById(R.id.dose);
        demoAreaEditText = findViewById(R.id.demoAreaAcres);
        uploadButton = findViewById(R.id.upload);
        selectedFilesCountTextView = findViewById(R.id.filescount);
        saveButton = findViewById(R.id.saveBtn);


        //getting ids ends


        Intent i = getIntent();

     //   Toast.makeText(instance, "id Hai : "+i.getStringExtra("DEMO_L3_SERIAL_ID"), Toast.LENGTH_SHORT).show();

        if(i!=null && (i.getStringExtra("DEMO_L3_SERIAL_ID")==null || i.getStringExtra("DEMO_L3_SERIAL_ID").equals(""))) {


            dateOfExecutionEditText.setEnabled(false);
            dateOfExecutionEditText.setFocusable(false);
            doseEditText.setEnabled(false);
            doseEditText.setFocusable(false);
            demoAreaEditText.setEnabled(false);
            demoAreaEditText.setFocusable(false);
            uploadButton.setEnabled(false);
            saveButton.setEnabled(false);
        }


        else
        {
            demoL3SerialId = Integer.valueOf(i.getStringExtra("DEMO_L3_SERIAL_ID"));


       //     Toast.makeText(instance, "Retrieving id is "+demoL3SerialId, Toast.LENGTH_SHORT).show();

            try {
                stage = demoL3ActivityPresenterInterface.getStage(demoL3SerialId);
            //    Toast.makeText(DemoL3Activity_Execution.this, "STAGE " + stage, Toast.LENGTH_SHORT).show();


                if(stage != null && (!stage.equals(CommonConstants.EXECUTION.toString())))
                {

               //     Toast.makeText(instance, "STAGE IS not null "+stage, Toast.LENGTH_SHORT).show();

                    Cursor cursor = demoL3ActivityPresenterInterface.getDemoL3DataFromID(demoL3SerialId);

                    if (cursor != null && cursor.moveToFirst()) {
                        do {
                            try {


                                String dateOfExecution_retrieved = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_EXECUTION));
                                String dose_retrieved = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DOSE));

                                String demoArea_retrieved = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_ACRES));


                         //       Toast.makeText(instance, "date of protocol execution  "+dateOfExecution_retrieved, Toast.LENGTH_SHORT).show();




                                dateOfExecutionEditText.setText(dateOfExecution_retrieved);
                                dateOfExecutionEditText.setFocusable(false);
                                dateOfExecutionEditText.setEnabled(false);

                                doseEditText.setText(dose_retrieved);
                                doseEditText.setFocusable(false);
                                doseEditText.setEnabled(false);

                                demoAreaEditText.setText(demoArea_retrieved);
                                demoAreaEditText.setEnabled(false);
                                demoAreaEditText.setFocusable(false);

                                uploadButton.setEnabled(false);
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




















        //method for pop-up of calender by vinod  on  14/09/2019
        dateOfExecutionEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar clndr = Calendar.getInstance();
                int day = clndr.get(Calendar.DAY_OF_MONTH);
                int month = clndr.get(Calendar.MONTH);
                int year = clndr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(DemoL3Activity_Execution.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateOfExecutionEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.getDatePicker().setMaxDate(new Date().getTime());



                picker.show();
                dateOfExecutionEditText.setEnabled(false);
            }
        });
        //method for pop-up of calender by vinod  on  14/09/2019


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





saveButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String dateOfExecution_entered = dateOfExecutionEditText.getText().toString();
        String dose_entered = doseEditText.getText().toString();
        String demoArea_entered = demoAreaEditText.getText().toString();
        Calendar cal=Calendar.getInstance();
        Date modifyDate=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String modifyDate_string = dateFormat.format(modifyDate);
        Integer stage = CommonConstants.RESULT_INTERIM;
        String uploadFlagStatus = "No";

        if (dateOfExecution_entered != null && !dateOfExecution_entered.equals("")
                && dose_entered !=null && !dose_entered.equals("")
                && demoArea_entered != null && !demoArea_entered.equals("")
                && demoL3SerialId != null && !demoL3SerialId.toString().equals("") ) {

            Toast.makeText(DemoL3Activity_Execution.this, "Saved successfully", Toast.LENGTH_SHORT).show();

            DemoL3ExecutionPresenterInterface demoL3ExecutionPresenterInterface = new DemoL3ExecutionPresenter();


            try {
                demoL3ExecutionPresenterInterface.insertDemoL3Executiondata_Update(dateOfExecution_entered,
                        dose_entered, demoArea_entered,  modifyDate_string, stage,
                        demoL3SerialId,  uploadFlagStatus, attachmentList);


           /*     dateOfExecutionEditText.setBackgroundColor(android.R.color.darker_gray);
                // dateOfActivityEditText.setHintTextColor(android.R.color.darker_gray);
                dateOfExecutionEditText.setTextColor(R.color.blackcolor);
                dateOfExecutionEditText.setEnabled(false);

                doseEditText.setBackgroundColor(android.R.color.darker_gray);
                // dateOfActivityEditText.setHintTextColor(android.R.color.darker_gray);
                doseEditText.setTextColor(R.color.blackcolor);
                doseEditText.setEnabled(false);


                demoAreaEditText.setBackgroundColor(android.R.color.darker_gray);
                // dateOfActivityEditText.setHintTextColor(android.R.color.darker_gray);
                demoAreaEditText.setTextColor(R.color.blackcolor);
                demoAreaEditText.setEnabled(false);



                uploadButton.setEnabled(false);
                saveButton.setEnabled(false); */

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


            Intent intent = new Intent(DemoL3Activity_Execution.this, com.kaizenmax.taikinys_icl.view.DemoL3_InterimResult.class);
            //pgsBar.setVisibility(View.GONE);

            intent.putExtra("DEMO_L3_SERIAL_ID", demoL3SerialId.toString());
            startActivity(intent);
            finish();






        }

        else
        {

            if (dateOfExecution_entered == null || dateOfExecution_entered.equals(""))
                dateOfExecutionEditText.setError("Please select date of execution");

            if (dose_entered == null || dose_entered.equals(""))
                doseEditText.setError("Please enter dose(Kg/Acres)");

            if (demoArea_entered == null || demoArea_entered.equals(""))
                demoAreaEditText.setError("Please enter demo area");




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

        StepBean stepBean2 = new StepBean(s3,0);

        StepBean stepBean3 = new StepBean(s4,-1);

        StepBean stepBean4 = new StepBean(s5,-1);


        if (stage!=null && stage.equals(CommonConstants.PROTOCOL.toString())) {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2, -1);
            stepBean2 = new StepBean(s3, 0);
            stepBean3 = new StepBean(s4, -1);
            stepBean4 = new StepBean(s5, -1);

        } else if (stage!=null && stage.equals(CommonConstants.EXECUTION.toString())) {
            stepBean0 = new StepBean(s1, 1);
            stepBean1 = new StepBean(s2, 1);
            stepBean2 = new StepBean(s3, 0);
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


                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(DemoL3Activity_Execution.this, R.color.green))//??StepsViewIndicator??????

                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(DemoL3Activity_Execution.this, android.R.color.darker_gray))//??StepsViewIndicator???????

                .setStepViewComplectedTextColor(ContextCompat.getColor(DemoL3Activity_Execution.this, android.R.color.black))//??StepsView text??????

                .setStepViewUnComplectedTextColor(ContextCompat.getColor(DemoL3Activity_Execution.this, android.R.color.black))//??StepsView text???????

                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(DemoL3Activity_Execution.this, R.drawable.tick))//??StepsViewIndicator CompleteIcon

                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(DemoL3Activity_Execution.this, R.drawable.default_icon))//??StepsViewIndicator DefaultIcon

                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(DemoL3Activity_Execution.this, R.drawable.inprogress))

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
        getMenuInflater().inflate(R.menu.demo_l3_activity__execution, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
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


            Intent intent = new Intent(DemoL3Activity_Execution.this, IndividualFarmerContactMainActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();


        } else if (id == R.id.fm) {
            Intent intent = new Intent(DemoL3Activity_Execution.this, FarmerMeetingActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.fd) {

            Intent intent = new Intent(DemoL3Activity_Execution.this, FieldDayActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.dv) {
            Intent intent = new Intent(DemoL3Activity_Execution.this, DiagnosticVisitActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        }

        else if(id==R.id.mc){
            Intent intent = new Intent(DemoL3Activity_Execution.this, MandiCampaignActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.demol3) {

            Intent intent = new Intent(DemoL3Activity_Execution.this, DemoL3Activity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.demol3_progress) {

            Intent intent = new Intent(DemoL3Activity_Execution.this, DemoL3_InProgressActivity.class);
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

        Intent intent = new Intent(DemoL3Activity_Execution.this, DemoL3_InterimResult.class);
        //pgsBar.setVisibility(View.GONE);
        if(demoL3SerialId!=null)
            intent.putExtra("DEMO_L3_SERIAL_ID", demoL3SerialId.toString());
        startActivity(intent);
        finish();
    }


    public void previous(View v)
    {

        Intent intent = new Intent(DemoL3Activity_Execution.this, DemoL3Activity_Protocol.class);
        //pgsBar.setVisibility(View.GONE);
        if(demoL3SerialId!=null)
        intent.putExtra("DEMO_L3_SERIAL_ID", demoL3SerialId.toString());
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        attachmentList =new ArrayList<byte []>();
        if(data!=null && data.getData()==null) {

           // Toast.makeText(this, "Request Code " + requestCode, Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "Result Code " + resultCode, Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {


            //    Toast.makeText(this, "Data " + data.getClipData(), Toast.LENGTH_SHORT).show();
              //  Toast.makeText(this, "Files count " + data.getClipData().getItemCount(), Toast.LENGTH_SHORT).show();

                ClipData clipData = data.getClipData();


                if(data!=null && data.getClipData()!=null && data.getClipData().getItemCount()==0)
                    selectedFilesCountTextView.setText("");

                if(clipData!=null & clipData.getItemCount()<=5 && clipData.getItemCount()!=0)
                {

                    selectedFilesCountTextView.setText(" "+clipData.getItemCount()+" files selected");

                    for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                        Uri uri = clipData.getItemAt(i).getUri();


                        InputStream iStream = null;
                        try {
                            iStream = getContentResolver().openInputStream(uri);

                            File f = new File(uri.getPath());
                            long size = f.length();
                        //    Toast.makeText(this, "FILE SIZE "+size, Toast.LENGTH_SHORT).show();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            byte[] inputData = getBytes(iStream);


                            //  Toast.makeText(this, "count : "+i+" byte array "+inputData , Toast.LENGTH_SHORT).show();

                            attachmentList.add(inputData);
                            // dbHelper.insertDataSetMaster(inputData);

                          //  Toast.makeText(this, "BYTE ARRAY " + inputData, Toast.LENGTH_SHORT).show();
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

                selectedFilesCountTextView.setText(" 1 file selected");

              //  Toast.makeText(this, "SINGLE BYTE ARRAY " + inputData, Toast.LENGTH_SHORT).show();
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

    public static DemoL3Activity_Execution getInstance() {
        return instance;
    }

}
