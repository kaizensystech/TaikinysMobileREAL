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
import com.kaizenmax.taikinys_icl.pojo.MandiCampaignPojo;
import com.kaizenmax.taikinys_icl.pojo.RetailerDetailsPojo;
import com.kaizenmax.taikinys_icl.view.IndividualFarmerContactMainActivity;
import com.kaizenmax.taikinys_icl.view.MandiCampaignActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MandiCampaignDataPushNetworkOperation implements MandiCampaignDataPushNetworkOperationInterface {

    MobileDatabase dbHelper =new MobileDatabase(MandiCampaignActivity.getInstance());

    @Override
    public void sendingMcDataToWebService() throws Exception {
        {
            //  Toast.makeText(instance, "RAM RAM", Toast.LENGTH_SHORT).show();


            ArrayList<Integer> uploadedIds = new ArrayList<Integer>();
            String url = "https://tvsfinal.herokuapp.com/rest/service/mandiCampaign";
            // String url = "https://raghav-rest-api.herokuapp.com/rest/json/raghav/post";

            RequestQueue requestQueue = Volley.newRequestQueue(MandiCampaignActivity.getInstance());

            final JSONArray postparams = new JSONArray();
            try {
                JSONObject object = new JSONObject();
                Cursor cursor = dbHelper.getMandiCampaignEntriesToBeUploaded();

                if (cursor != null && cursor.getCount() != 0) {
                    // Toast.makeText(instance, "Uploading Data", Toast.LENGTH_SHORT).show();
                    if (cursor != null && cursor.moveToFirst()) {
                        do {

                            String dateOfActivity = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_DATE_OF_ACTIVITY));
                            String mandiName = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_MANDI_NAME));
                            String faOfficeDistrict = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_DISTRICT));
                            String cropCategory = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CROP_CATEGORY));
                            String cropFocus = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CROP_FOCUS));
                            String expenses = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_EXPENSES));
                            String meetingPurpose = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CAMPAIGN_PURPOSE));
                            String activitySummary = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_ACTIVITY_SUMMARY));
                            String createdOn = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CREATED_ON));
                            String createdby = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CREATED_BY));
                            String clientName = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_CLIENT_NAME));
                            String uploadFlagStatus = cursor.getString(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_UPLOAD_FLAG));
                            Integer mc_id = cursor.getInt(cursor.getColumnIndex(MandiCampaignPojo.MANDICAMPAIGN_COLUMN_ID));





                            //  Toast.makeText(IndividualFarmerContactActivity.this, "District "+faOfficeDistrict, Toast.LENGTH_SHORT).show();

                            //  Toast.makeText(this, "TOAST "+id+" Cursor Size "+cursor.getCount(), Toast.LENGTH_SHORT).show();
                            object = new JSONObject();

                           // Toast.makeText(MandiCampaignActivity.getInstance(), "MANDI NAME "+mandiName +" District "+faOfficeDistrict, Toast.LENGTH_SHORT).show();
                            object.put("dateOfActivity", dateOfActivity+" 00:00:00");
                            object.put("mandiName", mandiName);
                            object.put("district", faOfficeDistrict );
                            object.put("cropCategory", cropCategory);
                            object.put("cropName", cropFocus);
                            object.put("campaignPurpose", meetingPurpose);
                            object.put("activitySummary", activitySummary );
                            object.put("createdBy", createdby);
                            object.put("expenses", expenses);
                            object.put("createdOn", createdOn);
                            object.put("clientName",clientName);


                            List<RetailerDetailsPojo> retailerDetailsPojoList = dbHelper.getRetailerDetailsForMandiCampaignEntries(mc_id);


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
                            List<String> productsDetailsList = dbHelper.getProductDetailsForMandiCampaignEntries(mc_id);


                            JSONArray productsDetails_jsonArray = new JSONArray();
                            for (int i = 0; i < productsDetailsList.size(); i++) {

                                JSONObject productObject = new JSONObject();

                                String productName = productsDetailsList.get(i);
                                productObject.put("productName", productName);



                                productsDetails_jsonArray.put(productObject);
                            }

                            object.put("productDetailsRests", productsDetails_jsonArray);








                            //Attachment Details by vinod on 09/04/2019

                            List<byte[]> attachmentDetails = dbHelper.getAttachmentsForMandiCampaignEntries(mc_id);


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

                            uploadedIds.add(mc_id);
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


                    dbHelper.updateMobileFlagForMandiCampaignEntries(uploadedIds);

                    // Toast.makeText(this, "ENDINg DBCRETESTATUS "+dbCreateStatus+" DB INSERT STATUS "+dbInsertSTatus+" DB RETrieve STatus "+dbRetriveSTatus, Toast.LENGTH_SHORT).show();


                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
