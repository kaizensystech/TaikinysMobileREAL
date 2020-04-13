package java.com.kaizenmax.taikinys_icl.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.kaizenmax.taikinys_icl.R;
import com.kaizenmax.taikinys_icl.backgroundservices.DataDownloadService;
import com.kaizenmax.taikinys_icl.backgroundservices.ExampleService;
import com.kaizenmax.taikinys_icl.model.MobileDatabase;
import com.kaizenmax.taikinys_icl.pojo.DataSetMasterPojo;
import com.kaizenmax.taikinys_icl.pojo.FaMasterPojo;
import com.kaizenmax.taikinys_icl.pojo.FarmerDetailsPojo;
import com.kaizenmax.taikinys_icl.pojo.PromoFarmerMeetingPojo;
import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;
import com.kaizenmax.taikinys_icl.pojo.UsersPojo;
import com.kaizenmax.taikinys_icl.pojo.VillagesPojo;
import com.kaizenmax.taikinys_icl.presenter.IndividualFarmerContactActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.IndividualFarmerContactActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.util.MultiSelectionSpinner;
import com.kaizenmax.taikinys_icl.view.DemoL3Activity;
import com.kaizenmax.taikinys_icl.view.DemoL3_InProgressActivity;
import com.kaizenmax.taikinys_icl.view.DiagnosticVisitActivity;
import com.kaizenmax.taikinys_icl.view.FarmerMeetingActivity;
import com.kaizenmax.taikinys_icl.view.FieldDayActivity;
import com.kaizenmax.taikinys_icl.view.MandiCampaignActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class IndividualFarmerContactMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText dateOfActivityEditText;
    Spinner cropCategorySpinner;
    Spinner cropFocusSpinner;
    MultiSelectionSpinner productFocusMultiSpinner;
    EditText observationsEditText;
    EditText farmerNameEditText;
    AutoCompleteTextView villageNameEditText;
    EditText farmerMobileEditText;
    EditText landAcresEditText;
    EditText expensesEditText;
    TextView retailerDetailsTextView;

    private android.support.v7.widget.LinearLayoutCompat parentLinearLayout;
    public LinearLayout retailerLayout;
//Retailer details variables are also to be taken here

    AutoCompleteTextView firmName;
    EditText proprietorName;
    EditText retailerMobile;
    Button addRowBtn;
    Button deleteRowBtn;
    Button saveButton;



    // VARIABLES OTHER THAN COMPONENTS
    DatePickerDialog picker;
    //MobileDatabase dbHelper;
    RequestQueue requestQueue;

    List<String> cropCategories;
    List<String> cropFocusList;
    String selectedCropCategory;
    String selectedCropFocus;

    private static IndividualFarmerContactMainActivity instance;


    public static Integer dbCreateStatus=0;
    public static Integer dbInsertSTatus=0;
    public static Integer dbRetriveSTatus=0;



    List<View> viewList;
    List<RetailerDetailsPojo> retailerDetailsPojoList;

    ProgressBar progressBar;
    SharedPreferences sharedpreferences;

    IndividualFarmerContactActivityPresenterInterface individualFarmerContactActivityPresenterInterface;


    String clientName;
    String faOfficeState;


    MobileDatabase dbHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_farmer_contact_main);
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



       //

        viewList=new ArrayList<View>();
        cropCategories=new ArrayList<String>();
        cropFocusList=new ArrayList<String>();
        retailerDetailsPojoList=new ArrayList<RetailerDetailsPojo>();
        dbHelper= new MobileDatabase(this);




       //


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // GETTING VALUES OF COMPONENTS
        dateOfActivityEditText = findViewById(R.id.dateOfActivity);
        dateOfActivityEditText.setInputType(InputType.TYPE_NULL);
        //        // it will not allow cursor to go to this field i.e non-focusable
        // dateOfActivityEditText.setShowSoftInputOnFocus(false);
        cropCategorySpinner = findViewById(R.id.cropCategory);
        cropFocusSpinner = findViewById(R.id.cropFocus);
        productFocusMultiSpinner = findViewById(R.id.productFocus);
        observationsEditText = findViewById(R.id.observations);
        farmerNameEditText = findViewById(R.id.farmerName);
        villageNameEditText = findViewById(R.id.village);
        farmerMobileEditText = findViewById(R.id.farmerMob);
        landAcresEditText = findViewById(R.id.acres);
        expensesEditText = findViewById(R.id.expenses);
        retailerDetailsTextView=findViewById(R.id.retailerDetailsText);




        firmName = findViewById(R.id.firmName);
        proprietorName = findViewById(R.id.propName);
        retailerMobile = findViewById(R.id.retailerMobile);
        addRowBtn = findViewById(R.id.addButton);
        deleteRowBtn = findViewById(R.id.deleteButton);
        saveButton = findViewById(R.id.saveBtn);
        instance=this;
        individualFarmerContactActivityPresenterInterface = new IndividualFarmerContactActivityPresenter();

        progressBar=findViewById(R.id.pBar);
        progressBar.setVisibility(View.GONE);


        //GETTING VALUES OF COMPONENTS ENDS


        try {
            clientName = individualFarmerContactActivityPresenterInterface.getClientName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            faOfficeState=individualFarmerContactActivityPresenterInterface.getStateName();
        } catch (Exception e) {
            e.printStackTrace();
        }


//ADDING CALENDER ON DATE OF ACTIVITY

        dateOfActivityEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar clndr = Calendar.getInstance();
                int day = clndr.get(Calendar.DAY_OF_MONTH);
                int month = clndr.get(Calendar.MONTH);
                int year = clndr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(IndividualFarmerContactMainActivity.this,
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




//ADDING CALENDER ON DATE OF ACTIVITY ENDS

        getDataSetEntriesMethod(); //method to get entries of all dataset table


        // dbHelper = new MobileDatabase(this);

        // ADDING RETAILER DETAILS LAYOUT
        parentLinearLayout = findViewById(R.id.parent_linear_layout);

        retailerLayout = findViewById(R.id.retailerDetails);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.retailer_details_multiple_layout, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 2);

        firmName=rowView.findViewById(R.id.firmName);


        viewList.add(rowView);

        setAdapterOnFirmName(rowView);


        // ADDING RETAILER DETAILS LAYOUT ENDS

        //SETTING LIST IN PRODUCT FOCUS
        List<String> productlist = new ArrayList<String>();

//String clientName = dbHelper.getClientName();
//String faOfficeState=dbHelper.getStateName();


        try {

            productlist=individualFarmerContactActivityPresenterInterface.getProductFocusList(clientName,faOfficeState);
            productFocusMultiSpinner.setItems(productlist);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //  Toast.makeText(IndividualFarmerContactActivity.this, "ClientName "+clientName +" State"+faOfficeState, Toast.LENGTH_SHORT).show();

//SETTING LIST IN PRODUCT FOCUS ENDS




        List<String> villageList=new ArrayList<String>();

        // villageList=dbHelper.getVillageList();

        try {
            villageList = individualFarmerContactActivityPresenterInterface.getVillageList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,villageList);
        villageNameEditText.setAdapter(adapter);
        villageNameEditText.setThreshold(1);







        // cropCategories=dbHelper.getCropCategories(); to be removed

        try {
            cropCategories=individualFarmerContactActivityPresenterInterface.getCropCategories();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cropFocusList.add("Select Focus Crop*");


        //   villagesLocalTest();
        //usersLocalTest();
        //localTestingFaMaster();




        // testingLocalRetrieval();//TEST method to retrieve local entries

        //Adding List to Crop Category

        ArrayAdapter<String> cropCategoryDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cropCategories);


        cropCategoryDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cropCategorySpinner.setAdapter(cropCategoryDataAdapter);

        //Adding List Crop Category Ends


        // Adding List to CropFocus

        ArrayAdapter<String> cropFocusDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cropFocusList);


        cropFocusDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cropFocusSpinner.setAdapter(cropFocusDataAdapter);


        //Adding List to CropFocus

//Adding Listener to Crop category so that crop Focus list can be according to selected crop category

        cropCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedCropCategory= cropCategories.get(position);

                // cropFocusList=   dbHelper.getCropFocus(selectedCropCategory);

                try {
                    cropFocusList= individualFarmerContactActivityPresenterInterface.getCropFocus(selectedCropCategory);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ArrayAdapter<String> cropFocusDataAdapter = new ArrayAdapter<String>(IndividualFarmerContactMainActivity.this, android.R.layout.simple_spinner_dropdown_item, cropFocusList);


                cropFocusDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                cropFocusSpinner.setAdapter(cropFocusDataAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//Adding Listener to Crop category so that crop Focus list can be according to selected crop category Ends


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


        //Adding listener on save button

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(IndividualFarmerContactActivity.this, "dateOfActivity " + dateOfActivityEditText.getText(), Toast.LENGTH_SHORT).show();

                String dateOfActivity_entered = dateOfActivityEditText.getText().toString();
                String cropCategory_entered = selectedCropCategory;
                String cropFocus_entered = selectedCropFocus;
                //FOCUS MULTIPLE PRODUCTS ayga is line pr
                String observations_entered = observationsEditText.getText().toString();
                String farmerName_entered = farmerNameEditText.getText().toString();
                String villageName_entered = villageNameEditText.getText().toString();
                String farmerMobile_entered = farmerMobileEditText.getText().toString();
                String landAcres_entered = landAcresEditText.getText().toString();
                String expenses_entered = expensesEditText.getText().toString();
                //RETAILER AYGA YHA is line m
                String uploadFlagStatus = "No";
                List<String> selectedProductsList = productFocusMultiSpinner.getSelectedStrings();



                // Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
                // setSupportActionBar(myToolbar);


                if (dateOfActivity_entered.equalsIgnoreCase("") || cropCategory_entered.equalsIgnoreCase("")

                        || cropFocus_entered.equalsIgnoreCase("")

                        || selectedProductsList.size() == 0

                        || farmerName_entered.equalsIgnoreCase("")

                        || villageName_entered.equalsIgnoreCase("")


                        || farmerMobile_entered.equalsIgnoreCase("")

                        || landAcres_entered.equalsIgnoreCase("")

                       // removed by jyoti, bhavna in guidance of Vinod on 11/13/2019 || expenses_entered.equalsIgnoreCase("")

                        || villageName_entered.trim().length()<2

                        || farmerMobile_entered.trim().length()<10




                ) {
                    if (dateOfActivity_entered.equalsIgnoreCase("")) {
                        //dateOfActivityEditText.setHint("Please enter date of activity");//it gives user to hint
                        dateOfActivityEditText.setError("Please enter date of activity");//it gives user to info message //use any one //
                    }

                    if (cropCategory_entered.equalsIgnoreCase("Select Crop Category*")) {
                        // cropCategorySpinner.setHint("Please enter date of activity");//it gives user to hint
                        //cropCategorySpinner.setError("Please enter date of activity");//it gives user to info message //use any one //
                        TextView errorText = (TextView) cropCategorySpinner.getSelectedView();
                        errorText.setError("Please select crop category");
                        //   errorText.setTextColor(Color.RED);//just to highlight that this is an error
                        // errorText.setText("my actual error text");//changes the selected item text to this
                    }



                    if (cropFocus_entered.equalsIgnoreCase("Select Crop Focus*")) {
                        // cropCategorySpinner.setHint("Please enter date of activity");//it gives user to hint
                        //cropCategorySpinner.setError("Please enter date of activity");//it gives user to info message //use any one //
                        TextView errorText = (TextView) cropFocusSpinner.getSelectedView();
                        errorText.setError("Please select crop focus");
                        // errorText.setTextColor(Color.RED);//just to highlight that this is an error
                        // errorText.setText("my actual error text");//changes the selected item text to this
                    }

                    if (selectedProductsList.size() == 0 ) {
                        // cropCategorySpinner.setHint("Please enter date of activity");//it gives user to hint
                        //cropCategorySpinner.setError("Please enter date of activity");//it gives user to info message //use any one //

                        TextView errorText = (TextView) productFocusMultiSpinner.getSelectedView();
                        errorText.setError("Please select product focus");



                        //  errorText.setTextColor(Color.RED);//just to highlight that this is an error
                        // errorText.setText("my actual error text");//changes the selected item text to this
                    }




                    if (farmerName_entered.equalsIgnoreCase("")) {
                        // cropCategorySpinner.setHint("Please enter date of activity");//it gives user to hint
                        //cropCategorySpinner.setError("Please enter date of activity");//it gives user to info message //use any one //

                        farmerNameEditText.setError("Please enter farmer name");
                        //errorText.setTextColor(Color.RED);//just to highlight that this is an error
                        // errorText.setText("my actual error text");//changes the selected item text to this
                    }
                    if (villageName_entered.equalsIgnoreCase("")) {
                        //dateOfActivityEditText.setHint("Please enter date of activity");//it gives user to hint
                        villageNameEditText.setError("Please enter village");//it gives user to info message //use any one //
                    }

                    if (farmerMobile_entered.equalsIgnoreCase("")) {
                        //dateOfActivityEditText.setHint("Please enter date of activity");//it gives user to hint
                        farmerMobileEditText.setError("Please enter farmer mobile");//it gives user to info message //use any one //
                    }

                    if (landAcres_entered.equalsIgnoreCase("")) {
                        //dateOfActivityEditText.setHint("Please enter date of activity");//it gives user to hint
                        landAcresEditText.setError("Please enter land in acres");//it gives user to info message //use any one //
                    }

                /*    if (expenses_entered.equalsIgnoreCase("")) {
                        //dateOfActivityEditText.setHint("Please enter date of activity");//it gives user to hint
                        expensesEditText.setError("Please enter expenses");//it gives user to info message //use any one //
                    }
                     // removed by jyoti, bhavna in guidance of Vinod on 11/13/2019

                     */

                    if(villageName_entered!=null && !villageName_entered.equals("")    && villageName_entered.trim().length()<2)
                    {
                        villageNameEditText.setError("Please enter minimum two characters in village");
                    }


                    if(!farmerMobile_entered.trim().equals("") && farmerMobile_entered.trim().length()<10)
                    {
                        farmerMobileEditText.setError("Please enter ten digits in farmer mobile number");
                    }


                } else {


                //    Toast.makeText(IndividualFarmerContactMainActivity.this, "HELLO", Toast.LENGTH_SHORT).show();

                    for (int i = 0; i < viewList.size(); i++) {

                  //      Toast.makeText(IndividualFarmerContactMainActivity.this, "HII", Toast.LENGTH_SHORT).show();

                        EditText firmName = (viewList.get(i)).findViewById(R.id.firmName);
                        EditText retailerMobile = (viewList.get(i)).findViewById(R.id.retailerMobile);
                        EditText proprietorName = (viewList.get(i)).findViewById(R.id.propName);
                        // String as=zx.getText().toString();

                        RetailerDetailsPojo retailerDetailsPojo = new RetailerDetailsPojo();
                        retailerDetailsPojo.setFirmName(firmName.getText().toString());
                        retailerDetailsPojo.setRetailerMobile(retailerMobile.getText().toString());
                        retailerDetailsPojo.setPropName(proprietorName.getText().toString());
                        // Toast.makeText(IndividualFarmerContactMainActivity.this, "FirmName:  " + firmName.getText().toString(), Toast.LENGTH_SHORT).show();



                        retailerDetailsPojoList.add(retailerDetailsPojo);

                    }



                    // String faOfficeDistrict=dbHelper.getFaOfficeDistrict();
                    String faOfficeDistrict="";

                    try {
                        faOfficeDistrict=individualFarmerContactActivityPresenterInterface.getFaOfficeDistrict();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Calendar cal=Calendar.getInstance();
                    Date createdOn=cal.getTime();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String createdOn_string = dateFormat.format(createdOn);
                    String createdby="";

                    try {
                        createdby=individualFarmerContactActivityPresenterInterface.getMobileFromSharedpreference();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    try {
                        individualFarmerContactActivityPresenterInterface.insertIFCdata("IFC", dateOfActivity_entered, villageName_entered,
                                cropFocus_entered, cropCategory_entered, farmerName_entered, farmerMobile_entered, landAcres_entered,
                                expenses_entered, observations_entered, uploadFlagStatus, retailerDetailsPojoList, selectedProductsList,
                                createdOn_string,createdby,clientName,faOfficeDistrict);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }





//,farmerName_entered,farmerMobile_entered,landAcres_entered
                    // Toast.makeText(IndividualFarmerContactActivity.this, "Product ", Toast.LENGTH_SHORT).show();


                    dateOfActivityEditText.setText("");
                    cropCategorySpinner.setSelection(0);
                    cropFocusSpinner.setSelection(0);
                    productFocusMultiSpinner.setSelection(0);
                    //reset it also  productFocusMultiSpinner
                    observationsEditText.setText("");
                    farmerMobileEditText.setText("");
                    villageNameEditText.setText("");
                    farmerNameEditText.setText("");

                    farmerMobileEditText.setText("");
                    landAcresEditText.setText("");
                    expensesEditText.setText("");


                    for (int i = 0; i < viewList.size(); i++) {
                        View view = viewList.get(i);
                        EditText firmName = view.findViewById(R.id.firmName);
                        EditText propName = view.findViewById(R.id.propName);
                        EditText retailerMobile = view.findViewById(R.id.retailerMobile);

                    //



                        firmName.setText("");
                        propName.setText("");
                        retailerMobile.setText("");



                    }


                    //  proprietorName.setText("");
                    //  retailerMobile.setText("");

                    Toast.makeText(IndividualFarmerContactMainActivity.this, "Saved successfully", Toast.LENGTH_LONG).show();


                    //  Toast.makeText(IndividualFarmerContactActivity.this, "DB INSERT STATUS "+dbInsertSTatus, Toast.LENGTH_SHORT).show();




                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                        try {
                            individualFarmerContactActivityPresenterInterface.sendingDataToWebService();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //sendingDataToWebService(); to be removed
                    }

                }



            }
        });


        //Adding listener on save button ends


//CODE TO START FOREGROUND SERVICE
        Intent serviceIntent = new Intent(this, ExampleService.class);
        serviceIntent.putExtra("inputExtra", "kuch bhi");
        ContextCompat.startForegroundService(this, serviceIntent);


        Intent serviceIntent2 = new Intent(this, DataDownloadService.class);
        serviceIntent2.putExtra("inputExtra", "kuch bhiHo");
        ContextCompat.startForegroundService(this, serviceIntent2);



//CODE TO START FOREGROUND SERVICE Ends


        //CODE TO STOP FOREGROUND SERVICES
        // Intent serviceIntent2 = new Intent(this, ExampleService.class);
        //stopService(serviceIntent2);


    }   //On CREATE METHOD ENDING

    public void getDataSetFromCloudAndLocalSave()
    {
        // Toast.makeText(this, "INSERTING DATA START :", Toast.LENGTH_SHORT).show();


        // String   url2 = "https://tvsfinal.herokuapp.com/service/dataSetMaster/E92M75GV9kUQnNURUWg4r9hge5";



        progressBar.setVisibility(View.VISIBLE);
        dateOfActivityEditText.setVisibility(View.GONE);
        cropCategorySpinner.setVisibility(View.GONE);
        cropFocusSpinner.setVisibility(View.GONE);
        productFocusMultiSpinner.setVisibility(View.GONE);
        observationsEditText.setVisibility(View.GONE);
        farmerNameEditText.setVisibility(View.GONE);
        villageNameEditText.setVisibility(View.GONE);
        farmerMobileEditText.setVisibility(View.GONE);
        landAcresEditText.setVisibility(View.GONE);
        expensesEditText.setVisibility(View.GONE);
        retailerDetailsTextView.setVisibility(View.GONE);
        saveButton.setVisibility(View.GONE);
        addRowBtn.setVisibility(View.GONE);

        for (int i=0;i<viewList.size();i++)
        {
            View v=viewList.get(i);

            v.findViewById(R.id.firmName).setVisibility(View.GONE);
            v.findViewById(R.id.retailerMobile).setVisibility(View.GONE);
            v.findViewById(R.id.propName).setVisibility(View.GONE);
            v.findViewById(R.id.deleteButton).setVisibility(View.GONE);
        }

       String awsProdKey= "E92M75GV9kUQnQQNURUWg4r9hge5" ;
        String herokuTestingTaikinysKey ="E92M75GV9kUQnNURUWg4r9hge5" ;

        String url2="https://taikinys.kaizenmax.com/rest/service/dataSetMaster/E92M75GV9kUQnQQNURUWg4r9hge5";
        requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url2,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try{
                            // Loop through the array elements
                            int count=0;
                            //   Toast.makeText(IndividualFarmerContactActivity.this, "LENGTH OF FETCHED DATA :"+response.length(), Toast.LENGTH_SHORT).show();


                            dbHelper.deleteAllEntriesOfDataSetMaster(); //deleting data set entries before inserting



                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject dataSetMaster = response.getJSONObject(i);

                                // Get the current student (json object) data
                                // String clientName = student.getString("clientName");
                                //  String dataSetTitle = student.getString("dataSetTitle");
                                //  String createdOn = student.getString("createdOn");






                                String dataSetTitle = dataSetMaster.getString("dataSetTitle");
                                String dataSetDescription = dataSetMaster.getString("dataSetDescription");
                                String element1 = dataSetMaster.getString("element1");
                                String element2 = dataSetMaster.getString("element2");
                                String element3 = dataSetMaster.getString("element3");
                                String element4 = dataSetMaster.getString("element4");
                                String number1=dataSetMaster.getString("number1");
                                String number2=dataSetMaster.getString("number2");
                                String text1=dataSetMaster.getString("text1");
                                String text2=dataSetMaster.getString("text2");
                                String date1=dataSetMaster.getString("date1");
                                String date2=dataSetMaster.getString("date2");
                                String status=dataSetMaster.getString("status");
                                String createdOn=dataSetMaster.getString("createdOn");
                                String createdBy=dataSetMaster.getString("createdBy");
                                String id=dataSetMaster.getString("id");
                                String clientName=dataSetMaster.getString("clientName");
                                //    Toast.makeText(IndividualFarmerContactActivity.this, "Index Retrieved "+i, Toast.LENGTH_SHORT).show();

                                try {

                                    if(dataSetTitle.equals("ClientDistrictRetailer"))
                                    {
                                        String faOfficeDistrict=dbHelper.getFaOfficeDistrict();

                                        if(element1.equals(faOfficeDistrict))
                                        {

                                            //  Toast.makeText(IndividualFarmerContactActivity.this, "FA DISTRICT OFFICE : "+faOfficeDistrict, Toast.LENGTH_SHORT).show();
                                            //Toast.makeText(IndividualFarmerContactActivity.this, "Firm Name "+element2, Toast.LENGTH_SHORT).show();
                                            dbHelper.insertDataSetMaster(dataSetTitle, dataSetDescription, element1, element2, element3, element4
                                                    , number1, number2, text1, text2, date1, date2,
                                                    status, new Date(),
                                                    createdBy, clientName);
                                            count++;
                                        }
                                    }
                                    else {


                                        dbHelper.insertDataSetMaster(dataSetTitle, dataSetDescription, element1, element2, element3, element4
                                                , number1, number2, text1, text2, date1, date2,
                                                status, new Date(),
                                                createdBy, clientName);
                                        count++;
                                        //  Toast.makeText(IndividualFarmerContactActivity.this, "Index INSERT "+i+" status "+status, Toast.LENGTH_SHORT).show();

                                    }


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                //   Toast.makeText(IndividualFarmerContactActivity.this, "Values Index"+i+" clientName: "+clientName+" dataSetTitle: "+dataSetTitle+" Created on: "+createdOn, Toast.LENGTH_SHORT).show();

                                // Display the formatted json data in text view
                                //   mTextView.append(firstName +" " + lastName +"\nAge : " + age);
                                // mTextView.append("\n\n");
                            }

                            Intent intent = new Intent(IndividualFarmerContactMainActivity.this, IndividualFarmerContactMainActivity.class);
                            //pgsBar.setVisibility(View.GONE);
                            startActivity(intent);
                            finish();





                            // Toast.makeText(IndividualFarmerContactActivity.this, "COUNT "+count, Toast.LENGTH_SHORT).show();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Toast.makeText(IndividualFarmerContactMainActivity.this, "ERROR "+error.networkResponse.statusCode, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);


    }


    public  void getDataSetEntriesMethod()
    {

        // Toast.makeText(this, "FETCHING DATA", Toast.LENGTH_SHORT).show();
        final Cursor cursor = dbHelper.getAllDataSetEntries();


        // Toast.makeText(this, "CURSOR SIZE "+cursor.getCount(), Toast.LENGTH_SHORT).show();
        if(cursor.getCount()==0)
        {
            getDataSetFromCloudAndLocalSave();
        }

        else {
            int x = 0;
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String id = cursor.getString(cursor.getColumnIndex(DataSetMasterPojo.DATASETMASTER_COLUMN_ID));
                    String dataSetTitle = cursor.getString(cursor.getColumnIndex(DataSetMasterPojo.DATASETMASTER_COLUMN_DATASET_TITLE));
                    String status=cursor.getString(cursor.getColumnIndex(DataSetMasterPojo.DATASETMASTER_COLUMN_STATUS));
                    //String element1 = cursor.getString(cursor.getColumnIndex(MobileDatabase.DATASETMASTER_COLUMN_ELEMENT1));
                    //  String element2 = cursor.getString(cursor.getColumnIndex(MobileDatabase.DATASETMASTER_COLUMN_ELEMENT2));
                    x++;
                    //   Toast.makeText(this, "Id from Local DB"+x +" datasettitle "+dataSetTitle, Toast.LENGTH_SHORT).show();

                    //   Toast.makeText(this, "SERIAL: "+x+" STATUS: "+status, Toast.LENGTH_SHORT).show();


                    // do what ever you want here
                } while (cursor.moveToNext());

                // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
            }
            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
            cursor.close();

        }
    }





    //Method to add retailer details Layout
    public void addRetailerRow(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.retailer_details_multiple_layout, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 2);
        firmName = ((View) rowView.getParent().getParent()).findViewById(R.id.firmName);


        viewList.add(rowView);

        setAdapterOnFirmName(rowView);
        //  Toast.makeText(IndividualFarmerContactActivity.getInstance(), "FIRM NAME "+rowView.findViewById(R.id.farmerName), Toast.LENGTH_SHORT).show();


        //    Toast.makeText(this, "FIRM NAME " + firmName.getText(), Toast.LENGTH_SHORT).show();
        //  Toast.makeText(this, "Proprietor NAME "+proprietorName.getText(), Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, "Retailer Name "+retailerMobile.getText(), Toast.LENGTH_SHORT).show();
    }


    //Method to delete retailer details Layout
    public void deleteRetailerRow(View v) {

        //  Toast.makeText(this, "aaaa ", Toast.LENGTH_SHORT).show();
//https://stackoverflow.com/questions/3995215/add-and-remove-views-in-android-dynamically

      /*  View namebar = View.findViewById(R.id.namebar);
        ((ViewGroup) namebar.getParent()).removeView(namebar); */


       // Toast.makeText(IndividualFarmerContactMainActivity.getInstance(), "VIEW "+v, Toast.LENGTH_SHORT).show();


        View namebar = ((View) v.getParent().getParent()).findViewById(R.id.retailerDetails);
        ViewGroup parent = (ViewGroup) namebar.getParent();

        if (parent != null) {
            parent.removeView(namebar);
        }

        viewList.remove(namebar);

    }


    public void testingLocalRetrieval()
    {
        Cursor z=dbHelper.getAllPromoEntries();
        Integer id=null;
        if (z != null && z.moveToFirst()) {
            do {
                id = z.getInt(z.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_ID));
                String flagStatus=z.getString(z.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_UPLOAD_FLAG));
                String village=z.getString(z.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_VILLAGE));

                //  Toast.makeText(this, "ID "+id+" FlagStatus "+flagStatus+" Village "+village, Toast.LENGTH_SHORT).show();
                //  Toast.makeText(this, "Farmer Name "+fmname, Toast.LENGTH_SHORT).show();

                // do what ever you want here


                // List<FarmerDetailsPojo> farmerDetailsPojoList= dbHelper.getFarmerDetails();
                List<FarmerDetailsPojo> FarmerDetailsPojoList=dbHelper.getFarmerDetails(id);
                for (int i=0;i<FarmerDetailsPojoList.size();i++)
                {
                    FarmerDetailsPojo farmerDetailsPojo=FarmerDetailsPojoList.get(i);
                    String farmerName=farmerDetailsPojo.getFarmerName();
                    Toast.makeText(instance, "Farmer Name "+farmerName, Toast.LENGTH_SHORT).show();
                }

                List<RetailerDetailsPojo> retailerList=dbHelper.getRetailerDetails(id);

                for (int i=0;i<retailerList.size();i++)
                {
                    RetailerDetailsPojo  retailerDetailsPojo=retailerList.get(i);

                    //Toast.makeText(instance, "Firm Name "+retailerDetailsPojo.getFirmName(), Toast.LENGTH_SHORT).show();
                }

            } while (z.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        z.close();
    }







    // Sending data to Web services Starts

  //  public  void sendingDataToWebService()

//Sending data to Web services Ends




    public static IndividualFarmerContactMainActivity getInstance() {
        return instance;
    }


    public void getDataSetFromCloudAndLocalSaveBackGroundService() {
        // Toast.makeText(this, "INSERTING DATA START :", Toast.LENGTH_SHORT).show();


        // String   url2 = "https://tvsfinal.herokuapp.com/service/dataSetMaster/E92M75GV9kUQnNURUWg4r9hge5";






       // String url2="https://tvsfinal.herokuapp.com/rest/service/dataSetMaster/E92M75GV9kUQnNURUWg4r9hge5";
       String url2="https://taikinys.kaizenmax.com/rest/service/dataSetMaster/E92M75GV9kUQnQQNURUWg4r9hge5";


        requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url2,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try{
                            // Loop through the array elements
                            int count=0;
                            //   Toast.makeText(IndividualFarmerContactActivity.this, "LENGTH OF FETCHED DATA :"+response.length(), Toast.LENGTH_SHORT).show();


                            dbHelper.deleteAllEntriesOfDataSetMaster(); //deleting data set entries before inserting



                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject dataSetMaster = response.getJSONObject(i);

                                // Get the current student (json object) data
                                // String clientName = student.getString("clientName");
                                //  String dataSetTitle = student.getString("dataSetTitle");
                                //  String createdOn = student.getString("createdOn");






                                String dataSetTitle = dataSetMaster.getString("dataSetTitle");
                                String dataSetDescription = dataSetMaster.getString("dataSetDescription");
                                String element1 = dataSetMaster.getString("element1");
                                String element2 = dataSetMaster.getString("element2");
                                String element3 = dataSetMaster.getString("element3");
                                String element4 = dataSetMaster.getString("element4");
                                String number1=dataSetMaster.getString("number1");
                                String number2=dataSetMaster.getString("number2");
                                String text1=dataSetMaster.getString("text1");
                                String text2=dataSetMaster.getString("text2");
                                String date1=dataSetMaster.getString("date1");
                                String date2=dataSetMaster.getString("date2");
                                String status=dataSetMaster.getString("status");
                                String createdOn=dataSetMaster.getString("createdOn");
                                String createdBy=dataSetMaster.getString("createdBy");
                                String id=dataSetMaster.getString("id");
                                String clientName=dataSetMaster.getString("clientName");
                                //    Toast.makeText(IndividualFarmerContactActivity.this, "Index Retrieved "+i, Toast.LENGTH_SHORT).show();

                                try {
                                    if(dataSetTitle.equals("ClientDistrictRetailer"))
                                    {
                                        String faOfficeDistrict=dbHelper.getFaOfficeDistrict();
                                        //  Toast.makeText(IndividualFarmerContactActivity.this, "FA DISTRICT OFFICE : "+faOfficeDistrict, Toast.LENGTH_SHORT).show();
                                        if(element1.equals(faOfficeDistrict))
                                        {
                                            dbHelper.insertDataSetMaster(dataSetTitle, dataSetDescription, element1, element2, element3, element4
                                                    , number1, number2, text1, text2, date1, date2,
                                                    status, new Date(),
                                                    createdBy, clientName);
                                            count++;
                                        }
                                    }
                                    else {
                                        dbHelper.insertDataSetMaster(dataSetTitle, dataSetDescription, element1, element2, element3, element4
                                                , number1, number2, text1, text2, date1.toString(), date2.toString(),
                                                status, new Date(),
                                                createdBy, clientName);
                                        count++;
                                        //  Toast.makeText(IndividualFarmerContactActivity.this, "Index INSERT "+i+" status "+status, Toast.LENGTH_SHORT).show();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                //   Toast.makeText(IndividualFarmerContactActivity.this, "Values Index"+i+" clientName: "+clientName+" dataSetTitle: "+dataSetTitle+" Created on: "+createdOn, Toast.LENGTH_SHORT).show();

                                // Display the formatted json data in text view
                                //   mTextView.append(firstName +" " + lastName +"\nAge : " + age);
                                // mTextView.append("\n\n");
                            }






                            // Toast.makeText(IndividualFarmerContactActivity.this, "COUNT "+count, Toast.LENGTH_SHORT).show();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Toast.makeText(IndividualFarmerContactMainActivity.this, "ERROR "+error.networkResponse.statusCode, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);


    }








    public void localTestingFaMaster()
    {
        Cursor cursor=dbHelper.getFaMasterAllEntries();

        Integer id=null;
        if (cursor != null && cursor.moveToFirst()) {
            do {

                String firstName=cursor.getString(cursor.getColumnIndex(FaMasterPojo.FAMASTER_COLUMN_FA_FIRSTNAME));
                String lastName=cursor.getString(cursor.getColumnIndex(FaMasterPojo.FAMASTER_COLUMN_FA_LASTNAME));
                String clientName=cursor.getString(cursor.getColumnIndex(FaMasterPojo.FAMASTER_COLUMN_FA_CLIENTNAME));


                String faOfficeTerritory=cursor.getString(cursor.getColumnIndex(FaMasterPojo.FAMASTER_COLUMN_FA_OFFICE_TERRITORY));

                String faValidityFrom=cursor.getString(cursor.getColumnIndex(FaMasterPojo.FAMASTER_COLUMN_FA_VALIDITY_FROM));

                String faValidityTo=cursor.getString(cursor.getColumnIndex(FaMasterPojo.FAMASTER_COLUMN_FA_VALIDITY_TO));
                String faDistrict=cursor.getString(cursor.getColumnIndex(FaMasterPojo.FAMASTER_COLUMN_FA_OFFICE_DISTRICT));
                String headquarter=cursor.getString(cursor.getColumnIndex(FaMasterPojo.FAMASTER_COLUMN_FA_HEADQUARTER));
                String faOfficeRegionalOffice=cursor.getString(cursor.getColumnIndex(FaMasterPojo.FAMASTER_COLUMN_FA_OFFICE_REGIONAL_OFFICE));
                String faOfficeState=cursor.getString(cursor.getColumnIndex(FaMasterPojo.FAMASTER_COLUMN_FA_OFFICE_STATE));
                String status=cursor.getString(cursor.getColumnIndex(FaMasterPojo.FAMASTER_COLUMN_FA_STATUS));


           /*     Toast.makeText(this, "firstName "+firstName
                        +" lastName "+lastName
                        +" clientName "+clientName
                        +" faOfficeTerritory "+faOfficeTerritory
                        +" faValidityFrom "+faValidityFrom
                        +" faValidityTo "+faValidityTo
                        +" faDistrict "+faDistrict
                        +" headquarter "+headquarter
                        +" faOfficeRegionalOffice "+faOfficeRegionalOffice
                        +" faOfficeState "+faOfficeState
                        +" status "+status, Toast.LENGTH_SHORT).show();
                        */



            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        cursor.close();
    }



    public void usersLocalTest()
    {
        Cursor cursor=dbHelper.getUsersAllEntries();


        if (cursor != null && cursor.moveToFirst()) {
            do {

                String userName=cursor.getString(cursor.getColumnIndex(UsersPojo.USERS_COLUMN_USERNAME));
                String password=cursor.getString(cursor.getColumnIndex(UsersPojo.USERS_COLUMN_PASSWORD));
                String otp=cursor.getString(cursor.getColumnIndex(UsersPojo.USERS_COLUMN_OTP));
                String status=cursor.getString(cursor.getColumnIndex(UsersPojo.USERS_COLUMN_STATUS));

      /*      Toast.makeText(this, "UserName "+userName
                    +" password "+password
                    +" otp "+otp
                    +" status "+status, Toast.LENGTH_SHORT).show(); */



            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        cursor.close();
    }

    public void villagesLocalTest()
    {
        Cursor cursor=dbHelper.getVillagesAllEntries();


        if (cursor != null && cursor.moveToFirst()) {
            do {

                String villageName=cursor.getString(cursor.getColumnIndex(VillagesPojo.VILLAGES_COLUMN_VILLAGE_NAME));

                //  Toast.makeText(this, "VillageName "+villageName, Toast.LENGTH_SHORT).show();



            } while (cursor.moveToNext());

            // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        }
        // Toast.makeText(this, "TOTAL FETCHED COUNT "+x, Toast.LENGTH_SHORT).show();
        cursor.close();
    }

    public void setAdapterOnFirmName(final View v) {
        List<String> firmNameList = new ArrayList<String>();

        // firmNameList = dbHelper.getFirmNameList(); //to be removed

        individualFarmerContactActivityPresenterInterface = new IndividualFarmerContactActivityPresenter();

        try {
            firmNameList = individualFarmerContactActivityPresenterInterface.getFirmNameList();
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
                    propName = individualFarmerContactActivityPresenterInterface.getPropName(firmName.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String retailerMobile= null;
                try {
                    retailerMobile = individualFarmerContactActivityPresenterInterface.getRetailerMobile(firmName.getText().toString());
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
        getMenuInflater().inflate(R.menu.individual_farmer_contact_main, menu);
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


            Intent intent = new Intent(IndividualFarmerContactMainActivity.this, IndividualFarmerContactMainActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();


        } else if (id == R.id.fm) {
            Intent intent = new Intent(IndividualFarmerContactMainActivity.this, FarmerMeetingActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.fd) {

            Intent intent = new Intent(IndividualFarmerContactMainActivity.this, FieldDayActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.dv) {
            Intent intent = new Intent(IndividualFarmerContactMainActivity.this, DiagnosticVisitActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        }

        else if(id==R.id.mc){
            Intent intent = new Intent(IndividualFarmerContactMainActivity.this, MandiCampaignActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.demol3) {
            Intent intent = new Intent(IndividualFarmerContactMainActivity.this, DemoL3Activity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.demol3_progress) {
            Intent intent = new Intent(IndividualFarmerContactMainActivity.this, DemoL3_InProgressActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
