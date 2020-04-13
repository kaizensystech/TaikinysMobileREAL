package java.com.kaizenmax.taikinys_icl.model;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kaizenmax.taikinys_icl.model.MasterDateSynchronizationNetworkOperationInterface;
import com.kaizenmax.taikinys_icl.model.MobileDatabase;
import com.kaizenmax.taikinys_icl.presenter.OtpVerificationActivityPresenter;
import com.kaizenmax.taikinys_icl.view.IndividualFarmerContactMainActivity;
import com.kaizenmax.taikinys_icl.view.OtpVerificationActivity;
import com.kaizenmax.taikinys_icl.view.WelcomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MasterDateSynchronizationNetworkOperation implements MasterDateSynchronizationNetworkOperationInterface {


    @Override
    public void faSpecificDataSynchronization(String mobileNumber) throws Exception {



            //  MobileDatabase dbHelper =new MobileDatabase(IndividualFarmerContactMainActivity.getInstance());
            //dbHelper.deleteAllEntriesOfFAMaster();
            //dbHelper.deleteAllEntriesOfVillages();


            RequestQueue requestQueue = Volley.newRequestQueue(IndividualFarmerContactMainActivity.getInstance());
            JsonObjectRequest jsonObjReq=null;

            // String url = "https://tvsfinal.herokuapp.com/faces/rest/service/validateUser";

            //String url="https://tvsfinal.herokuapp.com/rest/service/userFA_MasterSync";
            String url="https://taikinys.kaizenmax.com/rest/service/userFA_MasterSync" ;


            final JSONObject postparams = new JSONObject();
            try {
                postparams.put("mobileNumber", mobileNumber);

            } catch (JSONException e) {
                e.printStackTrace();
            }




            jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    url, postparams,
                    new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {
                            //  Toast.makeText(IndividualFarmerContactActivity.this, "URL "+url, Toast.LENGTH_SHORT).show();
                            // Toast.makeText(OtpVerificationActivity.this, "Success "+response, Toast.LENGTH_SHORT).show();


                            com.kaizenmax.taikinys_icl.model.MobileDatabase dbHelper=new MobileDatabase(IndividualFarmerContactMainActivity.getInstance());

                            JSONObject jsonObject= (JSONObject) response;

                            try {
                                String faFirstName=jsonObject.getString("faFirstName");
                                String faLastName=jsonObject.getString("faLastName");
                                String faOfficeTerritory=jsonObject.getString("fAOfficeTerritory");
                                String clientName=jsonObject.getString("clientName");
                                String faValidityFrom=jsonObject.getString("faValidityFrom");
                               String faValidityTo=jsonObject.getString("faValidityTo");
                               String faDistrict=jsonObject.getString("fAOfficeLocation");
                               String headquarter=jsonObject.getString("headquarter");
                                String faOfficeRegionalOffice=jsonObject.getString("fAOfficeRegionalOffice");
                                String faOfficeState=jsonObject.getString("fAOfficeState");
                                String status=jsonObject.getString("status");


                                String userName=jsonObject.getString("userName");
                               // String password=jsonObject.getString("password");
                               // String otp=jsonObject.getString("otp");
                                String userStatus=jsonObject.getString("userStatus");

                                JSONArray jsonArray=null;


                                String j2=jsonObject.getString("villageName");

//jsonArray=j2.optJSONArray("villageName");

                                if(j2.equals("null"))
                                {
                                    // Toast.makeText(OtpVerificationActivity.this, "obj "+j2, Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    jsonArray =jsonObject.getJSONArray("villageName");
                                }


                                // jsonArray =jsonObject.getJSONArray("villageName");
                             /*   Toast.makeText(OtpVerificationActivity.this, "jsonArray "+jsonArray, Toast.LENGTH_SHORT)
                                        .show(); */


                                if(jsonArray!=null) {
                                    dbHelper.deleteAllEntriesOfVillages();
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        // Get current json object
                                        String villageName = jsonArray.getString(i);

                                        dbHelper.insertVillagesData(villageName);
                                    }

                                }



                           /*    Toast.makeText(OtpVerificationActivity.this, "faFirstName "+faFirstName
                                        +"faLastName "+faLastName
                                        +"faOfficeTerritory "+faOfficeTerritory
                                        +"clientName "+clientName
                                        +"faValidityFrom "+faValidityFrom
                                        +"faValidityTo "+faValidityTo
                                        +"faDistrict "+faDistrict
                                        +"headquarter "+headquarter
                                        +"faOfficeRegionalOffice "+faOfficeRegionalOffice
                                        +"faOfficeState "+faOfficeState
                                        +"status "+status

                                        , Toast.LENGTH_SHORT).show(); */

                            /*    Toast.makeText(OtpVerificationActivity.this, "userName "+userName
                                        +" password "+password
                                        +" otp "+otp
                                        +" userStatus "+userStatus , Toast.LENGTH_SHORT).show();
                                        */


                            /*    Toast.makeText(OtpVerificationActivity.this, "District "+faDistrict
                                        +" Client "+clientName, Toast.LENGTH_SHORT).show(); */


                             /*   dbHelper.insertFAMasterData(faFirstName,faLastName,
                                        faOfficeTerritory,clientName,faValidityFrom,
                                        faValidityTo,faDistrict,headquarter,faOfficeRegionalOffice,
                                        faOfficeState,status
                                ); */

                            //    dbHelper.insertUsersData(userName,otp,password,userStatus);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }









                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Failure Callback

                            // Toast.makeText(OtpVerificationActivity.this, "Error "+error.networkResponse.statusCode, Toast.LENGTH_SHORT).show();






                        }
                    });
            //Adding the request to the queue along with a unique string tag

            requestQueue.add(jsonObjReq);




    }
}
