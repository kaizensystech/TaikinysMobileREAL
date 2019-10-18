package com.kaizenmax.taikinys_icl.pojo;


import java.util.Date;

public class PromoFarmerMeetingPojo   {
//OTHER PURPOSE
    public static final String PROMOFARMERMEETING_TABLE_NAME = "promofarmermeeting";
    public static final String PROMOFARMERMEETING_COLUMN_ID = "id";
    public static final String PROMOFARMERMEETING_COLUMN_CHOOSE_ACTIVITY = "chooseactivity";
    public static final String PROMOFARMERMEETING_COLUMN_DATE_OF_ACTIVITY = "dateofactivity";
    public static final String PROMOFARMERMEETING_COLUMN_NUMBER_OF_FARMER = "numberoffarmer";
    public static final String PROMOFARMERMEETING_COLUMN_VILLAGE = "village";
    public static final String PROMOFARMERMEETING_COLUMN_DISTRICT = "district";
    public static final String PROMOFARMERMEETING_COLUMN_CROP_CATEGORY = "cropcategory";
    public static final String PROMOFARMERMEETING_COLUMN_CROP_FOCUS = "cropfocus";
    public static final String PROMOFARMERMEETING_COLUMN_FOCUS_PRODUCT = "focusproduct";
    public static final String PROMOFARMERMEETING_COLUMN_EXPENSES = "expenses";
    public static final String PROMOFARMERMEETING_COLUMN_MEETING_PURPOSE = "meetingpurpose";
    public static final String PROMOFARMERMEETING_COLUMN_ATTACHMENT_FILE = "attachment";
    public static final String PROMOFARMERMEETING_COLUMN_LOCATION = "location";
    public static final String PROMOFARMERMEETING_COLUMN_STATUS = "status";
    public static final String PROMOFARMERMEETING_COLUMN_STAGE= "stage";
    public static final String PROMOFARMERMEETING_COLUMN_CREATED_ON = "createdon";
    public static final String PROMOFARMERMEETING_COLUMN_CREATED_BY = "createdby";
    public static final String PROMOFARMERMEETING_COLUMN_ATTACHMENT_FILE_NAME= "attachmentfilename";
    public static final String PROMOFARMERMEETING_COLUMN_OBSERVATIONS = "observations";
    public static final String PROMOFARMERMEETING_COLUMN_CLIENT_NAME = "clientname";
    public static final String PROMOFARMERMEETING_COLUMN_CROP_STAGE = "cropstage";
    public static final String PROMOFARMERMEETING_COLUMN_PROBLEM_CATEGORY = "problemcategory";
    public static final String PROMOFARMERMEETING_COLUMN_PROBLEM_SUB_CATEGORY = "problemsubcategory";
    public static final String PROMOFARMERMEETING_COLUMN_PROBLEM_DESCRIPTION = "problemdescription";
    public static final String PROMOFARMERMEETING_COLUMN_RECOMMENDATION = "recommendation";
    public static final String PROMOFARMERMEETING_COLUMN_INSTRUCTIONS_DOSE = "instructiondose";
    public static final String PROMOFARMERMEETING_COLUMN_EXPERT_HELP = "experthelp";
    public static final String PROMOFARMERMEETING_COLUMN_DESCRIPTION = "description";
    public static final String PROMOFARMERMEETING_COLUMN_NEXT_FIELD_VISIT_DATE = "nextfieldvisitdate";
    public static final String PROMOFARMERMEETING_COLUMN_UPLOAD_FLAG="uploadflag";










   //////////////////////////////////////////////////////////////////////////////////////////










    private Long id;
    private String chooseActivity;
    private Date dateOfActivity;
    private Integer numberOfFarmer;
    private String village;
    private String district;
    private String cropFocus;
    private String cropCategory;
    private String focusProduct;
    private Integer expenses;
    private String meetingPurpose;
    //	private String otherPurpose;
    private byte[] attachFile;

    private String location;
    private Boolean status;
    private Integer stage;
    private Date createdOn;
    private String createdBy;
    private String fileName;




    private String observations;

    private String clientName;



    // Added these following fields for FieldVisit
    private String cropStage;
    private String problemCategory;
    private String problemSubCategory;
    private String problemDescription;
    private String recommendation;
    private String instructionsDose;
    private Boolean expertHelp;
    private String description;


    //NEWLY ADDED

    private Date nextFvVisitDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChooseActivity() {
        return chooseActivity;
    }

    public void setChooseActivity(String chooseActivity) {
        this.chooseActivity = chooseActivity;
    }

    public Date getDateOfActivity() {
        return dateOfActivity;
    }

    public void setDateOfActivity(Date dateOfActivity) {
        this.dateOfActivity = dateOfActivity;
    }

    public Integer getNumberOfFarmer() {
        return numberOfFarmer;
    }

    public void setNumberOfFarmer(Integer numberOfFarmer) {
        this.numberOfFarmer = numberOfFarmer;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCropFocus() {
        return cropFocus;
    }

    public void setCropFocus(String cropFocus) {
        this.cropFocus = cropFocus;
    }

    public String getCropCategory() {
        return cropCategory;
    }

    public void setCropCategory(String cropCategory) {
        this.cropCategory = cropCategory;
    }

    public String getFocusProduct() {
        return focusProduct;
    }

    public void setFoucusProduct(String foucusProduct) {
        this.focusProduct = foucusProduct;
    }

    public Integer getExpenses() {
        return expenses;
    }

    public void setExpenses(Integer expenses) {
        this.expenses = expenses;
    }

    public String getMeetingPurpose() {
        return meetingPurpose;
    }

    public void setMeetingPurpose(String meetingPurpose) {
        this.meetingPurpose = meetingPurpose;
    }

    public byte[] getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(byte[] attachFile) {
        this.attachFile = attachFile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCropStage() {
        return cropStage;
    }

    public void setCropStage(String cropStage) {
        this.cropStage = cropStage;
    }

    public String getProblemCategory() {
        return problemCategory;
    }

    public void setProblemCategory(String problemCategory) {
        this.problemCategory = problemCategory;
    }

    public String getProblemSubCategory() {
        return problemSubCategory;
    }

    public void setProblemSubCategory(String problemSubCategory) {
        this.problemSubCategory = problemSubCategory;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getInstructionsDose() {
        return instructionsDose;
    }

    public void setInstructionsDose(String instructionsDose) {
        this.instructionsDose = instructionsDose;
    }

    public Boolean getExpertHelp() {
        return expertHelp;
    }

    public void setExpertHelp(Boolean expertHelp) {
        this.expertHelp = expertHelp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getNextFvVisitDate() {
        return nextFvVisitDate;
    }

    public void setNextFvVisitDate(Date nextFvVisitDate) {
        this.nextFvVisitDate = nextFvVisitDate;
    }
}
