package com.kaizenmax.taikinys_icl.presenter;

import java.util.List;

public interface DemoL3ExecutionPresenterInterface {

    public void insertDemoL3Executiondata_Update(String dateOfExecution_entered, String dose_entered, String demoArea_entered,
                                                 String modifyDate_string, Integer stage, Integer demoL3SerialId,
                                                 String uploadFlagStatus, List<byte[]> attachmentList) throws Exception;
}
