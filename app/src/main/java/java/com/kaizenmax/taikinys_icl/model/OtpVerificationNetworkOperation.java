package java.com.kaizenmax.taikinys_icl.model;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kaizenmax.taikinys_icl.model.OtpVerificationActivityNetworkOperationInterface;
import com.kaizenmax.taikinys_icl.presenter.OtpVerificationActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.OtpVerificationActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.view.OtpVerificationActivity;
import com.kaizenmax.taikinys_icl.view.WelcomeActivity;


import org.json.JSONException;
import org.json.JSONObject;

public class OtpVerificationNetworkOperation implements OtpVerificationActivityNetworkOperationInterface {

    String result="";

    OtpVerificationActivityPresenterInterface otpVerificationActivityPresenterInterface;
    @Override
    public void otpVerification(String mobileNumber, String otp) throws Exception {


      //  MobileDatabase dbHelper =new MobileDatabase(IndividualFarmerContactMainActivity.getInstance());
        //dbHelper.deleteAllEntriesOfFAMaster();
        //dbHelper.deleteAllEntriesOfVillages();


        RequestQueue requestQueue = Volley.newRequestQueue(WelcomeActivity.getInstance());
        JsonObjectRequest jsonObjReq=null;

        // String url = "https://tvsfinal.herokuapp.com/faces/rest/service/validateUser";

        //String url="https://tvsfinal.herokuapp.com/rest/service/userFA_Master";
        String url="https://taikinys.kaizenmax.com/rest/service/userFA_Master";



        final JSONObject postparams = new JSONObject();
        try {
            postparams.put("userName", mobileNumber);
            postparams.put("otp", otp);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        otpVerificationActivityPresenterInterface=new OtpVerificationActivityPresenter();

        jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, postparams,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        //  Toast.makeText(IndividualFarmerContactActivity.this, "URL "+url, Toast.LENGTH_SHORT).show();
                        // Toast.makeText(OtpVerificationActivity.this, "Success "+response, Toast.LENGTH_SHORT).show();

                        result="SUCCESS";


                        try {
                            if(response!=null)
                            otpVerificationActivityPresenterInterface.successResponseMethod(result,response);
                            else
                                Toast.makeText(OtpVerificationActivity.getInstance(), "Internal network error. Please try after sometime.", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Failure Callback

                       // Toast.makeText(OtpVerificationActivity.this, "Error "+error.networkResponse.statusCode, Toast.LENGTH_SHORT).show();

                        if(error!=null && error.networkResponse!=null) {
                            // Toast.makeText(WelcomeActivity.getInstance(), "Error: " + error.networkResponse.statusCode, Toast.LENGTH_SHORT).show();

                            if((error.networkResponse.statusCode)==401) {
                                result = "UNAUTHORIZED";
                                // Toast.makeText(WelcomeActivity.getInstance(), "M hu 401", Toast.LENGTH_SHORT).show();


                                try {
                                    otpVerificationActivityPresenterInterface.errorResponseMethod(result);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                            else {


                                result = "NETWORKERROR";
                                try {
                                    otpVerificationActivityPresenterInterface.errorResponseMethod(result);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }


                        }

                        else {
                            result = "NETWORKERROR";

                            try {
                                otpVerificationActivityPresenterInterface.errorResponseMethod(result);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }


                    }
                });
        //Adding the request to the queue along with a unique string tag

        requestQueue.add(jsonObjReq);



    }




}
