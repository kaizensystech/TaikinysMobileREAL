package java.com.kaizenmax.taikinys_icl.presenter;

import android.content.Context;
import android.content.SharedPreferences;


import com.kaizenmax.taikinys_icl.model.MobileDatabase;
import com.kaizenmax.taikinys_icl.model.OtpVerificationActivityNetworkOperationInterface;
import com.kaizenmax.taikinys_icl.model.OtpVerificationNetworkOperation;
import com.kaizenmax.taikinys_icl.presenter.OtpVerificationActivityPresenterInterface;
import com.kaizenmax.taikinys_icl.util.SharedPreferenceUtil;
import com.kaizenmax.taikinys_icl.view.OtpVerificationActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OtpVerificationActivityPresenter implements OtpVerificationActivityPresenterInterface {


OtpVerificationActivityNetworkOperationInterface otpVerificationActivityNetworkOperationInterface;


    @Override
    public void sendingMobileandOtpToWebservice(String mobileNumber, String otp) throws Exception {

        otpVerificationActivityNetworkOperationInterface= new OtpVerificationNetworkOperation();
        otpVerificationActivityNetworkOperationInterface.otpVerification(mobileNumber,otp);

    }

    @Override
    public void successResponseMethod(String responsResult, Object response) throws Exception {
        OtpVerificationActivity.getInstance().successResponseMethod(responsResult,response);
    }


    @Override
    public void errorResponseMethod(String result) throws Exception {
        OtpVerificationActivity.getInstance().errorResponseMethod(result);
    }

    @Override
    public void savingMobileAndOtpInSharedPreference(String enteredMobileNumber, String enteredOtp) {

        SharedPreferences sharedpreferences=OtpVerificationActivity.getInstance().getSharedPreferences(SharedPreferenceUtil.PREFERENCES, Context.MODE_PRIVATE);;
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(SharedPreferenceUtil.MOBILEKEY,enteredMobileNumber );
        editor.putString(SharedPreferenceUtil.OTPKEY,enteredOtp );
        editor.commit();
    }

    @Override
    public void savingFaMasterInLocalDatabase(Object response) throws Exception {

        MobileDatabase dbHelper=new MobileDatabase(OtpVerificationActivity.getInstance());

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
            String password=jsonObject.getString("password");
            String otp=jsonObject.getString("otp");
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


            dbHelper.insertFAMasterData(faFirstName,faLastName,
                    faOfficeTerritory,clientName,faValidityFrom,
                    faValidityTo,faDistrict,headquarter,faOfficeRegionalOffice,
                    faOfficeState,status
            );

            dbHelper.insertUsersData(userName,otp,password,userStatus);

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}
