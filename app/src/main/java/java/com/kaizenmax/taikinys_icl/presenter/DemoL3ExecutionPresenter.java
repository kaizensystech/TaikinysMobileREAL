package java.com.kaizenmax.taikinys_icl.presenter;



import com.kaizenmax.taikinys_icl.model.MobileDatabase;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ExecutionPresenterInterface;
import com.kaizenmax.taikinys_icl.view.DemoL3Activity_Execution;

import java.util.List;

public class DemoL3ExecutionPresenter implements DemoL3ExecutionPresenterInterface {
    MobileDatabase dbHelper =new MobileDatabase(DemoL3Activity_Execution.getInstance());
    @Override
    public void insertDemoL3Executiondata_Update(String dateOfExecution_entered, String dose_entered, String demoArea_entered,
                                                 String modifyDate_string, Integer stage, Integer demoL3SerialId,
                                                 String uploadFlagStatus, List<byte[]> attachmentList) throws Exception {
dbHelper.insertDemoL3Executiondata_Update(dateOfExecution_entered, dose_entered,  demoArea_entered,
         modifyDate_string,  stage,  demoL3SerialId,
         uploadFlagStatus, attachmentList);

    }
}
