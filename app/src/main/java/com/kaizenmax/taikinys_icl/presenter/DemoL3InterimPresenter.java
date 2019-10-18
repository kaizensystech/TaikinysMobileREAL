package com.kaizenmax.taikinys_icl.presenter;


import com.kaizenmax.taikinys_icl.model.MobileDatabase;
import com.kaizenmax.taikinys_icl.view.DemoL3_InterimResult;

public class DemoL3InterimPresenter implements DemoL3InterimPresenterInterface {

    MobileDatabase dbHelper =new MobileDatabase(DemoL3_InterimResult.getInstance());
    @Override
    public void insertDemoL3Interimdata_Update(String dateOfInterim_entered, String interimResult_entered, String modifyDate_string,
                                               Integer stage, String uploadFlagStatus, Integer demoL3SerialId) throws Exception {

        dbHelper.insertDemoL3Interimdata_Update( dateOfInterim_entered,  interimResult_entered,
                 modifyDate_string, stage,  uploadFlagStatus,  demoL3SerialId);
    }
}
