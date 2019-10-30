package com.kaizenmax.taikinys_icl.model;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kaizenmax.taikinys_icl.presenter.WelcomeActivityPresenter;
import com.kaizenmax.taikinys_icl.presenter.WelcomeActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.view.WelcomeActivity;


import org.json.JSONException;
import org.json.JSONObject;

public class SignUpNetworkOperation implements SignUpNetworkOperationInterface {
String result="";

WelcomeActivityPresenterInterface welcomeActivityPresenterInterface;





    @Override
    public void signUp(String enteredMobileNumber) throws Exception {

        RequestQueue requestQueue = Volley.newRequestQueue(WelcomeActivity.getInstance());
        JsonObjectRequest jsonObjReq=null;

        // String url = "https://tvsfinal.herokuapp.com/faces/rest/service/validateUser";

        String url="https://tvsfinal.herokuapp.com/rest/service/validateUser";

        final JSONObject postparams = new JSONObject();
        try {
            postparams.put("mobileNumber", enteredMobileNumber);
            //   postparams.put("password", "7838106926");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        welcomeActivityPresenterInterface=new WelcomeActivityPresenter();
        jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, postparams,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        //  Toast.makeText(IndividualFarmerContactActivity.this, "URL "+url, Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(WelcomeActivity.this, "Success "+response, Toast.LENGTH_SHORT).show();

                        result="SUCCESS";

                        try {
                           welcomeActivityPresenterInterface.responseMethod(result);


                           // WelcomeActivity.getInstance().responseMethod(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Failure Callback

                        //   Toast.makeText(WelcomeActivity.this, "Error "+error, Toast.LENGTH_SHORT).show();

                        if(error!=null && error.networkResponse!=null) {
                            // Toast.makeText(WelcomeActivity.getInstance(), "Error: " + error.networkResponse.statusCode, Toast.LENGTH_SHORT).show();

                            if((error.networkResponse.statusCode)==401) {
                                result = "UNAUTHORIZED";
                                // Toast.makeText(WelcomeActivity.getInstance(), "M hu 401", Toast.LENGTH_SHORT).show();


                                try {
                                    welcomeActivityPresenterInterface.responseMethod(result);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                            else {


                                result = "NETWORKERROR";
                                try {
                                    welcomeActivityPresenterInterface.responseMethod(result);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }


                        }

                        else {
                            result = "NETWORKERROR";

                            try {
                                welcomeActivityPresenterInterface.responseMethod(result);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }

                    }
                });
        //Adding the request to the queue along with a unique string tag

        requestQueue.add(jsonObjReq);


        // Toast.makeText(WelcomeActivity.getInstance(), "Final Result "+result, Toast.LENGTH_SHORT).show();


    }


}
