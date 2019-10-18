package com.kaizenmax.taikinys_icl.pojo;


import java.util.Date;

public class DataSetMasterPojo
{

    /**
     *
     */
    public static final String DATASETMASTER_TABLE_NAME = "datasetmaster";
    public static final String DATASETMASTER_COLUMN_ID = "id";
    public static final String DATASETMASTER_COLUMN_DATASET_TITLE = "datasettitle";
    public static final String DATASETMASTER_COLUMN_DATASET_DESCRIPTION = "datasetdescription";
    public static final String DATASETMASTER_COLUMN_ELEMENT1 = "element1";
    public static final String DATASETMASTER_COLUMN_ELEMENT2 = "element2";
    public static final String DATASETMASTER_COLUMN_ELEMENT3 = "element3";
    public static final String DATASETMASTER_COLUMN_ELEMENT4 = "element4";
    public static final String DATASETMASTER_COLUMN_NUMBER1 = "number1";
    public static final String DATASETMASTER_COLUMN_NUMBER2 = "number2";
    public static final String DATASETMASTER_COLUMN_TEXT1 = "text1";
    public static final String DATASETMASTER_COLUMN_TEXT2 = "text2";
    public static final String DATASETMASTER_COLUMN_DATE1 = "date1";
    public static final String DATASETMASTER_COLUMN_DATE2 = "date2";
    public static final String DATASETMASTER_COLUMN_STATUS = "status";
    public static final String DATASETMASTER_COLUMN_CREATED_ON = "createdon";
    public static final String DATASETMASTER_COLUMN_CREATED_BY = "createdby";
    public static final String DATASETMASTER_COLUMN_CLIENT_NAME = "clientname";

    private String dataSetTitle;
    private String dataSetDescription;
    private String element1;
    private String element2;
    private String element3;
    private String element4;
    private String number1;
    private String number2;
    private String text1;
    private String text2;
    private Date date1;
    private Date date2;
    private String status;

    private Date createdOn;
    private String createdBy;
    private Long id;

    private String clientName;


    public String getDataSetTitle() {
        return dataSetTitle;
    }
    public void setDataSetTitle(String dataSetTitle) {
        this.dataSetTitle = dataSetTitle;
    }

    public String getDataSetDescription() {
        return dataSetDescription;
    }
    public void setDataSetDescription(String dataSetDescription) {
        this.dataSetDescription = dataSetDescription;
    }


    public String getElement1() {
        return element1;
    }
    public void setElement1(String element1) {
        this.element1 = element1;
    }

    public String getElement2() {
        return element2;
    }
    public void setElement2(String element2) {
        this.element2 = element2;
    }


    public String getElement3() {
        return element3;
    }
    public void setElement3(String element3) {
        this.element3 = element3;
    }


    public String getElement4() {
        return element4;
    }
    public void setElement4(String element4) {
        this.element4 = element4;
    }

    public String getNumber1() {
        return number1;
    }
    public void setNumber1(String number1) {
        this.number1 = number1;
    }


    public String getNumber2() {
        return number2;
    }
    public void setNumber2(String number2) {
        this.number2 = number2;
    }


    public String getText1() {
        return text1;
    }
    public void setText1(String text1) {
        this.text1 = text1;
    }


    public String getText2() {
        return text2;
    }
    public void setText2(String text2) {
        this.text2 = text2;
    }


    public Date getDate1() {
        return date1;
    }
    public void setDate1(Date date1) {
        this.date1 = date1;
    }


    public Date getDate2() {
        return date2;
    }
    public void setDate2(Date date2) {
        this.date2 = date2;
    }


    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }







}

