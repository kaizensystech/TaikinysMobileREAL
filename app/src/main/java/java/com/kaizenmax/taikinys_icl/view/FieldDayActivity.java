package java.com.kaizenmax.taikinys_icl.view;

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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.kaizenmax.taikinys_icl.R;
import com.kaizenmax.taikinys_icl.model.MobileDatabase;
import com.kaizenmax.taikinys_icl.pojo.FarmerDetailsPojo;
import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;
import com.kaizenmax.taikinys_icl.presenter.FieldDayActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.FieldDayActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.presenter.IndividualFarmerContactActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.IndividualFarmerContactActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.util.ActivityNames;
import com.kaizenmax.taikinys_icl.util.MultiSelectionSpinner;
import com.kaizenmax.taikinys_icl.view.DemoL3Activity;
import com.kaizenmax.taikinys_icl.view.DemoL3_InProgressActivity;
import com.kaizenmax.taikinys_icl.view.DiagnosticVisitActivity;
import com.kaizenmax.taikinys_icl.view.FarmerMeetingActivity;
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

public class FieldDayActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText dateOfActivityEditText;
    EditText  noOfFarmersEditText;
    EditText expensesEditText;
    AutoCompleteTextView villageNameEditText;
    Spinner cropCategorySpinner;
    Spinner cropFocusSpinner;
    MultiSelectionSpinner productFocusMultiSpinner;
    Spinner meetingPurposeSpinner;
    EditText observationsEditText;
    Button uploadButton;
    Button saveButton;
    DatePickerDialog picker;
    TextView selectedFilesCountTextview;

    private static FieldDayActivity instance;


 //   private FarmerMeetingActivityInterface farmerMeetingActivityInterface;

    String clientName;
    String faOfficeState;
    List<String> cropCategories=new ArrayList<String>();
    List<String> cropFocusList=new ArrayList<String>();

    MobileDatabase dbHelper= new MobileDatabase(this);
    String selectedCropCategory;
    String selectedCropFocus;

    String selectedMeetingPurpose;

    private android.support.v7.widget.LinearLayoutCompat parentLinearLayout;
    public LinearLayout retailerLayout;

    //private android.support.v7.widget.LinearLayoutCompat parentLinearLayout_farmers;
    public LinearLayout farmerLayout;

    List<View> viewList=new ArrayList<View>();
    List<RetailerDetailsPojo> retailerDetailsPojoList=new ArrayList<RetailerDetailsPojo>();

    List<View> farmerViewList=new ArrayList<View>();
    List<FarmerDetailsPojo> farmerDetailsPojoList=new ArrayList<FarmerDetailsPojo>();


    AutoCompleteTextView firmName;
    EditText proprietorName;
    EditText retailerMobile;

    EditText farmerNameEditText;
    EditText acresEditText;
    EditText farmerMobileEditText;

    //For upload
    int PICK_IMAGE_MULTIPLE = 1;
    List<byte []> attachmentList =new ArrayList<byte []>();

    private FieldDayActivityPresenterInterface fieldDayActivityPresenterInterface;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_day);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
   /*     FloatingActionButton fab = findViewById(R.id.fab);
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


//Variables id findviewbyid  by vinod on 27/08/2019


        instance=this;
        fieldDayActivityPresenterInterface = new FieldDayActivityPresenter();

        // testingFMLocalRetrival();
        dateOfActivityEditText = findViewById(R.id.dateOfActivity);
        dateOfActivityEditText.setInputType(InputType.TYPE_NULL);
        //        // it will not allow cursor to go to this field i.e non-focusable



//method for pop-up of calender by vinod  on  13/08/2019
        dateOfActivityEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar clndr = Calendar.getInstance();
                int day = clndr.get(Calendar.DAY_OF_MONTH);
                int month = clndr.get(Calendar.MONTH);
                int year = clndr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(FieldDayActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateOfActivityEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.getDatePicker().setMaxDate(new Date().getTime());



                picker.show();
                dateOfActivityEditText.setFocusable(false);

            }
        });







        // dateOfActivityEditText.setShowSoftInputOnFocus(false);
        noOfFarmersEditText = findViewById(R.id.nooffarmers);
        expensesEditText = findViewById(R.id.expenses);
        villageNameEditText = findViewById(R.id.village);
        cropCategorySpinner = findViewById(R.id.cropCategory);
        cropFocusSpinner = findViewById(R.id.cropFocus);
        meetingPurposeSpinner = findViewById(R.id.meetingpurpose);
        productFocusMultiSpinner = findViewById(R.id.productFocus);
        observationsEditText = findViewById(R.id.observations);



     //   addRowBtn = findViewById(R.id.addButton);
       // addRowFarmerBtn = findViewById(R.id.addButtonFarmers);

        saveButton = findViewById(R.id.saveBtn);
        uploadButton= findViewById(R.id.upload);
        selectedFilesCountTextview = findViewById(R.id.filescount);





        parentLinearLayout = findViewById(R.id.parent_linear_layout);

        retailerLayout = findViewById(R.id.retailerDetails);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.retailer_details_multiple_layout, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 2);

        // firmName=rowView.findViewById(R.id.firmName);


        viewList.add(rowView);

       setAdapterOnFirmName(rowView);



        farmerLayout = findViewById(R.id.farmerDetails);

      //  LayoutInflater inflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View rowView2 = inflater.inflate(R.layout.farmer_details_multipe_layout, null);
        parentLinearLayout.addView(rowView2, parentLinearLayout.getChildCount() - 5);

        farmerViewList.add(rowView2);







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








        try {
            clientName = fieldDayActivityPresenterInterface.getClientName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            faOfficeState=fieldDayActivityPresenterInterface.getStateName();
        } catch (Exception e) {
            e.printStackTrace();
        }




        //SETTING LIST IN PRODUCT FOCUS
        List<String> productlist = new ArrayList<String>();
        productlist.add("ASD");

//String clientName = dbHelper.getClientName();
//String faOfficeState=dbHelper.getStateName();


        try {

            productlist=fieldDayActivityPresenterInterface.getProductFocusList(clientName,faOfficeState);
            productFocusMultiSpinner.setItems(productlist);


        } catch (Exception e) {
            e.printStackTrace();
        }




        //  Toast.makeText(IndividualFarmerContactActivity.this, "ClientName "+clientName +" State"+faOfficeState, Toast.LENGTH_SHORT).show();

//SETTING LIST IN PRODUCT FOCUS ENDS


        //SETTING LIST IN VILLAGE DROP DOWN

        List<String> villageList=new ArrayList<String>();

        // villageList=dbHelper.getVillageList();

        try {
            villageList = fieldDayActivityPresenterInterface.getVillageList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,villageList);
        villageNameEditText.setAdapter(adapter);
        villageNameEditText.setThreshold(1);

//  SETTING LIST IN VILLAGE DROP DOWN ENDS

//GETTING CROP CATEGORIES

        try {
            cropCategories=fieldDayActivityPresenterInterface.getCropCategories();
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
                    cropFocusList= fieldDayActivityPresenterInterface.getCropFocus(selectedCropCategory);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ArrayAdapter<String> cropFocusDataAdapter = new ArrayAdapter<String>(FieldDayActivity.this, android.R.layout.simple_spinner_dropdown_item, cropFocusList);


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

            meetingPurposeList=fieldDayActivityPresenterInterface.getMeetingPurposeList(ActivityNames.FIELD_DAY);

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



        // Adding method to get meeting purposes ends



saveButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String dateofActivity_entered = dateOfActivityEditText.getText().toString();
        String noOfFarmers_entered = noOfFarmersEditText.getText().toString();
        String expenses_entered = expensesEditText.getText().toString();
        String village_entered = villageNameEditText.getText().toString();
        List<String> selectedProductsList = productFocusMultiSpinner.getSelectedStrings();
        String uploadFlagStatus = "No";

        String observations = observationsEditText.getText().toString();
        Boolean errorStatus = false;
        for (int i = 0; i < farmerViewList.size(); i++) {
            farmerNameEditText = farmerViewList.get(i).findViewById(R.id.farmerName);
            acresEditText = farmerViewList.get(i).findViewById(R.id.landacres);
            farmerMobileEditText = farmerViewList.get(i).findViewById(R.id.farmerMobile);
            errorStatus = false;

            if (farmerNameEditText.getText().toString() == null || farmerNameEditText.getText().toString().equals("")) {
                farmerNameEditText.setError("Please enter farmer name.");


                errorStatus = true;

                //   Toast.makeText(FarmerMeetingActivity.getInstance(), "ERROR hai "+farmerMobileEditText.getText()
                //         .toString(), Toast.LENGTH_SHORT).show();

            }
            if (acresEditText.getText().toString() == null || acresEditText.getText().toString().equals("")) {
                acresEditText.setError("Please enter land in acres.");
                errorStatus = true;
            }

            if (farmerMobileEditText.getText().toString() == null || farmerMobileEditText.getText().toString().equals("")) {

                farmerMobileEditText.setError("Please enter farmer mobile.");
                errorStatus = true;
            }


            if (farmerMobileEditText.getText().toString() != null && !farmerMobileEditText.getText().toString().equals("")

                    && farmerMobileEditText.getText().toString().trim().length() != 10) {
                farmerMobileEditText.setError("Please enter 10 digits in farmer mobile.");

                //  Toast.makeText(FarmerMeetingActivity.getInstance(), "Length "+farmerMobileEditText.getText().toString().length(), Toast.LENGTH_SHORT).show();
                errorStatus = true;
            }

            if (errorStatus == true)
                break;

        }


        if (dateofActivity_entered != null && !dateofActivity_entered.equals("")

                && village_entered!=null && !village_entered.equals("") && !(village_entered.trim().length()<2)
                && selectedCropCategory != null && !selectedCropCategory.equals("") && !selectedCropCategory.equals("Select Crop Category*")
                && selectedCropFocus != null && !selectedCropFocus.equals("") && !selectedCropFocus.equals("Select Crop Focus*")
                && selectedMeetingPurpose != null && !selectedMeetingPurpose.equals("") && !selectedMeetingPurpose.equals("Select Meeting Purpose*")
                && selectedProductsList.size() != 0 && !(selectedProductsList.size() == 1 && selectedProductsList.get(0).equals("Select Product Focus*"))
                && errorStatus == false)
        {
            Toast.makeText(FieldDayActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();

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

            for (int i = 0; i < farmerViewList.size(); i++) {

                EditText farmerName = (farmerViewList.get(i)).findViewById(R.id.farmerName);
                EditText farmerLand = (farmerViewList.get(i)).findViewById(R.id.landacres);
                EditText farmerMobile = (farmerViewList.get(i)).findViewById(R.id.farmerMobile);
                // String as=zx.getText().toString();

                FarmerDetailsPojo farmerDetailsPojo = new FarmerDetailsPojo();
                farmerDetailsPojo.setFarmerName(farmerName.getText().toString());
                farmerDetailsPojo.setFarmerLand(farmerLand.getText().toString());
                farmerDetailsPojo.setFarmerMobile(farmerMobile.getText().toString());

                // Toast.makeText(IndividualFarmerContactActivity.this, "FirmName:  " + as, Toast.LENGTH_SHORT).show();



                farmerDetailsPojoList.add(farmerDetailsPojo);

            }


            String faOfficeDistrict="";

            try {
                faOfficeDistrict=fieldDayActivityPresenterInterface.getFaOfficeDistrict();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Calendar cal=Calendar.getInstance();
            Date createdOn=cal.getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String createdOn_string = dateFormat.format(createdOn);
            String createdby="";

            try {
                createdby=fieldDayActivityPresenterInterface.getMobileFromSharedpreference();
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {

                //inserting Farmer Meeting data into local database using this method created by vinod on 14/08/2019
                fieldDayActivityPresenterInterface.insertFDdata("FD", dateofActivity_entered,
                        noOfFarmers_entered,expenses_entered,
                        village_entered,selectedCropCategory,selectedCropFocus,
                        selectedMeetingPurpose,selectedProductsList,
                        observations,farmerDetailsPojoList,retailerDetailsPojoList,

                        uploadFlagStatus,
                        createdOn_string,createdby,clientName,faOfficeDistrict,attachmentList);
            } catch (Exception e) {
                e.printStackTrace();
            }


            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                try {

                    IndividualFarmerContactActivityPresenterInterface individualFarmerContactActivityPresenterInterface = new IndividualFarmerContactActivityPresenter();
                     individualFarmerContactActivityPresenterInterface.sendingDataToWebService();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //sendingDataToWebService(); to be removed
            }


//TO reset data by vinod on 27/8/2019

            Intent intent = new Intent(FieldDayActivity.this, FieldDayActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();






        } else {
            if (dateofActivity_entered == null || dateofActivity_entered.equals(""))
                dateOfActivityEditText.setError("Please select date of activity");


            if(village_entered==null || village_entered.equals(""))
                villageNameEditText.setError("Please enter village");

            if(  village_entered!=null && !village_entered.equals("")    && village_entered.trim().length()<2)
            {
                villageNameEditText.setError("Please enter minimum two characters in village");
            }

            if (selectedCropCategory == null || selectedCropCategory.equals("") || selectedCropCategory.equals("Select Crop Category*")) {
                TextView errorText = (TextView) cropCategorySpinner.getSelectedView();
                errorText.setError("Please select crop category");
            }

            if (selectedCropFocus == null || selectedCropFocus.equals("") || selectedCropFocus.equals("Select Crop Focus*")) {
                TextView errorText = (TextView) cropFocusSpinner.getSelectedView();
                errorText.setError("Please select crop focus");
            }

            if (selectedMeetingPurpose == null || selectedMeetingPurpose.equals("") || selectedMeetingPurpose.equals("Select Meeting Purpose*")) {
                TextView errorText = (TextView) meetingPurposeSpinner.getSelectedView();
                errorText.setError("Please select meeting purpose");
            }

            if (selectedProductsList.size() == 0 || (selectedProductsList.size() == 1 && selectedProductsList.get(0).equals("Select Product Focus*"))) {
                TextView errorText = (TextView) productFocusMultiSpinner.getSelectedView();
                errorText.setError("Please select product focus");
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
        getMenuInflater().inflate(R.menu.field_day, menu);
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


            Intent intent = new Intent(FieldDayActivity.this, IndividualFarmerContactMainActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();


        } else if (id == R.id.fm) {
            Intent intent = new Intent(FieldDayActivity.this, FarmerMeetingActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.fd) {
            Intent intent = new Intent(FieldDayActivity.this, FieldDayActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.dv) {

            Intent intent = new Intent(FieldDayActivity.this, DiagnosticVisitActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }
        else if(id==R.id.mc){
            Intent intent = new Intent(FieldDayActivity.this, MandiCampaignActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }


        else if (id == R.id.demol3) {

            Intent intent = new Intent(FieldDayActivity.this, DemoL3Activity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }


        else if(id == R.id.demol3_progress)
        {
            Intent intent = new Intent(FieldDayActivity.this, DemoL3_InProgressActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static FieldDayActivity getInstance() {
        return instance;
    }


    public void setAdapterOnFirmName(final View v) {
        List<String> firmNameList = new ArrayList<String>();

        // firmNameList = dbHelper.getFirmNameList(); //to be removed

        fieldDayActivityPresenterInterface = new FieldDayActivityPresenter();

        try {
            firmNameList = fieldDayActivityPresenterInterface.getFirmNameList();
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
                    propName = fieldDayActivityPresenterInterface.getPropName(firmName.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String retailerMobile= null;
                try {
                    retailerMobile = fieldDayActivityPresenterInterface.getRetailerMobile(firmName.getText().toString());
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



    public void addFarmerRow(View v){

        Boolean errorStatus=false;
        for(int i=0;i<farmerViewList.size();i++) {
            farmerNameEditText = farmerViewList.get(i).findViewById(R.id.farmerName);
            acresEditText = farmerViewList.get(i).findViewById(R.id.landacres);
            farmerMobileEditText = farmerViewList.get(i).findViewById(R.id.farmerMobile);


            if (farmerNameEditText.getText().toString() == null || farmerNameEditText.getText().toString().equals(""))
            {
                farmerNameEditText.setError("Please enter farmer name.");


                errorStatus = true;

                //   Toast.makeText(FarmerMeetingActivity.getInstance(), "ERROR hai "+farmerMobileEditText.getText()
                //         .toString(), Toast.LENGTH_SHORT).show();

            }
            if(acresEditText.getText().toString() == null || acresEditText.getText().toString().equals(""))
            {
                acresEditText.setError("Please enter land in acres.");
                errorStatus = true;
            }

            if(farmerMobileEditText.getText().toString() == null || farmerMobileEditText.getText().toString().equals(""))
            {

                farmerMobileEditText.setError("Please enter farmer mobile.");
                errorStatus = true;
            }


            if(farmerMobileEditText.getText().toString() != null && !farmerMobileEditText.getText().toString().equals("")

                    &&  farmerMobileEditText.getText().toString().trim().length() != 10)
            {
                farmerMobileEditText.setError("Please enter 10 digits in farmer mobile.");

                //  Toast.makeText(FarmerMeetingActivity.getInstance(), "Length "+farmerMobileEditText.getText().toString().length(), Toast.LENGTH_SHORT).show();
                errorStatus = true;
            }

            if(errorStatus==true)
                break;

        }


        if(errorStatus== false) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.farmer_details_multipe_layout, null);
            // Add the new row before the add field button.
            parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 5);
            // firmName = ((View) rowView.getParent().getParent()).findViewById(R.id.firmName);


            farmerViewList.add(rowView);

        }
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


    public void deleteRetailerRow(View v) {

        //  Toast.makeText(this, "aaaa ", Toast.LENGTH_SHORT).show();
//https://stackoverflow.com/questions/3995215/add-and-remove-views-in-android-dynamically




        View namebar = ((View) v.getParent().getParent()).findViewById(R.id.retailerDetails);
        ViewGroup parent = (ViewGroup) namebar.getParent();
        if (parent != null) {
            parent.removeView(namebar);
        }

        viewList.remove(namebar);

    }

    public void deleteFarmerRow(View v) {

        //  Toast.makeText(this, "aaaa ", Toast.LENGTH_SHORT).show();
//https://stackoverflow.com/questions/3995215/add-and-remove-views-in-android-dynamically



        View namebar = ((View) v.getParent().getParent()).findViewById(R.id.farmerDetails);

       // View namebar = findViewById(R.id.farmerDetails);
        ViewGroup parent = (ViewGroup) namebar.getParent();
        if (parent != null) {
            parent.removeView(namebar);
        }

        farmerViewList.remove(namebar);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        attachmentList =new ArrayList<byte []>();
        if(data!=null && data.getData()==null) {

         //   Toast.makeText(this, "Request Code " + requestCode, Toast.LENGTH_SHORT).show();
           // Toast.makeText(this, "Result Code " + resultCode, Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {


             //   Toast.makeText(this, "Data " + data.getClipData(), Toast.LENGTH_SHORT).show();
               // Toast.makeText(this, "Files count " + data.getClipData().getItemCount(), Toast.LENGTH_SHORT).show();

                ClipData clipData = data.getClipData();


                if(data!=null && data.getClipData()!=null && data.getClipData().getItemCount()==0 )
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
                          //  Toast.makeText(this, "FILE SIZE "+size, Toast.LENGTH_SHORT).show();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            byte[] inputData = getBytes(iStream);


                            //  Toast.makeText(this, "count : "+i+" byte array "+inputData , Toast.LENGTH_SHORT).show();

                            attachmentList.add(inputData);
                            // dbHelper.insertDataSetMaster(inputData);

                           // Toast.makeText(this, "BYTE ARRAY " + inputData, Toast.LENGTH_SHORT).show();
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





                    selectedFilesCountTextview.setText("1 file selected");





                //Toast.makeText(this, "SINGLE BYTE ARRAY " + inputData, Toast.LENGTH_SHORT).show();
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




}
