package com.kaizenmax.taikinys_icl.pojo;

import java.util.Date;

public class MandiCampaignPojo {

    public static final String MANDICAMPAIGN_TABLE_NAME = "promomandicampaign";
    public static final String MANDICAMPAIGN_COLUMN_ID = "id";
    public static final String MANDICAMPAIGN_COLUMN_DATE_OF_ACTIVITY = "dateofactivity";
    public static final String MANDICAMPAIGN_COLUMN_MANDI_NAME = "mandiname";
    public static final String MANDICAMPAIGN_COLUMN_DISTRICT = "district";
    public static final String MANDICAMPAIGN_COLUMN_CROP_CATEGORY = "cropcategory";
    public static final String MANDICAMPAIGN_COLUMN_CROP_FOCUS = "cropfocus";
    public static final String MANDICAMPAIGN_COLUMN_EXPENSES = "expenses";
    public static final String MANDICAMPAIGN_COLUMN_CAMPAIGN_PURPOSE = "campaignpurpose";
    public static final String MANDICAMPAIGN_COLUMN_ACTIVITY_SUMMARY = "activitysummary";
    public static final String MANDICAMPAIGN_COLUMN_CREATED_ON = "createdon";
    public static final String MANDICAMPAIGN_COLUMN_CREATED_BY = "createdby";
    public static final String MANDICAMPAIGN_COLUMN_CLIENT_NAME = "clientname";
    public static final String MANDICAMPAIGN_COLUMN_UPLOAD_FLAG = "uploadflag";






































    private Date dateOfActivity;
    private String mandiName;
    private String district;
    private String cropCategory;
    private String cropName;
    //private String productName;
    private String campaignPurpose;
    private String activitySummary;
    private byte[] attachment;


    private Long id;

    private String createdBy;
    private Date createdOn;

    private Integer expenses;

  //  private Set<RetailerDetails> retailerDetails = new HashSet<RetailerDetails>(0);

   // private Set<ProductMaster> productMasters = new HashSet<ProductMaster>(0);

   // private Set<AttachmentMaster> attachmentMasters = new HashSet<AttachmentMaster>(0);





}
