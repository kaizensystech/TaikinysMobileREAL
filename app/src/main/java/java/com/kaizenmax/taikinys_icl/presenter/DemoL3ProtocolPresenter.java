package java.com.kaizenmax.taikinys_icl.presenter;



import com.kaizenmax.taikinys_icl.model.MobileDatabase;
import com.kaizenmax.taikinys_icl.presenter.DemoL3ProtocolPresenterInterface;
import com.kaizenmax.taikinys_icl.view.DemoL3Activity_Protocol;

import java.util.List;

public class DemoL3ProtocolPresenter implements DemoL3ProtocolPresenterInterface {
    MobileDatabase dbHelper =new MobileDatabase(DemoL3Activity_Protocol.getInstance());
    @Override
    public void insertDemoL3Protocoldata_Update(String dateOfProtocol_entered, String cropCategory_entered,
                                                     String cropFocus_entered, String meetingPurpose_entered,
                                                     List<String> selectedProductList, Integer demoL3SerialId,
                                                     String uploadFlagStatus, String modifyDate_string,
                                                     Integer stage) throws Exception {

        dbHelper.insertDemoL3Protocoldata_Update(dateOfProtocol_entered,cropCategory_entered,
        cropFocus_entered,  meetingPurpose_entered,
         selectedProductList,  demoL3SerialId,
         uploadFlagStatus,  modifyDate_string, stage);

    }
}
