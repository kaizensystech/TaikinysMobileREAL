package com.kaizenmax.taikinys_icl.model;

public interface DemoL3DataPushNetworkOperationInterface {
    public void sendingDemoL3DataToWebService() throws Exception;

    public void gettingDemoL3DataFromWebService(String mobileNumber) throws Exception;

}
