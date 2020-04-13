package java.com.kaizenmax.taikinys_icl.presenter;

import java.util.List;

public interface DemoL3ProtocolPresenterInterface {
    public void insertDemoL3Protocoldata_Update(String dateOfProtocol_entered, String cropCategory_entered,
                                                String cropFocus_entered, String meetingPurpose_entered,
                                                List<String> selectedProductList, Integer demoL3SerialId,
                                                String uploadFlagStatus, String modifyDate_string,
                                                Integer stage) throws Exception;
}
