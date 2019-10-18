package com.kaizenmax.taikinys_icl.presenter;

public interface WelcomeActivityPresenterInterface {



    public String getMobileNumberFromSharedPreference() throws  Exception;
    public void sendingMobileToWebservice(String mobileNumber) throws Exception;

    public void responseMethod(String response) throws  Exception;
}
