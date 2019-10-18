package com.kaizenmax.taikinys_icl.view;

import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.kaizenmax.taikinys_icl.R;
import com.kaizenmax.taikinys_icl.model.MandiCampaignDataPushNetworkOperation;
import com.kaizenmax.taikinys_icl.model.MandiCampaignDataPushNetworkOperationInterface;
import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;
import com.kaizenmax.taikinys_icl.presenter.MandiCampaignActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.MandiCampaignActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.util.ActivityNames;
import com.kaizenmax.taikinys_icl.util.MultiSelectionSpinner;

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

public class MandiCampaignActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText dateOfActivityEditText;
    EditText mandiNameEditText;
    EditText expensesEditText;
    Spinner cropCategorySpinner;
    Spinner cropFocusSpinner;
    MultiSelectionSpinner productFocusMultiSpinner;
    Spinner campaignPurposeSpinner;
    EditText activitySummaryEditText;
    Button uploadButton;
    Button saveButton;
    AutoCompleteTextView firmName;


    DatePickerDialog picker;
    private static MandiCampaignActivity instance;
    private android.support.v7.widget.LinearLayoutCompat parentLinearLayout;
    List<View> viewList=new ArrayList<View>();
    List<RetailerDetailsPojo> retailerDetailsPojoList=new ArrayList<RetailerDetailsPojo>();
    String clientName;
    String faOfficeState;


    List<String> cropCategories=new ArrayList<String>();
    List<String> cropFocusList=new ArrayList<String>();

    String selectedCropCategory;
    String selectedCropFocus;
    String selectedMeetingPurpose;


    MandiCampaignActivityPresenterInterface mandiCampaignActivityPresenterInterface;

    int PICK_IMAGE_MULTIPLE = 1;
    List<byte []> attachmentList =new ArrayList<byte []>();
    private TextView filesCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandi_campaign);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     /*   FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });  */
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        instance = this;
        mandiCampaignActivityPresenterInterface = new MandiCampaignActivityPresenter();
        dateOfActivityEditText = findViewById(R.id.dateOfActivity);
        dateOfActivityEditText.setInputType(InputType.TYPE_NULL);
        mandiNameEditText = findViewById(R.id.mandiName);
        expensesEditText = findViewById(R.id.expenses);
        cropCategorySpinner = findViewById(R.id.cropCategory);
        cropFocusSpinner = findViewById(R.id.cropFocus);
        productFocusMultiSpinner = findViewById(R.id.productFocus);
        campaignPurposeSpinner = findViewById(R.id.campaignPurpose);
        activitySummaryEditText = findViewById(R.id.activitySummary);
        uploadButton = findViewById(R.id.upload);
        saveButton = findViewById(R.id.saveBtn);
        filesCountTextView = findViewById(R.id.filescount);

dateOfActivityEditText.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final Calendar clndr = Calendar.getInstance();
        int day = clndr.get(Calendar.DAY_OF_MONTH);
        int month = clndr.get(Calendar.MONTH);
        int year = clndr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(MandiCampaignActivity.this,
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


        parentLinearLayout = findViewById(R.id.parent_linear_layout);

      //  retailerLayout = findViewById(R.id.retailerDetails);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.retailer_details_multiple_layout, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 2);

        // firmName=rowView.findViewById(R.id.firmName);


        viewList.add(rowView);

        setAdapterOnFirmName(rowView);




        try {
            clientName = mandiCampaignActivityPresenterInterface.getClientName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            faOfficeState=mandiCampaignActivityPresenterInterface.getStateName();
        } catch (Exception e) {
            e.printStackTrace();
        }




        //SETTING LIST IN PRODUCT FOCUS
        List<String> productlist = new ArrayList<String>();
        productlist.add("ASD");

//String clientName = dbHelper.getClientName();
//String faOfficeState=dbHelper.getStateName();


        try {

            productlist=mandiCampaignActivityPresenterInterface.getProductFocusList(clientName,faOfficeState);
            productFocusMultiSpinner.setItems(productlist);


        } catch (Exception e) {
            e.printStackTrace();
        }



//GETTING CROP CATEGORIES

        try {
            cropCategories=mandiCampaignActivityPresenterInterface.getCropCategories();
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

                try {
                    cropFocusList= mandiCampaignActivityPresenterInterface.getCropFocus(selectedCropCategory);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ArrayAdapter<String> cropFocusDataAdapter = new ArrayAdapter<String>(MandiCampaignActivity.this, android.R.layout.simple_spinner_dropdown_item, cropFocusList);


                cropFocusDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                cropFocusSpinner.setAdapter(cropFocusDataAdapter);

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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Adding Listener to CropFocus to get selectedCropFocus Ends



        //Adding method to get meeting purposes by vinod on 13/08/2019

        List<String> meetingPurposeList = new ArrayList<String>();
        // meetingPurposeList.add("Select Meeting Purpose*");
        //meetingPurposeList.add("A");

//String clientName = dbHelper.getClientName();
//String faOfficeState=dbHelper.getStateName();


        try {

            meetingPurposeList=mandiCampaignActivityPresenterInterface.getMeetingPurposeList(ActivityNames.MANDI_CAMPAIGN);

            ArrayAdapter<String> meetingPurposeDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, meetingPurposeList);


            meetingPurposeDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            campaignPurposeSpinner.setAdapter(meetingPurposeDataAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }




        //Adding listener on meetingPurpose by vinod on 16/08/2019

        final List<String> finalMeetingPurposeList = meetingPurposeList;
        campaignPurposeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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



        // Adding method to get meeting purposes ends


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
                String dateOfActivity_entered = dateOfActivityEditText.getText().toString();
                String mandiName_entered = mandiNameEditText.getText().toString();
                String expenses_entered = expensesEditText.getText().toString();
                String activitySummary_entered = activitySummaryEditText.getText().toString();
                List<String> selectedProductsList = productFocusMultiSpinner.getSelectedStrings();
                String uploadFlagStatus = "No";



                if (dateOfActivity_entered != null && !dateOfActivity_entered.equals("")
                        && mandiName_entered !=null && !mandiName_entered.equals("")
                        && selectedCropCategory != null && !selectedCropCategory.equals("") && !selectedCropCategory.equals("Select Crop Category*")
                        && selectedCropFocus != null && !selectedCropFocus.equals("") && !selectedCropFocus.equals("Select Crop Focus*")
                        && selectedProductsList.size() != 0 && !(selectedProductsList.size() == 1 && selectedProductsList.get(0).equals("Select Product Focus*"))
                       && selectedMeetingPurpose !=null && !selectedMeetingPurpose.equals("") && !selectedMeetingPurpose.equals("Select Campaign Purpose*")
                            )

                {

                    Toast.makeText(MandiCampaignActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();


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


                    String faOfficeDistrict="";

                    try {
                        faOfficeDistrict=mandiCampaignActivityPresenterInterface.getFaOfficeDistrict();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Calendar cal=Calendar.getInstance();
                    Date createdOn=cal.getTime();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String createdOn_string = dateFormat.format(createdOn);
                    String createdby="";

                    try {
                        createdby=mandiCampaignActivityPresenterInterface.getMobileFromSharedpreference();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    try {
                        mandiCampaignActivityPresenterInterface.insertMCdata(dateOfActivity_entered,
                                mandiName_entered,  faOfficeDistrict, selectedCropCategory,
                                selectedCropFocus, expenses_entered, selectedMeetingPurpose, activitySummary_entered,
                                 createdOn_string, createdby, clientName, uploadFlagStatus,
                                retailerDetailsPojoList, selectedProductsList, attachmentList);




                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                        try {
                            MandiCampaignDataPushNetworkOperationInterface mandiCampaignDataPushNetworkOperationInterface = new MandiCampaignDataPushNetworkOperation();
                            mandiCampaignDataPushNetworkOperationInterface.sendingMcDataToWebService();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //sendingDataToWebService(); to be removed
                    }




                    Intent intent = new Intent(MandiCampaignActivity.this, MandiCampaignActivity.class);
                    //pgsBar.setVisibility(View.GONE);
                    startActivity(intent);
                    finish();










                }

                else
                {
                  if(dateOfActivity_entered==null || dateOfActivity_entered.equals(""))
                      dateOfActivityEditText.setError("Please select date of activity");
                  if(mandiName_entered==null || mandiName_entered.equals(""))
                      mandiNameEditText.setError("Please enter mandi name");
                  if(selectedCropCategory==null || selectedCropCategory.equals("") || selectedCropCategory.equals("Select Crop Category*"))
                  {
                      TextView errorText = (TextView) cropCategorySpinner.getSelectedView();
                      errorText.setError("Please select crop category");
                  }

                  if(selectedCropFocus==null || selectedCropFocus.equals("") || selectedCropFocus.equals("Select Crop Focus*"))
                  {
                      TextView errorText = (TextView) cropFocusSpinner.getSelectedView();
                      errorText.setError("Please select crop focus");
                  }

                    if (selectedProductsList.size() == 0 || (selectedProductsList.size() == 1 && selectedProductsList.get(0).equals("Select Product Focus*"))) {
                        TextView errorText = (TextView) productFocusMultiSpinner.getSelectedView();
                        errorText.setError("Please select product focus");
                    }


                    if (selectedMeetingPurpose == null || selectedMeetingPurpose.equals("") || selectedMeetingPurpose.equals("Select Campaign Purpose*")) {
                        TextView errorText = (TextView) campaignPurposeSpinner.getSelectedView();
                        errorText.setError("Please select campaign purpose");
                    }

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
        getMenuInflater().inflate(R.menu.mandi_campaign, menu);
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


            Intent intent = new Intent(MandiCampaignActivity.this, IndividualFarmerContactMainActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();


        } else if (id == R.id.fm) {
            Intent intent = new Intent(MandiCampaignActivity.this, FarmerMeetingActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.fd) {

            Intent intent = new Intent(MandiCampaignActivity.this, FieldDayActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.dv) {
            Intent intent = new Intent(MandiCampaignActivity.this, DiagnosticVisitActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        }

        else if(id==R.id.mc){
            Intent intent = new Intent(MandiCampaignActivity.this, MandiCampaignActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.demol3) {

            Intent intent = new Intent(MandiCampaignActivity.this, DemoL3Activity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.demol3_progress) {

            Intent intent = new Intent(MandiCampaignActivity.this, DemoL3_InProgressActivity.class);
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

        setAdapterOnFirmName(rowView);
        //  Toast.makeText(IndividualFarmerContactActivity.getInstance(), "FIRM NAME "+rowView.findViewById(R.id.farmerName), Toast.LENGTH_SHORT).show();


        //    Toast.makeText(this, "FIRM NAME " + firmName.getText(), Toast.LENGTH_SHORT).show();
        //  Toast.makeText(this, "Proprietor NAME "+proprietorName.getText(), Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, "Retailer Name "+retailerMobile.getText(), Toast.LENGTH_SHORT).show();
    }



    public void deleteRetailerRow(View v){

        //  Toast.makeText(this, "aaaa ", Toast.LENGTH_SHORT).show();
//https://stackoverflow.com/questions/3995215/add-and-remove-views-in-android-dynamically




        View namebar = findViewById(R.id.retailerDetails);
        ViewGroup parent = (ViewGroup) namebar.getParent();
        if (parent != null) {
            parent.removeView(namebar);
        }

        viewList.remove(namebar);

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(data!=null && data.getData()==null) {

            Toast.makeText(this, "Request Code " + requestCode, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Result Code " + resultCode, Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {


                Toast.makeText(this, "Data " + data.getClipData(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Files count " + data.getClipData().getItemCount(), Toast.LENGTH_SHORT).show();

                ClipData clipData = data.getClipData();


                if(data!=null && data.getClipData()!=null && data.getClipData().getItemCount()==0)
                    filesCountTextView.setText("");

                if(clipData!=null & clipData.getItemCount()<=5 && clipData.getItemCount()!=0)
                {

                    filesCountTextView.setText(" "+clipData.getItemCount()+" files selected");

                    for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                        Uri uri = clipData.getItemAt(i).getUri();


                        InputStream iStream = null;
                        try {
                            iStream = getContentResolver().openInputStream(uri);

                            File f = new File(uri.getPath());
                            long size = f.length();
                            Toast.makeText(this, "FILE SIZE "+size, Toast.LENGTH_SHORT).show();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            byte[] inputData = getBytes(iStream);


                            //  Toast.makeText(this, "count : "+i+" byte array "+inputData , Toast.LENGTH_SHORT).show();

                            attachmentList.add(inputData);
                            // dbHelper.insertDataSetMaster(inputData);

                            Toast.makeText(this, "BYTE ARRAY " + inputData, Toast.LENGTH_SHORT).show();
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

                filesCountTextView.setText(" 1 file selected");

                Toast.makeText(this, "SINGLE BYTE ARRAY " + inputData, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }





    }




    public static MandiCampaignActivity getInstance() {
        return instance;
    }

    public void setAdapterOnFirmName(final View v) {
        List<String> firmNameList = new ArrayList<String>();

        // firmNameList = dbHelper.getFirmNameList(); //to be removed



        try {
            firmNameList = mandiCampaignActivityPresenterInterface.getFirmNameList();
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
                //   Toast.makeText(DiagnosticVisitActivity.this, "DISMISS", Toast.LENGTH_SHORT).show();

                String propName= null;
                try {
                    propName = mandiCampaignActivityPresenterInterface.getPropName(firmName.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String retailerMobile= null;
                try {
                    retailerMobile = mandiCampaignActivityPresenterInterface.getRetailerMobile(firmName.getText().toString());
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

}
