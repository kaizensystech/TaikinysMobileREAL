package com.kaizenmax.taikinys_icl.view;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.content.Intent;
import android.renderscript.RenderScript;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.kaizenmax.taikinys_icl.R;
import com.kaizenmax.taikinys_icl.model.MobileDatabase;
import com.kaizenmax.taikinys_icl.pojo.DemoL3Pojo;
import com.kaizenmax.taikinys_icl.pojo.FarmerDetailsPojo;
import com.kaizenmax.taikinys_icl.pojo.MandiCampaignPojo;
import com.kaizenmax.taikinys_icl.pojo.PastRecord;
import com.kaizenmax.taikinys_icl.pojo.ProductsDetailsPojo;
import com.kaizenmax.taikinys_icl.pojo.PromoFarmerMeetingPojo;
import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;
import com.kaizenmax.taikinys_icl.presenter.IndividualFarmerContactActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.PastRecordActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.PastRecordActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.util.RSBlurProcessor;
import com.kaizenmax.taikinys_icl.util.PastRecordAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.kaizenmax.taikinys_icl.util.BitMapUtil.getBitmapFromView;

public class PastRecordActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    PastRecordActivityPresenterInterface pastRecordActivityPresenterInterface;
    static PastRecordActivity instance;


    private List<PastRecord> pastRecordList = new ArrayList<>();
    private Map<String,PastRecord> pastRecordMap = new HashMap<>();
    private int counter = 0;
    private RecyclerView recyclerView;
    private LinearLayoutCompat mainLinearLayoutCompat;
    private PastRecordAdapter mAdapter;


    MobileDatabase dbHelper;
    PastRecord pastRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_record);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mainLinearLayoutCompat = (LinearLayoutCompat) findViewById(R.id.content_past_record);

        mAdapter = new PastRecordAdapter(pastRecordList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        //pastRecordActivityPresenterInterface = new PastRecordActivityPresenter();
        //preparePastRecordData();

        try {
            //pastRecordList.add(new PastRecord(1,"FM","5 Nov 19","Bajra", "Bilaspur","Nashik","Ramesh Kumar","9708747474", "1"));
            getAllPromoEntries();
            getAllMandiCampaignEntries();
            getAllDemoL3Entries();

            Collections.sort(pastRecordList, new Comparator<PastRecord>() { //to sort an list according to one property of it
                @Override
                public int compare(PastRecord u1, PastRecord u2) {

                    Date d1 = u1.getDateofActivity();
                    Date d2 = u2.getDateofActivity();

                    int compareDate = d2.compareTo(d1);
                    //Toast.makeText(PastRecordActivity.getInstance(), "Date 1 - "+s1+"Date 2 - "+s2+"Result = "+compareDate, Toast.LENGTH_SHORT).show();

                    if (compareDate != 0)
                    {
                        return compareDate;
                    }
                    int compareID = u2.getId().compareTo(u1.getId());
                    return compareID;
                }
            });

            //Toast.makeText(this, pastRecordList.size() + " records found ", Toast.LENGTH_SHORT).show();
            mAdapter.notifyDataSetChanged();
        }
        catch (Exception ex){

        }
        instance = this;
    }

    public static PastRecordActivity getInstance(){
        return instance;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.ifc) {
            // IFC Activity clicked by user


            Intent intent = new Intent(PastRecordActivity.this, IndividualFarmerContactMainActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();


        } else if (id == R.id.fm) {
            Intent intent = new Intent(PastRecordActivity.this, FarmerMeetingActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.fd) {

            Intent intent = new Intent(PastRecordActivity.this, FieldDayActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        } else if (id == R.id.dv) {
            Intent intent = new Intent(PastRecordActivity.this, DiagnosticVisitActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();

        }

        else if(id==R.id.mc){
            Intent intent = new Intent(PastRecordActivity.this, MandiCampaignActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.demol3) {
            Intent intent = new Intent(PastRecordActivity.this, DemoL3Activity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.demol3_progress) {
            Intent intent = new Intent(PastRecordActivity.this, DemoL3_InProgressActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }

        else if (id == R.id.pastRecord) {
            Intent intent = new Intent(PastRecordActivity.this, PastRecordActivity.class);
            //pgsBar.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void getAllPromoEntries() throws Exception{

       //  Toast.makeText(this, "Initiating db query", Toast.LENGTH_SHORT).show();
        try {
            dbHelper = new MobileDatabase(this);
            final Cursor cursor = dbHelper.getAllPromoEntriesPastRecord();
        //    Toast.makeText(this, "DB query records - " + cursor.getCount(), Toast.LENGTH_SHORT).show();
            if (cursor.getCount() == 0) {
                // getDataSetFromCloudAndLocalSave();
            } else {

                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        Integer id = cursor.getInt(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_ID));
                        String choose_activity = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CHOOSE_ACTIVITY));
                        String date_of_activity = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DATE_OF_ACTIVITY));
                        String village = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_VILLAGE));
                        String district = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DISTRICT));
                        String crop_category = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_CATEGORY));
                        String crop_category_focus = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_FOCUS));
                        String meeting_highlight = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DESCRIPTION));
                        String observation = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_OBSERVATIONS));
                        String expenses = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_EXPENSES));



                        String firmname = "";
                        String retailername = "";
                        String retailermobile = "";

                        List<RetailerDetailsPojo> retailerList=dbHelper.getRetailerDetails(id);
                        if(retailerList.size()!=0)
                        for (int i=0;i<1;i++)
                        {
                            RetailerDetailsPojo retailerDetailsPojo=retailerList.get(i);
                            firmname = retailerDetailsPojo.getFirmName();
                            retailername = retailerDetailsPojo.getPropName();
                            retailermobile = retailerDetailsPojo.getRetailerMobile();
                            //Toast.makeText(instance, "Firm Name "+retailerDetailsPojo.getFirmName(), Toast.LENGTH_SHORT).show();
                        }

                        List<String> productList=dbHelper.getProductDetails(id);
                        StringBuilder products = new StringBuilder();
                        int j = 0;
                        if(productList.size()>0){
                            for(j=0; j< productList.size()-1; j++){
                                products.append(productList.get(j));
                                products.append(", ");
                            }
                            products.append(productList.get(j));
                        }


                        String farmerName = "";
                        String farmermobile = "";
                        String farmerland = "";

                        // List<FarmerDetailsPojo> farmerDetailsPojoList= dbHelper.getFarmerDetails();
                        List<FarmerDetailsPojo> FarmerDetailsPojoList = dbHelper.getFarmerDetails(id);
                        if(FarmerDetailsPojoList.size()!=0)
                            for (int i = 0; i < FarmerDetailsPojoList.size(); i++) {
                                FarmerDetailsPojo farmerDetailsPojo = FarmerDetailsPojoList.get(i);
                                farmerName = farmerDetailsPojo.getFarmerName();
                                farmermobile = farmerDetailsPojo.getFarmerMobile();
                                farmerland = farmerDetailsPojo.getFarmerLand();

                                counter++;
                                pastRecord = new PastRecord();
                                pastRecord.setId(id);
                                pastRecord.setChoose_activity(choose_activity);
                                pastRecord.setDate_of_activity(formateDateVew(date_of_activity));
                                pastRecord.setDateofActivity(convertToDate(date_of_activity));
                                pastRecord.setVillage(village);
                                pastRecord.setDistrict(district);
                                pastRecord.setCrop_category(crop_category);
                                pastRecord.setVillage_formatted(formatStringView(village));
                                pastRecord.setDistrict_formatted(formatStringView(district));
                                pastRecord.setCrop_category_formatted(formatStringView(crop_category));
                                pastRecord.setFarmerName(farmerName);
                                pastRecord.setFarmermobile(farmermobile);
                                pastRecord.setFarmermobile_formatted(farmermobile);
                                pastRecord.setCrop_category_focus(crop_category_focus);

                                if (choose_activity.equals("IFC") || choose_activity.equals("FM") || choose_activity.equals("FD"))
                                    pastRecord.setFocus_product(products.toString());
                                if (choose_activity.equals("DV"))
                                    pastRecord.setFocus_product("Not Applicable");

                                if (choose_activity.equals("IFC") || choose_activity.equals("FD"))
                                    pastRecord.setObservation(observation);
                                if (choose_activity.equals("FM"))
                                    pastRecord.setObservation(meeting_highlight);
                                if (choose_activity.equals("DV"))
                                    pastRecord.setObservation("Not Applicable");


                                pastRecord.setExpenses(expenses);

                                if (choose_activity.equals("IFC") || choose_activity.equals("FM") || choose_activity.equals("FD"))
                                    pastRecord.setFarmerland(farmerland);
                                if (choose_activity.equals("DV"))
                                    pastRecord.setFarmerland("Not Applicable");


                                pastRecord.setRetailerfirm(firmname);
                                pastRecord.setRetailername(retailername);
                                pastRecord.setRetailerphn(retailermobile);

                                pastRecord.setHeader_1(choose_activity+"    Farmer: ");
                                pastRecord.setHeader_2("Ph:");
                                pastRecord.setHeader_3("Crop:");
                                pastRecord.setHeader_4("Vill:");
                                pastRecord.setHeader_5("Dist:");

                                pastRecord.setRow_id(Integer.toString(counter));

                                if(isLastTwoMonthData(date_of_activity)) {
                                    pastRecordList.add(pastRecord);
                                    pastRecordMap.put(Integer.toString(counter), pastRecord);
                                }

                                //Toast.makeText(this, " Product count " + pastRecord.toString() + " No. of Retailers " + retailerList.size(), Toast.LENGTH_SHORT).show();

                            }
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }

        }
        catch(Exception ex){
            Log.e("Query DB", "Past Record failing!!!!", ex);

        }

     //   Toast.makeText(this, "List size - " + pastRecordList.size(), Toast.LENGTH_SHORT).show();
     //   return pastRecordList1;

    }

    public void getAllMandiCampaignEntries() throws Exception{
        try {
            dbHelper = new MobileDatabase(this);
            final Cursor cursor = dbHelper.getAllEntriesMandiCampaignPastRecord();
            if (cursor.getCount() == 0) {
                // getDataSetFromCloudAndLocalSave();
            } else {

                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        Integer id = cursor.getInt(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_ID));
                        String choose_activity = "MC";
                        String date_of_activity = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_DATE_OF_ACTIVITY));
                        String crop_category = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CROP_CATEGORY));
                        String name = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_MANDI_NAME));
                        String district = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_DISTRICT));
                        String crop_focus = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CROP_FOCUS));
                        String expenses = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_EXPENSES));



                        List<String> productList=dbHelper.getProductDetailsForMandiCampaignEntries(id);
                        StringBuilder products = new StringBuilder();
                        int j = 0;
                        if(productList.size()>0){
                            for(j=0; j< productList.size()-1; j++){
                                products.append(productList.get(j));
                                products.append(", ");
                            }
                            products.append(productList.get(j));
                        }

                        String firmname = "";
                        String retailername = "";
                        String retailermobile = "";

                        List<RetailerDetailsPojo> retailerList=dbHelper.getRetailerDetailsForMandiCampaignEntries(id);
                        if(retailerList.size()!=0)
                            for (int i=0;i<1;i++)
                            {
                                RetailerDetailsPojo retailerDetailsPojo=retailerList.get(i);
                                firmname = retailerDetailsPojo.getFirmName();
                                retailername = retailerDetailsPojo.getPropName();
                                retailermobile = retailerDetailsPojo.getRetailerMobile();
                                //Toast.makeText(instance, "Firm Name "+retailerDetailsPojo.getFirmName(), Toast.LENGTH_SHORT).show();
                            }

                        counter++;
                        pastRecord = new PastRecord();
                        pastRecord.setId(id);
                        pastRecord.setChoose_activity(choose_activity);
                        pastRecord.setFarmerName(name);
                        pastRecord.setDate_of_activity(formateDateVew(date_of_activity));
                        pastRecord.setDateofActivity(convertToDate(date_of_activity));
                        pastRecord.setCrop_category(crop_category);
                        pastRecord.setDistrict(district);
                        pastRecord.setCrop_category_formatted(formatStringView(crop_category));
                        pastRecord.setDistrict_formatted(formatStringView(district));
                        pastRecord.setCrop_category_focus(crop_focus);
                        pastRecord.setFocus_product(products.toString());
                        pastRecord.setObservation("Not Applicable");
                        pastRecord.setFarmermobile("Not Applicable");
                        pastRecord.setFarmermobile_formatted("");
                        pastRecord.setVillage_formatted("");
                        pastRecord.setVillage("Not Applicable");
                        pastRecord.setFarmerland("Not Applicable");
                        pastRecord.setExpenses(expenses);
                        pastRecord.setRetailerfirm(firmname);
                        pastRecord.setRetailername(retailername);
                        pastRecord.setRetailerphn(retailermobile);
                        pastRecord.setHeader_1(choose_activity+"   Mandi: ");
                        pastRecord.setHeader_2("     ");
                        pastRecord.setHeader_3("Crop:");
                        pastRecord.setHeader_4("     ");
                        pastRecord.setHeader_5("Dist:");

                        pastRecord.setRow_id(Integer.toString(counter));

                        if(isLastTwoMonthData(date_of_activity)) {
                            pastRecordList.add(pastRecord);
                            pastRecordMap.put(Integer.toString(counter), pastRecord);
                        }
                       // Toast.makeText(this, "Past Record - " + pastRecord.toString() + " id " + id, Toast.LENGTH_SHORT).show();

                    } while (cursor.moveToNext()) ;
                }
            }
        }
        catch(Exception e){

        }
    }

    public void getAllDemoL3Entries() throws Exception{
        try {
            dbHelper = new MobileDatabase(this);
            final Cursor cursor = dbHelper.getAllEntriesFromDemoL3PastRecord();
            if (cursor.getCount() == 0) {
                // getDataSetFromCloudAndLocalSave();
            } else {
                int x = 0;
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        Integer id = cursor.getInt(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_ID));
                        String choose_activity = "DL3";
                        String date_of_activity = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_ACTIVITY));
                        String crop_category = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_CROP_CATEGORY));
                        String district = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DISTRICT));
                        String village = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_VILLAGE));
                        String expenses = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_EXPENSES));
                        String crop_focus = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_CROP_FOCUS));
                        String demol3_serialid = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID));

                        List<String> productList=dbHelper.getProductDetailsForDEMOL3Entries(demol3_serialid);
                        StringBuilder products = new StringBuilder();
                        int j = 0;
                        if(productList.size()>0){
                            for(j=0; j< productList.size()-1; j++){
                                products.append(productList.get(j));
                                products.append(", ");
                            }
                            products.append(productList.get(j));
                        }

                        String firmname = "";
                        String retailername = "";
                        String retailermobile = "";

                        List<RetailerDetailsPojo> retailerList=dbHelper.getRetailerDetailsForDEMOL3Entries(demol3_serialid);
                        if(retailerList.size()!=0)
                            for (int i=0;i<1;i++)
                            {
                                RetailerDetailsPojo retailerDetailsPojo=retailerList.get(i);
                                firmname = retailerDetailsPojo.getFirmName();
                                retailername = retailerDetailsPojo.getPropName();
                                retailermobile = retailerDetailsPojo.getRetailerMobile();
                                //Toast.makeText(instance, "Firm Name "+retailerDetailsPojo.getFirmName(), Toast.LENGTH_SHORT).show();
                            }

                        String farmerName = "";
                        String farmerMobile = "";
                        String farmerLand = "";

                        FarmerDetailsPojo farmerDetailsPojo = dbHelper.getFarmerDetailsForDemoL3(demol3_serialid);
                            if(farmerDetailsPojo != null) {
                                farmerName = farmerDetailsPojo.getFarmerName();
                                farmerMobile = farmerDetailsPojo.getFarmerMobile();
                                farmerLand = farmerDetailsPojo.getFarmerLand();
                            }


                        counter++;
                        pastRecord = new PastRecord();
                        pastRecord.setId(id);
                        pastRecord.setChoose_activity(choose_activity);
                        pastRecord.setFarmerName(farmerName);
                        pastRecord.setDate_of_activity(formateDateVew(date_of_activity));
                        pastRecord.setDateofActivity(convertToDate(date_of_activity));
                        pastRecord.setCrop_category(crop_category);
                        pastRecord.setDistrict(district);
                        pastRecord.setCrop_category_formatted(formatStringView(crop_category));
                        pastRecord.setDistrict_formatted(formatStringView(district));
                        pastRecord.setCrop_category_focus(crop_focus);
                        pastRecord.setFocus_product(products.toString());
                        pastRecord.setObservation("Not Applicable");
                        pastRecord.setFarmermobile(farmerMobile);
                        pastRecord.setFarmermobile_formatted(farmerMobile);
                        pastRecord.setVillage_formatted(formatStringView(village));
                        pastRecord.setVillage(village);
                        pastRecord.setFarmerland(farmerLand);
                        pastRecord.setExpenses(expenses);
                        pastRecord.setRetailerfirm(firmname);
                        pastRecord.setRetailername(retailername);
                        pastRecord.setRetailerphn(retailermobile);
                        pastRecord.setHeader_1(choose_activity+"   Farmer: ");
                        pastRecord.setHeader_2("Ph:");
                        pastRecord.setHeader_3("Crop:");
                        pastRecord.setHeader_4("Vill:");
                        pastRecord.setHeader_5("Dist:");

                        pastRecord.setRow_id(Integer.toString(counter));

                        if(isLastTwoMonthData(date_of_activity)) {
                            pastRecordList.add(pastRecord);
                            pastRecordMap.put(Integer.toString(counter), pastRecord);
                        }
                       // Toast.makeText(this, "Past Record - " + pastRecord.toString() + " id " + id, Toast.LENGTH_SHORT).show();

                    } while (cursor.moveToNext()) ;
            }
        }
        }
        catch(Exception e){

        }
    }

    public void onClick(View view) {
        String row_id = ((TextView) view.findViewById(R.id.row_id)).getText().toString();
       // Toast.makeText(this, row_id + " was clicked!", Toast.LENGTH_SHORT).show();

        View popupView = LayoutInflater.from(PastRecordActivity.this).inflate(R.layout.content_past_record_single_record, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);



        ImageButton btnDismiss =  popupView.findViewById(R.id.ib_close);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });

        /*

        // Blurring the background activity
        RenderScript renderScript = RenderScript.create(PastRecordActivity.this);
        Bitmap blurBackGround = new RSBlurProcessor(renderScript).blur(getBitmapFromView(recyclerView), 15, 1);
        View blurView = LayoutInflater.from(PastRecordActivity.this).inflate(R.layout.blur_background, null);
        final PopupWindow blurWindow = new PopupWindow(blurView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        ImageView img = blurView.findViewById(R.id.bgImgView);
        img.setImageBitmap(blurBackGround);
        blurWindow.showAtLocation(blurView, Gravity.CENTER, 0, 0);
        */

        //disabling click event on background activity
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 30);

       // String act = "21 Nov 2019, 9.00 pm";
       // TextInputEditText tv = popupView.findViewById(R.id.dateOfActivity);
       // tv.setText(act);

        setPopupView(popupView,row_id);

    }

    public void setPopupView(View popupView,String row_id){

        PastRecord pastRecord = pastRecordMap.get(row_id);

        TextView activity = popupView.findViewById(R.id.activity);
        String chooseActivity = pastRecord.getChoose_activity();
        if(chooseActivity.equals("IFC")){
            activity.setText("Individual Farmer Contact");
        }
        else if(chooseActivity.equals("FM")){
            activity.setText("Farmer Meeting");
        }
        else if(chooseActivity.equals("DV")){
            activity.setText("Diagnostic Visit ");
        }
        else if(chooseActivity.equals("FD")){
            activity.setText("Field Day");
        }
        else if(chooseActivity.equals("DL3")){
            activity.setText("Demo L3");
        }
        else if(chooseActivity.equals("MC")){
            activity.setText("Mandi Campaign");
        }



        TextInputEditText dateOfActivity = popupView.findViewById(R.id.dateOfActivity);
        dateOfActivity.setText(formateDateVew2(pastRecord.getDate_of_activity()));

        TextInputEditText cropCategory = popupView.findViewById(R.id.cropCategory);
        cropCategory.setText(pastRecord.getCrop_category());

        TextInputEditText cropCategoryfocus = popupView.findViewById(R.id.cropCategoryfocus);
        cropCategoryfocus.setText(pastRecord.getCrop_category_focus());

        TextInputEditText product = popupView.findViewById(R.id.product);
        product.setText(pastRecord.getFocus_product());

        TextInputEditText observation = popupView.findViewById(R.id.observation);
        observation.setText(pastRecord.getObservation());

        TextInputEditText farmername = popupView.findViewById(R.id.farmername);
        farmername.setText(pastRecord.getFarmerName());

        TextInputEditText village = popupView.findViewById(R.id.village);
        village.setText(pastRecord.getVillage());

        TextInputEditText phno = popupView.findViewById(R.id.phno);
        phno.setText(pastRecord.getFarmermobile());

        TextInputEditText landacres = popupView.findViewById(R.id.landacres);
        landacres.setText(pastRecord.getFarmerland());

        TextInputEditText expenses = popupView.findViewById(R.id.expenses);
        expenses.setText(pastRecord.getExpenses());

        TextInputEditText retailerfirm = popupView.findViewById(R.id.retailerfirm);
        retailerfirm.setText(pastRecord.getRetailerfirm());

        TextInputEditText retailername = popupView.findViewById(R.id.retailername);
        retailername.setText(pastRecord.getRetailername());

        TextInputEditText retailerMobile = popupView.findViewById(R.id.retailerMobile);
        retailerMobile.setText(pastRecord.getRetailerphn());
    }

    public String formatStringView(String str){
        if(str.length()>10){
            str = str.substring(0,10);
        }
        return str;
    }

    public String formateDateVew(String date){
        Date formateddate  = null;
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat format2 = new SimpleDateFormat("dd MMM yy");
        try {
            formateddate = format1.parse(date);
        }
        catch(Exception ex){
            return date;
        }
        return format2.format(formateddate);
    }

    public String formateDateVew2(String date){
        Date formateddate  = null;
        SimpleDateFormat format1 = new SimpleDateFormat("dd MMM yy");
        SimpleDateFormat format2 = new SimpleDateFormat("dd MMMM yyyy");
        try {
            formateddate = format1.parse(date);
        }
        catch(Exception ex){
            return date;
        }
        return format2.format(formateddate);
    }

    public Date convertToDate(String date_of_activity){
        Date formateddate  = null;
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            formateddate = format1.parse(date_of_activity);
            formateddate = format2.parse(format2.format(formateddate));
        }
        catch(Exception ex){

        }
        return formateddate;
    }

    public Boolean isLastTwoMonthData(String date_of_activity){

        Boolean flag = false;

        Calendar cal=Calendar.getInstance();
        Date endDate=cal.getTime();
        cal.add(Calendar.MONTH, -2);
        Date startDate = cal.getTime();

        Date formateddate  = null;
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        //SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            formateddate = format1.parse(date_of_activity);
        }
        catch(Exception ex){

        }
        String createdOn_String =  format1.format(formateddate);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String startDate_String = sdf.format(startDate);
        String endDate_String = sdf.format(endDate);

        Date createdOn;
        try {
            createdOn = sdf.parse(createdOn_String);
            startDate = sdf.parse(startDate_String);
            endDate = sdf.parse(endDate_String);


            if ((createdOn.after(startDate) || createdOn.equals(startDate))
                    && (createdOn.before(endDate) || createdOn.equals(endDate))) {
                flag = true;

            }
            else {
                flag = false;
            }
        }
        catch (Exception ex){

        }
       // Toast.makeText(this, flag + "   Try 2 - " + createdOn + " - " + startDate + " - " + endDate, Toast.LENGTH_SHORT).show();
        return flag;
    }
}


