package com.kaizenmax.taikinys_icl.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.kaizenmax.taikinys_icl.model.MobileDatabase;
import com.kaizenmax.taikinys_icl.pojo.FarmerDetailsPojo;
import com.kaizenmax.taikinys_icl.pojo.PromoFarmerMeetingPojo;
import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;
import com.kaizenmax.taikinys_icl.util.SharedPreferenceUtil;
import com.kaizenmax.taikinys_icl.view.IndividualFarmerContactMainActivity;
import com.kaizenmax.taikinys_icl.view.PastRecordActivity;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IndividualFarmerContactActivityPresenter implements IndividualFarmerContactActivityPresenterInterface {
    MobileDatabase dbHelper =new MobileDatabase(IndividualFarmerContactMainActivity.getInstance());

    @Override
    public List<String> getFirmNameList() throws Exception {
      List<String> firmList= dbHelper.getFirmNameList();

        return firmList;
    }

    @Override
    public String getPropName(String firmName) throws Exception {

        String propName= dbHelper.getPropName(firmName);

        return propName;
    }

    @Override
    public String getRetailerMobile(String firmName) throws Exception {

        String retailerMobile=dbHelper.getRetailerMobile(firmName);

        return retailerMobile;
    }

    @Override
    public String getClientName() throws Exception {
        String clientName = dbHelper.getClientName();

        return clientName;
    }

    @Override
    public String getStateName() throws Exception {
        String faOfficeState=dbHelper.getStateName();
        return faOfficeState;
    }

    @Override
    public List<String> getProductFocusList(String clientName, String faOfficeState) {
       List<String> productlist=dbHelper.getProductFocusList(clientName,faOfficeState);
        return productlist;
    }

    @Override
    public List<String> getVillageList() throws Exception {
        List<String> villageList=dbHelper.getVillageList();
        return villageList;
    }

    @Override
    public List<String> getCropCategories() throws Exception {
        List<String> cropCategories=dbHelper.getCropCategories();
        return  cropCategories;
    }

    @Override
    public List<String> getCropFocus(String selectedCropCategory) throws Exception {
       List<String > cropFocusList=   dbHelper.getCropFocus(selectedCropCategory);
        return cropFocusList;
    }

    @Override
    public String getFaOfficeDistrict() throws Exception {
        String faOfficeDistrict=dbHelper.getFaOfficeDistrict();
        return faOfficeDistrict;
    }

    @Override
    public String getMobileFromSharedpreference() throws Exception {

        String createdby="";
       SharedPreferences sharedpreferences = IndividualFarmerContactMainActivity.getInstance().getSharedPreferences(SharedPreferenceUtil.PREFERENCES, Context.MODE_PRIVATE);




        if(sharedpreferences!=null)
        {
            if(sharedpreferences.getString(SharedPreferenceUtil.MOBILEKEY,null)==null)
            {


            }
            else
            {

                createdby=sharedpreferences.getString(SharedPreferenceUtil.MOBILEKEY,null);
                //   Toast.makeText(IndividualFarmerContactActivity.this, "mob "+createdby+" createdOn "+createdOn
                //         +" district "+faOfficeDistrict
                //       +"clientName "+clientName, Toast.LENGTH_SHORT).show();
            }

        }
        return createdby;
    }

    @Override
    public void insertIFCdata(String ifc, String dateOfActivity_entered, String villageName_entered,
                              String cropFocus_entered, String cropCategory_entered,
                              String farmerName_entered, String farmerMobile_entered,
                              String landAcres_entered, String expenses_entered,
                              String observations_entered, String uploadFlagStatus,
                              List<RetailerDetailsPojo> retailerDetailsPojoList,
                              List<String> selectedProductsList, String createdOn_string,
                              String createdby, String clientName, String faOfficeDistrict) throws Exception {

        dbHelper.insertIFCdata("IFC", dateOfActivity_entered, villageName_entered,
                cropFocus_entered, cropCategory_entered, farmerName_entered, farmerMobile_entered, landAcres_entered,
                expenses_entered, observations_entered, uploadFlagStatus, retailerDetailsPojoList, selectedProductsList,
                createdOn_string,createdby,clientName,faOfficeDistrict);

    }

    @Override
    public void sendingDataToWebService() throws Exception {
        {
            //  Toast.makeText(instance, "RAM RAM", Toast.LENGTH_SHORT).show();


            ArrayList<Integer> uploadedIds = new ArrayList<Integer>();
            String url = "https://taikinys.kaizenmax.com/rest/service/meeting";
            // String url = "https://raghav-rest-api.herokuapp.com/rest/json/raghav/post";
            RequestQueue requestQueue = Volley.newRequestQueue(IndividualFarmerContactMainActivity.getInstance());

            final JSONArray postparams = new JSONArray();
            try {
                JSONObject object = new JSONObject();
                Cursor cursor = dbHelper.getPromoEntriesToBeUploaded();

                if (cursor != null && cursor.getCount() != 0) {
                    // Toast.makeText(instance, "Uploading Data", Toast.LENGTH_SHORT).show();
                    if (cursor != null && cursor.moveToFirst()) {
                        do {
                            String chooseActivity = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CHOOSE_ACTIVITY));
                            String dateOfActivity = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DATE_OF_ACTIVITY));
                            String cropCategory = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_CATEGORY));
                            String cropFocus = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_FOCUS));
                            //     String focusProduct=cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CR))
                            String observations = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_OBSERVATIONS));
                            //String farmerName=cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.far))
                            String village = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_VILLAGE));
                            String expenses = cursor.getString(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_EXPENSES));
                            Integer id = cursor.getInt(cursor.getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_ID));

                            String createdOn=cursor.getString(cursor
                                    .getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CREATED_ON));
                            String createdby=cursor.getString(cursor
                                    .getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CREATED_BY));
                            String clientName=cursor.getString(cursor
                                    .getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CLIENT_NAME));
                            String faOfficeDistrict=cursor.getString(cursor
                                    .getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DISTRICT));

                            String numberOfFarmers = cursor.getString(cursor
                                    .getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_NUMBER_OF_FARMER));

                            String meetingPurpose = cursor.getString(cursor
                                    .getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_MEETING_PURPOSE));

                            String cropStage = cursor.getString(cursor
                            .getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_CROP_STAGE));

                            String instructionsDose = cursor.getString(cursor
                            .getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_INSTRUCTIONS_DOSE));

                            String problemCategory = cursor.getString(cursor
                               .getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_PROBLEM_CATEGORY));

                            String problemSubCategory = cursor.getString(cursor
                            .getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_PROBLEM_SUB_CATEGORY));

                            String description = cursor.getString(cursor
                                    .getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_DESCRIPTION));

                            String recommendation = cursor.getString(cursor
                                    .getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_RECOMMENDATION));


                            String nextVisitDate = cursor.getString(cursor
                                     .getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_NEXT_FIELD_VISIT_DATE));

                           String problemDescription = cursor.getString(cursor
                                    .getColumnIndex(PromoFarmerMeetingPojo.PROMOFARMERMEETING_COLUMN_PROBLEM_DESCRIPTION));




                            //  Toast.makeText(IndividualFarmerContactActivity.this, "District "+faOfficeDistrict, Toast.LENGTH_SHORT).show();

                            //  Toast.makeText(this, "TOAST "+id+" Cursor Size "+cursor.getCount(), Toast.LENGTH_SHORT).show();
                            object = new JSONObject();

                            object.put("chooseActivity", chooseActivity);
                            object.put("dateOfActivity", dateOfActivity+" 00:00:00");
                            object.put("cropCategory", cropCategory);
                            object.put("cropFocus", cropFocus);
                            object.put("observations", observations);

                            object.put("village", village);
                            object.put("expenses", expenses);

                            object.put("expenses", expenses);

                          //
                            object.put("numberOfFarmers", numberOfFarmers);

                            object.put("meetingPurpose", meetingPurpose);
                            object.put("cropStage", cropStage);
                            object.put("instructionsDose",instructionsDose);

                            object.put("problemCategory", problemCategory);
                            object.put("problemSubCategory", problemSubCategory);
                            object.put("description", description);
                            object.put("recommendation",recommendation);
                            object.put("nextVisitDate", nextVisitDate+" 00:00:00");
                            object.put("problemDescription", problemDescription);

                            List<RetailerDetailsPojo> retailerDetailsPojoList = dbHelper.getRetailerDetails(id);


                            JSONArray retailerDetails_jsonArray = new JSONArray();
                            for (int i = 0; i < retailerDetailsPojoList.size(); i++) {

                                JSONObject retailerObject = new JSONObject();

                                RetailerDetailsPojo retailerDetailsPojo = retailerDetailsPojoList.get(i);
                                retailerObject.put("firmName", retailerDetailsPojo.getFirmName());
                                retailerObject.put("propreitorName", retailerDetailsPojo.getPropName());
                                retailerObject.put("retailerMobile", retailerDetailsPojo.getRetailerMobile());


                                retailerDetails_jsonArray.put(retailerObject);
                            }

                            object.put("retailerDetailsRests", retailerDetails_jsonArray);




                            //PRODUCT LIST
                            List<String> productsDetailsList = dbHelper.getProductDetails(id);


                            JSONArray productsDetails_jsonArray = new JSONArray();
                            for (int i = 0; i < productsDetailsList.size(); i++) {

                                JSONObject productObject = new JSONObject();

                                String productName = productsDetailsList.get(i);
                                productObject.put("productName", productName);



                                productsDetails_jsonArray.put(productObject);
                            }

                            object.put("productDetailsRests", productsDetails_jsonArray);





                            List<FarmerDetailsPojo>  farmerDetailsPojoList = dbHelper.getFarmerDetails(id);


                            JSONArray farmerDetails_jsonArray = new JSONArray();
                            for (int i = 0; i < farmerDetailsPojoList.size(); i++) {

                                JSONObject farmerObject = new JSONObject();

                                FarmerDetailsPojo farmerDetailsPojo = farmerDetailsPojoList.get(i);
                                farmerObject.put("farmerName", farmerDetailsPojo.getFarmerName());
                                farmerObject.put("mobileNumber", farmerDetailsPojo.getFarmerMobile());
                                farmerObject.put("acres", farmerDetailsPojo.getFarmerLand());


                                farmerDetails_jsonArray.put(farmerObject);
                            }

                            object.put("farmerDetailsRests", farmerDetails_jsonArray);

                            //Attachment Details by vinod on 26/08/2019

                            List<byte[]> attachmentDetails = dbHelper.getAttachments(id);


                            JSONArray attachmentDetails_jsonArray = new JSONArray();
                            for (int i = 0; i < attachmentDetails.size(); i++) {

                                JSONObject attachmentObject = new JSONObject();

                                // RetailerDetailsPojo retailerDetailsPojo = retailerDetailsPojoList.get(i);

                                byte[] fileByteArray = attachmentDetails.get(i);

                                String encodedString = Base64.encodeToString(fileByteArray, Base64.DEFAULT);


                                attachmentObject.put("atttachment",encodedString );



                                attachmentDetails_jsonArray.put(attachmentObject);
                            }

                            object.put("attachmentDetailsRests", attachmentDetails_jsonArray);



                            object.put("createdBy",createdby);
                            object.put("clientName",clientName);
                            object.put("district",faOfficeDistrict);
                            object.put("createdOn",createdOn);


              /*      Calendar calendar=Calendar.getInstance();
                    Date d = calendar.getTime();


                    SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");

                    Date date = null;
                   String gg = new Date().toString();

                        date = format.parse(gg);
System.out.println("date hh-"+date);*/



                            //  object.put("createdOn",date.toString());

                            //    Toast.makeText(this, "New Date "+new Date(), Toast.LENGTH_SHORT).show();



                            postparams.put(object);

                            uploadedIds.add(id);
                        } while (cursor.moveToNext());

                    }

                    cursor.close();


                    JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.POST,
                            url, postparams,
                            new Response.Listener() {
                                @Override
                                public void onResponse(Object response) {
                                    //  Toast.makeText(IndividualFarmerContactActivity.this, "URL "+url, Toast.LENGTH_SHORT).show();
                                    //  Toast.makeText(IndividualFarmerContactActivity.this, "Success", Toast.LENGTH_SHORT).show();


                                    //
                                    // Toast.makeText(IndividualFarmerContactActivity.this, "RESPONSE "+response, Toast.LENGTH_SHORT).show();
                                }

                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    //Failure Callback

                                    Toast.makeText(IndividualFarmerContactMainActivity.getInstance(), "Error " + error, Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Adding the request to the queue along with a unique string tag

                    requestQueue.add(jsonObjReq);


                    dbHelper.updateMobileFlagPromoFarmerMeetingEntries(uploadedIds);

                    // Toast.makeText(this, "ENDINg DBCRETESTATUS "+dbCreateStatus+" DB INSERT STATUS "+dbInsertSTatus+" DB RETrieve STatus "+dbRetriveSTatus, Toast.LENGTH_SHORT).show();


                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    public void test(){
       // Toast.makeText(PastRecordActivity.getInstance(), "Testing", Toast.LENGTH_SHORT).show();
    }
}
