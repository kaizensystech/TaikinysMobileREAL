package com.kaizenmax.taikinys_icl.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.kaizenmax.taikinys_icl.model.SignUpNetworkOperation;
import com.kaizenmax.taikinys_icl.model.SignUpNetworkOperationInterface;
import com.kaizenmax.taikinys_icl.util.SharedPreferenceUtil;
import com.kaizenmax.taikinys_icl.view.WelcomeActivity;


public class WelcomeActivityPresenter implements WelcomeActivityPresenterInterface {
    SharedPreferences sharedpreferences;
    SignUpNetworkOperationInterface signUpNetworkOperationInterface;

    @Override
    public String getMobileNumberFromSharedPreference() throws Exception {
        String mobile=null;
        sharedpreferences= WelcomeActivity.getInstance().getSharedPreferences(SharedPreferenceUtil.PREFERENCES, Context.MODE_PRIVATE);
        if(sharedpreferences!=null)
        {
            mobile=   sharedpreferences.getString(SharedPreferenceUtil.MOBILEKEY,null);
        }
        return mobile;

    }

    @Override
    public void sendingMobileToWebservice(String enteredMobileNumber) throws Exception {

        try {
            signUpNetworkOperationInterface=new SignUpNetworkOperation();
            signUpNetworkOperationInterface.signUp(enteredMobileNumber);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void responseMethod(String response) throws Exception {
WelcomeActivity.getInstance().responseMethod(response);
    }
}
