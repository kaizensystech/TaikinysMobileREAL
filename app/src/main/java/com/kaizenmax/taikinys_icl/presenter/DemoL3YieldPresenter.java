package com.kaizenmax.taikinys_icl.presenter;

import com.kaizenmax.taikinys_icl.model.MobileDatabase;
import com.kaizenmax.taikinys_icl.view.DemoL3Activity_YieldResult;


public class DemoL3YieldPresenter implements DemoL3YieldPresenterInterface {
    MobileDatabase dbHelper =new MobileDatabase(DemoL3Activity_YieldResult.getInstance());

    @Override
    public void insertDemoL3Yielddata_Update(String dateOfYield_entered, String yieldResult_entered, String expense_entered,
                                             Integer demoL3SerialId, String modifyDate_string, String uploadFlagStatus,
                                             Integer stage) throws Exception {



        dbHelper.insertDemoL3Yielddata_Update( dateOfYield_entered,  yieldResult_entered,  expense_entered,
                 demoL3SerialId,  modifyDate_string,  uploadFlagStatus, stage);
    }
}
