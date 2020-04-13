package java.com.kaizenmax.taikinys_icl.presenter;

public interface OtpVerificationActivityPresenterInterface {


    public void sendingMobileandOtpToWebservice(String mobileNumber, String otp) throws Exception;


public void successResponseMethod(String responsResult, Object response) throws Exception;
    public void errorResponseMethod(String result) throws  Exception;

    public void savingMobileAndOtpInSharedPreference(String enteredMobileNumber, String enteredOtp) throws  Exception;

    public void savingFaMasterInLocalDatabase(Object response) throws  Exception;
}
