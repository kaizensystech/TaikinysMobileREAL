package com.kaizenmax.taikinys_icl.model;

import android.database.Cursor;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.kaizenmax.taikinys_icl.pojo.DemoL3Pojo;
import com.kaizenmax.taikinys_icl.pojo.FarmerDetailsPojo;
import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;
import com.kaizenmax.taikinys_icl.view.DemoL3Activity;
import com.kaizenmax.taikinys_icl.view.IndividualFarmerContactMainActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DemoL3DataPushNetworkOperation implements DemoL3DataPushNetworkOperationInterface {

    MobileDatabase dbHelper =new MobileDatabase(DemoL3Activity.getInstance());
    RequestQueue requestQueue;

    @Override
    public void sendingDemoL3DataToWebService() throws Exception {
        {
            //  Toast.makeText(instance, "RAM RAM", Toast.LENGTH_SHORT).show();


            ArrayList<String> uploadedIds = new ArrayList<String>();
            String url = "https://tvsfinal.herokuapp.com/rest/service/saveDemoL3Details";
            // String url = "https://raghav-rest-api.herokuapp.com/rest/json/raghav/post";

            RequestQueue requestQueue = Volley.newRequestQueue(IndividualFarmerContactMainActivity.getInstance());

            final JSONArray postparams = new JSONArray();
            try {
                JSONObject object = new JSONObject();
                String demol3SerialId = null;
                String dateOfActivity= null;
                String village = null;
                String cropCategory = null;
                String cropFocus = null;;
                String demoPurpose = null;
                String acres = null;
                String dose = null;
                String interim = null;
                String yield = null;
                String expenses = null;
                String dateOfYield = null;
                String status = null;
                String createdOn = null;
                String createdBy= null;
                String clientName = null;
                String modifyDate = null;
                String dateofprotocol = null;
                String dateofexecution = null;
                String dateofinterim = null;
                String district = null;
                String uploadflag = null;
                String permanentdemol3serialid = null;
                String stage = null;




                Cursor cursor = dbHelper.getDemoL3Data_EntriesToBeUploaded();

                if (cursor != null && cursor.getCount() != 0) {
                    // Toast.makeText(instance, "Uploading Data", Toast.LENGTH_SHORT).show();
                    if (cursor != null && cursor.moveToFirst()) {
                        do {



                             stage = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_STAGE));
                             demol3SerialId = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DEMOL3_SERIAL_ID)); //this is tempId






                              dateOfActivity= cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_ACTIVITY));
                              village = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_VILLAGE));
                              cropCategory = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_CROP_CATEGORY));
                              cropFocus = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_CROP_FOCUS));;
                              demoPurpose = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DEMO_PURPOSE));;
                              acres = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_ACRES));
                              dose = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DOSE));;
                              interim = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_INTERIM));;
                              yield = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_YIELD));;
                              expenses = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_EXPENSES));;
                              dateOfYield = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DATEOFYIELD));;
                              status = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_STATUS));;
                              createdOn = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_CREATED_ON));;
                              createdBy= cursor.getString(cursor.getColumnIndex(DemoL3Pojo. DEMOL3_COLUMN_CREATED_BY));;
                              clientName = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_CLIENT_NAME));;
                              modifyDate = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_MODIFY_DATE));;
                              dateofprotocol = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_PROTOCOL));;
                              dateofexecution = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_EXECUTION));;
                              dateofinterim = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DATE_OF_INTERIM));;
                              district = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_DISTRICT));;
                              uploadflag = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_UPLOAD_FLAG));;
                              permanentdemol3serialid = cursor.getString(cursor.getColumnIndex(DemoL3Pojo.DEMOL3_COLUMN_PERMANENT_DEMOL3_SERIAL_ID));;


                            Toast.makeText(DemoL3Activity.getInstance(), "DATE OF INTERM "+dateofinterim +"INTERIM  "+interim, Toast.LENGTH_SHORT).show();

                            object.put("tempDemoL3Id",demol3SerialId );
                            object.put("dateOfActivity", dateOfActivity+" 00:00:00");
                            object.put("village", village);
                            object.put("createdOn",createdOn);
                            object.put("modifyDate",modifyDate);
                            object.put("district", district);
                            object.put("clientName",clientName);
                            object.put("stage",stage);
                            object.put("createdBy", createdBy);
                            object.put("permanentSerialId", permanentdemol3serialid);



                            List<byte[]> attachmentDetails = dbHelper.getAttachmentsForDEMOL3Entries(demol3SerialId);


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



                            List<FarmerDetailsPojo>  farmerDetailsPojoList = dbHelper.getDemoL3FarmerDetails(demol3SerialId);


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






                            //PRODUCT LIST
                            List<String> productsDetailsList = dbHelper.getProductDetailsForDEMOL3Entries(demol3SerialId);


                            JSONArray productsDetails_jsonArray = new JSONArray();
                            for (int i = 0; i < productsDetailsList.size(); i++) {

                                JSONObject productObject = new JSONObject();

                                String productName = productsDetailsList.get(i);
                                productObject.put("productName", productName);



                                productsDetails_jsonArray.put(productObject);
                            }

                            object.put("productDetailsRests", productsDetails_jsonArray);







                            List<RetailerDetailsPojo> retailerDetailsPojoList = dbHelper.getRetailerDetailsListForDemoL3(demol3SerialId);

                            JSONArray retailerDetails_jsonArray = new JSONArray();

                            //JSONArray retailerDetails_jsonArray = new JSONArray();
                            for (int i = 0; i < retailerDetailsPojoList.size(); i++) {

                                JSONObject retailerObject = new JSONObject();

                                RetailerDetailsPojo retailerDetailsPojo = retailerDetailsPojoList.get(i);
                                retailerObject.put("firmName", retailerDetailsPojo.getFirmName());
                                retailerObject.put("propreitorName", retailerDetailsPojo.getPropName());
                                retailerObject.put("retailerMobile", retailerDetailsPojo.getRetailerMobile());


                                retailerDetails_jsonArray.put(retailerObject);
                            }

                            object.put("retailerDetailsRests", retailerDetails_jsonArray);



                            object.put("cropCategory", cropCategory);
                            object.put("cropFocus", cropFocus);
                            object.put("demoPurpose", demoPurpose);
                            object.put("dateOfProtocol", dateofprotocol+" 00:00:00");



                            object.put("dose", dose);
                            object.put("dateOfExecution", dateofexecution+" 00:00:00");
                            object.put("acres",acres);


                            object.put("interim", interim);
                            object.put("dateOfInterim",dateofinterim+" 00:00:00");


                            object.put("lastDateOfActivity",dateOfYield+" 00:00:00");

                            object.put("yield", yield);
                            object.put("expense",expenses );










                            postparams.put(object);
                            uploadedIds.add(demol3SerialId);
                        } while (cursor.moveToNext());

                    }

                    cursor.close();







                            //  Toast.makeText(IndividualFarmerContactActivity.this, "District "+faOfficeDistrict, Toast.LENGTH_SHORT).show();


                    final String finalCreatedBy = createdBy;
                    JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.POST,
                            url, postparams,
                            new Response.Listener() {
                                @Override
                                public void onResponse(Object response) {
                                    //  Toast.makeText(IndividualFarmerContactActivity.this, "URL "+url, Toast.LENGTH_SHORT).show();
                                    //  Toast.makeText(IndividualFarmerContactActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                   // Toast.makeText(DemoL3Activity.getInstance(), "SUCCESS UPLOADING DONE", Toast.LENGTH_SHORT).show();

                                    try {
                                        gettingDemoL3DataFromWebService(finalCreatedBy);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


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


                  dbHelper.updateMobileFlagForDEMOL3Entries(uploadedIds);

                    // Toast.makeText(this, "ENDINg DBCRETESTATUS "+dbCreateStatus+" DB INSERT STATUS "+dbInsertSTatus+" DB RETrieve STatus "+dbRetriveSTatus, Toast.LENGTH_SHORT).show();


                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void gettingDemoL3DataFromWebService(String mobileNumber) throws Exception {
        // Toast.makeText(this, "INSERTING DATA START :", Toast.LENGTH_SHORT).show();


        // String   url2 = "https://tvsfinal.herokuapp.com/service/dataSetMaster/E92M75GV9kUQnNURUWg4r9hge5";






        //String url21="https://tvsfinal.herokuapp.com/rest/service/dataSetMaster/E92M75GV9kUQnNURUWg4r9hge5";

        String url2 = "https://tvsfinal.herokuapp.com/rest/service/sendDemoL3detailsFA_Wise/"+mobileNumber;

        requestQueue = Volley.newRequestQueue(IndividualFarmerContactMainActivity.getInstance());
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


                           // dbHelper.deleteAllEntriesOfDataSetMaster(); //deleting data set entries before inserting



                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject demoL3Object = response.getJSONObject(i);

                                // Get the current student (json object) data
                                // String clientName = student.getString("clientName");
                                //  String dataSetTitle = student.getString("dataSetTitle");
                                //  String createdOn = student.getString("createdOn");

                       /*         object.put("tempDemoL3Id",demol3SerialId );
                                object.put("dateOfActivity", dateOfActivity+" 00:00:00");
                                object.put("village", village);
                                object.put("createdOn",createdOn);
                                object.put("modifyDate",modifyDate);
                                object.put("district", district);
                                object.put("clientName",clientName);
                                object.put("stage",stage);
                                object.put("createdBy", createdBy);
                                object.put("attachmentDetailsRests", attachmentDetails_jsonArray);
                                object.put("farmerDetailsRests", farmerDetails_jsonArray);
                                object.put("productDetailsRests", productsDetails_jsonArray);
                                object.put("retailerDetailsRests", retailerDetails_jsonArray);
                                object.put("cropCategory", cropCategory);
                                object.put("cropFocus", cropFocus);
                                object.put("demoPurpose", demoPurpose);
                                object.put("dateOfProtocol", dateofprotocol+" 00:00:00");


                                object.put("dose", dose);

                                object.put("dateOfExecution", dateofexecution);
                                object.put("acres",acres);


                                object.put("interim", interim);
                                object.put("dateOfInterim",dateofinterim+" 00:00:00");


                                object.put("lastDateOfActivity",dateOfYield+" 00:00:00");
                                object.put("yield", yield);
                                object.put("expense",expenses ); */



                                String tempDemoL3Id = demoL3Object.getString("tempDemoL3Id");
                                String permanentId = null;
                                try {

                                    if (demoL3Object.getString("demoL3SerialId") != null && !demoL3Object.getString("demoL3SerialId").equals("null")
                                            && !demoL3Object.getString("demoL3SerialId").equals(""))
                                        permanentId = demoL3Object.getString("demoL3SerialId");

                                }


                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                                String dateOfActivity = demoL3Object.getString("dateOfActivity");
                                String village = demoL3Object.getString("village");
                                String createdOn = demoL3Object.getString("createdOn");
                                String modifyDate = demoL3Object.getString("modifyDate");
                                String district = demoL3Object.getString("district");
                                String clientName = demoL3Object.getString("clientName");
                                String stage = demoL3Object.getString("stage");
                                String createdBy = demoL3Object.getString("createdBy");
                                try {
                                    JSONArray attachmentDetailsRests = (JSONArray) demoL3Object.get("attachmentDetailsRests");
                                    JSONArray farmerDetailsRests = (JSONArray) demoL3Object.get("farmerDetailsRests");
                                    JSONArray productDetailsRests = (JSONArray) demoL3Object.get("productDetailsRests");
                                    JSONArray retailerDetailsRests = (JSONArray) demoL3Object.get("retailerDetailsRests");

                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }

                                String cropCategory = demoL3Object.getString("cropCategory");
                                String cropFocus = demoL3Object.getString("cropFocus");
                                String demoPurpose = demoL3Object.getString("demoPurpose");
                                String dateOfProtocol = demoL3Object.getString("dateOfProtocol");
                                String dose = demoL3Object.getString("dose");
                                String dateOfExecution = demoL3Object.getString("dateOfExecution");
                                String acres = demoL3Object.getString("acres");
                                String interim = demoL3Object.getString("interim");
                                String dateofinterim = demoL3Object.getString("dateOfInterim");
                                String lastDateOfActivity = demoL3Object.getString("lastDateOfActivity");
                                String yield = demoL3Object.getString("yield");
                                String expenses = demoL3Object.getString("expense");






                                           dbHelper.updateDemoL3_PERMANENT_ID(tempDemoL3Id,permanentId);








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
                        Toast.makeText(DemoL3Activity.getInstance(), "ERROR "+error.networkResponse.statusCode, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);


    }
}
